INSERT INTO CLIENT (first_name,second_name, last_name, serial, Pnumber)
VALUES
  	('Lokesh', 'Gupta', 'Mirana','234235','6346'),
  	('John', 'Doe', 'Valak','282345','76266');

INSERT INTO BILL (Balance, Bank)
VALUES
  	(10000, 'vtb'),
  	(500, 'sber'),
  	(12131, 'cokoke');

INSERT INTO CARD (CNumber, Date_of, Pin, CSV, client_id, bill_id)
VALUES
  	('0234234', '22.11.12', '0000', '252', 2, 1),
  	('3235235', '22.11.12', '2222', '252', 2, 2),
  	('3235235', '22.11.12', '2222', '252', 1, 3),
  	('3235235', '22.11.12', '2222', '252', 1, 2),
  	('3235235', '22.11.12', '2222', '252', 1, 2),
  	('3252553', '10.05.24', '8254', '734', 1, 1);

INSERT INTO TERMINAL (Balance, Filial_owner)
VALUES
  	(32623623, 'vtb'),
  	(234, 'sber'),
  	(112632, 'cokoke');

INSERT INTO OPERATION (Type, Amount, terminal_id, account_from_id, account_to_id)
VALUES
  	('Trans', 500,  1, '1', '2'),
  	('Trans', 1500, 1, '2', '3'),
  	('ADDIn', 1500, 2, '3', '1'),
  	('Trans', 500,  3, '1', '3');

-- Users
-- password in plaintext: "password"
INSERT INTO USER (user_id, password, email, username, name, last_name, active)
VALUES
  (1, '$2a$06$OAPObzhRdRXBCbk7Hj/ot.jY3zPwR8n7/mfLtKIgTzdJa4.6TwsIm', 'user@mail.com', 'user', 'Name', 'Surname', 1);
-- password in plaintext: "password"
INSERT INTO USER (user_id, password, email, username, name, last_name, active)
VALUES
  (2, '$2a$06$OAPObzhRdRXBCbk7Hj/ot.jY3zPwR8n7/mfLtKIgTzdJa4.6TwsIm', 'johndoe@gmail.com', 'johndoe', 'John', 'Doe', 1);
-- password in plaintext: "password"
INSERT INTO USER (user_id, password, email, username, name, last_name, active)
VALUES (3, '$2a$06$OAPObzhRdRXBCbk7Hj/ot.jY3zPwR8n7/mfLtKIgTzdJa4.6TwsIm', 'ana@mail.com', 'ana', 'Ana', 'Surname', 1);

-- Roles
INSERT INTO ROLE (role_id, role)
VALUES (1, 'ROLE_ADMIN');
INSERT INTO ROLE (role_id, role)
VALUES (2, 'ROLE_USER');

-- User Roles
INSERT INTO USER_ROLE (user_role_id, user_id, role_id)
VALUES (1, 1, 1);
INSERT INTO USER_ROLE (user_role_id, user_id, role_id)
VALUES (2, 2, 2);
INSERT INTO USER_ROLE (user_role_id, user_id, role_id)
VALUES (3, 3, 2);
