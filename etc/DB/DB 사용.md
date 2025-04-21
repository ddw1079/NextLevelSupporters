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

# 2. Table CREATE SQL

## 2-1. USER TABLE
``` SQL
CREATE TABLE user_table (
    ID NUMBER PRIMARY KEY,
    LOGIN_ID VARCHAR2(255) NOT NULL UNIQUE,
    LOGIN_PW VARCHAR2(255) NOT NULL,
    -- Oracle DB 에는 Boolea 항목이 없어 이렇게 구현해야함.
    IS_ACTIVE CHAR(1) CHECK (IS_ACTIVE IN ('Y', 'N')),
    NAME VARCHAR2(255) NOT NULL,
    PHONE VARCHAR2(255),
    ACCOUNT VARCHAR2(255),
    USER_TYPE NUMBER CHECK (USER_TYPE IN (0, 1, 2))
    -- Type 설명: 0 - 후원자, 1 - 수혜자, 2 - Admin
);
```

## 2-2. RECEIVER TABLE
``` SQL
CREATE TABLE RECEIVER (
    RECEIVER_ID NUMBER PRIMARY KEY,
    REASON VARCHAR2(255),
    FOREIGN KEY (RECEIVER_ID) REFERENCES user_table(ID)
);
```

## 2-3. HISTORY TABLE
``` SQL
CREATE TABLE HISTORY (
    CREATE_DATE DATE DEFAULT SYSDATE,
    GIVER_ID NUMBER NOT NULL,
    RECEIVER_ID NUMBER NOT NULL,
    AMOUNT NUMBER NOT NULL,
    MESSAGE VARCHAR2(255),
    IS_RECEIVED CHAR(1) CHECK (IS_RECEIVED IN ('Y', 'N')),
    FOREIGN KEY (GIVER_ID) REFERENCES USER_TABLE(ID),
    FOREIGN KEY (RECEIVER_ID) REFERENCES USER_TABLE(ID)
);
```

# 3. Insert Test Data

## 3-1. Insert USER Table
``` SQL
-- Type 설명: 0 - 후원자, 1 - 수혜자, 2 - Admin
INSERT INTO user_table (ID, LOGIN_ID, LOGIN_PW, IS_ACTIVE, NAME, PHONE, ACCOUNT, USER_TYPE)
VALUES (1, 'sponsor01', 'password123', 'Y', 'John Doe', '010-1234-5678', '123-456-789', 0);

INSERT INTO user_table (ID, LOGIN_ID, LOGIN_PW, IS_ACTIVE, NAME, PHONE, ACCOUNT, USER_TYPE)
VALUES (2, 'receiver01', 'securepass', 'Y', 'Jane Smith', '010-9876-5432', '987-654-321', 1);

INSERT INTO user_table (ID, LOGIN_ID, LOGIN_PW, IS_ACTIVE, NAME, PHONE, ACCOUNT, USER_TYPE)
VALUES (3, 'admin01', 'adminsecure', 'Y', 'Admin User', '010-2222-3333', '000-111-222', 2);
```

## 3-2. Insert RECEIVER Table
``` SQL
INSERT INTO receiver (RECEIVER_ID, REASON)
VALUES (2, 'Educational expenses');
```

## 3-3. Insert HISTORY Table
``` SQL
INSERT INTO history (CREATE_DATE, GIVER_ID, RECEIVER_ID, AMOUNT, MESSAGE, IS_RECEIVED)
VALUES (SYSDATE, 1, 2, 100000, 'Donation for education', 'N');
```
## 4. ID 자동생성 setting
``` SQL
CREATE SEQUENCE user_seq
START WITH 100
INCREMENT BY 1
NOCACHE
NOCYCLE;
```
