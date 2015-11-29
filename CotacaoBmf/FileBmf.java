package CotacaoBmf;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import Dados.Diretorios;



public class FileBmf {
	
	protected  String     filename =new String();
	protected  String     filenametxt=new String();
	protected  String     filenamezip=new String();
	protected  String     pathfile  = new String(); 
	protected  String     pathfilename =new String();
	protected  String     pathfilenametxt =new String();
	protected  String     pathfilenamezip =new String();
        protected  boolean    isAno=false;
        protected  boolean    isDay=false;
	private static final int posicao_char_ano_dia = 9;
	private static final int tamanhostringano =14;
        private static final int tamanhostringdia =18;
        private  int numerodelinhas = 0;
        private File arq;
    

	public FileBmf(String filename){
		
		String temp =new String();
		filename.trim();
		temp=filename.substring(0, posicao_char_ano_dia+1);
		boolean stringcorreta = false;
		if(temp.equals("COTAHIST_A")==true)
		{
			if (filename.length()>=tamanhostringano){
				 filename=filename.substring(0,tamanhostringano);
				 isAno=true;
                                 stringcorreta= true;
			 }
		}
		if (temp.equals("COTAHIST_D")==true)
		{
			if (filename.length()>=tamanhostringdia ){
				 filename=filename.substring(0,tamanhostringdia);
                                 isDay=true;
				 stringcorreta= true;
			 }
		}
		
		if(stringcorreta==true){
		 this.filename= filename;
		 if(filename.charAt(posicao_char_ano_dia)=='A')
		 {
		     this.pathfile       = Diretorios.path_datafiles_cot_his();
		 }
		 else
		 {
			 this.pathfile=Diretorios.path_datafiles_cot_day() ;
		 } 
		 PFnames();
		}
	}
	
	
	public FileBmf(int ano){
		String Ano = new String();
		if (ano<10) Ano="0"+ano;
		else Ano =""+ano;
		if (ano<100) this.filename="COTAHIST_A"+"20"+Ano;
		else         this.filename="COTAHIST_A"+Ano; 
		this.pathfile=Diretorios.path_datafiles_cot_his();
                isAno=true;
		PFnames();
	}

	
	public FileBmf(int dia ,int mes ,int ano){
		String Dia = new String();
		String Mes = new String();
		String Ano = new String();
		if (dia<10) Dia="0"+dia;
		else Dia =""+dia;
		if (mes<10) Mes= "0"+mes;
		else Mes =""+ mes;
		if (ano<10) Ano="0"+ano;
		else Ano =""+ano;
		if (ano<1000) this.filename="COTAHIST_D"+Dia +Mes +"20"+Ano;
		else          this.filename="COTAHIST_D"+Dia +Mes +Ano; 
		this.pathfile = Diretorios.path_datafiles_cot_day();
		isDay=true;
                PFnames();
	}
	

	
	
	
	public boolean checkfile()throws FileNotFoundException, IOException{ 
	 String linedata=new String();
 
	 linedata=getline();

	 // Verifica se inicio da linha come�a com "01"
	 if (linedata.charAt(0)!='0'|| linedata.charAt(1)!='1') return (false);
	 //Verifica quantidade de caracteres na linha .
	 if(linedata.length()!=245) return(false);
	 //Verifica se a posic�o de '$' est� de acordo com modelo de arquivo.
	 if(linedata.charAt(53)!='$') return(false);
	 //Compara as posi�oes de CODNEGn_e_CODISIn.
	 String str=linedata.substring(12,16);
	 if(!str.equals(linedata.substring(232,236))) return(false); 
	 return (true);
	}
	
	
        public String headfile()throws FileNotFoundException, IOException{
		return getline(0);
	}

	public String getline()throws FileNotFoundException, IOException	{
		return getline(1);
	}
	
	public String getline(int linenumber) throws FileNotFoundException, IOException{
		return (getline(linenumber,linenumber)[0]);
	}
	
	public String[] getline(int firstline ,int lastline ) throws FileNotFoundException, IOException{

		if(lastline<firstline)
		{
			String arraylines[] = new String[0];
			return arraylines;
		}
		int i=0,j=1;
		String linedata =new String();
		String arraylines[] = new String[lastline-firstline+1]; 
		
		      this.arq = new File(pathfilenametxt);
		     
				 BufferedReader out = new BufferedReader(new FileReader(arq));
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
	
	public int numerodelinhas()throws FileNotFoundException, IOException{ 
            String line;
	   if(this.numerodelinhas==0)
               
	   {
		
	      File arq = new File(pathfilenametxt);
		     
			 BufferedReader out = new BufferedReader(new FileReader(arq));
			 while(out.ready()){ 
				line= out.readLine();
				 numerodelinhas++;
                                 if(line.startsWith("99")) break;
			 }
			 out.close();
		
	  }
		return this.numerodelinhas;
	}
	public void deletaArquivo(){
           File file = new File(pathfilenametxt);
		if(file.exists()) 
			file.delete();
           file = new File(pathfilenamezip);
           if(file.exists()) 
			file.delete();
        }
	
	private void PFnames(){
		if((((System.getProperty("os.name")).toLowerCase()).trim()).equals("linux"))
                {
                    this.filenametxt= this.filename +".TXT";
                    this.filenamezip=this.filename +".zip" ;
                    this.pathfilename   = this.pathfile+ this.filename;
                    this.pathfilenametxt=this.pathfilename+".TXT";
                    this.pathfilenamezip=this.pathfilename+".zip"; 
                }
                else
                {
                    this.filenametxt= this.filename +".txt";
                    this.filenamezip=this.filename +".zip" ;
                    this.pathfilename   = this.pathfile+ this.filename;
                    this.pathfilenametxt=this.pathfilename+".txt";
                    this.pathfilenamezip=this.pathfilename+".zip";
                }
		
	}
	
	
}
