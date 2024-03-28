import { useState } from 'react';
import styles from './Calendar.module.css';

const Calendar = () => {
  const [currentDate, setCurrentDate] = useState(new Date());
  const [selectedDate, setSelectedDate] = useState(null);

  const createCalendar = (year, month) => {
    const dayNames = ["일", "월", "화", "수", "목", "금", "토"];

    const daysInMonth = new Date(year, month + 1, 0).getDate();
    const firstDayOfMonth = new Date(year, month, 1).getDay();

    
    let days = [];
    for (let i = 0; i < 7; i++) {
      days.push(<div className={`${styles.day} ${styles.day_label} ${i==0 ? ` ${styles.sunday}` : i==6 ? ` ${styles.saturday}` : ''}`}>{dayNames[i]}</div>);
    } 
    for (let i = 0; i < firstDayOfMonth; i++) {
      days.push(<div className={styles.day} key={`empty-${i}`}></div>);
    }
    for (let i = 1; i <= daysInMonth; i++) {
      const className = getClassNames(i, month);
      days.push(<div className={className} key={i} onClick={() => handleDateClick(i)}>{i}</div>);
    }
    return days;
  };

  const getClassNames = (day, month) => {
    let classNames = `${styles.day}`;
    if (day === currentDate.getDate() && month === currentDate.getMonth()) {
      classNames += ` ${styles.today}`;
    }
    if (selectedDate && day === selectedDate.getDate() && month === selectedDate.getMonth()) {
      classNames += ` ${styles.selected}`;
    }
    if (new Date(currentDate.getFullYear(), month, day).getDay() === 0) {
      classNames += ` ${styles.sunday}`;
    } else if (new Date(currentDate.getFullYear(), month, day).getDay() === 6) {
      classNames += ` ${styles.saturday}`;
    }
    return classNames;
  };

  const handleDateClick = (day) => {
    setSelectedDate(new Date(currentDate.getFullYear(), currentDate.getMonth(), day));
  };

  const changeMonth = (delta) => {
    const newDate = new Date(currentDate.getFullYear(), currentDate.getMonth() + delta, 1);
    setCurrentDate(newDate);
    setSelectedDate(null); // Change month, reset selectedDate
  };

  return (
    <>
      <div className={styles.calendar}>
        <div className={styles.month}>{currentDate.getMonth()+1}</div>
        <div className={styles.days}>
          {createCalendar(currentDate.getFullYear(), currentDate.getMonth())}
        </div>
        {/* <button onClick={() => changeMonth(-1)}>Prev</button>
        <button onClick={() => changeMonth(1)}>Next</button> */}
        <div style={{position:'relative'}}>
        <img className={styles.calendar_bottom} src='/imgs/calendar_bottom.png'/>
        <img className={styles.duck_img} src='/imgs/ch1_nobol_samewalk.gif'/>
        </div>
      </div>
      {/* <div style={{marginTop:'20px'}}>Selected Date: {selectedDate && selectedDate.toLocaleDateString()}</div> */}
    </>
  );
}

export default Calendar;
