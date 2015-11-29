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
@Table(name = "TES_PRECO_TAXA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TesPrecoTaxa.findAll", query = "SELECT t FROM TesPrecoTaxa t"),
    @NamedQuery(name = "TesPrecoTaxa.findByIdRegistro", query = "SELECT t FROM TesPrecoTaxa t WHERE t.idRegistro = :idRegistro"),
    @NamedQuery(name = "TesPrecoTaxa.findByDataVencimento", query = "SELECT t FROM TesPrecoTaxa t WHERE t.dataVencimento = :dataVencimento"),
    @NamedQuery(name = "TesPrecoTaxa.findByTaxaCompra", query = "SELECT t FROM TesPrecoTaxa t WHERE t.taxaCompra = :taxaCompra"),
    @NamedQuery(name = "TesPrecoTaxa.findByTaxaVenda", query = "SELECT t FROM TesPrecoTaxa t WHERE t.taxaVenda = :taxaVenda"),
    @NamedQuery(name = "TesPrecoTaxa.findByPrecoDiaCompra", query = "SELECT t FROM TesPrecoTaxa t WHERE t.precoDiaCompra = :precoDiaCompra"),
    @NamedQuery(name = "TesPrecoTaxa.findByPrecoDiaVenda", query = "SELECT t FROM TesPrecoTaxa t WHERE t.precoDiaVenda = :precoDiaVenda"),
    @NamedQuery(name = "TesPrecoTaxa.findByDataReferencia", query = "SELECT t FROM TesPrecoTaxa t WHERE t.dataReferencia = :dataReferencia")})
public class TesPrecoTaxa implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_registro")
    private Integer idRegistro;
    @Column(name = "data_vencimento")
    @Temporal(TemporalType.DATE)
    private Date dataVencimento;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "taxa_compra")
    private BigDecimal taxaCompra;
    @Column(name = "taxa_venda")
    private BigDecimal taxaVenda;
    @Column(name = "preco_dia_compra")
    private BigDecimal precoDiaCompra;
    @Column(name = "preco_dia_venda")
    private BigDecimal precoDiaVenda;
    @Column(name = "data_referencia")
    @Temporal(TemporalType.DATE)
    private Date dataReferencia;
    @JoinColumn(name = "fk_titulo", referencedColumnName = "id_titulo")
    @ManyToOne
    private TesTitulos fkTitulo;

    public TesPrecoTaxa() {
    }

    public TesPrecoTaxa(Integer idRegistro) {
        this.idRegistro = idRegistro;
    }

    public Integer getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(Integer idRegistro) {
        this.idRegistro = idRegistro;
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public BigDecimal getTaxaCompra() {
        return taxaCompra;
    }

    public void setTaxaCompra(BigDecimal taxaCompra) {
        this.taxaCompra = taxaCompra;
    }

    public BigDecimal getTaxaVenda() {
        return taxaVenda;
    }

    public void setTaxaVenda(BigDecimal taxaVenda) {
        this.taxaVenda = taxaVenda;
    }

    public BigDecimal getPrecoDiaCompra() {
        return precoDiaCompra;
    }

    public void setPrecoDiaCompra(BigDecimal precoDiaCompra) {
        this.precoDiaCompra = precoDiaCompra;
    }

    public BigDecimal getPrecoDiaVenda() {
        return precoDiaVenda;
    }

    public void setPrecoDiaVenda(BigDecimal precoDiaVenda) {
        this.precoDiaVenda = precoDiaVenda;
    }

    public Date getDataReferencia() {
        return dataReferencia;
    }

    public void setDataReferencia(Date dataReferencia) {
        this.dataReferencia = dataReferencia;
    }

    public TesTitulos getFkTitulo() {
        return fkTitulo;
    }

    public void setFkTitulo(TesTitulos fkTitulo) {
        this.fkTitulo = fkTitulo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRegistro != null ? idRegistro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TesPrecoTaxa)) {
            return false;
        }
        TesPrecoTaxa other = (TesPrecoTaxa) object;
        if ((this.idRegistro == null && other.idRegistro != null) || (this.idRegistro != null && !this.idRegistro.equals(other.idRegistro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.TesPrecoTaxa[ idRegistro=" + idRegistro + " ]";
    }
    
}
