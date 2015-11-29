/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.ProxVersao;

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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author erico
 */
@Entity
@Table(name = "ITR_COMPOSICAO_CAPITAL_SOCIAL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ItrComposicaoCapitalSocial.findAll", query = "SELECT i FROM ItrComposicaoCapitalSocial i"),
    @NamedQuery(name = "ItrComposicaoCapitalSocial.findByIdComposicao", query = "SELECT i FROM ItrComposicaoCapitalSocial i WHERE i.idComposicao = :idComposicao"),
    @NamedQuery(name = "ItrComposicaoCapitalSocial.findByFkEmpresa", query = "SELECT i FROM ItrComposicaoCapitalSocial i WHERE i.fkEmpresa = :fkEmpresa"),
    @NamedQuery(name = "ItrComposicaoCapitalSocial.findByDataReferencia", query = "SELECT i FROM ItrComposicaoCapitalSocial i WHERE i.dataReferencia = :dataReferencia"),
    @NamedQuery(name = "ItrComposicaoCapitalSocial.findByNumeroIdentificacaoComposicaoCapitalSocial", query = "SELECT i FROM ItrComposicaoCapitalSocial i WHERE i.numeroIdentificacaoComposicaoCapitalSocial = :numeroIdentificacaoComposicaoCapitalSocial"),
    @NamedQuery(name = "ItrComposicaoCapitalSocial.findByQuantidadeAcaoOrdinariaCapitalIntegralizado", query = "SELECT i FROM ItrComposicaoCapitalSocial i WHERE i.quantidadeAcaoOrdinariaCapitalIntegralizado = :quantidadeAcaoOrdinariaCapitalIntegralizado"),
    @NamedQuery(name = "ItrComposicaoCapitalSocial.findByQuantidadeAcaoPreferencialCapitalIntegralizado", query = "SELECT i FROM ItrComposicaoCapitalSocial i WHERE i.quantidadeAcaoPreferencialCapitalIntegralizado = :quantidadeAcaoPreferencialCapitalIntegralizado"),
    @NamedQuery(name = "ItrComposicaoCapitalSocial.findByQuantidadeTotalAcaoCapitalIntegralizado", query = "SELECT i FROM ItrComposicaoCapitalSocial i WHERE i.quantidadeTotalAcaoCapitalIntegralizado = :quantidadeTotalAcaoCapitalIntegralizado"),
    @NamedQuery(name = "ItrComposicaoCapitalSocial.findByQuantidadeAcaoOrdinariaTesouraria", query = "SELECT i FROM ItrComposicaoCapitalSocial i WHERE i.quantidadeAcaoOrdinariaTesouraria = :quantidadeAcaoOrdinariaTesouraria"),
    @NamedQuery(name = "ItrComposicaoCapitalSocial.findByQuantidadeAcaoPreferencialTesouraria", query = "SELECT i FROM ItrComposicaoCapitalSocial i WHERE i.quantidadeAcaoPreferencialTesouraria = :quantidadeAcaoPreferencialTesouraria"),
    @NamedQuery(name = "ItrComposicaoCapitalSocial.findByQuantidadeTotalAcaoTesouraria", query = "SELECT i FROM ItrComposicaoCapitalSocial i WHERE i.quantidadeTotalAcaoTesouraria = :quantidadeTotalAcaoTesouraria"),
    @NamedQuery(name = "ItrComposicaoCapitalSocial.findByVersaoDocumento", query = "SELECT i FROM ItrComposicaoCapitalSocial i WHERE i.versaoDocumento = :versaoDocumento")})
