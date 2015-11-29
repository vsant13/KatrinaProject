package AtualizacaoEmissor;


import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.StringTokenizer;


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Erico
 */
public class DadosCia{
        
    private int VERSAODOCUMENTO;  //Versao 1 antes de 2015.Versao 2 comecou em 2015 .
    protected  String[] lines;
    protected  StringBuilder[] 
          cod_cvm,
denominacao_social,
denominacao_comercial,
logradouro,
complemento,
bairro,
cep,
municipio,
uf,
ddd,
telefone,
fax,
denominacao_anterior,
setor_atividade,
cnpj,
dri,
auditor,
quant_de_acoes_ordinarias,
quant_de_acoes_preferenciais,
situacao,
data_da_situacao,
tipo_papel1,
tipo_papel2,
tipo_papel3,
tipo_papel4,
tipo_papel5,
tipo_papel6,
controle_acionario,
data_de_registro,
data_de_cancelamento,
mercado,
bolsa1,
bolsa2,
bolsa3,
bolsa4,
bolsa5,
bolsa6,
bolsa7,
bolsa8,
bolsa9,
motivo_do_cancelamento,
patrimonio_liquido,
data_do_patrimonio,
e_mail,
nome_setor_atividade,
data_da_acao,
tipo_negocio1,
tipo_negocio2,
tipo_negocio3,
tipo_negocio4,
tipo_negocio5,
tipo_negocio6,
tipo_mercado1,
tipo_mercado2,
tipo_mercado3,
tipo_mercado4,
tipo_mercado5,
tipo_mercado6;
boolean[] cnpj_null;
     
        
    
   protected DadosCia(String[] lines){
       VERSAODOCUMENTO=LeArquivo.VERSAODOCUMENTO;
       this.lines=lines;
       aloca(lines.length);
       for(int i=1;i<lines.length-1;i++)
             {
		 setData(i,lines[i]);
             }
   }
    
    
    private void setData(int i,String linha){
       
        
       linha=linha.replaceAll("\"", "-");
       String replChar="";
       String forChar="";
       String serchChar="";
       switch(VERSAODOCUMENTO)
       {    
           case 1:
           replChar=";;";
           forChar=";_;";
           serchChar=";";
               break;
           case 2:
           replChar="\t\t";
           forChar="\t_\t";
           serchChar="\t"; 
               break;
       }
     
       while(linha.contains(replChar))
       {
           linha=linha.replaceAll(replChar, forChar);
       }
       
         linha=linha+"_";
        StringTokenizer token= new StringTokenizer(linha ,serchChar);
        
            
        String[] palavra = new String[60];
        String str_temp;
        int j=0;

        while(token.hasMoreTokens())
         {               
                str_temp=token.nextToken();
                palavra[j]=str_temp;
                 j++; 
         }
        switch(VERSAODOCUMENTO)
        {
            case 1:
               metvers1(i,palavra); 
                break;
            case 2:
               metvers2(i,palavra); 
                break; 
        }
   
    }
    
            
          
