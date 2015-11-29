package CotacaoBmf;

import ConexaoBD.ConnectionDB;
import Dados.ConfiguracoesIniciais;
import Dados.DadosDB;
import Erros.Erro;
import InterfaceGraf.IFLoadBar1;
import ManutencaoBD.ControledeInsercao;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;



public class BDBmf extends FileBmf {
	
	
	public BDBmf(String filename) {super(filename);}
	public BDBmf(int ano){super (ano);}
	public BDBmf(int dia ,int mes , int ano){super(dia, mes ,ano);}
	private static IFLoadBar1 if_insercao_cotas_bmf =new IFLoadBar1();
        private boolean modoDeFecharGUIManual=false;  	
        private boolean apagarArquivo=true;
        private boolean comprovanteDePermissao=false;  //garante que foi pedida a permissao .
        private boolean permissao=false;                //permissao concedida ou negada.
        
	 public void apagarArquivosAposInsercao(boolean apagar)
        {
            apagarArquivo=apagar;
        }
        public void setManualCloseGUI(boolean modomanual)
        {
            modoDeFecharGUIManual=modomanual;
        }
        public void  CloseGUI(){
            if_insercao_cotas_bmf.close();
        }
        
        public boolean insertall ()throws FileNotFoundException, IOException, BDBmfException{
	      if (!ConnectionDB.gerenciador()) return  false;
              return(insertall(0));
	}
	
	private boolean insertall(int firstline) throws FileNotFoundException, IOException, BDBmfException {
                   double temp;
                   int numvetores; 
                   int numlinhas=super.numerodelinhas();
                   if_insercao_cotas_bmf.setTitle("INSERINDO COTAÇÃO ");
                   if_insercao_cotas_bmf.setTitleMessage("INSERINDO COTAÇÃO BOVESPA NO BANCO DE DADOS");
                   if_insercao_cotas_bmf.setTempodeTermino(true);
                   if_insercao_cotas_bmf.setVisible(true);
                    Runtime comand =Runtime.getRuntime();
		if(firstline<=numlinhas)
		{  
                   temp=(0.05/250)*comand.freeMemory();
                   numvetores=(int)temp;
		   insertlines(firstline,firstline+numvetores); //segundo parametro � quantas linhas do arquivo v�o ser inseridas de cada vez.
		   return insertall(firstline+numvetores+1); 
		}
		else {
                        ControledeInsercao.Conclusao(super.filename);
                        comprovanteDePermissao=false;   //validade da permissao vencida .
                        permissao=false;
                        if(modoDeFecharGUIManual)
                            if_insercao_cotas_bmf.zeraBarValue();
                        else
                            CloseGUI();
                        if(apagarArquivo)
                            super.deletaArquivo();
                        System.gc();
			return true;
		}
	}
	
