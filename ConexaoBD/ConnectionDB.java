package ConexaoBD;

import Erros.Erro;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class ConnectionDB  {
	
	public static Connection conexao ;
	public static Statement state ; 
	public static ResultSet result ; 
        public  String Url;
        public static boolean SEMSCHEMA = false;
        
public ConnectionDB(){
        close();
	conectaMySQL();
	}

   
          public static boolean gerenciador(){ 
           boolean resultado=true;
           if(conexao==null)
           {
               ConnectionDB con= new ConnectionDB();
               if(conexao==null)
               {
                 return(false);  
               }
           }
           
           return(resultado);
       }
      

    
       private void conectaMySQL(){ 
         if(SEMSCHEMA==true){this.Url=UserDB.Url;}
         else this.Url=UserDB.Url_merc;
           try{   
                 Class.forName("com.mysql.jdbc.Driver");
		 conexao = DriverManager.getConnection(this.Url,UserDB.Username,UserDB.Password);
                 state =conexao.createStatement();
	     }catch(ClassNotFoundException ex){
		Erro  err = new Erro(true, true);
                err.setIdErroDB(8101);
                err.setSystemPrintMessage("    ERRO "+ex);
                err.setInterfaceMessage("ERRO : Não foi encontrado o driver do connector mysql  \n      Não foi possivel conectar-se ao banco de dados");
                err.Execute();
            } catch (SQLException ex) {
                Erro  err = new Erro(true, true);
                err.setIdErroDB(8102);
                err.setSystemPrintMessage("    ERRO "+ex);
                err.setInterfaceMessage("ERRO : Erro em manipulacao das variaveis em SQL\n      Não foi possivel conectar-se ao banco de dados");
                err.Execute();
            }
    
        }   
        
       
      
       
        public static void close()
        {   
            try {
                if(conexao!=null) {conexao.close();conexao=null;}
	        if(state!=null)   {state.close(); state=null;}
	        if(result!=null)   {result.close(); result=null;}
 
              } catch (SQLException ex) {//Pouco importa  o que acontece aki;
              }
        }
        
        
}

