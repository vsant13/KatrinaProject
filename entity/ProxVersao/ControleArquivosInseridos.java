/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.ProxVersao;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "CONTROLE_ARQUIVOS_INSERIDOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ControleArquivosInseridos.findAll", query = "SELECT c FROM ControleArquivosInseridos c"),
    @NamedQuery(name = "ControleArquivosInseridos.findByIdArquivo", query = "SELECT c FROM ControleArquivosInseridos c WHERE c.idArquivo = :idArquivo"),
    @NamedQuery(name = "ControleArquivosInseridos.findByTipoArquivo", query = "SELECT c FROM ControleArquivosInseridos c WHERE c.tipoArquivo = :tipoArquivo"),
    @NamedQuery(name = "ControleArquivosInseridos.findByEstado", query = "SELECT c FROM ControleArquivosInseridos c WHERE c.estado = :estado")})
public class ControleArquivosInseridos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_arquivo")
    private String idArquivo;
    @Column(name = "tipo_arquivo")
    private String tipoArquivo;
    @Column(name = "estado")
    private String estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkArquivo")
    private Collection<ItrConta> itrContaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkArquivo")
    private Collection<ItrProventos> itrProventosCollection;
    @OneToMany(mappedBy = "fkArquivo")
    private Collection<PapelDeDesconhecido> papelDeDesconhecidoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkArquivo")
    private Collection<ItrComposicaoCapitalSocial> itrComposicaoCapitalSocialCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkArquivo")
    private Collection<DfpComposicaoCapitalSocial> dfpComposicaoCapitalSocialCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkArquivo")
    private Collection<DfpProventos> dfpProventosCollection;
    @OneToMany(mappedBy = "fkArquivo")
    private Collection<PapelBovespa> papelBovespaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkArquivo")
    private Collection<DfpConta> dfpContaCollection;

    public ControleArquivosInseridos() {
    }

    public ControleArquivosInseridos(String idArquivo) {
        this.idArquivo = idArquivo;
    }

    public String getIdArquivo() {
        return idArquivo;
    }

    public void setIdArquivo(String idArquivo) {
        this.idArquivo = idArquivo;
    }

    public String getTipoArquivo() {
        return tipoArquivo;
    }

    public void setTipoArquivo(String tipoArquivo) {
        this.tipoArquivo = tipoArquivo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @XmlTransient
    public Collection<ItrConta> getItrContaCollection() {
        return itrContaCollection;
    }

    public void setItrContaCollection(Collection<ItrConta> itrContaCollection) {
        this.itrContaCollection = itrContaCollection;
    }

    @XmlTransient
    public Collection<ItrProventos> getItrProventosCollection() {
        return itrProventosCollection;
    }

    public void setItrProventosCollection(Collection<ItrProventos> itrProventosCollection) {
        this.itrProventosCollection = itrProventosCollection;
    }

    @XmlTransient
    public Collection<PapelDeDesconhecido> getPapelDeDesconhecidoCollection() {
        return papelDeDesconhecidoCollection;
    }

    public void setPapelDeDesconhecidoCollection(Collection<PapelDeDesconhecido> papelDeDesconhecidoCollection) {
        this.papelDeDesconhecidoCollection = papelDeDesconhecidoCollection;
    }

    @XmlTransient
    public Collection<ItrComposicaoCapitalSocial> getItrComposicaoCapitalSocialCollection() {
        return itrComposicaoCapitalSocialCollection;
    }

    public void setItrComposicaoCapitalSocialCollection(Collection<ItrComposicaoCapitalSocial> itrComposicaoCapitalSocialCollection) {
        this.itrComposicaoCapitalSocialCollection = itrComposicaoCapitalSocialCollection;
    }

    @XmlTransient
    public Collection<DfpComposicaoCapitalSocial> getDfpComposicaoCapitalSocialCollection() {
        return dfpComposicaoCapitalSocialCollection;
    }

    public void setDfpComposicaoCapitalSocialCollection(Collection<DfpComposicaoCapitalSocial> dfpComposicaoCapitalSocialCollection) {
        this.dfpComposicaoCapitalSocialCollection = dfpComposicaoCapitalSocialCollection;
    }

    @XmlTransient
    public Collection<DfpProventos> getDfpProventosCollection() {
        return dfpProventosCollection;
    }

    public void setDfpProventosCollection(Collection<DfpProventos> dfpProventosCollection) {
        this.dfpProventosCollection = dfpProventosCollection;
    }

    @XmlTransient
    public Collection<PapelBovespa> getPapelBovespaCollection() {
        return papelBovespaCollection;
    }

    public void setPapelBovespaCollection(Collection<PapelBovespa> papelBovespaCollection) {
        this.papelBovespaCollection = papelBovespaCollection;
    }

    @XmlTransient
    public Collection<DfpConta> getDfpContaCollection() {
        return dfpContaCollection;
    }

    public void setDfpContaCollection(Collection<DfpConta> dfpContaCollection) {
        this.dfpContaCollection = dfpContaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idArquivo != null ? idArquivo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ControleArquivosInseridos)) {
            return false;
        }
        ControleArquivosInseridos other = (ControleArquivosInseridos) object;
        if ((this.idArquivo == null && other.idArquivo != null) || (this.idArquivo != null && !this.idArquivo.equals(other.idArquivo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ControleArquivosInseridos[ idArquivo=" + idArquivo + " ]";
    }
    
}
