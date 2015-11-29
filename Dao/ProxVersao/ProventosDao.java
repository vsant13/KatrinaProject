package Dao.ProxVersao;

import ConexaoBD.ConnectionDB;
import ConexaoBD.FuncoesDB;
import ConexaoBD.FuncoesDB.FuncoesDBException;
import ConexaoBD.Query;
import Erros.Erro;
import entity.ProxVersao.DfpProventos;
import entity.ProxVersao.ItrProventos;
import entity.ProxVersao.Proventos;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;





/**
 *
 * @author Erico
 * 
 * INCOMPLETO , DEIXAR PARA VERSÃO 2.0
 */
public class ProventosDao {
     
    public String  table = "_PROVENTOS";
    private FuncoesDB funcao = new FuncoesDB();
    private Proventos proventos;
    private boolean isDFP;
    private Query query;
    private PreparedStatement psQuery =null;
    
  public  String   id_empresa="id_empresa",cod_cvm="cod_cvm",denominacao_social="denominacao_social",
 denominacao_comercial="denominacao_comercial",logradouro="logradouro",complemento="complemento",bairro="bairro",
cep="cep",municipio="municipio",uf="uf",ddd="ddd",telefone="telefone",fax="fax",denominacao_anterior="denominacao_anterior",
setor_atividade="setor_atividade",cnpj="cnpj",dri="dri",auditor="auditor",quant_de_acoes_ordinarias="quant_de_acoes_ordinarias",
quant_de_acoes_preferenciais="quant_de_acoes_preferenciais",situacao="situacao",data_da_situacao="data_da_situacao",
tipo_papel1="tipo_papel1",tipo_papel2="tipo_papel2",tipo_papel3="tipo_papel3",tipo_papel4="tipo_papel4",tipo_papel5="tipo_papel5",
tipo_papel6="tipo_papel6",controle_acionario="controle_acionario",data_de_registro="data_de_registro",data_de_cancelamento="data_de_cancelamento",
mercado="mercado",bolsa1="bolsa1",bolsa2="bolsa2",bolsa3="bolsa3",bolsa4="bolsa4",bolsa5="bolsa5",
bolsa6="bolsa6",bolsa7="bolsa7",bolsa8="bolsa8",bolsa9="bolsa9",motivo_do_cancelamento="motivo_do_cancelamento",
patrimonio_liquido="patrimonio_liquido",data_do_patrimonio="data_do_patrimonio",e_mail="e_mail",nome_setor_atividade="nome_setor_atividade",
data_da_acao="data_da_acao",tipo_negocio1="tipo_negocio1",tipo_negocio2="tipo_negocio2",tipo_negocio3="tipo_negocio3",tipo_negocio4="tipo_negocio4",
tipo_negocio5="tipo_negocio5",tipo_negocio6="tipo_negocio6",tipo_mercado1="tipo_mercado1",tipo_mercado2="tipo_mercado2",tipo_mercado3="tipo_mercado3",
tipo_mercado4="tipo_mercado4",tipo_mercado5="tipo_mercado5",tipo_mercado6="tipo_mercado6";
    
    public ProventosDao(boolean isDFP){
        this.proventos=null;
            this.isDFP=isDFP;
    }
   
    public ProventosDao(ItrProventos provento) throws ProventosException{
        this.proventos = provento;
    }
    public ProventosDao(DfpProventos provento) throws ProventosException{
        this.proventos = provento;
    }
    
