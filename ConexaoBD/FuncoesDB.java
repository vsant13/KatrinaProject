/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexaoBD;

import Dados.ConfiguracoesIniciais;
import Erros.Erro;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Erico
 */
public class FuncoesDB {
    private boolean check=false;
    private String TABLE=ConfiguracoesIniciais.getNomeDatabasepadrao()+".";
    
    public FuncoesDB(){
        
        checkconnection(); 
    }
    
    private void checkconnection(){
     check=ConnectionDB.gerenciador();
    }
    
    
 /** 
 * @deprecated, 
 * replaced by <code>InsertInto(String table ,String values[])</code>. 
 */  
 @Deprecated
  public void InsertInto(String table ,String values)throws FuncoesDBException{
     checkconnection();
     if (check==true)
     {
         String insert="INSERT INTO ";
         table=table.trim();
         String TempTable=table;
         if (!TempTable.contains(TABLE))table=TABLE+table;
         
         
         values=values.trim();
         String Val=" VALUES";
         if(!values.startsWith("(")) values="("+values;
         if(!values.endsWith(")"))  values=values+")";
         values=colocaAspas(values);
         String query =insert+table+Val+values;
         try {
             ConnectionDB.state.execute(query);
         } catch (SQLException ex) {
            System.out.println("Erro na query: "+query);
            getErro(1);
         }
     }
     else getErro(0);            
    }
    
    public void InsertInto(String table ,String values[])throws FuncoesDBException{
      checkconnection();
     if (check==true)
     {
         String insert="INSERT INTO ";
         table=table.trim();
         String TempTable=table;
         if (!TempTable.contains(TABLE))table=TABLE+table;
         
         
         String Val=" VALUES";
         String VALUE ="(";
         for(int i=0;i<values.length;i++)
         {
             if(i!=0)VALUE+=",";
             if(values[i]==null) values[i]="null";
             if (values[i].contains("'") || values[i].contains("\""))
             { 
                 values[i]=values[i].replaceAll("\"", "");
                 values[i]=values[i].replaceAll("'", "");
             }
             if(values[i].trim().isEmpty() || values[i].toLowerCase().contains("null"))
            {
                values[i]="null";
            }
            else
                values[i]="'"+values[i]+"'"; 
            VALUE+=values[i];
         }
         VALUE+=")";
         String query =insert+table+Val+VALUE;
         try {
             ConnectionDB.state.execute(query);
         } catch (SQLException ex) {
            System.out.println("Erro na query: "+query);
            getErro(1);
         }
     }
     else getErro(0);            
    }
    
    /** 
 * @deprecated, 
 * replaced by <code>InsertInto(String table ,String columns, String values[])</code>. 
 */  
 @Deprecated
  public void InsertInto(String table ,String columns,String values)throws FuncoesDBException{
     checkconnection();
     if (check==true)
     {
         String insert="INSERT INTO ";
         table=table.trim();
         String TempTable=table;
         if (!TempTable.contains(TABLE))table=TABLE+table;
         
         
         columns=columns.trim();
         if(!columns.startsWith("(")) columns="("+columns;
         if(!columns.endsWith(")"))  columns=columns+")";
         
         values=values.trim();
         String Val=" VALUES";
         if(!values.startsWith("(")) values="("+values;
         if(!values.endsWith(")"))  values=values+")";
         values=colocaAspas(values);
         
         String query =insert+table+columns+Val+values;
         try {
             ConnectionDB.state.execute(query);
         } catch (SQLException ex) {
           System.out.println("Erro na query: "+query);
            getErro(1);
         }
     }
     else getErro(0);            
    }
    
    public void InsertInto(String table ,String columns,String values[])throws FuncoesDBException{
     checkconnection();
     if (check==true)
     {
         String insert="INSERT INTO ";
         table=table.trim();
         String TempTable=table;
         if (!TempTable.contains(TABLE))table=TABLE+table;
         
             columns=columns.trim();
         if(!columns.startsWith("(")) columns="("+columns;
         if(!columns.endsWith(")"))  columns=columns+")";
         
         
         String Val=" VALUES";
         String VALUE ="(";
         for(int i=0;i<values.length;i++)
         {
             if(i!=0)VALUE+=",";
             if(values[i]==null) values[i]="null";
             if (values[i].contains("'") || values[i].contains("\""))
             { 
                 values[i]=values[i].replaceAll("\"", "");
                 values[i]=values[i].replaceAll("'", "");
             }
            if(values[i].trim().isEmpty() || values[i].toLowerCase().contains("null"))
            {
                values[i]="null";
            }
            else
             values[i]="'"+values[i]+"'"; 
            VALUE+=values[i];
         }
         VALUE+=")";
         String query =insert+table+columns+Val+VALUE;
         try {
             ConnectionDB.state.execute(query);
         } catch (SQLException ex) {
            System.out.println("Erro na query: "+query);
            getErro(1);
         }
     }
     else getErro(0);            
    }
    
