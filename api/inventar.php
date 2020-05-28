<?php

$con = new mysqli("allgold-db", "test", "test", "allgold") or die("Datenbankverbindung fehlgeschlagen");

switch ($_SERVER["REQUEST_METHOD"]) {
    case "GET":
        /* Inventarinfo zu einem Produkt an einer Verkaufsstelle */
        if (isset($_GET["verkaufsstelle"]) and isset($_GET["produkt"])) {
            $verkaufsstelle = $_GET["verkaufsstelle"];
            $produkt = $_GET["produkt"];
            $res = $con->query("SELECT prod.name AS produkt, vorrat, bedarf FROM (SELECT produkt_id, vorrat, bedarf FROM Inventar WHERE verkaufsstelle_id = (SELECT id FROM Verkaufsstelle WHERE name='$verkaufsstelle') AND produkt_id = (SELECT id FROM Produkt WHERE name='$produkt')) AS inv, (SELECT * FROM Produkt) as prod WHERE prod.id = inv.produkt_id;");

            if ($res->num_rows > 0) {
                $inventar_element = $res->fetch_assoc();
                http_response_code(200);
                echo (json_encode($inventar_element));
            } else {
                http_response_code(404);
            }
        }

        /* Inventarinfo zu allen Produkten an einer Verkaufsstelle */ else if (isset($_GET["verkaufsstelle"])) {
            $verkaufsstelle = $_GET["verkaufsstelle"];
            $res = $con->query("SELECT prod.id AS artnr, prod.name AS produkt, prod.preis AS preis, vorrat, bedarf FROM (SELECT produkt_id, vorrat, bedarf FROM Inventar WHERE verkaufsstelle_id = (SELECT id FROM Verkaufsstelle WHERE name='$verkaufsstelle')) AS inv, (SELECT * FROM Produkt) as prod WHERE prod.id = inv.produkt_id;");

            if ($res->num_rows > 0) {
                $inventar = array();
                while ($inventar_element = $res->fetch_assoc()) {
                    $inventar[] = $inventar_element;
                }
                http_response_code(200);
                echo (json_encode($inventar));
            } else {
                http_response_code(404);
            }
        }

    case "POST":
        break;

    case "PUT":
        break;

    case "DELETE":
        break;
}
