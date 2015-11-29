
package Erros;

import javax.swing.JOptionPane;

public class IFSimple extends javax.swing.JDialog implements IFMyDialog {
     private int IntAddButton = 0;
    private int IntLeftButton = 1;
    private int IntRightButton = 2;
    private int BotaoSelecionado = -1; 
    private boolean CHANGENAMEADDBUTTON=false;
    private boolean CHANGENAMELEFTBUTTON=false;
    private boolean CHANGENAMERIGHTBUTTON=false;
    
     private int OPTION=-1;
    
    public IFSimple(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
    }
    
    public IFSimple() {
        this(null,true);
    }
    public IFSimple(String titulo,String title,String message,String LeftButtonName ,String RightButtonName) {
        this();
        setTitle(title);
        setTitulo(titulo);
        setMessage(message);
        setNameLeftButton(LeftButtonName);
        setNameRightButton(RightButtonName);
        setDefault();
    }
    public IFSimple(String titulo,String title,String message,String LeftButtonName ,String RightButtonName ,String AddButtonName) {
        this();
        setTitle(title);
        setTitulo(titulo);
        setMessage(message);
        setNameLeftButton(LeftButtonName);
        setNameRightButton(RightButtonName);
        setNameAddButton(AddButtonName);
        setDefault();
    }
    public IFSimple(String titulo,String message,int OPTION) {
        this();
        setTitulo(titulo);
        setMessage(message);
        this.OPTION=OPTION;
        setDefault();
    }
    public IFSimple(String titulo,String title,String message,String LeftButtonName ,String RightButtonName ,String AddButtonName ,int OPTION) {
        this();
        setTitle(title);
        setTitulo(titulo);
        setMessage(message);
        setNameLeftButton(LeftButtonName);
        setNameRightButton(RightButtonName);
        setNameAddButton(AddButtonName);
        this.OPTION=OPTION;
        setDefault();
    }
    private void setDefault(){
        switch(OPTION){
            case JOptionPane.DEFAULT_OPTION:
                if(!CHANGENAMERIGHTBUTTON) setNameRightButton("Ok");
                setVisibleAddButton(false);
                setVisibleLeftButton(false);
                IntAddButton = -1;
                IntLeftButton = -1;
                IntRightButton = 0;
                break;
            case JOptionPane.YES_NO_OPTION:
                if(!CHANGENAMERIGHTBUTTON) setNameRightButton("No");
                if(!CHANGENAMELEFTBUTTON) setNameLeftButton("Yes");
                setVisibleAddButton(false);
                IntAddButton = -1;
                IntLeftButton = 0;
                IntRightButton = 1;
                break;
            case JOptionPane.OK_CANCEL_OPTION:   
                if(!CHANGENAMERIGHTBUTTON) setNameRightButton("Cancel");
                if(!CHANGENAMELEFTBUTTON) setNameLeftButton("Ok");
                setVisibleAddButton(false);
                IntAddButton = -1;
                IntLeftButton = 0;
                IntRightButton = 1;
                break;
            case JOptionPane.YES_NO_CANCEL_OPTION:
                if(!CHANGENAMERIGHTBUTTON) setNameRightButton("Cancel");
                if(!CHANGENAMELEFTBUTTON) setNameLeftButton("No");
                if(!CHANGENAMEADDBUTTON) setNameAddButton("Yes");
                IntAddButton = 0;
                IntLeftButton = 1;
                IntRightButton = 2;
                break;
            default:
                
        }
    }
    @Override
    public void setOption(int OPTION){
        this.OPTION=OPTION;
        setDefault();
    }
    @Override
    public void setTitulo(String titulo){ 
        lb_titulo.setText(titulo);
    }
    @Override
    public void setMessage(String message){ 
        ta_message.setText(message);
    }
    private void setVisibleAddButton(boolean estado){
        AddButton.setVisible(estado);
    }
    @Override
    public void setNameAddButton(String name){
        AddButton.setText(name);
        CHANGENAMEADDBUTTON=true;
        AddButton.setVisible(true);
    }
    private void setVisibleLeftButton(boolean estado){
        LeftButton.setVisible(estado);
    }
    @Override
    public void setNameLeftButton(String name){
        LeftButton.setText(name);
        CHANGENAMELEFTBUTTON=true;
        LeftButton.setVisible(true);
    }
    private void setVisibleRightButton(boolean estado){
        RightButton.setVisible(estado);
    }
    @Override
    public void setNameRightButton(String name){
         RightButton.setText(name);
         CHANGENAMERIGHTBUTTON=true;
         RightButton.setVisible(true);
    }
    @Override
    public int showConfirmDialog(){
        int retorno;
        setVisible(true);
        close();
        retorno =BotaoSelecionado;
        return retorno;
    }
    @Override
    public int showConfirmDialog(String titulo,String title ,String message,String LeftButtonName ,String RightButtonName ){
        setTitle(title);
        setTitulo(titulo);
        setMessage(message);
        setNameLeftButton(LeftButtonName);
        setNameRightButton(RightButtonName);
        return showConfirmDialog();
    }
    @Override
    public int showConfirmDialog(String titulo ,String title,String message ,String LeftButtonName ,String RightButtonName,int OPTION){
        setTitle(title);
        setTitulo(titulo);
        setMessage(message);
        setNameLeftButton(LeftButtonName);
        setNameRightButton(RightButtonName);
        this.OPTION=OPTION;
        setDefault();
        return showConfirmDialog();
    }
    
    public void close(){
    dispose();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        lb_titulo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ta_message = new javax.swing.JTextArea();
        LeftButton = new javax.swing.JButton();
        RightButton = new javax.swing.JButton();
        AddButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel2.setBackground(new java.awt.Color(180, 180, 180));

        lb_titulo.setBackground(new java.awt.Color(0, 102, 153));
        lb_titulo.setFont(new java.awt.Font("Catriel", 1, 14)); // NOI18N
        lb_titulo.setForeground(new java.awt.Color(255, 255, 255));
        lb_titulo.setText("NÃO INDENTIFICADO");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lb_titulo)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb_titulo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        ta_message.setColumns(20);
        ta_message.setRows(5);
        jScrollPane1.setViewportView(ta_message);

        LeftButton.setText("Ok");
        LeftButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LeftButtonActionPerformed(evt);
            }
        });

        RightButton.setText("Cancel");
        RightButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RightButtonActionPerformed(evt);
            }
        });

        AddButton.setText("Add");
        AddButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 298, Short.MAX_VALUE)
                        .addComponent(AddButton, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(LeftButton, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(RightButton, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LeftButton)
                    .addComponent(RightButton)
                    .addComponent(AddButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void LeftButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LeftButtonActionPerformed
        // TODO add your handling code here:
        BotaoSelecionado=IntLeftButton;
        close();
    }//GEN-LAST:event_LeftButtonActionPerformed

    private void RightButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RightButtonActionPerformed
        // TODO add your handling code here:
        BotaoSelecionado=IntRightButton;
        close();
    }//GEN-LAST:event_RightButtonActionPerformed

    private void AddButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddButtonActionPerformed
        // TODO add your handling code here:
        BotaoSelecionado=IntAddButton;
        close();
    }//GEN-LAST:event_AddButtonActionPerformed

   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddButton;
    private javax.swing.JButton LeftButton;
    private javax.swing.JButton RightButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lb_titulo;
    private javax.swing.JTextArea ta_message;
    // End of variables declaration//GEN-END:variables

   
}
