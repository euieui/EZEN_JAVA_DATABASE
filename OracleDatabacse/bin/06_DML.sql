-- DML (DATA Management Language)
-- 데이터 조작 언어

-- 테이블에 레코드를 조작(추가, 수정, 삭제, 조회) 하기 위한 명령어들
-- INSERT (추가)
-- UPDATE (수정)
-- DELETE(삭제)
-- SELECT(조회 및 선택)

--[1] 샘플 테이블 생성
create table exam01(
	deptno number(2),   -- 부서번호
	dname varchar2(14), -- 부서명
	loc varchar2(14) -- 위치
);

-- 레코드 추가
-- 레코드 추가 명령 사용1
-- insert into 테이블 이름 (필드명1, 필드명2, ....) values (값1 , 값2, ....)
-- 값은 문자('123')와 숫자(123)를 구분하여 입력합니다

-- 레코드 추가 명령 사용 2
-- insert into 테이블 이름 values (전체 column(필드,열)에 넣을 값들);

-- 첫번째 방식은 필드명과 입력되어야 하는 값을 1:1 로 매핑하여 입력합니다
-- 널값이 있어도 되는 필드는 필드명, 또는 기본값이 있는 필드명은 필드명과 값을 생략하고 입력가능합니다
-- 두번째 방식은 모든 필드에 해당하는 데이터를 모두 입력하는 경우로서 필드명들을 명령어 속에
-- 나열하지 않아도 되지만, 필드의 순서대로 빠짐없이 데이터가 나열되어야 하는 불편함도 있습니다.

-- 첫번째 방식의 레코드 추가
insert into exam01(deptno, dname, loc) values(10,'ACCOUNT','NEW YORK');

-- 두번째 방식의 레코드 추가
insert into exam01 values(30,'SALES','CHICHAGO');

-- 두가지 방법 모두 null 값을 입력할 수 있습니다.
insert into exam01(deptno, dname) values (20, 'MARKETING'); -- 첫번째 방법
insert into exam01 values (40,'OPERATION',null); -- 두번째 방법   //--- 무슨키가 없어서 에러가 생긴다 ? pk? 근데 데이터는 들어갔네

select * from exam01;
select * from booklist;

-- 두가지 방법중 자유롭게 선택하여서, booklist 테이블에 10개의 레코드를 추가해주세요.
-- booknum 은 시퀀스를 이용합니다.
-- grade 는 'all' '13' '18' 세가지만 골라서 입력해주세요
insert into booklist(booknum,subject,makeyear,inprice,rentprice,grade) values (book_seq.nextVal,'좀비아이',2020,12000,2500,'all');
insert into booklist values (book_seq.nextVal,'일곱해의 마지막',2020,12150,2000,'all');
insert into booklist values (book_seq.nextVal,'럭키',2020,14400,2500,'all');
insert into booklist values (book_seq.nextVal,'불편한 편의점',2019,12600,2000,'13');
insert into booklist values (book_seq.nextVal,'약속의 땅',2021,29700,3000,'all');
insert into booklist values (book_seq.nextVal,'달러구트의 꿈 백화점',2020,12420,2000,'13');
insert into booklist values (book_seq.nextVal,'완전한행복',2019,14220,2500,'all');
insert into booklist values (book_seq.nextVal,'아몬드',2019,10800,1500,'all');
insert into booklist values (book_seq.nextVal,'문명. 2',2018,13320,2000,'18');
insert into booklist values (book_seq.nextVal,'이것이자바다',2017,30000,3000,'18');

-- memberlist 에 6명의 데이터를 추가해주세요 member_seq 를 이용해주세요

--- //// 5번부터시작해서 지워보자 일단!
select * from memberlist;
drop sequence member_seq;
create sequence member_seq start with 6 increment by 1;


