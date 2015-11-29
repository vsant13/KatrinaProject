/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AtualizacaoEmissor;

import ConexaoNET.Soquete;
import Dados.Diretorios;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.Calendar;

/**
 *
 * @author Erico
 */
public class DownArqsdeAtualizacao {
    
    protected boolean  DOWNCOMPLETE; 
    
    private static  String adress ;
    public static  String path=Diretorios.path_datafiles_config_emissor();
    private String filename ;
    private FileOutputStream file ;
    private InputStream is;
    private Soquete sqt;
    
    protected DownArqsdeAtualizacao() throws IOException{   
        try {
            downFilesSPW();
            downFilesEmissor();
            renameFiles();
            deleteFiles();
        } catch (IOException ex) {
             DOWNCOMPLETE=false;
             throw ex;
        }
   DOWNCOMPLETE=true;
}
    
    
    private void downFilesSPW() throws IOException{
        filename = Emissor.default_file_spw+".zip";
        adress = "http://www.cvm.gov.br/cadastro/SPW_CIA_ABERTA.ZIP";
        sqt = new Soquete(adress);
        sqt.conn = (HttpURLConnection) sqt.url.openConnection();
        sqt.conn.setDoOutput(true);
        sqt.conn.setRequestMethod("GET");
        is = sqt.conn.getInputStream();
        file = new FileOutputStream(path + filename);
        downFiles();
  }
    
    public void downFilesEmissor() throws IOException {
        filename = Emissor.default_file_emissor+".zip";
        adress = "http://www.bmfbovespa.com.br/isin/DownloadArquivo.asp?TipoArquivo=M";
        sqt = new Soquete(adress);
        sqt.conn = (HttpURLConnection) sqt.url.openConnection();
        sqt.conn.setDoOutput(true);
        sqt.conn.setRequestMethod("GET");
        is = sqt.conn.getInputStream();
        file = new FileOutputStream(path + filename);
        downFiles();
    }

    private void renameFiles() throws IOException{

    Calendar calendario =Calendar.getInstance();
    int year=calendario.get(Calendar.YEAR);
    File arq1 = new File(Diretorios.path_datafiles_config_emissor()+"SPW_CIA_ABERTA.TXT");
    arq1.renameTo(new File(Diretorios.path_datafiles_config_emissor()+Emissor.default_file_spw+(year)+".txt"));
    File arq2 = new File(Diretorios.path_datafiles_config_emissor()+"EMISSOR.TXT");
    arq2.renameTo(new File(Diretorios.path_datafiles_config_emissor()+Emissor.default_file_emissor+(year)+".txt"));   
    }

    public void downFiles() throws IOException{

            byte[] b = new byte[1024];
            int count;

            while((count = is.read(b)) >= 0){
                    file.write(b, 0 , count);
            }
            is.close();
            file.close();
            sqt.descompactador(path + filename,path);
            sqt.deleteZip(path + filename);
    }

	  public void deleteFiles(){
		
		File pasta = new File(path);
		File list[] = pasta.listFiles();
		int i = 0;
		String temp;
		
		while(i < list.length){
			temp = list[i].getName();
			if(!temp.contains(Emissor.default_file_spw) && !temp.contains(Emissor.default_file_emissor)){
				System.out.println("delete file - "+temp);
				list[i].delete();
			}
			i++;
		}
	}
	
}

