/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import entity.Cia;
import ConexaoBD.ConnectionDB;
import ConexaoBD.FuncoesDB;
import ConexaoBD.FuncoesDB.FuncoesDBException;
import ConexaoBD.Query;
import Dados.DadosDB;
import Erros.Erro;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;





/**
 *
 * @author Erico
 */
public class CiaDao {
     
    public String  table = "CIAS";
    private FuncoesDB funcao = new FuncoesDB();
    private Cia cias;
    private Query query;
    private PreparedStatement psQuery =null;
    
  public  String   id_empresa,cod_cvm,denominacao_social,denominacao_comercial,logradouro,complemento,bairro,cep,municipio,uf,ddd,
telefone,fax,denominacao_anterior,setor_atividade,cnpj,dri,auditor,quant_de_acoes_ordinarias,quant_de_acoes_preferenciais,situacao,
data_da_situacao,tipo_papel1,tipo_papel2,tipo_papel3,tipo_papel4,tipo_papel5,tipo_papel6,controle_acionario,data_de_registro,data_de_cancelamento,
mercado,bolsa1,bolsa2,bolsa3,bolsa4,bolsa5,bolsa6,bolsa7,bolsa8,bolsa9,motivo_do_cancelamento,patrimonio_liquido,data_do_patrimonio,e_mail,
nome_setor_atividade,data_da_acao,tipo_negocio1,tipo_negocio2,tipo_negocio3,tipo_negocio4,tipo_negocio5,tipo_negocio6,tipo_mercado1,tipo_mercado2,
tipo_mercado3,tipo_mercado4,tipo_mercado5,tipo_mercado6;
    
    public CiaDao(){
        this.cias=null;
        setVariaveis();
        
    }
   
    public CiaDao(Cia cias) throws CiasException{
        this.cias = cias;
        setVariaveis();
    }
    
    private void setVariaveis(){
        DadosDB.getInstance();
        id_empresa=DadosDB.getDataBaseColumnName(1,1);
        cod_cvm=DadosDB.getDataBaseColumnName(1,2);
        denominacao_social=DadosDB.getDataBaseColumnName(1,3);
        denominacao_comercial=DadosDB.getDataBaseColumnName(1,4);
        logradouro=DadosDB.getDataBaseColumnName(1,5);
        complemento=DadosDB.getDataBaseColumnName(1,6);
        bairro=DadosDB.getDataBaseColumnName(1,7);
        cep=DadosDB.getDataBaseColumnName(1,8);
        municipio=DadosDB.getDataBaseColumnName(1,9);
        uf=DadosDB.getDataBaseColumnName(1,10);
        ddd=DadosDB.getDataBaseColumnName(1,11);
        telefone=DadosDB.getDataBaseColumnName(1,12);
        fax=DadosDB.getDataBaseColumnName(1,13);
        denominacao_anterior=DadosDB.getDataBaseColumnName(1,14);
        setor_atividade=DadosDB.getDataBaseColumnName(1,15);
        cnpj=DadosDB.getDataBaseColumnName(1,16);
        dri=DadosDB.getDataBaseColumnName(1,17);
        auditor=DadosDB.getDataBaseColumnName(1,18);
        quant_de_acoes_ordinarias=DadosDB.getDataBaseColumnName(1,19);
        quant_de_acoes_preferenciais=DadosDB.getDataBaseColumnName(1,20);
        situacao=DadosDB.getDataBaseColumnName(1,21);
        data_da_situacao=DadosDB.getDataBaseColumnName(1,22);
        tipo_papel1=DadosDB.getDataBaseColumnName(1,23);
        tipo_papel2=DadosDB.getDataBaseColumnName(1,24);
        tipo_papel3=DadosDB.getDataBaseColumnName(1,25);
        tipo_papel4=DadosDB.getDataBaseColumnName(1,26);
        tipo_papel5=DadosDB.getDataBaseColumnName(1,27);
        tipo_papel6=DadosDB.getDataBaseColumnName(1,28);
        controle_acionario=DadosDB.getDataBaseColumnName(1,29);
        data_de_registro=DadosDB.getDataBaseColumnName(1,30);
        data_de_cancelamento=DadosDB.getDataBaseColumnName(1,31);
        mercado=DadosDB.getDataBaseColumnName(1,32);
        bolsa1=DadosDB.getDataBaseColumnName(1,33);
        bolsa2=DadosDB.getDataBaseColumnName(1,34);
        bolsa3=DadosDB.getDataBaseColumnName(1,35);
        bolsa4=DadosDB.getDataBaseColumnName(1,36);
        bolsa5=DadosDB.getDataBaseColumnName(1,37);
        bolsa6=DadosDB.getDataBaseColumnName(1,38);
        bolsa7=DadosDB.getDataBaseColumnName(1,39);
        bolsa8=DadosDB.getDataBaseColumnName(1,40);
        bolsa9=DadosDB.getDataBaseColumnName(1,41);
        motivo_do_cancelamento=DadosDB.getDataBaseColumnName(1,42);
        patrimonio_liquido=DadosDB.getDataBaseColumnName(1,43);
        data_do_patrimonio=DadosDB.getDataBaseColumnName(1,44);
        e_mail=DadosDB.getDataBaseColumnName(1,45);
        nome_setor_atividade=DadosDB.getDataBaseColumnName(1,46);
        data_da_acao=DadosDB.getDataBaseColumnName(1,47);
        tipo_negocio1=DadosDB.getDataBaseColumnName(1,48);
        tipo_negocio2=DadosDB.getDataBaseColumnName(1,49);
        tipo_negocio3=DadosDB.getDataBaseColumnName(1,50);
        tipo_negocio4=DadosDB.getDataBaseColumnName(1,51);
        tipo_negocio5=DadosDB.getDataBaseColumnName(1,52);
        tipo_negocio6=DadosDB.getDataBaseColumnName(1,53);
        tipo_mercado1=DadosDB.getDataBaseColumnName(1,54);
        tipo_mercado2=DadosDB.getDataBaseColumnName(1,55);
        tipo_mercado3=DadosDB.getDataBaseColumnName(1,56);
        tipo_mercado4=DadosDB.getDataBaseColumnName(1,57);
        tipo_mercado5=DadosDB.getDataBaseColumnName(1,58);
        tipo_mercado6=DadosDB.getDataBaseColumnName(1,59);
    }
    
