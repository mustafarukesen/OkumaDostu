import React from "react";
const Modal = ({ show, item, onClose }) => {
  if (!show) {
    return null;
  }
  return (
    <>
      <div className="overlay">
        <div className="overlay-inner">
          <button className="close" onClick={onClose}>
            <i class="fas fa-times"></i>
          </button>
          <div className="inner-box">
            <img src={item.thumbnail} alt="" />
            <div className="info">
              <h1 title="Kitap İsmi">{item.title}</h1>
              <h4 title="Yazar">{item.authorName}</h4>
              <h4 title="Yayınevi">{item.publisherName}</h4>
              <h4 title="Kategori">{item.categoryName}</h4>
              <h5 title="Yayınlanma Tarihi">{item.publishedDate}</h5>
              <h6 title="Sayfa Sayısı">Sayfa Sayısı : {item.pageCount}</h6>
              <h6 title="ISBN13">ISBN13 Numarası : {item.isbn13}</h6>
            </div>
          </div>
          <h4 title="Açıklama" className="description">{item.description}</h4> <br></br>
        </div>
      </div>
    </>
  );
};
export default Modal;
