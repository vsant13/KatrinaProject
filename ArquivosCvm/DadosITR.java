package ArquivosCvm;




import Erros.Erro;
import java.io.File;
import java.io.IOException;


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
 * @author Erico
 */
public class DadosITR {
    
   public boolean permissao_conta=true; 
   public boolean permissao_composicao=true;
   public boolean permissao_provento =true;
    
 //Para tabela de contas:   
   public  String[] NUMEROCONTA,
                    NUMEROORDEMCONTA,
                    DESCRICAOCONTA,
                    VALORCONTA1,
                    VALORCONTA2,
                    VALORCONTA3,
                    VALORCONTA4,
                    VALORCONTA5,
                    VALORCONTA6,
                    VALORCONTA7,
                    VALORCONTA8,
                    VALORCONTA9,
                    VALORCONTA10,
                    VALORCONTA11,
                    VALORCONTA12,
                     I_DATAINICIOPERIODO,
                     I_DATAFIMPERIODO;
   
   private String[] Numeroidenfperiodo,
                    NIP;
  //Para tabela composição de capital:     
   public String NICCS,
                 QAOCI,
                 QAFCI,                     
                 QTACI,
                 QAOT,
                 QAPT,  
                 QTAT;  
   //Para tabela de proventos:
   public String[] TipoProvento,
           DataAprovacaoProvento,
           DataInicioPagamento,
           DescricaoCodigoEspecie,
           SiglaCodigoEspecie,
           DescricaoCodigoClassePreferencial,
           SiglaCodigoClassePreferencial,
           ValorProventoPorAcao;
  
   public int tamanho_vetor_conta=0; 
   public int tamanho_vetor_provento=0; 
   
   public DadosITR(String pathfiles) throws DadosIIRException{
       String barraWinOuLin;
        if(System.getProperty("os.name").toLowerCase().trim().equals("linux"))
             barraWinOuLin="/";
         else
             barraWinOuLin="\\";
       try {
           File fileRIFD = new File(pathfiles+barraWinOuLin+"InfoFinaDFin.xml") ;
           File fileRPDF = new File(pathfiles+barraWinOuLin+"PeriodoDemonstracaoFinanceira.xml") ;
           File fileRPPDDFN = new File(pathfiles+barraWinOuLin+"PagamentoProventoDinheiroDemonstracaoFinanceiraNegocios.xml") ;
           File fileRCCSDFN = new File(pathfiles+barraWinOuLin+"ComposicaoCapitalSocialDemonstracaoFinanceiraNegocios.xml") ;
            ReaderInfoFinaDemonstracaoFin(fileRIFD);
            ReaderPeriodoDemonstracaoFinanceira(fileRPDF);
            ReaderPagamentoProventoDinheiroDemonstracaoFinanceiraNegocios(fileRPPDDFN);
            ReaderComposicaoCapitalSocialDemonstracaoFinanceiraNegocios(fileRCCSDFN);    
       } catch (ParserConfigurationException ex) {
           Erro  err = new Erro(true, false);
           err.setIdErroDB(13101);
           err.setSystemPrintMessage("    ERRO "+ex);
           err.Execute();
           throw new DadosIIRException();
       } catch (SAXException ex) {
           Erro  err = new Erro(true, false);
           err.setIdErroDB(13102);
           err.setSystemPrintMessage("    ERRO "+ex);
           err.Execute();
           throw new DadosIIRException();
       } catch (IOException ex) {
           Erro  err = new Erro(true, false);
           err.setIdErroDB(13103);
           err.setSystemPrintMessage("    ERRO "+ex);
           err.Execute();
           throw new DadosIIRException();
       }
   }
     
