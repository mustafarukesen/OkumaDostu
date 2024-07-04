import logo from "@/assets/blackLogo.png";
import { useAuthDispatch, useAuthState } from "../../state/context";
import { Link, useNavigate } from "react-router-dom";
import { logout } from "./api";
import "./navStyle.css";
import { ProfileImage } from "../ProfileImage";

export function Navbar() {
  const authState = useAuthState();
  const dispatch = useAuthDispatch();
  const navigate = useNavigate();

  const onClickLogout = async () => {
    try {
      await logout();
      navigate("/");
    } catch {
    } finally {
      dispatch({ type: "logout-success" });
    }
  };

  return (
    <nav className="navbar navbar-expand-lg navbar-light bg-body-tertiary fixed-top">
      <div className="container-fluid">
        <Link className="navbar-brand" to="/">
          <img
            src={logo}
            alt="Logo"
            width="150"
            height="50"
            style={{ cursor: "pointer" }}
          />
        </Link>
        <p
          style={{
            marginTop: "12px",
            marginLeft: "500px",
            fontFamily: '"Frank Ruhl Libre", serif',
            fontSize: "large",
            fontWeight: "bold",
          }}
        >
          Hoş Geldiniz
        </p>
        <div className="collapse navbar-collapse" id="navbarNav">
          <ul className="navbar-nav ms-auto">
            {authState.id === 0 && (
              <>
                <li className="nav-item">
                  <Link
                    className="nav-link btn btn-outline-primary me-2"
                    to="/login"
                  >
                    Giriş Yap
                  </Link>
                </li>
                <li className="nav-item">
                  <Link className="nav-link btn btn-primary" to="/signup">
                    Kayıt Ol
                  </Link>
                </li>
              </>
            )}
            {authState.id > 0 && (
              <>
                <li className="nav-item">
                  <Link
                    className="nav-link btn btn-primary me-2"
                    to={`/user/recommendations`}
                  >
                    <span className="ms-2">Önerilerim</span>
                  </Link>
                </li>

                <li className="nav-item">
                  <Link
                    className="nav-link btn btn-primary me-2"
                    to={`/user/favorites`}
                  >
                    <span className="ms-2">Favorilerim</span>
                  </Link>
                </li>

                <li className="nav-item">
                  <Link
                    className="nav-link btn btn-primary me-2"
                    to={`/user/${authState.id}`}
                  >
                    <ProfileImage width={30} image={authState.image} />
                    <span className="ms-2">
                      {authState.firstName} {authState.lastName}
                    </span>
                  </Link>
                </li>
                <li className="nav-item">
                  <span
                    className="nav-link btn btn-danger"
                    role="button"
                    onClick={onClickLogout}
                  >
                    Çıkış Yap
                  </span>
                </li>
              </>
            )}
          </ul>
        </div>
      </div>
    </nav>
  );
}
