import { BookImage } from "@/shared/components/BookImage";
import { useState } from "react";
import "./../style.css";
import BookModal from "./BookModal";
import { Button } from "@/shared/components/Button";
import { addFavorite } from "./api";
import { useAuthState } from "@/shared/state/context";

export function BookCard({ book }) {
  const [show, setShow] = useState(false);
  const [selectedBook, setSelectedBook] = useState(null);
  const authState = useAuthState();
  const [apiProgress, setApiProgress] = useState(false);

  const handleFavoriteClick = async () => {
    setApiProgress(true);
    try {
      const response = await addFavorite(book.id, authState);
      setBookPage(response.data);
    } catch (error) {
      console.error("Arama sırasında bir hata oluştu:", error);
    } finally {
      setApiProgress(false);
    }
  };
  return (
    <>
      <div
        className="b-card"
        onClick={() => {
          setShow(true);
          setSelectedBook(book);
        }}
      >
        <Button
          type="button"
          className="favorite-btn btn btn-outline-info"
          apiProgress={apiProgress}
          onClick={(e) => {
            e.stopPropagation(); // Card tıklanmasını engelle
            handleFavoriteClick();
          }}
        >
          {/* {selectedBook ? "-" : "+"} */}
        </Button>
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
