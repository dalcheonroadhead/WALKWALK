import YouMsg from "./YouMsg";
import MyMsg from "./MyMsg";
const Message = ({
    isTyping,
    id,
    currentTypingId,
    message,
    pageOwnerId
  }) => {
    

    return (
      //메세지 타입에 따라 클래스 이름이 달라지도록! 
      <div>
        <div className={Number(pageOwnerId) == message.senderId? "user-message" : "other-message"}>
        {/* isTyping = 애니메이션을 할까말까 boolean값, curretTypingId는 제일 최근에 쳤던 메세지 ID */}
        {message.voiceURL !== ''? <audio controls autoPlay>
          <source src={message.voiceURL} type="audio/mpeg" />
        </audio>: message.textContent}
      </div>
        {Number(pageOwnerId) == message.senderId ?  (<MyMsg data={message} />) : (<YouMsg data={message}/>)}
      </div>
    );
  };

  export default Message;