import React, { useState, useEffect } from "react";
import Navbar1 from "./Navbar";
import axios from "axios";
import { useAuth } from "./AuthContext";

import "bootstrap/dist/css/bootstrap.min.css";
import "./User.css";

export const User = () => {
  const { token, firstName } = useAuth();

  const [favorites, setFavorites] = useState([]);
  const [recommendations, setRecommendations] = useState([]);

  useEffect(() => {
    axios
      .get("http://localhost:8080/api/v1/favorites", {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      .then((response) => {
        setFavorites(response.data);
      })
      .catch((error) => {
        console.error("Error fetching favorites:", error);
      });

    axios
      .get("http://localhost:8080/api/v1/users/recommendations", {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      .then((response) => {
        setRecommendations(response.data);
      })
      .catch((error) => {
        console.error("Error fetching recommendations:", error);
      });
  }, [token]);

  return (
    <>
      <Navbar1 />

      <div className="profile-container">
        <div className="profile-section">
          <div className="profile-card">
            <h3>Merhaba, {firstName}</h3>
          </div>
        </div>
        <div className="profile-section mt-4">
          <div className="profile-card">
            <h3>Favorilerim</h3>
            <ul>
              {favorites.map((book) => (
                <li key={book.id}>
                  <img
                    src={book.thumbnail}
                    alt={book.title}
                    style={{ width: "70px", height: "100px" }}
                  />
                  <span>
                    {book.title} - {book.authorName}
                  </span>
                </li>
              ))}
            </ul>
          </div>
        </div>
        <div className="profile-section mt-4">
          <div className="profile-card">
            <h3>Önerilen Kitaplar</h3>
            <ul>
              {recommendations.map((book) => (
                <li key={book.id}>
                  <img
                    src={book.thumbnail}
                    alt={book.title}
                    style={{ width: "50px", height: "75px" }}
                  />
                  <span>
                    {book.title} - {book.authorName}
                  </span>
                </li>
              ))}
            </ul>
          </div>
        </div>
      </div>
    </>
  );
};

export default User;
