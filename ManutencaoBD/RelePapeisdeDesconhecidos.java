
package ManutencaoBD;

import ConexaoBD.ConnectionDB;
import ConexaoBD.FuncoesDB;
import ConexaoBD.FuncoesDB.FuncoesDBException;
import Dados.ConfiguracoesIniciais;
import Dados.DadosDB;
import Erros.Erro;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 *
 * @author Erico
 */
public class RelePapeisdeDesconhecidos {
    
    static FuncoesDB funcao =new FuncoesDB(); 
    
     private static final String id_empresa=DadosDB.getDataBaseColumnName(1,1);
     private static final String fk_codneg_nome=DadosDB.getDataBaseColumnName(12,2);
     private static final String tableCias=DadosDB.getDataBaseTableName(1);
     private static final String tablePBovespa=DadosDB.getDataBaseTableName(11);
     private static final String tablePDesconhecido=DadosDB.getDataBaseTableName(12);
     private static String colunasPB=null;
     private static String colunasPD=null;
     
   
    public static void Releitura(){
        String seleciona_compativeis = "SELECT DISTINCT "+id_empresa+" FROM "+ConfiguracoesIniciais.getNomeDatabasepadrao() +
                "."+tableCias+" INNER JOIN "+ConfiguracoesIniciais.getNomeDatabasepadrao() +"."+tablePDesconhecido+" ON "+id_empresa+"="+fk_codneg_nome;  
        try {
            
            ArrayList<String> fila = new ArrayList<>();
            funcao.Select(seleciona_compativeis);
            while(ConnectionDB.result.next())
            {
                fila.add(ConnectionDB.result.getString(id_empresa));
            }
            while(!fila.isEmpty())
            {
                Releitura(fila.remove(0));
            }
        } catch (SQLException ex) {
            Erro  err = new Erro(true, false);
            ex.printStackTrace();
            err.setIdErroDB(10101);
            err.setSystemPrintMessage("    ERRO "+ex);
            err.Execute();
        } catch (FuncoesDBException ex) {
            Erro  err = new Erro(true, false);
            ex.printStackTrace();
            err.setIdErroDB(10102);
            err.setSystemPrintMessage("    ERRO "+ex);
            err.Execute();
        }

    }
    
    
    private static void Releitura(String cod_bmf) throws FuncoesDBException , SQLException{
 
         
               //Para verificações:
               String verifica="select "+fk_codneg_nome+" from "+ConfiguracoesIniciais.getNomeDatabasepadrao() +"."+tablePDesconhecido+" where "+fk_codneg_nome+"=\""+cod_bmf+"\"";
               String verifica2="select "+id_empresa+" from "+ConfiguracoesIniciais.getNomeDatabasepadrao() +"."+tableCias+" where "+id_empresa+"=\""+cod_bmf+"\"";
               //Para Insercão de dados:
               
               
               String colunasPB= getColunasPB();
               
               
               String colunasPD=getColunasPD();
              
               String selectquery="select "+colunasPD+" from "+ConfiguracoesIniciais.getNomeDatabasepadrao()+"."+DadosDB.getDataBaseTableName(12)  +
               " where "+fk_codneg_nome+"=\""+cod_bmf+"\"";
               //Para apagar dados reinseridos:
               String condicoes=" WHERE "+fk_codneg_nome+"=\""+cod_bmf+"\"";
               funcao.Select(verifica);  //double check
                 if(ConnectionDB.result.next())
                 {
                    funcao.Select(verifica2);//double check
                    if(ConnectionDB.result.next())
                    {
                        funcao.InsertIntoFromOtherTable(tablePBovespa,colunasPB,selectquery); 
                        funcao.Delete(tablePDesconhecido, condicoes);
                    }
                    else
                    {
                        System.out.println("Não existe cadastro de empresa para : " + cod_bmf);
                    }
                  }
                 else
                 {
                     System.out.println("Não existe cotação para : "+ cod_bmf);
                 }
    }
    
    private static String getColunasPB(){
        if(colunasPB==null)
        {
         colunasPB="(";
               for(int i=2;i<=29;i++)
                   colunasPB=colunasPB+DadosDB.getDataBaseColumnName(11, i)+",";
               colunasPB=colunasPB+DadosDB.getDataBaseColumnName(11, 30)+")";
        }
        return colunasPB;
    }
    private static String getColunasPD(){
        if(colunasPD==null)
        {
         colunasPD="";
               for(int i=2;i<=29;i++)
                   colunasPD=colunasPD+DadosDB.getDataBaseColumnName(12 ,i) +",";
               colunasPD=colunasPD+DadosDB.getDataBaseColumnName(12, 30);
        }
        return colunasPD;
    }
        
}
