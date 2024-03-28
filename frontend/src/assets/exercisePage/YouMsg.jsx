export default function YouMsg({ data }) {
    // 상대방 채팅의 경우 상대방의 이름과 프사가 보여야 한다.
    const formattedTime = new Date(data.createdAt).toLocaleTimeString([], {
      hour: "2-digit",
      minute: "2-digit",
    });
    // console.log("youMsg");
    // console.log(data);
    return (
      <>
        <div className="flex">
          {/* 상대방 프사 */}
          <img
            src={data.senderprofileUrl}
            className="h-[45px] w-[45px] mt-1 rounded-full mx-2 shadow-md"
          />
          <div>
            {/* 상대방 이름 */}
            <p className="font-bold">
              {data.senderNickname}
            </p>
            <div className="flex">
              <div className="max-w-[80%] m-2 py-3 px-4 rounded-sm self-end bg-gray-50 font-bold text-sm text-wrap shadow-md dark:text-black ">
                {data.textContent}
              </div>
              <div className="text-xs mb-2 self-end">{formattedTime}</div>
            </div>
          </div>
        </div>
      </>
    );
  }