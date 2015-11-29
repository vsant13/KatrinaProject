/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Erros;

/**
 *
 * @author Erico
 */
public interface IFMyDialog {
    public void setTitle(String title); 
    public void setTitulo(String titulo);
    public void setNameAddButton(String name);
    public void setNameLeftButton(String name);
    public void setNameRightButton(String name);
    public void setMessage(String message);
    public void setOption(int OPTION);
    public int showConfirmDialog();
    public int showConfirmDialog(String titulo ,String title,String message ,String LeftButtonName ,String RightButtonName );
    public int showConfirmDialog(String titulo ,String title ,String message,String LeftButtonName ,String RightButtonName,int OPTION);
    
    
}
