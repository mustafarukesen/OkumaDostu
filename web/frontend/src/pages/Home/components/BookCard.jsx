import { BookImage } from "@/shared/components/BookImage";
import { useEffect, useState } from "react";
import "./../style.css";
import BookModal from "./BookModal";
import { Button } from "@/shared/components/Button";
import { addFavorite, deleteFavorite, loadFavorites } from "./api";
import { useAuthState } from "@/shared/state/context";
import Cookies from "js-cookie";

export function BookCard({ book }) {
  const [show, setShow] = useState(false);
  const [selectedBook, setSelectedBook] = useState(null);
  const authState = useAuthState();
  const [apiProgress, setApiProgress] = useState(false);
  const [favoriteBookIds, setFavoriteBookIds] = useState([]);

  const handleFavoriteClick = async () => {
    setApiProgress(true);
    try {
      const cookieValue = Cookies.get("okumadostu-token");
      if (favoriteBookIds.includes(book.id)) {
        const favoriteId = favoriteBookIds.find((id) => id === book.id);
        await deleteFavorite(cookieValue, favoriteId);
        setFavoriteBookIds(favoriteBookIds.filter((id) => id !== book.id));
      } else {
        await addFavorite(book.id, cookieValue);
        setFavoriteBookIds([...favoriteBookIds, book.id]);
      }
    } catch (error) {
      console.error("Arama sırasında bir hata oluştu:", error);
    } finally {
      setApiProgress(false);
    }
  };

  const getFavorites = async () => {
    setApiProgress(true);
    try {
      const response = await loadFavorites(authState.id);
      const favoriteIds = response.data.map((favorite) => favorite.bookId);
      setFavoriteBookIds(favoriteIds);
    } catch (error) {
      console.error("Arama sırasında bir hata oluştu:", error);
    } finally {
      setApiProgress(false);
    }
  };

  useEffect(() => {
    if (authState.id) {
      getFavorites();
    }
  }, [authState.id]);

  return (
    <>
      <div
        className="b-card"
        onClick={() => {
          setShow(true);
          setSelectedBook(book);
        }}
      >
        {" "}
        {authState.id > 0 && (
          <>
            <Button
              type="button"
              className="favorite-btn btn btn-outline-info"
              apiProgress={apiProgress}
              onClick={(e) => {
                e.stopPropagation();
                handleFavoriteClick();
              }}
            >
              {favoriteBookIds.includes(book.id) ? "+" : "-"}
            </Button>
          </>
        )}
        <BookImage image={book.thumbnail} />
        <div className="b-bottom">
          <h3 className="b-title">{book.title}</h3>
        </div>
      </div>
      {selectedBook && (
        <BookModal
          show={show}
          book={selectedBook}
          onClose={() => setShow(false)}
        />
      )}
    </>
  );
}
