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
import MyWallet from "./assets/myPage/MyWallet";
import UserInfoUpdate from "./assets/myPage/UserInfoUpdate";
import Alarm from "./assets/alarmPage/Alarm";
import Report from "./assets/reportPage/Report";
import Yesterday from "./assets/yesterdayPage/Yesterday";
import Treasure from "./assets/treasurePage/Treasure";
import Voice from "./assets/voicePage/Voice";
import Store from "./assets/storePage/Store";
import './App.css'

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
        </Route>
        {/* 상하단바 없는 페이지 */}
        <Route path="/" element={<Login />}></Route>
        <Route path="/oauth/callback/google/token" element={<LoginRedirect />}></Route>
        <Route path="/signup" element={<Signup />}></Route>
        <Route path="/halligalli" element={<HalliGalli />}></Route>
        <Route path="/mywallet" element={<MyWallet />}></Route>
        <Route path="/userinfoupdate" element={<UserInfoUpdate />}></Route>
        <Route path="/alarm" element={<Alarm />}></Route>
        <Route path="/report" element={<Report />}></Route>
        <Route path="/yesterday" element={<Yesterday />}></Route>
        <Route path="/treasure" element={<Treasure />}></Route>
        <Route path="/voice" element={<Voice />}></Route>
        <Route path="/store" element={<Store />}></Route>
      </Routes>
    </BrowserRouter>
  )
}

export default App