   private void metvers1(int i , String palavra[]){
       
                    cod_cvm[i]=colocaAspas (palavra[0].trim());
            denominacao_social[i]=colocaAspas (palavra[1].trim());
            denominacao_comercial[i]=colocaAspas (palavra[2].trim());
            logradouro[i]=colocaAspas (palavra[3].trim());
            complemento[i]=colocaAspas (palavra[4].trim());
            bairro[i]=colocaAspas (palavra[5].trim());
            cep[i]=colocaAspas (palavra[6].trim());
            municipio[i]=colocaAspas (palavra[7].trim());
            uf[i]=colocaAspas (palavra[8].trim());
            ddd[i]=colocaAspas (palavra[9].trim());
            telefone[i]=colocaAspas (palavra[10].trim());
            fax[i]=colocaAspas (palavra[11].trim());  
            denominacao_anterior[i]=colocaAspas (palavra[12].trim());
            setor_atividade[i]=colocaAspas (palavra[13].trim());
            cnpj[i]=colocaAspas (palavra[14].trim());
            dri[i]=colocaAspas (palavra[15].trim());
            auditor[i]=colocaAspas (palavra[16].trim());
            quant_de_acoes_ordinarias[i]=colocaAspas (palavra[17].trim());
            quant_de_acoes_preferenciais[i]=colocaAspas (palavra[18].trim());
            situacao[i]=colocaAspas (palavra[19].trim());

            tipo_papel1[i]=colocaAspas (palavra[21].trim());
            tipo_papel2[i]=colocaAspas (palavra[22].trim());
            tipo_papel3[i]=colocaAspas (palavra[23].trim());
            tipo_papel4[i]=colocaAspas (palavra[24].trim());
            tipo_papel5[i]=colocaAspas (palavra[25].trim());
            tipo_papel6[i]=colocaAspas (palavra[26].trim());
            controle_acionario[i]=colocaAspas (palavra[27].trim());


            mercado[i]=colocaAspas (palavra[30].trim());
            bolsa1[i]=colocaAspas (palavra[31].trim());
            bolsa2[i]=colocaAspas (palavra[32].trim());
            bolsa3[i]=colocaAspas (palavra[33].trim());
            bolsa4[i]=colocaAspas (palavra[34].trim());
            bolsa5[i]=colocaAspas (palavra[35].trim());
            bolsa6[i]=colocaAspas (palavra[36].trim());
            bolsa7[i]=colocaAspas (palavra[37].trim());
            bolsa8[i]=colocaAspas (palavra[38].trim());
            bolsa9[i]=colocaAspas (palavra[39].trim());
            motivo_do_cancelamento[i]=colocaAspas (palavra[40].trim());
            patrimonio_liquido[i]=colocaAspas (palavra[41].trim());

            e_mail[i]=colocaAspas (palavra[43].trim());
            nome_setor_atividade[i]=colocaAspas (palavra[44].trim());

            tipo_negocio1[i]=colocaAspas (palavra[46].trim());
            tipo_negocio2[i]=colocaAspas (palavra[47].trim());
            tipo_negocio3[i]=colocaAspas (palavra[48].trim());
            tipo_negocio4[i]=colocaAspas (palavra[49].trim());
            tipo_negocio5[i]=colocaAspas (palavra[50].trim());
            tipo_negocio6[i]=colocaAspas (palavra[51].trim());
            tipo_mercado1[i]=colocaAspas (palavra[52].trim());
            tipo_mercado2[i]=colocaAspas (palavra[53].trim());
            tipo_mercado3[i]=colocaAspas (palavra[54].trim());
            tipo_mercado4[i]=colocaAspas (palavra[55].trim());
            tipo_mercado5[i]=colocaAspas (palavra[56].trim());
            tipo_mercado6[i]=colocaAspas (palavra[57].trim());  

            Date DataSit ;
            Date DataReg ;  
            Date DataCan ;  
            Date DataPat ;  
            Date DataAca ;  
            String DataNascDb ;
            try{

                if(!palavra[20].equals("_")&& palavra[20].length()==10){  
                DataSit = new SimpleDateFormat("dd/MM/yyyy").parse(palavra[20].trim());
                DataNascDb = new SimpleDateFormat("yyyy-MM-dd").format(DataSit);
                data_da_situacao[i]=colocaAspas (DataNascDb);
                }
                else {data_da_situacao[i]=colocaAspas ("_"); }

                if(!palavra[28].equals("_")&& palavra[28].length()==10){ 
                DataReg = new SimpleDateFormat("dd/MM/yyyy").parse(palavra[28].trim());
                DataNascDb = new SimpleDateFormat("yyyy-MM-dd").format(DataReg);
                data_de_registro[i]=colocaAspas (DataNascDb);
                 }
                else data_de_registro[i]=colocaAspas ("_");

                if(!palavra[29].equals("_")&& palavra[29].length()==10){ 
                DataCan = new SimpleDateFormat("dd/MM/yyyy").parse(palavra[29].trim());
                DataNascDb = new SimpleDateFormat("yyyy-MM-dd").format(DataCan);
                data_de_cancelamento[i]=colocaAspas (DataNascDb);
                 }
                else {data_de_cancelamento[i]=colocaAspas ("_"); }

                if(!palavra[42].equals("_")&& palavra[42].length()==10){ 
                DataPat = new SimpleDateFormat("dd/MM/yyyy").parse(palavra[42].trim());
                DataNascDb = new SimpleDateFormat("yyyy-MM-dd").format(DataPat);
                data_do_patrimonio[i]=colocaAspas (DataNascDb);
                 }
                else {data_do_patrimonio[i]=colocaAspas ("_"); }

                if(!palavra[45].equals("_")&& palavra[45].length()==10){ 
                DataAca = new SimpleDateFormat("dd/MM/yyyy").parse(palavra[45].trim());
                DataNascDb = new SimpleDateFormat("yyyy-MM-dd").format(DataAca);
                data_da_acao[i]=colocaAspas (DataNascDb);
                 }
                else {data_da_acao[i]=colocaAspas ("_"); }

            }catch( java.text.ParseException e ){  
              e.printStackTrace();  
            }   
   
   }
        
