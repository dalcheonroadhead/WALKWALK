
import Msg from "./Msg.module.css"

export default function MyMsg({ data }) {
  // console.log("myMsg");
  console.log(data);
  const formattedTime =
    typeof data.createdAt === "string"
      ? new Date(data.createdAt).toLocaleTimeString([], {
          hour: "2-digit",
          minute: "2-digit",
        })
      : new Date(data.createdAt).toLocaleTimeString([], {
          hour: "2-digit",
          minute: "2-digit",
        });
  return (
    <>
      <div className={Msg.time}>{formattedTime}</div>
    </>
  );
}
