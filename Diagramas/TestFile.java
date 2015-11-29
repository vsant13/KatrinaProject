package Diagramas;

import ArquivosCvm.BDCvm;
import ArquivosCvm.CiaDeliverData;
import ArquivosCvm.DadosITR;
import AtualizacaoEmissor.Emissor;
import ConexaoBD.ConnectionDB;
import ConexaoBD.FuncoesDB;
import ConexaoBD.UserDB;
import ConexaoNET.HTMLParser;
import ConexaoNET.ParseHandlingC;
import CotacaoBmf.BDBmf;
import CotacaoBmf.FileBmfNet;
import Dados.ConfiguracoesIniciais;
import Dados.DadosDB;
import Dados.DadosEmissor;
import Dados.Diretorios;
import Dao.CiaDao;
import Erros.Erro;
import InterfaceGraf.IFControlDB;
import InterfaceGraf.IFLoadBar1;
import InterfaceGraf.IFSubConfigurarUsDB;
import ManutencaoBD.RelePapeisdeDesconhecidos;
import entity.Cia;
import entity.ProxVersao.DfpConta;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class TestFile {
	
	
	
	public static void main(String args[]) throws Exception{
           
            /*
             * ACHO QUE ESSES SAO OS MAIS IMPORTANTES E MAIS COMPLETOS ATE AGORA . 
             * EXECUTE UM BLOCO DE CADA VEZ E NA ORDEM EM QUE ESTAO , DE UMA LIDA NOS COMENTARIOS ANTES
             * PARA HABILITAR UM BLOCO, NA FRENTE DE CADA BLOCO TEM OS SEGUINTES CARACTERES : " /* " .COLOQUE NA FRENTE DISSO ESSES CARACTERES "//" .
             * EXEMPLO : 
             *           /* //BLOCO-1...    ---FICA--->    // /* //BLOCO-1 ...
             * TERMINADO DE EXECUTAR O BLOCO APAGUE OS CARACTERES "//" COLOCADOS E VA PARA O PROXIMO.
             */
            
            
            
            /* //BLOCO-1 : Pede para configurar sua senha e usuario Mysql e salva em ..datafiles/config/UserDB.txt .
                 //         Para verificacao erre a senha antes , aperte fechar , em fim procure alguma forma de dar erro .
                 //          No fim coloque sua senha correta e rode novamente para certificar-se que não mais precisara de senha.
                 //          Isso pode causar um erro, por causa do conector mysql ,entao pule para o BLOCO-2 para tenta fazer a conecao com
                 //          banco ,se houver problemas verifique o conector mysql : http://www.youtube.com/watch?v=lqT-SBMwcxA   
                 //          Ja tem um conector na pasta ..datafiles/config/mysql-connector-java-5.11.35
                 UserDB.getUserDB();
             //*/
               
            
             /*  //BLOCO-2 : Tenta fazer a conexao com bancos de dados .
                 //          Se houver problemas verifique o conector mysql : http://www.youtube.com/watch?v=lqT-SBMwcxA   
                 //          Ja tem um conector na pasta ..datafiles/config/mysql-connector-java-5.11.35
               UserDB.getUserDB();
               ConnectionDB.close();
               ConnectionDB.SEMSCHEMA=true;
               boolean check=ConnectionDB.gerenciador();
               ConnectionDB.close();
               if(check==true)System.out.println("É POSSIVEL CONECTAR AO MYSQL!!!");
               else System.out.println("NÃO É POSSIVEL CONECTAR AO MYSQL!!! VERIFIQUE SE TEM NA BIBLIOTECA mysql-connector-java-5.1.25-bin.jar");
             //*/
            
            
               /*    //BLOCO-3 :  Faca a configuracao inicial do banco de dados . Primeiramente certifique-se que não tem o schema mercadofinanceiro no banco de dados,
                //         nao teria problema se tivesse , mas é só para acompanhar melhor o que acontece desde o comeco .Agora rode o bloco-3 , deve ja ter criado
                //         o schema .Blz agora rode de novo o bloco-3 e voce vai ver uma nova janela de mensagem, isso pq vc tinha um arquivo no banco e ele fez backup 
                //         deste, clique para fechar a janela dessa vez.Ok deve ter aberto a janela de criacao igual a primeira vez . rode denovo e clique em cancelar, 
                //        vai fazer a mesma coisa . Finalmente rode de novo e  clique em sim vai abrir uma janela com os arquivos de backup (repare que só pode ter um 
                //        backup por dia , os mais atuais do dia  substuem os anteriores) , novamente explore os botoes cancelar e fechar e abra um arquivo de backup
                //       e procure os erros).
           
                UserDB.getUserDB();  //Ja deve ter percebido que sem isso nada funciona.
                ConfiguracoesIniciais.Config_BancodeDados();
                 
           //*/
            
            
         /* //BLOCO-4 : Download do arquivo da bovespa  
               //          Nada de novo nessa ,só preparei algumas funcoes para evitar problemas no banco de dados , mas nao foi muito testado ainda
               //          Se quiser usar um arquivo mais rapido use de dia por exemplo FileBmfNet(9,10,11)-->09/10/2011.
             FileBmfNet file = new FileBmfNet(30,02,2014);
                file.downFiles();
            //*/  
            
           /*//BLOCO-5 : COlocar arquivo bovespa no banco
               //          Nada de novo nessa ,só preparei algumas funcoes para evitar problemas no banco de dados , mas nao foi muito testado ainda
                 UserDB.getUserDB();  //Ja deve ter percebido que sem isso nada funciona.
                 BDBmf TENTATIVA = new BDBmf(19,12,2014);   
                      
                 TENTATIVA.insertall(); 
             //*/

   

            
     /*  
            Emissor em = new Emissor();
            em.BaixarAtualizacoes();
            em.AtualizaCia(2013);
            //*/
            
                    
     
          
    /*  
            UserDB.getUserDB();
            Cia cia = new Cia("CCPR");
            CiaDao ciaDao= new CiaDao(cia);
            cia=ciaDao.getFirst();
            BDCvm bdcvm= new BDCvm(cia);
            

     */          
            
                  
       /*         Cia cia= new Cia();
                cia.setIdEmpresa("Vale");
                CiaDao ciadao = new CiaDao(cia);
                cia =  ciadao.getFirst();
            Conta conta = new Conta(cia,1,13);
            System.out.println(conta.data_fim_referecia_principal+"***"+conta.valor_conta[1]+"*****"+conta.numero_conta);
            System.out.println(cia.getIdEmpresa()+"***"+cia.getCodCvm());
            conta.getNext();
            System.out.println(conta.data_fim_referecia_principal+"***"+conta.valor_conta[1]+"*****"+conta.numero_conta);
            System.out.println(cia.getIdEmpresa()+"***"+cia.getCodCvm());
            conta.numerocontaIslike("1.01.02.01%");
            System.out.println(conta.data_fim_referecia_principal+"***"+conta.descricao_conta+"*****"+conta.numero_conta);
            System.out.println(cia.getIdEmpresa()+"***"+cia.getCodCvm());
            conta.descricaocontaIslike("T%");
            System.out.println(conta.data_fim_referecia_principal+"***"+conta.descricao_conta+"*****"+conta.numero_conta);
            System.out.println(cia.getIdEmpresa()+"***"+cia.getCodCvm());
            conta.getNext();
            System.out.println(conta.data_fim_referecia_principal+"***"+conta.descricao_conta+"*****"+conta.numero_conta);
            System.out.println(cia.getIdEmpresa()+"***"+cia.getCodCvm());
            BDCvm BDCVM = new BDCvm(cia);//*/
                
               
                
          
          
   //       
         
      
            
        // /*  
         
           
            
          
        //*/
            
            
          /*
            DadosDB.getInstance();
   
            for(int i=1;i<=DadosDB.getDataBaseTableNumber();i++)
            {
                System.out.println(DadosDB.getDataBaseTableName(i)+"-------------------");
                int n=DadosDB.getDataBaseColumnNumber(i);
                 for(int j=1;j<=n;j++)
                     System.out.println(DadosDB.getDataBaseColumnName(i, j));
            }
            System.out.println(DadosDB.getDataBaseColumnName(1, 1));
                //   */
           
            
         //  Facede fa = FacedeHighlander.getInstance();
        // fa.bovespaRotine();
         // fa.bovespaGetAllYears();
         // fa.consertaBovespaEmAndamento();
            
           
        }
       
}