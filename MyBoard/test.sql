CREATE TABLE myboard(
	num NUMBER(4) PRIMARY KEY,
	author VARCHAR2(15) NOT NULL,
	title VARCHAR2(100) NOT NULL,
	content VARCHAR2(2000) NOT NULL,
	writeday DATE DEFAULT SYSDATE,
	readcnt NUMBER(6) DEFAULT 0,
	repRoot NUMBER(4),
	repStep NUMBER(4),
	repIndent NUMBER(2)
)

SELECT * FROM myboard ORDER BY repRoot desc, repStep asc

SELECT ROWNUM rnum, num, title, author, writeday, readcnt, repIndent 
FROM(SELECT * FROM myboard ORDER BY repRoot desc, repStep asc)

SELECT * FROM(SELECT ROWNUM rnum, num, title, author, writeday, readcnt, repIndent FROM(SELECT * FROM myboard ORDER BY repRoot desc, repStep asc)) WHERE rnum >= ? AND rnum <= ?

DELETE FROM MYBOARD WHERE num = 2

COMMIT

INSERT INTO myboard (num, author, title, content, repRoot, repStep, repIndent) 
VALUES (NVL2(num,1,num+1), '김', 'hello', 'hello world', 1, 0, 0)

SELECT NVL2(MAX(NUM), 2, 1) FROM myboard
/* num 값을 자동으로 입력하게 하기 위해서 NVL2를 사용한다 */
/* NVL2(MAX(NUM), 2, 1) : myboard에서 가장 큰 num의 값을 가지고 와서 */
/* num이 null이면, 1을, 그렇지 않으면 MAX(NUM)+1 */
/* NVL2(조건, 값1, 값2) : 조건이 null이 아니냐? 그렇다면 값1, null이면 값2 */


SELECT COUNT(num) FROM myboard



SELECT * FROM (SELECT ROWNUM rnum, num, title, author, writeday, readcnt, repIndent FROM(SELECT * FROM myboard ORDER BY repRoot desc, repStep asc)) WHERE rnum >= ? AND rnum <= ?