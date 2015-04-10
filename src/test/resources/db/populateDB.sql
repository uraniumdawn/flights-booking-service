DELETE FROM booking;
DELETE FROM flights;
DELETE FROM airports;
DELETE FROM users;
ALTER SEQUENCE global_sequence RESTART WITH 1000;



INSERT INTO users (first_name, second_name, email, password)
VALUES ('Laura', 'Palmer', 'WhoKillLauraPalmer@linch.com', 'FireWalkWithMe');
INSERT INTO users (first_name, second_name, email, password) VALUES ('Dale', 'Cooper', 'coop@linch.com', 'cherrypie');
INSERT INTO users (first_name, second_name, email, password) VALUES ('Maddy', 'Ferguson', 'Sheryl@linch.com', 'Lee');
INSERT INTO users (first_name, second_name, email, password) VALUES ('Audrey', 'Horne', 'Sherilyn@linch.com', 'Fenn');

INSERT INTO user_roles (user_id, role) VALUES (1000, 'ROLE_ADMIN');
INSERT INTO user_roles (user_id, role) VALUES (1001, 'ROLE_USER');
INSERT INTO user_roles (user_id, role) VALUES (1002, 'ROLE_USER');
INSERT INTO user_roles (user_id, role) VALUES (1003, 'ROLE_USER');

INSERT INTO airports (iata_code, name, city, country)
VALUES ('ATL', 'Hartsfield-Jackson Atlanta International Airport', 'Atlanta', 'USA');
INSERT INTO airports (iata_code, name, city, country)
VALUES ('LAX', 'Los Angeles International Airport', 'Los Angeles', 'USA');
INSERT INTO airports (iata_code, name, city, country)
VALUES ('ORD', 'Chicago OHare International Airport', 'Chikago', 'USA');
INSERT INTO airports (iata_code, name, city, country)
VALUES ('YYZ', 'Toronto Pearson International Airport', 'Toronto', 'Canada');
INSERT INTO airports (iata_code, name, city, country)
VALUES ('YUL', 'Montreal-Pierre Elliott Trudeau International Airport', 'Montreal', 'Canada');
INSERT INTO airports (iata_code, name, city, country)
VALUES ('YUC', 'Calgary International Airport', 'Calgary', 'Canada');

INSERT INTO flights (point_of_departure, destination, time, price) VALUES ('YYZ', 'YUL', '2014-10-20 17:05', 125.00);
INSERT INTO flights (point_of_departure, destination, time, price) VALUES ('YUL', 'YYZ', '2014-11-14 04:17:00', 152.00);
INSERT INTO flights (point_of_departure, destination, time, price) VALUES ('YYZ', 'YUC', '2014-09-07 10:45:00', 95.00);
INSERT INTO flights (point_of_departure, destination, time, price) VALUES ('ATL', 'LAX', '2014-07-18 17:58:00', 58.00);
INSERT INTO flights (point_of_departure, destination, time, price) VALUES ('YYZ', 'ATL', '2014-06-07 15:24:00', 115.00);
INSERT INTO flights (point_of_departure, destination, time, price) VALUES ('YUC', 'ORD', '2014-05-01 03:07:00', 45.00);

INSERT INTO booking (user_id, flight_id) VALUES (1000, 1004);
INSERT INTO booking (user_id, flight_id) VALUES (1000, 1007);
INSERT INTO booking (user_id, flight_id) VALUES (1000, 1005);
INSERT INTO booking (user_id, flight_id) VALUES (1001, 1006);
INSERT INTO booking (user_id, flight_id) VALUES (1001, 1006);
INSERT INTO booking (user_id, flight_id) VALUES (1002, 1009);
INSERT INTO booking (user_id, flight_id) VALUES (1003, 1008);
INSERT INTO booking (user_id, flight_id) VALUES (1003, 1004);