	private void insertlines(int firstline, int lastline) throws FileNotFoundException, IOException, BDBmfException{
          
             int Count;
             DadosDB.getInstance();
             String id_empresa=DadosDB.getDataBaseColumnName(1, 1);
             String tableCias=DadosDB.getDataBaseTableName(1);
             String tablePapelBovespa=DadosDB.getDataBaseTableName(11);
             String tablePapelDesconhecido=DadosDB.getDataBaseTableName(12);
             String colPapelBovespa="";
             String colPapelDesconhecido="";

             for(int i=2;i<=29;i++)
                 colPapelBovespa=colPapelBovespa+DadosDB.getDataBaseColumnName(11, i)+",";
             colPapelBovespa=colPapelBovespa+DadosDB.getDataBaseColumnName(11, 30);
             
             for(int i=2;i<=29;i++)
                 colPapelDesconhecido=colPapelDesconhecido+DadosDB.getDataBaseColumnName(12, i)+",";
             colPapelDesconhecido=colPapelDesconhecido+DadosDB.getDataBaseColumnName(12, 30);
             
             String verifica_cia="select "+id_empresa+" from "+ConfiguracoesIniciais.getNomeDatabasepadrao()+"."+tableCias+" where "+id_empresa+"=";
             String verifica;
             String table_papel;
             String table_papel_cia="."+tablePapelBovespa+"("+colPapelBovespa+") ";
             String table_papel_de_desconhecido="."+tablePapelDesconhecido +"("+colPapelDesconhecido +") ";
             StringBuilder query= new StringBuilder("");
             
             
                 int supernumerodelinhas=super.numerodelinhas();
                 if(lastline>supernumerodelinhas){
	         lastline=supernumerodelinhas-1;
		}
	      String[] lines=super.getline(firstline,lastline);
              DadosBmf dados =new DadosBmf(lines);
              if(!comprovanteDePermissao)
              {
                  permissao = ControledeInsercao.Permissao(super.filename ,"COTACAOBMF",tablePapelBovespa,tablePapelDesconhecido);
                  comprovanteDePermissao=true;
              }
              if(permissao==true)
              {
                 ConnectionDB.gerenciador();
		 try{
                 
                     for(Count=0;Count<lines.length;Count++)
                     { 
                         if(lines[Count].startsWith("01"))
                         {
                                 table_papel=table_papel_cia;
                                 verifica=verifica_cia+""+dados.codneg_name[Count];
                             
                             
                              ConnectionDB.result=ConnectionDB.state.executeQuery(verifica);
                              if(ConnectionDB.result.next()){/*não faz nada*/}
                              else {table_papel=table_papel_de_desconhecido;}
                                        
                                  query = new StringBuilder("INSERT INTO "); //"INSERT INTO "+TABLE+" VALUES"+ ()* from pessoas";" +";" +;
			          query.append(ConfiguracoesIniciais.getNomeDatabasepadrao());
                                  query.append(table_papel);
                                 query.append(" VALUES ( ");
                                 query.append(dados.codneg_name[Count]); query.append(",");
                                 query.append(dados.codbdi[Count]); query.append(",");
                                 query.append(dados.tpmerc[Count]); query.append(",");
                                 query.append(dados.indopc[Count]); query.append(",");
                                 query.append("'"+super.filename+"'"); query.append(",");
                                 query.append(dados.especi[Count]); query.append(",");
				 query.append(dados.data[Count]); query.append(",");   
                                 query.append(dados.codneg_tipo[Count]); query.append(",");
                                 query.append(dados.prazot[Count]); query.append(",");
                                 query.append(dados.modref[Count]); query.append(",");
                                 query.append(dados.preabe[Count]); query.append(",");
                                 query.append(dados.premax[Count]); query.append(",");
                                 query.append(dados.premin[Count]); query.append(",");
                                 query.append(dados.premed[Count]); query.append(",");
                                 query.append(dados.preult[Count]); query.append(",");
                                 query.append(dados.preofc[Count]); query.append(",");
                                 query.append(dados.preofv[Count]); query.append(",");
                                 query.append(dados.totneg[Count]); query.append(",");
                                 query.append(dados.quatot[Count]); query.append(",");
                                 query.append(dados.voltot[Count]); query.append(",");
                                 query.append(dados.preexe[Count]); query.append(",");
                                 query.append(dados.datven[Count]); query.append(",");
                                 query.append(dados.fatcon[Count]); query.append(",");
                                 query.append(dados.ptoexe[Count]); query.append(",");
                                 query.append(dados.codisi_pais[Count]); query.append(",");
                                 query.append(dados.codisi_tipo[Count]); query.append(",");
                                 query.append(dados.codisi_indem[Count]); query.append(",");
                                 query.append(dados.codisi_digcon[Count]); query.append(",");
                                 query.append(dados.dismes[Count]); query.append(")"); 
				 ConnectionDB.state.execute(query.toString());         
                         }
                         if_insercao_cotas_bmf.if_aux(supernumerodelinhas,"Inserindo arquivo Cotação Bovespa :         "+super.filename);
                     }
		}catch(SQLException ex) {
                    Erro  err = new Erro(true, false);
                    err.setIdErroDB(5101);
                    err.setSystemPrintMessage("    ERRO "+ex.getMessage());
                    err.Execute();   
                    throw new BDBmfException(super.filename);
                   }
              }
        }
        
        
        

            public class BDBmfException extends Exception {    
          private String arquivo="";

          public BDBmfException(){
                    super();
            }

          public BDBmfException(String arquivo){
              super();  
              this.arquivo=arquivo;    
            }

            @Override
            public String toString(){
                    return "Ocorreu algum erro durante  a inserção do arquivo da cotação bovespa "+arquivo;
            }

            @Override
            public String getMessage(){
                    return "Ocorreu algum erro durante  a inserção do arquivo da cotação bovespa "+arquivo;

            }
    }
        
        
        
   }
                     




