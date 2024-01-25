import { SEARCH_BOOKS, ADD_TO_FAVORITES, SEARCH_AUTHORS } from "./actions";

const initialState = {
  books: [],
  favorites: [],
  authors: [],
};

const rootReducer = (state = initialState, action) => {
  switch (action.type) {
    case SEARCH_BOOKS:
      return { ...state, books: performSearch(state.books, action.payload) };

    case SEARCH_AUTHORS:
      return {
        ...state,
        authors: performSearch(state.authors, action.payload),
      };

    case ADD_TO_FAVORITES:
      return { ...state, favorites: [...state.favorites, action.payload] };

    default:
      return state;
  }
};

const performSearch = (books, searchTerm) => {
  return books.filter((book) => book.title.includes(searchTerm));
};

export default rootReducer;
