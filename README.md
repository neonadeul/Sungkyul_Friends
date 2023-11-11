# Sungkyul_Friends
<img src="https://github.com/neonadeul/Sungkyul_Friends/assets/119486666/1acebf65-fb39-49cd-8fc8-06840110875a" width="100" height="100"> <br>
성결대학교 내 짝선배 짝후배 매칭 어플 <br>


## ⌨️프로젝트 소개
다들 대학교에 처음 입학하면 대학생활에 대해 아는 것도 하나 없고 막막함을 느껴봤을 것이다.<br>
이때, 아는 선배가 있다면 학교생활이 좀더 나을 거라는 생각이 들었다.<br>
조언을 얻을 선배를 원하거나 친하게 지낼 후배를 원하는 학생들을 위해 <br>
선후배를 서로 매칭해주는 학교 내 매칭어플인 ‘성결친구’를 만들게 되었다.<br><br>

좀더 나은 매칭을 위해 단순한 랜덤 매칭이 아닌 '관심사'를 도입해서 <br>
관심사가 많이 겹치는 사용자가 우선적으로 매칭되도록 구현하였다.<br>
매칭 뿐 아니라 매칭된 상대방과의 채팅도 가능하고, 매칭을 취소했을 때 상대방의 후기도 남길 수 있게 해 <br>
다음번에 매칭된 상대방이 후기를 보고 이 사람이 어떤 사람인지를 알 수 있게 하였다.<br>
<br>

## ⚙️개발 환경
#### 개발도구
<img src="https://img.shields.io/badge/androidstudio-3DDC84?style=for-the-badge&logo=androidstudio&logoColor=white"> <img src="https://img.shields.io/badge/visualstudiocode-007ACC?style=for-the-badge&logo=visualstudiocode&logoColor=white">

#### 데이터베이스
<img src="https://img.shields.io/badge/firebase-FFCA28?style=for-the-badge&logo=firebase&logoColor=white"> <img src="https://img.shields.io/badge/amazonrds-527FFF?style=for-the-badge&logo=amazonrds&logoColor=white"> <img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white">

#### 언어
<img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white"> <img src="https://img.shields.io/badge/php-777BB4?style=for-the-badge&logo=php&logoColor=white">

<br><br>

## 👨‍👩‍👧‍👦팀원 구성 및 역할
- 박지연(팀장): 백엔드, 채팅기능 구현
- 김민지: 프론트엔드, UI 구현 및 API 연동
- 신지민: 백엔드, 로그인 및 회원가입 구현, API 개발
- 정예린: 프론트엔드, UI 구현 및 API 연동
- 최윤정: 백엔드, 매칭 API 구현
<br>

## 📌주요기능
#### ⭐로그인 및 회원가입
<img src="https://github.com/neonadeul/Sungkyul_Friends/assets/119486666/9c67efd8-3142-4afb-b5e2-a31f97d9b9c4" width="120" height="200"> <img src="https://github.com/neonadeul/Sungkyul_Friends/assets/119486666/8a578231-d21c-41a5-8c32-d185d27c756b" width="120" height="200"> <img src="https://github.com/neonadeul/Sungkyul_Friends/assets/119486666/f4a02dab-9f10-4e47-ac5d-f1bb00214fae" width="120" height="200">

- ‘성결친구’ 이용 시 로그인이 필수
- 회원가입시 중복 ID 검사
- 회원가입시 매칭에 사용되는 관심사 최대 5개 선택<br><br>


#### ⭐짝 선배 - 짝 후배 매칭 과정
<img src="https://github.com/neonadeul/Sungkyul_Friends/assets/119486666/ea0ca8bb-5201-4010-a39f-8a0d510de1bc" width="120" height="200"> <img src="https://github.com/neonadeul/Sungkyul_Friends/assets/119486666/991c99cc-ae9f-4ddd-8140-23709eb50c80" width="120" height="200"> <img src="https://github.com/neonadeul/Sungkyul_Friends/assets/119486666/3c01d1a4-2efb-440c-b78e-baf7450433e7" width="120" height="200"> <img src="https://github.com/neonadeul/Sungkyul_Friends/assets/119486666/ab566c26-c615-4e23-84fa-2638e20a83f5" width="120" height="200">

- `메인 화면`
  - 중앙에 매칭 하러가기 버튼
  - 하단바에 메인 화면, 채팅방, 매칭 목록, 마이페이지로 가는 버튼
- `매칭 조건 고르기`
  - 학과는 회원가입시 선택한 자신의 학과로 고정, 원하는 상대방의 학년과 성별 선택 가능
  - ‘매칭’ 버튼을 누르면 매칭 요청이 신청됨