     public Cia getFirst(){
        try {
        if(cias==null)
        {
            query = new Query();
            query.addSELECTALL();
            query.addFROM();
            query.addtable(table);
            
           // query=iscolsnull(query);
            
                funcao.Select(query.getQuery());
                    if(ConnectionDB.result.next())
                           return setData();   
        }
        else
        {
           return analiza_cias();
        }
        } catch (SQLException ex) {

        } catch (FuncoesDB.FuncoesDBException ex) {

        } catch (CiasException ex) {
            
        }
        return null;
    }
    public Cia getFirst(Query byQuery){
        
        try {
            funcao.Select(byQuery.getQuery());
                if(ConnectionDB.result.next())
                       return setData();
                
        } catch (SQLException ex) {
            
        } catch (FuncoesDB.FuncoesDBException ex) { 
            
        }
        return null;
    }
    public Cia getNext(){
        try {
            if(ConnectionDB.result.next())
            {
                ConnectionDB.result.findColumn("id_empresa");
                return setData();
            }
            else    
            {
                System.out.println("Erro");
            }
        } catch (SQLException ex) {
            System.out.println("Erro de uso da getnext");
        }
        return null;
      }
    public List<Cia> getList(){
        List<Cia> list = new ArrayList<Cia>();
        query = new Query();
        query.addSELECTALL();
        query.addFROM();
        query.addtable(table);
        //query=iscolsnull(query);
       
        try {
             funcao.Select(query.getQuery());
                while(ConnectionDB.result.next())
                        list.add(setData());
                return list;
        } catch (SQLException ex) {
            
        } catch (FuncoesDB.FuncoesDBException ex) {
           
        }
        return null; 
    }
     public List<Cia> getList(String column,String likeValue){
        List<Cia> list = new ArrayList<Cia>();
        query = new Query();
        query.addSELECTALL();
        query.addFROM();
        query.addtable(table);
        //query=iscolsnull(query);
        if(!query.getQuery().toLowerCase().contains("where"))
            query.addWHERE();
        query.addsimplecolumn(column);
        query.addLIKE();
        if(!likeValue.contains("%"))
        {
            query.add("%");
            query.addNewLocalValues(1);
        }
        
        
        try {
            psQuery = ConnectionDB.conexao.prepareStatement(query.getQuery());
            psQuery.setString(1, likeValue);
            
            funcao.Select(psQuery);
                while(ConnectionDB.result.next())
                        list.add(setData());
                return list;
        } catch (SQLException ex) {
       
        } catch (FuncoesDB.FuncoesDBException ex) {
           
        }
        return null; 
    }
    public List<Cia> getList(Query byQuery) {
        List<Cia> list = new ArrayList<Cia>();
        try {
            funcao.Select(byQuery.getQuery());
                while(ConnectionDB.result.next())
                        list.add(setData());
                return list;
        } catch (SQLException ex) {
             Erro  err = new Erro(true, true);
            err.setIdErroDB(1101);
            err.setSystemPrintMessage("    ERRO "+ex.getMessage());
            err.setInterfaceMessage("ERRO : Erro ao executar script em SQL de criação do banco.");
            err.Execute();
        } catch (FuncoesDB.FuncoesDBException ex) {
             Erro  err = new Erro(true, true);
            err.setIdErroDB(1101);
            err.setSystemPrintMessage("    ERRO "+ex.getMessage());
            err.setInterfaceMessage("ERRO : Erro ao executar script em SQL de criação do banco.");
            err.Execute();
        }
        return null; 
    }
    
    
    
