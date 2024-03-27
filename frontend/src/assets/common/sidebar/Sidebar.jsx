import { useState } from 'react';
import styles from './Sidebar.module.css';
import { useEffect } from 'react';

const Sidebar = () =>{
    const [opened, setOpened] = useState(false);

    const handleSidebarClickEvent = ()=>{
        setOpened(!opened);
    }
    const handleSidebarCloseEvent = ()=>{
        setOpened(false);
    }
    useEffect(()=>{
        
    })
    return (
        <>
            <div className={`${styles.sidebar_container} ${opened && styles.sidebar_container_opened}`}>
                <div className={`${styles.hamburger_container} ${opened && styles.hamburger_container_opened}`} onClick={handleSidebarClickEvent}>
                    <div className={`${styles.hamburger} ${opened && styles.hamburger_1_opened}`}></div>
                    <div className={`${styles.hamburger} ${opened && styles.hamburger_2_opened}`}></div>
                    <div className={`${styles.hamburger} ${opened && styles.hamburger_3_opened}`}></div>
                </div>
            </div>
        </>
    )
}

export default Sidebar;