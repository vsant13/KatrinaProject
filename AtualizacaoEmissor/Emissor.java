
 /* To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AtualizacaoEmissor;


import ConexaoBD.UserDB;
import Dados.ConfiguracoesIniciais;
import Dados.DadosDB;
import Dados.DadosEmissor;
import Dados.Diretorios;
import Erros.Erro;
import ManutencaoBD.RelePapeisdeDesconhecidos;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author Erico
 */
public class Emissor {
  
           protected static final String  default_file_emissor = "EMISSOR_CVM_";
           protected static final String default_file_spw ="ARQUIVO_CVM_SPW_CI_";

        public static boolean BaixarAtualizacoes(){
               DownArqsdeAtualizacao down;
               try {
                   down = new DownArqsdeAtualizacao();
               } catch (IOException ex) {
                Erro  err = new Erro(true, false);
                err.setIdErroDB(11103);
                err.setSystemPrintMessage("    ERRO "+ex.getMessage());
                err.Execute();
                return false;
               }
               return down.DOWNCOMPLETE;
        }

        
        public static void AtualizaCia(){
            int year;
            GregorianCalendar calendar = new GregorianCalendar();
            year =calendar.get(GregorianCalendar.YEAR);
            AtualizaCia(year);
        }
           
        public static void AtualizaCia(int Ano_Do_Arquivo_Emissor){      
           String PathArquivoEmissor=Diretorios.path_datafiles_config_emissor()+default_file_emissor+Ano_Do_Arquivo_Emissor;
           String PathArquivoSPW=Diretorios.path_datafiles_config_emissor()+default_file_spw+Ano_Do_Arquivo_Emissor;
            
           ComparaArquivos comp=new ComparaArquivos();  
           comp.setArquivo1(PathArquivoEmissor);
           comp.setArquivo2(PathArquivoSPW);
         
                
                int max;
                int res;
                
                max= Lastcreate_db_file();
                res=escrevecia(max+1,comp);
               
                String comando;
                Runtime comand =Runtime.getRuntime();
                String Num;
                String DefaultUTF8=" --default-character-set=utf8"; //ARQUIVOS EM SQL DEVEM ESTAR SALVOS EM UTF8.
 
                try{   
              
                        for(int i=max+1;i<res;i++)
                        {
                               if(i<10) Num="00"+i;      
                                else {
                                      if(i<100) Num="0"+i;    
                                      else        Num=i+"";
                                     }
                               String pathsqlfile= Diretorios.path_datafiles_config_create_BD()+ "create_database_"+Num + ".sql";
                               
                                if((((System.getProperty("os.name")).toLowerCase()).trim()).equals("linux"))
                               {
                                   comando = "sh "+Diretorios.path_datafiles_config_create_BD()+"MaSLinAtualizaCia.sh "+UserDB.getUsername()+" "+UserDB.getPassword()+
                                           " "  +pathsqlfile+" "+ DefaultUTF8;
                                    Process exec = comand.exec(comando);
                                    exec.waitFor();
                                    exec.getErrorStream();
                                    System.out.println(comando);
                               }
                               else
                               {
                                   comando ="cmd /c mysql --user="+UserDB.getUsername()+" --password="+UserDB.getPassword()+" < "+pathsqlfile+ DefaultUTF8;
                                    Process exec = comand.exec(comando);
                                    exec.waitFor();
                                    exec.getErrorStream();
                                    System.out.println(comando);
                               }
                           
                        }
                    RelePapeisdeDesconhecidos.Releitura(); //Manuteção do banco de dados;
                    }catch( IOException | InterruptedException ex){
                    Erro  err = new Erro(true, true);
                    err.setIdErroDB(11101);
                    err.setSystemPrintMessage("    ERRO "+ex.getMessage());
                    err.setInterfaceMessage("ERRO : Erro ao executar script em SQL de criação do banco.");
                    err.Execute();
                    }
                
             comando=null;
             System.gc();
        }
        
