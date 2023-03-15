-- sum() : 특정 필드의 합계
select sum(rentprice) as "대여가격 합계" from booklist;
select sum(rentprice) as "대여가격 합계" from booklist where inprice >=18000;

-- count : 필드내의 데이터 갯수(레코드 갯수)
select count(*) as "회원인원수" from MEMBERLIST;
select count(*) as "회원인원수" from memberlist where bpoint >=20000

-- avg : 평균
select avg(inprice) from booklist;
select round(avg(inprice),0) from booklist;

-- MAX : 최대값
select max(inprice) from booklist;
-- min : 최소값
select min(inprice) from booklist;

-- variance(분산), stddev(표준편차)
select variance(salary), STDDEV(salary) from employees;
select salary from EMPLOYEES;

-- group by : 하나의 필드를 지목해서 같은 값끼리 그룹을 형성한 결과를 도출합니다
select * from rentlist;
-- 도서별 대여 건수
select count(*) from rentlist; -- 전체 대여 건수
select booknum,count(*) from rentlist group by booknum; -- 각 도서별 대여건수 : 한번도 대여안된 도서제외


-- rentlist 날짜별 할인금액의 평균
select rentdate, avg(discount) from rentlist group by rentdate order by rentdate desc;
-- rentlist 날짜별 대여 건수
select rentdate, count(*) from rentlist group by rentdate;


-- employees 테이블의 부서id별 급여의 평균
select * from employees;
select department_id, round(avg(salary)) from EMPLOYEES group by department_id order by round(avg(salary));

-- kor_loan_status 테이블의 period(년도와 월)을 1차 그룹으로 region(지역) 을 2차 그룹으로한 대출 잔액(loan_jan_amt) 합계
select * from kor_loan_status;
select period, region, sum(loan_jan_amt) from kor_loan_status group by period , region order by period, region;

-- having 절 : 그룹핑된 내용들에 조건을 붙일때
-- 날짜별 할인금액의 평균을 출력합니다. 다만 그 평균 금액이 180 미만인 그룹만 출력
select * from rentlist;
select rentdate as "날짜", avg(discount) as "할인평균" from rentlist group by rentdate having avg(discount)<180;

-- kor_loan_status 테이블의 날짜별 대출 전액의 합계 중 period 가 2013년 11월 인 데이터 출력
select * from kor_loan_status;
-- select period as " 날짜",  sum(loan_jan_amt) as "대출 전액의 합계" from KOR_LOAN_STATUS group by period having period='201311';
select period, region, sum(loan_jan_amt) totl_jan from kor_loan_status where period = '201311' group by period, region order by region;

-- ★★★ group by 절에는 select 와 from 사이에 기술되 함수를 제외한 모든 필드를 반드시 써야합니다.