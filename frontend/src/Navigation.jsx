import { lazy, Suspense } from "react";
import { Route, Routes } from "react-router-dom";

const Login = lazy(() => import("./components/Login/Login"));

const routes = Object.freeze([
  { path: "/", element: <Login />, auth: false },
]);

const Navigation = () => {
  return (
    <main className="App">
      <Suspense fallback={<div>Loading...</div>}>
        <Routes>
          {routes.map(({ path, element }, idx) => (
            <Route key={idx} path={path} element={element} />
          ))}
        </Routes>
      </Suspense>
    </main>
  );
};

export default Navigation;