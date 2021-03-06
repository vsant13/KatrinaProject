
package InterfaceGraf;

public class IFLoadBar1 extends javax.swing.JFrame implements Runnable{
     private double count=0;
     private long timeinicial=-1;
     private long time=0;
     private boolean SetTempodeTermino=false;
     private String str_lb_footnote="";
    
    public IFLoadBar1() {
        initComponents();
        lb_footnote.setVisible(false);
        
    }
    public void setTitleMessage(String titulo)
    { 
        lb_titulo.setText(titulo);
    }
    public void setBarIndeterminate(boolean estado)
    { 
        p_bar1.setIndeterminate(estado);
    }
    public void setBarStringPainted(boolean estado)
    { 
         p_bar1.setStringPainted(estado);
    }
    public void setMessage(String message)
    {
        ta_texto1.setText(message);
    }
    public void zeraBarValue()
    {
        p_bar1.setValue(p_bar1.getMinimum());
    }
    public void setTempodeTermino(boolean estado)
    {
        SetTempodeTermino=estado;
    }
    public void setWarningWaitFinishMessage(boolean estado)
    {
        str_lb_footnote=" Atenção : Aguarde até o fim do Processo.   "+str_lb_footnote;
        lb_footnote.setText(str_lb_footnote);
        lb_footnote.setVisible(estado);
    }
    public void AddTextTofootNote(String addtext)
    {
        str_lb_footnote=str_lb_footnote+" "+addtext;
        lb_footnote.setText(str_lb_footnote);
    }
    public void if_aux(int num_arq,String str){
            int p_barvalue=p_bar1.getValue();
            int max=p_bar1.getMaximum();
            int add;
            count+=(double)max/num_arq;
            if (count>=1)
            {
                add=((int)count);
                p_bar1.setValue((p_barvalue+add));
                if(p_barvalue+add>100) zeraBarValue();
                if(SetTempodeTermino)
                { 
                   if(timeinicial<0)timeinicial=System.currentTimeMillis();   
                   time=System.currentTimeMillis();
                   long estime ;
                   String strtime;
                   if (p_barvalue!=0) estime=((max-p_barvalue)*(time-timeinicial)/p_barvalue)/1000;
                   else estime=0;
                   strtime=formataTempo(estime);
                   ta_texto1.setText(str+ "\n\n" +(p_bar1.getValue())+ "%  Completo.  Estimativa de termino:   "+ strtime);
                   lb_footnote.setText(str_lb_footnote+"  Estimativa de termino: "+ strtime);
                }
                else
                {
                   ta_texto1.setText(str );
                }
                count-=(int)count;
                
            }
         
    }
    
    private String formataTempo(long elapsed)  
    { 
      int ss = (int)elapsed % 60;  
      elapsed /=(int) 60;  
      int min = (int)elapsed % 60;  
      elapsed /=(int) 60;  
      int hh = (int)elapsed % 24;  
      return strzero(hh) + ":" + strzero(min) + ":" + strzero(ss);  
    }  
  
    private String strzero(int n)  
    {  
      if(n < 10)  
        return "0" + String.valueOf(n);  
      return String.valueOf(n);  
    }  
    
    public void close(){
    dispose();
    }
     
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        lb_titulo = new javax.swing.JLabel();
        p_bar1 = new javax.swing.JProgressBar();
        lb_footnote = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ta_texto1 = new javax.swing.JTextArea();
        jProgressBar1 = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Create database");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

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
                .addComponent(lb_titulo, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
                .addContainerGap())
        );

        p_bar1.setBackground(new java.awt.Color(255, 255, 255));
        p_bar1.setForeground(new java.awt.Color(240, 240, 240));
        p_bar1.setBorderPainted(false);
        p_bar1.setStringPainted(true);

        lb_footnote.setFont(new java.awt.Font("Simplified Arabic", 0, 12)); // NOI18N
        lb_footnote.setText(" Atenção : Aguarde até o fim do Processo.");

        ta_texto1.setColumns(20);
        ta_texto1.setRows(5);
        jScrollPane1.setViewportView(ta_texto1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(p_bar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lb_footnote)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(p_bar1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addComponent(lb_footnote))
        );

        jProgressBar1.setIndeterminate(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 433, Short.MAX_VALUE)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lb_footnote;
    private javax.swing.JLabel lb_titulo;
    private javax.swing.JProgressBar p_bar1;
    private javax.swing.JTextArea ta_texto1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {
        setVisible(true);
    }

}