   private void metvers2(int i , String palavra[]){
       
                    cod_cvm[i]=colocaAspas (palavra[0].trim());
            denominacao_social[i]=colocaAspas (palavra[1].trim());
            denominacao_comercial[i]=colocaAspas (palavra[2].trim());
            setor_atividade[i]=colocaAspas (palavra[3].trim());
            //Nao existe campo 4 no banco.
            cnpj[i]=colocaAspas (palavra[5].trim());
            //campo 6 data de registro
            //Nao existe campo 7 no banco.
            //campo 8 data de cancelamento.
            motivo_do_cancelamento[i]=colocaAspas (palavra[9].trim());
            situacao[i]=colocaAspas (palavra[10].trim());
            //campo 11 data de situacao.
            //Nao existe campo 12 no banco.
            //Nao existe campo 13 no banco.
            //Nao existe campo 14 no banco.
            //Nao existe campo 15 no banco.
            auditor[i]=colocaAspas (palavra[16].trim());
            //Nao existe campo 17 no banco.
            //Nao existe campo 18 no banco.
            logradouro[i]=colocaAspas (palavra[19].trim());
            complemento[i]=colocaAspas (palavra[20].trim());
            bairro[i]=colocaAspas (palavra[21].trim());
            municipio[i]=colocaAspas (palavra[22].trim());
            uf[i]=colocaAspas (palavra[23].trim());
             //Nao existe campo 24 no banco.
            cep[i]=colocaAspas (palavra[25].trim());
            int k;
            if(palavra[26].length()>1)
            {
                k=palavra[26].indexOf(")");
                String temp=palavra[26].substring(1,k);
                ddd[i]=colocaAspas(temp.trim());
                telefone[i]=colocaAspas (palavra[26].substring(k+1).trim());
            }
            else
            {
                 ddd[i]=colocaAspas("_");
                telefone[i]=colocaAspas ("_");
            }
            if(palavra[27].length()>1)
            {
                k=palavra[27].indexOf(")");
               fax[i]=colocaAspas (palavra[27].substring(k+1).trim());  
            }
            
            e_mail[i]=colocaAspas (palavra[28].trim());
            

            denominacao_anterior[i]=colocaAspas ("_");
            dri[i]=colocaAspas ("_");
            quant_de_acoes_ordinarias[i]=colocaAspas ("_");
            quant_de_acoes_preferenciais[i]=colocaAspas ("_");
            tipo_papel1[i]=colocaAspas ("_");
            tipo_papel2[i]=colocaAspas ("_");
            tipo_papel3[i]=colocaAspas ("_");
            tipo_papel4[i]=colocaAspas ("_");
            tipo_papel5[i]=colocaAspas ("_");
            tipo_papel6[i]=colocaAspas ("_");
            controle_acionario[i]=colocaAspas ("_");
            mercado[i]=colocaAspas ("_");
            bolsa1[i]=colocaAspas ("_");
            bolsa2[i]=colocaAspas ("_");
            bolsa3[i]=colocaAspas ("_");
            bolsa4[i]=colocaAspas ("_");
            bolsa5[i]=colocaAspas ("_");
            bolsa6[i]=colocaAspas ("_");
            bolsa7[i]=colocaAspas ("_");
            bolsa8[i]=colocaAspas ("_");
            bolsa9[i]=colocaAspas ("_");
            patrimonio_liquido[i]=colocaAspas ("_");
            nome_setor_atividade[i]=colocaAspas ("_");
            tipo_negocio1[i]=colocaAspas ("_");
            tipo_negocio2[i]=colocaAspas ("_");
            tipo_negocio3[i]=colocaAspas ("_");
            tipo_negocio4[i]=colocaAspas ("_");
            tipo_negocio5[i]=colocaAspas ("_");
            tipo_negocio6[i]=colocaAspas ("_");
            tipo_mercado1[i]=colocaAspas ("_");
            tipo_mercado2[i]=colocaAspas ("_");
            tipo_mercado3[i]=colocaAspas ("_");
            tipo_mercado4[i]=colocaAspas ("_");
            tipo_mercado5[i]=colocaAspas ("_");
            tipo_mercado6[i]=colocaAspas ("_");  

            Date DataSit ;
            Date DataReg ;  
            Date DataCan ;  
            Date DataPat ;  
            Date DataAca ;  
            String DataNascDb ;
            try{

                if(!palavra[11].equals("_")&& palavra[11].length()==10){  
                DataSit = new SimpleDateFormat("dd/MM/yyyy").parse(palavra[11].trim());
                DataNascDb = new SimpleDateFormat("yyyy-MM-dd").format(DataSit);
                data_da_situacao[i]=colocaAspas (DataNascDb);
                }
                else {data_da_situacao[i]=colocaAspas ("_"); }

                if(!palavra[6].equals("_")&& palavra[6].length()==10){ 
                DataReg = new SimpleDateFormat("dd/MM/yyyy").parse(palavra[6].trim());
                DataNascDb = new SimpleDateFormat("yyyy-MM-dd").format(DataReg);
                data_de_registro[i]=colocaAspas (DataNascDb);
                 }
                else data_de_registro[i]=colocaAspas ("_");

                if(!palavra[8].equals("_")&& palavra[8].length()==10){ 
                DataCan = new SimpleDateFormat("dd/MM/yyyy").parse(palavra[8].trim());
                DataNascDb = new SimpleDateFormat("yyyy-MM-dd").format(DataCan);
                data_de_cancelamento[i]=colocaAspas (DataNascDb);
                 }
                else {data_de_cancelamento[i]=colocaAspas ("_"); }

                if(!palavra[7].equals("_")&& palavra[7].length()==10){ 
                DataPat = new SimpleDateFormat("dd/MM/yyyy").parse(palavra[7].trim());
                DataNascDb = new SimpleDateFormat("yyyy-MM-dd").format(DataPat);
                data_do_patrimonio[i]=colocaAspas (DataNascDb);
                 }
                else {data_do_patrimonio[i]=colocaAspas ("_"); }

               data_da_acao[i]=colocaAspas ("_"); 

            }catch( java.text.ParseException e ){  
              e.printStackTrace();  
            }   
   
   }
   
   
    
