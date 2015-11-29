/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexaoBD;

import Dados.ConfiguracoesIniciais;
import sun.misc.Queue;

/**
 *
 * @author Erico
 */




public class Query {

    private String Database= ConfiguracoesIniciais.getNomeDatabasepadrao();
    
    
    public final String SELECT ="\n SELECT ";
    public final String SELECTALL ="\n SELECT * ";
    public final String SELECT_DISTINCT =" \nSELECT DISTINCT ";
    public final String DISTINCT =" DISTINCT ";
    public final String FROM ="\n FROM ";
    public final String WHERE ="\n WHERE \n";
    public final String AND =" AND \n";
    public final String OR =" OR \n";
    public final String IN =" IN ";
    public final String BETWEEN =" BETWEEN ";
    public final String LIKE =" LIKE ";
    public final String ORDERBY=" ORDER BY ";
    public final String GROUPBY =" GROUP BY ";
    public final String HAVING= " HAVING \n";
    public final String JOIN ="\n JOIN \n";
    public final String INNERJOIN="\n INNER JOIN \n";
    public final String LEFTJOIN="\n LEFT JOIN \n";
    public final String RIGHTJOIN="\n RIGHT JOIN \n";
    public final String FULLJOIN="\n FULLJOIN \n";
    public final String ON=" ON \n";
    public final String INTO =" INTO ";
    public final String UNION = "\n UNION \n";
    public final String UNIONALL = "\n UNION ALL \n";
    public final String EXISTS = " EXISTS ";
    public final String CASE =" CASE \n";
    public final String WHEN =" WHEN \n";
    public final String THEN =" THEN \n";
    public final String ELSE =" ELSE ";
    public final String END =" END \n";
    public final String IFNULL =" IFNULL(";
    public final String COALESCE= " COALESCE(";
    public final String NULLIF =" NULLIF(";
    public final String SUM ="SUM(";
    public final String MIN =" MIN(";
    public final String MAX =" MAX(";
    public final String COUNT =" COUNT(";
    public final String AVG = " AVG(";
    public final String STD= " STD(";
    public final String STDDEV=" STDDEV(";
    public final String LIMIT =" LIMIT ";
    public final String AS=" AS \n";
    public final String NOW=" NOW() ";
    public final String CURDATE =" CURDATE() ";
    public final String CURTIME= " CURTIME() ";
    public final String DATE= " DATE(";
    public final String EXTRACT= " EXTRACT(";
    public final String MONTH="MONTH";
    public final String DAY="DAY";
    public final String YEAR="YEAR";
    public final String DATE_ADD= " DATE_ADD(";
    public final String DATE_SUB=" DATE_SUB(";
    public final String DATEDIFF= " DATEDIFF(";
    public final String INTERVAL=" INTERVAL ";
    public final String DESC= " DESC ";
    public final String SYSDATE= " SYSDATE() ";
    public final String NOT =" NOT ";
    
