/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Erros;

import javax.swing.JOptionPane;

/**
 *
 * @author Erico
 */
public final class Erro extends MyDialogs{
   
    private boolean COMERROCYPECAD;
    private static boolean INTERVALODEESPERA;
    private boolean EXIT =false;
    private String id_erro;
    private static long tempoinicial=0;
    
    public Erro(){
        super();
        this.Default();
    }
    public Erro(boolean PRINTERRO,boolean CAIXADIALOGO){
        super(PRINTERRO,CAIXADIALOGO);
        this.Default();  
    }
    public Erro(boolean PRINTERRO,String nome_interface){
        super(PRINTERRO,nome_interface);
        this.Default();
        setInterface(nome_interface);
    }
    public Erro(boolean PRINTERRO,IFMyDialog object_interface){
        super(PRINTERRO,object_interface);
        this.Default();
        setInterface(object_interface);
    }
    
    public void setIdErroDB(int id_erro){
        this.id_erro=""+id_erro;
        super.id=""+id_erro;
    }

    public void setExitAplication(boolean estado){
        this.COMERROCYPECAD=true;
        super.CAIXADIALOGO=true;
        this.ifmessage+="\n Não é possivel a continuação da aplicação .\n\nA aplicação será fechada devido a um erro sem solução";
        this.EXIT=estado;
    }
    
    public void ComErroCypecad(boolean estado){
        this.COMERROCYPECAD=estado;
        INTERVALODEESPERA = false;
        tempoinicial=System.currentTimeMillis();
    }
   
    public int Execute(){
        if (!COMERROCYPECAD) EsperaIntervalodeEspera();
        if(INTERVALODEESPERA==true) super.CAIXADIALOGO=false; 
        int resposta=super.showMySimpleDialog();
        if(EXIT)
            {
                System.out.println("\nFIM DE APLICAÇÃO DEVIDO A UM ERRO GRAVE ");
                System.exit(1);
            } 
        return resposta;
    }
    
    private void EsperaIntervalodeEspera(){
        long tempo=System.currentTimeMillis();
        tempo=tempo-tempoinicial;
        if(tempo<4000) INTERVALODEESPERA=true;
        else INTERVALODEESPERA=false;
        tempoinicial=System.currentTimeMillis();
    }
    
    private void Default(){
        String default_ifmessage_erro ="Erro : Ocorreu um problema durante a execução do programa.";
        String default_ifTitlemessage_erro ="  ERRO";
        String default_message_erro ="Erro : Ocorreu um problema durante a execução do programa.";
        String default_nome_interface="default";
        String default_ifTitle= "Error";
        String default_id_erro="  Sem numeração";
        int default_option =-1;
        
        super.OPTION=default_option;
        super.ifTitle=default_ifTitle;
        super.ifTitlemessage=default_ifTitlemessage_erro;  
        super.system_message=default_message_erro;
        super.nome_interface=default_nome_interface;
        super.ifmessage=default_ifmessage_erro; 
        super.id=default_id_erro;
        super.type="ERRO";
        super.Icon=JOptionPane.ERROR_MESSAGE;
        this.id_erro=default_id_erro;
        
    }
       
    
}