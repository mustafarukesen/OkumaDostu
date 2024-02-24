import React, { Component } from "react";
import HomePage from "./components/HomePage";
import "./components/style.css";
import { BrowserRouter, Route, Routes } from "react-router-dom";

import Signup from "./components/Signup";
import Login from "./components/Login";
import User from "./components/User";

export default class App extends Component {
  render() {
    return (
      <>
        <BrowserRouter>
          <Routes>
            <Route path="/" element={<HomePage />}></Route>
            <Route path="/signup" element={<Signup />} />
            <Route path="/login" element={<Login />} />
            <Route path="/profile" element={<User />} />
          </Routes>
        </BrowserRouter>
      </>
    );
  }
}
