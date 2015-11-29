
package Dados;


import ConexaoBD.UserDB;
import Erros.Erro;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;



public class ConfiguracoesDB {
    
        private static Connection con ;                                            //variaveis de conexao BD
	private static Statement st ;                                              //variaveis de conexao BD                                                     
	private static ResultSet res ;                                            //variaveis de conexao BD
    
        /*isDataBase(String database)
         *Verifica de existe o database (schema) 
         */
        public boolean isDataBase(String database){
           System.out.println("Inicia isDataBase da classe ConfiguracoesDB");       //avisa entrada do metodo;
		boolean is=false;                                                   //variavel de retorno;
                ArrayList<String> lista;                                            //ArrayList para armazenar os database existentes;
                    lista=getDataBases();                                           //Chama o metodo getDataBases();
                    if(lista.contains(database))                      //se existe o database :;                 
                    {
                         System.out.println("   Database: "+database+ " already exists");  //imprimi msg
                         is=true;                                                       //define variavel de retorno true;
                    }
                    else                                                           // se nao existe     
                    {
                         is=false;                                                      //define variavel de retorno false;
                    }
                    System.out.println("Finaliza isDataBase da classe ConfiguracoesDB,return:"+is);    //avisa saida do metodo;
                    return (is);                                                            //return variavel de retorno;         
	}//Fim de metodo
        
        
        /*isDataBase(String database)
         *Verifica de existe o database (schema) 
         */
        public  ArrayList<String> getDataBases(){
           
                System.out.println("Inicia getDataBases da classe ConfiguracoesDB");       //avisa entrada do metodo;
                ArrayList<String> lista;                                                    //ArrayList para armazenar os database existentes;
                   lista = new ArrayList<String>();                                         //ArrayList para armazenar os database existentes;
              try {
                      conectaMySQL();                                                       //chama o metodo conectaMysSQL;
                      DatabaseMetaData meta = con.getMetaData();                            //pega as informacoes do Banco ;
                      res= meta.getCatalogs();                                               //pega as informacoes do Banco ;   
                      while (res.next())                                                    //enquantp tiver mais informacoes do Banco: ;
                      {
                        String listofDatabases=res.getString("TABLE_CAT");                   //coloca numa string temporaria;
                        lista.add(listofDatabases);                                          //coloca na lista de database;       
                      }
            } //Fim de metodo
            catch (SQLException ex) {
                        Erro  err = new Erro(true, true);
                        err.setIdErroDB(2101);
                        err.setSystemPrintMessage("    ERRO "+ex);
                        err.setInterfaceMessage("ERRO : Erro em manipulacao das variaveis em SQL");
                        err.Execute();
		}finally{
                        close();                                                                    //fecha conexao;
                        System.out.println("Finaliza getDataBases da classe ConfiguracoesDB");    //avisa saida do metodo;
                        return (lista);                                                      //retorna a lista
                        }   
		
	}//Fim de metodo
        
        
        /*CreateDataBase(String database)
         *Cria o database (schema) 
         */
        public boolean CreateDataBase(String database){
                System.out.println("Inicia CreateDataBase da classe ConfiguracoesDB");       //avisa entrada do metodo;
		boolean criado=false;                                                       //variavel de retorno;                                      
              try{ 
                         
                         conectaMySQL();                                                      //chama o metodo conectaMySQL;  
                         st.executeUpdate("CREATE DATABASE "+database);                       //cria o database;          
                         System.out.println("   Database foi criado");                       //print;      
                         criado=true;                                                        //set variavel de retorno;   
		}catch(SQLException ex) {
			Erro  err = new Erro(true, true);
                        err.setIdErroDB(2102);
                        err.setSystemPrintMessage("    ERRO "+ex);
                        err.setInterfaceMessage("ERRO : Erro em manipulacao das variaveis em SQL");
                        err.Execute();
                        criado=false;                                                       //set variavel de retorno; 
		}finally{
			close();                                                                //fecha conexao;
                        System.out.println("Finaliza CreateDataBase da classe ConfiguracoesDB");    //avisa saida do metodo;
                        return (criado);                                                        //retorna a variavel
                        }
        }//Fim de metodo
        
           
        /*backupDataBase(String database)
         *Cria o backup do database (schema) 
         */
        public void backupDataBase(String database){
           System.out.println("Inicia backupDataBase da classe ConfiguracoesDB");       //avisa entrada do metodo;  
           Calendar cal = Calendar.getInstance();                                       //chama Calendar;
           String sqlfile= "backup_"+database+"_"+cal.get(Calendar.DATE)+"_"+(cal.get(Calendar.MONTH)+1)+"_"+cal.get(Calendar.YEAR)+".sql";  //string com nome do arquivo;
           String pathsqlfile = Diretorios.path_datafiles_config_backup_BD()+ sqlfile;                      //string com caminho do arquivo;
          
            String comando;                                                                                 //string com comando para o mysql;
            String comandoLin;
            comando = "cmd /c mysqldump --user="+ UserDB.getUsername()+" --password="+ UserDB.getPassword()+" --opt "+database+ "> "+pathsqlfile;
            comandoLin="sh "+Diretorios.path_datafiles_config_backup_BD()+"MaSLinBackUpDb.sh "+
                     UserDB.getUsername()+" "+UserDB.getPassword()+" "+ConfiguracoesIniciais.getNomeDatabasepadrao()+" "  +pathsqlfile;
             Runtime comand =Runtime.getRuntime();                                                  //chama Runtime;
             try{ 
                   if((((System.getProperty("os.name")).toLowerCase()).trim()).equals("linux"))
                   {
                        Process exec = comand.exec(comandoLin);                                                  //executa comando para mysql;        
                        exec.waitFor();
                        exec.getErrorStream();
                        System.out.println("  "+comandoLin);
                   }
                   else
                   {
                        Process exec = comand.exec(comando);                                                  //executa comando para mysql;        
                        exec.waitFor();
                        exec.getErrorStream();
                        System.out.println("  "+comando);                                                        //print comando; 
                   }
                 
                 
                   
              }catch(  IOException | InterruptedException ex){
                DropDataBase(database);
                Erro  err = new Erro(true, true);
                err.setIdErroDB(2103);
                err.setSystemPrintMessage("    ERRO "+ex);
                err.setInterfaceMessage("ERRO : Erro ao criar um backup em SQL da criação do banco.");
                err.Execute();
              }
            comando=null;                                                                               //libera memoria;
            System.gc();
            System.out.println("Finaliza backupDataBase da classe ConfiguracoesDB");             //avisa saida do metodo;    
        }//Fim de metodo
        
        
        /*restoreDataBase(String database ,String backupPath)
         *Restaura o banco de dados a partir do backup.Chama o backup do database (schema) para o banco ; 
         */
        public void restoreDataBase(String database ,String backupPath){
            System.out.println("Inicia restoreDataBase da classe ConfiguracoesDB");       //avisa entrada do metodo;  
            CreateDataBase(database);                                                       //chama o metodo CreateDataBase;
            String comando;  
            String comandoLin  ;//string com comando para o mysql;
             comando = "cmd /c mysql --user="+ UserDB.getUsername()+" --password="+ UserDB.getPassword() +" "+database+ "< "+backupPath;
             comandoLin="sh "+Diretorios.path_datafiles_config_backup_BD()+"MaSLinRestoreDb.sh "+
                     UserDB.getUsername()+" "+UserDB.getPassword()+" "+ConfiguracoesIniciais.getNomeDatabasepadrao()+" "  +backupPath;
             Runtime comand =Runtime.getRuntime();                                          //chama Runtime;
             try{
                 
              if((((System.getProperty("os.name")).toLowerCase()).trim()).equals("linux"))
                   {
                        Process exec = comand.exec(comandoLin);                                                  //executa comando para mysql;        
                        exec.waitFor();
                        exec.getErrorStream();
                        System.out.println("  "+comando);
                   }
                else
                    {
                        Process exec = comand.exec(comando);                                           //executa comando para mysql;
                        exec.waitFor();
                        exec.getErrorStream();
                        System.out.println("  "+comando);                                                 //print comando; 
                    }
              }catch( IOException | InterruptedException ex){
              DropDataBase(database);
                Erro  err = new Erro(true, true);
                err.setIdErroDB(2104);
                err.setSystemPrintMessage("    ERRO "+ex);
                err.setInterfaceMessage("ERRO : Erro ao executar um script sql de criação do banco");
                err.Execute();
             }
            comando=null;                                                                       //libera memoria;
            System.gc();
            System.out.println("Finaliza restoreDataBase da classe ConfiguracoesDB");             //avisa saida do metodo;   
        }//Fim de metodo
        
        
        /*DropDataBase(String database)
         *Apaga database; 
         */
         public void DropDataBase(String database){
                System.out.println("Inicia DropDataBase da classe ConfiguracoesDB");       //avisa entrada do metodo;
		try{
                    conectaMySQL();                                                       //chama conectaMySQL;
                    String query = "DROP DATABASE "+database;                         //String se comando sql;         
                    st.execute(query);                                                 //executa o comando; 
                    System.out.println(query);                                      //print comando; //print comando;         
		}catch(SQLException ex) {
                                Erro  err = new Erro(true, true);
                                err.setIdErroDB(2105);
                                err.setSystemPrintMessage("    ERRO "+ex);
                                err.setInterfaceMessage("ERRO : Erro em manipulacao das variaveis em SQL");
                                err.Execute();
		}finally{
                            close();                                                      //fecha conexao mysql;
                            System.out.println("Finaliza DropDataBase da classe ConfiguracoesDB");      //avisa saida do metodo;      
                        }
        }//Fim de metodo
        
         
         /*conectaMySQL()
         *Conecta ao banco de dados do mysql; 
         */
         private final  void conectaMySQL(){
             //NÃO SE CONECTA A ALGUM BANCO DE DADOS ESPECIFICO. conectaMySQL!=ConnectionDB
         try{
		 Class.forName("com.mysql.jdbc.Driver");  //conexao especial!
		 con = DriverManager.getConnection("jdbc:mysql://"+UserDB.getHostname()+":"+UserDB.getPort(),UserDB.getUsername(),UserDB.getPassword()); 
                 st =con.createStatement();

            }catch(ClassNotFoundException ex){
		Erro  err = new Erro(true, true);
                err.setIdErroDB(2106);
                err.setSystemPrintMessage("    ERRO "+ex);
                err.setInterfaceMessage("ERRO : Não foi encontrado o driver do connector mysql  ");
                err.Execute();
            } catch (SQLException ex) {
                Erro  err = new Erro(true, true);
                err.setIdErroDB(2107);
                err.setSystemPrintMessage("    ERRO "+ex);
                err.setInterfaceMessage("ERRO : Erro em manipulacao das variaveis em SQL");
                err.Execute();
            }
        }     
         
         /*close()
         *Fecha conexao com o banco de dados do mysql; 
         */
         private static void close()
          {   
            try {
                    if(con!=null) {con.close();con=null;}
                    if(st!=null)  {st.close(); st=null;}
                    if(res!=null) {res.close();res=null;} 
                  } catch (SQLException ex) {
                     Erro  err = new Erro(true, true);
                     err.setIdErroDB(2108);
                     err.setSystemPrintMessage("    ERRO "+ex);
                     err.setInterfaceMessage("ERRO : Erro em manipulacao das variaveis em SQL");
                     err.Execute();
                 }
          }
         
         /*
         *
         */
         
         
}//Fim de classe
