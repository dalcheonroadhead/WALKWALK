# 🐣WALKWALK - 블록체인을 활용한 운동 격려 서비스🐣
<img width="350" alt="image" src="https://github.com/DoingHyoDo10/WALKWALK/assets/102154788/2bcc01d3-9cb7-4f9c-b649-85ccdb85ffbf">
<br>
WALKWALK 링크: https://j10d210.p.ssafy.io/
<br><br><br>

## 서비스 개요
운동을 안 해 걱정인 주변인이 있다면 용돈을 수단으로 삼아 운동을 시킬 수 있습니다.

운동과 용돈 지급 내역은 블록체인으로 투명하게 운영됩니다.
<br><br><br>

## 주요 기능

- 미션
    - 운동을 시키는 사람은 할리, 운동을 할 사람은 갈리로 나뉘어 앱을 사용하게 됩니다.
    - 할리와 갈리 매칭을 해두면, 할리는 갈리에게 미션을 걸 수 있습니다.
    - 하루 최소 운동(걸음) 시간을 설정하고, 이를 달성하면 용돈을 지급합니다.

- 응원
    - 운동 시작 버튼을 누르면 할리에게 알림이 갑니다.
    - 그때, 할리는 갈리에게 응원 메시지를 보낼 수 있습니다.

- 리포트
    - 운동하기 버튼을 눌러 진행한 운동은 블록체인에 저장이 됩니다.
    - 이 데이터를 토대로 월별 리포트를 제공합니다.

- 랭킹
    - 친구들 사이에서 내 순위를 확인할 수 있습니다.
    - 순위는 걸음 수 기준 일, 주, 월별, 스트릭(운동을 꾸준히 한 기간) 기준으로 제공됩니다.
    - 가상 맵으로도 이를 확인할 수 있습니다.

- 자가 미션
    - 자기 자신에게도 최소 운동 기준을 설정할 수 있습니다.
    - 초기에는 WHO의 연령대별 기준으로 초기화되어 있습니다.
    - 자가 미션을 기준으로 스트릭이 산정됩니다.
<br><br><br>

## 프로젝트 정보
2024/02/19 ~ 2024/04/03 (45일간 진행)

SSAFY 10기 2학기 특화 프로젝트 - WALKWALK 
<br><br><br>

## 주요 기술 및 개발 환경