- `매칭 결과 확인 다이얼로그`
  - 확인을 누르면 매칭 결과 확인 페이지로 넘어감
- `매칭 결과 확인 페이지`
  - ‘확인하기’ 버튼을 눌러 매칭 결과 확인 가능
  - ‘매칭취소’ 버튼을 눌러 보냈던 매칭 요청 취소 가능<br><br>


#### ⭐짝 선배 - 짝 후배 매칭 과정
<img src="https://github.com/neonadeul/Sungkyul_Friends/assets/119486666/50465eaf-7594-45f8-8b8a-50e178eff63e" width="120" height="200"> <img src="https://github.com/neonadeul/Sungkyul_Friends/assets/119486666/e63e95cc-62ee-45b6-999f-692b3da8df99" width="120" height="200">

- `매칭 방식`
  - 조건 선택 후 ‘매칭’버튼을 누르면 매칭 요청 전송
  - 매칭 결과 확인 페이지에서 ‘확인하기’ 버튼을 누르면 그때 매칭 요청을 보낸 사용자 중 조건에 맞는 사람들끼리 매칭
- `매칭 실패`
  - 매칭 요청을 보낸 사용자들 중 서로 조건에 맞는 사용자가 없어서 매칭 실패
- `매칭 성공`
  - 매칭 요청을 보낸 사용자들 중 서로 조건에 맞는 사용자가 있어 매칭 성공
  - 조건에 맞는 사용자가 여러 명일 경우 관심사가 더 많이 일치하는 순으로 매칭<br><br>

#### ⭐매칭 완료시 가능한 것
<img src="https://github.com/neonadeul/Sungkyul_Friends/assets/119486666/279e01eb-3247-4935-82d3-156fcdc586dd" width="120" height="200"> <img src="https://github.com/neonadeul/Sungkyul_Friends/assets/119486666/ba84dd23-74cf-40c6-9399-8e1be6249ce6" width="120" height="200"> <img src="https://github.com/neonadeul/Sungkyul_Friends/assets/119486666/53e56e23-c2d3-4aae-826f-ce56fdb12584" width="120" height="200">

- `상대방과의 화면`
  - 상대방 프로필을 누르면 상대방 프로필 화면으로 전환
  - 말풍선 아이콘을 누르면 상대방과의 채팅방으로 이동
  - 매칭 취소 버튼을 눌러 매칭 취소 가능
- `상대방 프로필`
  - 상대방의 이름, 주전공, 학번, 성별 확인 가능
  - ‘받은 후기 멘트 보러가기’ 버튼을 눌러 상대방이 받은 후기 확인 가능
  - 상대방이 받은 후기 학점의 평균값이 계산되어 프로필 옆에 보임 (여기선 A+)
- `채팅`
  - 매칭된 상대방과 실시간 채팅 가능<br><br>


#### ⭐매칭 취소
<img src="https://github.com/neonadeul/Sungkyul_Friends/assets/119486666/0b25c962-8bde-4873-ac9f-6f35c5c16d65" width="120" height="200"> <img src="https://github.com/neonadeul/Sungkyul_Friends/assets/119486666/19ba325d-676c-4a4d-9f69-771ab9c46e00" width="120" height="200"> <img src="https://github.com/neonadeul/Sungkyul_Friends/assets/119486666/224d0cdd-2916-4ed7-84c3-5c3b5357d3b0" width="120" height="200">

- `매칭 취소 버튼`
  - 매칭된 상대방과의 화면에서 ‘매칭 취소’ 버튼을 눌러 매칭 취소 가능
- `취소 확인 다이얼로그`
  - 정말 매칭취소를 원하는지 한번 더 물어보는 다이얼로그
- `취소 완료 다이얼로그`
  - 취소 완료를 알려주는 다이얼로그
  - 여기서 상대방의 후기를 남기러 가거나 새로운 매칭을 하러 갈 수 있음<br><br>


#### ⭐리뷰
<img src="https://github.com/neonadeul/Sungkyul_Friends/assets/119486666/68aee73e-2e66-4d6d-a3fc-db7459257feb" width="120" height="200"> <img src="https://github.com/neonadeul/Sungkyul_Friends/assets/119486666/f2bd10cd-0178-4fb7-9eba-d681ea802e5f" width="120" height="200">

- `후기 작성`
  - 후기는 별점 대신 학점의 형식으로 선택 가능
  - 하고 싶은 멘트를 남길 수 있음
- `후기 내역`
  - 다른 사람이 남긴 후기 내역을 볼 수 있음
  - 상대방 프로필에서 상대방의 후기 확인 가능, 마이페이지에서 내가 받은 후기 확인 가능
  - 후기 학점의 평균값이 계산되어 프로필 옆에 보임

