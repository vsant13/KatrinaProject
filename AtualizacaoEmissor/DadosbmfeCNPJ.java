
package AtualizacaoEmissor;

import java.util.StringTokenizer;

 class DadosbmfeCNPJ {
   
    protected String nome_do_arquivo,
                  data_de_geracao;
            
   protected  String[] lines;
   protected  StringBuilder[]
            codigobmf,
            nome,
            CNPJ;
    
    
   protected DadosbmfeCNPJ (String[] lines){
       this.lines=lines;
       codigobmf= new StringBuilder[lines.length];
       nome= new StringBuilder[lines.length];
       CNPJ= new StringBuilder[lines.length];
       for(int i=0;i<lines.length-1;i++)
             {
      		 setData(i,lines[i]);      
             }
    
    }
    
    private void setData(int i,String linha){
        
        linha=linha.replaceAll("\",\"","\"#%\"");
        StringTokenizer token= new StringTokenizer(linha ,"#%");
        String[] palavra = new String[6];
        String temp;
        int j=0;
        
				    while(token.hasMoreTokens())
                                    {
                                        palavra[j]= token.nextToken();
					    j++;
                                            if(j>5)
                                                break;
				    }
                                    
            codigobmf[i]=new StringBuilder(palavra[0].trim());
            nome[i]=new StringBuilder(palavra[1].trim());
            temp=palavra[2].trim();
            if(!temp.isEmpty()){
                    while(temp.startsWith("\"0"))
                    {
                        temp=temp.replaceFirst("\"0", "\"");
                    }
            }
            CNPJ[i]=new StringBuilder(temp.trim()); 
        
    }
    
}
