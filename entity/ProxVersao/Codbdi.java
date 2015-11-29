/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.ProxVersao;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author erico
 */
@Entity
@Table(name = "CODBDI")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Codbdi.findAll", query = "SELECT c FROM Codbdi c"),
    @NamedQuery(name = "Codbdi.findByIdCodbdi", query = "SELECT c FROM Codbdi c WHERE c.idCodbdi = :idCodbdi"),
    @NamedQuery(name = "Codbdi.findByTipoCodbdi", query = "SELECT c FROM Codbdi c WHERE c.tipoCodbdi = :tipoCodbdi")})
public class Codbdi implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_codbdi")
    private Integer idCodbdi;
    @Column(name = "tipo_codbdi")
    private String tipoCodbdi;
    @OneToMany(mappedBy = "fkCodbdi")
    private Collection<PapelBovespa> papelBovespaCollection;

    public Codbdi() {
    }

    public Codbdi(Integer idCodbdi) {
        this.idCodbdi = idCodbdi;
    }

    public Integer getIdCodbdi() {
        return idCodbdi;
    }

    public void setIdCodbdi(Integer idCodbdi) {
        this.idCodbdi = idCodbdi;
    }

    public String getTipoCodbdi() {
        return tipoCodbdi;
    }

    public void setTipoCodbdi(String tipoCodbdi) {
        this.tipoCodbdi = tipoCodbdi;
    }

    @XmlTransient
    public Collection<PapelBovespa> getPapelBovespaCollection() {
        return papelBovespaCollection;
    }

    public void setPapelBovespaCollection(Collection<PapelBovespa> papelBovespaCollection) {
        this.papelBovespaCollection = papelBovespaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCodbdi != null ? idCodbdi.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Codbdi)) {
            return false;
        }
        Codbdi other = (Codbdi) object;
        if ((this.idCodbdi == null && other.idCodbdi != null) || (this.idCodbdi != null && !this.idCodbdi.equals(other.idCodbdi))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Codbdi[ idCodbdi=" + idCodbdi + " ]";
    }
    
}