   private  void ReaderInfoFinaDemonstracaoFin(File fileXML) throws ParserConfigurationException, SAXException, IOException{
        DocumentBuilderFactory builderfactory = DocumentBuilderFactory.newInstance();
       
            DocumentBuilder docbuilder = builderfactory.newDocumentBuilder();
            Document document= docbuilder.parse(fileXML);
            document.normalize();
            
            Element elementAOIFDF = getSubtag( document ,"ArrayOfInfoFinaDFin"); 
            NodeList nodelistNIP= getNodeList(elementAOIFDF, "NumeroIdentificacaoPeriodo");
            NodeList nodelistNC=getNodeList(elementAOIFDF, "NumeroConta") ;
            NodeList nodelistNOC=getNodeList(elementAOIFDF, "NumeroOrdemConta") ;
            NodeList nodelistDC =getNodeList(elementAOIFDF, "DescricaoConta1") ;
            NodeList nodelistVC1 =getNodeList(elementAOIFDF, "ValorConta1") ;
            NodeList nodelistVC2 =getNodeList(elementAOIFDF, "ValorConta2") ;
            NodeList nodelistVC3 =getNodeList(elementAOIFDF, "ValorConta3") ;
            NodeList nodelistVC4 =getNodeList(elementAOIFDF, "ValorConta4") ;
            NodeList nodelistVC5 =getNodeList(elementAOIFDF, "ValorConta5") ;
            NodeList nodelistVC6 =getNodeList(elementAOIFDF, "ValorConta6") ;
            NodeList nodelistVC7 =getNodeList(elementAOIFDF, "ValorConta7") ;
            NodeList nodelistVC8 =getNodeList(elementAOIFDF, "ValorConta8") ;
            NodeList nodelistVC9 =getNodeList(elementAOIFDF, "ValorConta9") ;
            NodeList nodelistVC10 =getNodeList(elementAOIFDF, "ValorConta10") ;
            NodeList nodelistVC11 =getNodeList(elementAOIFDF, "ValorConta11") ;
            NodeList nodelistVC12 =getNodeList(elementAOIFDF, "ValorConta12") ;
            tamanho_vetor_conta=nodelistDC.getLength();
             if(nodelistDC.getLength()==0)
             {
                 permissao_conta=false;
                 return;
             }
            Numeroidenfperiodo =new String[nodelistDC.getLength()];
            NUMEROCONTA= new String[nodelistDC.getLength()];
            NUMEROORDEMCONTA=new String[nodelistDC.getLength()];
            DESCRICAOCONTA=new String[nodelistDC.getLength()];
            VALORCONTA1=new String[nodelistDC.getLength()];
            VALORCONTA2=new String[nodelistDC.getLength()];
            VALORCONTA3=new String[nodelistDC.getLength()];
            VALORCONTA4=new String[nodelistDC.getLength()];
            VALORCONTA5=new String[nodelistDC.getLength()];
            VALORCONTA6=new String[nodelistDC.getLength()];
            VALORCONTA7=new String[nodelistDC.getLength()];
            VALORCONTA8=new String[nodelistDC.getLength()];
            VALORCONTA9=new String[nodelistDC.getLength()];
            VALORCONTA10=new String[nodelistDC.getLength()];
            VALORCONTA11=new String[nodelistDC.getLength()];
            VALORCONTA12=new String[nodelistDC.getLength()];
            
              for(int i=0; i<nodelistDC.getLength();i++)
              {
                  Node nodeNIP=nodelistNIP.item(i);
                  Node nodeNC=nodelistNC.item(i);
                  Node nodeNOC=nodelistNOC.item(i);
                  Node nodeDC= nodelistDC.item(i);
                  Node nodeVC1= nodelistVC1.item(i);
                  Node nodeVC2= nodelistVC2.item(i);
                  Node nodeVC3= nodelistVC3.item(i);
                  Node nodeVC4= nodelistVC4.item(i);
                  Node nodeVC5= nodelistVC5.item(i);
                  Node nodeVC6= nodelistVC6.item(i);
                  Node nodeVC7= nodelistVC7.item(i);
                  Node nodeVC8= nodelistVC8.item(i);
                  Node nodeVC9= nodelistVC9.item(i);
                  Node nodeVC10= nodelistVC10.item(i);
                  Node nodeVC11= nodelistVC11.item(i);
                  Node nodeVC12= nodelistVC12.item(i);
                 
                  
                  Element elementNIP = (Element) nodeNIP;
                  Element elementNC = (Element) nodeNC;
                  Element elementNOC = (Element) nodeNOC;
                  Element elementDC = (Element) nodeDC;
                  Element elementVC1 = (Element) nodeVC1;
                  Element elementVC2 = (Element) nodeVC2;
                  Element elementVC3 = (Element) nodeVC3;
                  Element elementVC4 = (Element) nodeVC4;
                  Element elementVC5 = (Element) nodeVC5;
                  Element elementVC6 = (Element) nodeVC6;
                  Element elementVC7 = (Element) nodeVC7;
                  Element elementVC8 = (Element) nodeVC8;
                  Element elementVC9 = (Element) nodeVC9;
                  Element elementVC10 = (Element) nodeVC10;
                  Element elementVC11 = (Element) nodeVC11;
                  Element elementVC12 = (Element) nodeVC12;
            
            Numeroidenfperiodo[i]=elementNC.getTextContent();
            NUMEROCONTA[i]= elementNC.getTextContent();
            NUMEROORDEMCONTA[i]=elementNOC.getTextContent();
            DESCRICAOCONTA[i]=elementDC.getTextContent();
            VALORCONTA1[i]=elementVC1.getTextContent();
            VALORCONTA2[i]=elementVC2.getTextContent();
            VALORCONTA3[i]=elementVC3.getTextContent();
            VALORCONTA4[i]=elementVC4.getTextContent();
            VALORCONTA5[i]=elementVC5.getTextContent();
            VALORCONTA6[i]=elementVC6.getTextContent();
            VALORCONTA7[i]=elementVC7.getTextContent();
            VALORCONTA8[i]=elementVC8.getTextContent();
            VALORCONTA9[i]=elementVC9.getTextContent();
            VALORCONTA10[i]=elementVC10.getTextContent();
            VALORCONTA11[i]=elementVC11.getTextContent();
            VALORCONTA12[i]=elementVC12.getTextContent();
                                   
              }
     
        
    }
    
   
   
