package ConexaoNET;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.text.html.*;
import javax.swing.text.html.parser.ParserDelegator;

import Erros.Erro;
import Tesouro.TesouroDados;

@SuppressWarnings("serial")
public class HTMLParser extends HTMLEditorKit {
	  
	public ArrayList<HashMap<String,String>> list;
	
	public ArrayList<TesouroDados> listT , listT2;
	
	HTMLEditorKit.ParserCallback callback , callback2;

	
	private boolean opt = true , table2 = false;
	
	public HTMLParser(){
		callback = new ParseHandlingC();
	}
	
	public HTMLParser(boolean o){
		
		callback = new ParseHandlingT1();
		opt = false;
	}
	
	public void readHTML(BufferedReader sr){
		
		HTMLEditorKit.Parser parse = new ParserDelegator();
		try {
			if(!table2){
				parse.parse(sr,callback, true);
				if(opt){
					ParseHandlingC.refazLista();
					list = ParseHandlingC.getValue();
				}
				else{
					listT = ParseHandlingT1.getValue();
					table2 = true;
				}
			}
			else{
				callback2 = new ParseHandlingT2();
				parse.parse(sr,callback2, true);
				listT2 = ParseHandlingT2.getValue();
			}
		} catch (IOException e) {

			Erro err = new Erro(true,true);
			err.setIdErroDB(16101);
			err.setSystemPrintMessage("ERRO: " + e.getMessage());
			err.setInterfaceMessage("Erro ao realizar o Parser do HTML");
			err.ComErroCypecad(true);
			err.Execute();
		}
	}
        
}
