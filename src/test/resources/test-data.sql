INSERT INTO state(id, code, name) values (1, 'CA', 'California');
INSERT INTO zip(id, code, city, state_id) values (1, '90001', 'Los Angeles', 1);
INSERT INTO address(id, street_1, street_2, zip_id) values (100, '4800 E Interstate 440 Road', 'APT 1234', 1);

INSERT INTO state(id, code, name) values (2, 'CO', 'Colorado');
INSERT INTO zip(id, code, city, state_id) values (2, '80210', 'Denver', 2);

INSERT INTO state(id, code, name) values (3, 'TX', 'Texas');
INSERT INTO zip(id, code, city, state_id) values (3, '75051', 'Dallas', 3);
INSERT INTO address(id, street_1, street_2, zip_id) values (200, '1234 Main Rd', null, 3);