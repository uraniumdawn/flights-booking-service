DROP TABLE IF EXISTS booking;
DROP TABLE IF EXISTS flights;
DROP TABLE IF EXISTS airports;
DROP TABLE IF EXISTS user_Roles;
DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS global_sequence;

CREATE SEQUENCE global_sequence START 1000;

CREATE TABLE users (
  id           INTEGER PRIMARY KEY DEFAULT nextval('GLOBAL_SEQUENCE'),
  first_name   VARCHAR(20)        NOT NULL,
  second_name  VARCHAR(20)        NOT NULL,
  login        VARCHAR(20) UNIQUE NOT NULL,
  password     VARCHAR(20) UNIQUE NOT NULL,
  registration TIMESTAMP          DEFAULT now()
);

CREATE TABLE user_roles (
  user_id INTEGER NOT NULL REFERENCES users (id) ON DELETE CASCADE,
  role    VARCHAR,
  CONSTRAINT user_roles_idx UNIQUE (user_id, role)
);

CREATE TABLE airports (
  IATA_code VARCHAR(3)  PRIMARY KEY,
  name      VARCHAR(60) UNIQUE NOT NULL,
  city      VARCHAR(20),
  country   VARCHAR(10)
);

CREATE TABLE flights (
  id                 INT PRIMARY KEY DEFAULT nextval('GLOBAL_SEQUENCE'),
  point_of_departure VARCHAR(3)    NOT NULL REFERENCES airports (IATA_code) ON DELETE CASCADE,
  destination        VARCHAR(3)    NOT NULL REFERENCES airports (IATA_code) ON DELETE CASCADE,
  time               TIMESTAMP     NOT NULL,
  prise              DECIMAL(7, 2) NOT NULL,
  CHECK (point_of_departure <> flights.destination)
);

CREATE TABLE booking (
  id        BIGINT PRIMARY KEY DEFAULT nextval('GLOBAL_SEQUENCE'),
  user_id   INT NOT NULL REFERENCES users (id),
  flight_id INT NOT NULL REFERENCES flights (id)
);