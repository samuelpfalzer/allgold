<?php

if ($_SERVER["REQUEST_METHOD"] != "POST") {
    die();
}

require_once('../../db/Produkt.php');

$con = new mysqli("mariadb", "test", "test", "testdb") or die("DB-Verbindung fehlgeschlagen");
$produkt = new Produkt($con);

if (isset($_POST['name']) and isset($_POST['preis'])) {
    $produkt->erstellen($_POST['name'], floatval($_POST['preis']));
    if ($produkt->speichern()) {
        http_response_code(201);
    } else {
        http_response_code(400);
    }
}

?>