
insert into bank_account(iban,bic,balance) values('fr0548796264781','1876943264',100);
insert into bank_account(iban,bic,balance) values('fr0897462187894','1458796354',0);
insert into bank_account(iban,bic,balance) values('fr0897464844894','14584954854',200);

insert into users(email,password,bank_account_id) values('ouioui@email.com','1234567890',1);
insert into users(email,password,bank_account_id) values('nonnon@email.com','9876543210',2);
insert into users(email,password,bank_account_id) values('okok@email.com','8576543210',3);

insert into friend(user_id,friend_id) values(1,2);
insert into friend(user_id,friend_id) values(1,3);
insert into friend(user_id,friend_id) values(3,1);
insert into friend(user_id,friend_id) values(2,1);


