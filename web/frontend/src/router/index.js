import App from "@/App";
import { Home } from "@/pages/Home";
import { createBrowserRouter } from "react-router-dom";
import { SignUp } from "@/pages/Signup";
import { Login } from "@/pages/Login";
import { User } from "@/pages/User";
import { Favorites } from "@/pages/Favorites";
import { Activation } from "@/pages/Activation";
import { Recommendations } from "@/pages/Recommendations";

export default createBrowserRouter([
  {
    path: "/",
    Component: App,
    children: [
      {
        path: "/",
        index: true,
        Component: Home,
      },
      {
        path: "/signup",
        Component: SignUp,
      },
      {
        path: "/login",
        Component: Login,
      },
      {
        path: "/user/:id",
        Component: User,
      },
      {
        path: "/user/favorites",
        Component: Favorites,
      },
      {
        path: "/activate-account",
        Component: Activation,
      },
      {
        path: "/user/recommendations",
        Component: Recommendations,
      },
    ],
  },
]);
