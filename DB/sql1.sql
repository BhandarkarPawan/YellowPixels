DROP DATABASE YellowPixels;
CREATE DATABASE YellowPixels;
USE YellowPixels;

CREATE TABLE Main_table
(
phone_num bigint,
category varchar(20),

street varchar(20),
city varchar(20),
url varchar(350),
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

INSERT INTO Main_table VALUES(9886144693, 'Individual', 'Karangalpady', 'Mangalore', 'https://www.google.com/maps/place/12%C2%B052''39.6%22N+74%C2%B050''37.7%22E/@12.8776672,74.8416273,17z/data=!3m1!4b1!4m6!3m5!1s0x0:0x0!7e2!8m2!3d12.8776619!4d74.8438158');
INSERT INTO Main_table VALUES(9886144694, 'Individual', 'Karangalpady', 'Mangalore', 'https://www.google.com/maps/place/12%C2%B052''34.8%22N+74%C2%B050''39.2%22E/@12.8763282,74.8420353,17z/data=!3m1!4b1!4m6!3m5!1s0x0:0x0!7e2!8m2!3d12.8763232!4d74.8442237');
INSERT INTO Main_table VALUES(9886144695, 'Individual', 'Ashok Nagar', 'Mangalore', 'https://www.google.com/maps/place/12%C2%B053''34.3%22N+74%C2%B049''25.9%22E/@12.8928632,74.8216633,17z/data=!3m1!4b1!4m6!3m5!1s0x0:0x0!7e2!8m2!3d12.8928584!4d74.8238523');
INSERT INTO Main_table VALUES(9886144696, 'Individual', 'Kudroli', 'Mangalore', 'https://www.google.com/maps/place/12%C2%B052''38.6%22N+74%C2%B049''45.0%22E/@12.8773922,74.8269783,17z/data=!3m1!4b1!4m6!3m5!1s0x0:0x0!7e2!8m2!3d12.8773872!4d74.8291666');
INSERT INTO Main_table VALUES(9886144697, 'Individual', 'Kadri', 'Mangalore', 'https://www.google.com/maps/place/12%C2%B053''27.4%22N+74%C2%B051''41.2%22E/@12.8909482,74.8592623,17z/data=!3m1!4b1!4m6!3m5!1s0x0:0x0!7e2!8m2!3d12.8909435!4d74.8614513');
INSERT INTO Main_table VALUES(9886144698, 'Individual', 'Car street', 'Mangalore', 'https://www.google.com/maps/place/12%C2%B052''13.7%22N+74%C2%B050''05.7%22E/@12.8704763,74.8343718,19z/data=!3m1!4b1!4m6!3m5!1s0x0:0x0!7e2!8m2!3d12.8704751!4d74.834919');
INSERT INTO Main_table VALUES(9886144699, 'Individual', 'Lalbhag', 'Mangalore', 'https://www.google.com/maps/place/12%C2%B052''59.9%22N+74%C2%B050''04.4%22E/@12.8832992,74.8323603,17z/data=!3m1!4b1!4m6!3m5!1s0x0:0x0!7e2!8m2!3d12.8832937!4d74.8345492');
INSERT INTO Main_table VALUES(9886144701, 'Individual', 'Lalbagh', 'Mangalore', 'https://www.google.com/maps/place/12%C2%B053''11.9%22N+74%C2%B050''20.8%22E/@12.8866462,74.8369203,17z/data=!3m1!4b1!4m6!3m5!1s0x0:0x0!7e2!8m2!3d12.8866408!4d74.8391089');
INSERT INTO Main_table VALUES(9886144702, 'Individual', 'Kuloor', 'Mangalore', 'https://www.google.com/maps/place/12%C2%B055''34.6%22N+74%C2%B049''57.1%22E/@12.9262802,74.8303333,17z/data=!3m1!4b1!4m6!3m5!1s0x0:0x0!7e2!8m2!3d12.9262753!4d74.8325221');
INSERT INTO Main_table VALUES(9886144703, 'Individual', 'Suratkal', 'Mangalore', 'https://www.google.com/maps/place/12%C2%B059''42.9%22N+74%C2%B047''37.8%22E/@12.9952522,74.7916363,17z/data=!3m1!4b1!4m6!3m5!1s0x0:0x0!7e2!8m2!3d12.9952473!4d74.7938248');
INSERT INTO Main_table VALUES(9886144704, 'Individual', 'Suratkal', 'Mangalore', 'https://www.google.com/maps/place/13%C2%B000''26.2%22N+74%C2%B047''24.3%22E/@13.0072742,74.7879053,17z/data=!3m1!4b1!4m6!3m5!1s0x0:0x0!7e2!8m2!3d13.0072691!4d74.7900945');
INSERT INTO Main_table VALUES(9886144705, 'Individual', 'Kadri', 'Mangalore', 'https://www.google.com/maps/place/12%C2%B053''14.2%22N+74%C2%B051''31.1%22E/@12.8872882,74.8564623,17z/data=!3m1!4b1!4m6!3m5!1s0x0:0x0!7e2!8m2!3d12.8872829!4d74.8586506');
INSERT INTO Main_table VALUES(9886144706, 'Individual', 'Ballalbagh', 'Mangalore', 'https://www.google.com/maps/place/12%C2%B053''04.4%22N+74%C2%B050''01.3%22E/@12.8845532,74.8314953,17z/data=!3m1!4b1!4m6!3m5!1s0x0:0x0!7e2!8m2!3d12.8845478!4d74.8336838');
INSERT INTO Main_table VALUES(9886144707, 'Individual', 'Ballalbagh', 'Mangalore', 'https://www.google.com/maps/place/12%C2%B052''59.2%22N+74%C2%B050''08.3%22E/@12.8831102,74.8334583,17z/data=!3m1!4b1!4m6!3m5!1s0x0:0x0!7e2!8m2!3d12.8831047!4d74.8356474');
INSERT INTO Main_table VALUES(9886144708, 'Individual', 'Karangalpady', 'Mangalore', 'https://www.google.com/maps/place/12%C2%B052''45.3%22N+74%C2%B050''42.6%22E/@12.8792462,74.8429683,17z/data=!3m1!4b1!4m6!3m5!1s0x0:0x0!7e2!8m2!3d12.8792412!4d74.8451569');
INSERT INTO Main_table VALUES(9886144709, 'Individual', 'Kadri', 'Mangalore', 'https://www.google.com/maps/place/12%C2%B053''05.0%22N+74%C2%B051''26.7%22E/@12.8847362,74.8552173,17z/data=!3m1!4b1!4m6!3m5!1s0x0:0x0!7e2!8m2!3d12.8847308!4d74.8574059');
INSERT INTO Main_table VALUES(9886144710, 'Individual', 'Ballalbagh', 'Mangalore', 'https://www.google.com/maps/place/12%C2%B053''02.8%22N+74%C2%B050''16.9%22E/@12.8841242,74.8358403,17z/data=!3m1!4b1!4m6!3m5!1s0x0:0x0!7e2!8m2!3d12.8841188!4d74.8380294');
INSERT INTO Main_table VALUES(9886144711, 'Individual', 'Ballalbagh', 'Mangalore', 'https://www.google.com/maps/place/12%C2%B052''57.8%22N+74%C2%B050''16.9%22E/@12.8827232,74.8358243,17z/data=!3m1!4b1!4m6!3m5!1s0x0:0x0!7e2!8m2!3d12.8827184!4d74.8380127');
INSERT INTO Main_table VALUES(9886144712, 'Hospital', 'Hampankatta', 'Mangalore', 'https://www.google.com/maps/place/KMC+Hospital+Mangalore/@12.8709456,74.8452876,18z/data=!4m8!1m2!2m1!1skmc+mangalore!3m4!1s0x3ba35a4993ec4925:0xf7e8eff2463b0352!8m2!3d12.8721648!4d74.848842');
INSERT INTO Main_table VALUES(9886144713, 'Hospital', 'Kavoor', 'Mangalore', 'https://www.google.com/maps/place/12%C2%B055''55.0%22N+74%C2%B051''22.0%22E/@12.9319562,74.8539203,17z/data=!3m1!4b1!4m6!3m5!1s0x0:0x0!7e2!8m2!3d12.9319505!4d74.8561087');
INSERT INTO Main_table VALUES(9886144714, 'Hospital', 'Kavoor', 'Mangalore', 'https://www.google.com/maps/place/12%C2%B055''58.0%22N+74%C2%B051''19.7%22E/@12.9327932,74.8532873,17z/data=!3m1!4b1!4m6!3m5!1s0x0:0x0!7e2!8m2!3d12.9327875!4d74.855476');
INSERT INTO Main_table VALUES(9886144715, 'Hospital', 'National highway 66', 'Mangalore', 'https://www.google.com/maps/place/A.J.+Hospital/@12.8993579,74.8438537,17z/data=!3m1!4b1!4m5!3m4!1s0x3ba35a682d20b7bb:0x56352072513aae09!8m2!3d12.8993527!4d74.8460424');
INSERT INTO Main_table VALUES(9886144716, 'Hospital', 'Kottara', 'Mangalore', 'https://www.google.com/maps/place/12%C2%B054''40.7%22N+74%C2%B050''07.4%22E/@12.9112992,74.8332033,17z/data=!3m1!4b1!4m6!3m5!1s0x0:0x0!7e2!8m2!3d12.9112939!4d74.8353917');
INSERT INTO Main_table VALUES(9886144717, 'Hospital', 'Hampankatta', 'Mangalore', 'https://www.google.com/maps/place/12%C2%B052''20.1%22N+74%C2%B050''54.9%22E/@12.8722642,74.8464063,17z/data=!3m1!4b1!4m6!3m5!1s0x0:0x0!7e2!8m2!3d12.8722589!4d74.8485952');
INSERT INTO Main_table VALUES(9886144718, 'Hospital', 'Kodialbail', 'Mangalore', 'https://www.google.com/maps/place/Yenepoya+Specialty+Hospital/@12.873045,74.8390355,17z/data=!3m1!4b1!4m5!3m4!1s0x3ba35a4e4fe99a89:0xed72ce8be87c6920!8m2!3d12.8730398!4d74.8412242');
INSERT INTO Main_table VALUES(9886144719, 'Hospital', 'Kankanady', 'Mangalore', 'https://www.google.com/maps/place/OMEGA+Hospital/@12.8704011,74.8607221,17z/data=!3m1!4b1!4m5!3m4!1s0x3ba35a2d8e82f17b:0xc50c7a94a531a6b1!8m2!3d12.8703959!4d74.8629108');
INSERT INTO Main_table VALUES(9886144720, 'Hospital', 'Pumpwell', 'Mangalore', 'https://www.google.com/maps/place/Indiana+Hospital+%26+Heart+Institute/@12.8677186,74.8640617,17z/data=!3m1!4b1!4m5!3m4!1s0x3ba35a2cc3237679:0xfab2afa5f124a0e1!8m2!3d12.8677134!4d74.8662504');
INSERT INTO Main_table VALUES(9886144721, 'Hospital', 'Balmatta', 'Mangalore', 'https://www.google.com/maps/place/S.C.S.Hospital/@12.8760026,74.8477934,17z/data=!3m1!4b1!4m5!3m4!1s0x3ba35a3721e0dadb:0x456982ce690f83f!8m2!3d12.8759974!4d74.8499821');
INSERT INTO Main_table VALUES(9886144722, 'Travel_agency', 'Kavoor', 'Mangalore', 'https://www.google.com/maps/place/Sri+Kateel+Tours+%26+Travels+Pvt.+Ltd.+Taxi+Service+In+Mangalore/@12.9263708,74.8549262,17z/data=!4m8!1m2!2m1!1sshri+kateel+tours!3m4!1s0x3ba350b88c9e972d:0x5a7256d5dd205727!8m2!3d12.9270471!4d74.8578267');
INSERT INTO Main_table VALUES(9886144723, 'Travel_agency', 'Urwa', 'Mangalore', 'https://www.google.com/maps/place/Rachana+Travels/@12.8962467,74.833508,17z/data=!3m1!4b1!4m5!3m4!1s0x3ba35a64dec4b569:0xa1463c075d633829!8m2!3d12.8962415!4d74.8356967');
INSERT INTO Main_table VALUES(9886144724, 'Travel_agency', 'Attavar', 'Mangalore', 'https://www.google.com/maps/place/12%C2%B051''51.0%22N+74%C2%B050''37.3%22E/@12.8641772,74.8415103,17z/data=!3m1!4b1!4m6!3m5!1s0x0:0x0!7e2!8m2!3d12.8641717!4d74.8436986');
INSERT INTO Main_table VALUES(9886144725, 'Travel_agency', 'Bunder', 'Mangalore', 'https://www.google.com/maps/place/12%C2%B052''03.0%22N+74%C2%B050''22.2%22E/@12.8675092,74.8372983,17z/data=!3m1!4b1!4m6!3m5!1s0x0:0x0!7e2!8m2!3d12.8675038!4d74.8394868');
INSERT INTO Main_table VALUES(9886144726, 'Travel_agency', 'Bejai', 'Mangalore', 'https://www.google.com/maps/place/Cox+and+Kings+Mangalore/@12.883022,74.8429869,17z/data=!4m8!1m2!2m1!1scoz+and+kings+travels+mangalore!3m4!1s0x3ba35a418eaefe51:0x37dde0ad678f5e7b!8m2!3d12.885827!4d74.845264');
INSERT INTO Main_table VALUES(9886144727, 'Travel_agency', 'Lallbagh', 'Mangalore', 'https://www.google.com/maps/place/12%C2%B053''01.8%22N+74%C2%B050''32.0%22E/@12.8838272,74.8400253,17z/data=!3m1!4b1!4m6!3m5!1s0x0:0x0!7e2!8m2!3d12.8838221!4d74.8422144');
INSERT INTO Main_table VALUES(9886144728, 'Travel_agency', 'Balmatta', 'Mangalore', 'https://www.google.com/maps/place/Globe+Travels/@12.8715782,74.8458193,17z/data=!3m1!4b1!4m5!3m4!1s0x3ba35a4a6eff1cbd:0xc9d4b1e29225e0bd!8m2!3d12.871573!4d74.848008');
INSERT INTO Main_table VALUES(9886144729, 'Travel_agency', 'PVS', 'Mangalore', 'https://www.google.com/maps/place/Nirmala+Travels/@12.8760312,74.8414813,17z/data=!3m1!4b1!4m5!3m4!1s0x3ba35a4f5e97beb7:0x2fea6a3537ce33e6!8m2!3d12.876026!4d74.84367');
INSERT INTO Main_table VALUES(9886144730, 'Travel_agency', 'Balmatt', 'Mangalore', 'https://www.google.com/maps/place/Flora+Travels/@12.8710582,74.8531073,17z/data=!3m1!4b1!4m5!3m4!1s0x3ba350a13025ca43:0xf6fa4029182e6836!8m2!3d12.871053!4d74.855296');
INSERT INTO Main_table VALUES(9886144731, 'Travel_agency', 'PVS', 'Mangalore', 'https://www.google.com/maps/place/Riya+the+travel+expert/@12.8746952,74.8388273,17z/data=!3m1!4b1!4m5!3m4!1s0x3ba35a4f9591c85b:0x5e082f1a80b7ab0!8m2!3d12.87469!4d74.841016');
INSERT INTO Main_table VALUES(9886144732, 'Hotel', 'Kottara', 'Mangalore', 'https://www.google.com/maps/place/Ginger+Hotel/@12.909004,74.8337824,17z/data=!3m1!4b1!4m5!3m4!1s0x3ba350827cbca255:0xc44341ca94604c7!8m2!3d12.9089988!4d74.8359711');
INSERT INTO Main_table VALUES(9886144733, 'Hotel', 'Bunts hostel', 'Mangalore', 'https://www.google.com/maps/place/Goldfinch+Hotel/@12.8742935,74.846971,17z/data=!3m1!4b1!4m5!3m4!1s0x3ba350a13025ca43:0x767a67fcef3026d!8m2!3d12.8742883!4d74.8491597');
INSERT INTO Main_table VALUES(9886144734, 'Hotel', 'Bunts hostel', 'Mangalore', 'https://www.google.com/maps/place/12%C2%B052''37.8%22N+74%C2%B050''57.6%22E/@12.8771802,74.8471423,17z/data=!3m1!4b1!4m6!3m5!1s0x0:0x0!7e2!8m2!3d12.877175!4d74.8493314');
INSERT INTO Main_table VALUES(9886144735, 'Hotel', 'PVS', 'Mangalore', 'https://www.google.com/maps/place/Treebo+Pappilon+Palace+-+Hotel+in+Mangalore/@12.8748292,74.8377853,17z/data=!3m1!4b1!4m5!3m4!1s0x3ba35a4fef3f71dd:0x78e6097e09f2c09d!8m2!3d12.874824!4d74.839974');
INSERT INTO Main_table VALUES(9886144736, 'Hotel', 'PVS', 'Mangalore', 'https://www.google.com/maps/place/The+Ocean+Pearl/@12.8752682,74.8382637,17z/data=!3m1!4b1!4m5!3m4!1s0x3ba35a4fecc2654f:0x100f75e680494323!8m2!3d12.875263!4d74.8404524');
INSERT INTO Main_table VALUES(9886144737, 'Hotel', 'Bunder', 'Mangalore', 'https://www.google.com/maps/place/The+Gateway+Hotel+Old+Port+Road,+Mangalore/@12.8620442,74.8346469,19.08z/data=!4m5!3m4!1s0x3ba35bacf63d68fb:0xc9b324f386d5920a!8m2!3d12.862067!4d74.835147');
INSERT INTO Main_table VALUES(9886144738, 'Hotel', 'Attavar', 'Mangalore', 'https://www.google.com/maps/place/Metro+Plaza+Hotel/@12.8649357,74.8422939,17z/data=!3m1!4b1!4m5!3m4!1s0x3ba35a4c9f0a1483:0xf85bf78823661ac0!8m2!3d12.8649305!4d74.8444826');
INSERT INTO Main_table VALUES(9886144739, 'Hotel', 'Kodialbail', 'Mangalore', 'https://www.google.com/maps/place/Hotel+Deepa+Comforts/@12.8778427,74.8396611,17z/data=!3m1!4b1!4m5!3m4!1s0x3ba35a450a67d4d3:0x5abfdc19dc91ce75!8m2!3d12.8778375!4d74.8418498');
INSERT INTO Main_table VALUES(9886144740, 'Hotel', 'Balmatta', 'Mangalore', 'https://www.google.com/maps/place/Hotel+Maya+International/@12.871465,74.8538334,18z/data=!3m1!4b1!4m5!3m4!1s0x3ba35a369e89e809:0x89186ff939d44d1b!8m2!3d12.8714629!4d74.854721');
INSERT INTO Main_table VALUES(9886144741, 'Hotel', 'Tannirbavi', 'Mangalore', 'https://www.google.com/maps/place/12%C2%B052''17.2%22N+74%C2%B049''07.5%22E/@12.8714492,74.8165713,17z/data=!3m1!4b1!4m6!3m5!1s0x0:0x0!7e2!8m2!3d12.8714443!4d74.81876');
INSERT INTO Main_table VALUES(9886144742, 'Shops', 'Bunts hostel', 'Mangalore', 'https://www.google.com/maps/place/12%C2%B052''35.5%22N+74%C2%B050''53.4%22E/@12.8765422,74.8459773,17z/data=!3m1!4b1!4m6!3m5!1s0x0:0x0!7e2!8m2!3d12.8765367!4d74.8481661');
INSERT INTO Main_table VALUES(9886144743, 'Shops', 'Old kent raoad', 'Mangalore', 'https://www.google.com/maps/place/12%C2%B051''36.0%22N+74%C2%B050''25.1%22E/@12.8600074,74.8397181,18z/data=!3m1!4b1!4m6!3m5!1s0x0:0x0!7e2!8m2!3d12.8600056!4d74.8403019');
INSERT INTO Main_table VALUES(9886144744, 'Shops', 'Hampankatta', 'Mangalore', 'https://www.google.com/maps/place/City+Centre+Mall/@12.8714823,74.840489,17z/data=!3m2!4b1!5s0x3ba35a4e84df981b:0x2675c21b82dce239!4m5!3m4!1s0x3ba35a4e858a8517:0xcfa2fb1fe25e0164!8m2!3d12.8714771!4d74.8426777');
INSERT INTO Main_table VALUES(9886144745, 'Shops', 'KSR road', 'Mangalore', 'https://www.google.com/maps/place/GIRIAS+INDIA+-+MANGALORE-KSR+ROAD/@12.8695503,74.8423251,20.91z/data=!4m5!3m4!1s0x3ba35a4c3439711d:0x12d21945053b8ab3!8m2!3d12.8694741!4d74.8423514');
INSERT INTO Main_table VALUES(9886144746, 'Shops', 'Bendoore', 'Mangalore', 'https://www.google.com/maps/place/Bhima+Jewellers/@12.8743975,74.8528683,17.26z/data=!4m5!3m4!1s0x3ba35a371c58d597:0xae5c01a043f8a569!8m2!3d12.874407!4d74.854692');
INSERT INTO Main_table VALUES(9886144747, 'Shops', 'Bendoore', 'Mangalore', 'https://www.google.com/maps/place/12%C2%B052''33.2%22N+74%C2%B051''23.5%22E/@12.8758904,74.8547048,17z/data=!3m1!4b1!4m6!3m5!1s0x0:0x0!7e2!8m2!3d12.8758863!4d74.8565318');
INSERT INTO Main_table VALUES(9886144748, 'Shops', 'MG road', 'Mangalore', 'https://www.google.com/maps/place/Kalyan+Jewellers/@12.8830162,74.8373053,17z/data=!3m1!4b1!4m5!3m4!1s0x3ba35a4337be12b9:0x53083c1d87116d44!8m2!3d12.883011!4d74.839494');
INSERT INTO Main_table VALUES(9886144749, 'Shops', 'Lallbagh', 'Mangalore', 'https://www.google.com/maps/place/12%C2%B053''02.2%22N+74%C2%B050''20.0%22E/@12.8839362,74.8367043,17z/data=!3m1!4b1!4m6!3m5!1s0x0:0x0!7e2!8m2!3d12.8839314!4d74.8388932');
INSERT INTO Main_table VALUES(9886144750, 'Shops', 'Lallbagh', 'Mangalore', 'https://www.google.com/maps/place/12%C2%B053''05.8%22N+74%C2%B050''20.9%22E/@12.8849402,74.8369623,17z/data=!3m1!4b1!4m6!3m5!1s0x0:0x0!7e2!8m2!3d12.884935!4d74.8391505');
INSERT INTO Main_table VALUES(9886144751, 'Shops', 'Pandeshwar', 'Mangalore', 'https://www.google.com/maps/place/The+Forum+Fiza+Mall/@12.8575949,74.8361499,17z/data=!3m2!4b1!5s0x3ba35bae23326d4f:0xe6f4c7066f758590!4m5!3m4!1s0x3ba35bae233a2a7f:0xbfe781ac5c407f93!8m2!3d12.8575897!4d74.8383386');

-- Dummy data to test Individual UI
INSERT INTO Individual VALUES(9886144693, 'Pawan', 'Bhandarkar');
INSERT INTO Individual VALUES(9886144694, 'Nikhil','Kamath');
INSERT INTO Individual VALUES(9886144695, 'Json', 'Dsouza');
INSERT INTO Individual VALUES(9886144696, 'Raj', 'Kapoor');
INSERT INTO Individual VALUES(9886144697, 'Ganapathi', 'Sharma');
INSERT INTO Individual VALUES(9886144698, 'John', 'Walk');
INSERT INTO Individual VALUES(9886144699, 'Peter', 'Bing');
INSERT INTO Individual VALUES(9886144701, 'Roop Singam', 'Rathod');
INSERT INTO Individual VALUES(9886144702, 'Alif', 'Shah');
INSERT INTO Individual VALUES(9886144703, 'Iwan', 'Khan');
INSERT INTO Individual VALUES(9886144704, 'Ira', 'Green');
INSERT INTO Individual VALUES(9886144705, 'Jon', 'Blake');
INSERT INTO Individual VALUES(9886144706, 'Sudeep', 'Khan');
INSERT INTO Individual VALUES(9886144707, 'Chandra', 'Reddy');
INSERT INTO Individual VALUES(9886144708, 'Vivek', 'Roy');
INSERT INTO Individual VALUES(9886144709, 'June', 'Paint');
INSERT INTO Individual VALUES(9886144710, 'Jonny', 'Jain');
INSERT INTO Individual VALUES(9886144711, 'Deepika', 'Desai');

INSERT INTO Hospital VALUES(9886144712, 'KMC', 1,1,1,1);
INSERT INTO Hospital VALUES(9886144713, 'COLOMBIA', 1,1,1,1);
INSERT INTO Hospital VALUES(9886144714, 'APOLLO', 1,1,1,1);
INSERT INTO Hospital VALUES(9886144715, 'A.J HOSPITAL', 0,1,1,0);
INSERT INTO Hospital VALUES(9886144716, 'SHALBY HOSPITAL', 1,0,0,0);
INSERT INTO Hospital VALUES(9886144717, 'MANIPAL HOSPITAL', 0,1,1,1);
INSERT INTO Hospital VALUES(9886144718, 'YENEPOYA', 1,1,0,1);
INSERT INTO Hospital VALUES(9886144719, 'OMEGA', 0,1,0,0);
INSERT INTO Hospital VALUES(9886144720, 'INDIANA HOSPITAL', 1,0,1,0);
INSERT INTO Hospital VALUES(9886144721, 'S.C.S HOSPITAL', 1,1,1,1);

INSERT INTO Travel_agency VALUES(9886144722, 'SHRI KATEEL TOURS', 1,0,0,0,1);
INSERT INTO Travel_agency VALUES(9886144723, 'RACHANA TRAVELS', 0,1,1,0,1);
INSERT INTO Travel_agency VALUES(9886144724, 'RIHAN TOURS', 0,1,1,1,1);
INSERT INTO Travel_agency VALUES(9886144725, 'MANGALORE TOURS', 0,1,0,1,0);
INSERT INTO Travel_agency VALUES(9886144726, 'COZ AND KINGS', 1,1,0,0,1);	
INSERT INTO Travel_agency VALUES(9886144727, 'AMS TOURS', 1,0,1,0,0);
INSERT INTO Travel_agency VALUES(9886144728, 'GLOBE TRAVELS', 0,1,0,0,0);
INSERT INTO Travel_agency VALUES(9886144729, 'NIRMALA TRAVELS', 1,0,1,0,0);
INSERT INTO Travel_agency VALUES(9886144730, 'FLORA TRAVELS', 1,1,1,1,1);
INSERT INTO Travel_agency VALUES(9886144731, 'RIYA TOURS', 0,0,1,0,0);

INSERT INTO Hotel VALUES(9886144732, 'GINGER HOTEL', 1,0);
INSERT INTO Hotel VALUES(9886144733, 'GOLDFINCH HOTEL', 0,1);
INSERT INTO Hotel VALUES(9886144734, 'HOTEL MANGALORE', 1,0);
INSERT INTO Hotel VALUES(9886144735, 'PAPPILON', 1,1);
INSERT INTO Hotel VALUES(9886144736, 'OCEAN PERL', 0,0);
INSERT INTO Hotel VALUES(9886144737, 'THE GATEWAY', 0,1);
INSERT INTO Hotel VALUES(9886144738, 'METRO PLAZA HOTEEL', 1,1);
INSERT INTO Hotel VALUES(9886144739, 'DEEPA COMFORTS', 1,0);
INSERT INTO Hotel VALUES(9886144740, 'MAYA INTERNATIONAL', 0,0);
INSERT INTO Hotel VALUES(9886144741, 'EUROPIUM HOTEL', 1,0);

INSERT INTO Shops VALUES(9886144742, 'PAI ELECTRONICS','Electronics');
INSERT INTO Shops VALUES(9886144743, 'MORE','Grocery');
INSERT INTO Shops VALUES(9886144744, 'CITY CENTRE','Mall');
INSERT INTO Shops VALUES(9886144745, 'GIRIAS','Electronics');
INSERT INTO Shops VALUES(9886144746, 'BHIMA','Jewellery');
INSERT INTO Shops VALUES(9886144747, 'THE LAVENDER','Florist');
INSERT INTO Shops VALUES(9886144748, 'KALYAN','Jewellery');
INSERT INTO Shops VALUES(9886144749, 'AROMA','Florist');
INSERT INTO Shops VALUES(9886144750, 'FOODWORLD','Grocery');
INSERT INTO Shops VALUES(9886144751, 'FORUM','Mall');

select * from Main_table;
select * from Travel_agency;
select * from Hospital;
select * from Hotel;
select * from Shops;
select * from Individual;
select * from Works_for;
