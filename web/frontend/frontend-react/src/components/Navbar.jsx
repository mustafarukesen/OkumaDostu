import React, { useState } from "react";
import "bootstrap";
import { Link, useNavigate } from "react-router-dom";
import { useAuth } from "./AuthContext";
import "./Navbar.css";
import axios from "axios";

const Navbar1 = () => {
  const navigate = useNavigate();
  const { firstName, token } = useAuth();
  const [offcanvasVisible, setOffcanvasVisible] = useState(false);
  const { setToken } = useAuth();

  const goToLogin = () => {
    navigate("/login");
    setOffcanvasVisible(false);
  };

  const goToHomeClick = () => {
    navigate("/");
    setOffcanvasVisible(false);
  };
  const goToRegister = async () => {
    navigate("/signup");
    setOffcanvasVisible(false);
  };

  const goToProfile = () => {
    navigate("/profile");
    setOffcanvasVisible(false);
  };

  const toggleOffcanvas = () => {
    setOffcanvasVisible(!offcanvasVisible);
  };

  const signOut = () => {
    axios
      .post("http://localhost:8080/api/v1/auth/logout", {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      .then((response) => {
        console.log(response.data);
        setToken(response.data);
        navigate("/");
      })
      .catch((error) => {
        console.error("Error fetching favorite books:", error);
      });
  };

  return (
    <>
      <nav class="navbar bg-body-tertiary fixed-top">
        <div class="container-fluid">
          <img
            src="images/blackLogo.png"
            alt="Logo"
            width="150"
            height="50"
            onClick={goToHomeClick}
            style={{ cursor: "pointer" }}
          ></img>
          {token ? (
            <p className="n-welcome">Hoş geldiniz, {firstName}</p>
          ) : (
            <>
              <button className="o-session" type="button" onClick={goToLogin}>
                Oturum Açın
              </button>
            </>
          )}

          <button
            className="navbar-toggler"
            type="button"
            onClick={toggleOffcanvas}
          >
            <span className="navbar-toggler-icon"></span>
          </button>

          <div
            className={`offcanvas offcanvas-end ${
              offcanvasVisible ? "show" : ""
            }`}
            tabIndex="-1"
          >
            <div class="offcanvas-header text-start">
              <img
                src="images/blackLogo.png"
                alt="Logo"
                width="150"
                height="50"
              ></img>
              <button
                type="button"
                class="btn-close"
                onClick={toggleOffcanvas}
                data-bs-dismiss="offcanvas"
                aria-label="Close"
              ></button>
            </div>
            {token ? (
              <div class="offcanvas-body">
                <ul class="navbar-nav justify-content-end flex-grow-1 pe-3">
                  <li class="nav-item">
                    <label className="nav-menu-item" onClick={goToHomeClick}>
                      Anasayfa
                    </label>
                  </li>

                  <li className="nav-item">
                    <label className="nav-menu-item" onClick={goToProfile}>
                      Profilim
                    </label>
                  </li>
                  <li className="nav-item">
                    <label className="nav-menu-item" onClick={signOut}>
                      Çıkış yap
                    </label>
                  </li>
                </ul>
              </div>
            ) : (
              <div class="offcanvas-body">
                <ul class="navbar-nav justify-content-end flex-grow-1 pe-3">
                  <li class="nav-item">
                    <label className="nav-menu-item" onClick={goToHomeClick}>
                      Anasayfa
                    </label>
                  </li>

                  <li className="nav-item">
                    <label className="nav-menu-item" onClick={goToLogin}>
                      Giriş yap
                    </label>
                  </li>
                  <li className="nav-item">
                    <label className="nav-menu-item" onClick={goToRegister}>
                      Kayıt ol
                    </label>
                  </li>
                </ul>
              </div>
            )}
          </div>
        </div>
      </nav>
    </>
  );
};

export default Navbar1;
