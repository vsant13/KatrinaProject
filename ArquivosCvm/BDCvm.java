/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ArquivosCvm;


import ArquivosCvm.CiaDeliverData.CiaDeliverException;
import ConexaoBD.ConnectionDB;
import ConexaoBD.FuncoesDB;
import ConexaoBD.FuncoesDB.FuncoesDBException;
import ConexaoNET.HTMLParser;
import ConexaoNET.ParseHandlingC;
import Dados.ConfiguracoesIniciais;
import Dados.DadosDB;
import Dados.Diretorios;
import Erros.BadRequestException;
import Erros.Erro;
import InterfaceGraf.IFLoadBar1;
import ManutencaoBD.ControledeInsercao;
import entity.Cia;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;


/**
 *
 * @author Erico
 */
public class BDCvm {

    private static int numTentativas = 0;
    private Cia cia; 
    private CiaDeliverData CDD;
    private HTMLParser parser;
    private FileCvmNet filenet;
    private FuncoesDB funcao = new FuncoesDB();
    private String path;
    private IFLoadBar1 if_insert_cvm= new IFLoadBar1(); 
    public boolean CONCLUIDO;
    
    
        public BDCvm(Cia cia) {
            this.cia = cia;
        }
        public boolean execute() throws BDCvmException
        {
             try {    
                check_DFP();
                check_ITR();
            } catch (BDCvmException ex) {
                if_insert_cvm.close();
                CONCLUIDO=false;
                throw ex;
            } catch (CiaDeliverException e) {
				Erro err = new Erro(true,true);
				err.setIdErroDB(12105);
				err.setSystemPrintMessage("ERRO: " + e.getMessage());
				err.setInterfaceMessage("Erro ao conectar com a tabela da CVM \n Nao foi possivel atualizar!");
				err.Execute();
                                return false;
			}
            CONCLUIDO=true;
             File file = new File(Diretorios.path_datafiles_cvm_data());
             deleteFiles(file);
             file.mkdir();
             return true;
            
        }
        private void deleteFiles(File file)
        {
                File arqs[]=file.listFiles();
                for(int i =0;i<arqs.length;i++)
                {   if(arqs[i].isDirectory())
                        deleteFiles(arqs[i]);
                    else    
                        arqs[i].delete();
                }
            file.delete();
        }
        
