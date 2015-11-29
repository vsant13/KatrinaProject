/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao.ProxVersao;

import ConexaoBD.ConnectionDB;
import ConexaoBD.FuncoesDB;
import ConexaoBD.Query;
import Erros.Erro;
import entity.ProxVersao.ComposicaoCapitalSocial;
import entity.ProxVersao.ControleArquivosInseridos;
import entity.ProxVersao.DfpComposicaoCapitalSocial;
import entity.ProxVersao.ItrComposicaoCapitalSocial;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author erico
 * 
 * INCOMPLETO , DEIXAR PARA VERSÃO 2.0
 * 
 */
public class ComposicaoCapitalSocialDao {
    

      
    public String  table = "_COMPOSICAO_CAPITAL_SOCIAL";
    private FuncoesDB funcao = new FuncoesDB();
    private ComposicaoCapitalSocial ComposicaoCapitalSocial;
    private boolean isDFP;
    private Query query;
    private PreparedStatement psQuery =null;
    
    
    public ComposicaoCapitalSocialDao(boolean isDFP){
        this.isDFP=isDFP;
        if(isDFP)
        {
            table= "DFP"+table;
            this.ComposicaoCapitalSocial=null;
            isDFP=true;
        }
        else
        {
            table="ITR"+table;
            this.ComposicaoCapitalSocial=null;
            isDFP=false;
        }
    }
   
    public ComposicaoCapitalSocialDao(DfpComposicaoCapitalSocial dfpComposicaoCapitalSocial) throws ComposicaoCapitalSocialException{
        this.ComposicaoCapitalSocial = (ComposicaoCapitalSocial) dfpComposicaoCapitalSocial;
        isDFP=true;
    }
    public ComposicaoCapitalSocialDao(ItrComposicaoCapitalSocial itrComposicaoCapitalSocial) throws ComposicaoCapitalSocialException{
        this.ComposicaoCapitalSocial = (ComposicaoCapitalSocial) itrComposicaoCapitalSocial;
        isDFP=false;
    }
    
    
     public ComposicaoCapitalSocial getFirst(){
        try {
                 if(ComposicaoCapitalSocial==null)
                 {
                    query = new Query();
                    query.addSELECTALL();
                    query.addFROM();
                    query.addtable(table);


                        funcao.Select(query.getQuery());
                            if(ConnectionDB.result.next())
                                   return setData();   
                }
             
        else
        {
           return analiza_obj();
        }
        } catch (SQLException ex) {

        } catch (FuncoesDB.FuncoesDBException ex) {

        } catch (ComposicaoCapitalSocialException ex) {
            
        }
        return null;
    }
    public ComposicaoCapitalSocial getFirst(Query byQuery){
        
        try {
            funcao.Select(byQuery.getQuery());
                if(ConnectionDB.result.next())
                       return setData();
                
        } catch (SQLException ex) {
            
        } catch (FuncoesDB.FuncoesDBException ex) { 
            
        }
        return null;
    }
    public ComposicaoCapitalSocial getNext(){
        try {
            if(ConnectionDB.result.next())
            {
                return setData();
            }
            else    
            {
                System.out.println("Erro");
            }
        } catch (SQLException ex) {
            System.out.println("Erro de uso da getnext");
        }
        return null;
      }
    public List<ComposicaoCapitalSocial> getList(){
        List<ComposicaoCapitalSocial> list = new ArrayList<ComposicaoCapitalSocial>();
        query = new Query();
        query.addSELECTALL();
        query.addFROM();
        query.addtable(table);
        //query=iscolsnull(query);
       
        try {
             funcao.Select(query.getQuery());
                while(ConnectionDB.result.next())
                        list.add(setData());
                return list;
        } catch (SQLException ex) {
            
        } catch (FuncoesDB.FuncoesDBException ex) {
           
        }
        return null; 
    }
     public List<ComposicaoCapitalSocial> getList(String column,String likeValue){
        List<ComposicaoCapitalSocial> list = new ArrayList<ComposicaoCapitalSocial>();
        query = new Query();
        query.addSELECTALL();
        query.addFROM();
        query.addtable(table);
        //query=iscolsnull(query);
        if(!query.getQuery().toLowerCase().contains("where"))
            query.addWHERE();
        query.addsimplecolumn(column);
        query.addLIKE();
        if(!likeValue.contains("%"))
        {
            query.add("%");
            query.addNewLocalValues(1);
        }
        
        
        try {
            psQuery = ConnectionDB.conexao.prepareStatement(query.getQuery());
            psQuery.setString(1, likeValue);
            
            funcao.Select(psQuery);
                while(ConnectionDB.result.next())
                        list.add(setData());
                return list;
        } catch (SQLException ex) {
       
        } catch (FuncoesDB.FuncoesDBException ex) {
           
        }
        return null; 
    }
    public List<ComposicaoCapitalSocial> getList(Query byQuery){
        List<ComposicaoCapitalSocial> list = new ArrayList<ComposicaoCapitalSocial>();
        try {
            funcao.Select(byQuery.getQuery());
                while(ConnectionDB.result.next())
                        list.add(setData());
                return list;
        } catch (SQLException ex) {
            
        } catch (FuncoesDB.FuncoesDBException ex) {
            
        }
        return null; 
    }
    
    
    
