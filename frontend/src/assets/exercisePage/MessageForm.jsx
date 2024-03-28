import { useState } from "react";
const MessageForm = ({ onSendMessage }) => {
    const [message, setMessage] = useState("");
  
  
    // 입력 끝나고 Enter나 전송 버튼 눌렀을 때 해야될 상황
    const HandleSubmit = (event) => {
      event.preventDefault();
      
      // 보내는 메세지에 금방 쓴 메세지를 메세지 배열 속에 넣는 함수에 인수로 넣기 
      onSendMessage(message);
      // 입력 칸은 비우기 
      setMessage("");
  
  
    };
    
  
    return (
      <form onSubmit={HandleSubmit} className="message-form">
        <input
          type="text"
          value={message}
          onChange={(event) => setMessage(event.target.value)}
          className="message-input"
        />
        <button type="submit" className="send-button">
          Send
        </button>
      </form>
    );
  
  }

  export default MessageForm;