        private void check_DFP() throws BDCvmException, CiaDeliverException{
           
           CDD = new CiaDeliverData(cia.getCnpj()+"",cia.getCodCvm()+"");
           CDD.executePost(CDD.DFP);
           parser = new HTMLParser();
           parser.readHTML(CDD.in);
           CDD.closeStream();
           
           DadosDB.getInstance();
           
           String [] tabelas_ref = new String[3];                                        //tabelas relacionadas no banco de dados .
           tabelas_ref[0]=DadosDB.getDataBaseTableName(4);
           tabelas_ref[1]=DadosDB.getDataBaseTableName(5);
           tabelas_ref[2]=DadosDB.getDataBaseTableName(6);
           
                                            
           if_insert_cvm.setTitleMessage("ATUALIZANDO ARQUIVOS CVM DFP DA EMPRESA : ' "+cia.getIdEmpresa()+" '");// Interface gráfica
           if_insert_cvm.setTitle("ATUALIZAÇÃO DE ARQUIVOS CVM");
           if_insert_cvm.setBarIndeterminate(false);
           if_insert_cvm.setBarStringPainted(true);
           if_insert_cvm.setWarningWaitFinishMessage(true);
           if_insert_cvm.setTempodeTermino(true);
           if_insert_cvm.zeraBarValue();
           for(int i=0;i<parser.list.size();i++)
           {
                boolean perm=ControledeInsercao.Permissao(parser.list.get(i).get(ParseHandlingC.PROTOCOLO),"CVMDFP", tabelas_ref);
                if(perm)
                {
                    if(!if_insert_cvm.isVisible()) 
                        if_insert_cvm.setVisible(true);
                    if_insert_cvm.setMessage("Baixando demonstração financeira padrão (DFP) referente a : "+parser.list.get(i).get(ParseHandlingC.DATAENCERRAMENTO));
                    down(0,i);
                    if_insert_cvm.setMessage("Inserindo no banco DFP ");
                    insert(0,i);
                    boolean ret=ControledeInsercao.Conclusao(parser.list.get(i).get(ParseHandlingC.PROTOCOLO));
                }
                if_insert_cvm.if_aux(parser.list.size(), "dados atualizados");
           }
        }
       
    
        private void check_ITR() throws BDCvmException, CiaDeliverException{ 
           CDD = new CiaDeliverData(cia.getCnpj()+"",cia.getCodCvm()+"");
           CDD.executePost(CDD.ITR);
           parser = new HTMLParser();
           parser.readHTML(CDD.in);
           CDD.closeStream();
           
           
           String [] tabelas_ref = new String[3];                                         //tabelas relacionadas no banco de dados .
           tabelas_ref[0]=DadosDB.getDataBaseTableName(8);
           tabelas_ref[1]=DadosDB.getDataBaseTableName(9);
           tabelas_ref[2]=DadosDB.getDataBaseTableName(10);
         
           if_insert_cvm.setTitleMessage("ATUALIZANDO ARQUIVOS CVM ITR DA EMPRESA : ' "+cia.getIdEmpresa()+" '");// Interface gráfica
           if_insert_cvm.setTitle("ATUALIZAÇÃO DE ARQUIVOS CVM");
           if_insert_cvm.setBarIndeterminate(false);
           if_insert_cvm.setBarStringPainted(true);
           if_insert_cvm.setTempodeTermino(true);
           if_insert_cvm.zeraBarValue();
           for(int i=0;i<parser.list.size();i++)
           {
                boolean perm=ControledeInsercao.Permissao(parser.list.get(i).get(ParseHandlingC.PROTOCOLO),"CVMITR", tabelas_ref);
               if(perm)
                {
                    if(!if_insert_cvm.isVisible()) 
                        if_insert_cvm.setVisible(true);
                    if_insert_cvm.setMessage("Baixando as Informações Trimestrais(ITR)  referente a : "+parser.list.get(i).get(ParseHandlingC.DATAENCERRAMENTO));
                    down(1,i);
                    if_insert_cvm.setMessage("Inserindo no banco ITR");
                    insert(1,i);
                    boolean ret=ControledeInsercao.Conclusao(parser.list.get(i).get(ParseHandlingC.PROTOCOLO));
                }
                if_insert_cvm.if_aux(parser.list.size(), "arquivo atualizado ");
           }
           if_insert_cvm.close();
        }
        
        
        