public class ItrComposicaoCapitalSocial implements Serializable ,ComposicaoCapitalSocial {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_composicao")
    private Long idComposicao;
    @Basic(optional = false)
    @Column(name = "fk_empresa")
    private String fkEmpresa;
    @Basic(optional = false)
    @Column(name = "data_referencia")
    @Temporal(TemporalType.DATE)
    private Date dataReferencia;
    @Basic(optional = false)
    @Column(name = "numero_identificacao_composicao_capital_social")
    private long numeroIdentificacaoComposicaoCapitalSocial;
    @Basic(optional = false)
    @Column(name = "quantidade_acao_ordinaria_capital_integralizado")
    private long quantidadeAcaoOrdinariaCapitalIntegralizado;
    @Basic(optional = false)
    @Column(name = "quantidade_acao_preferencial_capital_integralizado")
    private long quantidadeAcaoPreferencialCapitalIntegralizado;
    @Basic(optional = false)
    @Column(name = "quantidade_total_acao_capital_integralizado")
    private long quantidadeTotalAcaoCapitalIntegralizado;
    @Basic(optional = false)
    @Column(name = "quantidade_acao_ordinaria_tesouraria")
    private long quantidadeAcaoOrdinariaTesouraria;
    @Basic(optional = false)
    @Column(name = "quantidade_acao_preferencial_tesouraria")
    private long quantidadeAcaoPreferencialTesouraria;
    @Basic(optional = false)
    @Column(name = "quantidade_total_acao_tesouraria")
    private long quantidadeTotalAcaoTesouraria;
    @Column(name = "versao_documento")
    private Integer versaoDocumento;
    @JoinColumn(name = "fk_arquivo", referencedColumnName = "id_arquivo")
    @ManyToOne(optional = false)
    private ControleArquivosInseridos fkArquivo;

    public ItrComposicaoCapitalSocial() {
    }

    public ItrComposicaoCapitalSocial(Long idComposicao) {
        this.idComposicao = idComposicao;
    }

    public ItrComposicaoCapitalSocial(Long idComposicao, String fkEmpresa, Date dataReferencia, long numeroIdentificacaoComposicaoCapitalSocial, long quantidadeAcaoOrdinariaCapitalIntegralizado, long quantidadeAcaoPreferencialCapitalIntegralizado, long quantidadeTotalAcaoCapitalIntegralizado, long quantidadeAcaoOrdinariaTesouraria, long quantidadeAcaoPreferencialTesouraria, long quantidadeTotalAcaoTesouraria) {
        this.idComposicao = idComposicao;
        this.fkEmpresa = fkEmpresa;
        this.dataReferencia = dataReferencia;
        this.numeroIdentificacaoComposicaoCapitalSocial = numeroIdentificacaoComposicaoCapitalSocial;
        this.quantidadeAcaoOrdinariaCapitalIntegralizado = quantidadeAcaoOrdinariaCapitalIntegralizado;
        this.quantidadeAcaoPreferencialCapitalIntegralizado = quantidadeAcaoPreferencialCapitalIntegralizado;
        this.quantidadeTotalAcaoCapitalIntegralizado = quantidadeTotalAcaoCapitalIntegralizado;
        this.quantidadeAcaoOrdinariaTesouraria = quantidadeAcaoOrdinariaTesouraria;
        this.quantidadeAcaoPreferencialTesouraria = quantidadeAcaoPreferencialTesouraria;
        this.quantidadeTotalAcaoTesouraria = quantidadeTotalAcaoTesouraria;
    }

    @Override
    public Long getIdComposicao() {
        return idComposicao;
    }

    @Override
    public void setIdComposicao(Long idComposicao) {
        this.idComposicao = idComposicao;
    }

    @Override
    public String getFkEmpresa() {
        return fkEmpresa;
    }

    @Override
    public void setFkEmpresa(String fkEmpresa) {
        this.fkEmpresa = fkEmpresa;
    }

    @Override
    public Date getDataReferencia() {
        return dataReferencia;
    }

    @Override
    public void setDataReferencia(Date dataReferencia) {
        this.dataReferencia = dataReferencia;
    }

