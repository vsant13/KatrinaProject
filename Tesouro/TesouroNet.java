package Tesouro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import ConexaoNET.Soquete;
import Erros.Erro;

public class TesouroNet extends Soquete {

	private final static String adress = "http://www3.tesouro.gov.br/tesouro_direto/consulta_titulos_novosite/consultatitulos.asp";
	
	private final static String adress2 = "http://www3.tesouro.gov.br/tesouro_direto/rentabilidade_novosite.asp";
	
	public TesouroNet() {
		super(adress);
		
		try {
			conn =  (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setDoOutput(true);
		}catch( IOException e){
			Erro err = new Erro(true,true);
			err.setIdErroDB(19101);
			err.setSystemPrintMessage("ERRO: " + e.getMessage());
			err.setInterfaceMessage("Erro ao realizar ao conectar em :" + adress);
			err.ComErroCypecad(true);
			err.Execute();
		}

	}
	
	public TesouroNet(boolean rent) {
		super(adress2);
		
		try {
			conn =  (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setDoOutput(true);
		}catch( IOException e){
			Erro err = new Erro(true,true);
			//err.setIdErroDB(0);
			err.setSystemPrintMessage("ERRO: " + e.getMessage());
			err.setInterfaceMessage("Erro ao realizar ao conectar em :" + adress2);
			err.ComErroCypecad(true);
			err.Execute();
		}

	}
	

	public void startStream() throws TesouroNetException{
		
		try {
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} catch (IOException e) {
			Erro err = new Erro(true,true);
			err.setIdErroDB(19103);
			err.setSystemPrintMessage("ERRO: " + e.getMessage());
			err.setInterfaceMessage("Erro ao iniciar a stream de input");
			err.ComErroCypecad(true);
			err.Execute();
			throw new TesouroNetException("Nao conectou!!",19103);
		}	
	}
	
	public void closeStream() throws TesouroNetException{
		
		try {
			in.close();
			conn.disconnect();
		} catch (IOException e) {
			Erro err = new Erro(true,true);
			err.setIdErroDB(19104);
			err.setSystemPrintMessage("ERRO: " + e.getMessage());
			err.setInterfaceMessage("Erro ao fechar a conexao e /ou input stream!");
			err.ComErroCypecad(true);
			err.Execute();
			throw new TesouroNetException("Nao desconectou!!",19104);
		}
	}


@SuppressWarnings("serial")
public class TesouroNetException extends Exception{
	
	int id;
	public TesouroNetException (String message,int id){
		super(message);
		this.id = id;
	}
	
	public int getId(){
		return this.id;
	}
}

}
