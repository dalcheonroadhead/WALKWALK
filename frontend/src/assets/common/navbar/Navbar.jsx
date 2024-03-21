import styles from "./Navbar.module.css";
import RankIcon from "../../../../public/imgs/rank_icon.svg";

const Navbar = function(){
    return(
        <>
        <div className={styles.nav_container}>
            <img src={RankIcon}/>
        </div>
        </>
    );
}

export default Navbar;