    @Override
    public long getNumeroIdentificacaoComposicaoCapitalSocial() {
        return numeroIdentificacaoComposicaoCapitalSocial;
    }

    @Override
    public void setNumeroIdentificacaoComposicaoCapitalSocial(long numeroIdentificacaoComposicaoCapitalSocial) {
        this.numeroIdentificacaoComposicaoCapitalSocial = numeroIdentificacaoComposicaoCapitalSocial;
    }

    @Override
    public long getQuantidadeAcaoOrdinariaCapitalIntegralizado() {
        return quantidadeAcaoOrdinariaCapitalIntegralizado;
    }

    @Override
    public void setQuantidadeAcaoOrdinariaCapitalIntegralizado(long quantidadeAcaoOrdinariaCapitalIntegralizado) {
        this.quantidadeAcaoOrdinariaCapitalIntegralizado = quantidadeAcaoOrdinariaCapitalIntegralizado;
    }

    @Override
    public long getQuantidadeAcaoPreferencialCapitalIntegralizado() {
        return quantidadeAcaoPreferencialCapitalIntegralizado;
    }

    @Override
    public void setQuantidadeAcaoPreferencialCapitalIntegralizado(long quantidadeAcaoPreferencialCapitalIntegralizado) {
        this.quantidadeAcaoPreferencialCapitalIntegralizado = quantidadeAcaoPreferencialCapitalIntegralizado;
    }

    @Override
    public long getQuantidadeTotalAcaoCapitalIntegralizado() {
        return quantidadeTotalAcaoCapitalIntegralizado;
    }

    @Override
    public void setQuantidadeTotalAcaoCapitalIntegralizado(long quantidadeTotalAcaoCapitalIntegralizado) {
        this.quantidadeTotalAcaoCapitalIntegralizado = quantidadeTotalAcaoCapitalIntegralizado;
    }

    @Override
    public long getQuantidadeAcaoOrdinariaTesouraria() {
        return quantidadeAcaoOrdinariaTesouraria;
    }

    @Override
    public void setQuantidadeAcaoOrdinariaTesouraria(long quantidadeAcaoOrdinariaTesouraria) {
        this.quantidadeAcaoOrdinariaTesouraria = quantidadeAcaoOrdinariaTesouraria;
    }

    @Override
    public long getQuantidadeAcaoPreferencialTesouraria() {
        return quantidadeAcaoPreferencialTesouraria;
    }

    @Override
    public void setQuantidadeAcaoPreferencialTesouraria(long quantidadeAcaoPreferencialTesouraria) {
        this.quantidadeAcaoPreferencialTesouraria = quantidadeAcaoPreferencialTesouraria;
    }

    @Override
    public long getQuantidadeTotalAcaoTesouraria() {
        return quantidadeTotalAcaoTesouraria;
    }

    @Override
    public void setQuantidadeTotalAcaoTesouraria(long quantidadeTotalAcaoTesouraria) {
        this.quantidadeTotalAcaoTesouraria = quantidadeTotalAcaoTesouraria;
    }

    @Override
    public Integer getVersaoDocumento() {
        return versaoDocumento;
    }

    @Override
    public void setVersaoDocumento(Integer versaoDocumento) {
        this.versaoDocumento = versaoDocumento;
    }

    @Override
    public ControleArquivosInseridos getFkArquivo() {
        return fkArquivo;
    }

    @Override
    public void setFkArquivo(ControleArquivosInseridos fkArquivo) {
        this.fkArquivo = fkArquivo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idComposicao != null ? idComposicao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ItrComposicaoCapitalSocial)) {
            return false;
        }
        ItrComposicaoCapitalSocial other = (ItrComposicaoCapitalSocial) object;
        if ((this.idComposicao == null && other.idComposicao != null) || (this.idComposicao != null && !this.idComposicao.equals(other.idComposicao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ItrComposicaoCapitalSocial[ idComposicao=" + idComposicao + " ]";
    }
    
}