### 아키텍처 구성도
![Web App Reference Architecture (9)](https://github.com/DoingHyoDo10/WALKWALK/assets/93258358/f018b5b6-4da4-43ad-af2f-ba2390ef4bb3)

### Frontend

- Visual Studio Code
- HTML5, CSS3, JavaScript(ES6)
- React
- Stompjs
- zustand
- Nodejs
- Vite
- Tailwind CSS
- 사용한 외부 API
    - Web Speech API
    - Google Fitness API
    - Google geoLocation API
    - Google Map React API

### Backend

- IntelliJ
- JVM Open JDK
- Spring Boot
    - Spring Data JPA
    - Spring Security
    - SSE Emitter
- Maria DB
- JWT
- OAuth2.0
- STOMP-WEB-SOCKET
- REDIS Lettuce
- REDIS
- Gradle
- AWS S3 Bucket Cloud
- AWS Transcribe
- AWS Lambda
- Spring Batch
- Google TTS Cloud AI
- 사용한 외부 API
    - Google fitness API

### BLOCKCHAIN

- Solidity
- Remix IDE
- MetaMask
- Ethereum
- Sepolia TestNet
- ethersjs
- Web3j
- Klaytn API Service

### CI/CD

- AWS EC2
- Docker
- Jenkins
<br><br><br>

## 협업 툴

- GitLab - 코드 버전 관리 및 MR과 리뷰
- Jira - 매주 목표량 설정, 프로젝트 진행도 확인
- Notion - 회의록 작성, 기술 레퍼런스 공유, 프로젝트 산출물 관리
- Figma - 목업, 와이어프레임, 디자인 공유
- MatterMost - 자료 및 api 현황과 요청사항 공유
- Code With Me - 실시간 협업 코딩
<br><br><br>

## 포팅 메뉴얼


[포팅 메뉴얼 링크!](https://lab.ssafy.com/s10-blockchain-contract-sub2/S10P22D210/-/blob/d182195b6dea4772f0d6107ca9456296be04a571/exec/%ED%8F%AC%ED%8C%85%20%EB%A9%94%EB%89%B4%EC%96%BC%20WALK_WALK.pdf)
<br><br><br>

## UCC 및 시연영상

- UCC - https://youtu.be/01bYtPFIBRs
- 시연영상 - https://youtu.be/X-z9YPcKuxY
[![WALK_WALK_VIDEO](https://github.com/DoingHyoDo10/WALKWALK/assets/102154788/523f2025-7d71-4bec-95a8-28a97dee969e)](https://youtu.be/X-z9YPcKuxY)

<br><br><br>

# 효도하기 10조(D210)의 팀원들을 소개합니다!

| 이름 | 역할 | 개발 내용 |
| --- | --- | --- |
| 전수민 | 팀장<br/>Backend<br/>Frontend | - DB 설계<br/>- 회원, Spring Security & JWT &  OAuth2.0<br/>- 채팅 및 tts, 응원 메시지 구현<br/>- 화면 설계 |
| 한채연 | Backend<br/>Blockchain | - DB 설계<br/>- 지갑 관리<br/>- 카카오페이 API를 통한 결제 구현<br/>- 블록체인을 활용한 영수증 발급 |
| 최지수 | Backend | - DB 설계<br/>- 운동 데이터, 기준 및 운동 도메인 api<br/>- 스프링 배치 및 스케줄링을 통한 대용량 데이터 처리 서버 구현<br/>- 랭킹 도메인 페이저블 구현 |
| 심규영 | Frontend<br/>Blockchain | - 화면 설계<br/>- 블록체인<br/>- 회원관리 & OAuth2.0<br/>- 결제 |
| 김세현 | Frontend<br/>Backend | - DB 설계<br/>- 서버 EC2 배포<br/>- CI/CD with Jenkins<br/>- 친구, 할리갈리 관리<br/>- SSE 알림 서비스<br/>- 메인화면, 할리갈리, 친구 페이지 구현 |
| 김규리 | Frontend | - Figma를 이용한 화면 디자인 설계<br/>- 메인, 랭킹, 친구, 할리갈리 미션 설정, 상점, 응원메시지, 보물찾기 화면 구현<br/>- axios로 API 연결<br/>- Zustand로 상태관리 |

<br><br><br>

## 주요 서비스 화면

### 1. 회원 가입 및 로그인
<img width="350" alt="image" src="https://github.com/DoingHyoDo10/WALKWALK/assets/102154788/71e3f355-cf4b-4a1e-a42c-6d4a72250a15">
<br><br><br>

### 2. 메인 화면
<img width="350" alt="image" src="https://github.com/DoingHyoDo10/WALKWALK/assets/102154788/6d909ae8-cf42-4dc1-b4b7-e78c14225eee">
<br><br><br>

### 3. 랭킹
<img width="350" alt="image" src="https://github.com/DoingHyoDo10/WALKWALK/assets/102154788/7ce90968-b9f4-4ed5-abbb-d591b9c11a42">
<br><br><br>

### 4. 운동 페이지
<img width="350" alt="image" src="https://github.com/DoingHyoDo10/WALKWALK/assets/102154788/af4f7a36-13ee-4ce3-986f-2c2c958fbde9">
<br><br><br>

### 5. 응원 메시지
<img width="350" alt="image" src="https://github.com/DoingHyoDo10/WALKWALK/assets/102154788/9767ce86-ac0e-471c-99b4-fcc7161c5a6c">
<br><br><br>

### 6. 응원 메시지 보관함
<img width="350" alt="image" src="https://github.com/DoingHyoDo10/WALKWALK/assets/102154788/4499a43f-c90d-4c7c-bb4a-4f9de0fa08d4">
<br><br><br>

### 7. 응원 메시지 보내기
<img width="350" alt="image" src="https://github.com/DoingHyoDo10/WALKWALK/assets/102154788/c1f1b427-1464-4d66-8d88-c0fcb02cc2d6">
<br><br><br>

### 8. 친구 목록
<img width="350" alt="image" src="https://github.com/DoingHyoDo10/WALKWALK/assets/102154788/42b25c66-b167-470e-96cf-7323a78c4092">
<br><br><br>


### 9. 친구 검색
<img width="350" alt="image" src="https://github.com/DoingHyoDo10/WALKWALK/assets/102154788/e71e094f-e12b-44d9-93c9-cc4c9641c827">
<br><br><br>


### 10. 친구 신청
<img width="350" alt="image" src="https://github.com/DoingHyoDo10/WALKWALK/assets/102154788/01bc8fb7-d7c6-4d61-8616-2687f5b499cd">
<br><br><br>


### 11. 마이페이지
<img width="350" alt="image" src="https://github.com/DoingHyoDo10/WALKWALK/assets/102154788/f8d0f012-0371-44ac-bbb6-608937dbe617">
<br><br><br>


## 프로젝트 산출물
### 와이어프레임
<img width="900" alt="image" src="https://github.com/DoingHyoDo10/WALKWALK/assets/78338944/c0881703-b992-44d8-b195-b83750cddfe6">
<br><br>

### UI 디자인
<img width="900" alt="image" src="https://github.com/DoingHyoDo10/WALKWALK/assets/78338944/370f483e-93ec-4c93-862e-8e7111204906">
<br><br>

### ERD
<img width="900" alt="image" src="https://github.com/DoingHyoDo10/WALKWALK/assets/78338944/9e624fc0-b08d-4df8-a129-f080fd2327af">
<br><br>

### 서비스 아키텍처
