package Tesouro;

import java.sql.SQLException;

import ManutencaoBD.ControledeInsercao;
import Tesouro.TesouroNet.TesouroNetException;

import ConexaoBD.ConnectionDB;
import ConexaoBD.FuncoesDB;
import ConexaoBD.FuncoesDB.FuncoesDBException;
import ConexaoNET.HTMLParser;
import Dados.ConfiguracoesIniciais;
import Dados.DadosDB;
import Erros.Erro;


public class BDTesouro {
	
	private TesouroNet t;
	private HTMLParser u;
	private FuncoesDB funcao;
        
        private final String tablePrecoTaxa;
	private final String tableTitulos;
        private final String tableRentabilidade;
        
        private String colunaTitulos="" ;
	private String colunaPreco ="";
        private String colunaRenta ="";
        
	public BDTesouro(){
		
		u = new HTMLParser(true);
		t = new TesouroNet();
		funcao = new FuncoesDB();
                
                DadosDB.getInstance();
                tablePrecoTaxa=DadosDB.getDataBaseTableName(13);
                tableTitulos=DadosDB.getDataBaseTableName(15);
                tableRentabilidade=DadosDB.getDataBaseTableName(14);
                
                colunaTitulos=DadosDB.getDataBaseColumnName(15, 1);
                for(int i =2;i<=8;i++)
                    colunaPreco=colunaPreco+DadosDB.getDataBaseColumnName(13, i)+",";
                colunaPreco=colunaPreco+DadosDB.getDataBaseColumnName(13, 9);
                for(int i =2;i<=9;i++)
                    colunaRenta=colunaRenta+DadosDB.getDataBaseColumnName(14, i)+",";
                colunaRenta=colunaRenta+DadosDB.getDataBaseColumnName(14, 10);
                
		
	}
	
	public void getTables() {
	
            try {
                t.startStream();
                u.readHTML(t.in);
                t.closeStream();
                t = null;
                t = new TesouroNet(true);
                t.startStream();
                u.readHTML(t.in);
                t.closeStream();
                t = null;
            } catch (TesouroNetException e) {
                        Erro err = new Erro(true,true);
			err.setIdErroDB(e.getId());
			err.setSystemPrintMessage("ERRO: " + e.getMessage());
			err.setInterfaceMessage(e.getMessage());
			err.Execute();
            }
	}
	
	public void insertTesouro(){
		
		boolean perm;
		
		try {
			perm = ControledeInsercao.Permissao(u.listT.get(0).getData_referencia(), "Tesouro_preco", tablePrecoTaxa);
			if(perm){
				insertT1();
				insertT2();
			}
			perm = ControledeInsercao.Conclusao(u.listT2.get(0).getData_referencia());
			
		} catch (SQLException e) {
			Erro err = new Erro(true,true);
			err.setIdErroDB(18101);
			err.setInterfaceTitle("ERRO SQL");
			err.setInterfaceMessage(e.getMessage());
			err.setMessage(e.getMessage());
			err.Execute();
		}
	}
	
	private void insertT1() throws SQLException{

		
		String dados1 = null;
		String dados2[] = new String[8];
		
		
		
		for(int i = 0 ; i < u.listT.size() ; i++){
			
			dados1 = u.listT.get(i).getTitulo();
			
			dados2[0] = u.listT.get(i).getTitulo();
                        dados2[1] =	u.listT.get(i).getData_referencia();
			dados2[2] = u.listT.get(i).getVencimento();
			dados2[3] = u.listT.get(i).getTaxa_compra();
			dados2[4] = u.listT.get(i).getTaxa_venda();
			dados2[5] = u.listT.get(i).getPreco_dia_compra();
			dados2[6] = u.listT.get(i).getPreco_dia_venda();
                        dados2[7] =	u.listT.get(i).getData_referencia();
			
			
			try {
				ConnectionDB.state.execute("INSERT IGNORE INTO "+ConfiguracoesIniciais.getNomeDatabasepadrao()+"."+tableTitulos+"(" + 
											colunaTitulos + ") VALUES('"+ dados1 + "');");
				
				funcao.InsertInto(tablePrecoTaxa, colunaPreco , dados2);
				
			} catch (FuncoesDBException e) {

				Erro err = new Erro(true,true);
				err.setIdErroDB(18102);
				err.setInterfaceTitle("ERRO NA FUNCAO INSERT NA TABELA "+tablePrecoTaxa);
				err.setInterfaceMessage(e.getMessage() + "\n***" + u.listT2.get(i).getTitulo() + "  " + u.listT2.get(i).getVencimento());
				err.Execute();
			}
		}
	}
	
	private void insertT2() throws SQLException{
		
		String dados1;
		String dados2[] = new String[9];
		

		for(int i=0; i < u.listT2.size() ; i++){
				
			dados1 = u.listT2.get(i).getTitulo();
				
			dados2[0] = u.listT2.get(i).getTitulo();
                        dados2[1] = u.listT2.get(i).getData_referencia();
			dados2[2] = u.listT2.get(i).getVencimento();
			dados2[3] = u.listT2.get(i).getMes_ant();
			dados2[4] = u.listT2.get(i).getUlt_30();
			dados2[5] = u.listT2.get(i).getMeses_12();
			dados2[6] = u.listT2.get(i).getCompra_dia_ano();
			dados2[7] =	u.listT2.get(i).getVenda_dia_ano();
			dados2[8] = u.listT2.get(i).getData_referencia();
				
			try{
				ConnectionDB.state.execute("INSERT IGNORE INTO "+ConfiguracoesIniciais.getNomeDatabasepadrao()+"."+tableTitulos+"(" + 
							colunaTitulos + ") VALUES('"+ dados1 + "');");
					
				funcao.InsertInto(tableRentabilidade, colunaRenta, dados2);
					
			} catch (FuncoesDBException e) {
				
				Erro err = new Erro(true,true);
				//err.setIdErroDB(e.getId);
				err.setInterfaceTitle("ERRO NA FUNCAO INSERT NA TABELA TES_RENTABILIDADE");
				err.setInterfaceMessage(e.getMessage() + "\n***" + u.listT2.get(i).getTitulo() + "  " + u.listT2.get(i).getVencimento());
				err.Execute();
			}
		}
	}
        
         public String getData(){
             return u.listT.get(0).getData_referencia();
        }
	
	
}
