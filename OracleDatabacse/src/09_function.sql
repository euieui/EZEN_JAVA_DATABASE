-- 라이브러리 함수 요약
-- * 오라클 명령어 : 내장 함수
--[1] 샘플 테이블인 dual 테이블
select * from tab;
select * from dual;
--dual : 테이블이 대상이 아닌 연산을 하려고 할때 from 다음에 형식적으로 붙이는 없는 테이블의 이름
-- /// 임시 테이블이라고 생각하면 되는건가

-- [2] 임시 데이터 출력
select 1234*1234 from dual;

-- *** 문자열 처리 관련 함수 ***
-- [3] lower(): 모든 문자를 소문자로 변환
select lower('Hong Kil Dong') as "소문자" from dual;
-- [4] upper(): 모든 문자를 대문자로 변환
select upper('Hong Kil Dong') as "대문자" from dual;
-- [5] initcap(): 첫자만 대문자로 변환
select initcap('hong kil dong') as "첫글자만 대문자" from dual;

-- [6] concat() : 문자열 연결
select concat('이젠 IT ', '아카데미') from dual;
-- [7] length() : 문자열의 길이
select length('이젠 아이티 아카데미'), length('THE ezen IT')from dual;
-- [8] substr() : 문자열 추출(데이터, 인덱스(1), 카운트)
select substr('홍길동 만세',2,4) from dual;
-- substr 의 경우 자바의 substring 처럼 시작번째부터 끝번재+1이 아니라
-- 시작 번째 부터 글자수를 나타 냅니다. 위의 예경우 2번재 글자부터 네글자 표시

-- [9] instr() : 문자열 시작 위치
select instr('홍길동 만세 동그라미','동') from dual;

-- [10] lpad(), rpad() : 자리 채우기
select lpad('Oracle', 20, '#') from dual; -- ##############Oracle
select rpad('oracle', 20, '*') from dual; -- Oracle****************

-- [11] trim() : 컬럼이나 대상 문자열에서 특정 문자가 첫번재 글자이거나
--						마지막 글자이면 잘라내고 남은 물자열만 반환.
select trim('a' from 'aaaOracleaaaaaaa') as result from dual; -- Oracle
select trim(' ' from '   Oracle   ' ) as result from dual; -- Oracle

-- *** 수식 처리 관련 함수 ***
-- [12] round(): 반올림(음수 : 소수점 이상 자리)
select round(12.3456,3) from dual; 
-- 12.3456 : 반올림하려는 대상 숫자 3 : 반올림하여 표시하고자하는 마지막 자리수

-- 3 : 소수점 네째자리에서 반올림하여 세째자리까지 남김
-- 2 : 소수점 세째자리에서 반올림하여 두째자리까지 남김
-- 0 : 소수점 첫째자리에서 반올림하여 소수점 자리수 없앰
-- -1 : 1의 자리에서 반올림하여 10의 자리까지 남김
-- -2 : 10의 자리에서 반올림하여 100의 자리까지 남김

select round(1728.9382, 3) from dual -- 1728.938
select round(1728.9382, 2) from dual -- 1728.94
select round(1728.9382, 1) from dual -- 1728.9
select round(1728.9382, 0) from dual -- 1729
select round(1728.9382, -1) from dual -- 1730
select round(1728.9382, -2) from dual -- 1700
select round(1728.9382, -3) from dual -- 2000

-- [13] abs() : 절대값
select abs(-10) from dual;
-- [14] floor() : 소수점 아래 절사 - 반올림 없음
select floor(12.94567) from dual;
-- [15] trunc() : 특정 자리 자르기 - 반올림 없음 , 3은 남기고 싶은 소수점 아래 자리수
select trunc(12.34567, 3) from dual; --12.345
-- [16] mod() : 나머지 -- 8을 5로 나눈 나머지
select mod(8,5) from dual; --3

-- *** 날짜 처리 관련 함수 ***
--[17] sysdate : 날짜
select sysdate from dual; -- 오늘 날짜와 현재 시간

--[18] months_between(): 개월 수 구하기
select floor(months_between('2021-12-31','2020-07-10')) from dual; --17.677419

--[19] add_months(): 개월 수 더하기
select add_months(sysdate, 200) from dual; -- 2038-06-05 17:13:38.0

--[20] next_day() : 다가올 요일에 해당하는 날짜 - 오늘 날짜(sysdate)에서 가장 가까운 일요일
select next_day(sysdate,'일요일') from dual;

--[21] last_day() : 해당 달의 마지막 일 수
select last_day(sysdate) from dual;
select last_day('2020-12-15') from dual;

--[22] to_char(): 문자열로 반환
select to_char(sysdate,'yyyy-mm-dd') from dual;
--[23] to_date(): 날짜형(date)으로 변환
select to_date('2019/12/31','yyyy/mm/dd') from dual;


-- 그 외 활용가능한 함수들...
--[24] nvl() : NULL인 데이터를 다른 ㄷ이터로 변경
select comm/100 as comm_pct from emp;
-- comm 필드 값에 따라서 일부 레코드는 계산결과가 null 이 나올 수 있음
select nvl(comm,1)/100 as comm_pct from emp;
-- 해당 필드 값이 null 이면 1로 바꿔서 계산에 참여

