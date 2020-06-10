/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pp4.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author samu
 */
@Entity
@Table(name = "Lieferung")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Lieferung.findAll", query = "SELECT l FROM Lieferung l"),
    @NamedQuery(name = "Lieferung.findById", query = "SELECT l FROM Lieferung l WHERE l.id = :id"),
    @NamedQuery(name = "Lieferung.findByMenge", query = "SELECT l FROM Lieferung l WHERE l.menge = :menge")})
public class Lieferung implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "menge")
    private int menge;
    @JoinColumn(name = "verkaufsstelle_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Verkaufsstelle verkaufsstelleId;
    @JoinColumn(name = "produkt_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Produkt produktId;
    @JoinColumn(name = "lieferant", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Lieferant lieferant;

    public Lieferung() {
    }

    public Lieferung(Integer id) {
        this.id = id;
    }

    public Lieferung(Integer id, int menge) {
        this.id = id;
        this.menge = menge;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getMenge() {
        return menge;
    }

    public void setMenge(int menge) {
        this.menge = menge;
    }

    public Verkaufsstelle getVerkaufsstelleId() {
        return verkaufsstelleId;
    }

    public void setVerkaufsstelleId(Verkaufsstelle verkaufsstelleId) {
        this.verkaufsstelleId = verkaufsstelleId;
    }

    public Produkt getProduktId() {
        return produktId;
    }

    public void setProduktId(Produkt produktId) {
        this.produktId = produktId;
    }

    public Lieferant getLieferant() {
        return lieferant;
    }

    public void setLieferant(Lieferant lieferant) {
        this.lieferant = lieferant;
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
        if (!(object instanceof Lieferung)) {
            return false;
        }
        Lieferung other = (Lieferung) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pp4.model.Lieferung[ id=" + id + " ]";
    }
    
}