    private String QUERY="";
    
 
    public String getQuery(){
        return QUERY;
    }
    public void setDatabase(String nomedatabase){
        Database=nomedatabase;
    }
    public void addSELECT(){
        QUERY+=SELECT;
    }
    public void addSELECTALL(){
        QUERY+=SELECTALL;
    }
    public void addSELECT_DISTINCT(){
        QUERY+=SELECT_DISTINCT;
    }
    public void DISTINCT(){
        QUERY+=DISTINCT;
    }
    public void addcolumn(Column column){  //FALTA CONSERTAR
       QUERY+=column.getcolumn();
    }
    public void addsimplecolumn(String simplecolumn){
        QUERY+=simplecolumn;
    }
    public void addFROM(){
        QUERY+=FROM;
    }
    public void addtable(String table){
        table.trim();
        if(table.contains("."))
            QUERY+=table+" ";
        else
            QUERY+=Database+"."+table+" ";
    }
    public void addWHERE(){
        QUERY+=WHERE;
    }       
    public void addcondition(String column,Operador operador,String value){
        value=value.replaceAll("'", "");
        value=value.replaceAll("\"","");
        value="'"+value+"'";
        QUERY+=column+operador.getoperador()+value+" ";
    }
    public void addcondition(String column,String operador,String value){
        value=value.replaceAll("'", "");
        value=value.replaceAll("\"","");
        value="'"+value+"'";
        QUERY+=column+operador+value+" ";
    }
    public void addcondition(String column,String operador){
        QUERY+=column+operador;
    }
    public void addcondition(String column,Operador operador){
        QUERY+=column+operador.getoperador();
    }
    public void addcondition(String column,Operador operador,Query subquery){
        QUERY+=column+operador.getoperador();
        addsubquery(subquery);
    }
    public void addIN(){
        QUERY+=IN;
    }
    public void addON(){
        QUERY+=ON;
    }
    public void addAND(){
        QUERY+=AND;
    }
    public void assNOT(){
        QUERY+=NOT;
    }
    public void addOR(){
        QUERY+=OR;
    }
    public void addBETWEEN(){
        QUERY+=BETWEEN;
    }
     public void addLIKE(){
        QUERY+=LIKE;
    }
     public void addORDER_BY(){
        QUERY+=ORDERBY;
    }
     public void addGROUP_BY(){
        QUERY+=GROUPBY;
    }
     public void addHAVING(){
        QUERY+=HAVING;
    }
     public void addJOIN(){
        QUERY+=JOIN;
    }
     public void addINNERJOIN(){
        QUERY+=INNERJOIN;
    }
     public void addLEFTJOIN(){
        QUERY+=LEFTJOIN;
    }
     public void addRIGHTJOIN(){
        QUERY+=RIGHTJOIN;
    }
     public void addFULLJOIN(){
        QUERY+=FULLJOIN;
    }
     public void addINTO(){
        QUERY+=INTO;
    }
     public void addUNION(){
        QUERY+=UNION;
    }
     public void addUNIONALL(){
        QUERY+=UNIONALL;
    }
     public void addEXISTS(){
        QUERY+=EXISTS;
    }
     public void addCASE(){
        QUERY+=CASE;
    }
     public void addWHEN(){
        QUERY+=WHEN;
    }
     public void addTHEN(){
        QUERY+=THEN;
    }
     public void addELSE(){
        QUERY+=ELSE;
    }
     public void addEND(){
        QUERY+=END;
    }
     public void addLIMIT(){
        QUERY+=LIMIT;
    }
     public void addAS(){
        QUERY+=AS;
    }
     public void addfunctionNOW(){
        QUERY+=NOW;
    }
    public void addfunctionCURDATE(){
        QUERY+=CURDATE;
    }
    public void addfunctionCURTIME(){
        QUERY+=CURTIME;
    }
    public void addMONTH(){
        QUERY+=MONTH;
    }
    public void addDAY(){
        QUERY+=DAY;
    }
     public void addYEAR(){
        QUERY+=YEAR;
    }
     public void addDESC(){
        QUERY+=DESC;
    }
     public void addINTERVAL(){
        QUERY+=INTERVAL;
    }
     public void addfunctionIFNULL(){
        QUERY+=IFNULL+"?) ";
    }
     public void addfunctionNULLIF(){
        QUERY+=NULLIF+"?) ";
    }
     public void addfunctionCOALESCE(Column column){
        QUERY+=COALESCE+column.getcolumn()+") ";
    } 
     public void addfunctionSUM(String column){
        QUERY+=SUM+column+") ";
    }
     public void addfunctionMIN(String column){
        QUERY+=MIN+column+") ";
    }
     public void addfunctionMAX(String column){
        QUERY+=MAX+column+") ";
    }
     public void addfunctionCOUNT(String column){
        QUERY+=COUNT+column+") ";
    }
     public void addfunctionAVG(String column){
        QUERY+=AVG+column+") ";
    }
     public void addfunctionSTD(String column){
        QUERY+=STD+column+") ";
    }
     public void addfunctionSTDDEV(String column){
        QUERY+=STDDEV+column+") ";
    }
     public void addfunctionDATE(String column){
        QUERY+=DATE+column+") ";
    }
     public void addfunctionEXTRACT(){
        QUERY+=EXTRACT;
     }
     public void addfunctionDATE_SUB(String columndate,int quanto ,String tipo){
        QUERY+=DATE_SUB+columndate+","+INTERVAL+"'"+quanto+"' "+tipo+") ";
     }
     public void addfunctionDATE_ADD(String columndate,int quanto ,String tipo){
        QUERY+=DATE_ADD+columndate+","+INTERVAL+"'"+quanto+"' "+tipo+") ";
     }
     public void addfunctionDATEDIFF(String columndate1,String columndate2){
        QUERY+=DATEDIFF+"'"+columndate1+"','"+columndate2+"') ";
     }
     public void addfunctionSYSDATE(){
         QUERY+=SYSDATE;
     }
     public void addtext(String text){
        QUERY+=" "+text+" ";
     }
     public void addNewLocalValues(int quantidadedevalores){
         if(quantidadedevalores<1) return;
         if(quantidadedevalores==1) 
         {
             QUERY+=" ? ";
             return;
         }
            QUERY+=" ( ";
         for(int i=1;i<quantidadedevalores;i++){
          QUERY+=" ? ,";
         }
            QUERY+=" ? )";
     }
     public void addvalue(Value value){
         QUERY+=value.getvalue()+" ";
     }
       public void addsimplevalue(String simplevalue){
         simplevalue=simplevalue.replaceAll("'", "");
         simplevalue=simplevalue.replaceAll("\"","");
         simplevalue="'"+simplevalue+"' "; 
         QUERY+=simplevalue;
     }
     public void addsubquery(Query query){
        QUERY+="("+query.QUERY+") ";
     }
     public void add(String variaveislocais){
        QUERY+=variaveislocais;
     }
     public void addquery(Query query){
        QUERY+=" "+query.QUERY+" ";         
     }
     public class Operador{
        public static final String IGUAL="=";
        public static final String MENORIGUAL="<=";
        public static final String MAIORIGUAL=">=" ;
        public static final String MENOR="<";
        public static final String MAIOR=">";
        public static final String DIFERENTEDE="<>";
        public static final String MAIS="+";
        public static final String MENOS="-";
        public static final String MULTIPICA="*";
        public static final String Divide="/";
        private String OPERADOR; 

