-- * 오라클 - 뷰(View)
--		- 물리적인 테이블에 근거한 논리적인 가상 테이블.
--		- 가상이란 단어는 실질적으로 데이터를 저장하고 있지 않기 때문에 붙인 것이고,
--		   테이블이란 단어는 실질적으로 데이터를 저장하고 있지 않더라도 사용 계정자는
--		   마치 테이블을 사용하는 것과 동일하게 뷰를 사용할 수 있기 때문에 붙인 것입니다.
--		- 뷰는 기본테이블에서 파생된 객체로서 기본테이블에 대한 하나의 쿼리문입니다.
--		- 실제 테이블에 저장된 데이터를 뷰를 토앻서 볼 수 있도록 합니다.
--		- 사용자에게 주어진 뷰를 통해서 기본 테이블을 제한적으로 사용할 수 있습니다
--		- 뷰는 이미 존재하고 있는 테이블에 제한적으로 접근하도록 합니다
--		- 뷰를 생성하기 위해서는 실질적으로 데이터를 저장하고 있는 물리적인 테이블이
--		   존재해야 되는데 이 테이블은 기본테이블이라고 합니다
--		- 두개이상의 테이블 또는 한개의 테이블이나 또 다른 뷰를 참조하는 객체입니다.
--		- 저장된 테이블이라기 보다 공식 또는 select 문을 갖고 있다가 명령대로 불러와 그때 그때 구성하는 형식입니다.
--			- 원본의 데이터 변화가 실시간으로 반영됩니다
--		- 생성방법
--			- create or Replace View 뷰이름 as (select 구문)
--			- select 명령과 join 명령 학습 후 다시 상세하게 공부할 에정입니다.


-- * 오라클 - 시퀀스 (Sequence)
--		: 테이블 내의 유일한 숫자를 자동으로 생성하는 자동 번호 발생기
--		: 테이블 생성 후 시퀀스(일련번호)를 따로 만들어야 한다.
-- 생성 방법
-- create sequence 시퀀스이름 start with 시작 숫자 increment by 증가량;

-- 주로 number 형식에 기본키값으로 사용합니다.
-- 일련번호정도로 이해하셔도 무방합니다.
-- number(자리수) : 자료형의 자리수가 몇자리냐에 따라 그 만큼 숫자가 증가 할 수 있습니다.

select * from memberlist;

--[1] 시퀀스의 생성
create sequence book_seq start with 1 increment by 1;

insert into memberlist(membernum,name,phone) values (book_seq.nextVal,'홍길동','010-1111-2222');
insert into memberlist(membernum,name,phone) values (book_seq.nextVal,'홍길서','010-3333-4444');
insert into memberlist(membernum,name,phone) values (book_seq.nextVal,'홍길남','010-5555-6666');
insert into memberlist(membernum,name,phone) values (book_seq.nextVal,'홍길북','010-7777-8888');

--[2] 현재 시퀀스가 어디까지 증가되어져 있는지 확인
select book_seq.currval, book_seq.nextVal from dual; -- 오 안써본 것 같음 select book_seq.currval, book_seq.nextVal from dual;

--[3] 시퀀스 수정 : 최대 증가값을 14까지로 제한
alter sequence book_seq maxvalue 14;

--[4] 시퀀스 삭제
drop sequence book_seq;

--[5] 시퀀스 재생성 : 다음 숫자부터 시작하게 하여 기존 레코드와 중복 되지 않게 합니다
create sequence book_seq start with 15 increment by 1;

-- * 현재 로그인한 사용자가 만들거나 보유하고 있는 테이블의 목록을 조화
select * from tabs;

-- 1부터 1씩 늘어나는 member_seq rent_seq 를 생성해주세요
create sequence member_seq start with 1 increment by 1;
create sequence rent_seq start with 1 increment by 1;


















