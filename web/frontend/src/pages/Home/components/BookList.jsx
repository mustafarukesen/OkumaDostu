import { BookCard } from "./BookCard";
import logo from "@/assets/whiteLogo.png";
import { Button } from "@/shared/components/Button";
import { useCallback, useEffect, useState } from "react";
import { Input } from "@/shared/components/Input";
import { loadBooksWithAuthor, loadBooksWithTitle } from "./api";
import { Spinner } from "@/shared/components/Spinner";
import { loadBooks } from "./api";
import "./../style.css";

export function BookList() {
  const [searchTerm, setSearchTerm] = useState("");
  const [apiProgress, setApiProgress] = useState(false);
  const [bookPage, setBookPage] = useState({
    content: [],
    last: false,
    first: false,
    number: 0,
  });

  const handleBookSearch = async (page = 0, size = 14) => {
    setApiProgress(true);
    try {
      const response = await loadBooksWithTitle(searchTerm, page, size);
      setBookPage(response.data);
    } catch (error) {
      console.error("Arama sırasında bir hata oluştu:", error);
    } finally {
      setApiProgress(false);
    }
  };

  const handleAuthorSearch = async (page = 0, size = 14) => {
    setApiProgress(true);
    try {
      const response = await loadBooksWithAuthor(searchTerm, page, size);
      setBookPage(response.data);
    } catch (error) {
      console.error("Arama sırasında bir hata oluştu:", error);
    } finally {
      setApiProgress(false);
    }
  };

  const getBooks = useCallback(async (page) => {
    setApiProgress(true);
    try {
      const response = await loadBooks(page);
      setBookPage(response.data);
      setApiProgress(false);
    } catch {
    } finally {
      setApiProgress(false);
    }
  }, []);

  useEffect(() => {
    getBooks();
  }, [getBooks]);

  return (
    <>
      <div className="b-header">
        <div className="b-row1">
          <img src={logo} alt="Logo" />
          <h1>
            "Bugün acılarımdan ve üzüntümden biraz <br />
            uzaklaşmak için şöyle bir okumak aklıma geldi."
          </h1>
        </div>
        <div className="b-row2">
          <h2>Kitap Bul</h2>
          <div className="b-search">
            <Input
              id="title"
              label="Kitap arayınız"
              type="text"
              placeholder="Kitap ismi arayınız"
              labelColor="white"
              onChange={(event) => setSearchTerm(event.target.value)}
            />
            <Button
              apiProgress={apiProgress}
              onClick={() => handleBookSearch()}
              className="b-search"
            >
              Ara
            </Button>
            {apiProgress && <Spinner />}
          </div>
          <div className="b-search">
            <Input
              id="author"
              label="Yazar arayınız"
              type="text"
              placeholder="Yazar ismi arayınız"
              labelColor="white"
              onChange={(event) => setSearchTerm(event.target.value)}
            />
            <Button
              apiProgress={apiProgress}
              onClick={() => handleAuthorSearch()}
              className="b-search"
            >
              Ara
            </Button>
            {apiProgress && <Spinner />}
          </div>
        </div>
      </div>

      <div className="h-container">
        {bookPage.content.map((book) => (
          <BookCard key={book.id} book={book} />
        ))}
      </div>

      <nav aria-label="Page navigation example" className="">
        <ul className="pagination justify-content-center">
          <li className="page-item">
            {apiProgress && <Spinner />}
            {!apiProgress && !bookPage.first && (
              <button
                className="btn btn-outline-primary btn-sm float-start"
                onClick={() => getBooks(bookPage.number - 1)}
              >
                Önceki
              </button>
            )}
            {!apiProgress && !bookPage.last && (
              <button
                className="btn btn-outline-primary btn-sm float-end"
                onClick={() => getBooks(bookPage.number + 1)}
              >
                Sonraki
              </button>
            )}
          </li>
        </ul>
      </nav>
    </>
  );
}
