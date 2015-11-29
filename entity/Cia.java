/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import entity.ProxVersao.PapelBovespa;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Erico
 */

public class Cia implements Serializable {
   
    @OneToMany(mappedBy = "fkCodnegNome")
    private Collection<PapelBovespa> papelBovespaCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_empresa")
    private String idEmpresa;
    @Basic(optional = false)
    @Column(name = "cod_cvm")
    private int codCvm;
    @Column(name = "denominacao_social")
    private String denominacaoSocial;
    @Column(name = "denominacao_comercial")
    private String denominacaoComercial;
    @Column(name = "logradouro")
    private String logradouro;
    @Column(name = "complemento")
    private String complemento;
    @Column(name = "bairro")
    private String bairro;
    @Column(name = "cep")
    private Integer cep;
    @Column(name = "municipio")
    private String municipio;
    @Column(name = "uf")
    private String uf;
    @Column(name = "ddd")
    private Integer ddd;
    @Column(name = "telefone")
    private Integer telefone;
    @Column(name = "fax")
    private Integer fax;
    @Column(name = "denominacao_anterior")
    private String denominacaoAnterior;
    @Column(name = "setor_atividade")
    private String setorAtividade;
    @Basic(optional = false)
    @Column(name = "cnpj")
    private long cnpj;
    @Column(name = "dri")
    private String dri;
    @Column(name = "auditor")
    private String auditor;
    @Column(name = "quant_de_acoes_ordinarias")
    private BigInteger quantDeAcoesOrdinarias;
    @Column(name = "quant_de_acoes_preferenciais")
    private BigInteger quantDeAcoesPreferenciais;
    @Column(name = "situacao")
    private String situacao;
    @Column(name = "data_da_situacao")
    @Temporal(TemporalType.DATE)
    private Date dataDaSituacao;
    @Column(name = "tipo_papel1")
    private String tipoPapel1;
    @Column(name = "tipo_papel2")
    private String tipoPapel2;
    @Column(name = "tipo_papel3")
    private String tipoPapel3;
    @Column(name = "tipo_papel4")
    private String tipoPapel4;
    @Column(name = "tipo_papel5")
    private String tipoPapel5;
    @Column(name = "tipo_papel6")
    private String tipoPapel6;
    @Column(name = "controle_acionario")
    private String controleAcionario;
    @Column(name = "data_de_registro")
    @Temporal(TemporalType.DATE)
    private Date dataDeRegistro;
    @Column(name = "data_de_cancelamento")
    @Temporal(TemporalType.DATE)
    private Date dataDeCancelamento;
    @Column(name = "mercado")
    private String mercado;
    @Column(name = "bolsa1")
    private String bolsa1;
    @Column(name = "bolsa2")
    private String bolsa2;
    @Column(name = "bolsa3")
    private String bolsa3;
    @Column(name = "bolsa4")
    private String bolsa4;
    @Column(name = "bolsa5")
    private String bolsa5;
    @Column(name = "bolsa6")
    private String bolsa6;
    @Column(name = "bolsa7")
    private String bolsa7;
    @Column(name = "bolsa8")
    private String bolsa8;
    @Column(name = "bolsa9")
    private String bolsa9;
    @Column(name = "motivo_do_cancelamento")
    private String motivoDoCancelamento;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "patrimonio_liquido")
    private Float patrimonioLiquido;
    @Column(name = "data_do_patrimonio")
    @Temporal(TemporalType.DATE)
    private Date dataDoPatrimonio;
    @Column(name = "e_mail")
    private String eMail;
    @Column(name = "nome_setor_atividade")
    private String nomeSetorAtividade;
    @Column(name = "data_da_acao")
    @Temporal(TemporalType.DATE)
    private Date dataDaAcao;
    @Column(name = "tipo_negocio1")
    private String tipoNegocio1;
    @Column(name = "tipo_negocio2")
    private String tipoNegocio2;
    @Column(name = "tipo_negocio3")
    private String tipoNegocio3;
    @Column(name = "tipo_negocio4")
    private String tipoNegocio4;
    @Column(name = "tipo_negocio5")
    private String tipoNegocio5;
    @Column(name = "tipo_negocio6")
    private String tipoNegocio6;
    @Column(name = "tipo_mercado1")
    private String tipoMercado1;
    @Column(name = "tipo_mercado2")
    private String tipoMercado2;
    @Column(name = "tipo_mercado3")
    private String tipoMercado3;
    @Column(name = "tipo_mercado4")
    private String tipoMercado4;
    @Column(name = "tipo_mercado5")
    private String tipoMercado5;
    @Column(name = "tipo_mercado6")
    private String tipoMercado6;
    
    
    public Cia() {

    }

    public Cia(String idEmpresa) {
       
        this.idEmpresa = idEmpresa;

        
    }

    public Cia( int codCvm) {
        this.codCvm = codCvm;
    }
    
    public Cia(long cnpj) {
        this.cnpj = cnpj;
    }

    public String getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(String idEmpresa) {
        this.idEmpresa = idEmpresa;

    }

    public int getCodCvm() {
        return codCvm;
    }

    public void setCodCvm(int codCvm) {
        this.codCvm = codCvm;

    }

    public String getDenominacaoSocial() {
        return denominacaoSocial;
    }

    public void setDenominacaoSocial(String denominacaoSocial) {
        this.denominacaoSocial = denominacaoSocial;

    }

    public String getDenominacaoComercial() {
        return denominacaoComercial;
    }

    public void setDenominacaoComercial(String denominacaoComercial) {
        this.denominacaoComercial = denominacaoComercial;

    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;

    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;

    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;

    }

    public Integer getCep() {
        return cep;
    }

    public void setCep(Integer cep) {
        this.cep = cep;

    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
   
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    
    }

    public Integer getDdd() {
        return ddd;
    }

    public void setDdd(Integer ddd) {
        this.ddd = ddd;
  
    }

    public Integer getTelefone() {
        return telefone;
    }

    public void setTelefone(Integer telefone) {
        this.telefone = telefone;
  
    }

    public Integer getFax() {
        return fax;
    }

    public void setFax(Integer fax) {
        this.fax = fax;

    }

    public String getDenominacaoAnterior() {
        return denominacaoAnterior;
    }

    public void setDenominacaoAnterior(String denominacaoAnterior) {
        this.denominacaoAnterior = denominacaoAnterior;
   
    }

    public String getSetorAtividade() {
        return setorAtividade;
    }

    public void setSetorAtividade(String setorAtividade) {
        this.setorAtividade = setorAtividade;
     
    }

    public long getCnpj() {
        return cnpj;
    }

    public void setCnpj(long cnpj) {
        this.cnpj = cnpj;

    }

    public String getDri() {
        return dri;
    }

    public void setDri(String dri) {
        this.dri = dri;
  
    }

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
  
    }

    public BigInteger getQuantDeAcoesOrdinarias() {
        return quantDeAcoesOrdinarias;
    }

    public void setQuantDeAcoesOrdinarias(BigInteger quantDeAcoesOrdinarias) {

    }

    public BigInteger getQuantDeAcoesPreferenciais() {
        return quantDeAcoesPreferenciais;
    }

    public void setQuantDeAcoesPreferenciais(BigInteger quantDeAcoesPreferenciais) {
        this.quantDeAcoesPreferenciais = quantDeAcoesPreferenciais;

    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
     
    }

    public Date getDataDaSituacao() {
        return dataDaSituacao;
    }

    public void setDataDaSituacao(Date dataDaSituacao) {
        this.dataDaSituacao = dataDaSituacao;
    }

    public String getTipoPapel1() {
        return tipoPapel1;
    }

    public void setTipoPapel1(String tipoPapel1) {
        this.tipoPapel1 = tipoPapel1;
   
    }

    public String getTipoPapel2() {
        return tipoPapel2;
    }

    public void setTipoPapel2(String tipoPapel2) {
        this.tipoPapel2 = tipoPapel2;

    }

    public String getTipoPapel3() {
        return tipoPapel3;
    }

    public void setTipoPapel3(String tipoPapel3) {
        this.tipoPapel3 = tipoPapel3;
 
    }

    public String getTipoPapel4() {
        return tipoPapel4;
    }

    public void setTipoPapel4(String tipoPapel4) {
        this.tipoPapel4 = tipoPapel4;
 
    }

    public String getTipoPapel5() {
        return tipoPapel5;
    }

    public void setTipoPapel5(String tipoPapel5) {
        this.tipoPapel5 = tipoPapel5;
  
    }

    public String getTipoPapel6() {
        return tipoPapel6;
    }

    public void setTipoPapel6(String tipoPapel6) {
        this.tipoPapel6 = tipoPapel6;
 
    }

    public String getControleAcionario() {
        return controleAcionario;
    }

    public void setControleAcionario(String controleAcionario) {
        this.controleAcionario = controleAcionario;

    }

    public Date getDataDeRegistro() {
        return dataDeRegistro;
    }

    public void setDataDeRegistro(Date dataDeRegistro) {
        this.dataDeRegistro = dataDeRegistro;
 
    }

    public Date getDataDeCancelamento() {
        return dataDeCancelamento;
    }

    public void setDataDeCancelamento(Date dataDeCancelamento) {
        this.dataDeCancelamento = dataDeCancelamento;
 
    }

    public String getMercado() {
        return mercado;
    }

    public void setMercado(String mercado) {
        this.mercado = mercado;

    }

    public String getBolsa1() {
        return bolsa1;
    }

    public void setBolsa1(String bolsa1) {
        this.bolsa1 = bolsa1;
 
    }

    public String getBolsa2() {
        return bolsa2;
    }

    public void setBolsa2(String bolsa2) {
        this.bolsa2 = bolsa2;
  
    }

    public String getBolsa3() {
        return bolsa3;
    }

    public void setBolsa3(String bolsa3) {
        this.bolsa3 = bolsa3;

    }

    public String getBolsa4() {
        return bolsa4;
    }

    public void setBolsa4(String bolsa4) {
        this.bolsa4 = bolsa4;
     
    }

    public String getBolsa5() {
        return bolsa5;
    }

    public void setBolsa5(String bolsa5) {
        this.bolsa5 = bolsa5;
     
    }

    public String getBolsa6() {
        return bolsa6;
    }

    public void setBolsa6(String bolsa6) {
        this.bolsa6 = bolsa6;

    }

    public String getBolsa7() {
        return bolsa7;
    }

    public void setBolsa7(String bolsa7) {
        this.bolsa7 = bolsa7;
   
    }

    public String getBolsa8() {
        return bolsa8;
    }

    public void setBolsa8(String bolsa8) {
        this.bolsa8 = bolsa8;
  
    }

    public String getBolsa9() {
        return bolsa9;
    }

    public void setBolsa9(String bolsa9) {
        this.bolsa9 = bolsa9;
    
    }

    public String getMotivoDoCancelamento() {
        return motivoDoCancelamento;
    }

    public void setMotivoDoCancelamento(String motivoDoCancelamento) {
        this.motivoDoCancelamento = motivoDoCancelamento;
    
    }

    public Float getPatrimonioLiquido() {
        return patrimonioLiquido;
    }

    public void setPatrimonioLiquido(Float patrimonioLiquido) {
        this.patrimonioLiquido = patrimonioLiquido;

    }

    public Date getDataDoPatrimonio() {
        return dataDoPatrimonio;
    }

    public void setDataDoPatrimonio(Date dataDoPatrimonio) {
        this.dataDoPatrimonio = dataDoPatrimonio;
  
    }

    public String getEMail() {
        return eMail;
    }

    public void setEMail(String eMail) {
        this.eMail = eMail;
     
        
    }

    public String getNomeSetorAtividade() {
        return nomeSetorAtividade;
    }

    public void setNomeSetorAtividade(String nomeSetorAtividade) {
        this.nomeSetorAtividade = nomeSetorAtividade;
     
    }

    public Date getDataDaAcao() {
        return dataDaAcao;
    }

    public void setDataDaAcao(Date dataDaAcao) {
        this.dataDaAcao = dataDaAcao;
    
    }

    public String getTipoNegocio1() {
        return tipoNegocio1;
    }

    public void setTipoNegocio1(String tipoNegocio1) {
        this.tipoNegocio1 = tipoNegocio1;
    
    }

    public String getTipoNegocio2() {
        return tipoNegocio2;
    }

    public void setTipoNegocio2(String tipoNegocio2) {
        this.tipoNegocio2 = tipoNegocio2;
     
    }

    public String getTipoNegocio3() {
        return tipoNegocio3;
    }

    public void setTipoNegocio3(String tipoNegocio3) {
        this.tipoNegocio3 = tipoNegocio3;
     
    }

    public String getTipoNegocio4() {
        return tipoNegocio4;
    }

    public void setTipoNegocio4(String tipoNegocio4) {
        this.tipoNegocio4 = tipoNegocio4;
  
    }

    public String getTipoNegocio5() {
        return tipoNegocio5;
    }

    public void setTipoNegocio5(String tipoNegocio5) {
        this.tipoNegocio5 = tipoNegocio5;
     
    }

    public String getTipoNegocio6() {
        return tipoNegocio6;
    }

    public void setTipoNegocio6(String tipoNegocio6) {
        this.tipoNegocio6 = tipoNegocio6;

    }

    public String getTipoMercado1() {
        return tipoMercado1;
    }

    public void setTipoMercado1(String tipoMercado1) {
        this.tipoMercado1 = tipoMercado1;
  
    }

    public String getTipoMercado2() {
        return tipoMercado2;
    }

    public void setTipoMercado2(String tipoMercado2) {
        this.tipoMercado2 = tipoMercado2;
 
    }

    public String getTipoMercado3() {
        return tipoMercado3;
    }

    public void setTipoMercado3(String tipoMercado3) {
        this.tipoMercado3 = tipoMercado3;

    }

    public String getTipoMercado4() {
        return tipoMercado4;
    }

    public void setTipoMercado4(String tipoMercado4) {
        this.tipoMercado4 = tipoMercado4;
    }

    public String getTipoMercado5() {
        return tipoMercado5;
    }

    public void setTipoMercado5(String tipoMercado5) {
        this.tipoMercado5 = tipoMercado5;

    }

    public String getTipoMercado6() {
        return tipoMercado6;
    }

    public void setTipoMercado6(String tipoMercado6) {
        this.tipoMercado6 = tipoMercado6;
    }
   
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEmpresa != null ? idEmpresa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cia)) {
            return false;
        }
        Cia other = (Cia) object;
        if ((this.idEmpresa == null && other.idEmpresa != null) || (this.idEmpresa != null && !this.idEmpresa.equals(other.idEmpresa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "viagem.CIAS[ idEmpresa=" + idEmpresa + " ]";
    }

    public Collection<PapelBovespa> getPapelBovespaCollection() {
        return papelBovespaCollection;
    }

    public void setPapelBovespaCollection(Collection<PapelBovespa> papelBovespaCollection) {
        this.papelBovespaCollection = papelBovespaCollection;
    }

   
  
    
}