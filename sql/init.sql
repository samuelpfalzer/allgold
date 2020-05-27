DROP TABLE IF EXISTS Lieferung;
DROP TABLE IF EXISTS Verkauf;
DROP TABLE IF EXISTS Inventar;
DROP TABLE IF EXISTS Lieferant;
DROP TABLE IF EXISTS Verkaufsstelle;
DROP TABLE IF EXISTS Produkt;


CREATE TABLE Produkt (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) UNIQUE NOT NULL,
    preis DECIMAL(6,2) NOT NULL,
    bestand INTEGER NOT NULL DEFAULT 0,
    aktiv BOOLEAN NOT NULL DEFAULT TRUE
);


CREATE TABLE Verkaufsstelle (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    typ ENUM ('Haendler', 'Automat') NOT NULL,
    name VARCHAR(100) UNIQUE NOT NULL,
    ort VARCHAR(100) UNIQUE NOT NULL,
    aktiv BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE Lieferant (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    aktiv BOOLEAN NOT NULL DEFAULT TRUE
);


CREATE TABLE Inventar (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    verkaufsstelle_id INTEGER NOT NULL,
    produkt_id INTEGER NOT NULL,
    vorrat INTEGER NOT NULL,
    bedarf INTEGER NOT NULL,

    UNIQUE (verkaufsstelle_id, produkt_id),
    FOREIGN KEY (verkaufsstelle_id) REFERENCES Verkaufsstelle(id),
    FOREIGN KEY (produkt_id) REFERENCES Produkt(id)
);


CREATE TABLE Verkauf (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    verkaufsstelle_id INTEGER NOT NULL,
    produkt_id INTEGER NOT NULL,
    menge INTEGER NOT NULL,
    zeitpunkt TIMESTAMP NOT NULL,

    FOREIGN KEY (verkaufsstelle_id) REFERENCES Verkaufsstelle(id),
    FOREIGN KEY (produkt_id) REFERENCES Produkt(id)
);


CREATE TABLE Lieferung (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    verkaufsstelle_id INTEGER NOT NULL,
    produkt_id INTEGER NOT NULL,
    menge INTEGER NOT NULL,
    lieferant INTEGER NOT NULL,

    FOREIGN KEY (verkaufsstelle_id) REFERENCES Verkaufsstelle(id),
    FOREIGN KEY (produkt_id) REFERENCES Produkt(id),
    FOREIGN KEY (lieferant) REFERENCES Lieferant(id)
);