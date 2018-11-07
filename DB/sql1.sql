DROP DATABASE YellowPixels;
CREATE DATABASE YellowPixels;
USE YellowPixels;

CREATE TABLE Address
(
address_id int,
street varchar(20),
city varchar(20),
url varchar(50),
PRIMARY KEY(address_id)
);

CREATE TABLE Main_table
(
phone_num bigint,
category varchar(20),
address_id int,
PRIMARY KEY(phone_num)
-- FOREIGN KEY(address_id) REFERENCES Address(address_id)
-- ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Travel_agency
(
phone_num bigint PRIMARY KEY,
name varchar(20),
has_bus boolean,
has_train boolean,
has_flight boolean,
has_car boolean,
has_international boolean,
FOREIGN KEY(phone_num) REFERENCES Main_table(phone_num)
ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Hospital
(
phone_num bigint PRIMARY KEY,
name varchar(20),
has_neuro boolean,
has_ortho boolean,
has_nephro boolean,
has_cardio boolean,
FOREIGN KEY(phone_num) REFERENCES Main_table(phone_num)
ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Hotel
(
phone_num bigint PRIMARY KEY,
name varchar(20),
has_nonveg boolean,
has_homedelivery boolean,
FOREIGN KEY(phone_num) REFERENCES Main_table(phone_num)
ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Shop
(
phone_num bigint PRIMARY KEY,
name varchar(20),
shop_type varchar(20), 
FOREIGN KEY(phone_num) REFERENCES Main_table(phone_num)
ON DELETE CASCADE ON UPDATE CASCADE,
CHECK (shop_type  in ('Jewellery', 'Electronics', 'Grocery', 'Florist', 'Mall'))
);

CREATE TABLE Individual
(
phone_num bigint PRIMARY KEY, 
FName varchar(20),
LName varchar(20)
-- FOREIGN KEY(phone_num) -- REFERENCES Main_table(phone_num)
-- ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Works_for
(
individual_phone_num bigint,
works_for_phone_num bigint,
FOREIGN KEY(individual_phone_num) REFERENCES Individual(phone_num)
ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY(works_for_phone_num) REFERENCES Main_Table(phone_num)
);

-- Dummy data to test Individual UI
INSERT INTO Individual VALUES(9886144693, 'Pawan', 'Bhandarkar');
INSERT INTO Individual VALUES(9886144694, 'Pawan1','Bhandarkar1');
INSERT INTO Individual VALUES(9886144695, 'Pawan2', 'Bhandarkar2');
INSERT INTO Individual VALUES(9886144696, 'Pawan3', 'Bhandarkar3');
INSERT INTO Individual VALUES(9886144697, 'Pawan4', 'Bhandarkar4');
INSERT INTO Individual VALUES(9886144698, 'Pawan5', 'Bhandarkar5');
INSERT INTO Individual VALUES(9886144699, 'Pawan6', 'Bhandarkar6');
INSERT INTO Individual VALUES(9886144701, 'Pawan7', 'Bhandarkar7');
INSERT INTO Individual VALUES(9886144702, 'Pawan8', 'Bhandarkar8');
INSERT INTO Individual VALUES(9886144703, 'Pawan9', 'Bhandarkar9');
INSERT INTO Individual VALUES(9886144704, 'Pawan10', 'Bhandarkar10');
INSERT INTO Individual VALUES(9886144705, 'Pawan11', 'Bhandarkar11');
INSERT INTO Individual VALUES(9886144706, 'Pawan12', 'Bhandarkar12');
INSERT INTO Individual VALUES(9886144707, 'Pawan13', 'Bhandarkar13');
INSERT INTO Individual VALUES(9886144708, 'Pawan14', 'Bhandarkar14');
INSERT INTO Individual VALUES(9886144709, 'Pawan15', 'Bhandarkar15');
INSERT INTO Individual VALUES(9886144710, 'Pawan16', 'Bhandarkar16');
INSERT INTO Individual VALUES(9886144711, 'Pawan17', 'Bhandarkar17');

select * from Address;
select * from Main_table;
select * from Travel_agency;
select * from Hospital;
select * from Hotel;
select * from Shop;
select * from Individual;
select * from Works_for;
