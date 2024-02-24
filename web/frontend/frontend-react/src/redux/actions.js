export const SEARCH_BOOKS = "SEARCH_BOOKS";
export const ADD_TO_FAVORITES = "ADD_TO_FAVORITES";
export const SEARCH_AUTHORS = "SEARCH_AUTHORS"

export const searchBooks = (searchTerm) => ({
  type: SEARCH_BOOKS,
  payload: searchTerm,
});

export const searchAuthors = (searchTerm) => ({
    type: SEARCH_AUTHORS,
    payload: searchTerm,
  });

export const addToFavorites = (book) => ({
  type: ADD_TO_FAVORITES,
  payload: book,
});
