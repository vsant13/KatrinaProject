/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.ProxVersao;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author erico
 */
public interface Proventos {
    

    public Long getIdProvento() ;

    public void setIdProvento(Long idProvento);

    public String getFkEmpresa() ;

    public void setFkEmpresa(String fkEmpresa) ;

    public String getTipoProvento() ;

    public void setTipoProvento(String tipoProvento);

    public Date getDataAprovacao() ;

    public void setDataAprovacao(Date dataAprovacao) ;

    public Date getDataInicioPagamento() ;

    public void setDataInicioPagamento(Date dataInicioPagamento) ;

    public String getDescricaoCodEspecie() ;

    public void setDescricaoCodEspecie(String descricaoCodEspecie) ;

    public String getSiglaCodEspecie() ;

    public void setSiglaCodEspecie(String siglaCodEspecie) ;

    public String getDescricaoCodClassePreferencial();

    public void setDescricaoCodClassePreferencial(String descricaoCodClassePreferencial) ;

    public String getSiglaCodClassePreferencial();

    public void setSiglaCodClassePreferencial(String siglaCodClassePreferencial) ;

    public BigDecimal getValorDoProventoPorAcao() ;

    public void setValorDoProventoPorAcao(BigDecimal valorDoProventoPorAcao) ;
    
    public Integer getVersaoDocumento() ;

    public void setVersaoDocumento(Integer versaoDocumento);

    public ControleArquivosInseridos getFkArquivo() ;
    
}
