package CotacaoBmf;



/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Erico
 */
public class DadosBmf {
    protected String nome_do_arquivo,
                  data_de_geracao;
                    
            
    protected  String[] lines;
    protected  StringBuilder[] 
          //  id_papel,
            data,
            codbdi,
            codneg_name,
            codneg_tipo,
            tpmerc,
            nomres,
            especi,
            prazot,
            modref,
            preabe,
            premax,
            premin,
            premed,
            preult,
            preofc,
            preofv,
            totneg,
            quatot,
            voltot,
            preexe,
            indopc,
            datven,
            fatcon,
            ptoexe,
            codisi_pais,
            codisi_name,
            codisi_tipo,
            codisi_indem,
            codisi_digcon,
            dismes;
            
    
   protected DadosBmf(String[] lines){
       this.lines=lines;
       aloca(lines.length);
       for(int i=0;i<lines.length;i++)
             {
                        if(lines[i].startsWith("00")){ //cabe�alho -conteudo do arquivo
			nome_do_arquivo="'"+lines[i].substring(2, 15)+"'";
                        data_de_geracao="'"+lines[i].substring(23, 31)+"'";	
			}
			if(lines[i].startsWith("01")){//conteudo de empresas
			 setData(i);
			}
			//if(lines[i].startsWith("99")){ignora , mesmo que cabeçalho} //ultima linha do arquivo -conteudo do arquivo
				
			
                        
                        
            }
       
       
   }
    private void aloca(int tamanho){
           // id_papel = new String[tamanho];
            data= new StringBuilder[tamanho];
            codneg_tipo= new StringBuilder[tamanho];
            prazot= new StringBuilder[tamanho];
            modref= new StringBuilder[tamanho];
            preabe= new StringBuilder[tamanho];
            premax= new StringBuilder[tamanho];
            premin= new StringBuilder[tamanho];
            premed= new StringBuilder[tamanho];
            preult= new StringBuilder[tamanho];
            preofc= new StringBuilder[tamanho];
            totneg= new StringBuilder[tamanho];
            quatot= new StringBuilder[tamanho];
            voltot= new StringBuilder[tamanho];
            preexe= new StringBuilder[tamanho];
            datven= new StringBuilder[tamanho];
            fatcon= new StringBuilder[tamanho];
            ptoexe= new StringBuilder[tamanho];
            codisi_pais= new StringBuilder[tamanho];
            codisi_tipo= new StringBuilder[tamanho];
            codisi_indem= new StringBuilder[tamanho];
            codisi_digcon= new StringBuilder[tamanho];
            dismes= new StringBuilder[tamanho];
            codbdi= new StringBuilder[tamanho];
            tpmerc= new StringBuilder[tamanho];
            especi= new StringBuilder[tamanho];
            indopc= new StringBuilder[tamanho];
            nomres= new StringBuilder[tamanho];
            preofv= new StringBuilder[tamanho];
            codisi_name= new StringBuilder[tamanho];
            codneg_name= new StringBuilder[tamanho];
  
    }
    
    private void setData(int i){
        //id_papel[i] ;
            data[i]=new StringBuilder(lines[i].substring(2, 10).trim()) ;
            codbdi[i]=colocaAspas (lines[i].substring(10, 12).trim());
            codneg_name[i]=colocaAspas(lines[i].substring(12, 16).trim());
            codneg_tipo[i]=colocaAspas(lines[i].substring(16, 24));
            tpmerc[i]=new StringBuilder(lines[i].substring(24, 27).trim());
            nomres[i]=colocaAspas(lines[i].substring(27, 39).trim());
            especi[i]=colocaAspas(lines[i].substring(39, 49).trim());
            prazot[i]=colocaAspas(lines[i].substring(49, 52).trim());
            modref[i]=colocaAspas(lines[i].substring(52,56).trim());
            preabe[i]=new StringBuilder(lines[i].substring(56, 69).trim());     preabe[i].append("/100");
            premax[i]=new StringBuilder( lines[i].substring(69, 82).trim());    premax[i].append("/100");
            premin[i]=new StringBuilder( lines[i].substring(82, 95).trim());    premin[i].append("/100");
            premed[i]=new StringBuilder(lines[i].substring(95, 108).trim());    premed[i].append("/100");
            preult[i]=new StringBuilder( lines[i].substring(108, 121).trim());  preult[i].append("/100");
            preofc[i]=new StringBuilder( lines[i].substring(121, 134).trim());  preofc[i].append("/100");
            preofv[i]=new StringBuilder( lines[i].substring(134, 147).trim());  preofv[i].append("/100");
            totneg[i]=new StringBuilder( lines[i].substring(147, 152).trim());
            quatot[i]=new StringBuilder( lines[i].substring(152, 170).trim());
            voltot[i]=new StringBuilder( lines[i].substring(170, 188).trim());   voltot[i].append("/100");
            preexe[i]=new StringBuilder( lines[i].substring(188,201).trim());   preexe[i].append("/100");
            indopc[i]=new StringBuilder ( lines[i].substring(201, 202).trim());
            datven[i]=new StringBuilder( lines[i].substring(202, 210).trim());
            fatcon[i]=new StringBuilder( lines[i].substring(210, 217).trim());
            ptoexe[i]=new StringBuilder( lines[i].substring(217, 230).trim()); ptoexe[i].append("/1000000");
            codisi_pais[i]=colocaAspas( lines[i].substring(230, 232).trim());
            codisi_name[i]=colocaAspas( lines[i].substring(232, 236).trim());
            codisi_tipo[i]=colocaAspas( lines[i].substring(236, 239).trim());
            codisi_indem[i]=colocaAspas( lines[i].substring(239, 241).trim());
            codisi_digcon[i]=new StringBuilder ( lines[i].substring(241, 242).trim());
            dismes[i]=new StringBuilder( lines[i].substring(242, 245).trim());
            
            
            
    }
    private StringBuilder colocaAspas(String str){
      
        StringBuilder rs ;
      if(!str.isEmpty()){ rs=new StringBuilder("'");
      rs.append(str);
      rs.append("'");
      }
      else rs=null;
      return (rs);
    
    }
    
    
                
 

    
    
    
    
}
