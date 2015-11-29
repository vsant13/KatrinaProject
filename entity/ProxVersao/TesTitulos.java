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
@Table(name = "TES_TITULOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TesTitulos.findAll", query = "SELECT t FROM TesTitulos t"),
    @NamedQuery(name = "TesTitulos.findByIdTitulo", query = "SELECT t FROM TesTitulos t WHERE t.idTitulo = :idTitulo"),
    @NamedQuery(name = "TesTitulos.findByDescri", query = "SELECT t FROM TesTitulos t WHERE t.descri = :descri")})
public class TesTitulos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_titulo")
    private String idTitulo;
    @Column(name = "descri")
    private String descri;
    @OneToMany(mappedBy = "fkTitulo")
    private Collection<TesRentabilidade> tesRentabilidadeCollection;
    @OneToMany(mappedBy = "fkTitulo")
    private Collection<TesPrecoTaxa> tesPrecoTaxaCollection;

    public TesTitulos() {
    }

    public TesTitulos(String idTitulo) {
        this.idTitulo = idTitulo;
    }

    public String getIdTitulo() {
        return idTitulo;
    }

    public void setIdTitulo(String idTitulo) {
        this.idTitulo = idTitulo;
    }

    public String getDescri() {
        return descri;
    }

    public void setDescri(String descri) {
        this.descri = descri;
    }

    @XmlTransient
    public Collection<TesRentabilidade> getTesRentabilidadeCollection() {
        return tesRentabilidadeCollection;
    }

    public void setTesRentabilidadeCollection(Collection<TesRentabilidade> tesRentabilidadeCollection) {
        this.tesRentabilidadeCollection = tesRentabilidadeCollection;
    }

    @XmlTransient
    public Collection<TesPrecoTaxa> getTesPrecoTaxaCollection() {
        return tesPrecoTaxaCollection;
    }

    public void setTesPrecoTaxaCollection(Collection<TesPrecoTaxa> tesPrecoTaxaCollection) {
        this.tesPrecoTaxaCollection = tesPrecoTaxaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTitulo != null ? idTitulo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TesTitulos)) {
            return false;
        }
        TesTitulos other = (TesTitulos) object;
        if ((this.idTitulo == null && other.idTitulo != null) || (this.idTitulo != null && !this.idTitulo.equals(other.idTitulo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.TesTitulos[ idTitulo=" + idTitulo + " ]";
    }
    
}
