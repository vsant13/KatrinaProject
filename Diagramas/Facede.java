package Diagramas;

import ArquivosCvm.BDCvm;
import AtualizacaoEmissor.Emissor;
import ConexaoBD.ConnectionDB;
import ConexaoBD.FuncoesDB;
import ConexaoBD.Query;
import ConexaoBD.UserDB;
import CotacaoBmf.BDBmf;
import java.io.IOException;
import CotacaoBmf.FileBmfNet;
import Dados.ConfiguracoesIniciais;
import Dados.DadosDB;
import Dados.DadosEmissor;
import Dao.CiaDao;
import Erros.Erro;
import Erros.IFSimple;
import Erros.MyDialogs;
import Tesouro.BDTesouro;
import entity.Cia;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Facede {
	
        private final String ITR_CONTA = DadosDB.getDataBaseTableName(9) , FK_EMPRESA = DadosDB.getDataBaseColumnName(9, 2) , DENOMINACAO_SOCIAL = DadosDB.getDataBaseColumnName(1, 3),
                             CIAS =DadosDB.getDataBaseTableName(1) , ID_EMPRESA = DadosDB.getDataBaseColumnName(1, 1);
    
        private BDTesouro tes;
        private String[] listCias, listCodCvm;
   
        private FileBmfNet bvp;
        private final int anoInicial=2002;
        private final int anoAtual;
        private final int mesAtual;
        private final int diaAtual;
        private BDBmf bdbmf;
        
	
        
        public Facede() {
            
            UserDB.getUserDB(); 
            Calendar cal = Calendar.getInstance();
            anoAtual= cal.get(Calendar.YEAR);
            mesAtual= cal.get(Calendar.MONTH)+1;
            diaAtual=cal.get(Calendar.DAY_OF_MONTH);
        }
        
        
        
        /* ROTINAS DE ATUALIZAÇÃO DO TESOURO */
        public void setTesouro(){
            
            UserDB.getUserDB(); 
            tes = new BDTesouro();
            tes.getTables();
        }
        
        public String getTesouroInfo(){
            return tes.getData();
        }
        public void tesouroRotine(){
                 
            tes.insertTesouro();
        }
        /************************************/
        
        
        /*ROTINAS DE ATUALIZAÇÃO CVM         */

    /**
     *
     * @param cia
     */
    
        public boolean addCia(Cia cia){
            
            boolean result = false;
            try {
                BDCvm add = new BDCvm(cia);
                result = add.execute();
            } catch (BDCvm.BDCvmException ex) {
                Logger.getLogger(Facede.class.getName()).log(Level.SEVERE, null, ex);
            }
            return result;
        }
        
        public String[] getCias(){
            
            int i = 0;
            try {
                UserDB.getUserDB();
                
                FuncoesDB fun = new FuncoesDB();
                Query query = new Query();
                
                
                
                fun.Select(query.SELECT_DISTINCT +FK_EMPRESA + query.FROM + ConfiguracoesIniciais.getNomeDatabasepadrao()+"."+ITR_CONTA);
                
                ConnectionDB.result.last();
                listCodCvm = new String[ConnectionDB.result.getRow()];
                listCias = new String[listCodCvm.length];
                ConnectionDB.result.beforeFirst();
                
                while(ConnectionDB.result.next()){
                    listCodCvm[i] = ConnectionDB.result.getString(FK_EMPRESA);
                    i++;
                }
                i=0;
                while(i < listCias.length){
                    fun.Select(query.SELECT + DENOMINACAO_SOCIAL + query.FROM + CIAS + query.WHERE + ID_EMPRESA 
                               + "=" + "'" + listCodCvm[i] + "'");
                    ConnectionDB.result.next();
                    listCias[i] = ConnectionDB.result.getString(1);
                    i++;
                }
                
            } catch (FuncoesDB.FuncoesDBException ex) {
                Erro err = new Erro(true,true);
                err.setIdErroDB(12101);
                err.setSystemPrintMessage("    ERRO "+ex.getMessage());
                err.ComErroCypecad(true);
                err.Execute();
            } catch (SQLException ex) {
            
                Erro err = new Erro(true,true);
                err.setIdErroDB(12102);
                err.setSystemPrintMessage("    ERRO "+ex.getMessage());
                err.ComErroCypecad(true);
                err.Execute();
            }
             return listCias;
        }
        
        public void atualizaCias(){
            
            int i = 0;
            
            while(i < listCodCvm.length){
                
                try {
                    Cia cia = new Cia(listCodCvm[i]);
                    CiaDao dao = new CiaDao(cia);
                    cia = dao.getFirst();
                    BDCvm bd = new BDCvm(cia);
                    bd.execute();
                    i++;
                } catch (CiaDao.CiasException | BDCvm.BDCvmException ex) {
                    Logger.getLogger(Facede.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        /************************************/
        
        
        
        /* ROTINAS DA BOVESPA               */
        /************************************/
        
        private void insertAnoBovespa(int ano)
        {
             
            bvp = new FileBmfNet(ano);
            if(bvp.downFiles(false))
            {
                bdbmf = new BDBmf(ano);
                bdbmf.setManualCloseGUI(true);
                try {
                        bdbmf.insertall();
                    } catch (IOException | BDBmf.BDBmfException ex) {
                        Logger.getLogger(Facede.class.getName()).log(Level.SEVERE, null, ex);
                    }
             }
        }
        private void insertDiaBovespa(int dia , int mes ,int ano){
                     
                             bvp = new FileBmfNet(dia,mes,ano);
				if(bvp.downFiles(false)){
                                     bdbmf = new BDBmf(dia,mes,ano); 
                                     bdbmf.setManualCloseGUI(true);
                                     try {
                                        bdbmf.insertall();
                                    } catch (IOException | BDBmf.BDBmfException ex) {
                                        Logger.getLogger(Facede.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                     }
        
        }
        private void insertPorNomeBovespa(String nomedoarquivo){
                     
                             bvp = new FileBmfNet(nomedoarquivo);
				if(bvp.downFiles(false)){
                                     bdbmf = new BDBmf(nomedoarquivo); 
                                     bdbmf.setManualCloseGUI(true);
                                     try {
                                        bdbmf.insertall();
                                    } catch (IOException | BDBmf.BDBmfException ex) {
                                        Logger.getLogger(Facede.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                     }
        
        }
        
        
        public void bovespaGetAllYears(){
           verficaAtualizacao();
            for(int ano = anoInicial;ano<anoAtual;ano++)
                insertAnoBovespa(ano);
            for(int mes=1;mes<=mesAtual;mes++)
                for(int dia=1;dia<=31;dia++)
                    insertDiaBovespa(dia,mes,anoAtual);
             if(bdbmf!=null)bdbmf.CloseGUI();
        }
	
	public void bovespaRotine() {
		try {
                         verficaAtualizacao();
                         consertaBovespaEmAndamento();
                         FuncoesDB funcoes = new FuncoesDB();
                         Query query = new Query();
                         query.addSELECT();
                         query.addfunctionMAX(DadosDB.getDataBaseColumnName(11, 8));
                         query.setDatabase(ConfiguracoesIniciais.getNomeDatabasepadrao());
                         query.addFROM();
                         query.addtable(DadosDB.getDataBaseTableName(11));
                         funcoes.Select(query.getQuery());

                         if(ConnectionDB.result.next())
                         {
                             Date date;
                             date= ConnectionDB.result.getDate(1);
                             if(date==null) throw new RotineException();
                             Calendar cale= new GregorianCalendar();
                             cale.setTime(date);
                             int ultdia=cale.get(Calendar.DAY_OF_MONTH);
                             int ultmes=cale.get(Calendar.MONTH)+1;
                             int ultano=cale.get(Calendar.YEAR);
                             int proxdia=ultdia+1;
                             if(ultano<anoAtual)
                             {
                                 for(int mes=ultmes;mes<=12;mes++)
                                 {
                                     for(int dia=proxdia;dia<=31;dia++)
                                     {
                                         insertDiaBovespa(dia,mes,ultano);
                                     }
                                     proxdia=1;
                                 }
                                 proxdia=1;
                                 ultmes=1;
                                 ultano++;
                                 while(ultano<anoAtual)
                                 {
                                     insertAnoBovespa(ultano);
                                     ultano++;
                                 }
                             }
                             if(ultano==anoAtual)
                                 for(int mes=ultmes;mes<=mesAtual;mes++)
                                 {
                                     for(int dia=proxdia;dia<=31;dia++)
                                     {
                                         insertDiaBovespa(dia,mes,ultano);
                                     }
                                     proxdia=1;
                                 }
                           }
                } catch (SQLException | FuncoesDB.FuncoesDBException ex) {
                     IFSimple ifsimple= new IFSimple(" Erro : SQLException / FuncoesDBException ","Problemas com variaveis/comandos sql ",MyDialogs.OK_OPTION);
                    Erro  err = new Erro(true, ifsimple);
                    err.setIdErroDB(16101);
                    err.setSystemPrintMessage("    ERRO "+ex.getMessage());
                    err.ComErroCypecad(true);
                    err.Execute();   
                    if(bdbmf!=null)bdbmf.CloseGUI();
                } catch (RotineException ex) {  
                IFSimple ifsimple= new IFSimple(" Erro: problemas de configuração inicial ","sem registro de ultima data de inserimento de arquivo ",MyDialogs.OK_OPTION);
                    Erro  err = new Erro(true, ifsimple);
                    err.setIdErroDB(16103);
                    err.setSystemPrintMessage("    ERRO "+ex.getMessage());
                    err.ComErroCypecad(true);
                    err.Execute();   
                     if(bdbmf!=null)bdbmf.CloseGUI();
            }  
                
                 if(bdbmf!=null)bdbmf.CloseGUI();
        }
        
        public void consertaBovespaEmAndamento(){
          try {
            FuncoesDB funcoes = new FuncoesDB();
                         Query query = new Query();
                         query.addSELECT();
                         query.addsimplecolumn(DadosDB.getDataBaseColumnName(3,1));
                         query.setDatabase(ConfiguracoesIniciais.getNomeDatabasepadrao());
                         query.addFROM();
                         query.addtable(DadosDB.getDataBaseTableName(3));
                         query.addWHERE();
                         query.addcondition(DadosDB.getDataBaseColumnName(3,3),Query.Operador.IGUAL,"andamento");
                         query.addAND();
                         query.addcondition(DadosDB.getDataBaseColumnName(3,2),Query.Operador.IGUAL,"COTACAOBMF");
                         funcoes.Select(query.getQuery());
                         List<String> pilhadeArquivos = new LinkedList<>();
                         while(ConnectionDB.result.next())
                         {
                             pilhadeArquivos.add(ConnectionDB.result.getString(1));
                         }
                         while(!pilhadeArquivos.isEmpty())
                         {
                             insertPorNomeBovespa((String) pilhadeArquivos.remove(pilhadeArquivos.size()-1));
                         }
                         if(bdbmf!=null)bdbmf.CloseGUI();
                         } catch (FuncoesDB.FuncoesDBException | SQLException ex) {
                            Logger.getLogger(Facede.class.getName()).log(Level.SEVERE, null, ex);
                        }
        
        }
        
        private void verficaAtualizacao(){
         if(anoAtual>DadosEmissor.getAnoAtualizado())
            {
                if(Emissor.BaixarAtualizacoes());
                 Emissor.AtualizaCia();
            }
        }
        
        public class RotineException extends Exception {    

          public RotineException(){
                    super();
            }

            @Override
            public String toString(){
                    return "Ocorreu algum erro durante  PreparedStatement psquery = a inserção de arquivos de rotina";
            }

            @Override
            public String getMessage(){
                    return "Ocorreu algum erro durante  a inserção de arquivos de rotina";

            }
        }
        //***********************************/
        
        
}

