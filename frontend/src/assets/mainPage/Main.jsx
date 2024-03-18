import { useState } from "react";
import styles from "./Main.module.css";

const Main = function(){

    const [tabIndex, settabIndex] = useState(0);

    const tabArr=[{
        tabTitle:(
            <div className="my_container">
                <h1>나</h1>
            </div>
        ),
        tabCon
    }
        
    ]
    return(
        <div>
            <div className="main_container">
                <div className="mode_tabs">
                    {tabArr.map((mode, index)=>{
                        return mode.tabTitle
                    })}
                </div>
            </div>
        </div>
    );
}

export default Main;