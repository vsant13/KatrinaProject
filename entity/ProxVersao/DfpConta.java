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
@Table(name = "DFP_CONTA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DfpConta.findAll", query = "SELECT d FROM DfpConta d"),
    @NamedQuery(name = "DfpConta.findByIdConta", query = "SELECT d FROM DfpConta d WHERE d.idConta = :idConta"),
    @NamedQuery(name = "DfpConta.findByFkEmpresa", query = "SELECT d FROM DfpConta d WHERE d.fkEmpresa = :fkEmpresa"),
    @NamedQuery(name = "DfpConta.findByNumeroConta", query = "SELECT d FROM DfpConta d WHERE d.numeroConta = :numeroConta"),
    @NamedQuery(name = "DfpConta.findByDescricaoConta", query = "SELECT d FROM DfpConta d WHERE d.descricaoConta = :descricaoConta"),
    @NamedQuery(name = "DfpConta.findByValorConta1", query = "SELECT d FROM DfpConta d WHERE d.valorConta1 = :valorConta1"),
    @NamedQuery(name = "DfpConta.findByValorConta2", query = "SELECT d FROM DfpConta d WHERE d.valorConta2 = :valorConta2"),
    @NamedQuery(name = "DfpConta.findByValorConta3", query = "SELECT d FROM DfpConta d WHERE d.valorConta3 = :valorConta3"),
    @NamedQuery(name = "DfpConta.findByValorConta4", query = "SELECT d FROM DfpConta d WHERE d.valorConta4 = :valorConta4"),
    @NamedQuery(name = "DfpConta.findByValorConta5", query = "SELECT d FROM DfpConta d WHERE d.valorConta5 = :valorConta5"),
    @NamedQuery(name = "DfpConta.findByValorConta6", query = "SELECT d FROM DfpConta d WHERE d.valorConta6 = :valorConta6"),
    @NamedQuery(name = "DfpConta.findByValorConta7", query = "SELECT d FROM DfpConta d WHERE d.valorConta7 = :valorConta7"),
    @NamedQuery(name = "DfpConta.findByValorConta8", query = "SELECT d FROM DfpConta d WHERE d.valorConta8 = :valorConta8"),
    @NamedQuery(name = "DfpConta.findByValorConta9", query = "SELECT d FROM DfpConta d WHERE d.valorConta9 = :valorConta9"),
    @NamedQuery(name = "DfpConta.findByValorConta10", query = "SELECT d FROM DfpConta d WHERE d.valorConta10 = :valorConta10"),
    @NamedQuery(name = "DfpConta.findByValorConta11", query = "SELECT d FROM DfpConta d WHERE d.valorConta11 = :valorConta11"),
    @NamedQuery(name = "DfpConta.findByValorConta12", query = "SELECT d FROM DfpConta d WHERE d.valorConta12 = :valorConta12"),
    @NamedQuery(name = "DfpConta.findByVersaoDocumento", query = "SELECT d FROM DfpConta d WHERE d.versaoDocumento = :versaoDocumento"),
    @NamedQuery(name = "DfpConta.findByDataInicioRefereciaPrincipal", query = "SELECT d FROM DfpConta d WHERE d.dataInicioRefereciaPrincipal = :dataInicioRefereciaPrincipal"),
    @NamedQuery(name = "DfpConta.findByDataFimRefereciaPrincipal", query = "SELECT d FROM DfpConta d WHERE d.dataFimRefereciaPrincipal = :dataFimRefereciaPrincipal")})
