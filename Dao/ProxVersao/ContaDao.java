/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao.ProxVersao;

import entity.Cia;
import ConexaoBD.ConnectionDB;
import ConexaoBD.FuncoesDB;
import ConexaoBD.Query;
import ConexaoBD.Query.Operador;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Erico
 * 
 *  INCOMPLETO , DEIXAR PARA VERSÃO 2.0
 */
public class ContaDao {
    FuncoesDB funcao = new FuncoesDB();
    
    public  String fk_empresa="fk_empresa",numero_conta="numero_conta",
                   descricao_conta="descricao_conta";
    public  Date   data_inicio_referecia_principal = new Date(),
                   data_fim_referecia_principal=new Date();
    public double[] valor_conta; 
   
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private String table;
    
    private Query query;
     
    public ContaDao(Cia cia ,int trimestre ,int ano) throws ParseException{
        if (ano <2000) ano+=2000;
        switch(trimestre){
            case 0 :
                 data_fim_referecia_principal =sdf.parse(ano+"-12-31");
                 table ="DFP_CONTA";
                break;
            case 1 :
                data_fim_referecia_principal = sdf.parse(ano+"-03-31");
                table ="ITR_CONTA";
                break;
            case 2 :
                data_fim_referecia_principal = sdf.parse(ano+"-06-30");
                table ="ITR_CONTA";
                break;    
            case 3 :
                data_fim_referecia_principal = sdf.parse(ano+"-09-30");
                table ="ITR_CONTA";
                break; 
            default :
                data_fim_referecia_principal = sdf.parse(ano+"-12-31");
                table ="DFP_CONTA";
            }
        
            try {
                query= new Query();
                query.addSELECTALL();
                query.addFROM();
                query.addtable(table);
                query.addWHERE();
                Operador oper1 =query.new Operador(Query.Operador.IGUAL);
                query.addcondition("fk_empresa",oper1,cia.getIdEmpresa());
                query.addAND();
                query.addcondition("data_fim_referecia_principal", oper1,sdf.format(data_fim_referecia_principal));
                query.addAND();
                Query subquery = new Query();
                subquery.addSELECT();
                subquery.addfunctionMAX("versao_documento");
                subquery.addFROM();
                subquery.addtable(table);
                subquery.addWHERE();
                subquery.addcondition("fk_empresa",oper1,cia.getIdEmpresa());
                subquery.addAND();
                subquery.addcondition("data_fim_referecia_principal", oper1,sdf.format(data_fim_referecia_principal));
                query.addcondition("versao_documento",oper1);
                query.addsubquery(subquery);
                
                
                funcao.Select(query.getQuery());
                if(ConnectionDB.result.next())
                     {
                        setData();
                        return;
                     }
                     else
                     {
                         System.out.println("Não existe empresa para esse parametro");
                     }
            } catch (FuncoesDB.FuncoesDBException ex) {
                Logger.getLogger(ContaDao.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
            Logger.getLogger(ContaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
      public void numerocontaIslike(String numeroconta){
         if(numeroconta.contains("%"))
          {
            query.addAND();
            query.addsimplecolumn("numero_conta");
            query.addLIKE();
            query.addsimplevalue(numeroconta);
          }
         else 
         {
             query.addAND();
             query.addcondition("numero_conta","=",numeroconta);
         }
        try {
            funcao.Select(query.getQuery());
            if(ConnectionDB.result.next())
                     {
                        setData();
                        return;
                     }
                     else
                     {
                         System.out.println("Não existe empresa para esse parametro");
                     }
        } catch (FuncoesDB.FuncoesDBException ex) {
            Logger.getLogger(ContaDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ContaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
      
      } 
      
      public void descricaocontaIslike(String descricaoconta){
          if(descricaoconta.contains("%"))
          {         
            query.addAND();
            query.addsimplecolumn("descricao_conta");
            query.addLIKE();
            query.addsimplevalue(descricaoconta);
          }
          else
          {
             query.addAND();
             query.addcondition("descricao_conta","=",descricaoconta);
          }
          try {
            funcao.Select(query.getQuery());
            if(ConnectionDB.result.next())
                     {
                        setData();
                        return;
                     }
                     else
                     {
                         System.out.println("Não existe empresa para esse parametro");
                     }
        } catch (FuncoesDB.FuncoesDBException ex) {
            Logger.getLogger(ContaDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ContaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
      
      } 
    
      public boolean getNext(){
        try {
            if(ConnectionDB.result.next())
            {
                ConnectionDB.result.findColumn("id_conta");
                setData();
                return true;
            }
            else    
            {
                return false;
            }
        } catch (SQLException ex) {
            System.out.println("Erro de uso da getnext");
            return false;
            
        }
      }
    
      private void setData() throws SQLException{
        fk_empresa= ConnectionDB.result.getString("fk_empresa");
        numero_conta=ConnectionDB.result.getString("numero_conta");
        descricao_conta=ConnectionDB.result.getString("descricao_conta");
        data_inicio_referecia_principal=ConnectionDB.result.getDate("data_inicio_referecia_principal");
        data_fim_referecia_principal=ConnectionDB.result.getDate("data_fim_referecia_principal");
        
        double dtemp[]= new double[12];
        boolean btemp[]= new boolean[12];
        int itemp=0;
        for(int i=0;i<12;i++)
        {
            dtemp[i]= ConnectionDB.result.getDouble("valor_conta_"+(i+1));
            if(dtemp[i]!=0)
            {
                btemp[i]=true;
                itemp++;
            }
            else btemp[i]=false;
        }
        valor_conta= new double[itemp];
        itemp=0;
        for(int i=0;i<12;i++)
        {
            if(btemp[i]==true)
            {
                valor_conta[itemp]=dtemp[i];
                itemp++;
            }
        }   
     }
}

   
