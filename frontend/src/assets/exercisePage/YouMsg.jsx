export default function YouMsg({ message}) {
    // 상대방 채팅의 경우 상대방의 이름과 프사가 보여야 한다.
    const formattedTime = new Date(message.createdAt).toLocaleTimeString([], {
      hour: "2-digit",
      minute: "2-digit",
    });
    // console.log("youMsg");
    return (
      <>
        <div className="flex" style={{flexDirection: "row"}}>
          {/* 상대방 프사 */}
          <img
            src={message.senderProfileUrl}
            className="h-[45px] w-[45px] mt-1 rounded-full mx-2 shadow-md"
          />
          <div>
            {/* 상대방 이름 */}
            <p className="font-bold">
              {message.senderNickname}
            </p>
            <div className="flex">
              <div className={"other-message"}>
              {message.voiceURL !== ''? 
                  <audio controls autoPlay>
                    <source src={message.voiceURL} type="audio/mpeg" />
                  </audio>: message.textContent}
              </div>
              <div className="text-xs mb-2 self-end">{formattedTime}</div>
            </div>
          </div>
        </div>
      </>
      
    );
  }