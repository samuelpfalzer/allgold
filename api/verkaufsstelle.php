<?php

$con = new mysqli("allgold-db", "test", "test", "allgold") or die("Datenbankverbindung fehlgeschlagen");

switch ($_SERVER["REQUEST_METHOD"]) {
    case "GET":
        $verkaufsstellen = array();
        $res = $con->query("SELECT name FROM Verkaufsstelle;");
        while ($verkaufsstelle = $res->fetch_assoc()) {
            $verkaufsstellen[] = $verkaufsstelle["name"];
        };
        echo (json_encode($verkaufsstellen));
        break;
    case "POST":
        break;

    case "PUT":
        break;

    case "DELETE":
        break;
}
