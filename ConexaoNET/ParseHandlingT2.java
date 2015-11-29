package ConexaoNET;

import java.util.ArrayList;

import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLEditorKit;

import Tesouro.TesouroDados;

public class ParseHandlingT2 extends HTMLEditorKit.ParserCallback{
	
	private static ArrayList<TesouroDados> listT2;
	private boolean inTable;
	private int num;
	private boolean isDate;
	private boolean nextTable, thisTable, finished;
	private String date;
	
	public ParseHandlingT2(){
		
		inTable = false;
		listT2 = new ArrayList<TesouroDados>();
		listT2.add(new TesouroDados());
		num = 0;
		isDate = false;
		nextTable = false;
		thisTable = false;
		finished = false;
		
	}
	
        @Override
	public void handleStartTag(HTML.Tag t,MutableAttributeSet a,int pos){
		
		if(!finished){
			if(t == HTML.Tag.CENTER)
				isDate = true;
			if(t == HTML.Tag.TD)
				inTable = true;
			if(nextTable){
				thisTable = true;
				nextTable = false;
			}
			else if(thisTable && !inTable)
				thisTable = false;
		}
	}
	
        @Override
	public void handleText(char[] text, int position) {
		
		TesouroDados aux = null;
		String temp = String.copyValueOf(text);
		
		if(inTable){
			if(isDate && temp.startsWith("Rentabilidade do Tesouro")){
				temp = temp.trim();
				date = temp.substring(temp.length()-10,temp.length());
				isDate = false;
			}
				
			if(temp.equals("Prefixados") || temp.startsWith("Indexados")){
				nextTable = true;
				thisTable = false;
			}
			if(temp.endsWith("em 12 meses")){
				finished = true;
				inTable = false;
				thisTable = false;
				listT2.remove(listT2.size()-1);
			}
			if(thisTable){
				aux = listT2.get(listT2.size()-1);
				if(num == 0){
					aux.setTitulo2(temp);
					aux.setData_referencia(date);
				}
				if(num == 1)
					aux.setVencimento(temp);
				if(num == 2)
					aux.setUlt_30(temp);
				if(num == 3)
					aux.setMes_ant(temp);
				if(num == 4)
					aux.setNo_ano(temp);
				if(num == 5)
					aux.setMeses_12(temp);
				if(num == 6)
					aux.setCompra_dia_ano(temp);
				if(num == 7){
					num = -1;
					aux.setVenda_dia_ano(temp);
					listT2.add(new TesouroDados(true));
				}
				num++;
					
			}
		}
			
	}
	
	public static ArrayList<TesouroDados> getValue(){
		
		return listT2;
	}


}