     private static int escrevecia(int l ,ComparaArquivos comp){
		
                 boolean resposta[][];
               try {
                   resposta = comp.Comparacao();
               } catch (FileNotFoundException  ex) {
                    Erro  err = new Erro(true, true);
                    err.setIdErroDB(11102);
                    err.setSystemPrintMessage("    ERRO "+ex.getMessage());
                    err.setInterfaceMessage("ERRO : Erro ao tentar comparar arquivos de atualização de emissores.");
                    err.Execute();
                    return 0;
               } catch (IOException ex) {
                    Erro  err = new Erro(true, true);
                    err.setIdErroDB(11103);
                    err.setSystemPrintMessage("    ERRO "+ex.getMessage());
                    err.setInterfaceMessage("ERRO : Erro ao tentar comparar arquivos de atualização de emissores.");
                    err.Execute();
                  return 0;
               }
                 String query;
                 query=new String("INSERT IGNORE INTO "+DadosDB.getDataBaseTableName(1)+"\n" +
                                    "(" );
                                    
   
                for(int i=1;i<=58;i++)
                    query=query+DadosDB.getDataBaseColumnName(1, i)+",";
                query=query+DadosDB.getDataBaseColumnName(1, 59);
                query=query+")"+"values" +"(";
                 
             try{             
                 int m=0;
                 int n=0;
                  for(int k=0;k<resposta.length;k++)
                  {   
          
                      PrintWriter pw;
                      if(l<10)
                                     pw =new PrintWriter(new FileWriter(Diretorios.path_datafiles_config_create_BD()+"create_database_00"+l+".sql"));
                      else {
                            if(l<100)pw =new PrintWriter(new FileWriter(Diretorios.path_datafiles_config_create_BD()+"create_database_0"+l+".sql"));    
                            else     pw =new PrintWriter(new FileWriter(Diretorios.path_datafiles_config_create_BD()+"create_database_"+l+".sql"));
                           }
                      l++;
                      pw.println("USE "+ConfiguracoesIniciais.getNomeDatabasepadrao()+";"); 
                   for(int j=m;j<=m+25000;j++)
                        { 
                          if(k>=resposta.length){
                            pw.close();
                            break;
                          } 
                          k++;
                          for(int i=0;i<resposta[j].length;i++)
                          {
                              if(resposta[j][i]==true )
                              { 
                                  if(!comp.dadoscia.situacao[i].toString().equals("\"CANCELADA\""))
                                  {    
                                    n++;
                                    pw.print ( query); 
                                    pw.print(comp.dadosbmf.codigobmf[j] + "," +
                                    comp.dadoscia.cod_cvm[i]   + "," +
                                    comp.dadosbmf.nome[j]      + "," +
                                    comp.dadoscia.denominacao_comercial[i] + "," +
                                    comp.dadoscia.logradouro[i]+ "," +
                                    comp.dadoscia.complemento[i]+ "," +
                                    comp.dadoscia.bairro[i]+ "," +
                                    comp.dadoscia.cep[i]+ "," +
                                    comp.dadoscia.municipio[i]+ "," +
                                    comp.dadoscia.uf[i]+ "," +
                                    comp.dadoscia.ddd[i]+ "," +
                                    comp.dadoscia.telefone[i]+ "," +
                                    comp.dadoscia.fax[i]+ "," +
                                    comp.dadoscia.denominacao_anterior[i]+ "," +
                                    comp.dadoscia.setor_atividade[i]+ "," +
                                    comp.dadoscia.cnpj[i]+ "," +
                                    comp.dadoscia.dri[i]+ ",");
                                    pw.print(comp.dadoscia.auditor[i]+ "," +
                                    comp.dadoscia.quant_de_acoes_ordinarias[i]+ "," +
                                    comp.dadoscia.quant_de_acoes_preferenciais[i]+ "," +
                                    comp.dadoscia.situacao[i]+ "," +
                                    comp.dadoscia.data_da_situacao[i]+ "," +
                                    comp.dadoscia.tipo_papel1[i]+ "," +
                                    comp.dadoscia.tipo_papel2[i]+ "," +
                                    comp.dadoscia.tipo_papel3[i]+ "," +
                                    comp.dadoscia.tipo_papel4[i]+ "," +
                                    comp.dadoscia.tipo_papel5[i]+ "," +
                                    comp.dadoscia.tipo_papel6[i]+ "," +
                                    comp.dadoscia.controle_acionario[i]+ "," +
                                    comp.dadoscia.data_de_registro[i]+ "," +
                                    comp.dadoscia.data_de_cancelamento[i]+ "," +
                                    comp.dadoscia.mercado[i]+ "," +
                                    comp.dadoscia.bolsa1[i]+ "," +
                                    comp.dadoscia.bolsa2[i]+ ",");
                                    pw.print(comp.dadoscia.bolsa3[i]+ "," +
                                    comp.dadoscia.bolsa4[i]+ "," +
                                    comp.dadoscia.bolsa5[i]+ "," +
                                    comp.dadoscia.bolsa6[i]+ "," +
                                    comp.dadoscia.bolsa7[i]+ "," +
                                    comp.dadoscia.bolsa8[i]+ "," +
                                    comp.dadoscia.bolsa9[i]+ "," +
                                    comp.dadoscia.motivo_do_cancelamento[i]+ "," +
                                    comp.dadoscia.patrimonio_liquido[i]+ "," +
                                    comp.dadoscia.data_do_patrimonio[i]+ "," +
                                    comp.dadoscia.e_mail[i]+ "," +
                                    comp.dadoscia.nome_setor_atividade[i]+ "," +
                                    comp.dadoscia.data_da_acao[i]+ ",");
                                    pw.println(comp.dadoscia.tipo_negocio1[i]+ "," +
                                    comp.dadoscia.tipo_negocio2[i]+ "," +
                                    comp.dadoscia.tipo_negocio3[i]+ "," +
                                    comp.dadoscia.tipo_negocio4[i]+ "," +
                                    comp.dadoscia.tipo_negocio5[i]+ "," +
                                    comp.dadoscia.tipo_negocio6[i]+ "," +
                                    comp.dadoscia.tipo_mercado1[i]+ "," +
                                    comp.dadoscia.tipo_mercado2[i]+ "," +
                                    comp.dadoscia.tipo_mercado3[i]+ "," +
                                    comp.dadoscia.tipo_mercado4[i]+ "," +
                                    comp.dadoscia.tipo_mercado5[i]+ "," +
                                    comp.dadoscia.tipo_mercado6[i]+ ");" );
                                    //break;  
                                  }
                                }
                              }
                            }m=m+25000;
                             pw.print ("");
                            pw.close();
                            
                          }
                        String add;
                        int ll=l-1;
                       if(ll<10)
                           add="00"+(ll);
                       if(l<100)
                           add="0"+(ll);
                       else
                           add=""+(ll);

                       DadosEmissor.setNomeArquivo("create_database_"+add);
                       Calendar cal = Calendar.getInstance();
                       int anoAtual= cal.get(Calendar.YEAR);
                       int mesAtual= cal.get(Calendar.MONTH)+1;
                       int diaAtual=cal.get(Calendar.DAY_OF_MONTH);
                       DadosEmissor.setDataAtualizado(diaAtual, mesAtual, anoAtual);
		   }catch(IOException e){}    
                    return l;   
	}
     
      private static int Lastcreate_db_file(){
      
          File dir = new File(Diretorios.path_datafiles_config_create_BD());
                String file[]= dir.list();
                String[] create_db_file=new String[file.length];
                Integer value;
                int max=0;
                int j=0;
                for(int i=0;i<file.length;i++)
                {
                    if(file[i].indexOf("create_database_")!=-1) 
                    {
                        create_db_file[j]= file[i];
                        value = Integer.parseInt(create_db_file[j].substring(16,19));
                        if(max<value) max=value;   
                        j++;
                    }
                }
                return max;
      }
     
   
     }
     
   
