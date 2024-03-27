import { Outlet } from "react-router-dom";
import Navbar from "./assets/common/navbar/Navbar";
import Toolbar from "./assets/common/toolbar/Toolbar";
import Sidebar from "./assets/common/sidebar/Sidebar";

const Layout = function () {
  return(
    <>
      <Toolbar></Toolbar>
      <Sidebar/>
      <Outlet />
      <Navbar></Navbar>
    </>
  )
}

export default Layout
