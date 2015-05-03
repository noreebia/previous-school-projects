/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package View;
import Model.*;
/**
 *
 * @author 이상명'spc
 */
public class UnitLandForm extends javax.swing.JFrame {
    Player p;
    B_UnitListForm ulf;
    Unit u;
    /**
     * Creates new form UnitLandForm
     */
    public UnitLandForm() {
        initComponents();
        setTitle("위치를 골라주세요");
    }
    
    public UnitLandForm(Player p,Unit u,B_UnitListForm ulf){
        this();
        this.p = p;
        this.u = u;
        this.ulf = ulf;
        setting();
        buttonModi();
    }
    
    public void setting(){
        if(p.fieldUnit[0][0]!=null){
            field1_1.setIcon(p.fieldUnit[0][0].mark);
            field1_1.setEnabled(false);
        }
        if(p.fieldUnit[0][1]!=null){
            field1_2.setIcon(p.fieldUnit[0][1].mark);
            field1_2.setEnabled(false);
        }
        if(p.fieldUnit[0][2]!=null){
            field1_3.setIcon(p.fieldUnit[0][2].mark);
            field1_3.setEnabled(false);
        }
        if(p.fieldUnit[0][3]!=null){
            field1_4.setIcon(p.fieldUnit[0][3].mark);
            field1_4.setEnabled(false);
        }
        if(p.fieldUnit[1][0]!=null){
            field2_1.setIcon(p.fieldUnit[1][0].mark);
            field2_1.setEnabled(false);
        }
        if(p.fieldUnit[1][1]!=null){
            field2_2.setIcon(p.fieldUnit[1][1].mark);
            field2_2.setEnabled(false);
        }
        if(p.fieldUnit[1][2]!=null){
            field2_3.setIcon(p.fieldUnit[1][2].mark);
            field2_3.setEnabled(false);
        }
        if(p.fieldUnit[1][3]!=null){
            field2_4.setIcon(p.fieldUnit[1][3].mark);
            field2_4.setEnabled(false);
        }
        if(p.fieldUnit[2][0]!=null){
            field3_1.setIcon(p.fieldUnit[2][0].mark);
            field3_1.setEnabled(false);
        }
        if(p.fieldUnit[2][1]!=null){
            field3_2.setIcon(p.fieldUnit[2][1].mark);
            field3_2.setEnabled(false);
        }
        if(p.fieldUnit[2][2]!=null){
            field3_3.setIcon(p.fieldUnit[2][2].mark);
            field3_3.setEnabled(false);
        }
        if(p.fieldUnit[2][3]!=null){
            field3_4.setIcon(p.fieldUnit[2][3].mark);
            field3_4.setEnabled(false);
        }
    }
    
