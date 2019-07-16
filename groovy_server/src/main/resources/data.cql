USE autoshowroom;

INSERT INTO makes (id, name) VALUES (1, 'Audi');
INSERT INTO makes (id, name) VALUES (2, 'Mercedes');
INSERT INTO makes (id, name) VALUES (3, 'BMW');
INSERT INTO makes (id, name) VALUES (4, 'Toyota');
INSERT INTO makes (id, name) VALUES (5, 'Mitsubishi');
INSERT INTO makes (id, name) VALUES (6, 'GMC');
INSERT INTO makes (id, name) VALUES (7, 'Renault');
INSERT INTO makes (id, name) VALUES (8, 'Lada');

INSERT INTO models (id, name, price) VALUES (1, 'A8', 6000000);
INSERT INTO models (id, name, price) VALUES (2, 'Camry', 1500000);
INSERT INTO models (id, name, price) VALUES (3, 'Kalina', 400000);
INSERT INTO models (id, name, price) VALUES (4, 'A7', 4650000);
INSERT INTO models (id, name, price) VALUES (5, 'X6', 5000000);
INSERT INTO models (id, name, price) VALUES (6, 'X6', 5000000);
INSERT INTO models (id, name, price) VALUES (7, 'X5', 4750000);
INSERT INTO models (id, name, price) VALUES (8, 'X4', 3480000);
INSERT INTO models (id, name, price) VALUES (9, 'Vesta', 600000);
INSERT INTO models (id, name, price) VALUES (10, 'Largus', 600000);

INSERT INTO options (id, name, price) VALUES (1, 'Спортивный аэродинамический обвес', 40000);
INSERT INTO options (id, name, price) VALUES (2, 'Отделка салона кожей', 100000);
INSERT INTO options (id, name, price) VALUES (3, 'Подогрев сидений', 5000);
INSERT INTO options (id, name, price) VALUES (4, 'Обогрев лобового стекла', 2000);
INSERT INTO options (id, name, price) VALUES (5, 'Зимняя резина', 34000);

INSERT INTO clients (id, name) VALUES (1, 'Bogdan');
INSERT INTO clients (id, name) VALUES (2, 'Hector');
INSERT INTO clients (id, name) VALUES (3, 'Ivan');
INSERT INTO clients (id, name) VALUES (4, 'Timur');