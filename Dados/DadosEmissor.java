/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dados;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author erico
 */
public class DadosEmissor {

    private static final String mainPath = Diretorios.path_datafiles_config_dados_ci()+"emissorCI.xml";

    private static DocumentBuilderFactory builderfactory;
    private static DocumentBuilder docbuilder;
    private static Document document=null;
    
    
    private static void Reader(File fileXML) throws ParserConfigurationException, SAXException, IOException{
        if(document==null)
        {
            builderfactory = DocumentBuilderFactory.newInstance();
            docbuilder = builderfactory.newDocumentBuilder();
            document= docbuilder.parse(fileXML);
            document.normalize();
        }
    }
    
    public static void setNomeArquivo(String nome){
       
        try {
            Reader(new File(mainPath));
            
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(DadosEmissor.class.getName()).log(Level.SEVERE, null, ex);
        }
            Element elementAFC=getSubtag( document ,"Atualizacaofilecreate_database");
             getSubtag(elementAFC, "NomeArquivo").setTextContent(nome);
             salvar();
    }
     public static void setDataAtualizado(int dia,int mes, int ano){
       
        try {
            Reader(new File(mainPath));
            
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(DadosEmissor.class.getName()).log(Level.SEVERE, null, ex);
        }
            Element elementAFC=getSubtag( document ,"Atualizacaofilecreate_database");
            Element elementD=getSubtag( elementAFC ,"data");
            getSubtag(elementD, "dia").setTextContent(dia+"");
            getSubtag(elementD, "mes").setTextContent(mes+"");
            getSubtag(elementD, "ano").setTextContent(ano+"");
            salvar();
    }
     
    
   public static String getNomeArquivo(){
       
        try {
            Reader(new File(mainPath));
            
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(DadosEmissor.class.getName()).log(Level.SEVERE, null, ex);
        }
            Element elementAFC=getSubtag( document ,"Atualizacaofilecreate_database");
            return getSubtag(elementAFC, "NomeArquivo").getTextContent();
    
    }
   public static int getDiaAtualizado(){
       
        try {
            Reader(new File(mainPath));
            
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(DadosEmissor.class.getName()).log(Level.SEVERE, null, ex);
        }
            Element elementAFC=getSubtag( document ,"Atualizacaofilecreate_database");
            Element elementD=getSubtag( elementAFC ,"data");
            return  Integer.parseInt(getSubtag(elementD, "dia").getTextContent());
    
    }
     public static int getMesAtualizado(){
       
        try {
            Reader(new File(mainPath));
            
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(DadosEmissor.class.getName()).log(Level.SEVERE, null, ex);
        }
            Element elementAFC=getSubtag( document ,"Atualizacaofilecreate_database");
            Element elementD=getSubtag( elementAFC ,"data");
            return  Integer.parseInt(getSubtag(elementD, "mes").getTextContent());
    
    }
     
     public static int getAnoAtualizado(){
       
        try {
            Reader(new File(mainPath));
            
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(DadosEmissor.class.getName()).log(Level.SEVERE, null, ex);
        }
            Element elementAFC=getSubtag( document ,"Atualizacaofilecreate_database");
            Element elementD=getSubtag( elementAFC ,"data");
            return Integer.parseInt(getSubtag(elementD, "ano").getTextContent());
    
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
 
    private static void salvar(){
         try {  
  
                    Source xmlSource = new DOMSource(document);  
  
                    Result result = new StreamResult(new FileOutputStream(new File(mainPath)));  
  
                    TransformerFactory transformerFactory = TransformerFactory  .newInstance();  
  
                    Transformer transformer = transformerFactory.newTransformer();  
  
                    transformer.setOutputProperty("indent", "yes");  
  
                    transformer.transform(xmlSource, result);  
                }  
  
                catch (Exception e) {  
                    e.printStackTrace();  
                }  
    
    
    }
    
    
}