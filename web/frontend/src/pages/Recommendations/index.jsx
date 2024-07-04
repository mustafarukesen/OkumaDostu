import { useEffect, useState } from "react";
import { getRecommendations } from "./api";
import { BookCard } from "@/pages/Home/components/BookCard";
import { useAuthState } from "@/shared/state/context";
import Cookies from "js-cookie";

export function Recommendations() {
  const authState = useAuthState();
  const [bookPage, setBookPage] = useState({
    content: [],
    last: false,
    first: false,
    number: 0,
  });
  const [apiProgress, setApiProgress] = useState(false);

  const fetchRecommendation = async (page) => {
    setApiProgress(true);
    try {
      const token = Cookies.get("okumadostu-token");
      const response = await getRecommendations(token, page);
      setBookPage(response.data);
    } catch (error) {
      console.error("Önerilen kitaplar yüklenirken bir hata oluştu:", error);
    } finally {
      setApiProgress(false);
    }
  };

  useEffect(() => {
    if (authState.id) {
      fetchRecommendation();
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
            <h1>Öneriler</h1>
          </div>
          <div className="card-body">
            <div className="h-container">
              {bookPage.content.map((book) => (
                <BookCard key={book.id} book={book} />
              ))}
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
