DELETE FROM booking;
DELETE FROM flights;
DELETE FROM airports;
DELETE FROM users;
ALTER SEQUENCE global_sequence RESTART WITH 1000;

INSERT INTO users (first_name, second_name, email, password)
VALUES ('Laura', 'Palmer', 'WhoKillLauraPalmer', 'FireWalkWithMe');
INSERT INTO users (first_name, second_name, email, password) VALUES ('Dale', 'Cooper', 'coop@linch.com', 'cherry pie');
INSERT INTO users (first_name, second_name, email, password) VALUES ('Maddy', 'Ferguson', 'Sheryl@linch.com', 'Lee');
INSERT INTO users (first_name, second_name, email, password) VALUES ('Audrey', 'Horne', 'Sherilyn@linch.com', 'Fenn');

SELECT * FROM users;

INSERT INTO user_roles (user_id, role) VALUES (1000, 'ROLE_ADMIN');
INSERT INTO user_roles (user_id, role) VALUES (1001, 'ROLE_USER');
INSERT INTO user_roles (user_id, role) VALUES (1002, 'ROLE_USER');
INSERT INTO user_roles (user_id, role) VALUES (1003, 'ROLE_USER');

INSERT INTO airports (IATA_code, name, city, country)
VALUES ('ATL', 'Hartsfield-Jackson Atlanta International Airport', 'Atlanta', 'USA');
INSERT INTO airports (IATA_code, name, city, country)
VALUES ('LAX', 'Los Angeles International Airport', 'Los Angeles', 'USA');
INSERT INTO airports (IATA_code, name, city, country)
VALUES ('ORD', 'Chicago OHare International Airport', 'Chikago', 'USA');
INSERT INTO airports (IATA_code, name, city, country)
VALUES ('YYZ', 'Toronto Pearson International Airport', 'Toronto', 'Canada');
INSERT INTO airports (IATA_code, name, city, country)
VALUES ('YUL', 'Montreal-Pierre Elliott Trudeau International Airport', 'Montreal', 'Canada');
INSERT INTO airports (IATA_code, name, city, country)
VALUES ('YUC', 'Calgary International Airport', 'Calgary', 'Canada');

INSERT INTO flights (point_of_departure, destination, time, prise) VALUES ('YYZ', 'YUL', '2014-10-20 17:05', 125.00);
INSERT INTO flights (point_of_departure, destination, time, prise) VALUES ('YUL', 'YYZ', '2014-11-14 04:17:00', 152.00);
INSERT INTO flights (point_of_departure, destination, time, prise) VALUES ('YYZ', 'YUC', '2014-09-07 10:45:00', 95.00);
INSERT INTO flights (point_of_departure, destination, time, prise) VALUES ('ATL', 'LAX', '2014-07-18 17:58:00', 58.00);
INSERT INTO flights (point_of_departure, destination, time, prise) VALUES ('YYZ', 'ATL', '2014-06-07 15:24:00', 115.00);
INSERT INTO flights (point_of_departure, destination, time, prise) VALUES ('YUC', 'ORD', '2014-05-01 03:07:00', 45.00);

INSERT INTO booking (user_id, flight_id) VALUES (1000, 1004);
INSERT INTO booking (user_id, flight_id) VALUES (1000, 1007);
INSERT INTO booking (user_id, flight_id) VALUES (1000, 1005);
INSERT INTO booking (user_id, flight_id) VALUES (1001, 1006);
INSERT INTO booking (user_id, flight_id) VALUES (1001, 1006);
INSERT INTO booking (user_id, flight_id) VALUES (1002, 1009);
INSERT INTO booking (user_id, flight_id) VALUES (1003, 1008);
INSERT INTO booking (user_id, flight_id) VALUES (1003, 1004);

SELECT * FROM users;

SELECT
  a1.name AS point_name,
  a2.name AS dest_name,
  f.time
FROM flights AS f
  LEFT JOIN airports AS a1 ON a1.IATA_code = f.point_of_departure
  LEFT JOIN airports AS a2 ON a2.IATA_code = f.destination
ORDER BY f.point_of_departure ASC, f.destination ASC;

SELECT
  users.first_name,
  users.second_name,
  flights.time,
  point_of_departure.name,
  destination.name
FROM booking
  JOIN users ON users.id = booking.user_id
  JOIN flights ON flights.id = booking.flight_id
  JOIN airports AS point_of_departure ON point_of_departure.IATA_code = flights.point_of_departure
  JOIN airports AS destination ON destination.IATA_code = flights.destination;