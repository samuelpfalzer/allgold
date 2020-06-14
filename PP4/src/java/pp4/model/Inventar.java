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
@Table(name = "Inventar")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Inventar.findAll", query = "SELECT i FROM Inventar i"),
    @NamedQuery(name = "Inventar.findByProduktId", query = "SELECT i FROM Inventar i WHERE i.produktId = :produktId"),
    @NamedQuery(name = "Inventar.findById", query = "SELECT i FROM Inventar i WHERE i.id = :id"),
    @NamedQuery(name = "Inventar.findByVorrat", query = "SELECT i FROM Inventar i WHERE i.vorrat = :vorrat"),
    @NamedQuery(name = "Inventar.findByBedarf", query = "SELECT i FROM Inventar i WHERE i.bedarf = :bedarf")})
public class Inventar implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "vorrat")
    private int vorrat;
    @Basic(optional = false)
    @NotNull
    @Column(name = "bedarf")
    private int bedarf;
    @JoinColumn(name = "verkaufsstelle_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Verkaufsstelle verkaufsstelleId;
    @JoinColumn(name = "produkt_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Produkt produktId;

    public Inventar() {
    }

    public Inventar(Integer id) {
        this.id = id;
    }

    public Inventar(Integer id, int vorrat, int bedarf) {
        this.id = id;
        this.vorrat = vorrat;
        this.bedarf = bedarf;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getVorrat() {
        return vorrat;
    }

    public void setVorrat(int vorrat) {
        this.vorrat = vorrat;
    }

    public int getBedarf() {
        return bedarf;
    }

    public void setBedarf(int bedarf) {
        this.bedarf = bedarf;
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
        if (!(object instanceof Inventar)) {
            return false;
        }
        Inventar other = (Inventar) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pp4.model.Inventar[ id=" + id + " ]";
    }
    
}
