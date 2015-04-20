DROP TABLE IF EXISTS booking;
DROP TABLE IF EXISTS flights;
DROP TABLE IF EXISTS airports;
DROP TABLE IF EXISTS user_Roles;
DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS global_sequence;


CREATE SEQUENCE global_sequence START 1000;

CREATE TABLE users (
  id           INTEGER PRIMARY KEY DEFAULT nextval('GLOBAL_SEQUENCE'),
  first_name   VARCHAR(50)        NOT NULL,
  second_name  VARCHAR(50)        NOT NULL,
  email        VARCHAR(50) UNIQUE NOT NULL,
  password     VARCHAR(50)        NOT NULL,
  registration TIMESTAMP           DEFAULT now(),
  enabled      BOOLEAN             DEFAULT TRUE
);

CREATE TABLE user_roles (
  user_id INTEGER UNIQUE NOT NULL REFERENCES users (id) ON DELETE CASCADE,
  role    VARCHAR
);

CREATE TABLE airports (
  iata_code VARCHAR(3) PRIMARY KEY,
  name      VARCHAR(100) UNIQUE NOT NULL,
  city      VARCHAR(20),
  country   VARCHAR(10)
);

CREATE TABLE flights (
  id                 INTEGER PRIMARY KEY DEFAULT nextval('GLOBAL_SEQUENCE'),
  point_of_departure VARCHAR(3)    NOT NULL REFERENCES airports (IATA_code) ON DELETE CASCADE,
  destination        VARCHAR(3)    NOT NULL REFERENCES airports (IATA_code) ON DELETE CASCADE,
  time               TIMESTAMP     NOT NULL,
  price              DECIMAL(7, 2) NOT NULL,
  CHECK (point_of_departure <> flights.destination)
);

CREATE TABLE booking (
  id        BIGINT PRIMARY KEY DEFAULT nextval('GLOBAL_SEQUENCE'),
  user_id   INTEGER NOT NULL REFERENCES users (id),
  flight_id INTEGER NOT NULL REFERENCES flights (id)
);