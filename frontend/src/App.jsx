import { BrowserRouter } from "react-router-dom";
import "./App.css";
import Navigation from "./Navigation";

function App() {
  return (
    <div >
      <BrowserRouter>
        <Navigation />
      </BrowserRouter>
    </div>
  );
}

export default App;