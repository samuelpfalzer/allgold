/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pp4.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author samu
 */
@Entity
@Table(name = "Produkt")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Produkt.findAll", query = "SELECT p FROM Produkt p"),
    @NamedQuery(name = "Produkt.findById", query = "SELECT p FROM Produkt p WHERE p.id = :id"),
    @NamedQuery(name = "Produkt.findByName", query = "SELECT p FROM Produkt p WHERE p.name = :name"),
    @NamedQuery(name = "Produkt.findByPreis", query = "SELECT p FROM Produkt p WHERE p.preis = :preis"),
    @NamedQuery(name = "Produkt.findByBestand", query = "SELECT p FROM Produkt p WHERE p.bestand = :bestand"),
    @NamedQuery(name = "Produkt.findByAktiv", query = "SELECT p FROM Produkt p WHERE p.aktiv = :aktiv")})
public class Produkt implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "name")
    private String name;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "preis")
    private BigDecimal preis;
    @Basic(optional = false)
    @NotNull
    @Column(name = "bestand")
    private int bestand;
    @Basic(optional = false)
    @NotNull
    @Column(name = "aktiv")
    private boolean aktiv;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "produktId")
    private Collection<Verkauf> verkaufCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "produktId")
    private Collection<Lieferung> lieferungCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "produktId")
    private Collection<Inventar> inventarCollection;

    public Produkt() {
    }

    public Produkt(Integer id) {
        this.id = id;
    }

    public Produkt(Integer id, String name, BigDecimal preis, int bestand, boolean aktiv) {
        this.id = id;
        this.name = name;
        this.preis = preis;
        this.bestand = bestand;
        this.aktiv = aktiv;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPreis() {
        return preis;
    }

    public void setPreis(BigDecimal preis) {
        this.preis = preis;
    }

    public int getBestand() {
        return bestand;
    }

    public void setBestand(int bestand) {
        this.bestand = bestand;
    }

    public boolean getAktiv() {
        return aktiv;
    }

    public void setAktiv(boolean aktiv) {
        this.aktiv = aktiv;
    }

    @XmlTransient
    public Collection<Verkauf> getVerkaufCollection() {
        return verkaufCollection;
    }

    public void setVerkaufCollection(Collection<Verkauf> verkaufCollection) {
        this.verkaufCollection = verkaufCollection;
    }

    @XmlTransient
    public Collection<Lieferung> getLieferungCollection() {
        return lieferungCollection;
    }

    public void setLieferungCollection(Collection<Lieferung> lieferungCollection) {
        this.lieferungCollection = lieferungCollection;
    }

    @XmlTransient
    public Collection<Inventar> getInventarCollection() {
        return inventarCollection;
    }

    public void setInventarCollection(Collection<Inventar> inventarCollection) {
        this.inventarCollection = inventarCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Produkt)) {
            return false;
        }
        Produkt other = (Produkt) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pp4.model.Produkt[ id=" + id + " ]";
    }
    
}
