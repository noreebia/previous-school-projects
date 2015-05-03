/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package View;
import KUspital.*;
/**
 *
 * @author Soo-Young
 */
public class SymptomsSheet extends javax.swing.JFrame {
MainSystem ms;
    /**
     * Creates new form SymptomsSheet
     */
    public SymptomsSheet(MainSystem ms) {

        this.ms=ms;
        initComponents();
        jTextArea1.setText("");
        if(ms.DBoutput.get_symptom_status(ms.my_uno, "scalp_bleeding")==1)
        {
         jCheckBox34.setSelected(true);           
         jTextArea1.append("두피 출혈, ");
        }
        else
        {
            jCheckBox34.setSelected(false);
        }
        if(ms.DBoutput.get_symptom_status(ms.my_uno, "balding")==1)
        {
         jCheckBox36.setSelected(true);         
         jTextArea1.append("탈모 증상, ");
        }
        else
        {
            jCheckBox36.setSelected(false);
        }
        if(ms.DBoutput.get_symptom_status(ms.my_uno, "brittle_hair")==1)
        {
         jCheckBox35.setSelected(true); 
         jTextArea1.append("퍽퍽한 머리카락, ");
        }
        else
        {
            jCheckBox35.setSelected(false);
        }
        if(ms.DBoutput.get_symptom_status(ms.my_uno, "headaches")==1)
        {
         jCheckBox37.setSelected(true);        
         jTextArea1.append("두통, ");
        }
        else
        {
            jCheckBox37.setSelected(false);
        }
        if(ms.DBoutput.get_symptom_status(ms.my_uno, "bleeding_eye")==1)
        {
         jCheckBox33.setSelected(true);           
         jTextArea1.append("안구 출혈, ");
        }
        else
        {
            jCheckBox33.setSelected(false);
        }
        if(ms.DBoutput.get_symptom_status(ms.my_uno, "blindness")==1)
        {
         jCheckBox32.setSelected(true);         
         jTextArea1.append("실명, ");
        }
        else
        {
            jCheckBox32.setSelected(false);
        }
        if(ms.DBoutput.get_symptom_status(ms.my_uno, "eye_irritation")==1)
        {
         jCheckBox31.setSelected(true);  
         jTextArea1.append("자극된 눈, ");
        }
        else
        {
            jCheckBox31.setSelected(false);
        }
        if(ms.DBoutput.get_symptom_status(ms.my_uno, "red_eye")==1)
        {
         jCheckBox30.setSelected(true);      
         jTextArea1.append("충혈, ");
        }
        else
        {
            jCheckBox30.setSelected(false);
        }
        if(ms.DBoutput.get_symptom_status(ms.my_uno, "nosebleed")==1)
        {
         jCheckBox22.setSelected(true);           
         jTextArea1.append("코피, ");
        }
        else
        {
            jCheckBox22.setSelected(false);
        }
        if(ms.DBoutput.get_symptom_status(ms.my_uno, "runny_nose")==1)
        {
         jCheckBox23.setSelected(true);         
         jTextArea1.append("콧물, ");
        }
        else
        {
            jCheckBox23.setSelected(false);
        }
        if(ms.DBoutput.get_symptom_status(ms.my_uno, "bleeding_gums")==1)
        {
         jCheckBox38.setSelected(true);           
         jTextArea1.append("잇몸 출혈, ");
        }
        else
        {
            jCheckBox38.setSelected(false);
        }
        if(ms.DBoutput.get_symptom_status(ms.my_uno, "chapped_lips")==1)
        {
         jCheckBox39.setSelected(true);           
         jTextArea1.append("갈라진 입술, ");
        }
        else
        {
            jCheckBox39.setSelected(false);
        }
        if(ms.DBoutput.get_symptom_status(ms.my_uno, "grinding_teeth")==1)
        {
         jCheckBox40.setSelected(true);     
         jTextArea1.append("이빨 가는 증상, ");
        }
        else
        {
            jCheckBox40.setSelected(false);
        }
        if(ms.DBoutput.get_symptom_status(ms.my_uno, "jaw_locking")==1)
        {
         jCheckBox41.setSelected(true);    
         jTextArea1.append("턱이 안 열리는 증상, ");
        }
        else
        {
            jCheckBox41.setSelected(false);
        }
        if(ms.DBoutput.get_symptom_status(ms.my_uno, "ear_ache")==1)
        {
         jCheckBox42.setSelected(true);    
         jTextArea1.append("귀앓이, ");
        }
        else
        {
            jCheckBox42.setSelected(false);
        }
        if(ms.DBoutput.get_symptom_status(ms.my_uno, "joint_paint")==1)
        {
         jCheckBox26.setSelected(true);       
         jTextArea1.append("관절 통증, ");
        }
        else
        {
            jCheckBox26.setSelected(false);
        }
        if(ms.DBoutput.get_symptom_status(ms.my_uno, "broken_bones")==1)
        {
         jCheckBox27.setSelected(true); 
         jTextArea1.append("골절, ");
        }
        else
        {
            jCheckBox27.setSelected(false);
        }
        if(ms.DBoutput.get_symptom_status(ms.my_uno, "coughing")==1)
        {
         jCheckBox1.setSelected(true);           
        }
        else
        {
            jCheckBox1.setSelected(false);
        }
        if(ms.DBoutput.get_symptom_status(ms.my_uno, "constipation")==1)
        {
         jCheckBox2.setSelected(true);         
         jTextArea1.append("기침, ");
        }
        else
        {
            jCheckBox2.setSelected(false);
        }
        if(ms.DBoutput.get_symptom_status(ms.my_uno, "nausea")==1)
        {
         jCheckBox3.setSelected(true);          
         jTextArea1.append("메스꺼움, ");
        }
        else
        {
            jCheckBox3.setSelected(false);
        }
        if(ms.DBoutput.get_symptom_status(ms.my_uno, "back_pain")==1)
        {
         jCheckBox18.setSelected(true);           
         jTextArea1.append("요통, ");
        }
        else
        {
            jCheckBox18.setSelected(false);
        }
        if(ms.DBoutput.get_symptom_status(ms.my_uno, "knee_pain")==1)
        {
         jCheckBox9.setSelected(true);          
         jTextArea1.append("무릎 통증, ");
        }
        else
        {
            jCheckBox9.setSelected(false);
        }

        setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel6 = new javax.swing.JPanel();
        jCheckBox30 = new javax.swing.JCheckBox();
        jCheckBox31 = new javax.swing.JCheckBox();
        jCheckBox32 = new javax.swing.JCheckBox();
        jCheckBox33 = new javax.swing.JCheckBox();
        jCheckBox34 = new javax.swing.JCheckBox();
        jCheckBox35 = new javax.swing.JCheckBox();
        jCheckBox36 = new javax.swing.JCheckBox();
        jCheckBox37 = new javax.swing.JCheckBox();
        jCheckBox22 = new javax.swing.JCheckBox();
        jCheckBox23 = new javax.swing.JCheckBox();
        jCheckBox41 = new javax.swing.JCheckBox();
        jCheckBox40 = new javax.swing.JCheckBox();
        jCheckBox39 = new javax.swing.JCheckBox();
        jCheckBox38 = new javax.swing.JCheckBox();
        jCheckBox42 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox27 = new javax.swing.JCheckBox();
        jCheckBox26 = new javax.swing.JCheckBox();
        jCheckBox18 = new javax.swing.JCheckBox();
        jCheckBox9 = new javax.swing.JCheckBox();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        jCheckBox30.setText("충혈");

        jCheckBox31.setText("눈 가려움증");

        jCheckBox32.setText("실명");

        jCheckBox33.setText("안구 출혈");

        jCheckBox34.setText("두피 출혈");

        jCheckBox35.setText("탈모");
        jCheckBox35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox35ActionPerformed(evt);
            }
        });

        jCheckBox36.setText("퍽퍽머리");
        jCheckBox36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox36ActionPerformed(evt);
            }
        });

        jCheckBox37.setText("두통");

        jCheckBox22.setText("코피");

        jCheckBox23.setText("과다한 콧물");

        jCheckBox41.setText("턱 경직");

        jCheckBox40.setText("이빨 갈기");

        jCheckBox39.setText("갈라진 입술");
        jCheckBox39.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox39ActionPerformed(evt);
            }
        });

        jCheckBox38.setText("잇몸 출혈");

        jCheckBox42.setText("귓병");

        jCheckBox3.setText("메스꺼움");

        jCheckBox2.setText("변비");

        jCheckBox1.setText("기침");

        jCheckBox27.setText("골절");

        jCheckBox26.setText("관절 고통");
        jCheckBox26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox26ActionPerformed(evt);
            }
        });

        jCheckBox18.setText("요통");

        jCheckBox9.setText("무릎 고통");
        jCheckBox9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox9ActionPerformed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
        );

        jLabel4.setText("현재 내 증상들");

        jButton1.setText("취소");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("저장");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("돌아가기");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addGap(105, 105, 105))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jCheckBox34)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox36)
                        .addGap(18, 18, 18)
                        .addComponent(jCheckBox35))
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel6Layout.createSequentialGroup()
                            .addComponent(jCheckBox38)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jCheckBox39)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jCheckBox40)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jCheckBox42))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                            .addComponent(jCheckBox1)
                            .addGap(34, 34, 34)
                            .addComponent(jCheckBox26)
                            .addGap(12, 12, 12)
                            .addComponent(jCheckBox3)
                            .addGap(4, 4, 4)
                            .addComponent(jCheckBox2)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jCheckBox33)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCheckBox31)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox32)
                        .addGap(30, 30, 30)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBox30)
                            .addComponent(jCheckBox37)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jCheckBox22)
                        .addGap(34, 34, 34)
                        .addComponent(jCheckBox23))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jCheckBox9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(97, 97, 97)
                                .addComponent(jCheckBox41))
                            .addComponent(jCheckBox18))
                        .addGap(18, 18, 18)
                        .addComponent(jCheckBox27)))
                .addContainerGap(52, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox34)
                    .addComponent(jCheckBox37)
                    .addComponent(jCheckBox36)
                    .addComponent(jCheckBox35))
                .addGap(35, 35, 35)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox33)
                    .addComponent(jCheckBox31)
                    .addComponent(jCheckBox30)
                    .addComponent(jCheckBox32))
                .addGap(29, 29, 29)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox38)
                    .addComponent(jCheckBox39)
                    .addComponent(jCheckBox40)
                    .addComponent(jCheckBox42))
                .addGap(36, 36, 36)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox1)
                    .addComponent(jCheckBox3)
                    .addComponent(jCheckBox26)
                    .addComponent(jCheckBox2))
                .addGap(35, 35, 35)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox9)
                    .addComponent(jCheckBox18)
                    .addComponent(jCheckBox41)
                    .addComponent(jCheckBox27))
                .addGap(29, 29, 29)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox22)
                    .addComponent(jCheckBox23))
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel6);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jCheckBox39ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox39ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox39ActionPerformed

    private void jCheckBox36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox36ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox36ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    PatientInfo pi=new PatientInfo(ms);
    dispose();// TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

    if(jCheckBox34.isSelected())
    {
        ms.DBinput.updateSymptoms(1, "scalp_bleeding", ms.my_uno);

    }
    else
    {
        ms.DBinput.updateSymptoms(0, "scalp_bleeding", ms.my_uno);
    }
    if(jCheckBox36.isSelected())
    {
        ms.DBinput.updateSymptoms(1, "brittle_hair", ms.my_uno);
    }
    else
    {
        ms.DBinput.updateSymptoms(0, "brittle_hair", ms.my_uno);
    }
    if(jCheckBox35.isSelected())
    {
        ms.DBinput.updateSymptoms(1, "balding", ms.my_uno);
    }
    else
    {
        ms.DBinput.updateSymptoms(0, "balding", ms.my_uno);
    }
    if(jCheckBox37.isSelected())
    {
        ms.DBinput.updateSymptoms(1, "headaches", ms.my_uno);
    }
    else
    {
        ms.DBinput.updateSymptoms(0, "headaches", ms.my_uno);
    }
    if(jCheckBox33.isSelected())
    {
        ms.DBinput.updateSymptoms(1, "bleeding_eye", ms.my_uno);
    }
    else
    {
        ms.DBinput.updateSymptoms(0, "bleeding_eye", ms.my_uno);
    }
    if(jCheckBox31.isSelected())
    {
        ms.DBinput.updateSymptoms(1, "eye_irritation", ms.my_uno);
    }
    else
    {
        ms.DBinput.updateSymptoms(0, "eye_irritation", ms.my_uno);
    }
    if(jCheckBox32.isSelected())
    {
        ms.DBinput.updateSymptoms(1, "blindness", ms.my_uno);
    }
    else
    {
        ms.DBinput.updateSymptoms(0, "blindness", ms.my_uno);
    }
    if(jCheckBox30.isSelected())
    {
        ms.DBinput.updateSymptoms(1, "red_eye", ms.my_uno);
    }
    else
    {
        ms.DBinput.updateSymptoms(0, "red_eye", ms.my_uno);
    }
    if(jCheckBox38.isSelected())
    {
        ms.DBinput.updateSymptoms(1, "bleeding_gums", ms.my_uno);
    }
    else
    {
        ms.DBinput.updateSymptoms(0, "bleeding_gums", ms.my_uno);
    }
    if(jCheckBox39.isSelected())
    {
        ms.DBinput.updateSymptoms(1, "chapped_lips", ms.my_uno);
    }
    else
    {
        ms.DBinput.updateSymptoms(0, "chapped_lips", ms.my_uno);
    }
    if(jCheckBox40.isSelected())
    {
        ms.DBinput.updateSymptoms(1, "grinding_teeth", ms.my_uno);
    }
    else
    {
        ms.DBinput.updateSymptoms(0, "grinding_teeth", ms.my_uno);
    }
    if(jCheckBox42.isSelected())
    {
        ms.DBinput.updateSymptoms(1, "ear_ache", ms.my_uno);
    }
    else
    {
        ms.DBinput.updateSymptoms(0, "ear_ache", ms.my_uno);
    }
    if(jCheckBox1.isSelected())
    {
        ms.DBinput.updateSymptoms(1, "coughing", ms.my_uno);
    }
    else
    {
        ms.DBinput.updateSymptoms(0, "coughing", ms.my_uno);
    }
    if(jCheckBox26.isSelected())
    {
        ms.DBinput.updateSymptoms(1, "joint_paint", ms.my_uno);
    }
    else
    {
        ms.DBinput.updateSymptoms(0, "joint_paint", ms.my_uno);
    }
    if(jCheckBox3.isSelected())
    {
        ms.DBinput.updateSymptoms(1, "nausea", ms.my_uno);
    }
    else
    {
        ms.DBinput.updateSymptoms(0, "nausea", ms.my_uno);
    }
    if(jCheckBox2.isSelected())
    {
        ms.DBinput.updateSymptoms(1, "constipation", ms.my_uno);
    }
    else
    {
        ms.DBinput.updateSymptoms(0, "constipation", ms.my_uno);
    }
    if(jCheckBox18.isSelected())
    {
        ms.DBinput.updateSymptoms(1, "back_pain", ms.my_uno);
    }
    else
    {
        ms.DBinput.updateSymptoms(0, "back_pain", ms.my_uno);
    }
    if(jCheckBox41.isSelected())
    {
        ms.DBinput.updateSymptoms(1, "jaw_locking", ms.my_uno);
    }
    else
    {
        ms.DBinput.updateSymptoms(0, "jaw_locking", ms.my_uno);
    }
    if(jCheckBox27.isSelected())
    {
        ms.DBinput.updateSymptoms(1, "broken_bones", ms.my_uno);
    }
    else
    {
        ms.DBinput.updateSymptoms(0, "broken_bones", ms.my_uno);
    }
    if(jCheckBox22.isSelected())
    {
        ms.DBinput.updateSymptoms(1, "nosebleed", ms.my_uno);
    }
    else
    {
        ms.DBinput.updateSymptoms(0, "nosebleed", ms.my_uno);
    }
    if(jCheckBox9.isSelected())
    {
        ms.DBinput.updateSymptoms(1, "knee_pain", ms.my_uno);
    }
    else
    {
        ms.DBinput.updateSymptoms(0, "knee_pain", ms.my_uno);
    }
    if(jCheckBox23.isSelected())
    {
        ms.DBinput.updateSymptoms(1, "runny_nose", ms.my_uno);
    }
    else
    {
        ms.DBinput.updateSymptoms(0, "runny_nose", ms.my_uno);
    }
    if(jCheckBox34.isSelected())
    {
        ms.DBinput.updateSymptoms(1, "scalp_bleeding", ms.my_uno);
    }
    else
    {
        ms.DBinput.updateSymptoms(0, "scalp_bleeding", ms.my_uno);
    }
    PatientInfo pai=new PatientInfo(ms);    
    Saved s=new Saved();
     dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jCheckBox35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox35ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox35ActionPerformed

    private void jCheckBox26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox26ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox26ActionPerformed

    private void jCheckBox9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox9ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

   // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     * @param args the command line arguments
     */
 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox18;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox22;
    private javax.swing.JCheckBox jCheckBox23;
    private javax.swing.JCheckBox jCheckBox26;
    private javax.swing.JCheckBox jCheckBox27;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox30;
    private javax.swing.JCheckBox jCheckBox31;
    private javax.swing.JCheckBox jCheckBox32;
    private javax.swing.JCheckBox jCheckBox33;
    private javax.swing.JCheckBox jCheckBox34;
    private javax.swing.JCheckBox jCheckBox35;
    private javax.swing.JCheckBox jCheckBox36;
    private javax.swing.JCheckBox jCheckBox37;
    private javax.swing.JCheckBox jCheckBox38;
    private javax.swing.JCheckBox jCheckBox39;
    private javax.swing.JCheckBox jCheckBox40;
    private javax.swing.JCheckBox jCheckBox41;
    private javax.swing.JCheckBox jCheckBox42;
    private javax.swing.JCheckBox jCheckBox9;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
