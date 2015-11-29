/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.ProxVersao;

import entity.Cia;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
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
@Table(name = "PAPEL_BOVESPA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PapelBovespa.findAll", query = "SELECT p FROM PapelBovespa p"),
    @NamedQuery(name = "PapelBovespa.findByIdPapel", query = "SELECT p FROM PapelBovespa p WHERE p.idPapel = :idPapel"),
    @NamedQuery(name = "PapelBovespa.findByEspeci", query = "SELECT p FROM PapelBovespa p WHERE p.especi = :especi"),
    @NamedQuery(name = "PapelBovespa.findByData", query = "SELECT p FROM PapelBovespa p WHERE p.data = :data"),
    @NamedQuery(name = "PapelBovespa.findByCodnegTipo", query = "SELECT p FROM PapelBovespa p WHERE p.codnegTipo = :codnegTipo"),
    @NamedQuery(name = "PapelBovespa.findByPrazot", query = "SELECT p FROM PapelBovespa p WHERE p.prazot = :prazot"),
    @NamedQuery(name = "PapelBovespa.findByModref", query = "SELECT p FROM PapelBovespa p WHERE p.modref = :modref"),
    @NamedQuery(name = "PapelBovespa.findByPreabe", query = "SELECT p FROM PapelBovespa p WHERE p.preabe = :preabe"),
    @NamedQuery(name = "PapelBovespa.findByPremax", query = "SELECT p FROM PapelBovespa p WHERE p.premax = :premax"),
    @NamedQuery(name = "PapelBovespa.findByPremin", query = "SELECT p FROM PapelBovespa p WHERE p.premin = :premin"),
    @NamedQuery(name = "PapelBovespa.findByPremed", query = "SELECT p FROM PapelBovespa p WHERE p.premed = :premed"),
    @NamedQuery(name = "PapelBovespa.findByPreult", query = "SELECT p FROM PapelBovespa p WHERE p.preult = :preult"),
    @NamedQuery(name = "PapelBovespa.findByPreofc", query = "SELECT p FROM PapelBovespa p WHERE p.preofc = :preofc"),
    @NamedQuery(name = "PapelBovespa.findByPreofv", query = "SELECT p FROM PapelBovespa p WHERE p.preofv = :preofv"),
    @NamedQuery(name = "PapelBovespa.findByTotneg", query = "SELECT p FROM PapelBovespa p WHERE p.totneg = :totneg"),
    @NamedQuery(name = "PapelBovespa.findByQuatot", query = "SELECT p FROM PapelBovespa p WHERE p.quatot = :quatot"),
    @NamedQuery(name = "PapelBovespa.findByVoltot", query = "SELECT p FROM PapelBovespa p WHERE p.voltot = :voltot"),
    @NamedQuery(name = "PapelBovespa.findByPreexe", query = "SELECT p FROM PapelBovespa p WHERE p.preexe = :preexe"),
    @NamedQuery(name = "PapelBovespa.findByDatven", query = "SELECT p FROM PapelBovespa p WHERE p.datven = :datven"),
    @NamedQuery(name = "PapelBovespa.findByFatcon", query = "SELECT p FROM PapelBovespa p WHERE p.fatcon = :fatcon"),
    @NamedQuery(name = "PapelBovespa.findByPtoexe", query = "SELECT p FROM PapelBovespa p WHERE p.ptoexe = :ptoexe"),
    @NamedQuery(name = "PapelBovespa.findByCodisiPais", query = "SELECT p FROM PapelBovespa p WHERE p.codisiPais = :codisiPais"),
    @NamedQuery(name = "PapelBovespa.findByCodisiTipo", query = "SELECT p FROM PapelBovespa p WHERE p.codisiTipo = :codisiTipo"),
    @NamedQuery(name = "PapelBovespa.findByCodisiIndem", query = "SELECT p FROM PapelBovespa p WHERE p.codisiIndem = :codisiIndem"),
    @NamedQuery(name = "PapelBovespa.findByCodisiDigcon", query = "SELECT p FROM PapelBovespa p WHERE p.codisiDigcon = :codisiDigcon"),
    @NamedQuery(name = "PapelBovespa.findByDismes", query = "SELECT p FROM PapelBovespa p WHERE p.dismes = :dismes")})
