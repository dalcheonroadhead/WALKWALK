import { useState } from "react";
import styles from "./Main.module.css";

const Main = function(){

    const [tabIndex, settabIndex] = useState(0);

    const tabClickHandler = function(index){
        settabIndex(index)
    }

    const tabArr=[{
        tabTitle:(
            // <div className="my_container">
            //     <h1>나</h1>
            // </div>
            <li onClick={()=>tabClickHandler(0)}>나</li>
        ),
        tabCont:(
            <div>
                <h1>하이</h1>
            </div>
        )
    },
    {
        tabTitle:(
            <li onClick={()=>tabClickHandler(1)}>할리</li>
        ),
        tabCont:(
            <div>
                <h1>할리하이</h1>
            </div>
        )
    },
    {
        tabTitle:(
            <li onClick={()=>tabClickHandler(2)}>갈리</li>
        ),
        tabCont:(
            <div>
                <h1>갈리하이</h1>
            </div>
        )
    }
        
    ]
    return(
        <div>
            <div className="main_container">
                <ul className="mode_tabs">
                    {tabArr.map((mode, index)=>{
                        return mode.tabTitle
                    })}
                </ul>

                <div>
                    {tabArr[tabIndex].tabCont}
                </div>
            </div>
        </div>
    );
}

export default Main;