use db;

insert into bank_account(iban,bic,balance) values('fr0548796264781','1876943264',400);
insert into bank_account(iban,bic,balance) values('fr0897462187894','1458796354',0);
insert into bank_account(iban,bic,balance) values('fr0897464844894','14584954854',200);
insert into bank_account(iban,bic,balance) values('de0897464844894','145887f4854',550);
insert into bank_account(iban,bic,balance) values('en0897464124894','68789954854',100);

insert into users(email,password,bank_account_id,name) values('ouioui@email.com','1234567890',1,"Didier");
insert into users(email,password,bank_account_id,name) values('nonnon@email.com','9876543210',2,"Fernand");
insert into users(email,password,bank_account_id,name) values('okok@email.com','8576543210',3,"Julie");
insert into users(email,password,bank_account_id,name) values('quatre@email.com','ouigzeggg',4,"Toun");
insert into users(email,password,bank_account_id,name) values('cinq@email.com','zguazgqazg',5,"Daniel");


insert into friend(user_id,friend_id) values(1,2);
insert into friend(user_id,friend_id) values(1,3);
insert into friend(user_id,friend_id) values(1,4);
insert into friend(user_id,friend_id) values(4,1);
insert into friend(user_id,friend_id) values(3,1);
insert into friend(user_id,friend_id) values(2,1);

insert into transactions(user_id,friend_id,amount,description,exec_time) values(1,2,20,"Resto",'2024-04-06 16:23:47');
insert into transactions(user_id,friend_id,amount,description,exec_time) values(1,2,5,"Timbre",'2024-04-14 15:23:41');





