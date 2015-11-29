package CotacaoBmf;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import ConexaoNET.Soquete;
import InterfaceGraf.IFLoadBar1;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;



public class FileBmfNet extends FileBmf {
	
	private static final String adress = "http://www.bmfbovespa.com.br/InstDados/SerHist/";
	public FileBmfNet(String filename) {super(filename);}
	public FileBmfNet(int ano){super (ano);}
	public FileBmfNet(int dia ,int mes , int ano){super(dia, mes ,ano);}
        
        private final static IFLoadBar1 ifbaixar= new IFLoadBar1(); 
	private boolean comMessagemdeErro=false;
        private boolean retorno;
        
    public void setComMessagemdeErro(boolean comMessagemdeErro)
    {
        this.comMessagemdeErro=comMessagemdeErro;
    }
    public boolean fezDownload(){
        return retorno;
    }
    
     public boolean downFiles(boolean comMessagemdeErro )  {
         setComMessagemdeErro(comMessagemdeErro);
         downFiles();
         return retorno;
     }
     
    public synchronized void  downFiles()  {
        if(isAno)
        {
                ifbaixar.setTitle("DOWNLOAD COTAÇÃO BOVESPA");
                ifbaixar.setTitleMessage("FAZENDO DOWNLOAD DO ARQUIVO COTAÇÃO BOVESPA");
                ifbaixar.setBarIndeterminate(true);
                ifbaixar.setBarStringPainted(false);
                ifbaixar.if_aux(0,"Download file :        "+super.filename+" \n\nTempo de espera  :Indeterminado" );
                ifbaixar.setVisible(true);
            
        }
                boolean deucerto=false;
    	Soquete sqt = new Soquete(adress + super.filenamezip);
        InputStream is;
    	FileOutputStream file;
       
                try{
                    sqt.conn = (HttpURLConnection) sqt.url.openConnection();
                    is = sqt.url.openStream();
                    file = new FileOutputStream(super.pathfilenamezip);
                    byte[] b = new byte[4096];
                    int count;
                    while((count = is.read(b)) >= 0)
                        file.write(b, 0 , count);
                    sqt.descompactador(super.pathfilenamezip, super.pathfile);
                    ifbaixar.close();
                    is.close();
                    file.close();
                    sqt.deleteZip(super.pathfilenamezip);
                    deucerto=true;
                }catch(IOException ex)
                {   
                    if(comMessagemdeErro)
                        JOptionPane.showConfirmDialog(null,"Não foi possivel baixar o arquivo :"+super.filename);
                    deucerto=false;
                }finally
                {
                    ifbaixar.close();
                }
             retorno= deucerto;
    }
 
}