    private ComposicaoCapitalSocial analiza_obj() throws ComposicaoCapitalSocialException{
            try {  
                    Query query = new Query(); 
                    query.addSELECTALL();
                    query.addFROM();
                    query.addtable(table);
                    query.addWHERE();
                   
                   int i[]=new  int[61]; 
                   int position=0;
                   
                    if(ComposicaoCapitalSocial.getIdComposicao()!=null){query.addsimplecolumn("id_composicao");query.addLIKE();query.addNewLocalValues(1);query.addAND();position++;i[position]=1;}
                    if(ComposicaoCapitalSocial.getFkEmpresa()!=null){query.addsimplecolumn("fk_empresa");query.addLIKE();query.addNewLocalValues(1);query.addAND();position++;i[position]=2;}
                    if(ComposicaoCapitalSocial.getFkArquivo()!=null){query.addsimplecolumn("fk_arquivo");query.addLIKE();query.addNewLocalValues(1);query.addAND();position++;i[position]=3;}
                    if(ComposicaoCapitalSocial.getDataReferencia()!=null){query.addsimplecolumn("data_referencia");query.addLIKE();query.addNewLocalValues(1);query.addAND();position++;i[position]=4;}
                    if(ComposicaoCapitalSocial.getNumeroIdentificacaoComposicaoCapitalSocial()!=0){query.addsimplecolumn("numero_identificacao_composicao_capital_social");query.addLIKE();query.addNewLocalValues(1);query.addAND();position++;i[position]=5;}
                    if(ComposicaoCapitalSocial.getQuantidadeAcaoOrdinariaCapitalIntegralizado()!=0){query.addsimplecolumn("quantidade_acao_ordinaria_capital_integralizado");query.addLIKE();query.addNewLocalValues(1);query.addAND();position++;i[position]=6;}
                    if(ComposicaoCapitalSocial.getQuantidadeAcaoPreferencialCapitalIntegralizado()!=0){query.addsimplecolumn("quantidade_acao_preferencial_capital_integralizado");query.addLIKE();query.addNewLocalValues(1);query.addAND();position++;i[position]=7;}
                    if(ComposicaoCapitalSocial.getQuantidadeTotalAcaoCapitalIntegralizado()!=0){query.addsimplecolumn("quantidade_total_acao_capital_integralizado");query.addLIKE();query.addNewLocalValues(1);query.addAND();position++;i[position]=8;}
                    if(ComposicaoCapitalSocial.getQuantidadeAcaoOrdinariaTesouraria()!=0){query.addsimplecolumn("quantidade_acao_ordinaria_tesouraria");query.addLIKE();query.addNewLocalValues(1);query.addAND();position++;i[position]=9;}
                    if(ComposicaoCapitalSocial.getQuantidadeAcaoPreferencialTesouraria()!=0){query.addsimplecolumn("quantidade_acao_preferencial_tesouraria");query.addLIKE();query.addNewLocalValues(1);query.addAND();position++;i[position]=10;}
                    if(ComposicaoCapitalSocial.getQuantidadeTotalAcaoTesouraria()!=0){query.addsimplecolumn("quantidade_total_acao_tesouraria");query.addLIKE();query.addNewLocalValues(1);query.addAND();position++;i[position]=11;}

                    query.addcondition("'1'", "=","'1'"); //evitador de ultimo and sozinho
                   
                   psQuery = ConnectionDB.conexao.prepareStatement(query.getQuery());
                   
                   for(int j=1;j<=position;j++)
                   {
                      switch(i[j]){
                        case  1:  psQuery.setString(j, "%"+ComposicaoCapitalSocial.getIdComposicao()+"%"); break;
                        case  2:  psQuery.setString(j, "%"+ComposicaoCapitalSocial.getFkEmpresa()+"%"); break;
                        case  3:  psQuery.setString(j, "%"+ComposicaoCapitalSocial.getFkArquivo()+"%"); break;
                        case  4:  psQuery.setString(j, "%"+ComposicaoCapitalSocial.getDataReferencia()+"%"); break;
                        case  5:  psQuery.setString(j, "%"+ComposicaoCapitalSocial.getNumeroIdentificacaoComposicaoCapitalSocial()+"%"); break;
                        case  6:  psQuery.setString(j, "%"+ComposicaoCapitalSocial.getQuantidadeAcaoOrdinariaCapitalIntegralizado()+"%"); break;
                        case  7:  psQuery.setString(j, "%"+ComposicaoCapitalSocial.getQuantidadeAcaoPreferencialCapitalIntegralizado()+"%"); break;
                        case  8:  psQuery.setString(j, "%"+ComposicaoCapitalSocial.getQuantidadeTotalAcaoCapitalIntegralizado()+"%"); break;
                        case  9:  psQuery.setString(j, "%"+ComposicaoCapitalSocial.getQuantidadeAcaoOrdinariaTesouraria()+"%"); break;
                        case  10:  psQuery.setString(j, "%"+ComposicaoCapitalSocial.getQuantidadeAcaoPreferencialTesouraria()+"%"); break;
                        case  11:  psQuery.setString(j, "%"+ComposicaoCapitalSocial.getQuantidadeTotalAcaoTesouraria()+"%"); break;
                      }
                   }
                       
                     funcao.Select(psQuery);
                     if(ConnectionDB.result.next())
                     {
                        return setData();
                     }
                     else
                     {
                         System.out.println("Não existe resultado para esse parametro");
                         throw new ComposicaoCapitalSocialException();
                     }
                } catch (FuncoesDB.FuncoesDBException ex) {
                    Erro  err = new Erro(true, true);
                    err.setIdErroDB(1101);
                    err.setSystemPrintMessage("    ERRO "+ex.getMessage());
                    err.setInterfaceMessage("ERRO : Erro ao executar script em SQL de criação do banco.");
                    err.Execute();
                    throw new ComposicaoCapitalSocialException();
                } catch (SQLException ex) {
                    Erro  err = new Erro(true, true);
                    err.setIdErroDB(1101);
                    err.setSystemPrintMessage("    ERRO "+ex.getMessage());
                    err.setInterfaceMessage("ERRO : Erro ao executar script em SQL de criação do banco.");
                    err.Execute();
                    throw new ComposicaoCapitalSocialException();
                }
    
    }
    
    
    
