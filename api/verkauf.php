<?php

$con = new mysqli("allgold-db", "test", "test", "allgold") or die("Datenbankverbindung fehlgeschlagen");

switch ($_SERVER["REQUEST_METHOD"]) {
    case "GET":
        break;

    case "POST":
        /* Format: {verkaufsstelle: "...", posten: [{produkt: id, menge: 2}, ...]}} */
        $payload = json_decode(file_get_contents("php://input"), true);
        if (!(isset($payload["posten"]) and isset($payload["verkaufsstelle"]))) {
            http_response_code(400);
            break;
        } else {
            $fehler = false;
            $posten = $payload["posten"];
            $verkaufsstelle = $payload["verkaufsstelle"];
            $verkaufsstelle_id = 0;

            $res = $con->query("SELECT id FROM Verkaufsstelle WHERE name = '$verkaufsstelle';");
            if ($res->num_rows > 0) {
                $verkaufsstelle_id = $res->fetch_assoc()["id"];
            } else {
                http_response_code(400);
                break;
            }
            $res->free();

            foreach ($posten as $p) {
                $produkt_id = $p["id"];

                /* verfuegbare Menge des Produkts an der Verkaufsstelle abfragen */
                $res = $con->query("SELECT vorrat FROM Inventar WHERE verkaufsstelle_id = $verkaufsstelle_id AND produkt_id = $produkt_id;");
                if ($res->num_rows > 0) {
                    $vorrat = intval($res->fetch_assoc()["vorrat"]);
                    if ($p["menge"] > $vorrat) {
                        $fehler = true;
                    }
                } else {
                    $fehler = true;
                }
            }

            if ($fehler) {
                http_response_code(400);
                break;
            }

            foreach ($posten as $p) {
                /* Verkauf erstellen */
                $menge = $p["menge"];
                $id = $p["id"];
                $con->query("INSERT INTO Verkauf(verkaufsstelle_id, produkt_id, menge) VALUES ($verkaufsstelle_id, $id, $menge);");

                /* Inventar updaten */
                $con->query("UPDATE Inventar SET vorrat = vorrat - $menge WHERE verkaufsstelle_id = $verkaufsstelle_id AND produkt_id = $id");
            }

            http_response_code(200);
            echo (json_encode('{pimmel: "ok"'));
        }
        break;

    case "PUT":
        break;

    case "DELETE":
        break;
}