insert into memberlist values (member_seq.nextVal,'추신수','010-5656-1234','84/07/07',240,'20/10/01',28,'M');
insert into memberlist values (member_seq.nextVal,'류현진','010-3333-1234','83/08/08',142,'20/10/01',27,'M');
insert into memberlist values (member_seq.nextVal,'손흥민','010-4444-1234','82/09/23',220,'20/10/01',23,'M');
insert into memberlist values (member_seq.nextVal,'이청용','010-6666-1234','81/06/14',440,'20/10/01',36,'F');
-- insert into memberlist values (member_seq.nextVal,'이영표','010-2580-1234','82/03/16',140,'20/10/01','M',31); 왜 공부하는데 여기 바뀌었지?
-- insert into memberlist values (member_seq.nextVal,'최지만','010-7777-1234','83/04/14',340,'20/10/01','F',29); 흠

-- rentlist 테이블도 rent_seq 를 이용해서 10개의 데이터를 추가해주세요
select * from rentlist;
insert into rentlist values ('2020/10/01',rent_seq.nextVal,16,2,100);
insert into rentlist values ('2020/10/01',rent_seq.nextVal,17,3,100);
insert into rentlist values ('2020/10/02',rent_seq.nextVal,18,4,200);
insert into rentlist values ('2020/10/02',rent_seq.nextVal,19,4,100);  -- num가 있는사람만 들어가겠지 ㅇㅇ
insert into rentlist values ('2020/10/03',rent_seq.nextVal,20,6,200);
insert into rentlist values ('2020/10/03',rent_seq.nextVal,16,7,300);
insert into rentlist values ('2020/10/04',rent_seq.nextVal,17,8,100);
insert into rentlist values ('2020/10/04',rent_seq.nextVal,21,8,300);
insert into rentlist values ('2020/10/05',rent_seq.nextVal,23,9,100);
insert into rentlist values ('2020/10/05',rent_seq.nextVal,19,10,200);




commit
-- DBMS에서의 데이터 운영은 실시간으로 데이터 원본으로 작업하고 변경사항이 저장되는 것이 아니라
-- 가상의 사본을 대상으로 작업하게 됩니다.
-- 이를 원본에 적용하기 위해서는 commit 이라는 명령으로 원본에 적용해주어야 다른 접근에서 변경이
-- 적영된 데이터를 볼 수 있습니다
-- 다만 이클립스에서의 데이터베이스 운영은 Auto Commit 이 적용되어 실행 즉시 적용되고 있습니다.


-- [3] 레코드 변경 - 수정 (UPDATE)
-- UPDATE 테이블명 SET 변경내용 WHERE 검색 조건

-- update member set age=30 where membernum=8;
-- 회원번호가 3 인 회원의 나이를 30으로 수정하세요  ///-- 회원번호가 3인?

-- 명령문에 WHERE 이후 구문은 생략이 가능합니다.
-- 다만 이부분을 생략하면 모든 레코드를 대상으로해서 UPDATE 명령이 실해오디어 모든 레코드가 수정됩니다.
-- 검색조건 : 필드명(비교-관계연산자) 조건값 으로 이루어진 조건연산이며, 흔히 자바에서 if()로 사용되며
-- 괄호안에 사용했떤 연산을 그대로 사용하는게 보통입니다
-- 나이가 29세 이상 -> WHERE AGE>=29

-- 데이터 수정의 실예
select * from exam01;
-- exam 01 테이블에서 deptno 값을 모두 30으로 수정
update exam01 set deptno=30;

-- exam01 테이블에서 dname이 'account' 인 레코드의 deptno 를 10으로 수정
update exam01 set deptno=10 where dname='ACCOUNT';

-- exam01 테이블에서 dname이 'sales'인 레코드의 deptno 를 20으로 수정
update exam01 set deptno=20 where dname='SALES';

-- exam01 테이블에서 dname이 'operation'인 레코드의 deptno를 30으로 수정
update exam01 set deptno=30 where dname='OPERATION';

-- exam01 테이블에서 dname이 'marketing'인 레코드의 deptno 를 40으로 수정
update exam01 set deptno=40 where dname='MARKETING';

-- exam01 테이블에서 deptno이 30인 레코드의 loc 를 'boston' 으로 수정
update exam01 set loc='BOSTON' where deptno=30;

-- exam01 테이블에서 deptno이 40인 레코드의 loc를'la'로 수정
update exam01 set loc='LA' where deptno=40;

select * from tabs; -- 현재 로그인된 사용자가 소유하고 있는 테이블의 목록을 볼 수 있습니다.


