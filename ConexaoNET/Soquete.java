package ConexaoNET;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import Erros.Erro;

public class Soquete {

	public URL url;
	public BufferedReader in;
	public DataOutputStream out;
	public HttpURLConnection conn;
	
	public Soquete(String url){
		
		try {
			this.url = new URL(url);
		} catch (MalformedURLException e) {
			Erro err = new Erro(true,true);
			err.setIdErroDB(17101);
			err.setSystemPrintMessage("ERRO: " + e.getMessage());
			err.setInterfaceMessage("Erro ao formar a URL!!");
			err.ComErroCypecad(true);
			err.Execute();
		}
	}
	
	
	public void descompactador(String from , String to) throws IOException{
		  	
		FileInputStream fis = new FileInputStream(from);  
	    ZipInputStream zis = new ZipInputStream(fis);  
	    FileOutputStream fos = null;  
	    BufferedOutputStream bos = null;  
	    ZipEntry ze = null;  
	    String name = null;  
	    
	    while ((ze = zis.getNextEntry()) != null) {  
	        name = to + '/' + ze.getName();  
	        fos = new FileOutputStream(name);  
	        bos = new BufferedOutputStream(fos, 4096);  
	        System.out.println("descompactando: '" + ze.getName() + "'");  
	        int bytes;  
	        byte buffer[] = new byte[4096];  
	        while ((bytes = zis.read(buffer, 0, 4096)) != -1) {  
	             bos.write(buffer, 0, bytes);  
	        }  
	        bos.flush();  
	        bos.close();  
	   }  
	   zis.close();
	}
	
	public boolean deleteZip(String arq) throws IOException{
		
		File file = new File(arq);
		boolean del =false;
		if(!file.exists()) 
			{
			   System.out.println("Nao existe o arquivo");
	           return del; 		
			}
		else del=file.delete();
		return del;	
	}
	
	
}
