INSERT INTO state(id, code, name) values (1, 'CA', 'California');
INSERT INTO zip(id, code, city, state_id) values (1, '90001', 'Los Angeles', 1);
INSERT INTO address(id, address_1, address_2, zip_id) values (100, '4800 E Interstate 440 Road', 'APT 1234', 1);

INSERT INTO state(id, code, name) values (2, 'CO', 'Colorado');
INSERT INTO zip(id, code, city, state_id) values (2, '80210', 'Denver', 2);