    private void aloca(int tamanho){
        
        cod_cvm= new StringBuilder[tamanho];
denominacao_social= new StringBuilder[tamanho];
denominacao_comercial= new StringBuilder[tamanho];
logradouro= new StringBuilder[tamanho];
complemento= new StringBuilder[tamanho];
bairro= new StringBuilder[tamanho];
cep= new StringBuilder[tamanho];
municipio= new StringBuilder[tamanho];
uf= new StringBuilder[tamanho];
ddd= new StringBuilder[tamanho];
telefone= new StringBuilder[tamanho];
fax= new StringBuilder[tamanho];
denominacao_anterior= new StringBuilder[tamanho];
setor_atividade= new StringBuilder[tamanho];
cnpj= new StringBuilder[tamanho];
dri= new StringBuilder[tamanho];
auditor= new StringBuilder[tamanho];
quant_de_acoes_ordinarias= new StringBuilder[tamanho];
quant_de_acoes_preferenciais= new StringBuilder[tamanho];
situacao= new StringBuilder[tamanho];
data_da_situacao= new StringBuilder[tamanho];
tipo_papel1= new StringBuilder[tamanho];
tipo_papel2= new StringBuilder[tamanho];
tipo_papel3= new StringBuilder[tamanho];
tipo_papel4= new StringBuilder[tamanho];
tipo_papel5= new StringBuilder[tamanho];
tipo_papel6= new StringBuilder[tamanho];
controle_acionario= new StringBuilder[tamanho];
data_de_registro= new StringBuilder[tamanho];
data_de_cancelamento= new StringBuilder[tamanho];
mercado= new StringBuilder[tamanho];
bolsa1= new StringBuilder[tamanho];
bolsa2= new StringBuilder[tamanho];
bolsa3= new StringBuilder[tamanho];
bolsa4= new StringBuilder[tamanho];
bolsa5= new StringBuilder[tamanho];
bolsa6= new StringBuilder[tamanho];
bolsa7= new StringBuilder[tamanho];
bolsa8= new StringBuilder[tamanho];
bolsa9= new StringBuilder[tamanho];
motivo_do_cancelamento= new StringBuilder[tamanho];
patrimonio_liquido= new StringBuilder[tamanho];
data_do_patrimonio= new StringBuilder[tamanho];
e_mail= new StringBuilder[tamanho];
nome_setor_atividade= new StringBuilder[tamanho];
data_da_acao= new StringBuilder[tamanho];
tipo_negocio1= new StringBuilder[tamanho];
tipo_negocio2= new StringBuilder[tamanho];
tipo_negocio3= new StringBuilder[tamanho];
tipo_negocio4= new StringBuilder[tamanho];
tipo_negocio5= new StringBuilder[tamanho];
tipo_negocio6= new StringBuilder[tamanho];
tipo_mercado1= new StringBuilder[tamanho];
tipo_mercado2= new StringBuilder[tamanho];
tipo_mercado3= new StringBuilder[tamanho];
tipo_mercado4= new StringBuilder[tamanho];
tipo_mercado5= new StringBuilder[tamanho];
tipo_mercado6= new StringBuilder[tamanho];
cnpj_null= new boolean[tamanho];      
    

    }
    
    
    private StringBuilder colocaAspas(String str){
      
        StringBuilder rs ;
      if(!str.isEmpty() && !str.equals("_")){ rs=new StringBuilder("\"");
      rs.append(str);
      rs.append("\"");
      }
      else rs=null;
      return (rs);
    
    }
    
    
    /* private static String convertANSItoISO88591(String str) {
	String ret ;
	try {
		ret = new String(str.getBytes("Cp858"),"ISO-8859-1");
               
        }catch (java.io.UnsupportedEncodingException e) {
		return null;
	}
	return ret;
     }
    */
}  
            