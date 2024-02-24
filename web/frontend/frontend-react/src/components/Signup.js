import React, { useState } from "react";
import "./Signup.css";
import axios from "axios";
import { useAuth } from "./AuthContext";
import { useNavigate } from "react-router-dom";
import Navbar1 from "./Navbar";

import user_icon from "./assets/name-surname.png";
import email_icon from "./assets/e-mail.png";
import password_icon from "./assets/password.png";

const Signup = () => {
  const [firstName, setFirsttname] = useState("");
  const [lastName, setLastName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const { setFirstName } = useAuth();
  const navigate = useNavigate();

  const handleRegister = async () => {
    try {
      const response = await axios.post(
        "http://localhost:8080/api/v1/auth/register",
        {
          firstName,
          lastName,
          email,
          password,
          confirmPassword,
        }
      );

      if (response.status === 200) {
        const newToken = response.data.access_token;
        const firstName = response.data.firstName;
        setFirstName(firstName);
        console.log(firstName);
        navigate("/login");
      } else {
        console.error("Kayıt işlemi başarısız.");
      }
    } catch (error) {
      console.error("Hata:", error.message);
    }
  };

  return (
    <>
      <Navbar1 />
      <div className="ls-container">
        <div className="ls-header">
          <div className="ls-text">Kayıt Ol</div>
          <div className="ls-underline"></div>
        </div>
        <div className="ls-inputs">
          <div className="ls-input">
            <img src={user_icon} alt="" />
            <input
              type="text"
              placeholder="İsim"
              value={firstName}
              onChange={(e) => setFirsttname(e.target.value)}
            />
          </div>
        </div>

        <div className="ls-inputs">
          <div className="ls-input">
            <img src={user_icon} alt="" />
            <input
              type="text"
              placeholder="Soyisim"
              value={lastName}
              onChange={(e) => setLastName(e.target.value)}
            />
          </div>
        </div>

        <div className="ls-inputs">
          <div className="ls-input">
            <img src={email_icon} alt="" />
            <input
              type="email"
              placeholder="Email Adresi"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
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

        <div className="ls-inputs">
          <div className="ls-input">
            <img src={password_icon} alt="" />
            <input
              type="password"
              placeholder="Şifre Tekrarı"
              value={confirmPassword}
              onChange={(e) => setConfirmPassword(e.target.value)}
            />
          </div>
        </div>
        <div className="ls-submit">
          <button className="ls-login" onClick={handleRegister}>
            Kayıt ol
          </button>
        </div>
      </div>
    </>
  );
};

export default Signup;