public class DfpConta implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_conta")
    private Long idConta;
    @Column(name = "fk_empresa")
    private String fkEmpresa;
    @Column(name = "numero_conta")
    private String numeroConta;
    @Column(name = "descricao_conta")
    private String descricaoConta;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor_conta_1")
    private BigDecimal valorConta1;
    @Column(name = "valor_conta_2")
    private BigDecimal valorConta2;
    @Column(name = "valor_conta_3")
    private BigDecimal valorConta3;
    @Column(name = "valor_conta_4")
    private BigDecimal valorConta4;
    @Column(name = "valor_conta_5")
    private BigDecimal valorConta5;
    @Column(name = "valor_conta_6")
    private BigDecimal valorConta6;
    @Column(name = "valor_conta_7")
    private BigDecimal valorConta7;
    @Column(name = "valor_conta_8")
    private BigDecimal valorConta8;
    @Column(name = "valor_conta_9")
    private BigDecimal valorConta9;
    @Column(name = "valor_conta_10")
    private BigDecimal valorConta10;
    @Column(name = "valor_conta_11")
    private BigDecimal valorConta11;
    @Column(name = "valor_conta_12")
    private BigDecimal valorConta12;
    @Column(name = "versao_documento")
    private Integer versaoDocumento;
    @Column(name = "data_inicio_referecia_principal")
    @Temporal(TemporalType.DATE)
    private Date dataInicioRefereciaPrincipal;
    @Column(name = "data_fim_referecia_principal")
    @Temporal(TemporalType.DATE)
    private Date dataFimRefereciaPrincipal;
    @JoinColumn(name = "fk_arquivo", referencedColumnName = "id_arquivo")
    @ManyToOne(optional = false)
    private ControleArquivosInseridos fkArquivo;

    public DfpConta() {
    }

    public DfpConta(Long idConta) {
        this.idConta = idConta;
    }

    public Long getIdConta() {
        return idConta;
    }

    public void setIdConta(Long idConta) {
        this.idConta = idConta;
    }

    public String getFkEmpresa() {
        return fkEmpresa;
    }

    public void setFkEmpresa(String fkEmpresa) {
        this.fkEmpresa = fkEmpresa;
    }

    public String getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(String numeroConta) {
        this.numeroConta = numeroConta;
    }

    public String getDescricaoConta() {
        return descricaoConta;
    }

    public void setDescricaoConta(String descricaoConta) {
        this.descricaoConta = descricaoConta;
    }

    public BigDecimal getValorConta1() {
        return valorConta1;
    }

    public void setValorConta1(BigDecimal valorConta1) {
        this.valorConta1 = valorConta1;
    }

    public BigDecimal getValorConta2() {
        return valorConta2;
    }

    public void setValorConta2(BigDecimal valorConta2) {
        this.valorConta2 = valorConta2;
    }

    public BigDecimal getValorConta3() {
        return valorConta3;
    }

    public void setValorConta3(BigDecimal valorConta3) {
        this.valorConta3 = valorConta3;
    }

    public BigDecimal getValorConta4() {
        return valorConta4;
    }

    public void setValorConta4(BigDecimal valorConta4) {
        this.valorConta4 = valorConta4;
    }

    public BigDecimal getValorConta5() {
        return valorConta5;
    }

    public void setValorConta5(BigDecimal valorConta5) {
        this.valorConta5 = valorConta5;
    }

    public BigDecimal getValorConta6() {
        return valorConta6;
    }

    public void setValorConta6(BigDecimal valorConta6) {
        this.valorConta6 = valorConta6;
    }

    public BigDecimal getValorConta7() {
        return valorConta7;
    }

    public void setValorConta7(BigDecimal valorConta7) {
        this.valorConta7 = valorConta7;
    }

    public BigDecimal getValorConta8() {
        return valorConta8;
    }

    public void setValorConta8(BigDecimal valorConta8) {
        this.valorConta8 = valorConta8;
    }

    public BigDecimal getValorConta9() {
        return valorConta9;
    }

    public void setValorConta9(BigDecimal valorConta9) {
        this.valorConta9 = valorConta9;
    }

    public BigDecimal getValorConta10() {
        return valorConta10;
    }

    public void setValorConta10(BigDecimal valorConta10) {
        this.valorConta10 = valorConta10;
    }

    public BigDecimal getValorConta11() {
        return valorConta11;
    }

    public void setValorConta11(BigDecimal valorConta11) {
        this.valorConta11 = valorConta11;
    }

    public BigDecimal getValorConta12() {
        return valorConta12;
    }

    public void setValorConta12(BigDecimal valorConta12) {
        this.valorConta12 = valorConta12;
    }

    public Integer getVersaoDocumento() {
        return versaoDocumento;
    }

    public void setVersaoDocumento(Integer versaoDocumento) {
        this.versaoDocumento = versaoDocumento;
    }

    public Date getDataInicioRefereciaPrincipal() {
        return dataInicioRefereciaPrincipal;
    }

    public void setDataInicioRefereciaPrincipal(Date dataInicioRefereciaPrincipal) {
        this.dataInicioRefereciaPrincipal = dataInicioRefereciaPrincipal;
    }

    public Date getDataFimRefereciaPrincipal() {
        return dataFimRefereciaPrincipal;
    }

    public void setDataFimRefereciaPrincipal(Date dataFimRefereciaPrincipal) {
        this.dataFimRefereciaPrincipal = dataFimRefereciaPrincipal;
    }

    public ControleArquivosInseridos getFkArquivo() {
        return fkArquivo;
    }

    public void setFkArquivo(ControleArquivosInseridos fkArquivo) {
        this.fkArquivo = fkArquivo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idConta != null ? idConta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DfpConta)) {
            return false;
        }
        DfpConta other = (DfpConta) object;
        if ((this.idConta == null && other.idConta != null) || (this.idConta != null && !this.idConta.equals(other.idConta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.DfpConta[ idConta=" + idConta + " ]";
    }
    
}
