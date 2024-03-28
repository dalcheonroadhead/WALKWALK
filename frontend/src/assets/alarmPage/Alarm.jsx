import styles from "./Alarm.module.css";
import { useLocation, useNavigate } from "react-router-dom";


const Alarm = function(){

    
    const navigate = useNavigate();
    

    const moveToAlarmPage = function () {
        navigate("/Alarm")
    }


    return(
        <> 
        </>
    );
}

export default Alarm; 