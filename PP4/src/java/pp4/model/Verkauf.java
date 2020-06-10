/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pp4.model;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author samu
 */
@Entity
@Table(name = "Verkauf")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Verkauf.findAll", query = "SELECT v FROM Verkauf v"),
    @NamedQuery(name = "Verkauf.findById", query = "SELECT v FROM Verkauf v WHERE v.id = :id"),
    @NamedQuery(name = "Verkauf.findByMenge", query = "SELECT v FROM Verkauf v WHERE v.menge = :menge"),
    @NamedQuery(name = "Verkauf.findByZeitpunkt", query = "SELECT v FROM Verkauf v WHERE v.zeitpunkt = :zeitpunkt")})
public class Verkauf implements Serializable {

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
    @Basic(optional = false)
    @NotNull
    @Column(name = "zeitpunkt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date zeitpunkt;
    @JoinColumn(name = "verkaufsstelle_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Verkaufsstelle verkaufsstelleId;
    @JoinColumn(name = "produkt_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Produkt produktId;

    public Verkauf() {
    }

    public Verkauf(Integer id) {
        this.id = id;
    }

    public Verkauf(Integer id, int menge, Date zeitpunkt) {
        this.id = id;
        this.menge = menge;
        this.zeitpunkt = zeitpunkt;
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

    public Date getZeitpunkt() {
        return zeitpunkt;
    }

    public void setZeitpunkt(Date zeitpunkt) {
        this.zeitpunkt = zeitpunkt;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Verkauf)) {
            return false;
        }
        Verkauf other = (Verkauf) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pp4.model.Verkauf[ id=" + id + " ]";
    }
    
}
