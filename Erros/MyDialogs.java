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
public  class MyDialogs extends JOptionPane {
    
    protected boolean CAIXADIALOGO=false;
    protected int OPTION;
    protected String system_message;
    protected String ifmessage;
    protected String ifTitlemessage;
    protected String ifTitle;
    protected String nome_interface;
    protected String type;
    protected String id;
    protected int Icon; 
    private boolean SYSTEMPRINTMESSAGE =false;
    private boolean OBJECTINTERFACE=false;
    private IFMyDialog Interface;
    
    
    public MyDialogs(){
        this.SYSTEMPRINTMESSAGE =true ;
        this.CAIXADIALOGO=false;
        Default();
    }
    public  MyDialogs(boolean SYSTEMPRINTMESSAGE,boolean CAIXADIALOGO){
        this.SYSTEMPRINTMESSAGE =SYSTEMPRINTMESSAGE ;
        this.CAIXADIALOGO=CAIXADIALOGO;
        Default();  
    }
    public  MyDialogs(boolean SYSTEMPRINTMESSAGE,String nome_interface){
        this.SYSTEMPRINTMESSAGE =SYSTEMPRINTMESSAGE ;
        this.CAIXADIALOGO=true;
        Default();
        setInterface(nome_interface);
    }
    public  MyDialogs(boolean SYSTEMPRINTMESSAGE ,IFMyDialog object_interface){
        this.SYSTEMPRINTMESSAGE =SYSTEMPRINTMESSAGE ;
        this.CAIXADIALOGO=true;
        Default();
        setInterface(object_interface);
    }
    
    
    public void setInterfaceOption(int OPTION){
        this.OPTION=OPTION;
    }
    public void setSystemPrintMessage(String message){
        this.system_message=message;
        SYSTEMPRINTMESSAGE=true;
    }
    public void setInterfaceTitle(String title){
        this.ifTitle=title;
        CAIXADIALOGO=true;        
    }
    public void setInterfaceTitleMessage(String ifTitlemessage){
        this.ifTitlemessage=ifTitlemessage;
        CAIXADIALOGO=true;        
    }
    public void setInterfaceMessage(String ifmessage){
        this.ifmessage="\n\n       "+ifmessage+"\n\n       ";
        CAIXADIALOGO=true;        
    }
    public void setInterface(String nome_interface){
        this.nome_interface=nome_interface;
        OBJECTINTERFACE=false;
        CAIXADIALOGO=true;
    }
    public void setInterface(IFMyDialog object_interface){
        this.nome_interface="isnotdefault";
        OBJECTINTERFACE=true;
        CAIXADIALOGO=true;
        Interface = object_interface;
    }   
    
     private void Default(){
        String default_ifmessage ="";
        String default_ifTitlemessage ="";
        String default_message ="";
        String default_nome_interface="default";
        String default_ifTitle= "";
        int default_option =-1;
        
        this.OPTION=default_option;
        this.ifTitle=default_ifTitle;
        this.ifTitlemessage=default_ifTitlemessage;   
        this.system_message=default_message;
        this.nome_interface=default_nome_interface;
        this.ifmessage=default_ifmessage;
        this.type="";
        this.id="";
        this.Icon=JOptionPane.PLAIN_MESSAGE;  
    }
     
     
      public int showMySimpleDialog(){
        int resposta=-1;
        if(this.SYSTEMPRINTMESSAGE)
        {
            System.out.println("     "+type+" :     "+id+"      "+system_message);
        }
        if(CAIXADIALOGO)
        {
            resposta = ifMyDialog();
            //coloca os valores de resposta   
        }
        return resposta;
    }
      
      
      
     private int ifMyDialog(){
       int retorno;
       String nome_da_interface=this.nome_interface.trim();
       if(nome_da_interface.equals("default"))
       {
           switch(OPTION){
            case DEFAULT_OPTION:
                retorno=0;
                JOptionPane.showMessageDialog(null,type+" :   "+id+"    "+ifmessage,ifTitle,Icon);
                break;
            default:
              retorno = JOptionPane.showConfirmDialog(null,type+" :   "+id+"    "+ifmessage,ifTitle,OPTION,Icon);    
             }
                        return retorno;      
       }
       if(!OBJECTINTERFACE)
       {
            try {  
                String nome_classe_interface="Erros."+nome_da_interface;
                Interface =(IFMyDialog) Class.forName(nome_classe_interface).newInstance();
                Interface.setTitle(this.ifTitle);
                Interface.setTitulo(this.ifTitlemessage);
                Interface.setMessage(type+" :   "+id+"    "+ifmessage);
                Interface.setOption(this.OPTION);
                retorno=Interface.showConfirmDialog();
                //COLOCAR OS METODOS DAS INTERFACES MAIS EKABORADAS AQUI...  
            } catch (InstantiationException ex) {  
                Erro  err = new Erro(true, false);
                err.setIdErroDB(4101);
                err.setSystemPrintMessage("    ERRO "+ex);
                err.Execute();  
                return -1;
            } catch (IllegalAccessException ex) {  
                Erro  err = new Erro(true, false);
                err.setIdErroDB(4102);
                err.setSystemPrintMessage("    ERRO "+ex);
                err.Execute();
                return -1;
            } catch (ClassNotFoundException ex) {  
                Erro  err = new Erro(true, false);
                err.setIdErroDB(4103);
                err.setSystemPrintMessage("    ERRO "+ex);
                err.Execute();
                return -1;
            }  
            return retorno;
       }
       retorno=Interface.showConfirmDialog();
       return retorno;
       
    }
}
