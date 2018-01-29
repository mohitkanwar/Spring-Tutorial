drop table BOOKINGS if exists;
create table BOOKINGS(ID serial, FIRST_NAME varchar(5) NOT NULL, BOOKING_TYPE varchar(10) NOT NULL);