/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AtualizacaoEmissor;

import java.io.FileNotFoundException;
import java.io.IOException;


/**
 *
 * @author Erico
 */
public class ComparaArquivos {
    
   protected  String     arquivo1 ;
   protected  String     arquivo2 ;
   
   
 
    LeArquivo bmfcnpj;
    LeArquivo cia ;
    
    DadosbmfeCNPJ dadosbmf ;
    DadosCia dadoscia ;
    
	protected void setArquivo1(String patharq){
  arquivo1=patharq;
 }
  protected void setArquivo2(String patharq){
  arquivo2=patharq;
 } 
  
	private String[] alocadados(int firstline, LeArquivo file) throws FileNotFoundException, IOException{
            String[] lines=file.getline(firstline,firstline+file.numerodelinhas());       
            System.gc();
	    return lines;
		
	}
    
    
    protected boolean[][] Comparacao() throws FileNotFoundException, IOException{
       bmfcnpj = new LeArquivo(arquivo1);
       cia = new LeArquivo(arquivo2);
       String[] lines1 = alocadados(0,bmfcnpj);
       String[] lines2 = alocadados(0, cia);
        
        
        dadosbmf =new DadosbmfeCNPJ(lines1);
        dadoscia = new DadosCia(lines2);
        System.out.println("num linhas :  " +bmfcnpj.numerodelinhas() );
   
       int i=0, j=0;
       boolean[][] confirmados = new boolean[dadosbmf.CNPJ.length][dadoscia.cnpj.length];

       for(j=0;j<dadoscia.cnpj.length-1;j++)
       { 
           if(dadoscia.cnpj[j]!=null )
            {
                for(i=0;i<dadosbmf.CNPJ.length-1;i++)
                 {
                     if(dadosbmf.CNPJ[i]!=null )
                     {
                         if(dadoscia.cnpj[j].toString().equals(dadosbmf.CNPJ[i].toString()))
                         {
                             confirmados[i][j]=true;
                         }
                         else confirmados[i][j]=false;///// 
                     }
                 }
             }
       }
       return confirmados;
      
    
    }
    
}
