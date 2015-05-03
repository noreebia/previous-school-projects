/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package View;
import javax.swing.*;
import KUspital.*;
/**
 *
 * @author noreebia
 */
public class DoctorHome extends Home {
    DefaultListModel dlm;
    MainSystem ms;
    /**
     * Creates new form NewJFrame5
     */
    public DoctorHome(MainSystem ms) {
        super();
        this.ms=ms;
        dlm=new DefaultListModel();
        this.jList1.setModel(dlm);
        setTitle("의사 접속");
        super.jButton7.setText("도움말");
        super.jButton2.setText("내 정보");
        super.jButton3.setText("환자 정보");
        super.jButton8.setText("로그 아웃");
        super.jButton4.setText("종료");
        super.jButton5.setText("방 들어가기");
        super.jButton1.setText("채팅 신청");
        super.jLabel5.setText("의사 번호");;
        super.jPanel1.remove(jButton1);
        initComponents();
        listInitialiser();
        buttonModi();
        this.jLabel5.setText("의사 번호:"+ ms.my_uno);
        this.jLabel2.setText(jLabel2.getText()+ ms.my_name);
        this.jLabel3.setText(jLabel3.getText()+ String.valueOf(ms.my_age));
        this.jLabel4.setText(jLabel4.getText()+ ms.my_gender);
        this.jLabel6.setText(jLabel6.getText()+ ms.my_phone);
        this.jLabel7.setText(jLabel7.getText()+ms.my_dept);   
        setVisible(true);
    }
    public void listInitialiser()
    {
        int[] list= ms.DBoutput.getMyDocList(ms.my_uno);
        for(int i=0;i<5;i++)
        {
            if(list[i] != 0)
            {
                dlm.addElement(ms.DBoutput.get_information(list[i], "ID"));
            }
        }
    }
        public void buttonModi(){
        this.jButton3.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt){
        MyPatientList e=new MyPatientList(ms);
        dispose();
            }
        });
        this.jButton5.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt){
                   Waiting w=new Waiting();
                    ms.startChat(String.valueOf(jList1.getSelectedValue()), w);

            }
        });
        
        this.jButton4.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt){
                   dispose();
            }
        });

        this.jButton8.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt){
                    Login l=new Login(ms);
                    dispose();
            }
        });
        this.jButton2.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt){
            DocInfo pi=new DocInfo(ms);
            dispose();
            }
        });
        this.jButton9.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt){
        Account a=new Account(ms);
        dispose();
            }
        });
        this.jButton7.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt){
        DoctorHelp dhelp=new DoctorHelp();

            }
        });
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setPreferredSize(new java.awt.Dimension(712, 386));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 712, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 376, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