     public Proventos getFirst(){
        try {
        if(proventos==null)
        {
            query = new Query();
            query.addSELECTALL();
            query.addFROM();
            query.addtable(table);
            
           // query=iscolsnull(query);
            
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

        } catch (ProventosException ex) {
            
        }
        return null;
    }
    public Proventos getFirst(Query byQuery){
        
        try {
            funcao.Select(byQuery.getQuery());
                if(ConnectionDB.result.next())
                       return setData();
                
        } catch (SQLException ex) {
            
        } catch (FuncoesDB.FuncoesDBException ex) { 
            
        }
        return null;
    }
    public Proventos getNext(){
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
    public List<Proventos> getList(){
        List<Proventos> list = new ArrayList<Proventos>();
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
     public List<Proventos> getList(String column,String likeValue){
        List<Proventos> list = new ArrayList<Proventos>();
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
    public List<Proventos> getList(Query byQuery) {
        List<Proventos> list = new ArrayList<Proventos>();
        try {
            funcao.Select(byQuery.getQuery());
                while(ConnectionDB.result.next())
                        list.add(setData());
                return list;
        } catch (SQLException ex) {
             Erro  err = new Erro(true, true);
            err.setIdErroDB(1101);
            err.setSystemPrintMessage("    ERRO "+ex.getMessage());
            err.setInterfaceMessage("ERRO : Erro ao executar script em SQL de criação do banco.");
            err.Execute();
        } catch (FuncoesDB.FuncoesDBException ex) {
             Erro  err = new Erro(true, true);
            err.setIdErroDB(1101);
            err.setSystemPrintMessage("    ERRO "+ex.getMessage());
            err.setInterfaceMessage("ERRO : Erro ao executar script em SQL de criação do banco.");
            err.Execute();
        }
        return null; 
    }
    
    
    
    private Proventos analiza_obj() throws ProventosException{
            try {  
                    Query query = new Query(); 
                    query.addSELECTALL();
                    query.addFROM();
                    query.addtable(table);
                    query.addWHERE();
                   
                    
                    
                   
                   int i[]=new  int[61]; 
                   int position=0;
                   
                   if(proventos.getIdProvento()!=null){query.addsimplecolumn(auditor);query.addLIKE();query.addNewLocalValues(1);query.addAND();position++;i[position]=1;}
                   if(proventos.getFkEmpresa()!=null){query.addsimplecolumn(bairro);query.addLIKE();query.addNewLocalValues(1);query.addAND();position++;i[position]=2;}
                   if(proventos.getFkArquivo()!=null){query.addsimplecolumn(bolsa1);query.addLIKE();query.addNewLocalValues(1);query.addAND();position++;i[position]=3;}
                   if(proventos.getTipoProvento()!=null){query.addsimplecolumn(bolsa2);query.addLIKE();query.addNewLocalValues(1);query.addAND();position++;i[position]=4;}
                   if(proventos.getDataAprovacao()!=null){query.addsimplecolumn(bolsa3);query.addLIKE();query.addNewLocalValues(1);query.addAND();position++;i[position]=5;}
                   if(proventos.getDataInicioPagamento()!=null){query.addsimplecolumn(bolsa4);query.addLIKE();query.addNewLocalValues(1);query.addAND();position++;i[position]=6;}
                   if(proventos.getDescricaoCodEspecie()!=null){query.addsimplecolumn(bolsa5);query.addLIKE();query.addNewLocalValues(1);query.addAND();position++;i[position]=7;}
                   if(proventos.getSiglaCodEspecie()!=null){query.addsimplecolumn(bolsa6);query.addLIKE();query.addNewLocalValues(1);query.addAND();position++;i[position]=8;}
                   if(proventos.getDescricaoCodClassePreferencial()!=null){query.addsimplecolumn(bolsa7);query.addLIKE();query.addNewLocalValues(1);query.addAND();position++;i[position]=9;}
                   if(proventos.getSiglaCodClassePreferencial()!=null){query.addsimplecolumn(bolsa8);query.addLIKE();query.addNewLocalValues(1);query.addAND();position++;i[position]=10;}
                   if(proventos.getValorDoProventoPorAcao()!=null){query.addsimplecolumn(bolsa9);query.addLIKE();query.addNewLocalValues(1);query.addAND();position++;i[position]=11;}
                   
                   query.addcondition("'1'", "=","'1'"); //evitador de ultimo and sozinho
                   
                   psQuery = ConnectionDB.conexao.prepareStatement(query.getQuery());
                   
                   for(int j=1;j<=position;j++)
                   {
                      switch(i[j]){
                        case  1:  psQuery.setString(j, "%"+proventos.getIdProvento()+"%"); break;
                        case  2:  psQuery.setString(j, "%"+proventos.getFkEmpresa()+"%"); break;
                        case  3:  psQuery.setString(j, "%"+proventos.getFkArquivo()+"%"); break;
                        case  4:  psQuery.setString(j, "%"+proventos.getTipoProvento()+"%"); break;
                        case  5:  psQuery.setString(j, "%"+proventos.getDataAprovacao()+"%"); break;
                        case  6:  psQuery.setString(j, "%"+proventos.getDataInicioPagamento()+"%"); break;
                        case  7:  psQuery.setString(j, "%"+proventos.getDescricaoCodEspecie()+"%"); break;
                        case  8:  psQuery.setString(j, "%"+proventos.getSiglaCodEspecie()+"%"); break;
                        case  9:  psQuery.setString(j, "%"+proventos.getDescricaoCodClassePreferencial()+"%"); break;
                        case  10:  psQuery.setString(j, "%"+proventos.getSiglaCodClassePreferencial()+"%"); break;
                        case  11:  psQuery.setString(j, "%"+proventos.getValorDoProventoPorAcao()+"%"); break;

                      }
                   }
                       
                     funcao.Select(psQuery);
                     if(ConnectionDB.result.next())
                     {
                        return setData();
                     }
                     else
                     {
                         System.out.println("Não existe empresa para esse parametro");
                         throw new ProventosException();
                     }
                } catch (FuncoesDBException ex) {
                    Erro  err = new Erro(true, true);
                    err.setIdErroDB(1101);
                    err.setSystemPrintMessage("    ERRO "+ex.getMessage());
                    err.setInterfaceMessage("ERRO : Erro ao executar script em SQL de criação do banco.");
                    err.Execute();
                    throw new ProventosException();
                } catch (SQLException ex) {
                    Erro  err = new Erro(true, true);
                    err.setIdErroDB(1102);
                    err.setSystemPrintMessage("    ERRO "+ex.getMessage());
                    err.setInterfaceMessage("ERRO : Erro ao executar script em SQL de criação do banco.");
                    err.Execute();
                    throw new ProventosException();
                }
    
    }
    
    
    public Proventos setData() throws SQLException{
            Proventos provento;
            if(isDFP)
                provento = new DfpProventos();
            else
                provento = new ItrProventos();
                        
            provento.setFkEmpresa(ConnectionDB.result.getString("fk_empresa"));
            provento.setTipoProvento(ConnectionDB.result.getString("tipo_provento"));
            provento.setDataAprovacao(ConnectionDB.result.getDate("data_aprovacao"));
            provento.setDataInicioPagamento(ConnectionDB.result.getDate("data_inicio_pagamento"));
            provento.setDescricaoCodEspecie(ConnectionDB.result.getString("descricao_cod_especie"));
            provento.setSiglaCodEspecie(ConnectionDB.result.getString("sigla_cod_especie"));
            provento.setDescricaoCodClassePreferencial(ConnectionDB.result.getString("descricao_cod_classe_preferencial"));
            provento.setSiglaCodClassePreferencial(ConnectionDB.result.getString("sigla_cod_classe_preferencial"));
            provento.setValorDoProventoPorAcao(ConnectionDB.result.getBigDecimal("valor_do_provento_por_acao"));
           
            return provento;
    }
    
    
    
    
    public class ProventosException extends Exception {    
          private String empresa="";

          public ProventosException(){
                    super();
            }

          public ProventosException(String arquivo){
              super();  
              this.empresa=arquivo;    
            }

            @Override
            public String toString(){
                    return "Não foi possivel adquirir os dados da empresa "+empresa;
            }

            @Override
            public String getMessage(){
                    return "Não foi possivel adquirir os dados da empresa "+empresa;

            }
    }
}