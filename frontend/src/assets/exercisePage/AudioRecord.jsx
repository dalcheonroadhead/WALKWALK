import React, { useState, useRef } from "react";

const mimeType = "audio/webm"

const AudioRecord = ({stompClient, currentMember, pageOwnerId, clientHeader}) => {

  // 1. 마이크 권한 허용 여부를 boolean으로 보여주는 변수  
  const [permission, setPermission] = useState(false);
  // 2. 새로운 MediaRecord 객체(MideaStream)로부터 만들어진 데이터를 참조하고 있는 변수다. 
  const mediaRecorder = useRef(null);
  // 3. 현재 녹음 상태를 대변하는 변수 (3개의 상태: 녹음 중, 비활성화 상태, 잠시 중지 상태)
  const [recordingStatus, setRecordingStatus] = useState("inactive");
  // 4. getUserMidea 함수로부터 받은 MediaStream 객체를 포함하고 있는 변수이다.
  const [stream, setStream] = useState(null);
  // 5. 녹음 Chunk -> 녹음본은 데이터량이 크기 때문에 Chunk라는 덩어리 단위로 끊어서 저장함. 
  const [audioChunks, setAudioChunks] = useState([]);
  // 6. 완료된 녹음 데이터를 들을 수 있는 Blob URL을 담은 변수 
  const [audio, setAudio] = useState(null);

  // 7. 사용자 권한 얻기 
  const getMicrophonePermission = async () => {
    if("MediaRecorder" in window) {
      try{
        const StreamData = await navigator.mediaDevices.getUserMedia({
          audio: true,
          video: false,
        });
        setPermission(true);
        setStream(StreamData);
      } catch (err) {
        console.log(err.message);
      }
    } else {
      alert("현재 쓰시는 중인 브라우저는 Media Recording API를 허용하지 않고 있습니다. 죄송합니다. 모바일로 녹음 기능을 이용해주세요!")
    }
  }

  // 8. 녹음 시작하는 함수 
  const startRecording = async () => {
    setRecordingStatus("recording");

    // 8-1 Stream을 사용하는 새로운 Media Recorder 객체를 만든다. 
    const media = new MediaRecorder(stream, {mimeType});

    // 8-2 만들어진 새로운 Media Recorder 객체를 우리가 위에서 만든 Media Recorder (useRef()) 참조변수가 참조할 수 있도록 한다.
    mediaRecorder.current = media;

    // 8-3 Media Recorder 객체의 시작 함수를 호출하여 녹음 과정을 시작할 수 있게 한다. 
    mediaRecorder.current.start();

    // 8-3-a 오디오 Chunk들을 받는 배열 
    let localAudioChunks = [];
    // 8-3-b 들어오는 data가 undefined거나, 없으면 바로 return하여 에러가 나는 경우를 대비한다.
    //       그 이외의 경우는 모두 배열안에 넣는다. 
    mediaRecorder.current.ondataavailable = (event) => {
      console.log(event.data);
      if(typeof event.data === "undefined") return;
      if(event.data.size === 0) return;
      localAudioChunks.push(event.data);  
    }

    setAudioChunks(localAudioChunks);
    console.log(audioChunks);
  }

  // 9. 녹음을 끝내는 함수 
  const stopRecording = () => {
    // 9-1 녹음 상태를 비활성화 상태로 돌린다.
    setRecordingStatus("inactive")

   

    // 9-2 위에서 만든 Media Recorder 객체가 녹음을 중지하도록 한다. 
    mediaRecorder.current.stop();

    // 9-3 Media Recoreder 객체가 중지 되었을 때, 실행되는 콜백함수인 onStop이 할 일을 정의한다. 
    mediaRecorder.current.onstop = () => {
      // 9-3-a audio Chunks data를 종합하여 blob File을 만든다. 
      const audioBlob = new Blob(audioChunks, {type: mimeType});

      // 9-3-b blob File을 들을 수 있는 URL 로 변환한다.
      const audioUrl = URL.createObjectURL(audioBlob);
      console.log(audioUrl);
      // 9-3-c 만들어진 URL을 세팅
      setAudio(audioUrl);
      // 9-3-b Audio Chunk 배열 비우기 
      setAudioChunks([]);

      const reader = new FileReader();

      console.log(currentMember);
      
      reader.readAsDataURL(audioBlob);
      reader.onloadend = () => {
        const base64 = reader.result;

        var messageInfo = {
          messageType: 'VOICE',
          textContent: null,
          voiceURL: base64,
          senderId: currentMember.member_id,
          senderProfileUrl: currentMember.member_profile_url,
          receiverId: pageOwnerId,
          senderNickname: currentMember.member_nickname,
          isOpened: false
        };
    
        stompClient.send("/pub/api/socket/talk", clientHeader, JSON.stringify(messageInfo));

        console.log(base64);
      }
    }
  }



  return(
    <div style={{display: "flex", flexDirection: "column"}}>
      {!permission? (<button className="btn glass" onClick={getMicrophonePermission}> 웹 녹음 권한</button>) : 
      null}
      {permission && recordingStatus === "inactive"? (
        <button onClick={startRecording} className="btn glass">🎙️녹음시작</button>
      ): null}
      {permission && recordingStatus === "recording"? (
        <button onClick={stopRecording} className="btn glass">
          🎙️녹음 끝내기
        </button>
      ): null}
    </div>
    
  )

};

export default AudioRecord;