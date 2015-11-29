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
@Table(name = "TPMERC")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tpmerc.findAll", query = "SELECT t FROM Tpmerc t"),
    @NamedQuery(name = "Tpmerc.findByIdTpmerc", query = "SELECT t FROM Tpmerc t WHERE t.idTpmerc = :idTpmerc"),
    @NamedQuery(name = "Tpmerc.findByTipoTpmerc", query = "SELECT t FROM Tpmerc t WHERE t.tipoTpmerc = :tipoTpmerc")})
public class Tpmerc implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_tpmerc")
    private Integer idTpmerc;
    @Column(name = "tipo_tpmerc")
    private String tipoTpmerc;
    @OneToMany(mappedBy = "fkTpmerc")
    private Collection<PapelBovespa> papelBovespaCollection;

    public Tpmerc() {
    }

    public Tpmerc(Integer idTpmerc) {
        this.idTpmerc = idTpmerc;
    }

    public Integer getIdTpmerc() {
        return idTpmerc;
    }

    public void setIdTpmerc(Integer idTpmerc) {
        this.idTpmerc = idTpmerc;
    }

    public String getTipoTpmerc() {
        return tipoTpmerc;
    }

    public void setTipoTpmerc(String tipoTpmerc) {
        this.tipoTpmerc = tipoTpmerc;
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
        hash += (idTpmerc != null ? idTpmerc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tpmerc)) {
            return false;
        }
        Tpmerc other = (Tpmerc) object;
        if ((this.idTpmerc == null && other.idTpmerc != null) || (this.idTpmerc != null && !this.idTpmerc.equals(other.idTpmerc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Tpmerc[ idTpmerc=" + idTpmerc + " ]";
    }
    
}
