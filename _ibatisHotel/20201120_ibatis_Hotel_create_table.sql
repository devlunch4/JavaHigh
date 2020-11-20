CREATE TABLE Hotelx (

    room_no   VARCHAR2(30) NOT NULL,
    room_type   VARCHAR2(30) NOT NULL,
    guest_name    VARCHAR2(30) NOT NULL,

    CONSTRAINT pk_Hotelx PRIMARY KEY ( room_no )
);

SELECT * FROM Hotelx;

BEGIN
FOR i IN 201 .. 210 loop
INSERT INTO Hotelx  (room_no, room_type, guest_name) VALUES(i, '싱글룸', '-');
end loop; 
end;

BEGIN
FOR i IN 301 .. 310 loop
INSERT INTO Hotelx  (room_no, room_type, guest_name) VALUES(i, '더블룸', '-');
end loop; 
end;

BEGIN
FOR i IN 401 .. 410 loop
INSERT INTO Hotelx  (room_no, room_type, guest_name) VALUES(i, '스위트룸', '-');
end loop; 
end;

commit;