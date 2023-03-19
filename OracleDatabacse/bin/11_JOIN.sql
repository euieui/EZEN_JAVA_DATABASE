-- JOIN
-- ; 두개이상의 테이블에 나눠져 있는 관련 데이터들을 하나의 테이블로 모아서 조회하고자 할때
-- 사용하는 명령입니다.


-- [1] 이름 scott 이 근무하는 부서명, 부서의 지역명 출력하고자 한다면...
select department_id from employees where emp_name = 'Douglas Grant';
-- 위명령을 실행후 얻어진 부서번호로 아래와 같이 부서번호 검색하여 부서명과 지역명을 알아냅니다.
select department_name, parent_id from departments where department_id=50;
-- 위의 두개의 명령을 하나의 명령으로 합해주는 역할을 join 명령이 실행합니다
-- [2] join : 두개이상의 테이블에 나뉘어 져 있는 데이터를 한번의 sql문으로 원하는 결과를 얻ㅅ습니다.

select * from departments;

-- cross join : 두개 이상의 테이블이 조인될때 where절에 의해 공통되는 컬럼에 의한 결합이 
-- 발생하지 않는 경우
-- * 가장 최악의 결과를 얻는 조인 방식
-- A 테이블과 B 테이블의 cross join 된다면
-- A 테이블의 1번 레코드와 B 테이블의 모든 레코드와 하나하나 모두 조합
-- A 테이블의 2번 레코드와 B 테이블의 모든 레코드와 하나하나 모두 조합
-- A 테이블의 3번 레코드와 B 테이블의 모든 레코드와 하나하나 모두 조합
-- ...

-- Aㅌㅔ이블의 레코드가 8개, B테이블의 레코드가 7개 라면 총 크로스조인의 결과 레코드수는 8*7 = 56
-- A테이블의 필드가 8개 , V테이블의 필드가 3개 라면 총 크로스 조인의 결과 필드 수는 8+3 =11

select * from dept; -- 레코드 4, 필드 3
select * from emp; -- 레코드 14, 필드 8

-- 크로스 조인
select * from dept, emp; -- 레코드 56 , 필드 11  (deptno 만 같은것만 의미가 있어) 나머지는 불필요한 데이터

-- equi join : 조인 대상이 되는 두테이블에서 공통적으로 존재하는 컬럼의 값이 일치하는 햄을 연결하여 결과를 생성
select * from dept, emp where emp.deptno = dept.deptno ;

-- 각 사원의 이름, 부서번호, 부서명, 지역을 출력하세요
select emp.ename, emp.deptno, dept.dname, dept.loc from dept, emp where emp.deptno = dept.deptno;

-- 이름이 SCOTT인 사원의 이름, 부서번호 , 부서명 , 위치 출력
select emp.ename, emp.deptno, dept.dname, dept.loc from dept, emp where emp.deptno = dept.deptno and emp.ename = 'SCOTT';

-- 컬럼 명 앞에 테이블 명을 기술하여 컬럼의 소속을 명확히 해주어야 오류가 없습니다
select emp.ename, dept.dname, dept.loc, emp.deptno from emp, dept where emp.deptno = dept.deptno and emp.ename ='SCOTT';


-- 테이블 명예 별칭을 부여한 후 컬럼앞에 소속테이블을 지정
-- 테이블 명으로 소속을 기술할때는 한쪽에 만 있는 필드에 생략이 가능하지만 아래와 같이
-- 별칭 부여시에는 모든 필드 앞에 반드시 별칭을 기술해야함
select a.ename, b.dname, b.loc, a.deptno from emp a, dept b where a.deptno = b.deptno and a.ename='SCOTT';

-- non-equi join
-- 동일 컬럼이 없어서 다른 조건을 사용하여 조인
-- 조인 조건에 특정 범위내에 있는지를 조사하기 위해 조건절에 조인 조건을 '=' 연산자 이외의 비교
-- 연산자를 이용
select * from emp;
select * from salgrade;

select a.ename, a.sal, b.grade from emp a,salgrade b where a.sal>=b.losal and a.sal <=b.hisal;

select a.ename, a.sal, b.grade from emp a, salgrade b where a.sal between b.losal and b.hisal;


--세개의 테이블을 하나로 join(equi, nonequi 조인의 조합)
select a.ename, a.sal, c.grade, b.dname from emp a, dept b, salgrade c where a.deptno=b.deptno and a.sal between c.losal and c.hisal;

-- 연습문제
-- rentlist 테이블의 rentdate, booknum, membernum 을 조회하되,
-- booklist 와 memberlist 테이블을 조인해서 책제목과 대여가격, 회원 이름과 사은 포인트를 출력하세요
-- 출력 순서 - 대여일자, 도서제목,회원이름,포인트,대여금액
-- 테이블의 별칭은 a,b,c
select * from rentlist;
select * from booklist;
select * from memberlist;
select a.rentdate,b.subject,c.name, c.bpoint,b.rentprice  from rentlist a, booklist b, memberlist c where b.booknum=a.booknum and a.membernum=c.membernum;

-- outer join
-- 조인 조건에 만족하지 못해서 해당 결과를 출력시에 누락이 되는 문제점이 발생할때
-- 해당 레코드를 출력하는 조인
select a.booknum,a.subject,b.rentdate from booklist a, rentlist b where a.booknum=b.booknum; -- 둘의 차이 알겠지 null 포함하면서 
select a.booknum,a.subject,b.rentdate from booklist a, rentlist b where a.booknum=b.booknum(+); -- rentlist 에 없는걸 출력함

--outer 조인으로 emp 테이블의 인원에 대한 부서명을 출력하되 부서명이 없는 필드는
-- null 값으로 표시하세요
select * from emp a, dept b where a.deptno(+)=b.deptno; -- 부서는 잇지만 사원이 없다!

-- [3] ANSI join
-- (1) Ansi Cross join
select * from emp, dept; -- 일반 크로스 조언 표현
select * from emp cross join dept; -- 일반 크로스 조인과 같은 효과

--(2) Ansi inner join -- 일반 equi 조인과 같은 효과
select ename, dname from emp a, dept b where a.deptno= b.deptno; -- 일반 equi 조인 표현 방식
select ename, dname from emp inner join dept on emp.deptno=dept.deptno; -- Ansi 이너 조인의 표현 방식
select ename, dname from emp inner join dept on emp.deptno=dept.deptno where ename ='SCOTT';

select ename, dname from emp inner join dept using(deptno);
-- 두테이블의 조인 기준이 되는 필드명이 똑같을때만 사용 가능

-- (3) Ansi Outer Join -- 기존 아우터 조인의 표현 방식
select * from emp,dept where emp.deptno=dept.deptno(+);
select * from emp,dept where emp.deptno(+)=dept.deptno;
-- Ansi Outer Join 표현 방식
select * from emp left outer join dept on emp.deptno= dept.deptno;
select * from emp right outer join dept on emp.deptno=dept.deptno;
-- 기준이 되는 필드명중 A 테이블의 필드에는 있으나 B테이블 필드에는 해당값이 없는 경우에 대한 표현여부 결정

