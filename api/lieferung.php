<?php

$con = new mysqli("allgold-db", "test", "test", "allgold") or die("Datenbankverbindung fehlgeschlagen");

switch ($_SERVER["REQUEST_METHOD"]) {
        /* Lieferplan fuer Lieferanten erstellen */
    case "GET":
        $lieferplan = array();
        $res = $con->query("SELECT verkaufsstelle.name AS name, produkt.name AS produkt, ort, (bedarf - vorrat) AS menge FROM Inventar, (SELECT id, name FROM Produkt WHERE aktiv = true) AS produkt, (SELECT id, name, ort FROM Verkaufsstelle WHERE aktiv = true) AS verkaufsstelle WHERE verkaufsstelle_id = verkaufsstelle.id AND produkt_id = produkt.id AND (bedarf - vorrat) > 0;");
        while ($eintrag = $res->fetch_assoc()) {
            if (!isset($lieferplan[$eintrag["name"]])) {
                $lieferplan[$eintrag["name"]] = array();
                $lieferplan[$eintrag["name"]]["name"] = $eintrag["name"];
                $lieferplan[$eintrag["name"]]["ort"] = $eintrag["ort"];
                $lieferplan[$eintrag["name"]]["posten"] = array();
            }
            $lieferplan[$eintrag["name"]]["posten"][] = array("produkt" => $eintrag["produkt"], "menge" => $eintrag["menge"]);
        }

        $lieferplan_out = array();

        /* remove the redundant name for easier client side processing */
        foreach ($lieferplan as $key => $val) {
            $lieferplan_out[] = $val;
        }

        http_response_code(200);
        echo (json_encode($lieferplan_out));
        break;

        /* Erfolgte Lieferung melden; geht davon aus, dass das gesamte Inventar eines Händlers aufgefüllt wurde */
    case "POST":
        $payload = json_decode(file_get_contents("php://input"), true);
        if (!(isset($payload["posten"]) and isset($payload["verkaufsstelle"]))) {
            http_response_code(400);
        }

        $response = array();
        foreach ($payload["posten"] as $posten) {
            $verkaufsstelle = $payload["verkaufsstelle"];
            $produkt = $posten["produkt"];
            $menge = $posten["menge"];

            $res = $con->query("INSERT INTO Lieferung(verkaufsstelle_id, produkt_id, menge, lieferant) VALUES ((SELECT id FROM Verkaufsstelle WHERE name='$verkaufsstelle'),(SELECT id FROM Produkt WHERE name='$produkt'),$menge,1);");

            $res = $con->query("UPDATE Inventar SET vorrat = vorrat + $menge WHERE verkaufsstelle_id = (SELECT id FROM Verkaufsstelle WHERE name='$verkaufsstelle') AND produkt_id = (SELECT id FROM Produkt WHERE name='$produkt');");

            $response[] = "UPDATE Inventar SET vorrat = vorrat + $menge WHERE verkaufsstelle_id = (SELECT id FROM Verkaufsstelle WHERE name='$verkaufsstelle') AND produkt_id = (SELECT id FROM Produkt WHERE name='$produkt');";
        }

        http_response_code(200);
        echo (json_encode($response));
        break;
}
