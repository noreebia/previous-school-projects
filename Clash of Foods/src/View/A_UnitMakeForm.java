/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package View;
import Model.*;
import Control.*;

/**
 *
 * @author 이상명'spc
 */
public class A_UnitMakeForm extends B_UnitMakeForm { //알촌 유닛생산 프레임입니다.

    private UnitControl um;
    private Player p;
    private Unit[] units;
    private Unit unit;
    private B_BasicForm bf;
    /**
     * Creates new form A_UnitMakeForm
     */
    public A_UnitMakeForm() {
        initComponents();
    }
    
    public A_UnitMakeForm(Player p,Unit[] u,B_BasicForm bf, UnitControl um){
        this.p = p;
        this.units = u;
        this.bf =bf;
        this.um = um;
        initComponents();
        modify();
        UnitInfo();
        btActionModi();
    }
    
    public void modify(){
        this.g1bt.setIcon(units[0].mark);
        this.g2bt.setIcon(units[1].mark);
        this.g3bt.setIcon(units[2].mark);
        this.l1bt.setIcon(units[3].mark);
        this.l2bt.setIcon(units[4].mark);
        this.l3bt.setIcon(units[5].mark);
        this.m1bt.setIcon(units[6].mark);
        this.m2bt.setIcon(units[7].mark);
        this.m3bt.setIcon(units[8].mark);
        g1name.setText(units[0].name);
        g2name.setText(units[1].name);
        g3name.setText(units[2].name);
        l1name.setText(units[3].name);
        l2name.setText(units[4].name);
        l3name.setText(units[5].name);
        m1name.setText(units[6].name);
        m2name.setText(units[7].name);
        m3name.setText(units[8].name);
        this.myMon.setText(Integer.toString(p.getPlayerMon()));
    }
    
    public void UnitInfo(){
        g1hp.setText(Integer.toString(units[0].health));
        g2hp.setText(Integer.toString(units[1].health));
        g3hp.setText(Integer.toString(units[2].health));
        l1hp.setText(Integer.toString(units[3].health));
        l2hp.setText(Integer.toString(units[4].health));
        l3hp.setText(Integer.toString(units[5].health));
        m1hp.setText(Integer.toString(units[6].health));
        m2hp.setText(Integer.toString(units[7].health));
        m3hp.setText(Integer.toString(units[8].health));
        g1dam.setText(Integer.toString(units[0].damage));
        g2dam.setText(Integer.toString(units[1].damage));
        g3dam.setText(Integer.toString(units[2].damage));
        l1dam.setText(Integer.toString(units[3].damage));
        l2dam.setText(Integer.toString(units[4].damage));
        l3dam.setText(Integer.toString(units[5].damage));
        m1dam.setText(Integer.toString(units[6].damage));
        m2dam.setText(Integer.toString(units[7].damage));
        m3dam.setText(Integer.toString(units[8].damage));
        g1mon.setText(Integer.toString(units[0].needMoney));
        g2mon.setText(Integer.toString(units[1].needMoney));
        g3mon.setText(Integer.toString(units[2].needMoney));
        l1mon.setText(Integer.toString(units[3].needMoney));
        l2mon.setText(Integer.toString(units[4].needMoney));
        l3mon.setText(Integer.toString(units[5].needMoney));
        m1mon.setText(Integer.toString(units[6].needMoney));
        m2mon.setText(Integer.toString(units[7].needMoney));
        m3mon.setText(Integer.toString(units[8].needMoney));
        g1t.setText(Integer.toString(units[0].needTurn));
        g2t.setText(Integer.toString(units[1].needTurn));
        g3t.setText(Integer.toString(units[2].needTurn));
        l1t.setText(Integer.toString(units[3].needTurn));
        l2t.setText(Integer.toString(units[4].needTurn));
        l3t.setText(Integer.toString(units[5].needTurn));
        m1t.setText(Integer.toString(units[6].needTurn));
        m2t.setText(Integer.toString(units[7].needTurn));
        m3t.setText(Integer.toString(units[8].needTurn));
    }
    
    public void dispose() { //종료시 실행되는 dispose를 오버라이딩해서, 종료할때 메인프레임의 돈부분을 새로고침해줍니다.
        bf.pMoneyLabel.setText(Integer.toString(p.getPlayerMon()));
        super.dispose();
    }
    
