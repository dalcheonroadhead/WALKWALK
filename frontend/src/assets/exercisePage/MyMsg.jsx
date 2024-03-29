
import Msg from "./Msg.module.css"

export default function MyMsg({ message, pageOwnerId }) {

  const formattedTime =
    typeof message.createdAt === "string"
      ? new Date(message.createdAt).toLocaleTimeString([], {
          hour: "2-digit",
          minute: "2-digit",
        })
      : new Date(message.createdAt).toLocaleTimeString([], {
          hour: "2-digit",
          minute: "2-digit",
        });
  return (
    <div style={{display:"flex", flexDirection: "row-reverse"}}>
      <div className={Number(pageOwnerId) == message.senderId? "user-message" : "other-message"}>
        {/* isTyping = 애니메이션을 할까말까 boolean값, curretTypingId는 제일 최근에 쳤던 메세지 ID */}
        {message.voiceURL !== ''? 
          <audio controls autoPlay>
            <source src={message.voiceURL} type="audio/mpeg" />
          </audio>: message.textContent}
      </div>

      <div className={Msg.time}>{formattedTime}</div>
    </div>
  );
}