   private  void ReaderPeriodoDemonstracaoFinanceira(File fileXML) throws ParserConfigurationException, SAXException, IOException{
        DocumentBuilderFactory builderfactory = DocumentBuilderFactory.newInstance();
        
            DocumentBuilder docbuilder = builderfactory.newDocumentBuilder();
            Document document= docbuilder.parse(fileXML);
            document.normalize();
            
            Element elementAOPDF = getSubtag(document ,"ArrayOfPeriodoDemonstracaoFinanceira");
            NodeList nodelistNIP = getNodeList( elementAOPDF ,"NumeroIdentificacaoPeriodo");
            NodeList nodelistDIP = getNodeList( elementAOPDF ,"DataInicioPeriodo");
            NodeList nodelistDFP = getNodeList( elementAOPDF ,"DataFimPeriodo");
            
                    
            NIP=new String[nodelistNIP.getLength()];
            I_DATAINICIOPERIODO=new String[nodelistDIP.getLength()];
            I_DATAFIMPERIODO=new String[nodelistDFP.getLength()];
            
              for(int i=0; i<nodelistNIP.getLength();i++)
              {
                  Node nodeNIP=nodelistNIP.item(i);
                  Node nodeDIP=nodelistDIP.item(i);
                  Node nodeDFP=nodelistDFP.item(i);
                  Element elementNIP = (Element) nodeNIP;
                  Element elementDIP = (Element) nodeDIP;
                  Element elementDFP = (Element) nodeDFP;

            NIP[i]=elementNIP.getTextContent();
            I_DATAINICIOPERIODO[i]=elementDIP.getTextContent();
            I_DATAFIMPERIODO[i]=elementDFP.getTextContent();                     
              }
     }
    
   
   