    public void btActionModi(){
        g1bt.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt){
                unit = new ADefender1();
                if(p.getPlayerMon()>=unit.needMoney){
                    p.setPlayerMon(p.getPlayerMon()-unit.needMoney);
                    UnitMakeActionPerformed(evt);
                    myMon.setText(Integer.toString(p.getPlayerMon()));
                }
                else{
                    MoneyErrorForm lowmoney = new MoneyErrorForm();
                    lowmoney.setVisible(true);
                }
            }
        });
        g2bt.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt){
                unit = new ADefender2();
                if(p.getPlayerMon()>=unit.needMoney){
                    p.setPlayerMon(p.getPlayerMon()-unit.needMoney);
                    UnitMakeActionPerformed(evt);
                    myMon.setText(Integer.toString(p.getPlayerMon()));
                }
                else{
                    MoneyErrorForm lowmoney = new MoneyErrorForm();
                    lowmoney.setVisible(true);
                }
            }
        });
        g3bt.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt){
                unit = new ADefender3();
                if(p.getPlayerMon()>=unit.needMoney){
                    p.setPlayerMon(p.getPlayerMon()-unit.needMoney);
                    UnitMakeActionPerformed(evt);
                    myMon.setText(Integer.toString(p.getPlayerMon()));
                }
                else{
                    MoneyErrorForm lowmoney = new MoneyErrorForm();
                    lowmoney.setVisible(true);
                }
            }
        });
        l1bt.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt){
                unit = new ALong1();
                if(p.getPlayerMon()>=unit.needMoney){
                    p.setPlayerMon(p.getPlayerMon()-unit.needMoney);
                    UnitMakeActionPerformed(evt);
                    myMon.setText(Integer.toString(p.getPlayerMon()));
                }
                else{
                    MoneyErrorForm lowmoney = new MoneyErrorForm();
                    lowmoney.setVisible(true);
                }
            }
        });
        l2bt.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt){
                unit = new ALong2();
                if(p.getPlayerMon()>=unit.needMoney){
                    p.setPlayerMon(p.getPlayerMon()-unit.needMoney);
                    UnitMakeActionPerformed(evt);
                    myMon.setText(Integer.toString(p.getPlayerMon()));
                }
                else{
                    MoneyErrorForm lowmoney = new MoneyErrorForm();
                    lowmoney.setVisible(true);
                }
            }
        });
        l3bt.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt){
                unit = new ALong3();
                if(p.getPlayerMon()>=unit.needMoney){
                    p.setPlayerMon(p.getPlayerMon()-unit.needMoney);
                    UnitMakeActionPerformed(evt);
                    myMon.setText(Integer.toString(p.getPlayerMon()));
                }
                else{
                    MoneyErrorForm lowmoney = new MoneyErrorForm();
                    lowmoney.setVisible(true);
                }
            }
        });
        m1bt.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt){
                unit = new AMagician1();
                if(p.getPlayerMon()>=unit.needMoney){
                    p.setPlayerMon(p.getPlayerMon()-unit.needMoney);
                    UnitMakeActionPerformed(evt);
                    myMon.setText(Integer.toString(p.getPlayerMon()));
                }
                else{
                    MoneyErrorForm lowmoney = new MoneyErrorForm();
                    lowmoney.setVisible(true);
                }
            }
        });
        m2bt.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt){
                unit = new AMagician2();
                if(p.getPlayerMon()>=unit.needMoney){
                    p.setPlayerMon(p.getPlayerMon()-unit.needMoney);
                    UnitMakeActionPerformed(evt);
                    myMon.setText(Integer.toString(p.getPlayerMon()));
                }
                else{
                    MoneyErrorForm lowmoney = new MoneyErrorForm();
                    lowmoney.setVisible(true);
                }
            }
        });
        m3bt.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt){
                unit = new AMagician3();
                if(p.getPlayerMon()>=unit.needMoney){
                    p.setPlayerMon(p.getPlayerMon()-unit.needMoney);
                    UnitMakeActionPerformed(evt);
                    myMon.setText(Integer.toString(p.getPlayerMon()));
                }
                else{
                    MoneyErrorForm lowmoney = new MoneyErrorForm();
                    lowmoney.setVisible(true);
                }
            }
        });
    }
    
    private void UnitMakeActionPerformed(java.awt.event.ActionEvent evt){
        um.makeUnit(unit);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 680, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 680, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
