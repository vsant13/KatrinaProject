/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.ProxVersao;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "DFP_PROVENTOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DfpProventos.findAll", query = "SELECT d FROM DfpProventos d"),
    @NamedQuery(name = "DfpProventos.findByIdProvento", query = "SELECT d FROM DfpProventos d WHERE d.idProvento = :idProvento"),
    @NamedQuery(name = "DfpProventos.findByFkEmpresa", query = "SELECT d FROM DfpProventos d WHERE d.fkEmpresa = :fkEmpresa"),
    @NamedQuery(name = "DfpProventos.findByTipoProvento", query = "SELECT d FROM DfpProventos d WHERE d.tipoProvento = :tipoProvento"),
    @NamedQuery(name = "DfpProventos.findByDataAprovacao", query = "SELECT d FROM DfpProventos d WHERE d.dataAprovacao = :dataAprovacao"),
    @NamedQuery(name = "DfpProventos.findByDataInicioPagamento", query = "SELECT d FROM DfpProventos d WHERE d.dataInicioPagamento = :dataInicioPagamento"),
    @NamedQuery(name = "DfpProventos.findByDescricaoCodEspecie", query = "SELECT d FROM DfpProventos d WHERE d.descricaoCodEspecie = :descricaoCodEspecie"),
    @NamedQuery(name = "DfpProventos.findBySiglaCodEspecie", query = "SELECT d FROM DfpProventos d WHERE d.siglaCodEspecie = :siglaCodEspecie"),
    @NamedQuery(name = "DfpProventos.findByDescricaoCodClassePreferencial", query = "SELECT d FROM DfpProventos d WHERE d.descricaoCodClassePreferencial = :descricaoCodClassePreferencial"),
    @NamedQuery(name = "DfpProventos.findBySiglaCodClassePreferencial", query = "SELECT d FROM DfpProventos d WHERE d.siglaCodClassePreferencial = :siglaCodClassePreferencial"),
    @NamedQuery(name = "DfpProventos.findByValorDoProventoPorAcao", query = "SELECT d FROM DfpProventos d WHERE d.valorDoProventoPorAcao = :valorDoProventoPorAcao"),
    @NamedQuery(name = "DfpProventos.findByVersaoDocumento", query = "SELECT d FROM DfpProventos d WHERE d.versaoDocumento = :versaoDocumento")})
public class DfpProventos implements Serializable ,Proventos{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_provento")
    private Long idProvento;
    @Basic(optional = false)
    @Column(name = "fk_empresa")
    private String fkEmpresa;
    @Basic(optional = false)
    @Column(name = "tipo_provento")
    private String tipoProvento;
    @Column(name = "data_aprovacao")
    @Temporal(TemporalType.DATE)
    private Date dataAprovacao;
    @Column(name = "data_inicio_pagamento")
    @Temporal(TemporalType.DATE)
    private Date dataInicioPagamento;
    @Column(name = "descricao_cod_especie")
    private String descricaoCodEspecie;
    @Column(name = "sigla_cod_especie")
    private String siglaCodEspecie;
    @Column(name = "descricao_cod_classe_preferencial")
    private String descricaoCodClassePreferencial;
    @Column(name = "sigla_cod_classe_preferencial")
    private String siglaCodClassePreferencial;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor_do_provento_por_acao")
    private BigDecimal valorDoProventoPorAcao;
    @Column(name = "versao_documento")
    private Integer versaoDocumento;
    @JoinColumn(name = "fk_arquivo", referencedColumnName = "id_arquivo")
    @ManyToOne(optional = false)
    private ControleArquivosInseridos fkArquivo;

    public DfpProventos() {
    }

    public DfpProventos(Long idProvento) {
        this.idProvento = idProvento;
    }

    public DfpProventos(Long idProvento, String fkEmpresa, String tipoProvento) {
        this.idProvento = idProvento;
        this.fkEmpresa = fkEmpresa;
        this.tipoProvento = tipoProvento;
    }

    public Long getIdProvento() {
        return idProvento;
    }

    @Override
    public void setIdProvento(Long idProvento) {
        this.idProvento = idProvento;
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
    public String getTipoProvento() {
        return tipoProvento;
    }
    @Override
    public void setTipoProvento(String tipoProvento) {
        this.tipoProvento = tipoProvento;
    }
    @Override
    public Date getDataAprovacao() {
        return dataAprovacao;
    }
    @Override
    public void setDataAprovacao(Date dataAprovacao) {
        this.dataAprovacao = dataAprovacao;
    }
    @Override
    public Date getDataInicioPagamento() {
        return dataInicioPagamento;
    }
    @Override
    public void setDataInicioPagamento(Date dataInicioPagamento) {
        this.dataInicioPagamento = dataInicioPagamento;
    }
    @Override
    public String getDescricaoCodEspecie() {
        return descricaoCodEspecie;
    }
    @Override
    public void setDescricaoCodEspecie(String descricaoCodEspecie) {
        this.descricaoCodEspecie = descricaoCodEspecie;
    }
    @Override
    public String getSiglaCodEspecie() {
        return siglaCodEspecie;
    }
    @Override
    public void setSiglaCodEspecie(String siglaCodEspecie) {
        this.siglaCodEspecie = siglaCodEspecie;
    }
    @Override
    public String getDescricaoCodClassePreferencial() {
        return descricaoCodClassePreferencial;
    }
    @Override
    public void setDescricaoCodClassePreferencial(String descricaoCodClassePreferencial) {
        this.descricaoCodClassePreferencial = descricaoCodClassePreferencial;
    }
    @Override
    public String getSiglaCodClassePreferencial() {
        return siglaCodClassePreferencial;
    }
    @Override
    public void setSiglaCodClassePreferencial(String siglaCodClassePreferencial) {
        this.siglaCodClassePreferencial = siglaCodClassePreferencial;
    }
    @Override
    public BigDecimal getValorDoProventoPorAcao() {
        return valorDoProventoPorAcao;
    }
    @Override
    public void setValorDoProventoPorAcao(BigDecimal valorDoProventoPorAcao) {
        this.valorDoProventoPorAcao = valorDoProventoPorAcao;
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

    public void setFkArquivo(ControleArquivosInseridos fkArquivo) {
        this.fkArquivo = fkArquivo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProvento != null ? idProvento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DfpProventos)) {
            return false;
        }
        DfpProventos other = (DfpProventos) object;
        if ((this.idProvento == null && other.idProvento != null) || (this.idProvento != null && !this.idProvento.equals(other.idProvento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.DfpProventos[ idProvento=" + idProvento + " ]";
    }
    
}