   private  void ReaderPagamentoProventoDinheiroDemonstracaoFinanceiraNegocios(File fileXML) throws ParserConfigurationException, SAXException, IOException{
            DocumentBuilderFactory builderfactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docbuilder = builderfactory.newDocumentBuilder();
            Document document= docbuilder.parse(fileXML);
            document.normalize();
            
            Element elementAOPPD = getSubtag( document ,"ArrayOfPagamentoProventoDinheiroDemonstracaoFinanceira"); //AOPPD
            NodeList nodelistDAP= getNodeList(elementAOPPD, "DataAprovacaoProvento");
            NodeList nodelistDIP =getNodeList(elementAOPPD, "DataInicioPagamento") ;
            NodeList nodelistVPPA =getNodeList(elementAOPPD, "ValorProventoPorAcao") ;
            NodeList nodelistTP = getNodeList(elementAOPPD, "TipoProvento");
            NodeList nodelistCEA = getNodeList(elementAOPPD, "CodigoEspecieAcao");
            NodeList nodelistCCEAP = getNodeList(elementAOPPD, "CodigoClasseAcaoPreferencial");
            tamanho_vetor_provento=nodelistDIP.getLength();
            
            if(nodelistDAP.getLength()==0)
             {
                 permissao_provento=false;
                 return;
             }
            
           TipoProvento=new String[nodelistDAP.getLength()];
           DataAprovacaoProvento=new String[nodelistDAP.getLength()];
           DataInicioPagamento=new String[nodelistDAP.getLength()];
           SiglaCodigoEspecie=new String[nodelistDAP.getLength()];
           DescricaoCodigoEspecie=new String[nodelistDAP.getLength()];
           SiglaCodigoClassePreferencial=new String[nodelistDAP.getLength()];
           DescricaoCodigoClassePreferencial=new String[nodelistDAP.getLength()];
           ValorProventoPorAcao=new String[nodelistDAP.getLength()];
           
            
              for(int i=0; i<nodelistDAP.getLength();i++)
              {
                  Node nodeDAP=nodelistDAP.item(i);
                  Node nodeDIP= nodelistDIP.item(i);
                  Node nodeVPPA= nodelistVPPA.item(i);
  
                  Element elementDAP = (Element) nodeDAP;
                  Element elementDIP = (Element) nodeDIP;
                  Element elementVPPA = (Element) nodeVPPA;
                  
                DataAprovacaoProvento[i]=elementDAP.getTextContent();
                DataInicioPagamento[i]=elementDIP.getTextContent();
                ValorProventoPorAcao[i]=elementVPPA.getTextContent();  
                
              }
             
              for(int i=0;i<nodelistTP.getLength();i++)
              {
                  
                    Node nodeTP = nodelistTP.item(i);
                    Element elementTP = (Element) nodeTP;
                    
                    NodeList nodelistDOD=getNodeList(elementTP, "DescricaoOpcaoDominio") ;
                    Node nodeDOD=nodelistDOD.item(0);
                    Element elementDOD = (Element) nodeDOD;
                    TipoProvento[i]=elementDOD.getTextContent();
                  
              }
              for(int i=0;i<nodelistCEA.getLength();i++)
              {
                  
                    Node nodeCEA = nodelistCEA.item(i);
                    Element elementCEA = (Element) nodeCEA;
                    
                    NodeList nodelistDOD=getNodeList(elementCEA, "DescricaoOpcaoDominio") ;
                    NodeList nodelistSOD=getNodeList(elementCEA, "SiglaOpcaoDominio") ;
                    
                    Node nodeDOD=nodelistDOD.item(0);
                    Element elementDOD = (Element) nodeDOD;
                    DescricaoCodigoEspecie[i]=elementDOD.getTextContent();

                    Node nodeSOD=nodelistSOD.item(0);
                    Element elementSOD = (Element) nodeSOD;
                    SiglaCodigoEspecie[i]=elementSOD.getTextContent();
                     
              }
              for(int i=0;i<nodelistCCEAP.getLength();i++)
              {
                  
                    Node nodeCCEAP = nodelistCCEAP.item(i);
                    Element elementCCEAP = (Element) nodeCCEAP;
                    
                    NodeList nodelistDOD=getNodeList(elementCCEAP, "DescricaoOpcaoDominio") ;
                    NodeList nodelistSOD=getNodeList(elementCCEAP, "SiglaOpcaoDominio") ;
                    
                    Node nodeDOD=nodelistDOD.item(0);
                    Element elementDOD = (Element) nodeDOD;
                    DescricaoCodigoClassePreferencial[i]=elementDOD.getTextContent();

                    Node nodeSOD=nodelistSOD.item(0);
                    Element elementSOD = (Element) nodeSOD;
                    SiglaCodigoClassePreferencial[i]=elementSOD.getTextContent();
                  
              }
        }
   

   
   private  void ReaderComposicaoCapitalSocialDemonstracaoFinanceiraNegocios(File fileXML) throws ParserConfigurationException, SAXException, IOException{
        DocumentBuilderFactory builderfactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docbuilder = builderfactory.newDocumentBuilder();
            Document document= docbuilder.parse(fileXML);
            document.normalize();
            
            Element elementCCSDF = getSubtag(document ,"ComposicaoCapitalSocialDemonstracaoFinanceira");//ComposicaoCapitalSocialDemonstracaoFinanceira
            Element elementNICCS=(Element)elementCCSDF.getElementsByTagName("NumeroIdentificacaoComposicaoCapitalSocial").item(0); //NumeroIdentificacaoComposicaoCapitalSocial
            Element elementQAOCI=(Element)elementCCSDF.getElementsByTagName("QuantidadeAcaoOrdinariaCapitalIntegralizado").item(0);  //QuantidadeAcaoOrdinariaCapitalIntegralizado       
            Element elementQAFCI=(Element)elementCCSDF.getElementsByTagName("QuantidadeAcaoPreferencialCapitalIntegralizado").item(0);  //QuantidadeAcaoPreferencialCapitalIntegralizado
            Element elementQTACI=(Element)elementCCSDF.getElementsByTagName("QuantidadeTotalAcaoCapitalIntegralizado").item(0);  //QuantidadeTotalAcaoCapitalIntegralizado
            Element elementQAOT=(Element)elementCCSDF.getElementsByTagName("QuantidadeAcaoOrdinariaTesouraria").item(0); //QuantidadeAcaoOrdinariaTesouraria
            Element elementQAPT=(Element)elementCCSDF.getElementsByTagName("QuantidadeAcaoPreferencialTesouraria").item(0);  //QuantidadeAcaoPreferencialTesouraria
            Element elementQTAT=(Element)elementCCSDF.getElementsByTagName("QuantidadeTotalAcaoTesouraria").item(0);  //QuantidadeTotalAcaoTesouraria
            
   
            NICCS=elementNICCS.getTextContent();
            QAOCI=elementQAOCI.getTextContent();
            QAFCI=elementQAFCI.getTextContent();                     
            QTACI=elementQTACI.getTextContent();
            QAOT=elementQAOT.getTextContent();
            QAPT=elementQAPT.getTextContent();  
            QTAT=elementQTAT.getTextContent();
           
    }
 
    
    
   
   
    private  Element getSubtag(Element rootElement,String tag){
       NodeList nodelist =rootElement.getElementsByTagName(tag);
       Node rootnode = nodelist.item(0);
       rootElement =(Element) rootnode;
       return rootElement;
    }
    private  Element getSubtag(Document document , String tag){
       NodeList nodelist = document.getElementsByTagName(tag);
       Node rootnode = nodelist.item(0);
       Element rootElement =(Element) rootnode;
       return rootElement;
    }
    private  NodeList getNodeList(Element rootElement , String tag){
       NodeList nodelist = rootElement.getElementsByTagName(tag);
       return nodelist;
    }
    private  NodeList getNodeList(Document document , String tag){
       NodeList nodelist = document.getElementsByTagName(tag);
       return nodelist;
    }

    
    
    
    
    
    public class DadosIIRException extends Exception {    

          public DadosIIRException(){
                    super();
            }

            @Override
            public String toString(){
                    return "Ocorreu algum erro  a leitura do arquivo itr/dfp xml ";
            }

            @Override
            public String getMessage(){
                    return "Ocorreu algum erro durante  a inserção do arquivo da cotação bovespa ";

            }
    }
    
}
