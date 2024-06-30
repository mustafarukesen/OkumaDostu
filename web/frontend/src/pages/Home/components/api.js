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

export function addFavorite(bookId, user) {
  return http.post("/api/v1/favorites", { params: { bookId, user } });
}
