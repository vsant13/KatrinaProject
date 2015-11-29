package ArquivosCvm;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.StringTokenizer;

import javax.net.ssl.HttpsURLConnection;

import ConexaoNET.Soquete;
import Dados.Diretorios;
import Erros.BadRequestException;
import Erros.Erro;
import Erros.InvalidPasswordException;
import InterfaceGraf.IFCvmSenhaGUI;



public class FileCvmNet extends Soquete{

	private static final String adress = "https://WWW.RAD.CVM.GOV.BR/DOWNLOAD/SolicitaDownload.asp";
	private String  login,password;
        private final String filename = "CvmSenha.txt";
        private IFCvmSenhaGUI gui;
        boolean passOk = false;
                        
	
	public FileCvmNet() {
		super(adress);
                File arq = new File(Diretorios.path_datafiles_config(),filename);
		
                        if(!arq.exists()){
                            gui = new IFCvmSenhaGUI(null, true);
                            gui.senttoObject(this);
                            gui.setVisible(true);
                            
                        }
                        else{
                            getLogin(null, null, true);
                        }	
	}
        
        public void startStrem(){
            
            try {
                conn = (HttpsURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
                conn.setDoInput(true);
            } catch (IOException e) {
                        Erro err = new Erro(true,true);
			err.setIdErroDB(15101);
			err.setSystemPrintMessage("ERRO: " + e.getMessage());
			err.setInterfaceMessage("Erro ao conecta com: " + adress);
			err.ComErroCypecad(true);
			err.Execute();
            }
        }
	public void executePost(String params2){
		
		try {
			out = new DataOutputStream(conn.getOutputStream());
			out.writeBytes(params2);
			out.close();
		} catch (IOException e) {

			Erro err = new Erro(true,true);
			err.setIdErroDB(15102);
			err.ComErroCypecad(true);
			err.Execute();
		}
	}
	public String getResponse() throws IOException{
		
		String response = "";
		
		in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		while(in.ready())
			response += in.readLine() + "\n";
		in.close();
		try {
		
			verifica(response);
                        passOk = true;
		
		} catch (BadRequestException e) {
			
			Erro err = new Erro(true,true);
			err.setIdErroDB(15103);
			err.setSystemPrintMessage("ERRO: " + e.getMessage());
			err.setInterfaceMessage("Erro ao realizar o pegar a resposta de: " + adress);
			err.ComErroCypecad(true);
			err.Execute();
		
		} catch (InvalidPasswordException e){

			Erro err = new Erro(true,true);
			err.setIdErroDB(15104);
			File arq = new File(Diretorios.path_datafiles_cvm_data(),filename);
			arq.delete();
                        gui = new IFCvmSenhaGUI(null,true);
                        gui.senttoObject(this);
                        gui.setVisible(true);
                        passOk = false;
		
		}
		return response;
	}
	
	public String buildQuery(String date,String time,int t){
		
		String query = "";
		String tipo;
		
		if(t == 0)
			tipo = "DFP";
		else
			tipo = "ITR";
		try {
			query = URLEncoder.encode("txtLogin","UTF-8") + "=" + URLEncoder.encode(login,"UTF-8");
			query += "&" + URLEncoder.encode("txtData","UTF-8") + "=" + URLEncoder.encode(date,"UTF-8");
			query += "&" + URLEncoder.encode("txtDocumento","UTF-8") + "=" + URLEncoder.encode(tipo,"UTF-8");
			query += "&" + URLEncoder.encode("txtSenha","UTF-8") + "=" + URLEncoder.encode(password,"UTF-8");
			query += "&" + URLEncoder.encode("txtHora","UTF-8") + "=" + URLEncoder.encode(time,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			System.out.println("ERRO: " + e.getMessage());
		}
		return query;
	}
	
	public String getURL(String xml , String cvmCode) throws BadRequestException{
			
		String url = "";
		String cvm = "";
		String aux = "";
		StringTokenizer token = new StringTokenizer(xml);
		StringTokenizer token2 = new StringTokenizer(xml);
		int i;
		
		for(i=cvmCode.length();i<6;i++)
			cvmCode = "0" + cvmCode;
		
		while(token2.hasMoreTokens()){
				
			aux = token2.nextToken();
				
			if(aux.startsWith("ccvm")){
				cvm = aux.substring(6,(aux.length()-1));
				if(cvm.equals(cvmCode)){	
					while(token.hasMoreTokens()){
						aux = token.nextToken();
						if(aux.startsWith("url")){
							url = (aux.substring(5,aux.length()-1));
							/*POG MASTER*/
							String temp = url.substring(0,85);
							temp += url.substring(89,url.length());
							url = temp;
							url = url.replace("http", "https");
							break;
						}
					}
				}
			}
		}
		
		verificaURL(url);
		
		return url;
	}
	
	/*public void saveURL(String url){
		
		File arq = new File(Diretorios.path_datafiles_cvm_data(), "url.txt");
		
		try {
			arq.createNewFile();
			PrintWriter out = new PrintWriter(arq);
			out.println(url);
			out.flush();
			
			out.close();
		} catch (IOException e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}
	*/
	public String[] parameterBuilder(int vet[]){
		
		String vet2[] = new String[vet.length];
		int i;
		
		for(i=0;i<vet.length;i++){
			if(vet[i]<10)
				vet2[i] = '0' + String.valueOf(vet[i]);
			else
				vet2[i] = String.valueOf(vet[i]);
		}
		return vet2;
	}

	public void closeStream() throws IOException{
		
		if(out != null) out.close();
		if(in != null)  in.close();
		conn.disconnect();
		
	}
	
	public void verificaURL(String text) throws BadRequestException{
		if(text == null || text.length() <= 0)
			throw new BadRequestException("Nenhum arquivo encontrado a partir da URL!!");
	}
	
	public void verifica(String text) throws InvalidPasswordException, BadRequestException{
		
		StringTokenizer token = new StringTokenizer(text);
		String aux = "", aux2 = "";
		
		while(token.hasMoreTokens()){
			aux2 = token.nextToken(">");
			if(aux2.equals("<NUMERO_DO_ERRO")){
				aux = token.nextToken("<");
				if (aux.equals(">1")) 
					throw new InvalidPasswordException("Senha expirou!");
				if(aux.startsWith(">2"))
					throw new BadRequestException("Nenhum arquivo encontrado!!");
			}
		}
	}
	
	public void getLogin(String login , String pass , boolean opt){
		
		File arq = new File(Diretorios.path_datafiles_config(),filename);
	
		try {
			if(!opt){
				arq.createNewFile();
				FileWriter w = new FileWriter(arq);
				this.login = login;
				this.password = pass;
				w.write(login + "\r\n" + password);
				w.close();
			}
			else{
				BufferedReader out = new BufferedReader(new FileReader(arq));
				this.login = out.readLine();
                                
				this.password = out.readLine();
				out.close();
			}
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			Erro err = new Erro(true,true);
			err.setIdErroDB(15105);
			err.setSystemPrintMessage("ERRO: " + e.getMessage());
			err.setInterfaceMessage("Erro ao manipula o arquivo CvmSenha.txt");
			err.ComErroCypecad(true);
			err.Execute();
		}
        
	}
}
