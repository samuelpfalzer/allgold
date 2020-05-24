<?php

$con = new mysqli("allgold-db", "test", "test", "allgold") or die("Datenbankverbindung fehlgeschlagen");

switch ($_SERVER["REQUEST_METHOD"]) {
    case "GET":
        break;

    case "POST":
        if (isset($_POST["json"])) {
            $fehler = false;
            $verkauf = json_decode($_POST["json"], true);
            $verkaufsstelle = $verkauf["verkaufsstelle"];
            $verkaufsstelle_id = 0;
            $produkt_ids = array();
            $produkt_vorratsmengen = array();
            $produkt_mengen = array();

            $res = $con->query("SELECT id FROM Verkaufsstelle WHERE name = '$verkaufsstelle';");
            if ($res->num_rows > 0) {
                $verkaufsstelle_id = $res->fetch_assoc()["id"];
            } else {
                http_response_code(400);
                break;
            }
            $res->free();

            $produkte = $verkauf["produkte"];
            foreach ($produkte as $produkt) {
                foreach ($produkt as $name => $menge) {
                    /* id des Produkts abfragen */
                    $res =  $con->query("SELECT id FROM Produkt WHERE name = '$name';");
                    if ($res->num_rows > 0) {
                        $produkt_ids[$name] = intval($res->fetch_assoc()["id"]);
                        $produkt_id = $produkt_ids[$name];
                    } else {
                        $fehler = true;
                    }
                    $res->free();

                    /* verfuegbare Menge des Produkts an der Verkaufsstelle abfragen */
                    $res = $con->query("SELECT vorrat FROM Inventar WHERE verkaufsstelle_id = $verkaufsstelle_id AND produkt_id = $produkt_id;");
                    if ($res->num_rows > 0) {
                        $produkt_vorratsmengen[$name] = intval($res->fetch_assoc()["vorrat"]);
                        if ($menge > $produkt_vorratsmengen[$name]) {
                            $fehler = true;
                        }
                        $produkt_mengen[$name] = $menge;
                    } else {
                        $fehler = true;
                    }
                }
            }

            if ($fehler) {
                http_response_code(400);
                break;
            }

            foreach ($produkt_ids as $name => $id) {
                /* Verkauf erstellen */
                $menge = $produkt_mengen[$name];
                $con->query("INSERT INTO Verkauf(verkaufsstelle_id, produkt_id, menge) VALUES ($verkaufsstelle_id, $id, $menge);");

                /* Inventar updaten */
                $con->query("UPDATE Inventar SET vorrat = vorrat - $menge WHERE verkaufsstelle_id = $verkaufsstelle_id AND produkt_id = $id");
            }

            http_response_code(200);
        } else {
            http_response_code(400);
        }
        break;

    case "PUT":
        break;

    case "DELETE":
        break;
}
