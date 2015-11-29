


package Dados;

import ConexaoBD.UserDB;
import Erros.Erro;
import Erros.IFSimple;
import Erros.MyDialogs;
import InterfaceGraf.IFLoadBar1;
import java.io.File;
import java.io.IOException;
import java.text.Collator;
import java.util.Arrays;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;



public class ConfiguracoesIniciais {
    private static String NomeDatabasepadrao;                                                            // Nome do Schema Utilizado pelo programa; 
    private static String backupfile;                                                                    // Nome do arquivo de backup a ser utilizado na configuracao;                   
   
    /*Config_BancodeDados()
    Prepara um Novo Banco de Dados (new Schema) com nome da variavel NomeDatabasepadrao; 
    Retorna :
    0 se é um novo database zerado;
    1 se é um novo database a partir de um backup;
    -1 se cancelar metodo;
    */ 
    public static int Config_BancodeDados() throws ConfiguracoesIniciaisException, InterruptedException{ 
        ConfiguracoesDB config= new ConfiguracoesDB();                                                                                       //chama  ConfiguracoesDB;
        File dir_backup = new File(Diretorios.path_datafiles_config_backup_BD());                                                                       //chama  File;
               if(config.isDataBase(NomeDatabasepadrao)==false)                                                      //verifica se o banco existe , se ele não existe:;
               {
               int resposta=dobackup(dir_backup);                                                 //chama o metodo dobackup ,pergunta se é para restaurar o backup;
                   if(resposta==1)
                   {
                       config.restoreDataBase(NomeDatabasepadrao, backupfile);                                 // entra no banco com os valores do backup selecionado;
                       System.out.println("Fim de Config_BancodeDados da classe ConfiguracoesIniciais");                                     //avisa o fim do metodo;
                       return resposta;                                                                                                                 // finaliza o metodo;
                   }
                   else
                   {   if(resposta==0)
                        config.CreateDataBase(NomeDatabasepadrao); // cria o banco de dados;
                       else
                        return resposta;   //se cancelado a operacao.
                   }                                                                             
               } 
               else                                                                                                                            // se o banco ja existe: ;
               {

                    IFSimple ifsimple= new IFSimple("Já existe database: "+ NomeDatabasepadrao,"Deve-se apagar o database antigo para continuar \n Deseja fazer backup e apagar o database antigo ?",MyDialogs.OK_CANCEL_OPTION);
                    MyDialogs mydialog= new MyDialogs(false, true);
                    mydialog.setInterface(ifsimple);
                    int i=mydialog.showMySimpleDialog();
                    if(i!=0)
                        return -1;
                    else
                        config.backupDataBase(NomeDatabasepadrao);                                                                         // faz um backup do banco atual ;
                       config.DropDataBase(NomeDatabasepadrao); 
                                                                                                      // apaga o banco atual;
                   int resposta=dobackup(dir_backup);                                            //chama o metodo dobackup ,pergunta se é para restaurar o backup;
                   if(resposta==1)
                   {
                       config.restoreDataBase(NomeDatabasepadrao, backupfile);                                  // entra no banco com os valores do backup selecionado;
                       System.out.println("Fim de Config_BancodeDados da classe ConfiguracoesIniciais");                                         //avisa o fim do metodo;
                       return resposta;                                                                                                                     // finaliza o metodo;
                   }
                   else
                   {   if(resposta==0)
                        config.CreateDataBase(NomeDatabasepadrao);
                       else
                       return resposta;
                   }                                                                             // cria o banco de dados;                                                                                 // cria o banco de dados;
               }
          
     Runtime comand =Runtime.getRuntime();                                                                                                              //chama Runtime;
     File dir_create = new File(Diretorios.path_datafiles_config_create_BD());                                                                           //chama File;
     String file[]= dir_create.list();                                                                                          //cria lista dos arquivos no diretorio;
     Arrays.sort(file, Collator.getInstance()); 
     String[] create_db_file=new String[file.length];                                                                                             //cria alguns objetos String[];
     String[] pathsqlfile=new String[file.length];
     String[] comando= new String[file.length];
     String[] comandoLin= new String[file.length];
     String DefaultUTF8=" --default-character-set=utf8";                                                                  //ARQUIVOS EM SQL DEVEM ESTAR SALVOS EM UTF8.
    
     int j=0;
     boolean isLinux;
             if((((System.getProperty("os.name")).toLowerCase()).trim()).equals("linux"))
                 isLinux=true;
             else
                 isLinux=false;
          
     
     for(int i=0;i<file.length;i++)                                                                      //Verifica quantos arquivos são do tipo "create_database_..." ;
     {                                                                                                                   // E escreve os comandos para Runtime executar;
         if(file[i].indexOf("create_database_")!=-1)
         {
             
             create_db_file[j]= file[i];
             pathsqlfile[j]= Diretorios.path_datafiles_config_create_BD()+ create_db_file[j]+"";
             comando[j] = "cmd /c mysql --user="+UserDB.getUsername()+" --password="+UserDB.getPassword()+"<"+pathsqlfile[j]+ DefaultUTF8;
             comandoLin[j]="sh "+Diretorios.path_datafiles_config_create_BD()+"/MaSLinCreateDb.sh "+
                     UserDB.getUsername()+" "+UserDB.getPassword()+" "+pathsqlfile[j]+" "+DefaultUTF8;
             j++;                                                                                                    //j= numero de arquivos com "create_database_..."
         }
     }
     
   try{
                IFLoadBar1 ifcreateDB = new IFLoadBar1();                                                                       //Chama a interface Gráfica IFLoadBar1 
                ifcreateDB.setTitle("EXECUTANDO ARQUIVOS SQL");                                                                 //define titulo da caixa de mensagem;
                ifcreateDB.setTitleMessage("EXECUTANDO OS ARQUIVOS DE EXTENSÃO SQL");                                                           //define titulo da mensagem;
                ifcreateDB.setVisible(true);                                                                                                            //deixa visivel    
                for(int i=0;i<j;i++)                                                                  // até chegar j , j= numero de arquivos com "create_database_..."
                {      if(!comando[i].isEmpty())                                           
                       {
                         if(isLinux)
                         {
                             Process exec = comand.exec(comandoLin[i]);    //executa comando de criacao do banco;
                            exec.waitFor();
                            ifcreateDB.if_aux(j, "Arquivo executado com sucesso :  "+create_db_file[i]);                                // auxilia a interface grafica;
                            exec.getErrorStream() ; 
                            System.out.println("    "+comandoLin[i]);
                         }
                         else
                         {
                            Process exec = comand.exec(comando[i]);                                                              //executa comando de criacao do banco;
                            exec.waitFor();
                            ifcreateDB.if_aux(j, "Arquivo executado com sucesso :  "+create_db_file[i]);                                // auxilia a interface grafica;
                            exec.getErrorStream() ; 
                             System.out.println("    "+comando[i]);
                         }
                       }
                }
                ifcreateDB.close();                                                                                                                  // fecha interface;
               }catch(  IOException ex){
                config.DropDataBase(NomeDatabasepadrao);
                Erro  err = new Erro(true, true);
                err.setIdErroDB(1101);
                err.setSystemPrintMessage("    ERRO "+ex.getMessage());
                err.setInterfaceMessage("ERRO : Erro ao executar script em SQL de criação do banco.");
                err.Execute();
                throw new ConfiguracoesIniciaisException();
               }
        comando=null;                                                                                                                                //libera memoria;
        System.gc();
        System.out.println("Fim de Config_BancodeDados da classe ConfiguracoesIniciais");                                                       //avisa o fim do metodo;
        return 0;
    
 }// Fim de metodo
    
    
    /*dobackup(File diretoriodebackup)
    Pergunta , chamando interfaces graficas se é para construir um Novo Banco de Dados (new Schema) a partir de um antigo (com arquivo de buckup) 
    retorna -1 se cancelar 
    retorna 0 se não é para fazer backup
    retorna 1 se é para fazer backup
    */
    private static int dobackup(File diretoriodebackup){
                   System.out.println("Inicia dobackup da classe ConfiguracoesIniciais");                                                  // Avisa entrada de metodo; 
                  int resultado=-1;
                  int numerodebackups= diretoriodebackup.list().length;                                                             //  Se existe arquivos no diretorio;  
                  if (numerodebackups!=0)                                                                                                     // Se tiver arquivos:;
                  {
                           boolean decisao=false;
                          while(!decisao)                                                                                   // Enquanto o usuario nao toma decisao:;
                         {
                            int resposta = JOptionPane.showConfirmDialog(null,         // pergunta ao usuario se que restaurar o backup(SIM) ou criar novo banco(NAO);.
                            "Existe arquivos de backup do Banco.Deseja carrega-los ao invés de criar um novo banco("+NomeDatabasepadrao+")?",
                            "Carregar arquivos de backup",
                            JOptionPane.YES_NO_OPTION);
                            if(resposta==JOptionPane.CLOSED_OPTION)
                                return(-1);
                            if(resposta==0)                                                                                                   // se a resposta for SIM;
                            {
                               decisao=FileChooser(diretoriodebackup) ;                                      //chama o metodo FileChooser, escolher arquivo de backup;
                               if(decisao)                                                                                          // Se o usuario escolheu um arquivo;
                               {
                                   resultado=1;                                                                                                    // Retorna true;
                               }
                            }
                            else                                                                                                              //Se a resposta for NAO;
                            {
                                resultado=0;                                                                                                       //Retorna false;
                                decisao =true;                                                                                          //condicao para sair do while;
                            }
                         }
                  }
                  System.out.println("Fim de dobackup da classe ConfiguracoesIniciais");                                                       //avisa o fim do metodo;
                  return resultado;                 
    }// Fim de metodo
    
    
    
