import { Outlet } from "react-router-dom";
import { AuthenticationContext } from "./shared/state/context";
import { Navbar } from "./shared/components/Navbar";

function App() {
  return (
    <AuthenticationContext>
      <Navbar />
      <div className="container mt-3">
        <Outlet />
      </div>
      {/* <Outlet /> */}
    </AuthenticationContext>
  );
}

export default App;
