DELETE board where board_id=4;
update board set board_title='test4', board_status='Y' where board_id=3;
update posts set posts_status='Y' where posts_id=2;
delete posts where posts_id=4;
DELETE postscomment where com_id=3;
update postscomment set com_status='Y' where com_id=2;

commit;

drop sequence seq_board;
create sequence seq_board start with 4;
drop sequence seq_com;
create sequence seq_com start with 3;


commit;