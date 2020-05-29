<?php

$con = new mysqli("allgold-db", "test", "test", "allgold") or die("Datenbankverbindung fehlgeschlagen");

switch ($_SERVER["REQUEST_METHOD"]) {
    case "GET":
        if (isset($_GET["name"])) {
            $name = $_GET["name"];
            $res = $con->query("SELECT * FROM Produkt WHERE name='$name';");
            if ($res->num_rows > 0) {
                $produkt = $res->fetch_assoc();
                http_response_code(200);
                echo (json_encode($produkt));
            } else {
                http_response_code(404);
            }
        } else {
            $res = $con->query("SELECT * FROM Produkt;");
            if ($res->num_rows > 0) {
                $produkte = array();
                while ($produkt = $res->fetch_assoc()) {
                    $produkte[] = $produkt;
                }
                http_response_code(200);
                echo (json_encode($produkte));
            } else {
                http_response_code(404);
            }
        }
        break;

    case "POST":
        $payload = json_decode(file_get_contents("php://input"), true);
        if (!(isset($payload["name"]) and isset($payload["preis"]))) {
            http_response_code(400);
            break;
        } else {
            $name = $payload["name"]; // TODO: validate
            $preis = $payload["preis"]; // TODO: validate
            $res = $con->query("INSERT INTO Produkt(name, preis) VALUES('$name', $preis);");

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
