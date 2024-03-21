import { BrowserRouter, Route, Routes } from "react-router-dom";
import Main from "./assets/mainPage/Main";
import Login from "./assets/memberPage/Login";
import LoginRedirect from "./assets/memberPage/LoginRedirect";
import './App.css'

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Login />}></Route>
        <Route path="/oauth/callback/google/token" element={<LoginRedirect />}></Route>
        <Route path="/main" element={<Main />}></Route>
      </Routes>
    </BrowserRouter>
  )
}

export default App
