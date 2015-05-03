/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package View;
import KUspital.*;
import javax.swing.ImageIcon;
/**
 *
 * @author Soo-Young
 */
public class OutsideDocInfo extends DocInfo {
    ImageIcon image;

    public OutsideDocInfo(MainSystem ms,int uno) {
        super(ms);          
        initComponents();
        asdf();   
        this.jTextField1.setText(ms.DBoutput.get_information(uno, "age"));
        jTextField2.setText(ms.DBoutput.get_information(uno, "gender"));
        jTextField9.setText(ms.DBoutput.get_information(uno, "phone"));
        jTextField3.setText(String.valueOf(uno));
        jTextField4.setText(ms.DBoutput.get_information(uno, "name"));
        switch (Integer.parseInt(ms.DBoutput.get_information(uno, "department")))
        {
            case 1:
                jTextField5.setText("소아과");
                break;
            case 2:
                jTextField5.setText("정형외과");
                break;
            case 3:
                jTextField5.setText("정신과");
                break;
            case 4:
                jTextField5.setText("비뇨기과");
                break;
        }
        jTextArea1.setText(ms.DBoutput.get_information(uno, "ta1"));
        jTextArea2.setText(ms.DBoutput.get_information(uno, "ta2"));
        if(ms.DBoutput.getPhoto(uno) != null)
        {
            image=new ImageIcon(ms.DBoutput.getPhoto(uno));
            this.jLabel1.setIcon(image);
            System.out.print(ms.DBoutput.getPhoto(uno));
        }
        else
        {
            this.jLabel1.setIcon(null);
        }
        setVisible(true);
    }     
    public void asdf()
    {
    this.jPanel3.remove(jButton1);
    this.jPanel3.remove(jButton2);
    this.jPanel3.remove(jButton3);
    this.jPanel3.remove(jButton4);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 444, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 720, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
