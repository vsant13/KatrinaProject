package ConexaoNET;

import java.util.ArrayList;

import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLEditorKit;

import Tesouro.TesouroDados;

public class ParseHandlingT1 extends HTMLEditorKit.ParserCallback{
	
	private static ArrayList<TesouroDados> listT;
	
	private boolean inTable , isDate;
	
	private int num;
	
	public ParseHandlingT1(){
		
		inTable = false;
		listT = new ArrayList<>();
		num = -1;
		isDate = false;
	}
	
        @Override
	public void handleStartTag(HTML.Tag t,MutableAttributeSet a,int pos){
		
		if(t == HTML.Tag.TD){
			if(a.containsAttribute(HTML.Attribute.CLASS , "listing0")){
					num++;
					inTable = true;
					listT.add(new TesouroDados());
			}
			else if(a.containsAttribute(HTML.Attribute.CLASS , "listing")){
				num++;
				inTable = true;
			}
			else if(a.containsAttribute(HTML.Attribute.CLASS, "listing2")){
					isDate = true;
			}
		}
		else if(isDate == true){
			inTable = true;
			num = -2;
		}
	}
	
        @Override
	public void handleText(char[] text, int position) {
		
		TesouroDados aux = null;
		String temp = String.copyValueOf(text);

		if(inTable){
			if(!listT.isEmpty() && isDate == false)
				aux = listT.get(listT.size()-1);
			if(num == -2){
				for(int i=0;i<listT.size();i++)
					listT.get(i).setData_referencia(temp);
				num++;
				isDate = false;
			}
			else if(num == 0){
				aux.setTitulo(temp);
			}
			else if(num == 1){
				aux.setVencimento(temp);
			}
			else if(num == 2){
				aux.setTaxa_compra(temp);
			}
			else if(num == 3){
				aux.setTaxa_venda(temp);
			}
			else if(num == 4){
				aux.setPreco_dia_compra(temp);
			}
			else if(num == 5){
				aux.setPreco_dia_venda(temp);
				num = -1;
			}
			inTable = false;
		}
	}
	
	public static ArrayList<TesouroDados> getValue(){
		
		return listT;
	}

}
