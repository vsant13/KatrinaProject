/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AtualizacaoEmissor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Erico
 */
public class LeArquivo {
    
	protected  String     filename =new String();
	protected  String     filenametxt=new String();
	protected  String     filenamezip=new String();
	protected  String     pathfile  = new String(); 
	protected  String     pathfilename =new String();
	protected  String     pathfilenametxt =new String();
	protected  String     pathfilenamezip =new String();
        private  int numerodelinhas = 0;
        private File arq;
        private String typeChar;
        protected static final int VERSAODOCUMENTO=2;  //Versao 1 antes de 2015.Versao 2 comecou em 2015 .
    
	
	protected LeArquivo(String filename){
                switch(VERSAODOCUMENTO)
                {
                    case 1:
                        typeChar="Cp858";
                        break;
                    case 2:
                        typeChar="ISO-8859-1";
                        break;
                }
            
            
		String temp =new String();
		filename=filename.trim();
		this.filename= filename;
		 PFnames();
	}
	

	protected String getline() throws FileNotFoundException, IOException	{
		return getline(0);
	}
	
	protected String getline(int linenumber) throws FileNotFoundException, IOException{
		return (getline(linenumber,linenumber)[0]);
	}
	
	protected String[] getline(int firstline ,int lastline ) throws FileNotFoundException, IOException{

		if(lastline<firstline)
		{
			String arraylines[] = new String[0];
			return arraylines;
		}
		int i=0,j=1;
		String linedata =new String();
		String arraylines[] = new String[lastline-firstline+1]; 
		
		      this.arq = new File(pathfilenametxt);

				 BufferedReader out = new BufferedReader(new InputStreamReader(new FileInputStream(arq), typeChar));//new FileReader(arq)
				 while(out.ready())
				 { 
					 linedata = out.readLine();
					 if(i==firstline)
					 {
						arraylines[0]=linedata; 
						while(out.ready() && j<=lastline-firstline)
						{
							arraylines[j]=out.readLine();
							j++;
						} 
					 break;
					 }
					 i++;
				 }
				 out.close();
	   return arraylines;
	}
	
	protected int numerodelinhas() throws FileNotFoundException, IOException{ 
            String line;
	   if(this.numerodelinhas==0) 
	   {
		
	      File arq = new File(pathfilenametxt);
		     
			 BufferedReader out = new BufferedReader(new FileReader(arq));
			 while(out.ready()){ 
				line= out.readLine();
				 numerodelinhas++;
			 }
			 out.close();
		
		}
		return this.numerodelinhas;
	}
	
	
	private void PFnames(){
		this.filenametxt= this.filename +".txt";
		this.filenamezip=this.filename +".zip" ;
		this.pathfilename   = this.pathfile+ this.filename;
	    this.pathfilenametxt=this.pathfilename+".txt";
	    this.pathfilenamezip=this.pathfilename+".zip";
		
	}
	
	
}
    
    
