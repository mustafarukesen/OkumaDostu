import { useEffect, useState } from "react";
import { loadFavorites } from "./api";
import { BookCard } from "@/pages/Home/components/BookCard";
import { useAuthState } from "@/shared/state/context";
import Cookies from "js-cookie";
import { Button } from "@/shared/components/Button";
import { Spinner } from "@/shared/components/Spinner";
import "./style.css";

export function Favorites() {
  const authState = useAuthState();
  const [bookPage, setBookPage] = useState({
    content: [],
    last: false,
    first: false,
    number: 0,
  });
  const [apiProgress, setApiProgress] = useState(false);

  const fetchFavorites = async (page) => {
    setApiProgress(true);
    try {
      const token = Cookies.get("okumadostu-token");
      const response = await loadFavorites(token, page);
      setBookPage(response.data);
    } catch (error) {
      console.error("Favori kitaplar yüklenirken bir hata oluştu:", error);
    } finally {
      setApiProgress(false);
    }
  };

  useEffect(() => {
    if (authState.id) {
      fetchFavorites();
    }
  }, [authState.id]);

  return (
    <div
      className="container"
      style={{ marginLeft: "100px", marginRight: "100px", marginTop: "100px" }}
    >
      <div className="col-lg-6 offset-lg-3 col-sm-8 offset-sm-2">
        <div className="card">
          <div className="text-center card-header">
            <h1>Favorilerim</h1>
          </div>
          <div className="card-body">
            <div className="h-container">
              {bookPage.content.map((book) => (
                <BookCard key={book.id} book={book} />
              ))}
            </div>
          </div>
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
                  Previous
                </button>
              )}
              {!apiProgress && !bookPage.last && (
                <button
                  className="btn btn-outline-primary btn-sm float-end"
                  onClick={() => getBooks(bookPage.number + 1)}
                >
                  Next
                </button>
              )}
            </li>
          </ul>
        </nav>
      </div>
    </div>
  );
}