-- POWER 함수
select power(3,2), power(3,3), power(3,3.0001) from dual;
-- 첫번째 요소값을 두번째 요소 만큼 거듭 제곱

-- 제곱근 sqrt
select sqrt(2) , sqrt(5) from dual;


--[25] decode() : switch 문과 같은 기능
select employee_id, emp_name, decode(department_id, 

	10, 'ACCOUNT',
	20, 'RESEARCH',
	30, 'SALES',
	40, 'OPERATIONS',
	50, 'SH_CLECK',
	60, 'IT_PROG',
	70, 'PR_REP',
	80, 'SA_REP',
	90, 'AD_PRES',
	100, 'FI_ACCOUNT',
	110, 'AC_ACCOUNT'

	) as "부서명" from employees;


--[26] case(): if ~ else if ~ 와 비슷한 구조
select employee_id , emp_name , department_id,
		case when department_id = 10 then 'ACCOUNT'
		       when department_id = 20 then 'RESEARCH'
		       when department_id = 30 then 'SALES'
		       when department_id = 40 then 'OPERATIONS'
		       when department_id = 50 then 'SH_CLECK'
		       when department_id = 60 then 'IT_PROG'
		       when department_id = 70 then 'PR_REP'
		       when department_id = 80 then 'SA_REP'
		       when department_id = 90 then 'AD_PRES'
		       when department_id = 100 then 'FI_ACCOUNT'
		       when department_id = 110 then 'AC_ACCOUNT'
	   end as "부서명"
from employees;

-- mod와 remainder
-- 둘다 첫번째 요소를 두번째 요소로 나눈 나머지를 계산하지만 내부적
-- 계산방법이 조금 다름
SELECT MOD(19,4), MOD(19.123, 4.2) FROM dual;
select remainder(19,4), remainder(19.123, 4.2) from dual;
-- mod : 19 - 4 * floor(19/4)
-- remainder : 19 - 4 * round(19/4)

-- 10.5를 4.2로 나눈 나머지 : 10.5 - (4.2 * 2) = 2.1
		       

-- 문자함수 replace
select replace('나는 너를 모르는데 너는 나를 알겠는가?','나','너') from dual;
-- replace(문자열1, 문자열2, 문자열3)
-- 문자열1 내에 있는 글자중에 문자열2를 찾아서 문자열 3으로 대체

select replace(' ABC DEF ', ' ', '') from dual;


-- 변환함수
select to_char(123456789,'999,999,999') from dual;
select to_char(sysdate,'yyyy-mm-dd') from dual;
select to_number('123456') from dual;
select to_date('20140101','yyyy-mm-dd') from dual;
select to_date('20140101 13:44:50','yyyy-mm-dd hh24:mi:ss') from dual;

--'am' or 'pm' 오전 오후 표시 -> to_char(sysdate,'am')
select to_char(sysdate,'am') from dual;

--yyyy -> 년도표시
select to_char(sysdate, 'yy"년"') from dual; -- ->21
select to_char(sysdate, 'y"년"') from dual; -- ->1

--month , mon -> 월표시
select to_char(sysdate, 'month') from dual; -- -> 10월
select to_char(sysdate,'mon') from dual; -- ->10월

-- D 요일 1~7 표시
select to_char(sysdate, 'D') from dual; --> 3(화요일에 해당하는 3) DD의 날짜와 다름
select to_char(sysdate, 'day') from dual; --> 화요일
		

-- DD 일자를 01~31로 표시
select to_char(sysdate, 'dd') from dual; --> 05

-- DDD 일자를 001~365 형태로 표시
select to_char(sysdate,'ddd') from dual; -->278

-- DL 오늘 날짜를 요일까지 표시
select to_char(sysdate, 'dl') from dual; --> 2019년 9월 25일 수요일

-- WW : 주를 01~53 주 형태로 표시


-- HH, Hh12 : 시간을 01~12시로
-- HH24 : 시간을 01~23시로 표시
-- MI : 분을 01~59 형태로 표시
select to_char(sysdate,'MI"분"') from dual;
-- ss : 초를 01~59 형태로 표시
select to_char(sysdate,'HH24"시 "MI"분" SS"초"') from dual;

-- 그 외 to_char 의 옵션
-- , : 세자리 마다 표시할 콤마로 사용
-- . : 소수점 자리수로 사용
-- 9 : 한자리 숫자가 나오는 자리로 사용
-- PR : 음수일때 <>로 표시
-- RN : 로마 숫자 표시
-- S : 양수 음수 표시


-- NULL 관련 함수

select nvl(manager_id, employee_id) from employees;
-- manager_id 이 널일때 employee_id 으로 출력 값을 대체

select employee_id, nvl2(commission_pct, salary+(salary*commission_pct), salary) as salary2 from employees;
-- nvl2 함수는 함수의 인수로 세걔의 인수를 받아서 첫번째 요소가 널이 아니면
-- 두번째 요소로, 첫번째 요소가 널이면 세번재 요소로 출력함