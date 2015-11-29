/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Diagramas;

/**
 *
 * @author vitor
 */
public class FacedeHighlander {
    
    public static Facede f;
    
    public static Facede getInstance(){
        
        if(f == null)
            return new Facede();
        return f;
    }
}