    private Cia analiza_cias() throws CiasException{
            try {  
                    Query query = new Query(); 
                    query.addSELECTALL();
                    query.addFROM();
                    query.addtable(table);
                    query.addWHERE();
                   
                    
                    
                   
                   int i[]=new  int[61]; 
                   int position=0;
                   
                   if(cias.getAuditor()!=null){query.addsimplecolumn(auditor);query.addLIKE();query.addNewLocalValues(1);query.addAND();position++;i[position]=1;}
                   if(cias.getBairro()!=null){query.addsimplecolumn(bairro);query.addLIKE();query.addNewLocalValues(1);query.addAND();position++;i[position]=2;}
                   if(cias.getBolsa1()!=null){query.addsimplecolumn(bolsa1);query.addLIKE();query.addNewLocalValues(1);query.addAND();position++;i[position]=3;}
                   if(cias.getBolsa2()!=null){query.addsimplecolumn(bolsa2);query.addLIKE();query.addNewLocalValues(1);query.addAND();position++;i[position]=4;}
                   if(cias.getBolsa3()!=null){query.addsimplecolumn(bolsa3);query.addLIKE();query.addNewLocalValues(1);query.addAND();position++;i[position]=5;}
                   if(cias.getBolsa4()!=null){query.addsimplecolumn(bolsa4);query.addLIKE();query.addNewLocalValues(1);query.addAND();position++;i[position]=6;}
                   if(cias.getBolsa5()!=null){query.addsimplecolumn(bolsa5);query.addLIKE();query.addNewLocalValues(1);query.addAND();position++;i[position]=7;}
                   if(cias.getBolsa6()!=null){query.addsimplecolumn(bolsa6);query.addLIKE();query.addNewLocalValues(1);query.addAND();position++;i[position]=8;}
                   if(cias.getBolsa7()!=null){query.addsimplecolumn(bolsa7);query.addLIKE();query.addNewLocalValues(1);query.addAND();position++;i[position]=9;}
                   if(cias.getBolsa8()!=null){query.addsimplecolumn(bolsa8);query.addLIKE();query.addNewLocalValues(1);query.addAND();position++;i[position]=10;}
                   if(cias.getBolsa9()!=null){query.addsimplecolumn(bolsa9);query.addLIKE();query.addNewLocalValues(1);query.addAND();position++;i[position]=11;}
                   if(cias.getComplemento()!=null){query.addsimplecolumn(complemento);query.addLIKE();query.addNewLocalValues(1);query.addAND();position++;i[position]=12;}
                   if(cias.getCep()!=null){query.addsimplecolumn(cep);query.addLIKE();query.addNewLocalValues(1);query.addAND();position++;i[position]=13;}
                   if(cias.getCnpj()!=0) {query.addsimplecolumn(cnpj);query.addLIKE();query.addNewLocalValues(1);query.addAND();position++;i[position]=14;}
                   if(cias.getCodCvm()!=0){query.addsimplecolumn(cod_cvm);query.addLIKE();query.addNewLocalValues(1);query.addAND();position++;i[position]=15;}
                   if(cias.getControleAcionario()!=null){query.addsimplecolumn(controle_acionario);query.addLIKE();query.addNewLocalValues(1);query.addAND();position++;i[position]=16;}
                   if(cias.getDataDaAcao()!=null){query.addsimplecolumn(data_da_acao);query.addLIKE();query.addNewLocalValues(1);query.addAND();position++;i[position]=17;}
                   if(cias.getDataDaSituacao()!=null){query.addsimplecolumn(data_da_situacao);query.addLIKE();query.addNewLocalValues(1);query.addAND();position++;i[position]=18;}
                   if(cias.getDataDeCancelamento()!=null){query.addsimplecolumn(data_de_cancelamento);query.addLIKE();query.addNewLocalValues(1);query.addAND();position++;i[position]=19;}
                   if(cias.getDataDeRegistro()!=null){query.addsimplecolumn(data_de_registro);query.addLIKE();query.addNewLocalValues(1);query.addAND();position++;i[position]=20;}
                   if(cias.getDataDoPatrimonio()!=null){query.addsimplecolumn(data_do_patrimonio);query.addLIKE();query.addNewLocalValues(1);query.addAND();position++;i[position]=21;}
                   if(cias.getDdd()!=null){query.addsimplecolumn(ddd);query.addLIKE();query.addNewLocalValues(1);query.addAND();position++;i[position]=22;}
                   if(cias.getDenominacaoAnterior()!=null){query.addsimplecolumn(denominacao_anterior);query.addLIKE();query.addNewLocalValues(1);query.addAND();position++;i[position]=23;}
                   if(cias.getDenominacaoComercial()!=null){query.addsimplecolumn(denominacao_comercial);query.addLIKE();query.addNewLocalValues(1);query.addAND();position++;i[position]=24;}
                   if(cias.getDenominacaoSocial()!=null){query.addsimplecolumn(denominacao_social);query.addLIKE();query.addNewLocalValues(1);query.addAND();position++;i[position]=25;}
                   if(cias.getDri()!=null){query.addsimplecolumn(dri);query.addLIKE();query.addNewLocalValues(1);query.addAND();position++;i[position]=26;}
                   if(cias.getEMail()!=null){query.addsimplecolumn(e_mail);query.addLIKE();query.addNewLocalValues(1);query.addAND();position++;i[position]=27;}
                   if(cias.getFax()!=null){query.addsimplecolumn(fax);query.addLIKE();query.addNewLocalValues(1);query.addAND();position++;i[position]=28;}
                   if(cias.getIdEmpresa()!=null){query.addsimplecolumn(id_empresa);query.addLIKE();query.addNewLocalValues(1);query.addAND();position++;i[position]=29;}
                   if(cias.getLogradouro()!=null){query.addsimplecolumn(logradouro);query.addLIKE();query.addNewLocalValues(1);query.addAND();position++;i[position]=30;}
                   if(cias.getMercado()!=null){query.addsimplecolumn(mercado);query.addLIKE();query.addNewLocalValues(1);query.addAND();position++;i[position]=31;}
                   if(cias.getMotivoDoCancelamento()!=null){query.addsimplecolumn(motivo_do_cancelamento);query.addLIKE();query.addNewLocalValues(1);query.addAND();position++;i[position]=32;}
                   if(cias.getMunicipio()!=null){query.addsimplecolumn(municipio);query.addLIKE();query.addNewLocalValues(1);query.addAND();position++;i[position]=33;}
                   if(cias.getNomeSetorAtividade()!=null){query.addsimplecolumn(nome_setor_atividade);query.addLIKE();query.addNewLocalValues(1);query.addAND();position++;i[position]=34;}
                   if(cias.getPatrimonioLiquido()!=null){query.addsimplecolumn(patrimonio_liquido);query.addLIKE();query.addNewLocalValues(1);query.addAND();position++;i[position]=36;}
                   if(cias.getQuantDeAcoesOrdinarias()!=null){query.addsimplecolumn(quant_de_acoes_ordinarias);query.addLIKE();query.addNewLocalValues(1);query.addAND();position++;i[position]=37;}
                   if(cias.getQuantDeAcoesPreferenciais()!=null){query.addsimplecolumn(quant_de_acoes_preferenciais);query.addLIKE();query.addNewLocalValues(1);query.addAND();position++;i[position]=38;}
                   if(cias.getSetorAtividade()!=null){query.addsimplecolumn(setor_atividade);query.addLIKE();query.addNewLocalValues(1);query.addAND();position++;i[position]=39;}
                   if(cias.getSituacao()!=null){query.addsimplecolumn(situacao);query.addLIKE();query.addNewLocalValues(1);query.addAND();position++;i[position]=40;}
                   if(cias.getTelefone()!=null){query.addsimplecolumn(telefone);query.addLIKE();query.addNewLocalValues(1);query.addAND();position++;i[position]=41;}
                   if(cias.getTipoMercado1()!=null){query.addsimplecolumn(tipo_mercado1);query.addLIKE();query.addNewLocalValues(1);query.addAND();position++;i[position]=42;}
                   if(cias.getTipoMercado2()!=null){query.addsimplecolumn(tipo_mercado2);query.addLIKE();query.addNewLocalValues(1);query.addAND();position++;i[position]=43;}
                   if(cias.getTipoMercado3()!=null){query.addsimplecolumn(tipo_mercado3);query.addLIKE();query.addNewLocalValues(1);query.addAND();position++;i[position]=44;}
                   if(cias.getTipoMercado4()!=null){query.addsimplecolumn(tipo_mercado4);query.addLIKE();query.addNewLocalValues(1);query.addAND();position++;i[position]=45;}
                   if(cias.getTipoMercado5()!=null){query.addsimplecolumn(tipo_mercado5);query.addLIKE();query.addNewLocalValues(1);query.addAND();position++;i[position]=46;}
                   if(cias.getTipoMercado6()!=null){query.addsimplecolumn(tipo_mercado6);query.addLIKE();query.addNewLocalValues(1);query.addAND();position++;i[position]=47;}
                   if(cias.getTipoNegocio1()!=null){query.addsimplecolumn(tipo_negocio1);query.addLIKE();query.addNewLocalValues(1);query.addAND();position++;i[position]=48;}
                   if(cias.getTipoNegocio2()!=null){query.addsimplecolumn(tipo_negocio2);query.addLIKE();query.addNewLocalValues(1);query.addAND();position++;i[position]=49;}
                   if(cias.getTipoNegocio3()!=null){query.addsimplecolumn(tipo_negocio3);query.addLIKE();query.addNewLocalValues(1);query.addAND();position++;i[position]=50;}
                   if(cias.getTipoNegocio4()!=null){query.addsimplecolumn(tipo_negocio4);query.addLIKE();query.addNewLocalValues(1);query.addAND();position++;i[position]=51;}
                   if(cias.getTipoNegocio5()!=null){query.addsimplecolumn(tipo_negocio5);query.addLIKE();query.addNewLocalValues(1);query.addAND();position++;i[position]=52;}
                   if(cias.getTipoNegocio6()!=null){query.addsimplecolumn(tipo_negocio6);query.addLIKE();query.addNewLocalValues(1);query.addAND();position++;i[position]=53;}
                   if(cias.getTipoPapel1()!=null){query.addsimplecolumn(tipo_papel1);query.addLIKE();query.addNewLocalValues(1);query.addAND();position++;i[position]=54;}
                   if(cias.getTipoPapel2()!=null){query.addsimplecolumn(tipo_papel2);query.addLIKE();query.addNewLocalValues(1);query.addAND();position++;i[position]=55;}
                   if(cias.getTipoPapel3()!=null){query.addsimplecolumn(tipo_papel3);query.addLIKE();query.addNewLocalValues(1);query.addAND();position++;i[position]=56;}
                   if(cias.getTipoPapel4()!=null){query.addsimplecolumn(tipo_papel4);query.addLIKE();query.addNewLocalValues(1);query.addAND();position++;i[position]=57;}
                   if(cias.getTipoPapel5()!=null){query.addsimplecolumn(tipo_papel5);query.addLIKE();query.addNewLocalValues(1);query.addAND();position++;i[position]=58;}
                   if(cias.getTipoPapel6()!=null){query.addsimplecolumn(tipo_papel6);query.addLIKE();query.addNewLocalValues(1);query.addAND();position++;i[position]=59;}
                   if(cias.getUf()!=null){query.addsimplecolumn(uf);query.addLIKE();query.addNewLocalValues(1);query.addAND();position++;i[position]=60;}
                   query.addcondition("'1'", "=","'1'"); //evitador de ultimo and sozinho
                   
                   psQuery = ConnectionDB.conexao.prepareStatement(query.getQuery());
                   
                   for(int j=1;j<=position;j++)
                   {
                      switch(i[j]){
                        case  1:  psQuery.setString(j, "%"+cias.getAuditor()+"%"); break;
                        case  2:  psQuery.setString(j, "%"+cias.getBairro()+"%"); break;
                        case  3:  psQuery.setString(j, "%"+cias.getBolsa1()+"%"); break;
                        case  4:  psQuery.setString(j, "%"+cias.getBolsa2()+"%"); break;
                        case  5:  psQuery.setString(j, "%"+cias.getBolsa3()+"%"); break;
                        case  6:  psQuery.setString(j, "%"+cias.getBolsa4()+"%"); break;
                        case  7:  psQuery.setString(j, "%"+cias.getBolsa5()+"%"); break;
                        case  8:  psQuery.setString(j, "%"+cias.getBolsa6()+"%"); break;
                        case  9:  psQuery.setString(j, "%"+cias.getBolsa7()+"%"); break;
                        case  10:  psQuery.setString(j, "%"+cias.getBolsa8()+"%"); break;
                        case  11:  psQuery.setString(j, "%"+cias.getBolsa9()+"%"); break;
                        case  12:  psQuery.setString(j, "%"+cias.getComplemento()+"%"); break;
                        case  13:  psQuery.setInt(j, cias.getCep()); break;
                        case  14:  psQuery.setLong(j, cias.getCnpj()); break;
                        case  15:  psQuery.setInt(j, cias.getCodCvm()); break;
                        case  16:  psQuery.setString(j, "%"+cias.getControleAcionario()+"%"); break;
                        case  17:  psQuery.setDate(j, (Date) cias.getDataDaAcao()); break;
                        case  18:  psQuery.setDate(j, (Date) cias.getDataDaSituacao()); break;
                        case  19:  psQuery.setDate(j, (Date) cias.getDataDeCancelamento()); break;
                        case  20:  psQuery.setDate(j, (Date) cias.getDataDeRegistro()); break;
                        case  21:  psQuery.setDate(j, (Date) cias.getDataDoPatrimonio()); break;
                        case  22:  psQuery.setInt(j, cias.getDdd()); break;
                        case  23:  psQuery.setString(j, "%"+cias.getDenominacaoAnterior()+"%"); break;
                        case  24:  psQuery.setString(j, "%"+cias.getDenominacaoComercial()+"%"); break;
                        case  25:  psQuery.setString(j, "%"+cias.getDenominacaoSocial()+"%"); break;
                        case  26:  psQuery.setString(j, "%"+cias.getDri()+"%"); break;
                        case  27:  psQuery.setString(j, "%"+cias.getEMail()+"%"); break;
                        case  28:  psQuery.setInt(j, cias.getFax()); break;
                        case  29:  psQuery.setString(j, "%"+cias.getIdEmpresa()+"%"); break;
                        case  30:  psQuery.setString(j, "%"+cias.getLogradouro()+"%"); break;
                        case  31:  psQuery.setString(j, "%"+cias.getMercado()+"%"); break;
                        case  32:  psQuery.setString(j, "%"+cias.getMotivoDoCancelamento()+"%"); break;
                        case  33:  psQuery.setString(j, "%"+cias.getMunicipio()+"%"); break;
                        case  34:  psQuery.setString(j, "%"+cias.getNomeSetorAtividade()+"%"); break;
                        case  36:  psQuery.setFloat(j, cias.getPatrimonioLiquido()); break;
                        case  37:  psQuery.setInt(j,cias.getQuantDeAcoesOrdinarias().intValue()); break;
                        case  38:  psQuery.setInt(j, cias.getQuantDeAcoesPreferenciais().intValue()); break;
                        case  39:  psQuery.setString(j, "%"+cias.getSetorAtividade()+"%"); break;
                        case  40:  psQuery.setString(j, "%"+cias.getSituacao()+"%"); break;
                        case  41:  psQuery.setInt(j, cias.getTelefone()); break;
                        case  42:  psQuery.setString(j, "%"+cias.getTipoMercado1()+"%"); break;
                        case  43:  psQuery.setString(j, "%"+cias.getTipoMercado2()+"%"); break;
                        case  44:  psQuery.setString(j, "%"+cias.getTipoMercado3()+"%"); break;
                        case  45:  psQuery.setString(j, "%"+cias.getTipoMercado4()+"%"); break;
                        case  46:  psQuery.setString(j, "%"+cias.getTipoMercado5()+"%"); break;
                        case  47:  psQuery.setString(j, "%"+cias.getTipoMercado6()+"%"); break;
                        case  48:  psQuery.setString(j, "%"+cias.getTipoNegocio1()+"%"); break;
                        case  49:  psQuery.setString(j, "%"+cias.getTipoNegocio2()+"%"); break;
                        case  50:  psQuery.setString(j, "%"+cias.getTipoNegocio3()+"%"); break;
                        case  51:  psQuery.setString(j, "%"+cias.getTipoNegocio4()+"%"); break;
                        case  52:  psQuery.setString(j, "%"+cias.getTipoNegocio5()+"%"); break;
                        case  53:  psQuery.setString(j, "%"+cias.getTipoNegocio6()+"%"); break;
                        case  54:  psQuery.setString(j, "%"+cias.getTipoPapel1()+"%"); break;
                        case  55:  psQuery.setString(j, "%"+cias.getTipoPapel2()+"%"); break;
                        case  56:  psQuery.setString(j, "%"+cias.getTipoPapel3()+"%"); break;
                        case  57:  psQuery.setString(j, "%"+cias.getTipoPapel4()+"%"); break;
                        case  58:  psQuery.setString(j, "%"+cias.getTipoPapel5()+"%"); break;
                        case  59:  psQuery.setString(j, "%"+cias.getTipoPapel6()+"%"); break;
                        case  60:  psQuery.setString(j, "%"+cias.getUf()+"%"); break;

                      }
                   }
                       
                     funcao.Select(psQuery);
                     if(ConnectionDB.result.next())
                     {
                        return setData();
                     }
                     else
                     {
                         System.out.println("Não existe empresa para esse parametro");
                         throw new CiasException();
                     }
                } catch (FuncoesDBException ex) {
                    Erro  err = new Erro(true, true);
                    err.setIdErroDB(1101);
                    err.setSystemPrintMessage("    ERRO "+ex.getMessage());
                    err.setInterfaceMessage("ERRO : Erro ao executar script em SQL de criação do banco.");
                    err.Execute();
                    throw new CiasException();
                } catch (SQLException ex) {
                    Erro  err = new Erro(true, true);
                    err.setIdErroDB(1102);
                    err.setSystemPrintMessage("    ERRO "+ex.getMessage());
                    err.setInterfaceMessage("ERRO : Erro ao executar script em SQL de criação do banco.");
                    err.Execute();
                    throw new CiasException();
                }
    
    }
    
    
    public Cia setData() throws SQLException{
      
            Cia cia = new Cia();
            cia.setIdEmpresa(ConnectionDB.result.getString(id_empresa));
            cia.setCodCvm(ConnectionDB.result.getInt(cod_cvm));
            cia.setDenominacaoSocial(ConnectionDB.result.getString(denominacao_social));
            cia.setDenominacaoComercial(ConnectionDB.result.getString(denominacao_comercial));
            cia.setLogradouro(ConnectionDB.result.getString(logradouro));
            cia.setComplemento(ConnectionDB.result.getString(complemento));
            cia.setBairro(ConnectionDB.result.getString(bairro));
            cia.setCep(ConnectionDB.result.getInt(cep));
            cia.setMunicipio(ConnectionDB.result.getString(municipio));
            cia.setUf(ConnectionDB.result.getString(uf));
            cia.setDdd(ConnectionDB.result.getInt(ddd));
            cia.setTelefone(ConnectionDB.result.getInt(telefone));
            cia.setFax(ConnectionDB.result.getInt(fax));
            cia.setDenominacaoAnterior(ConnectionDB.result.getString(denominacao_anterior));
            cia.setSetorAtividade(ConnectionDB.result.getString(setor_atividade));
            cia.setCnpj(ConnectionDB.result.getLong(cnpj));
            cia.setDri(ConnectionDB.result.getString(dri));
            cia.setAuditor(ConnectionDB.result.getString(auditor));
            if(ConnectionDB.result.getBigDecimal(quant_de_acoes_ordinarias)!=null)
             cia.setQuantDeAcoesOrdinarias(ConnectionDB.result.getBigDecimal(quant_de_acoes_ordinarias).toBigInteger());
            else
             cia.setQuantDeAcoesOrdinarias(new BigInteger("0"));
            if(ConnectionDB.result.getBigDecimal(quant_de_acoes_preferenciais)!=null)
             cia.setQuantDeAcoesPreferenciais(ConnectionDB.result.getBigDecimal(quant_de_acoes_preferenciais).toBigInteger());
            else
             cia.setQuantDeAcoesPreferenciais(new BigInteger("0"));
            cia.setSituacao(ConnectionDB.result.getString(situacao));
            cia.setDataDaSituacao(ConnectionDB.result.getDate(data_da_situacao));
            cia.setTipoPapel1(ConnectionDB.result.getString(tipo_papel1));
            cia.setTipoPapel2(ConnectionDB.result.getString(tipo_papel2));
            cia.setTipoPapel3(ConnectionDB.result.getString(tipo_papel3));
            cia.setTipoPapel4(ConnectionDB.result.getString(tipo_papel4));
            cia.setTipoPapel5(ConnectionDB.result.getString(tipo_papel5));
            cia.setTipoPapel6(ConnectionDB.result.getString(tipo_papel6));
            cia.setControleAcionario(ConnectionDB.result.getString(controle_acionario));
            cia.setDataDeRegistro(ConnectionDB.result.getDate(data_de_registro));
            cia.setDataDeCancelamento(ConnectionDB.result.getDate(data_de_cancelamento));
            cia.setMercado(ConnectionDB.result.getString(mercado));
            cia.setBolsa1(ConnectionDB.result.getString(bolsa1));
            cia.setBolsa2(ConnectionDB.result.getString(bolsa2));
            cia.setBolsa3(ConnectionDB.result.getString(bolsa3));
            cia.setBolsa4(ConnectionDB.result.getString(bolsa4));
            cia.setBolsa5(ConnectionDB.result.getString(bolsa5));
            cia.setBolsa6(ConnectionDB.result.getString(bolsa6));
            cia.setBolsa7(ConnectionDB.result.getString(bolsa7));
            cia.setBolsa8(ConnectionDB.result.getString(bolsa8));
            cia.setBolsa9(ConnectionDB.result.getString(bolsa9));
            cia.setMotivoDoCancelamento(ConnectionDB.result.getString(motivo_do_cancelamento));
            cia.setPatrimonioLiquido(ConnectionDB.result.getFloat(patrimonio_liquido));
            cia.setDataDoPatrimonio(ConnectionDB.result.getDate(data_do_patrimonio));
            cia.setEMail(ConnectionDB.result.getString(e_mail));
            cia.setNomeSetorAtividade(ConnectionDB.result.getString(nome_setor_atividade));
            cia.setDataDaAcao(ConnectionDB.result.getDate(data_da_acao));
            cia.setTipoNegocio1(ConnectionDB.result.getString(tipo_negocio1));
            cia.setTipoNegocio2(ConnectionDB.result.getString(tipo_negocio2));
            cia.setTipoNegocio3(ConnectionDB.result.getString(tipo_negocio3));
            cia.setTipoNegocio4(ConnectionDB.result.getString(tipo_negocio4));
            cia.setTipoNegocio5(ConnectionDB.result.getString(tipo_negocio5));
            cia.setTipoNegocio6(ConnectionDB.result.getString(tipo_negocio6));
            cia.setTipoMercado1(ConnectionDB.result.getString(tipo_mercado1));
            cia.setTipoMercado2(ConnectionDB.result.getString(tipo_mercado2));
            cia.setTipoMercado3(ConnectionDB.result.getString(tipo_mercado3));
            cia.setTipoMercado4(ConnectionDB.result.getString(tipo_mercado4));
            cia.setTipoMercado5(ConnectionDB.result.getString(tipo_mercado5));
            cia.setTipoMercado6(ConnectionDB.result.getString(tipo_mercado6));
            
            return cia;
    }
    
    
    
    
    public class CiasException extends Exception {    
          private String empresa="";

          public CiasException(){
                    super();
            }

          public CiasException(String arquivo){
              super();  
              this.empresa=arquivo;    
            }

            @Override
            public String toString(){
                    return "Não foi possivel adquirir os dados da empresa "+empresa;
            }

            @Override
            public String getMessage(){
                    return "Não foi possivel adquirir os dados da empresa "+empresa;

            }
    }
}