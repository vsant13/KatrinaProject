/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dados;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author erico
 */
public class DadosDB {

    private static String mainPath = Diretorios.path_datafiles_config_dados_ci()+"databaseCI.xml";
    private static String sistemaOperacional=null;

    private static DocumentBuilderFactory builderfactory;
    private static DocumentBuilder docbuilder;
    private static Document document=null;
    
    private static Element elementSo=null;
    private static Element elementDB=null;
    
    public static void getInstance(){
            if(System.getProperty("os.name").toLowerCase().trim().equals("linux"))
            {
                  sistemaOperacional="linux";
            }
            else
            {
                sistemaOperacional="windows";
            }
    }
  
    private static void Reader(File fileXML) throws ParserConfigurationException, SAXException, IOException{
        if(document==null)
        {
            builderfactory = DocumentBuilderFactory.newInstance();
            docbuilder = builderfactory.newDocumentBuilder();
            document= docbuilder.parse(fileXML);
            document.normalize();
        }
    }
        
    
    private static Element getDataBaseElement() throws ParserConfigurationException, SAXException, IOException{
        getInstance();
         Reader(new File(mainPath));
         if(elementSo==null)
         {
            Element elementCID=getSubtag( document ,"ConfiguracoesIniciaisDataBase");
            NodeList nodelistSO= getNodeList(elementCID, "SistemaOperacional");
             elementSo=null;
             for(int i=0; i<nodelistSO.getLength();i++)
             {
                elementSo=(Element)nodelistSO.item(i);
                if(elementSo.getAttribute("ID").equals(sistemaOperacional))
                {
                    break;
                }
                else
                {
                    elementSo=null;
                }
             }
             if(elementSo==null)
                 return null; //lancar erro
         }
             return ( getSubtag(elementSo, "DataBase"));
    
    }
    
    protected static String getDataBaseName()  {
           Element elementDB;
        try {
            elementDB = getDataBaseElement();
            
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(DadosDB.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
             return getSubtag(elementDB, "Nome").getTextContent();
    } 
             
    public static String getDataBaseTableName(int Table) 
    {
        if(elementDB==null)
        {
            try {
                elementDB = getDataBaseElement();
            } catch (ParserConfigurationException | SAXException | IOException ex) {
                Logger.getLogger(DadosDB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        Element elementT=getSubtag(elementDB, "Tabelas");
        Element elemenTn=getSubtag(elementT, "Tabela"+Table);
         return getSubtag(elemenTn, "Nome").getTextContent();
    } 
    
    public static String getDataBaseColumnName(int Table , int Colum) 
    {
        if(elementDB==null)
        {
            try {
                elementDB = getDataBaseElement();
            } catch (ParserConfigurationException | SAXException | IOException ex) {
                Logger.getLogger(DadosDB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        Element elementT=getSubtag(elementDB, "Tabelas");
        Element elemenTn=getSubtag(elementT, "Tabela"+Table);
        Element elementC=getSubtag(elemenTn,"Colunas");
         return getSubtag(elemenTn, "Coluna"+Colum).getTextContent();        
    }
    public static int getDataBaseTableNumber() 
    {
        if(elementDB==null)
        {
            try {
                elementDB = getDataBaseElement();
            } catch (ParserConfigurationException | SAXException | IOException ex) {
                Logger.getLogger(DadosDB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
         return Integer.parseInt(getSubtag(elementDB, "NumeroTabelas").getTextContent());        
    }
    public static int getDataBaseColumnNumber(int Table) 
    {
        if(elementDB==null)
        {
            try {
                elementDB = getDataBaseElement();
            } catch (ParserConfigurationException | SAXException | IOException ex) {
                Logger.getLogger(DadosDB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        Element elementT=getSubtag(elementDB, "Tabelas");
        Element elemenTn=getSubtag(elementT, "Tabela"+Table);
         return Integer.parseInt( getSubtag(elemenTn, "NumeroColunas").getTextContent());        
    }
    
    
    private static Element getSubtag(Element rootElement,String tag){
       NodeList nodelist =rootElement.getElementsByTagName(tag);
       Node rootnode = nodelist.item(0);
       rootElement =(Element) rootnode;
       return rootElement;
    }
    private static Element getSubtag(Document document , String tag){
       NodeList nodelist = document.getElementsByTagName(tag);
       Node rootnode = nodelist.item(0);
       Element rootElement =(Element) rootnode;
       return rootElement;
    }
    private static NodeList getNodeList(Element rootElement , String tag){
       NodeList nodelist = rootElement.getElementsByTagName(tag);
       return nodelist;
    }
    private static NodeList getNodeList(Document document , String tag){
       NodeList nodelist = document.getElementsByTagName(tag);
       return nodelist;
    }
    
    public class DadosCIException extends Exception{
       private String path=" ";
    
   public DadosCIException(){
                super();
        }
   public DadosCIException(String path){
                super();
                this.path+=path+" ";
        }
        
        @Override
        public String toString(){
                return "Não foi encontrado o diretorio/arquivos"+path+"exigido para continuação do programa\n"+
                       "Sistema corrompido impossivel operar sem os arquivos de sistema.";
        }
        
        @Override
        public String getMessage(){
                return "Não foi encontrado o diretorio/arquivos"+path+"exigido para continuação do programa\n"+
                       "Sistema corrompido impossivel operar sem os arquivos de sistema.";
        }
 }
}
