DELIMITER //
CREATE OR REPLACE PROCEDURE produkt_erstellen(name VARCHAR(30), preis DECIMAL(6,2))
BEGIN
    INSERT INTO Produkt(name, preis) VALUES (name, preis);
END //
DELIMITER ;

DELIMITER //
CREATE OR REPLACE PROCEDURE verkaufsstelle_erstellen(typ ENUM ('Haendler', 'Automat'), name VARCHAR(30), ort VARCHAR(50))
BEGIN
    INSERT INTO Haendler(typ, name, ort) VALUES (typ, name, ort);
END //
DELIMITER ;