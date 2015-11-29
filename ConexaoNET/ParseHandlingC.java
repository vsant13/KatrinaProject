package ConexaoNET;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.*;

public class ParseHandlingC extends HTMLEditorKit.ParserCallback{
	  
	public final static String CATEGORIA = "Categoria",
						VERSAO = "Versao",
						DATAENTREGA = "Data Entrega",
						HORAENTREGA = "Hora Entrega",
						DATAENCERRAMENTO = "Data Encerramento",
						PROTOCOLO = "Prot. de entrega";
	
	private boolean inTable = false;
	
	private boolean takeNext = false;
	
	private boolean nextTable = false;
        
        private boolean isTable = false;
	
	private static ArrayList<HashMap<String,String>> list;
	
	private static HashMap <String, String> dataMap;
	
	private String key = null, value = null;
	
	public ParseHandlingC() {
		
		dataMap = new HashMap <> ();
		list = new ArrayList<> ();
	}
	
        @Override
	public void handleStartTag(HTML.Tag t,MutableAttributeSet a,int pos){
		
		if(t == HTML.Tag.TR){
			if(a.containsAttribute(HTML.Attribute.CLASS, "TableOptions")){
				inTable = true;
			}
		}
	}
	
        @Override
	public void handleText(char[] text, int position) {
		
		String temp = String.copyValueOf(text);
                
                if(inTable){
			if(takeNext == true){
                                value = String.copyValueOf(text);
				dataMap = list.get(list.size() - 1);
				dataMap.put(key, value);
				takeNext = false;
			}
			if(temp.equals("Categoria")){
				key = String.valueOf(text);
				takeNext = true;
				nextTable = true;
                                isTable = true;
			}
                        if(isTable == true){
                            if(temp.equals("Data Encerramento")){
                                    key = String.valueOf(text);
                                    takeNext = true;
                            }
                            if(temp.equals("Data Entrega")){
                                    key = String.valueOf(text);
                                    takeNext = true;
                            }
                            if(temp.startsWith("Vers") && temp.length() == 6 && temp.endsWith("o")){ /* P.O.G TOTAL */
                                    key = VERSAO;
                                    takeNext = true;
                            }
                            if(temp.equals("Prot. de entrega")){
                                    key = String.valueOf(text);
                                    takeNext = true;
                                    isTable = false;
                            }
                            if(nextTable == true){
                                    list.add(new HashMap<String,String>());
                                    nextTable = false;
                            }
                        }
		}
			
	}
	
	public static void refazLista(){
		
		StringTokenizer token;
		String value;
		int i = 0;
		
		while(i < list.size()){
                    
                        token = new StringTokenizer(list.get(i).get(DATAENTREGA));
			value = token.nextToken();
			list.get(i).put(DATAENTREGA, value);
			value = token.nextToken();
			list.get(i).put(HORAENTREGA, value);
			i++;
		}
	}
	
	public static void getAtivo(){
		
		StringTokenizer token;
		String temp;
		int i = 0;
		
		while(i < list.size()){
		
			token = new StringTokenizer(list.get(i).get(CATEGORIA));
			while(token.hasMoreElements() == true){
				temp = token.nextToken();                                
				if(temp.equalsIgnoreCase("Inativo")){
					list.remove(i);
                                        i--;
				}
			}
			i++;
		}
	}
	public static ArrayList<HashMap<String,String>> getValue(){
		
		getAtivo();
		
		return list;
	}
}