    public void buttonModi(){ //버튼액션 개조용 메소드.
        field1_1.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt){
                addFieldActionPerformed(evt);
            }
        });
        field1_2.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt){
                addFieldActionPerformed(evt);
            }
        });
        field1_3.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt){
                addFieldActionPerformed(evt);
            }
        });
        field1_4.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt){
                addFieldActionPerformed(evt);
            }
        });
        field2_1.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt){
                addFieldActionPerformed(evt);
            }
        });
        field2_2.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt){
                addFieldActionPerformed(evt);
            }
        });
        field2_3.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt){
                addFieldActionPerformed(evt);
            }
        });
        field2_4.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt){
                addFieldActionPerformed(evt);
            }
        });
        field3_1.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt){
                addFieldActionPerformed(evt);
            }
        });
        field3_2.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt){
                addFieldActionPerformed(evt);
            }
        });
        field3_3.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt){
                addFieldActionPerformed(evt);
            }
        });
        field3_4.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt){
                addFieldActionPerformed(evt);
            }
        });
        
    }
    
    public void addFieldActionPerformed(java.awt.event.ActionEvent evt){
        if(evt.getSource() == field1_1){
            p.fieldUnit[0][0] = u;
            ulf.thread.SendCommand("100"+u.name);
        }
        else if(evt.getSource() == field1_2){
            p.fieldUnit[0][1] = u;
            ulf.thread.SendCommand("101"+u.name);
        }
        else if(evt.getSource() == field1_3){
            p.fieldUnit[0][2] = u;
            ulf.thread.SendCommand("102"+u.name);
        }
        else if(evt.getSource() == field1_4){
            p.fieldUnit[0][3] = u;
            ulf.thread.SendCommand("103"+u.name);
        }
        else if(evt.getSource() == field2_1){
            p.fieldUnit[1][0] = u;
            ulf.thread.SendCommand("110"+u.name);
        }
        else if(evt.getSource() == field2_2){
            p.fieldUnit[1][1] = u;
            ulf.thread.SendCommand("111"+u.name);
        }
        else if(evt.getSource() == field2_3){
            p.fieldUnit[1][2] = u;
            ulf.thread.SendCommand("112"+u.name);
        }
        else if(evt.getSource() == field2_4){
            p.fieldUnit[1][3] = u;
            ulf.thread.SendCommand("113"+u.name);
        }
        else if(evt.getSource() == field3_1){
            p.fieldUnit[2][0] = u;
            ulf.thread.SendCommand("120"+u.name);
        }
        else if(evt.getSource() == field3_2){
            p.fieldUnit[2][1] = u;
            ulf.thread.SendCommand("121"+u.name);
        }
        else if(evt.getSource() == field3_3){
            p.fieldUnit[2][2] = u;
            ulf.thread.SendCommand("122"+u.name);
        }
        else if(evt.getSource() == field3_4){
            p.fieldUnit[2][3] = u;
            ulf.thread.SendCommand("123"+u.name);
        }
        ulf.setting();
        super.dispose();
    }
    
    public void dispose(){
        super.dispose();
        if(u instanceof Defender1){
            p.G1UnitList.add(u);
        }
        else if(u instanceof Defender2){
            p.G2UnitList.add(u);
        }
        else if(u instanceof Defender3){
            p.G3UnitList.add(u);
        }
        else if(u instanceof Long_Range1){
            p.R1UnitList.add(u);
        }
        else if(u instanceof Long_Range2){
            p.R2UnitList.add(u);
        }
        else if(u instanceof Long_Range3){
            p.R3UnitList.add(u);
        }
        else if(u instanceof Magician1){
            p.M1UnitList.add(u);
        }
        else if(u instanceof Magician2){
            p.M2UnitList.add(u);
        }
        else if(u instanceof Magician3){
            p.M3UnitList.add(u);
        }
        ulf.setting();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        field1_1 = new javax.swing.JButton();
        field1_2 = new javax.swing.JButton();
        field1_3 = new javax.swing.JButton();
        field1_4 = new javax.swing.JButton();
        field2_2 = new javax.swing.JButton();
        field2_1 = new javax.swing.JButton();
        field2_3 = new javax.swing.JButton();
        field2_4 = new javax.swing.JButton();
        field3_2 = new javax.swing.JButton();
        field3_4 = new javax.swing.JButton();
        field3_1 = new javax.swing.JButton();
        field3_3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(field1_1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(field1_2, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(field1_3, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(field1_4, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(field2_1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(field2_2, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(field2_3, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(field2_4, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(field3_1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(field3_2, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(field3_3, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(field3_4, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(field1_1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(field1_2, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(field1_3, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(field1_4, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(field2_1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(field2_2, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(field2_3, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(field2_4, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(field3_1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(field3_2, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(field3_3, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(field3_4, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton field1_1;
    private javax.swing.JButton field1_2;
    private javax.swing.JButton field1_3;
    private javax.swing.JButton field1_4;
    private javax.swing.JButton field2_1;
    private javax.swing.JButton field2_2;
    private javax.swing.JButton field2_3;
    private javax.swing.JButton field2_4;
    private javax.swing.JButton field3_1;
    private javax.swing.JButton field3_2;
    private javax.swing.JButton field3_3;
    private javax.swing.JButton field3_4;
    // End of variables declaration//GEN-END:variables
}
