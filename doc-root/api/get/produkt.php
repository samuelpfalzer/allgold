<?php

if ($_SERVER["REQUEST_METHOD"] != "GET") {
    die();
}

require_once('../../db/Produkt.php');

$con = new mysqli("mariadb", "test", "test", "testdb") or die("DB-Verbindung fehlgeschlagen");
$produkt = new Produkt($con);

if (isset($_GET['all'])) {
    echo($produkt->getAll());
} elseif (isset($_GET['name'])) {
    if ($produkt->laden($_GET['name'])) {
        echo($produkt->json());
    }
}

?>