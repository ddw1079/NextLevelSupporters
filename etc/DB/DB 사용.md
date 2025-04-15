# DB 전체

## 0. DB 배포 계획

### 0-1. 취지
- 개발 시 모두의 DB 환경은 동일해야함.
- 이에 DB 스키마를 같이 쌓아올려 통일한 다음, 이를 기반으로 모두에게 DB CREATE SQL을 뿌리고 각자 로컬 환경에서 작업할 수 있게끔 하고자 함.

### 0-2. 목표
- 이번 문서에서는 각자 같은 환경에서 DB를 사용할 수 있게끔 DB 첫 세팅, CREATE TABLE SQL 이 들어갈 예정
- 이를 기반으로 JAVA 백엔드를 작업하면 됨.

## 1. ORACLE DB 새로운 유저, 새로운 접속 생성 방법

### 1-1. 새로운 유저 생성 방법
- 아래는 NLSDBA를 만드는 SQL입니다. 이걸 SYSTEM 으로 로그인해서 실행하세요:
``` SQL
ALTER SESSION SET "_ORACLE_SCRIPT"=true;
CREATE USER NLSDBA IDENTIFIED BY "nextlevelsupporters";
GRANT CONNECT, RESOURCE, DBA to NLSDBA;
```

### 1-2. 새로운 DB 접속 방법
1. SQLDEVELOPER 접속
2. 접속 밑의 초록색 플러스 버튼(새 접속...) 클릭
3. 새로 만들기/데이터베이스 접속 선택 창에서 DB 이름과 생성한 사용자 이름, 비밀번호 입력 후 테스트
4. 테스트 정상이면 접속 클릭

# 2. 