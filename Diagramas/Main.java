/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Diagramas;

import ArquivosCvm.BDCvm;
import ConexaoBD.UserDB;
import Dao.CiaDao;
import InterfaceGraf.IFMain;
import entity.Cia;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author erico
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
              for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                    if ("GTK+".equals(info.getName())) {
                        try {
                            javax.swing.UIManager.setLookAndFeel(info.getClassName());
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (InstantiationException ex) {
                            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IllegalAccessException ex) {
                            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (UnsupportedLookAndFeelException ex) {
                            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        break;
                    }
                }
            IFMain ifd= new IFMain();
            ifd.setVisible(true);
       
        
    }
    
}
