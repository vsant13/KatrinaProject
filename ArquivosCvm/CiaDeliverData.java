package ArquivosCvm;

import ConexaoNET.Soquete;
import Erros.Erro;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URLEncoder;


public class CiaDeliverData extends Soquete{

	private static final String adress = "http://siteempresas.bovespa.com.br/consbov/ExibeTodosDocumentosCVM.asp?";
	public final String ITR = "IDI1",
				        DFP = "IDI2";
                                      	
	public CiaDeliverData(String cnpj , String cvm) throws CiaDeliverException {
		
		super(adress +"CNPJ=" + cnpj + "&CCVM=" + cvm + "&TipoDoc=C&QtLinks=10");
		try{
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setRequestProperty("Cookie", "ASP.NET_SessionId=f5yygc552f5npv45ao3gel55; CVMWebCookie=SessionKey=1c683375-409c-4857-89cf-e8d41276ba39");
			conn.setRequestProperty("Connection", "keep-alive");
		
		}catch(IOException e) {
			
			Erro err = new Erro(true,true);
			err.setIdErroDB(14101);
			err.setSystemPrintMessage("ERRO: " + e.getMessage());
			err.setInterfaceMessage("Erro ao conectar com o site da CVM");
			err.ComErroCypecad(true);
			err.Execute();
			throw new CiaDeliverException("N�o conectou!!");
		}
	}
	
	public void startStream() throws CiaDeliverException{

		try {
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} catch (IOException e) {
			Erro err = new Erro(true,true);
			err.setIdErroDB(14102);
			err.setSystemPrintMessage("ERRO: " + e.getMessage());
			err.setInterfaceTitleMessage("Erro ao iniciar input stream");
			err.ComErroCypecad(true);
			err.Execute();
			throw new CiaDeliverException("N�o conectou!!");
		}
	}
	
	public String getResponse(){
		
		String response = "";
		try {
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			while(in.ready())
				response += in.readLine();
		} catch (IOException e) {
			System.out.println("Erro: " + e.getMessage());
		}
		
		return response;
	}
	
	public  String buildQuery(String tipo){
		
		String query = "";
                
		try {
			
			query = URLEncoder.encode("hdnCategoria","UTF-8") + "=" + URLEncoder.encode(tipo,"UTF-8");
			query += "&" + URLEncoder.encode("hdnPagina","UTF-8") + "=";
			query += "&" + URLEncoder.encode("FechaI","UTF-8") + "=";
			query += "&" + URLEncoder.encode("FechaV","UTF-8") + "=";
			
		} catch (UnsupportedEncodingException e) {
			System.out.print(e.getMessage());
		}
		
		return query;
	}
	public void executePost(String tipo) throws CiaDeliverException{
		
		try {
			String params;
			params = buildQuery(tipo); 
			out = new DataOutputStream(conn.getOutputStream());
			out.writeBytes(params);
			out.close();
			startStream();
		} catch (IOException e) {
			Erro err = new Erro(true,true);
			err.setIdErroDB(14103);
			err.setSystemPrintMessage("ERRO: " + e.getMessage());
			err.setMessage("Erro ao executar o POST em: " + adress);
			err.ComErroCypecad(true);
			err.Execute();
			throw new CiaDeliverException("N�o conectou!!");
		}
	}
	
	public void closeStream(){
		
		try {
			in.close();
			out.close();
			conn.disconnect();
		} catch (IOException e) {

			Erro err = new Erro(true,true);
			err.setIdErroDB(14104);
			err.setSystemPrintMessage("ERRO: " + e.getMessage());
			err.setInterfaceMessage("Erro ao fechar as stream e/ou a conex�o");
			err.ComErroCypecad(true);
			err.Execute();
		}
	}
   
	@SuppressWarnings("serial")
	public class CiaDeliverException extends Exception {
		
		public CiaDeliverException(String message){
			super(message);
		}
	}
}