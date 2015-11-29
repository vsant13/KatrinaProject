package ArquivosCvm;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

import ConexaoNET.Soquete;
import Dados.Diretorios;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileCvmDown extends Soquete{
	
	private FileOutputStream file ;
	private InputStream is;
	private String filename = "cvmfile.zip";
	protected String path = "";
        String data;
        String barraWinOuLin;
	
	public FileCvmDown(String adress, String cvm, String data) throws IOException{
		super(adress);
                
                if(System.getProperty("os.name").toLowerCase().trim().equals("linux"))
                    barraWinOuLin="/";
                else
                    barraWinOuLin="\\";
                
		this.data = data;
		createFolders(cvm);
                String ano = this.data;
		ano = ano.substring(6);
		path = Diretorios.path_datafiles_cvm_data() + barraWinOuLin + cvm + barraWinOuLin + ano + barraWinOuLin;
                if(data.substring(3, 5).equals("03"))
			path += "1T_" + ano + barraWinOuLin;
		else if (data.substring(3, 5).equals("06")) 
			path += "2T_" + ano + barraWinOuLin;
		else if (data.substring(3, 5).equals("09"))
			path += "3T_" + ano + barraWinOuLin;
                System.out.print(adress);
                conn = (HttpURLConnection) url.openConnection();
		is = url.openStream();
		file = new FileOutputStream(path + filename);
                
	}
	
	public void createFolders(String cvm){
		
		String aux = Diretorios.path_datafiles_cvm_data() + cvm + barraWinOuLin;
		String ano = "";
		
		if( !(new File(aux).isDirectory()))
			new File(aux).mkdir();
		ano = data;
		ano = ano.substring(6);
		aux = aux + ano + barraWinOuLin;
			
		if(!(new File(aux).isDirectory())){
				new File(aux).mkdir();
				new File(aux + "1T_" + ano).mkdir();
				new File(aux + "2T_" + ano).mkdir();
				new File(aux + "3T_" + ano).mkdir();
		}
	}
	
	public void downFiles() throws IOException{
		
		byte[] b = new byte[1024];
		int count;
                String filename2;
		
		while((count = is.read(b)) >= 0){
			file.write(b, 0 , count);
		}
		file.close();
		descompactador(path + filename,path);
		deleteZip(path + filename);
		File pasta = new File(path);
		File list[] = pasta.listFiles();
                for(count=0;count<list.length;count++){
                    filename2 = list[count].getName();
                    if(filename2.endsWith(".itr") || filename2.endsWith(".dfp")){
                        descompactador(path + filename2, path);
                    }
                }
		is.close();
		conn.disconnect();
            
	}
	
}
