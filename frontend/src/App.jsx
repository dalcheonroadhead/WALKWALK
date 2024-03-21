import { useState } from 'react'
import { BrowserRouter, Route, Routes } from "react-router-dom";
import Main from "./assets/mainPage/Main";
import Login from "./assets/memberPage/Login";
import './App.css'

function App() {
  const [count, setCount] = useState(0)

  return (
    <BrowserRouter>
      <Routes>
        <Route path="/main" element={<Main />}></Route>
        <Route path="/login" element={<Login />}></Route>
      </Routes>
    </BrowserRouter>
  )
}

export default App
