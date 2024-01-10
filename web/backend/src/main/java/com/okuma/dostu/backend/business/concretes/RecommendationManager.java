package com.okuma.dostu.backend.business.concretes;

import com.okuma.dostu.backend.business.abstracts.RecommendationService;
import com.okuma.dostu.backend.business.dtos.responses.users.GetAllRecommendationResponse;
import com.okuma.dostu.backend.core.security.user.User;
import com.okuma.dostu.backend.core.utilities.mappers.ModelMapperService;
import com.okuma.dostu.backend.dataAccess.abstracts.BookRepository;
import com.okuma.dostu.backend.dataAccess.abstracts.FavoriteRepository;
import com.okuma.dostu.backend.entities.concretes.Book;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RecommendationManager implements RecommendationService {

    private BookRepository bookRepository;
    private FavoriteRepository favoriteRepository;
    private ModelMapperService modelMapperService;

    @Override
    public List<GetAllRecommendationResponse> getAll(Principal connectedUser) {
        var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();

        List<String> userFavoriteAuthors = favoriteRepository.findUserAuthors(user);
        List<String> userFavoriteCategories = favoriteRepository.findUserCategories(user);

        List<Book> similarBooks = calculateJaccardSimilarity(userFavoriteAuthors, userFavoriteCategories);

        List<GetAllRecommendationResponse> getAllRecommendationResponses = similarBooks.stream()
                .map(similar -> modelMapperService.forResponse()
                        .map(similar, GetAllRecommendationResponse.class)).collect(Collectors.toList());

        return getAllRecommendationResponses;
    }

    private List<Book> calculateJaccardSimilarity(List<String> userFavoriteAuthors, List<String> userFavoriteCategories) {

        List<Book> allBooks = bookRepository.findAll();

        List<Book> similarBooks = allBooks.stream()
                .filter(book -> {
                    String bookCategoryName = book.getCategory().getName();
                    String bookAuthorName = book.getAuthor().getName();

                    double categorySimilarity = calculateJaccardIndex(userFavoriteCategories, Collections.singletonList(bookCategoryName));
                    double authorSimilarity = calculateJaccardIndex(userFavoriteAuthors, Collections.singletonList(bookAuthorName));

                    double totalSimilarity = categorySimilarity + authorSimilarity;

                    return totalSimilarity > 0.5;

                }).collect(Collectors.toList());

        Collections.shuffle(similarBooks);
        List<Book> selectedBooks = similarBooks.stream()
                .distinct()
                .limit(4)
                .collect(Collectors.toList());

        return selectedBooks;
    }

    private double calculateJaccardIndex(List<String> set1, List<String> set2) {
        Set<String> intersection = new HashSet<>(set1);
        intersection.retainAll(new HashSet<>(set2));

        Set<String> union = new HashSet<>(set1);
        union.addAll(new HashSet<>(set2));

        double intersectionSize = intersection.size();
        double unionSize = union.size();

        if (unionSize == 0) {
            return 0.0;
        }

        return intersectionSize / unionSize;
    }
}
