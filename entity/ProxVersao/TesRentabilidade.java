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
@Table(name = "TES_RENTABILIDADE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TesRentabilidade.findAll", query = "SELECT t FROM TesRentabilidade t"),
    @NamedQuery(name = "TesRentabilidade.findByIdRegistro", query = "SELECT t FROM TesRentabilidade t WHERE t.idRegistro = :idRegistro"),
    @NamedQuery(name = "TesRentabilidade.findByDataVencimento", query = "SELECT t FROM TesRentabilidade t WHERE t.dataVencimento = :dataVencimento"),
    @NamedQuery(name = "TesRentabilidade.findByMesAnt", query = "SELECT t FROM TesRentabilidade t WHERE t.mesAnt = :mesAnt"),
    @NamedQuery(name = "TesRentabilidade.findByUlt30", query = "SELECT t FROM TesRentabilidade t WHERE t.ult30 = :ult30"),
    @NamedQuery(name = "TesRentabilidade.findByMeses12", query = "SELECT t FROM TesRentabilidade t WHERE t.meses12 = :meses12"),
    @NamedQuery(name = "TesRentabilidade.findByCompraDiaAno", query = "SELECT t FROM TesRentabilidade t WHERE t.compraDiaAno = :compraDiaAno"),
    @NamedQuery(name = "TesRentabilidade.findByVendaDiaAno", query = "SELECT t FROM TesRentabilidade t WHERE t.vendaDiaAno = :vendaDiaAno"),
    @NamedQuery(name = "TesRentabilidade.findByDataReferencia", query = "SELECT t FROM TesRentabilidade t WHERE t.dataReferencia = :dataReferencia")})
public class TesRentabilidade implements Serializable {
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
    @Column(name = "mes_ant")
    private BigDecimal mesAnt;
    @Column(name = "ult_30")
    private BigDecimal ult30;
    @Column(name = "meses_12")
    private BigDecimal meses12;
    @Column(name = "compra_dia_ano")
    private BigDecimal compraDiaAno;
    @Column(name = "venda_dia_ano")
    private BigDecimal vendaDiaAno;
    @Column(name = "data_referencia")
    @Temporal(TemporalType.DATE)
    private Date dataReferencia;
    @JoinColumn(name = "fk_titulo", referencedColumnName = "id_titulo")
    @ManyToOne
    private TesTitulos fkTitulo;

    public TesRentabilidade() {
    }

    public TesRentabilidade(Integer idRegistro) {
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

    public BigDecimal getMesAnt() {
        return mesAnt;
    }

    public void setMesAnt(BigDecimal mesAnt) {
        this.mesAnt = mesAnt;
    }

    public BigDecimal getUlt30() {
        return ult30;
    }

    public void setUlt30(BigDecimal ult30) {
        this.ult30 = ult30;
    }

    public BigDecimal getMeses12() {
        return meses12;
    }

    public void setMeses12(BigDecimal meses12) {
        this.meses12 = meses12;
    }

    public BigDecimal getCompraDiaAno() {
        return compraDiaAno;
    }

    public void setCompraDiaAno(BigDecimal compraDiaAno) {
        this.compraDiaAno = compraDiaAno;
    }

    public BigDecimal getVendaDiaAno() {
        return vendaDiaAno;
    }

    public void setVendaDiaAno(BigDecimal vendaDiaAno) {
        this.vendaDiaAno = vendaDiaAno;
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
        if (!(object instanceof TesRentabilidade)) {
            return false;
        }
        TesRentabilidade other = (TesRentabilidade) object;
        if ((this.idRegistro == null && other.idRegistro != null) || (this.idRegistro != null && !this.idRegistro.equals(other.idRegistro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.TesRentabilidade[ idRegistro=" + idRegistro + " ]";
    }
    
}
