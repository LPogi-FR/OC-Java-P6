/* Setting up  DB */
create database db;
use db;

create table  if not exists bank_account
(
 id bigint not null,
 iban varchar(34) null,
 bic varchar(11) null,
 balance double,
 constraint pk_bank_account primary key (id)
 );

create table if not exists friend
(
friend_id  bigint not null,
user_id bigint not null,

constraint pk_friend primary key(friend_id,user_id)
);

create table if not exists users
(
id bigint not null,
email varchar(255)  null,
password varchar(10) null,
bank_account_id bigint null,
constraint pk_users primary key(id)
);
#friend_id bigint null

create table if not exists transactions
(
id bigint not null,
user_id bigint not null,
friend_id bigint not null,
amount double,
executionTime DATE
);

alter table users
add constraint fk_userentity_on_bank_account foreign key(bank_account_id) references bank_account(id);

alter table friend
add constraint fk_friendentity_on_user foreign key(user_id) references users(id);


#alter table transactions
#add constraint fk_transactionsentity_on_user foreign key(user_id) references users(id);

#alter table transactions
#add constraint fk_transactionsentity_on_friend foreign key (friend_id) references friend(id);

insert into bank_account(id,iban,bic,balance) values(1,'fr0548796264781','1876943264',100);
insert into bank_account(id,iban,bic,balance) values(2,'fr0897462187894','1458796354',-100);

insert into users(id,email,password,bank_account_id) values(1,'ouioui@email.com','1234567890',1);
insert into users(id,email,password,bank_account_id) values(2,'nonnon@email.com','9876543210',2);
insert into users(id,email,password) values(3,'okok@email.com','8576543210');

insert into friend(friend_id,user_id) values(1,2);
insert into friend(friend_id,user_id) values(1,3);
