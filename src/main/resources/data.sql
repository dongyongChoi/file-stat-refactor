insert into users (ID, USER_NAME, CREATED_AT, MODIFIED_AT)
values (1, '홍길동', sysdate, sysdate);

insert into FOLDER (ID, USER_ID, FOLDER_NAME, CREATED_AT, MODIFIED_AT)
values (1, 1, '내폴더', sysdate, sysdate);