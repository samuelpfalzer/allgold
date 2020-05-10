<?php
echo "PHP Test 2<br/>";

$mysqli = new mysqli('mdb', 'test', 'test', 'testdb');

if ($mysqli->connect_errno) {
    echo("cant connect to mariadb");
} else {
    $sql = "select * from test";
    if (!$result = $mysqli->query($sql)) {
        echo("cannot query mysql");
    } else {
        echo("<ul>");
        while ($res = $result->fetch_assoc()) {
            $x = $res['test'];
            echo("<li>$x</li>");
        }
        echo("</ul>");
    }
}

?>
