import React, { useState, useEffect } from "react";
import Card from "./Card";

import "./style.css";
import Navbar1 from "./Navbar";
import axios from "axios";

const HomePage = () => {
  const [search, setSearch] = useState("");
  const [bookData, setData] = useState([]);
  const [authorSearch, setAuthorSearch] = useState([]);

  useEffect(() => {
    listBook();
  }, []);
  const listBook = () => {
    axios
      .get("http://localhost:8080/api/v1/books")
      .then((res) => {
        if (res.status === 200) {
          const data = res.data;
          const bookItems = data.items || data;

          setData(bookItems);
        } else {
          console.error("API request failed with status:", res.status);
        }
      })
      .catch((err) => console.log(err));
  };

  const searchBook = () => {
    axios
      .get("http://localhost:8080/api/v1/books/title?title=" + search)
      .then((res) => {
        if (res.status === 200) {
          const data = res.data;
          const bookItems = data.items || data;

          setData(bookItems);
        } else {
          console.error("API request failed with status:", res.status);
        }
      })
      .catch((err) => console.log(err));
  };

  const searchAuthor = () => {
    axios
      .get(
        "http://localhost:8080/api/v1/books/author?authorName=" + authorSearch
      )
      .then((res) => {
        if (res.status === 200) {
          const data = res.data;
          const bookItems = data.items || data;
          setData(bookItems);
        } else {
          console.error("API request failed with status:", res.status);
        }
      })
      .catch((err) => console.error("API request error:", err));
  };
  const handleButtonBookSearchClick = () => {
    searchBook();
  };

  const handleButtonAuthorSearchClick = () => {
    searchAuthor();
  };

  return (
    <>
      <Navbar1 />
      <div className="header">
        <div className="row1">
          <img src="images/whiteLogo.png" alt="" />
          <h1>
            "Bugün acılarımdan ve üzüntümden biraz <br />
            uzaklaşmak için şöyle bir okumak aklıma geldi."
          </h1>
        </div>
        <div className="row2">
          <h2>Kitap Arayınız</h2>
          <div className="search">
            <input
              type="text"
              placeholder="Kitap ismi yazınız."
              value={search}
              onChange={(e) => setSearch(e.target.value)}
              onKeyDown={searchBook}
            />
            <button onClick={handleButtonBookSearchClick}>
              <i class="fas fa-search"></i>
            </button>
          </div>
          <h2>Yazar Arayınız</h2>
          <div className="search">
            <input
              type="text"
              placeholder="Yazar ismi arayınız"
              value={authorSearch}
              onChange={(e) => setAuthorSearch(e.target.value)}
              onKeyDown={searchAuthor}
            />

            <button onClick={handleButtonAuthorSearchClick}>
              <i class="fas fa-search"></i>
            </button>
          </div>
        </div>
      </div>
      <div className="h-container">{<Card book={bookData} />}</div>
    </>
  );
};
export default HomePage;
