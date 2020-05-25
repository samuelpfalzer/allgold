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

        echo (json_encode($lieferplan_out));
        break;

        /* Erfolgte Lieferung melden */
    case "POST":
        break;
}