    public void InsertIntoFromOtherTable(String table ,String SelectQuery) throws FuncoesDBException{
     checkconnection();
     if (check==true)
     {
         String insert="INSERT INTO ";
         table=table.trim();
         String TempTable=table;
         if (!TempTable.contains(TABLE))table=TABLE+table;
         
   
         String query =insert+table+" "+SelectQuery;
         try {
             ConnectionDB.state.execute(query);
         } catch (SQLException ex) {
            System.out.println("Erro na query: "+query);
            getErro(1);
         }
     }
     else getErro(0);            
    }
    
    
     public void InsertIntoFromOtherTable(String table ,String columns,String SelectQuery)throws FuncoesDBException{
     checkconnection();
     if (check==true)
     {
          String insert="INSERT INTO ";
         table=table.trim();
         String TempTable=table;
         if (!TempTable.contains(TABLE))table=TABLE+table;
         
         columns=columns.trim();
         if(!columns.startsWith("(")) columns="("+columns;
         if(!columns.endsWith(")"))  columns=columns+")";
   
         String query =insert+table+columns+" "+SelectQuery;
         try {
             ConnectionDB.state.execute(query);
         } catch (SQLException ex) {
            System.out.println("Erro na query: "+query);
            getErro(1);
         }
     }
     else getErro(0);            
    }
     
    
    public void  Select(String query) throws FuncoesDBException{
      checkconnection();  
      if((check==true))
      {  
            try {
                ConnectionDB.result=ConnectionDB.state.executeQuery(query);
                 
               
            } catch (SQLException ex) {
                System.out.println("Erro na query: "+query);
                getErro(1);
            }
      }
      else
      {
          getErro(0);
         // return ConnectionDB.result;
      }
    }  
    
    public void  Select(PreparedStatement psquery) throws FuncoesDBException{
      checkconnection();  
      if((check==true))
      {  
            try {
                ConnectionDB.result= psquery.executeQuery();
            } catch (SQLException ex) {
                System.out.println("Erro na query: "+psquery);
                getErro(1);
            }
      }
      else
      {
          getErro(0);
         // return ConnectionDB.result;
      }
    }  
    
    public void Update(String table,String set,String condicoes) throws FuncoesDBException{
     /*exemplo ; update("tabela",
                         "id_tabela=1000,tipo=anexo",
                         "where id_tabela='2000'") =>SQL=>
      => UPDATE mercadofinanceiro.tabela SET id_tabela="1000",tipo="anexo" where id_tabela='2000'
      */
     checkconnection();
     if (check==true)
     {
         String update="UPDATE  ";
         table=table.trim();
         String TempTable=table;
         if (!TempTable.contains(TABLE))table=TABLE+table;
         
         set=set.trim();
         String SET=" SET ";
         set=colocaAspas(set);
         
         String query =update+table+SET+set+" "+condicoes.trim();
         
         try {
             ConnectionDB.state.execute("SET SQL_SAFE_UPDATES=0");
             ConnectionDB.state.execute(query);
         } catch (SQLException ex) {
            System.out.println("Erro na query: "+query);
            getErro(1);
         }
     }
     else getErro(0);            
    }
        
   
    public void Delete(String table,String condicoes)throws FuncoesDBException{
     /*exemplo ; update("tabela",
                         "where id_tabela='2000'") =>SQL=>
      => DELETE mercadofinanceiro.tabela where id_tabela='2000'
      */
     checkconnection();
     if (check==true)
     {
         String delete="DELETE FROM ";
         table=table.trim();
         String TempTable=table;
        if (!TempTable.contains(TABLE))table=TABLE+table;
         
         
         String query =delete+table+" "+condicoes.trim();
         try {
             ConnectionDB.state.execute("SET SQL_SAFE_UPDATES=0");
             ConnectionDB.state.execute(query);
         } catch (SQLException ex) {
            System.out.println("Erro na query: "+query);
            getErro(1);
         }
     }
     else getErro(0);            
    }
    
    
    private String colocaAspas(String Values){
        if(Values==null) return "null";
        Values=Values.trim();
        if(Values.isEmpty() || Values.toLowerCase().contains("null"))
        {
            return "null";
        }
        if (Values.contains("'") || Values.contains("\""))
        {
           return Values;
        }
        else 
        {
            if(Values.startsWith("(") || Values.contains("=") || Values.contains(","))
            {
                if(!Values.contains("="))
                {
                     if(Values.startsWith("(")&& Values.endsWith(")")){Values="('"+Values.substring(1,Values.length()-1)+"')";}   
                     Values = Values.replaceAll(",","','");
                    return Values;
                }
                else
                {
                    if(Values.startsWith("(")&& Values.endsWith(")")){Values="('"+Values.substring(1,Values.length()-1)+"')";}  
                    else Values+="'";
                    Values = Values.replaceAll("=", "='");
                    Values = Values.replaceAll(",", "',");
                    return Values;
                }
            }
            else
            {
                Values="'"+Values+"'";
                return Values;
            }
         }
}


    private void getErro(int num) throws FuncoesDBException{
        Erro  err = new Erro(true, false);
            switch(num){
            case 0:
                err.setIdErroDB(7101);
                err.setSystemPrintMessage("    ERRO :Problemas na conexão do banco.");
                break;
            case 1:
                 err.setIdErroDB(7102);
                 err.setSystemPrintMessage("    ERRO :Não foi possivel efetuar comando em  SQL .");
                break;
             }
            err.Execute();
            throw new FuncoesDBException();
    }
    
    
    
    
    
   public class FuncoesDBException extends Exception{
    String query="";
    
   public FuncoesDBException(){
                super();
        }
   public FuncoesDBException(String query){
                super();
                this.query+="\nProblemas em executar :"+query;
        }
        
        @Override
        public String toString(){
                return "Problemas na execução de alguma função SQL "+query;
        }
        
        @Override
        public String getMessage(){
                return  "Problemas na execução de alguma função SQL "+query;
        }

}
    
    
}
