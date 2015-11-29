/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.ProxVersao;

import java.util.Date;

/**
 *
 * @author erico
 */
public interface ComposicaoCapitalSocial {
 
    public Long getIdComposicao() ;

    public void setIdComposicao(Long idComposicao) ;

    public String getFkEmpresa() ;

    public void setFkEmpresa(String fkEmpresa) ;

    public Date getDataReferencia() ;

    public void setDataReferencia(Date dataReferencia) ;

    public long getNumeroIdentificacaoComposicaoCapitalSocial() ;

    public void setNumeroIdentificacaoComposicaoCapitalSocial(long numeroIdentificacaoComposicaoCapitalSocial) ;

    public long getQuantidadeAcaoOrdinariaCapitalIntegralizado() ;

    public void setQuantidadeAcaoOrdinariaCapitalIntegralizado(long quantidadeAcaoOrdinariaCapitalIntegralizado) ;

    public long getQuantidadeAcaoPreferencialCapitalIntegralizado() ;

    public void setQuantidadeAcaoPreferencialCapitalIntegralizado(long quantidadeAcaoPreferencialCapitalIntegralizado) ;

    public long getQuantidadeTotalAcaoCapitalIntegralizado() ;

    public void setQuantidadeTotalAcaoCapitalIntegralizado(long quantidadeTotalAcaoCapitalIntegralizado) ;

    public long getQuantidadeAcaoOrdinariaTesouraria() ;

    public void setQuantidadeAcaoOrdinariaTesouraria(long quantidadeAcaoOrdinariaTesouraria) ;

    public long getQuantidadeAcaoPreferencialTesouraria() ;

    public void setQuantidadeAcaoPreferencialTesouraria(long quantidadeAcaoPreferencialTesouraria) ;

    public long getQuantidadeTotalAcaoTesouraria() ;

    public void setQuantidadeTotalAcaoTesouraria(long quantidadeTotalAcaoTesouraria) ;

    public Integer getVersaoDocumento() ;
    
    public void setVersaoDocumento(Integer versaoDocumento) ;

    public ControleArquivosInseridos getFkArquivo() ;
            
    public void setFkArquivo(ControleArquivosInseridos fkArquivo) ;
    
}
