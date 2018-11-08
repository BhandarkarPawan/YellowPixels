DROP DATABASE YellowPixels;
CREATE DATABASE YellowPixels;
USE YellowPixels;

CREATE TABLE Main_table
(
phone_num bigint,
category varchar(20),
street varchar(20),
city varchar(20),
url varchar(50),
PRIMARY KEY(phone_num)
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

CREATE TABLE Shops
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
LName varchar(20),
FOREIGN KEY(phone_num) REFERENCES Main_table(phone_num)
ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Works_for
(
individual_phone_num bigint,
works_for_phone_num bigint,
FOREIGN KEY(individual_phone_num) REFERENCES Individual(phone_num)
ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY(works_for_phone_num) REFERENCES Main_Table(phone_num)
);

INSERT INTO Main_table VALUES(9886144693, 'Individual', 'street1', 'CityA', 'url1');
INSERT INTO Main_table VALUES(9886144694, 'Individual', 'street2', 'CityB', 'url2');
INSERT INTO Main_table VALUES(9886144695, 'Individual', 'street3', 'CityC', 'url3');
INSERT INTO Main_table VALUES(9886144696, 'Individual', 'street4', 'CityD', 'url4');
INSERT INTO Main_table VALUES(9886144697, 'Individual', 'street5', 'CityA', 'url5');
INSERT INTO Main_table VALUES(9886144698, 'Individual', 'street6', 'CityB', 'url6');
INSERT INTO Main_table VALUES(9886144699, 'Individual', 'street7', 'CityC', 'url7');
INSERT INTO Main_table VALUES(9886144701, 'Individual', 'street8', 'CityD', 'url8');
INSERT INTO Main_table VALUES(9886144702, 'Individual', 'street9', 'CityA', 'url9');
INSERT INTO Main_table VALUES(9886144703, 'Individual', 'street10', 'CityB', 'url10');
INSERT INTO Main_table VALUES(9886144704, 'Individual', 'street11', 'CityC', 'url11');
INSERT INTO Main_table VALUES(9886144705, 'Individual', 'street12', 'CityD', 'url12');
INSERT INTO Main_table VALUES(9886144706, 'Individual', 'street13', 'CityA', 'url13');
INSERT INTO Main_table VALUES(9886144707, 'Individual', 'street14', 'CityB', 'url14');
INSERT INTO Main_table VALUES(9886144708, 'Individual', 'street15', 'CityC', 'url15');
INSERT INTO Main_table VALUES(9886144709, 'Individual', 'street16', 'CityD', 'url16');
INSERT INTO Main_table VALUES(9886144710, 'Individual', 'street17', 'CityA', 'url17');
INSERT INTO Main_table VALUES(9886144711, 'Individual', 'street18', 'CityB', 'url18');
INSERT INTO Main_table VALUES(9886144712, 'Hospital', 'street19', 'CityC', 'url19');
INSERT INTO Main_table VALUES(9886144713, 'Hospital', 'street20', 'CityD', 'url20');
INSERT INTO Main_table VALUES(9886144714, 'Hospital', 'street21', 'CityA', 'url21');
INSERT INTO Main_table VALUES(9886144715, 'Hospital', 'street22', 'CityB', 'url22');
INSERT INTO Main_table VALUES(9886144716, 'Hospital', 'street23', 'CityC', 'url23');
INSERT INTO Main_table VALUES(9886144717, 'Hospital', 'street24', 'CityD', 'url24');
INSERT INTO Main_table VALUES(9886144718, 'Hospital', 'street25', 'CityA', 'url25');
INSERT INTO Main_table VALUES(9886144719, 'Hospital', 'street26', 'CityB', 'url26');
INSERT INTO Main_table VALUES(9886144720, 'Hospital', 'street27', 'CityC', 'url27');
INSERT INTO Main_table VALUES(9886144721, 'Hospital', 'street28', 'CityD', 'url28');
INSERT INTO Main_table VALUES(9886144722, 'Travel_agency', 'street29', 'CityA', 'url29');
INSERT INTO Main_table VALUES(9886144723, 'Travel_agency', 'street30', 'CityB', 'url30');
INSERT INTO Main_table VALUES(9886144724, 'Travel_agency', 'street31', 'CityC', 'url31');
INSERT INTO Main_table VALUES(9886144725, 'Travel_agency', 'street32', 'CityD', 'url32');
INSERT INTO Main_table VALUES(9886144726, 'Travel_agency', 'street33', 'CityA', 'url33');
INSERT INTO Main_table VALUES(9886144727, 'Travel_agency', 'street34', 'CityB', 'url34');
INSERT INTO Main_table VALUES(9886144728, 'Travel_agency', 'street35', 'CityC', 'url35');
INSERT INTO Main_table VALUES(9886144729, 'Travel_agency', 'street36', 'CityD', 'url36');
INSERT INTO Main_table VALUES(9886144730, 'Travel_agency', 'street37', 'CityA', 'url37');
INSERT INTO Main_table VALUES(9886144731, 'Travel_agency', 'street38', 'CityB', 'url38');
INSERT INTO Main_table VALUES(9886144732, 'Hotel', 'street40', 'CityC', 'url39');
INSERT INTO Main_table VALUES(9886144733, 'Hotel', 'street41', 'CityD', 'url40');
INSERT INTO Main_table VALUES(9886144734, 'Hotel', 'street42', 'CityA', 'url41');
INSERT INTO Main_table VALUES(9886144735, 'Hotel', 'street43', 'CityB', 'url42');
INSERT INTO Main_table VALUES(9886144736, 'Hotel', 'street44', 'CityC', 'url43');
INSERT INTO Main_table VALUES(9886144737, 'Hotel', 'street45', 'CityD', 'url44');
INSERT INTO Main_table VALUES(9886144738, 'Hotel', 'street46', 'CityA', 'url45');
INSERT INTO Main_table VALUES(9886144739, 'Hotel', 'street47', 'CityB', 'url46');
INSERT INTO Main_table VALUES(9886144740, 'Hotel', 'street48', 'CityC', 'url47');
INSERT INTO Main_table VALUES(9886144741, 'Hotel', 'street49', 'CityD', 'url48');
INSERT INTO Main_table VALUES(9886144742, 'Shops', 'street50', 'CityA', 'url49');
INSERT INTO Main_table VALUES(9886144743, 'Shops', 'street51', 'CityB', 'url50');
INSERT INTO Main_table VALUES(9886144744, 'Shops', 'street52', 'CityC', 'url51');
INSERT INTO Main_table VALUES(9886144745, 'Shops', 'street53', 'CityD', 'url52');
INSERT INTO Main_table VALUES(9886144746, 'Shops', 'street54', 'CityA', 'url53');
INSERT INTO Main_table VALUES(9886144747, 'Shops', 'street55', 'CityB', 'url54');
INSERT INTO Main_table VALUES(9886144748, 'Shops', 'street56', 'CityC', 'url55');
INSERT INTO Main_table VALUES(9886144749, 'Shops', 'street57', 'CityD', 'url56');
INSERT INTO Main_table VALUES(9886144750, 'Shops', 'street58', 'CityA', 'url57');
INSERT INTO Main_table VALUES(9886144751, 'Shops', 'street39', 'CityB', 'url58');

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

INSERT INTO Hospital VALUES(9886144712, 'Hospital1', 0,0,0,0);
INSERT INTO Hospital VALUES(9886144713, 'Hospital2', 0,1,0,1);
INSERT INTO Hospital VALUES(9886144714, 'Hospital3', 1,0,1,0);
INSERT INTO Hospital VALUES(9886144715, 'Hospital4', 0,1,1,0);
INSERT INTO Hospital VALUES(9886144716, 'Hospital5', 1,0,0,0);
INSERT INTO Hospital VALUES(9886144717, 'Hospital6', 0,1,1,1);
INSERT INTO Hospital VALUES(9886144718, 'Hospital7', 1,1,0,1);
INSERT INTO Hospital VALUES(9886144719, 'Hospital8', 0,1,0,0);
INSERT INTO Hospital VALUES(9886144720, 'Hospital9', 1,0,1,0);
INSERT INTO Hospital VALUES(9886144721, 'Hospital10', 1,1,1,1);

INSERT INTO Travel_agency VALUES(9886144722, 'TravelAgency1', 1,0,0,0,1);
INSERT INTO Travel_agency VALUES(9886144723, 'TravelAgency2', 0,1,1,0,1);
INSERT INTO Travel_agency VALUES(9886144724, 'TravelAgency3', 0,1,1,1,1);
INSERT INTO Travel_agency VALUES(9886144725, 'TravelAgency4', 0,1,0,1,0);
INSERT INTO Travel_agency VALUES(9886144726, 'TravelAgency5', 1,1,0,0,1);	
INSERT INTO Travel_agency VALUES(9886144727, 'TravelAgency6', 1,0,1,0,0);
INSERT INTO Travel_agency VALUES(9886144728, 'TravelAgency7', 0,1,0,0,0);
INSERT INTO Travel_agency VALUES(9886144729, 'TravelAgency8', 1,0,1,0,0);
INSERT INTO Travel_agency VALUES(9886144730, 'TravelAgency9', 1,1,1,1,1);
INSERT INTO Travel_agency VALUES(9886144731, 'TravelAgency10', 0,0,1,0,0);

INSERT INTO Hotel VALUES(9886144732, 'Hotel1', 1,0);
INSERT INTO Hotel VALUES(9886144733, 'Hotel2', 0,1);
INSERT INTO Hotel VALUES(9886144734, 'Hotel3', 1,0);
INSERT INTO Hotel VALUES(9886144735, 'Hotel4', 1,1);
INSERT INTO Hotel VALUES(9886144736, 'Hotel5', 0,0);
INSERT INTO Hotel VALUES(9886144737, 'Hotel6', 0,1);
INSERT INTO Hotel VALUES(9886144738, 'Hotel7', 1,1);
INSERT INTO Hotel VALUES(9886144739, 'Hotel8', 1,0);
INSERT INTO Hotel VALUES(9886144740, 'Hotel9', 0,0);
INSERT INTO Hotel VALUES(9886144741, 'Hote10', 1,0);

INSERT INTO Shops VALUES(9886144742, 'Shop1','Electronics');
INSERT INTO Shops VALUES(9886144743, 'Shop2','Grocery');
INSERT INTO Shops VALUES(9886144744, 'Shop3','Mall');
INSERT INTO Shops VALUES(9886144745, 'Shop4','Electronics');
INSERT INTO Shops VALUES(9886144746, 'Shop5','Jewellery');
INSERT INTO Shops VALUES(9886144747, 'Shop6','Florist');
INSERT INTO Shops VALUES(9886144748, 'Shop7','Jewellery');
INSERT INTO Shops VALUES(9886144749, 'Shop8','Florist');
INSERT INTO Shops VALUES(9886144750, 'Shop9','Grocery');
INSERT INTO Shops VALUES(9886144751, 'Shop10','Mall');

select * from Main_table;
select * from Travel_agency;
select * from Hospital;
select * from Hotel;
select * from Shops;
select * from Individual;
select * from Works_for;
