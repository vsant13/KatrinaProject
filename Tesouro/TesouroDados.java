package Tesouro;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TesouroDados {
		
	private String titulo ,
				   vencimento ,
				   taxa_compra,
				   taxa_venda , 
				   preco_dia_compra,
				   preco_dia_venda,
				   data_referencia;
	
	private String ult_30,
			       mes_ant,
			       no_ano,
			       meses_12,
			       compra_dia_ano,
			       venda_dia_ano;
	
	public TesouroDados(){
		
		data_referencia = new String();
		titulo = new String();
		vencimento = new String();
		taxa_compra = new String();
		taxa_venda = new String();
		preco_dia_compra = new String();
		preco_dia_venda = new String();
	}
	
	public TesouroDados(boolean a){
		
		data_referencia = new String();
		titulo = new String();
		vencimento = new String();
		mes_ant = new String();
		ult_30 = new String();
		meses_12 = new String();
		compra_dia_ano = new String();
		venda_dia_ano = new String();
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		
		titulo = titulo.trim();
		titulo = titulo.substring(0,titulo.length()-7);
		titulo = titulo.replace("-", "");
		
		this.titulo = titulo;
	}
	
	public void setTitulo2(String titulo){
		
		titulo = titulo.trim();
		titulo = titulo.replace("-", "");
		
		this.titulo = titulo;
	}

	public String getVencimento() {
		return vencimento;
	}

	public void setVencimento(String vencimento) {
		Date DataSit;
	    try {
			DataSit = new SimpleDateFormat("dd/MM/yyyy").parse(vencimento.trim());
		    vencimento = new SimpleDateFormat("yyyy-MM-dd").format(DataSit);
	    } catch (ParseException e) {
			System.out.println(e.getMessage());
		}
		this.vencimento = vencimento;
	}

	public String getTaxa_compra() {
		return taxa_compra;
	}

	public void setTaxa_compra(String taxa_compra) {
		
		taxa_compra = verificaValor(taxa_compra);
		this.taxa_compra = taxa_compra;
	}

	public String getTaxa_venda() {
		return taxa_venda;
	}

	public void setTaxa_venda(String taxa_venda) {
	
		taxa_venda = verificaValor(taxa_venda);
		this.taxa_venda = taxa_venda;
	}

	public String getPreco_dia_compra() {
		return preco_dia_compra;
	}

	public void setPreco_dia_compra(String preco_dia_compra) {
		
		preco_dia_compra = verificaValor(preco_dia_compra);
		this.preco_dia_compra = preco_dia_compra;
	}

	public String getPreco_dia_venda() {
		return preco_dia_venda;
	}

	public void setPreco_dia_venda(String preco_dia_venda){
		
		preco_dia_venda = verificaValor(preco_dia_venda);
		this.preco_dia_venda = preco_dia_venda;
	}

	public String getUlt_30() {
		return ult_30;
	}

	public void setUlt_30(String ult_30) {
		
		ult_30 = verificaValor(ult_30);
		this.ult_30 = ult_30;
	}

	public String getMes_ant() {
		return mes_ant;
	}

	public void setMes_ant(String mes_ant) {
		
		mes_ant = verificaValor(mes_ant);
		this.mes_ant = mes_ant;
	}

	public String getNo_ano() {
		return no_ano;
	}

	public void setNo_ano(String no_ano) {
		
		no_ano = verificaValor(no_ano);
		this.no_ano = no_ano;
	}

	public String getMeses_12() {
		return meses_12;
	}

	public void setMeses_12(String meses_12) {
		
		meses_12 = verificaValor(meses_12);
		this.meses_12 = meses_12;
	}

	public String getCompra_dia_ano() {
		return compra_dia_ano;
	}

	public void setCompra_dia_ano(String compra_dia_ano) {
		
		compra_dia_ano = verificaValor(compra_dia_ano);
		this.compra_dia_ano = compra_dia_ano;
	}

	public String getVenda_dia_ano() {
		return venda_dia_ano;
	}

	public void setVenda_dia_ano(String venda_dia_ano) {
		
		venda_dia_ano = verificaValor(venda_dia_ano);
		this.venda_dia_ano = venda_dia_ano;
	}

	public String getData_referencia() {
		return data_referencia;
	}

	public void setData_referencia(String data_referencia) {
		Date DataSit;
		data_referencia = data_referencia.trim();
	    try {
	    	if(data_referencia.contains("/"))
	    		DataSit = new SimpleDateFormat("dd/MM/yyyy").parse(data_referencia.substring(0,10).trim());
	    	else
	    		DataSit = new SimpleDateFormat("dd-MM-yyyy").parse(data_referencia.substring(0,10).trim());
	    	data_referencia = new SimpleDateFormat("yyyy-MM-dd").format(DataSit);
	    } catch (ParseException e) {
			System.out.println(e.getMessage());
		}
	
		this.data_referencia = data_referencia;
	}
	
	public String verificaValor(String a){
		
		a = a.trim();
		if(a.equals("-"))
			return null;
		else{
			a = a.replace("R$","");
			a = a.replace("%", "");
			a = a.trim();
			a = a.replace(".","");
			a = a.replace(",", ".");
			return a;
		}
	}
	
}