    public ComposicaoCapitalSocial  setData() throws SQLException{
      
      //substituir por QueryRunner
        
            ComposicaoCapitalSocial composicaoCapitalSocial ;
            if(isDFP)
               composicaoCapitalSocial = (ComposicaoCapitalSocial) new DfpComposicaoCapitalSocial();
            else
               composicaoCapitalSocial = (ComposicaoCapitalSocial) new ItrComposicaoCapitalSocial();
            
            composicaoCapitalSocial.setIdComposicao(ConnectionDB.result.getLong("id_composicao"));
            composicaoCapitalSocial.setFkEmpresa(ConnectionDB.result.getString("fk_empresa"));
            composicaoCapitalSocial.setFkArquivo(new ControleArquivosInseridos 
                    (ConnectionDB.result.getString("fk_arquivo")));
            composicaoCapitalSocial.setDataReferencia(ConnectionDB.result.getDate("data_referencia"));
            composicaoCapitalSocial.setNumeroIdentificacaoComposicaoCapitalSocial(ConnectionDB.result.getLong("numero_identificacao_composicao_capital_social"));
            composicaoCapitalSocial.setQuantidadeAcaoOrdinariaCapitalIntegralizado(ConnectionDB.result.getLong("quantidade_acao_ordinaria_capital_integralizado"));
            composicaoCapitalSocial.setQuantidadeAcaoPreferencialCapitalIntegralizado(ConnectionDB.result.getLong("quantidade_acao_preferencial_capital_integralizado"));
            composicaoCapitalSocial.setQuantidadeTotalAcaoCapitalIntegralizado(ConnectionDB.result.getLong("quantidade_total_acao_capital_integralizado"));
            composicaoCapitalSocial.setQuantidadeAcaoOrdinariaTesouraria(ConnectionDB.result.getLong("quantidade_acao_ordinaria_tesouraria"));
            composicaoCapitalSocial.setQuantidadeAcaoPreferencialTesouraria(ConnectionDB.result.getLong("quantidade_acao_preferencial_tesouraria"));
            composicaoCapitalSocial.setQuantidadeTotalAcaoTesouraria(ConnectionDB.result.getLong("quantidade_total_acao_tesouraria"));

  
            return composicaoCapitalSocial;
    }

    public class ComposicaoCapitalSocialException extends Exception {    
          private String pesquisa="";

          public ComposicaoCapitalSocialException(){
                    super();
            }

          public ComposicaoCapitalSocialException(String pesquisa){
              super();  
              this.pesquisa=pesquisa;    
            }

            @Override
            public String toString(){
                    return "Não foi possivel adquirir os dados para "+pesquisa;
            }

            @Override
            public String getMessage(){
                    return "Não foi possivel adquirir os dados para "+pesquisa;

            }
    }
    
    
    
    
    
    
    
}
