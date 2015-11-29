package Dados;


import Erros.Erro;
import java.io.File;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;



public class Diretorios {
	
	private static String path;
	private static String path_temporario;
	private static String path_datafiles;
	private static String path_programas;
	private static String path_datafiles_cot_day;
	private static String path_datafiles_cot_his;
        private static String path_datafiles_cvm_data;
	private static String path_datafiles_config ;
        private static String path_datafiles_config_backup_BD;
        private static String path_datafiles_config_create_BD;
        private static String path_datafiles_config_emissor;
        private static String path_datafiles_config_dados_ci;
        private static String barraWinOuLin;
        private static char cbarraWinOuLin;
        
	public  Diretorios(){
		
                if(System.getProperty("os.name").toLowerCase().trim().equals("linux"))
                {
                    barraWinOuLin="/";
                    cbarraWinOuLin='/';
                }
                else
                {
                    barraWinOuLin="\\";
                    cbarraWinOuLin='\\';
                }
                 if(path==null)
		 {
		 String caminho=diretorioprincipal();
		 path=                     new String(caminho);
		 path_temporario =         new String(caminho + "temporario"+barraWinOuLin);
		 path_datafiles =          new String(caminho + "datafiles"+barraWinOuLin);
		 path_programas =          new String(caminho + "programas"+barraWinOuLin);
		 path_datafiles_cot_day=   new String(caminho + "datafiles"+barraWinOuLin+"cot_day"+barraWinOuLin);
		 path_datafiles_cot_his=   new String(caminho + "datafiles"+barraWinOuLin+"cot_his"+barraWinOuLin);
                 path_datafiles_cvm_data=   new String(caminho + "datafiles"+barraWinOuLin+"cvm_data"+barraWinOuLin);
		 path_datafiles_config =   new String(caminho + "datafiles"+barraWinOuLin+"config"+barraWinOuLin);
                 path_datafiles_config_backup_BD = new String(caminho + "datafiles"+barraWinOuLin+"config"+barraWinOuLin+"backup_BD"+barraWinOuLin);
                 path_datafiles_config_create_BD = new String(caminho + "datafiles"+barraWinOuLin+"config"+barraWinOuLin+"create_BD"+barraWinOuLin);
                 path_datafiles_config_emissor = new String(caminho + "datafiles"+barraWinOuLin+"config"+barraWinOuLin+"emissor"+barraWinOuLin);
                 path_datafiles_config_dados_ci = new String(caminho + "datafiles"+barraWinOuLin+"config"+barraWinOuLin+"dados_ci"+barraWinOuLin);
                       
                    try {
                        PrimeiraImportacia(path_datafiles_config_dados_ci);
                        PrimeiraImportacia(path_datafiles_config_backup_BD);
                        PrimeiraImportacia(path_datafiles_config_create_BD);
                        PrimeiraImportacia(path_datafiles_config_emissor);
                    } catch (DiretoriosException ex) {
                        Erro  err = new Erro(true, true);
                        err.setIdErroDB(3101);
                        err.setSystemPrintMessage("    ERRO "+ex);
                        err.setInterfaceMessage("ERRO GRAVE : Não foi encontrado o diretorio/arquivos de sistema.\n       Programa Corrompido\n \n");
                        err.setExitAplication(true);
                        err.Execute(); 
                    }
                        SecundaImportacia(path_temporario);
                        SecundaImportacia(path_programas);
                        SecundaImportacia(path_datafiles_cot_day);
                        SecundaImportacia(path_datafiles_cot_his);
                        SecundaImportacia(path_datafiles_cvm_data);
                 
                 }	    
	}


 public static String path(){
	 new Diretorios();
	 return (path);
 }
 public static String path_temporario(){
	 new Diretorios();
	 return (path_temporario);
 }
 public static String path_datafiles(){
	 new Diretorios();
	 return (path_datafiles);
 }
 public static String path_programas(){
	 new Diretorios();
	 return (path_programas);
 }
 public static String path_datafiles_cot_day(){
	 new Diretorios();
	 return (path_datafiles_cot_day);
 }
 public static String path_datafiles_cot_his(){
	 new Diretorios();
	 return (path_datafiles_cot_his);
 }
 public static String path_datafiles_cvm_data(){
	 new Diretorios();
	 return (path_datafiles_cvm_data);
 }
 public static String path_datafiles_config(){
	 new Diretorios();
	 return (path_datafiles_config);
 }
 public static String path_datafiles_config_backup_BD(){
	 new Diretorios();
	 return (path_datafiles_config_backup_BD);
 }
 public static String path_datafiles_config_create_BD(){
	 new Diretorios();
	 return (path_datafiles_config_create_BD);
 }
 public static String path_datafiles_config_emissor(){
	 new Diretorios();
	 return (path_datafiles_config_emissor);
 }
 public static String path_datafiles_config_dados_ci(){
     new Diretorios();
	 return (path_datafiles_config_dados_ci);
 }

 
       private void SecundaImportacia(String path){
       File dir = new File(path);
       if(!dir.exists())
           dir.mkdirs();
       }
       private void PrimeiraImportacia(String path) throws DiretoriosException{
       File dir = new File(path);
       if(!dir.exists())throw new DiretoriosException(path);
       }
 
 
	protected static String diretorioprincipal()
	{
            StringTokenizer string_lida = new StringTokenizer("");
            String caminho= new String("");
            String temp;
            if(System.getProperty("os.name").toLowerCase().trim().equals("linux"))
            {
                    File dir = new File (".");  
                try {
                    temp=dir.getCanonicalPath();
                } catch (IOException ex) {// Nunca vai entrar aqui!
                    temp="";
                }
            }
            else
                temp = System.getProperty("user.dir");

            string_lida = new StringTokenizer(temp,barraWinOuLin);  
		
		 while(string_lida.hasMoreTokens())
		 {
                    temp=string_lida.nextToken();
                    if(string_lida.hasMoreTokens())
                    {
                       caminho+= temp + cbarraWinOuLin;
                    }	
		  }
           if(System.getProperty("os.name").toLowerCase().trim().equals("linux"))
                     caminho="/"+caminho;
	   return caminho;
	}
        
        
   public class DiretoriosException extends Exception{
       private String path=" ";
    
   public DiretoriosException(){
                super();
        }
   public DiretoriosException(String path){
                super();
                this.path+=path+" ";
        }
        
        @Override
        public String toString(){
                return "Não foi encontrado o diretorio/arquivos"+path+"exigido para continuação do programa\n"+
                       "Sistema corrompido impossivel operar sem os arquivos de sistema.";
        }
        
        @Override
        public String getMessage(){
                return "Não foi encontrado o diretorio/arquivos"+path+"exigido para continuação do programa\n"+
                       "Sistema corrompido impossivel operar sem os arquivos de sistema.";
        }
}

        
        
}
