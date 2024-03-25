import { Outlet } from "react-router-dom";
import Navbar from "./assets/common/navbar/Navbar";

const Layout = function () {
  return(
    <>
      <Outlet />
      <Navbar></Navbar>
    </>
  )
}

export default Layout