        public Operador(String operador){
            OPERADOR=operador;
        }
        public void change(String operador){
            OPERADOR=operador;
        }
                
        protected String getoperador(){
            return OPERADOR;
        }
        
    }

  
    public class Value{
          private  Queue queue ;  
          private int length; 
          private String value=null;
          private boolean withparenteses=false;
         
          public Value(){
             queue = new Queue();
             length=0;
          }
          public Value(String Value){
             queue = new Queue();
             queue.enqueue(Value);
             length=1;
          }
          public void addValues(String Value){
              queue = new Queue();
              length++;
         }
         /* public void addValues(String Value){
             Value=Value.replaceAll("'", "");
             Value=Value.replaceAll("\"","");
             Value="'"+Value+"'";
             if(value.equals(null)) value+=Value+"\n";
             else value=","+Value+"\n";
         }
          
           public Value(String Value){
             Value=Value.replaceAll("'", "");
             Value=Value.replaceAll("\"","");
             Value="'"+Value+"'";
             value+=Value; 
         }
         public void addValues(String Value){
             Value=Value.replaceAll("'", "");
             Value=Value.replaceAll("\"","");
             Value="'"+Value+"'";
             if(value.equals(null)) value+=Value+"\n";
             else value=","+Value+"\n";
         }*/
         public void addParenteses(){
             withparenteses=true;
         }
         protected String getvalue(){
             if(withparenteses)value="("+value+")";
            return value;
         }
     }
    
     public class Column{
          private String coluna="";
          private boolean withparenteses=false;
          private Queue colunas;
          public Column(String Column){
              
             coluna+=Column; 
         }
         public void addColumn(String Column){
             if(coluna.equals("")) coluna=Column+"\n";
             else coluna+=","+Column+"\n";
         }
         public void addParenteses(){
             withparenteses=true;
         }
         protected String getcolumn(){
             if(withparenteses)coluna="("+coluna+")";
            return coluna;
         }
     }
        
}
