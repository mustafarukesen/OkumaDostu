import Modal from "./Modal";
import React, { useState, useEffect } from "react";
import { useAuth } from "./AuthContext";
import axios from "axios";

const Card = ({ book }) => {
  const [show, setShow] = useState(false);
  const [bookItem, setItem] = useState();
  const [favoriteBooks, setFavoriteBooks] = useState([]);
  const { token } = useAuth();

  useEffect(() => {
    if (token) {
      axios
        .get("http://localhost:8080/api/v1/favorites", {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        })
        .then((response) => {
          setFavoriteBooks(response.data);
        })
        .catch((error) => {
          console.error("Error fetching favorite books:", error);
        });
    }
  }, [token]);

  const isBookLiked = (bookId) => {
    return favoriteBooks.some((favorite) => favorite.bookId === bookId);
  };

  const handleCardClick = (item) => {
    setShow(true);
    setItem(item);
  };

  const handleHeartClick = (e, id) => {
    e.stopPropagation();

    if (token) {
      const isLiked = isBookLiked(id);

      if (isLiked) {
        const favoriteIdToRemove = favoriteBooks.find(
          (favorite) => favorite.bookId === id
        ).id;

        axios
          .delete(
            `http://localhost:8080/api/v1/favorites/${favoriteIdToRemove}`,
            {
              headers: {
                Authorization: `Bearer ${token}`,
              },
            }
          )
          .then((response) => {
            console.log(
              "Book removed from favorites successfully:",
              response.data
            );
            setFavoriteBooks(
              favoriteBooks.filter(
                (favorite) => favorite.id !== favoriteIdToRemove
              )
            );
          })
          .catch((error) => {
            console.error("Error removing book from favorites:", error);
          });
      } else {
        axios
          .post(
            "http://localhost:8080/api/v1/favorites",
            { bookId: id },
            {
              headers: {
                Authorization: `Bearer ${token}`,
              },
            }
          )
          .then((response) => {
            console.log("Book added to favorites successfully:", response.data);
            setFavoriteBooks([...favoriteBooks, response.data]);
          })
          .catch((error) => {
            console.error("Error adding book to favorites:", error);
          });
      }
    } else {
      window.location.href = "/login";
    }
  };

  return (
    <>
      {book.map((item) => {
        return (
          <div key={item.id} onClick={() => handleCardClick(item)}>
            <div className="card">
              <button
                className={`heart-button ${
                  isBookLiked(item.id) ? "liked" : ""
                }`}
                onClick={(e) => handleHeartClick(e, item.id)}
              >
                ❤
              </button>

              <img src={item.thumbnail} alt="" width={150} height={200} />
              <div className="bottom">
                <h3 className="title">{item.title} </h3>
                <h4 className="author">{item.authorName}</h4>
              </div>
            </div>
          </div>
        );
      })}
      <Modal show={show} item={bookItem} onClose={() => setShow(false)} />
    </>
  );
};

export default Card;
