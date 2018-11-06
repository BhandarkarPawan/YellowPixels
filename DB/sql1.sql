create database YellowPixel
use YellowPixel

select * from Address
select * from Main_table
select * from Travel_agency
select * from Hospital
select * from Hotel
select * from Shop
select * from Individual
select * from Works_for

drop table Address
drop table Main_table
drop table Travel_agency
drop table Hospital
drop table Hotel
drop table Shop
drop table Individual
drop table Works_for

create table Address
(
address_id bigint,
street varchar(10),
city varchar(10),
url varchar(50),
primary key(address_id)
);

create table Main_table
(
t_id bigint,
category varchar(10),
address_id bigint,
primary key(t_id),
foreign key(address_id) references Address(address_id)
on delete cascade on update cascade
);

create table Travel_agency
(
t_id bigint,
name varchar(10),
phone_no bigint,
has_bus boolean,
has_train boolean,
has_flight boolean,
has_car boolean,
has_internaltional boolean,
address_id bigint,
explaination varchar(50),
primary key(phone_no),
foreign key(t_id) references Main_table(t_id)
on delete cascade on update cascade,
foreign key(address_id) references Main_table(address_id)
on delete cascade on update cascade
);

create table Hospital
(
t_id bigint,
name varchar(10),
phone_no bigint,
has_neurology boolean,
has_orthopedics boolean,
has_nephrology boolean,
has_cardiology boolean,
address_id bigint,
explaination varchar(50),
primary key(phone_no),
foreign key(t_id) references Main_table(t_id)
on delete cascade on update cascade,
foreign key(address_id) references Main_table(address_id)
on delete cascade on update cascade
);

create table Hotel
(
t_id bigint,
name varchar(10),
phone_no bigint,
has_veg boolean,
has_nonveg boolean,
has_homedelivery boolean,
rate real,
address_id bigint,
explaination varchar(50),
primary key(phone_no),
foreign key(t_id) references Main_table(t_id)
on delete cascade on update cascade,
foreign key(address_id) references Main_table(address_id)
on delete cascade on update cascade
);

create table Shop
(
t_id bigint,
name varchar(10),
phone_no bigint,
has_electronics boolean,
has_grocery boolean,
has_jewellery boolean,
has_florist boolean,
address_id bigint,
explaination varchar(50),
primary key(phone_no),
foreign key(t_id) references Main_table(t_id)
on delete cascade on update cascade,
foreign key(address_id) references Main_table(address_id)
on delete cascade on update cascade
);

create table Individual
(
t_id bigint,
name varchar(10),
phon_no bigint UNIQUE,
ssn bigint,
address_id bigint,
explaination varchar(50),
primary key(ssn),
foreign key(t_id) references Main_table(t_id)
on delete cascade on update cascade,
foreign key(address_id) references Main_table(address_id)
on delete cascade on update cascade
);

create table Works_for
(
ssn bigint,
t_id bigint,
foreign key(ssn) references Individual(ssn)
on delete cascade on update cascade,
foreign key(t_id) references Main_Table(t_id)
);