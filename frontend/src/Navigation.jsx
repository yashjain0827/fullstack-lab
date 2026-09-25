import { lazy } from "react";
import { Route, Routes } from "react-router-dom";

const Login = lazy(() => import("./components/Login"));

// Route config
const routes = Object.freeze([
  { path: "/", element: <Login />, auth: false },
]);

const Navigation = () => {
  return (
    <main className="App">
      <Routes>
        {routes?.map(({ path, element }, idx) => (
          <Route key={idx} path={path} element={element} />
        ))}
      </Routes>
    </main>
  );
};

export default Navigation;
