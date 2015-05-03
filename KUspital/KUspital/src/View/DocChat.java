package View;
import KUspital.*;
import java.io.IOException;

public class DocChat extends Chat {
    
    public DocChat(MainSystem ms, int uno) throws IOException {
        super();

        this.uno = uno;
        this.ms=ms;
        super.jLabel1.setText((ms.DBoutput.get_information(uno, "name")));        
        buttonModi();
        initComponents();
    }
        public void buttonModi() throws IOException
    {
        rh.readHistory(ms.DBoutput.get_information(uno, "ID") + ms.my_ID + ".txt", this);
        
                this.jButton2.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt){
    SecondOutsidePatientInfo di=new SecondOutsidePatientInfo(ms,uno);
            }
        });
                
                this.jButton3.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt){
                thread.getOut();
                save = new SaveChat();
                save.Save(ms.DBoutput.get_information(uno, "ID") + ms.my_ID + ".txt", jTextArea1.getText());
            }
        });
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 643, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
