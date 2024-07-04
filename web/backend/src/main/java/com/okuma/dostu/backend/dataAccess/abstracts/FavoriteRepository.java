package com.okuma.dostu.backend.dataAccess.abstracts;

import com.okuma.dostu.backend.core.security.user.User;
import com.okuma.dostu.backend.entities.concretes.Book;
import com.okuma.dostu.backend.entities.concretes.Favorite;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {
    boolean existsByUserAndBook(User user, Book book);

    List<Favorite> findByUser(User user);

    @Query("select DISTINCT A.name " +
            "FROM Favorite F " +
            "JOIN Book B ON F.book = B " +
            "JOIN Author A ON B.author = A " +
            "WHERE F.user = :user")
    Page<String> findUserAuthors(@Param("user") User user, Pageable pageable);

    @Query("select DISTINCT C.name " +
            "FROM Favorite F " +
            "JOIN Book B ON F.book = B " +
            "JOIN Category C ON B.category = C " +
            "WHERE F.user = :user")
    Page<String> findUserCategories(@Param("user") User user, Pageable pageable);

    @Query("Select b.id " +
            "FROM Favorite f " +
            "INNER JOIN Book b on f.book = b " +
            "INNER JOIN User u on f.user = u " +
            "WHERE u.id = :id")
    List<Integer> findByUserId(Integer id);

    @Query("Select f " +
            "FROM Favorite f " +
            "INNER JOIN Book b on f.book = b " +
            "INNER JOIN User u on f.user = u " +
            "WHERE b.id = :id " +
            "AND u.id = :userId")
    Favorite findByBookId(Integer userId, int id);
}
