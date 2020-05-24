INSERT INTO Produkt(name, preis) VALUES 
('Milch', 1.00), ('Naturjoghurt', 1.20), ('Quark', 0.80), ('Sahne', 0.65), ('Emmentaler', 1.30), ('Erdbeerkaese', 1.37);

INSERT INTO Verkaufsstelle(typ, name, ort) VALUES
('Haendler', 'Peters Kaeseecke', 'Ziegengasse 3'),
('Automat', 'Automat 001', 'Milchstrasse 23'),
('Haendler', 'Molkereiprodukte Mueller', 'Malerweg 1'),
('Automat', 'Automat 002', 'Hauptstrasse 108'),
('Haendler', 'Herr Fahris fahrbarer Quarkverkauf', 'mobil');

INSERT INTO Lieferant(name) VALUES
('Peter Pointer'), ('Big Dick Kadim'), ('Herr Fahri');

INSERT INTO Inventar(verkaufsstelle_id, produkt_id, vorrat, bedarf) VALUES 
((SELECT id FROM Verkaufsstelle WHERE name = 'Peters Kaeseecke'), (SELECT id FROM Produkt WHERE name = 'Milch'), 0, 5),
((SELECT id FROM Verkaufsstelle WHERE name = 'Peters Kaeseecke'), (SELECT id FROM Produkt WHERE name = 'Naturjoghurt'), 2, 5),
((SELECT id FROM Verkaufsstelle WHERE name = 'Automat 001'), (SELECT id FROM Produkt WHERE name = 'Milch'), 3, 3),
((SELECT id FROM Verkaufsstelle WHERE name = 'Automat 001'), (SELECT id FROM Produkt WHERE name = 'Quark'), 3, 7),
((SELECT id FROM Verkaufsstelle WHERE name = 'Automat 001'), (SELECT id FROM Produkt WHERE name = 'Sahne'), 0, 5),
((SELECT id FROM Verkaufsstelle WHERE name = 'Molkereiprodukte Mueller'), (SELECT id FROM Produkt WHERE name = 'Milch'), 0, 5),
((SELECT id FROM Verkaufsstelle WHERE name = 'Automat 002'), (SELECT id FROM Produkt WHERE name = 'Milch'), 1,8),
((SELECT id FROM Verkaufsstelle WHERE name = 'Automat 002'), (SELECT id FROM Produkt WHERE name = 'Emmentaler'), 0, 5),
((SELECT id FROM Verkaufsstelle WHERE name = 'Herr Fahris fahrbarer Quarkverkauf'), (SELECT id FROM Produkt WHERE name = 'Emmentaler'), 4, 4),
((SELECT id FROM Verkaufsstelle WHERE name = 'Herr Fahris fahrbarer Quarkverkauf'), (SELECT id FROM Produkt WHERE name = 'Milch'), 0, 5),
((SELECT id FROM Verkaufsstelle WHERE name = 'Herr Fahris fahrbarer Quarkverkauf'), (SELECT id FROM Produkt WHERE name = 'Erdbeerkaese'), 0, 5);