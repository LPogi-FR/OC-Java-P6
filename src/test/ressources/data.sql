insert into bank_account(iban,bic,balance) values('fr0548796264781','1876943264',400);
insert into bank_account(iban,bic,balance) values('fr0897462187894','1458796354',0);
insert into bank_account(iban,bic,balance) values('fr0897464844894','14584954854',200);

insert into users(email,password,bank_account_id,name) values('Test1','1234567890',1,"Didier");
insert into users(email,password,bank_account_id,name) values('Test2','9876543210',2,"Fernand");
insert into users(email,password,bank_account_id,name) values('Test3','8576543210',3,"Julie");

insert into friend(user_id,friend_id) values(1,2);
insert into friend(user_id,friend_id) values(2,1);

insert into transactions(user_id,friend_id,amount,description,exec_time) values(1,2,20,"Resto",'2024-04-06 16:23:47');
insert into transactions(user_id,friend_id,amount,description,exec_time) values(1,2,5,"Timbre",'2024-04-14 15:23:41');




