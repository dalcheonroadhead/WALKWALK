import { useState } from 'react'
import { BrowserRouter, Route, Routes } from "react-router-dom";
import Main from "./assets/mainPage/Main";
import './App.css'

function App() {
  const [count, setCount] = useState(0)

  return (
    <>
      <BrowserRouter>
        <Routes>
          <Route path="/main" element={<Main />}></Route>
        </Routes>
      </BrowserRouter>
    </>
  )
}

export default App
