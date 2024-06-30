import { useEffect } from "react";
import "./../style.css";

const BookModal = ({ show, book, onClose }) => {
  useEffect(() => {
    const handleScroll = (event) => {
      event.preventDefault();
    };

    if (show) {
      document.body.classList.add("modal-open");
      document.addEventListener("scroll", handleScroll, { passive: false });
    } else {
      document.body.classList.remove("modal-open");
      document.removeEventListener("scroll", handleScroll);
    }

    return () => {
      document.body.classList.remove("modal-open");
      document.removeEventListener("scroll", handleScroll);
    };
  }, [show]);

  if (!show) {
    return null;
  }

  return (
    <div className="b-overlay">
      <div className="b-overlay-inner">
        <button className="btn-close float-end" onClick={onClose}>
          <i className="fas fa-times"></i>
        </button>
        <div className="b-inner-box">
          <img src={book.thumbnail} alt="Book Thumbnail" />
          <div className="info">
            <h1 title="Kitap İsmi">{book.title}</h1>
            <h4 title="Yazar">{book.authorName}</h4>
            <h4 title="Yayınevi">{book.publisherName}</h4>
            <h4 title="Kategori">{book.categoryName}</h4>
            <h5 title="Yayınlanma Tarihi">{book.publishedDate}</h5>
            <h6 title="Sayfa Sayısı">Sayfa Sayısı : {book.pageCount}</h6>
            <h6 title="ISBN13">ISBN13 Numarası : {book.isbn13}</h6>
          </div>
        </div>
        <h4 title="Açıklama" className="description">
          {book.description}
        </h4>
        <br />
      </div>
    </div>
  );
};

export default BookModal;
