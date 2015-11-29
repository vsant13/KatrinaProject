/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ManutencaoBD;

import ConexaoBD.ConnectionDB;
import ConexaoBD.FuncoesDB;
import ConexaoBD.FuncoesDB.FuncoesDBException;
import Dados.ConfiguracoesIniciais;
import Dados.DadosDB;
import Erros.Erro;
import java.sql.SQLException;


/**
 *
 * @author Erico
 */
public class ControledeInsercao {
   
    private static boolean PERMISSAO;
    private static String Nome_do_arquivo;
    private static String Tipo_de_arquivo;
    
     private static final String id_arquivo=DadosDB.getDataBaseColumnName(3,1);
     private static final String estado=DadosDB.getDataBaseColumnName(3,3);
     private static final String tableConArqIns=DadosDB.getDataBaseTableName(3);
    
    
    public static boolean Permissao(String nome_do_arquivo, String tipo_de_arquivo ,String table_referenciada){
     Nome_do_arquivo=nome_do_arquivo;
     Tipo_de_arquivo=tipo_de_arquivo;
    String tables[]= new String[1];
    tables[0]=table_referenciada;
    Controle( tables);
    return PERMISSAO;
    }
    
    public static boolean Permissao(String nome_do_arquivo,String tipo_de_arquivo ,String table_referenciada1,String table_referenciada2){
    Nome_do_arquivo=nome_do_arquivo;
    Tipo_de_arquivo=tipo_de_arquivo;
    String tables[]= new String[2];
    tables[0]=table_referenciada1;
    tables[1]=table_referenciada2;
    Controle( tables);
    return PERMISSAO;
    }
    
    public static boolean Permissao(String nome_do_arquivo,String tipo_de_arquivo ,String table_referenciada1,String table_referenciada2,String table_referenciada3){
    Nome_do_arquivo=nome_do_arquivo;
    Tipo_de_arquivo=tipo_de_arquivo;
    String tables[]= new String[3];
    tables[0]=table_referenciada1;
    tables[1]=table_referenciada2;
    tables[1]=table_referenciada3;
    Controle( tables);
    return PERMISSAO;
    }
    
    public static boolean Permissao(String nome_do_arquivo,String tipo_de_arquivo ,String table_referenciada[]){
    Nome_do_arquivo=nome_do_arquivo;
    Tipo_de_arquivo=tipo_de_arquivo;
    Controle(table_referenciada);
    return PERMISSAO;
    }
   
    
    
    private static void Controle(String table[]){
        FuncoesDB funcao = new FuncoesDB();
        Nome_do_arquivo=colocaAspas(Nome_do_arquivo);
        String[] valeus = new String[3];
        valeus[0]=Nome_do_arquivo;
        valeus[1]=Tipo_de_arquivo;
    try{
   
    String  query = new String("SELECT "+id_arquivo+","+estado+" FROM "+ ConfiguracoesIniciais.getNomeDatabasepadrao()+"."+ tableConArqIns+" where "+id_arquivo+"="+Nome_do_arquivo);
                     funcao.Select(query);
                              if(ConnectionDB.result.next())
                              {
                                  if((ConnectionDB.result.getString(estado)).equals("concluido"))
                                  {
                                        PERMISSAO=false;
                                  }
                                  else
                                  {
                                      for(int i=0;i<table.length;i++)
                                      { 
                                          // É necessario ser "fk_arquivo" .Sem possibidade de mudanca.
                                        funcao.Delete(table[i]," WHERE fk_arquivo="+Nome_do_arquivo);
                                      }
                                        funcao.Delete(tableConArqIns," WHERE "+id_arquivo+"="+Nome_do_arquivo);
                                        valeus[2]="andamento";
                                        funcao.InsertInto(tableConArqIns,valeus);
                                        PERMISSAO=true;    
                                  }
                               }
                              else
                              {
                                 valeus[2]="andamento";
                                 funcao.InsertInto(tableConArqIns,valeus);
                                 PERMISSAO=true;                      
                              }   
        } catch (SQLException ex) {
            Erro  err = new Erro(true, false);
            ex.printStackTrace();
            err.setIdErroDB(9101);
            err.setSystemPrintMessage("    ERRO "+ex);
            err.Execute();
            PERMISSAO=false;
        } catch (FuncoesDBException ex) {
            Erro  err = new Erro(true, false);
            ex.printStackTrace();
            err.setIdErroDB(9102);
            err.setSystemPrintMessage("    ERRO "+ex);
            err.Execute();
            PERMISSAO=false;
        }
    
    
     }
     private static String colocaAspas(String str){
      
        String rs ="";
      if(!str.isEmpty())
      { 
        if(!str.contains("'") && !str.contains("\""))
        rs="'"+str+"'"; 
      }
      else rs=null;
      return (rs);
    }
     
     
     public static boolean Conclusao(String Nome_do_arquivo){
        FuncoesDB funcao = new FuncoesDB();
        try{
               funcao.Update(tableConArqIns,estado+"=concluido","where "+id_arquivo+"="+"'"+Nome_do_arquivo+"'");
            } catch (FuncoesDBException ex) {   
                   Erro  err = new Erro(true, false);
                   ex.printStackTrace();
                   err.setIdErroDB(9103);
                   err.setSystemPrintMessage("    ERRO "+ex);
                   err.Execute();
                   return false;
           }   
        return true;
       }
}
