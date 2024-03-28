import { BrowserRouter, Route, Routes } from "react-router-dom";
import Layout from "./Layout";
import Main from "./assets/mainPage/Main";
import Login from "./assets/memberPage/Login";
import LoginRedirect from "./assets/memberPage/LoginRedirect";
import Signup from "./assets/memberPage/Signup";
import HalliGalli from "./assets/halligalliPage/HalliGalli";
import Rank from "./assets/rankPage/Rank";
import Walking from "./assets/walkingPage/Walking";
import Friend from "./assets/friendPage/Friend";
import Mypage from "./assets/myPage/Mypage";
import './App.css'
import SocketPage4Member from "./assets/exercisePage/SocketPage4Member";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        {/* 상하단바 보이는 페이지 */}
        <Route element={<Layout />}>
          <Route path="/main" element={<Main />}></Route>
          <Route path="/rank" element={<Rank />}></Route>
          <Route path="/walking" element={<Walking />}></Route>
          <Route path="/friend" element={<Friend />}></Route>
          <Route path="/mypage" element={<Mypage />}></Route>
          <Route path="/member/:id" element={<SocketPage4Member/>}></Route>
        </Route>
        {/* 상하단바 없는 페이지 */}
        <Route path="/" element={<Login />}></Route>
        <Route path="/oauth/callback/google/token" element={<LoginRedirect />}></Route>
        <Route path="/signup" element={<Signup />}></Route>
        <Route path="/halligalli" element={<HalliGalli />}></Route>
      </Routes>
    </BrowserRouter>
  )
}

export default App