    /*FileChooser(File diretorio)
    Chama uma Interface graficas para busca de arquivo nos diretorios  
    */
    private static boolean FileChooser(File diretorio){
        System.out.println("Inicia FileChooser da classe ConfiguracoesIniciais");                                                           // Avisa entrada de metodo;
        JFileChooser fileChooser = new JFileChooser(diretorio);                                                                                  //Chama JFileChooser;
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Arquivo SQL", "sql");                                                     //filtro de pesquisa;
        fileChooser.setFileFilter(filter);                                                                                                            // set filtro;
        int option = fileChooser.showOpenDialog(null);                                                                                  //deixa visivel a interface;
        if (option == JFileChooser.APPROVE_OPTION) {                                                                              // verifica se apertou o botão open;
            File file = fileChooser.getSelectedFile();                                                                                    // pega o arquivo selecionado;
            backupfile=file.getPath();                                                                               // pega o camimho (path) do arquivo selecionado;  
            System.out.println("Fim de FileChooser da classe ConfiguracoesIniciais");                                                         //avisa o fim do metodo;
            return true;                                                                                                     // retorna true , tem o caminho do backup;
        }
        System.out.println("Fim de FileChooser da classe ConfiguracoesIniciais");                                                                //avisa o fim do metodo;
        return false;                                                                                                 // retorna falso , nenhum arquivo foi selecionado;
 }// Fim de metodo
  
       
    public static String getNomeDatabasepadrao()
    {
        if(NomeDatabasepadrao==null)
        {
            DadosDB.getInstance();
            NomeDatabasepadrao=DadosDB.getDataBaseName();
        }
        return NomeDatabasepadrao;
    }
    
     public static class ConfiguracoesIniciaisException extends Exception {    

          public ConfiguracoesIniciaisException(){
                    super();
            }
            @Override
            public String toString(){
                    return "Ocorreu algum erro durante  as Configurações Iniciais ";
            }
            
            @Override
            public String getMessage(){
                    return "Ocorreu algum erro durante as Configurações Iniciais ";

            }
    }
    
     
    
}// Fim de classe