         private void insert(int tipo ,int num ) throws BDCvmException{        
            DadosITR dados  ;
            try {
                   dados = new DadosITR(path);
            } catch (DadosITR.DadosIIRException ex) {
                 throw new BDCvmException();
            }
                 
             String TIPO;
             String tableConta;
             String tableCCS;
             String tableProventos;
             String fkArquivoConta;
             String fkArquivoCCS;
             String fkArquivoProvento;
             String fkEmpresa;
             String dataFR;
             String versaoDocumento;
             int aux;
             if(tipo!=0) 
            {
                aux=4;
                tipo=1;
            }
            else 
                 aux=0;
             
             tableConta=DadosDB.getDataBaseTableName(5+aux);
                tableCCS=DadosDB.getDataBaseTableName(4+aux);
                tableProventos=DadosDB.getDataBaseTableName(6+aux);
                fkArquivoConta=DadosDB.getDataBaseColumnName(5+aux,3);
                fkArquivoCCS=DadosDB.getDataBaseColumnName(4+aux,3);;
                fkArquivoProvento=DadosDB.getDataBaseColumnName(6+aux,3);;
                fkEmpresa=DadosDB.getDataBaseColumnName(5+aux,2);;
                dataFR=DadosDB.getDataBaseColumnName(5+aux,2);;
                versaoDocumento=DadosDB.getDataBaseColumnName(5+aux,18);
       
            String dadodecomposicao[]=new String[11];
            String dadodeconta[]= new String[19];
            String dadodeproventos[]=new String[11];
            
            
            String versao_documento =parser.list.get(num).get(ParseHandlingC.VERSAO).trim(); 
            if(versao_documento.length()==3)versao_documento=versao_documento.substring(0,1);
            if(versao_documento.length()==4)versao_documento=versao_documento.substring(0,2);
            
            
 
            try {
                    if(versao_documento!="1")
                    {
                        
                        funcao.Select("SELECT DISTINCT "+   fkArquivoConta+" FROM "+ ConfiguracoesIniciais.getNomeDatabasepadrao()+
                                                          "."+tableConta+" WHERE "+fkEmpresa+"='"+cia.getIdEmpresa()+"'  AND " +
                                                            dataFR+"='"+dados.I_DATAFIMPERIODO[1]+"' AND "+
                                                            versaoDocumento+"<'"+versao_documento+"'");
                        while(ConnectionDB.result.next())
                        { 
                            String temp =ConnectionDB.result.getString(fkArquivoConta);
                            funcao.Delete(tableCCS," WHERE "+fkArquivoCCS+"="+"'"+temp+"'");
                            funcao.Delete(tableConta," WHERE "+fkArquivoConta+"="+"'"+temp+"'");
                            funcao.Delete(tableProventos," WHERE "+fkArquivoProvento+"="+"'"+temp+"'");
                            
                        }
                    }
                 
                
                
                    String colunasdecomposicao = "";
                    for(int i=2;i<=11;i++)
                        colunasdecomposicao=colunasdecomposicao+DadosDB.getDataBaseColumnName(4+aux, i)+",";
                    colunasdecomposicao=colunasdecomposicao+DadosDB.getDataBaseColumnName(4+aux, 12);
                    
                    String colunasdeconta = "";
                    for(int i=2;i<=19;i++)
                        colunasdeconta=colunasdeconta+DadosDB.getDataBaseColumnName(5+aux, i)+",";
                    colunasdeconta=colunasdeconta+DadosDB.getDataBaseColumnName(5+aux, 20);                                
                                                  
                   String colunasdeproventos = ""   ;
                    for(int i=2;i<=11;i++)
                        colunasdeproventos=colunasdeproventos+DadosDB.getDataBaseColumnName(6+aux, i)+",";
                    colunasdeproventos=colunasdeproventos+DadosDB.getDataBaseColumnName(6+aux, 12);           

                        if(dados.permissao_composicao)
                        { 
                                          dadodecomposicao[0] = cia.getIdEmpresa();
                                          dadodecomposicao[1] = parser.list.get(num).get(ParseHandlingC.PROTOCOLO);
                                          dadodecomposicao[2] = dados.I_DATAFIMPERIODO[0];
                                          dadodecomposicao[3] = dados.NICCS;
                                          dadodecomposicao[4] = dados.QAOCI;
                                          dadodecomposicao[5] = dados.QAFCI;
                                          dadodecomposicao[6] = dados.QTACI; 
                                          dadodecomposicao[7] = dados.QAOT;
                                          dadodecomposicao[8] = dados.QAPT;
                                          dadodecomposicao[9] = dados.QTAT;
                                          dadodecomposicao[10] = versao_documento;
                                       funcao.InsertInto(tableCCS,colunasdecomposicao,dadodecomposicao);
                        }
                        if(dados.permissao_conta)
                        {    
                            for( int j=0;j<dados.tamanho_vetor_conta;j++)
                                   {
                                        dadodeconta[0] = cia.getIdEmpresa();
                                        dadodeconta[1]= parser.list.get(num).get(ParseHandlingC.PROTOCOLO);
                                        dadodeconta[2] =dados.NUMEROCONTA[j];
                                        dadodeconta[3] =dados.DESCRICAOCONTA[j];
                                        dadodeconta[4]= dados.VALORCONTA1[j];
                                        dadodeconta[5] =dados.VALORCONTA2[j];
                                        dadodeconta[6] =dados.VALORCONTA3[j];
                                        dadodeconta[7] =dados.VALORCONTA4[j];
                                        dadodeconta[8] = dados.VALORCONTA5[j];
                                        dadodeconta[9] =dados.VALORCONTA6[j];
                                        dadodeconta[10] = dados.VALORCONTA7[j];
                                        dadodeconta[11] = dados.VALORCONTA8[j];
                                        dadodeconta[12] = dados.VALORCONTA9[j];
                                        dadodeconta[13] = dados.VALORCONTA10[j];
                                        dadodeconta[14] = dados.VALORCONTA11[j];
                                        dadodeconta[15] = dados.VALORCONTA12[j];
                                        dadodeconta[16] = versao_documento;
                                        dadodeconta[17] = dados.I_DATAINICIOPERIODO[tipo];
                                        dadodeconta[18] = dados.I_DATAFIMPERIODO[tipo];

                                       funcao.InsertInto(tableConta,colunasdeconta,dadodeconta);
                                   }
                        }
                        if(dados.permissao_provento)
                        { 
                             for( int j=0;j<dados.tamanho_vetor_provento;j++)
                                   {
                                        dadodeproventos[0] = cia.getIdEmpresa();
                                        dadodeproventos[1] =parser.list.get(num).get(ParseHandlingC.PROTOCOLO) ;
                                        dadodeproventos[2] =dados.TipoProvento[j];
                                        dadodeproventos[3] = dados.DataAprovacaoProvento[j];
                                        dadodeproventos[4] =dados.DataInicioPagamento[j];
                                        dadodeproventos[5] =dados.DescricaoCodigoEspecie[j];
                                        dadodeproventos[6] = dados.SiglaCodigoEspecie[j];
                                        dadodeproventos[7] = dados.DescricaoCodigoClassePreferencial[j];
                                        dadodeproventos[8] =dados.SiglaCodigoClassePreferencial[j];
                                        dadodeproventos[9] =dados.ValorProventoPorAcao[j];
                                        dadodeproventos[10] = versao_documento;
                                        funcao.InsertInto(tableProventos,colunasdeproventos,dadodeproventos);
                                   }
                       }
     
        } catch (FuncoesDBException ex) {
            Erro err = new Erro(true,true);
            err.setIdErroDB(12101);
            err.setSystemPrintMessage("    ERRO "+ex.getMessage());
            err.setInterfaceMessage("ERRO : Não foi possivel a atualização completa da empresa : '"+cia.getIdEmpresa()+" '");
            err.ComErroCypecad(true);
            err.Execute();
        } catch (SQLException ex) {
            Erro err = new Erro(true,true);
            err.setIdErroDB(12102);
            err.setSystemPrintMessage("    ERRO "+ex.getMessage());
            err.setInterfaceMessage("ERRO : Não foi possivel a atualização completa da empresa : '"+cia.getIdEmpresa()+" '");
            err.ComErroCypecad(true);
            err.Execute();
            throw new BDCvmException(parser.list.get(num).get(ParseHandlingC.PROTOCOLO));
        }
    }
       
