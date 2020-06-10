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
@Table(name = "Lieferant")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Lieferant.findAll", query = "SELECT l FROM Lieferant l"),
    @NamedQuery(name = "Lieferant.findById", query = "SELECT l FROM Lieferant l WHERE l.id = :id"),
    @NamedQuery(name = "Lieferant.findByName", query = "SELECT l FROM Lieferant l WHERE l.name = :name"),
    @NamedQuery(name = "Lieferant.findByAktiv", query = "SELECT l FROM Lieferant l WHERE l.aktiv = :aktiv")})
public class Lieferant implements Serializable {

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
    @Basic(optional = false)
    @NotNull
    @Column(name = "aktiv")
    private boolean aktiv;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lieferant")
    private Collection<Lieferung> lieferungCollection;

    public Lieferant() {
    }

    public Lieferant(Integer id) {
        this.id = id;
    }

    public Lieferant(Integer id, String name, boolean aktiv) {
        this.id = id;
        this.name = name;
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

    public boolean getAktiv() {
        return aktiv;
    }

    public void setAktiv(boolean aktiv) {
        this.aktiv = aktiv;
    }

    @XmlTransient
    public Collection<Lieferung> getLieferungCollection() {
        return lieferungCollection;
    }

    public void setLieferungCollection(Collection<Lieferung> lieferungCollection) {
        this.lieferungCollection = lieferungCollection;
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
        if (!(object instanceof Lieferant)) {
            return false;
        }
        Lieferant other = (Lieferant) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pp4.model.Lieferant[ id=" + id + " ]";
    }
    
}
