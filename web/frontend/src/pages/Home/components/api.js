import http from "@/lib/http";

export function loadBooks(page = 0) {
  return http.get("/api/v1/books/pagination", { params: { page, size: 14 } });
}

export function loadBooksWithTitle(title, page = 0, size = 10) {
  return http.get("/api/v1/books/title", { params: { title, page, size } });
}

export function loadBooksWithAuthor(authorName, page = 0) {
  return http.get("/api/v1/books/author", {
    params: { authorName, page, size: 14 },
  });
}

export function addFavorite(bookId, token) {
  return http.post(
    "/api/v1/favorites",
    { bookId },
    {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    }
  );
}

export function deleteFavorite(token, id) {
  return http.delete("/api/v1/favorites", {
    params: { id },
    headers: {
      Authorization: `Bearer ${token}`,
    },
  });
}

export function loadFavorites(id) {
  return http.get("/api/v1/favorites", { params: { id } });
}
