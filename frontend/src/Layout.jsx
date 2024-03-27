import { Outlet } from "react-router-dom";
import Navbar from "./assets/common/navbar/Navbar";
import Toolbar from "./assets/common/toolbar/Toolbar";
import Sidebar from "./assets/common/sidebar/Sidebar";
import styles from "./Layout.module.css"

const Layout = function () {
  return(
    <div className={styles.layoutContainer}>
      <div className={styles.toolbar}><Toolbar></Toolbar></div>
      <Sidebar/>
      <div className={styles.content}><Outlet /></div>
      <div className={styles.navbar}><Navbar></Navbar></div>
    </div>
  )
}

export default Layout
