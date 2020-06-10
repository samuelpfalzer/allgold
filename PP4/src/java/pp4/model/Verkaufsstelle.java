/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pp4.model;

import java.io.Serializable;
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
@Table(name = "Verkaufsstelle")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Verkaufsstelle.findAll", query = "SELECT v FROM Verkaufsstelle v"),
    @NamedQuery(name = "Verkaufsstelle.findById", query = "SELECT v FROM Verkaufsstelle v WHERE v.id = :id"),
    @NamedQuery(name = "Verkaufsstelle.findByTyp", query = "SELECT v FROM Verkaufsstelle v WHERE v.typ = :typ"),
    @NamedQuery(name = "Verkaufsstelle.findByName", query = "SELECT v FROM Verkaufsstelle v WHERE v.name = :name"),
    @NamedQuery(name = "Verkaufsstelle.findByOrt", query = "SELECT v FROM Verkaufsstelle v WHERE v.ort = :ort"),
    @NamedQuery(name = "Verkaufsstelle.findByAktiv", query = "SELECT v FROM Verkaufsstelle v WHERE v.aktiv = :aktiv")})
public class Verkaufsstelle implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "typ")
    private String typ;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "ort")
    private String ort;
    @Basic(optional = false)
    @NotNull
    @Column(name = "aktiv")
    private boolean aktiv;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "verkaufsstelleId")
    private Collection<Verkauf> verkaufCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "verkaufsstelleId")
    private Collection<Lieferung> lieferungCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "verkaufsstelleId")
    private Collection<Inventar> inventarCollection;

    public Verkaufsstelle() {
    }

    public Verkaufsstelle(Integer id) {
        this.id = id;
    }

    public Verkaufsstelle(Integer id, String typ, String name, String ort, boolean aktiv) {
        this.id = id;
        this.typ = typ;
        this.name = name;
        this.ort = ort;
        this.aktiv = aktiv;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
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
        if (!(object instanceof Verkaufsstelle)) {
            return false;
        }
        Verkaufsstelle other = (Verkaufsstelle) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pp4.model.Verkaufsstelle[ id=" + id + " ]";
    }
    
}
