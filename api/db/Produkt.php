<?php

class Produkt {
    private $name;
    private $preis;
    private $geladen = FALSE;
    private $con;

    public function __construct(mysqli $con) {
        $this->con = $con;
    }

    public function json() {
        if ($this->geladen) {
            return json_encode(array("Name" => $this->name, "Preis" => $this->preis));
        }
        return FALSE;
    }

    public function laden($name) {
        if ($this->geladen) {
            return FALSE;
        }
        $res = $this->con->query("SELECT * FROM Produkt WHERE name='$name'");
        if (!$res) {
            return False;
        } else {
            $r = $res->fetch_assoc();
            $this->name = $r["name"];
            $this->preis = $r["preis"];
            $this->geladen = TRUE;
            return True;
        }
    }

    public function erstellen($name, $preis) {
        if ($this->geladen) {
            return FALSE;
        }
        $res = $this->con->query("SELECT * FROM Produkt WHERE name='$name'");
        if ($res) {
            if ($res->num_rows == 0) {
                $this->name = $name;
                $this->preis = $preis;
                $res2 = $this->con->query("INSERT INTO Produkt(name,preis) VALUES ('" . $this->name . "', " . $this->preis . ")");
                if ($res2) {
                    $this->geladen = TRUE;
                    return TRUE;
                }
            }
        }
        return FALSE;
    }

    public function speichern() {
        if ($this->geladen) {
            $res = $this->con->query("UPDATE Produkt SET preis=$this->preis WHERE name='$this->name'");
            if ($res) {
                return TRUE;
            }
        }
        return FALSE;
    }

    public function setPreis($preis) {
        if ($this->geladen) {
            $this->preis = $preis;
            return TRUE;
        } else {
            return FALSE;
        }
    }

    public function getAll() {
        $res = $this->con->query("SELECT * FROM Produkt");
        $alle_produkte = array();
        while($r = $res->fetch_assoc()) {
            array_push($alle_produkte, array(
                "Name" => $r["name"],
                "Preis" => (float)$r["preis"]
            ));
        }
        return json_encode(array("Produkte" => $alle_produkte));
    }
}

?>