    private void down(int tipo, int num) throws BDCvmException{
            
            int i = 0;
            String query = null;
            filenet = new FileCvmNet();
            FileCvmDown d = null;
            try{
                    while(filenet.passOk != true && i < 5){
                        filenet.startStrem();
                        query = filenet.buildQuery(parser.list.get(num).get(ParseHandlingC.DATAENTREGA),parser.list.get(num).get(ParseHandlingC.HORAENTREGA),tipo);
                        filenet.executePost(query);
                        query = filenet.getResponse();                     
                        filenet.closeStream();
                        i++;
                    }
                    query = filenet.getURL(query,cia.getCodCvm()+"");
                    d = new FileCvmDown(query, cia.getCodCvm()+""  ,parser.list.get(num).get(ParseHandlingC.DATAENCERRAMENTO));
                    d.downFiles();
                    path = d.path;
            }catch(BadRequestException ex){
                Erro err = new Erro(true,true);
                err.setIdErroDB(12103);
                err.setSystemPrintMessage("    ERRO "+ex.getMessage());
                err.setInterfaceMessage("ERRO : Não foi possivel a atualização completa da empresa : '"+cia.getIdEmpresa()+" '");
                err.ComErroCypecad(true);
                err.Execute();
            }catch (IOException ex) {
                
                if(numTentativas == 5){
                    Erro err = new Erro(true,true);
                    err.setIdErroDB(12104);
                    err.setSystemPrintMessage("    ERRO "+ex.getMessage() +"\nTente mais tarde!");
                    err.setInterfaceMessage("ERRO : Não foi possivel a atualização completa da empresa : '"+cia.getIdEmpresa()+" '");
                    err.ComErroCypecad(true);
                    err.Execute();
                    throw new BDCvmException(parser.list.get(num).get(ParseHandlingC.PROTOCOLO));        
                    
                }
                else{
                    numTentativas++;
                    System.out.println(ex.getMessage());
                    down(tipo,num);
                    numTentativas = 0;
                }
            }finally{
                d = null;
            }          
}     
         
        public class BDCvmException extends Exception {    
          private String arquivo="";

          public BDCvmException(){
                    super();
            }

          public BDCvmException(String arquivo){
              super();  
              this.arquivo=arquivo;    
            }

            @Override
            public String toString(){
                    return "Ocorreu algum erro durante a inseção no banco de dados do arquivo cvm "+arquivo;
            }

            @Override
            public String getMessage(){
                    return "Ocorreu algum erro durante a inseção  no banco de dados do arquivo cvm"+arquivo;

            }
     }
    
}
