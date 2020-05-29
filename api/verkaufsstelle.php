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
        $payload = json_decode(file_get_contents("php://input"), true);
        if (!(isset($payload["typ"]) and isset($payload["name"]) and isset($payload["ort"]))) {
            http_response_code(400);
            break;
        } else {
            $typ = $payload["typ"]; // TODO: validate
            $name = $payload["name"]; // TODO: validate
            $ort = $payload["ort"]; // TODO: validate
            $res = $con->query("INSERT INTO Verkaufsstelle(typ, name, ort) VALUES('$typ', '$name', '$ort');");

            if ($res) {
                http_response_code(201);
            } else {
                http_response_code(409);
            }
        }
        break;

    case "PUT":
        break;

    case "DELETE":
        break;
}