select * from booklist;
select * from emp;
select * from memberlist;
select * from rentlist;

-- booklist 테이블의 제목 '봉제인형 살이사건' 도서의 grade 를 '18'으로 수정  ///-- 나는 봉제인형 살인사건이 없어서 럭키를
update booklist set grade=18 where subject='럭키';

-- emp 테이블의 모든 사원의 sal 값을 10%씩 인상
update emp set sal= sal*1.1;
-- emp 테이블에서 sal 값이 3000 이상인 사원의 급여 10% 삭감
update emp set sal=sal*0.9 where sal>=3000;

-- hiredate 가 2002년 이전인 사원의 급여를 +2000 -> (2001-12-31 보다 작거나 같은)
update emp set sal=sal+2000 where hiredate <='2001-12-31';

-- ename 이 j로 시작하는 사원의 job을 manager로 변경
update emp set job='MANAGER' where ename like 'j%';
update emp set job='MANAGER' where ename like '%j';  ---//// j로 끝나는
update emp set job='MANAGER' where ename like '%j%'; ---/// j를 포함하는

-- memberlist 테이블에서 bpoint 가 200이 넘는 사람만 bpoint*100으로 변경
update memberlist set bpoint=bpoint*100 where bpoint>=200;

-- rentlist 테이블에서 할인금액이 100원이 넘으면 모두 할인 금액을 90으로 변경
update rentlist set discount=90 where discount>=100;


-- [4]레코드의 삭제
-- delete from 테이블명 where 조건식
-- rentlist 테이블에서 discount가 100이하인 레코드를 삭제
delete from rentlist where discount<=100;

-- where 절이 없으면 테이블 내의 모든 레코드를 삭제합니다


select * from rentlist
select * from booklist
-- 삭제의 제한   ///---- 외래키 때문에 지우질 못함
delete from booklist where subject = '럭키';
-- 봉제인형 살인 사건 도서과 rentlist에 대여목록으로 존재하므로...
-- 외래키의 참조무결성에 위배됩니다 따라서 이 삭제명령은 에러가 발생합니다.

-- 해결 방법 #1
-- 이를 해결하려면 우선 rentlist ㅌ테이블에 해당 도서 대여목록 레코드를 모두 삭제한 후
-- booklist 테이블에서 해당 도서를 삭제해야 합니다.

-- delete from lentlist where booknum=7;
-- delete from booklist where subject = '럭키'

-- 해결 방법 #2
-- 외래키 제약조건을 삭제한 후 다시 생성
-- 생성시에 옵션을 추가해서 참조되는 값이 삭제되면 참조하는 값도 같이 삭제되게 구성합니다.
-- 외래키 삭제
alter table rentlist drop constraint fk1;
-- 새로운 외래키 추가
alter table rentlist add constraint fk1 foreign key(booknum) references booklist(booknum) on delete cascade;
-- on delete cascade : booklist 의 도서가 삭제되면 rentlist 의 해당 도서 대여내역도 함께 삭제하는 옵션


-- meberlist 테이블에서 회원 한명을 삭제하면, rentlist 테이블에서도 해당회원이 대여한 기록을 같이 삭제하도록
-- 외래키 설정을 바꿔주세요 (외래키 제약조건 삭제 후 재생성)
alter table rentlist drop constraint fk2;
alter table rentlist add constraint fk2 foreign key(membernum) references memberlist(membernum) on delete cascade;

select * from booklist;
select * from rentlist;
select * from memberlist;
-- delete from rentlist where booknum=7;
delete from booklist where subject ='럭키';
delete from memberlist where name ='추신수';

-- 참조되는 값의 삭제가 아니라 수정은 아직 적용되지 않ㅅ습니다.
-- booklist와 memberlist 테이블의 booknum, membernum 은 수정이 아직 불가능합니다
-- 이를 해결하기 위해서 외래키 설정시 on update cascade 옵션을 추가하면 될듯하지만
-- 이는 오라클에서 허용하지 않습니다
-- mysql에서만 사용이 가능하며, 오라클에서는 뒷단원의 트리거를 공부하면서 외래키가 수정이 되도록 하겠습니다.