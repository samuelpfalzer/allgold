DROP TABLE IF EXISTS Inventar;
DROP TABLE IF EXISTS Verkaufsstelle;
DROP TABLE IF EXISTS Produkt;

CREATE TABLE Produkt (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(30) UNIQUE NOT NULL,
    preis DECIMAL(6,2) NOT NULL
);

CREATE TABLE Verkaufsstelle (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    typ ENUM ('Haendler', 'Automat') NOT NULL,
    name VARCHAR(30) UNIQUE NOT NULL,
    ort VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE Inventar (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    verkaufsstelle_id INTEGER NOT NULL,
    produkt_id INTEGER NOT NULL,
    menge INTEGER NOT NULL,
    FOREIGN KEY (verkaufsstelle_id) REFERENCES Verkaufsstelle(id),
    FOREIGN KEY (produkt_id) REFERENCES Produkt(id)
);