public class PapelBovespa implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_papel")
    private Long idPapel;
    @Column(name = "especi")
    private String especi;
    @Column(name = "data")
    @Temporal(TemporalType.DATE)
    private Date data;
    @Column(name = "codneg_tipo")
    private String codnegTipo;
    @Column(name = "prazot")
    private String prazot;
    @Column(name = "modref")
    private String modref;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "preabe")
    private BigDecimal preabe;
    @Column(name = "premax")
    private BigDecimal premax;
    @Column(name = "premin")
    private BigDecimal premin;
    @Column(name = "premed")
    private BigDecimal premed;
    @Column(name = "preult")
    private BigDecimal preult;
    @Column(name = "preofc")
    private BigDecimal preofc;
    @Column(name = "preofv")
    private BigDecimal preofv;
    @Column(name = "totneg")
    private Integer totneg;
    @Column(name = "quatot")
    private BigInteger quatot;
    @Column(name = "voltot")
    private BigDecimal voltot;
    @Column(name = "preexe")
    private BigDecimal preexe;
    @Column(name = "datven")
    @Temporal(TemporalType.DATE)
    private Date datven;
    @Column(name = "fatcon")
    private Integer fatcon;
    @Column(name = "ptoexe")
    private Float ptoexe;
    @Column(name = "codisi_pais")
    private String codisiPais;
    @Column(name = "codisi_tipo")
    private String codisiTipo;
    @Column(name = "codisi_indem")
    private String codisiIndem;
    @Column(name = "codisi_digcon")
    private Integer codisiDigcon;
    @Column(name = "dismes")
    private Integer dismes;
    @JoinColumn(name = "fk_arquivo", referencedColumnName = "id_arquivo")
    @ManyToOne
    private ControleArquivosInseridos fkArquivo;
    @JoinColumn(name = "fk_codneg_nome", referencedColumnName = "id_empresa")
    @ManyToOne
    private Cia fkCodnegNome;
    @JoinColumn(name = "fk_codbdi", referencedColumnName = "id_codbdi")
    @ManyToOne
    private Codbdi fkCodbdi;
    @JoinColumn(name = "fk_tpmerc", referencedColumnName = "id_tpmerc")
    @ManyToOne
    private Tpmerc fkTpmerc;
    @JoinColumn(name = "fk_indopc", referencedColumnName = "id_indopc")
    @ManyToOne
    private Indopc fkIndopc;

    public PapelBovespa() {
    }

    public PapelBovespa(Long idPapel) {
        this.idPapel = idPapel;
    }

    public Long getIdPapel() {
        return idPapel;
    }

    public void setIdPapel(Long idPapel) {
        this.idPapel = idPapel;
    }

    public String getEspeci() {
        return especi;
    }

    public void setEspeci(String especi) {
        this.especi = especi;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getCodnegTipo() {
        return codnegTipo;
    }

    public void setCodnegTipo(String codnegTipo) {
        this.codnegTipo = codnegTipo;
    }

    public String getPrazot() {
        return prazot;
    }

    public void setPrazot(String prazot) {
        this.prazot = prazot;
    }

    public String getModref() {
        return modref;
    }

    public void setModref(String modref) {
        this.modref = modref;
    }

    public BigDecimal getPreabe() {
        return preabe;
    }

    public void setPreabe(BigDecimal preabe) {
        this.preabe = preabe;
    }

    public BigDecimal getPremax() {
        return premax;
    }

    public void setPremax(BigDecimal premax) {
        this.premax = premax;
    }

    public BigDecimal getPremin() {
        return premin;
    }

    public void setPremin(BigDecimal premin) {
        this.premin = premin;
    }

    public BigDecimal getPremed() {
        return premed;
    }

    public void setPremed(BigDecimal premed) {
        this.premed = premed;
    }

    public BigDecimal getPreult() {
        return preult;
    }

    public void setPreult(BigDecimal preult) {
        this.preult = preult;
    }

    public BigDecimal getPreofc() {
        return preofc;
    }

    public void setPreofc(BigDecimal preofc) {
        this.preofc = preofc;
    }

    public BigDecimal getPreofv() {
        return preofv;
    }

    public void setPreofv(BigDecimal preofv) {
        this.preofv = preofv;
    }

    public Integer getTotneg() {
        return totneg;
    }

    public void setTotneg(Integer totneg) {
        this.totneg = totneg;
    }

    public BigInteger getQuatot() {
        return quatot;
    }

    public void setQuatot(BigInteger quatot) {
        this.quatot = quatot;
    }

    public BigDecimal getVoltot() {
        return voltot;
    }

    public void setVoltot(BigDecimal voltot) {
        this.voltot = voltot;
    }

    public BigDecimal getPreexe() {
        return preexe;
    }

    public void setPreexe(BigDecimal preexe) {
        this.preexe = preexe;
    }

    public Date getDatven() {
        return datven;
    }

    public void setDatven(Date datven) {
        this.datven = datven;
    }

    public Integer getFatcon() {
        return fatcon;
    }

    public void setFatcon(Integer fatcon) {
        this.fatcon = fatcon;
    }

    public Float getPtoexe() {
        return ptoexe;
    }

    public void setPtoexe(Float ptoexe) {
        this.ptoexe = ptoexe;
    }

    public String getCodisiPais() {
        return codisiPais;
    }

    public void setCodisiPais(String codisiPais) {
        this.codisiPais = codisiPais;
    }

    public String getCodisiTipo() {
        return codisiTipo;
    }

    public void setCodisiTipo(String codisiTipo) {
        this.codisiTipo = codisiTipo;
    }

    public String getCodisiIndem() {
        return codisiIndem;
    }

    public void setCodisiIndem(String codisiIndem) {
        this.codisiIndem = codisiIndem;
    }

    public Integer getCodisiDigcon() {
        return codisiDigcon;
    }

    public void setCodisiDigcon(Integer codisiDigcon) {
        this.codisiDigcon = codisiDigcon;
    }

    public Integer getDismes() {
        return dismes;
    }

    public void setDismes(Integer dismes) {
        this.dismes = dismes;
    }

    public ControleArquivosInseridos getFkArquivo() {
        return fkArquivo;
    }

    public void setFkArquivo(ControleArquivosInseridos fkArquivo) {
        this.fkArquivo = fkArquivo;
    }

    public Cia getFkCodnegNome() {
        return fkCodnegNome;
    }

    public void setFkCodnegNome(Cia fkCodnegNome) {
        this.fkCodnegNome = fkCodnegNome;
    }

    public Codbdi getFkCodbdi() {
        return fkCodbdi;
    }

    public void setFkCodbdi(Codbdi fkCodbdi) {
        this.fkCodbdi = fkCodbdi;
    }

    public Tpmerc getFkTpmerc() {
        return fkTpmerc;
    }

    public void setFkTpmerc(Tpmerc fkTpmerc) {
        this.fkTpmerc = fkTpmerc;
    }

    public Indopc getFkIndopc() {
        return fkIndopc;
    }

    public void setFkIndopc(Indopc fkIndopc) {
        this.fkIndopc = fkIndopc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPapel != null ? idPapel.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PapelBovespa)) {
            return false;
        }
        PapelBovespa other = (PapelBovespa) object;
        if ((this.idPapel == null && other.idPapel != null) || (this.idPapel != null && !this.idPapel.equals(other.idPapel))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.PapelBovespa[ idPapel=" + idPapel + " ]";
    }
    
}
