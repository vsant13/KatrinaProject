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
@Table(name = "INDOPC")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Indopc.findAll", query = "SELECT i FROM Indopc i"),
    @NamedQuery(name = "Indopc.findByIdIndopc", query = "SELECT i FROM Indopc i WHERE i.idIndopc = :idIndopc"),
    @NamedQuery(name = "Indopc.findBySigla", query = "SELECT i FROM Indopc i WHERE i.sigla = :sigla"),
    @NamedQuery(name = "Indopc.findByTipoIndopc", query = "SELECT i FROM Indopc i WHERE i.tipoIndopc = :tipoIndopc")})
public class Indopc implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_indopc")
    private Integer idIndopc;
    @Column(name = "sigla")
    private String sigla;
    @Column(name = "tipo_indopc")
    private String tipoIndopc;
    @OneToMany(mappedBy = "fkIndopc")
    private Collection<PapelBovespa> papelBovespaCollection;

    public Indopc() {
    }

    public Indopc(Integer idIndopc) {
        this.idIndopc = idIndopc;
    }

    public Integer getIdIndopc() {
        return idIndopc;
    }

    public void setIdIndopc(Integer idIndopc) {
        this.idIndopc = idIndopc;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getTipoIndopc() {
        return tipoIndopc;
    }

    public void setTipoIndopc(String tipoIndopc) {
        this.tipoIndopc = tipoIndopc;
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
        hash += (idIndopc != null ? idIndopc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Indopc)) {
            return false;
        }
        Indopc other = (Indopc) object;
        if ((this.idIndopc == null && other.idIndopc != null) || (this.idIndopc != null && !this.idIndopc.equals(other.idIndopc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Indopc[ idIndopc=" + idIndopc + " ]";
    }
    
}
