import React, { useState } from "react";
import "./Login.css";
import axios from "axios";
import { useAuth } from "./AuthContext";
import email_icon from "./assets/e-mail.png";
import password_icon from "./assets/password.png";

import { useNavigate } from "react-router-dom";
import Navbar1 from "./Navbar";

const Login = () => {
  const [email, setEmaill] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();
  const { setLastName, setEmail, setFirstName, setToken } = useAuth();

  const handleLogin = async () => {
    try {
      const response = await axios.post(
        "http://localhost:8080/api/v1/auth/login",
        {
          email,
          password,
        }
      );

      if (response.status === 200) {
        const newToken = response.data.access_token;
        const firstName = response.data.firstName;
        const email = response.data.email;
        const lastName = response.data.lastName;
        setEmail(email);
        setLastName(lastName);
        setFirstName(firstName);
        console.log(lastName, firstName, email);
        setToken(newToken);
        navigate("/");
      } else {
        console.error("Kayıt işlemi başarısız.");
      }
    } catch (error) {
      console.error("Hata:", error.message);
    }
  };

  const goToRegister = async () => {
    navigate("/signup");
  };

  return (
    <>
      <Navbar1 />
      <div className="login-container">
        <div className="ls-header">
          <div className="ls-text">Giriş Yap</div>
          <div className="ls-underline"></div>
        </div>
        <div className="ls-inputs">
          <div className="ls-input">
            <img src={email_icon} alt="" />
            <input
              type="email"
              placeholder="Email Adresi"
              value={email}
              onChange={(e) => setEmaill(e.target.value)}
            />
          </div>
        </div>
        <div className="ls-inputs">
          <div className="ls-input">
            <img src={password_icon} alt="" />
            <input
              type="password"
              placeholder="Şifre"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
            />
          </div>
        </div>
        <div className="ls-forgot-password">
          <label className="l-login" onClick={goToRegister}>
            Hesap oluşturun
          </label>
        </div>
        <div className="ls-submit">
          <button className="ls-login" onClick={handleLogin}>
            Giriş yap
          </button>
        </div>
      </div>
    </>
  );
};

export default Login;
