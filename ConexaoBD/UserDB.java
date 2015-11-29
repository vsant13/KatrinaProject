package ConexaoBD;

import Dados.ConfiguracoesIniciais;
import Dados.Diretorios;
import Erros.Erro;
import InterfaceGraf.IFUsuarioDB;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;




public class UserDB {
	
	protected static String DatabaseName ;
	protected static String Hostname;
	protected static String Port;
	protected static String Username ;
	protected static String Password;
	protected static String Url;
        protected static String Url_merc;
        public static boolean permissao = false ;
        public static boolean aceito = false ;
        
        private UserDB(){
                setUserDB();
	}

        
        public static String getDatabaseName(){
        getUserDB();
            return DatabaseName;
        }
	public static String getHostname(){
        getUserDB();
             return Hostname;
        }
        public static String getPort(){
        getUserDB();
            return Port;
        }
        public static String getUsername(){
        getUserDB();
            return Username;
        }
        public static String getPassword(){   
        getUserDB();    
               return Password;
        }
        public static String getUrl(){    
        getUserDB();
            return Url;
        }
        public static String getUrl_merc(){    
        getUserDB();
            return Url_merc;
        }
      
  
        
        public static UserDB getUserDB(){
        
             UserDB user;
             user = new UserDB();
           return user;
        }
        
        private static boolean tentaconexao(){
          
            ConnectionDB.SEMSCHEMA=true;
          if(ConnectionDB.gerenciador())
          { 
              ConnectionDB.SEMSCHEMA=false; //volta ao original
              ConnectionDB.close();
              return true;
          }
          else 
          {
              ConnectionDB.SEMSCHEMA=true;  //volta ao original
              ConnectionDB.close();
              return false;
          }  
        }
       
       
        
        private static void setUserDB() {
        
        if(aceito==false)
                    {
                        int i=0,j=0;
                        String arraylines[] = new String[5]; 
                        String[] palavra = new String[5];
                        String str_temp;
                         try{
                                         File arq = new File(Diretorios.path_datafiles_config()+"UserDB.txt");
                                         arq.createNewFile();
                                         BufferedReader out = new BufferedReader(new FileReader(arq));
                                         while(out.ready()){ arraylines[i]=out.readLine(); 		
                                                             i++;
                                                           } 
                                         while(j<=i){ 
                                                      if(arraylines[j]!=null){    StringTokenizer token= new StringTokenizer(arraylines[j] ,"$");
                                                                                  str_temp=token.nextToken();
                                                                                  str_temp=token.nextToken();
                                                                                  //segunda palavra;
                                                                                  palavra[j]=str_temp;
                                                                             }
                                                      j++;
                                                    }
                                         out.close();
                               } catch (FileNotFoundException e) 
                                    { 
                                        //Não é possivel ocorrer essa opção , se o arquivo não existir ele é criado 
                                        // por arq.createNewFile(), se esse falhar lancará IOException e nunca chegará 
                                        //a tentar ler o arquivo e lancar FileNotFoundException .       
                                    } catch (IOException ex) {
                                        Erro err = new Erro(true,true);
                                        err.setIdErroDB(6101);
                                        ex.getStackTrace();
                                        err.setSystemPrintMessage("ERRO : "+ ex.getMessage());
                                        err.setInterfaceMessage("ERRO : Nas configurações de usuario do banco de dados ");
                                        err.setExitAplication(true);
                                        err.Execute();
                                    }
                                       
                       //DatabaseName : DEFINIDO POR Dados.ConfiguracoesIniciais ;
                        DatabaseName= ConfiguracoesIniciais.getNomeDatabasepadrao();
                        Username=palavra[0];
                        Hostname=palavra[1];
                        Port=palavra[2];
                        Password=palavra[3];
                        Url="jdbc:mysql://"+Hostname+":"+Port;
                        Url_merc="jdbc:mysql://"+Hostname+":"+Port+"/"+DatabaseName;
                       // jdbc:mysql://localhost:3306/test
                        
                        
                       if(tentaconexao()==false)
                       {
                            IFUsuarioDB user = new IFUsuarioDB();
                            permissao= user.showConfirmDialog(); 
                            setUserDB();
                       }
                       else aceito=true;
                       
                }
        }





}

