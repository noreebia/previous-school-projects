/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package View;
import Model.*;
import Control.*;
import java.awt.Color;
import javax.swing.ImageIcon;

/**
 *
 * @author 이상명'spc
 */
public class B_BasicForm extends javax.swing.JFrame { //기본 메인프레임

    protected Player p;
    protected UnitControl umc;
    protected Unit unit;
    protected Unit fixedunit;
    protected UnitInfoFrame uif;
    public GameThread thread;
    public GameEnd gg;
    protected MoveControl movec = new MoveControl();
    protected AttackControl ac = new AttackControl();
    //protected boolean isOuted = true;
    /**
     * Creates new form NewJFrame
     */
    public B_BasicForm() {
        initComponents();
    }
    
    public UnitControl getUMC()
    {
        return this.umc;
    }
    
    public void isDead()
    {
        for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < 4; j++)
            {
                if(p.fieldUnit[i][j] != null)
                {
                    if(p.fieldUnit[i][j].health <= 0)
                    {
                        p.fieldUnit[i][j] = null;
                    }
                }
                
                if(p.e.enemyFieldUnit[i][j] != null)
                {
                     if(p.e.enemyFieldUnit[i][j].health <= 0)
                    {
                        p.e.enemyFieldUnit[i][j] = null;
                    }
                }
            }
        }
    }
    
    public void enemySetting(){
        efield1_1.setEnabled(false);
        efield1_2.setEnabled(false);
        efield1_3.setEnabled(false);
        efield1_4.setEnabled(false);
        efield2_1.setEnabled(false);
        efield2_2.setEnabled(false);
        efield2_3.setEnabled(false);
        efield2_4.setEnabled(false);
        efield3_1.setEnabled(false);
        efield3_2.setEnabled(false);
        efield3_3.setEnabled(false);
        efield3_4.setEnabled(false);
        EnemyButton.setEnabled(false);
        if(p.e.enemyFieldUnit[0][0]!=null){
            efield1_1.setIcon(p.e.enemyFieldUnit[0][0].mark);
        }
        else{
            efield1_1.setIcon(null);
        }
        if(p.e.enemyFieldUnit[0][1]!=null){
            efield1_2.setIcon(p.e.enemyFieldUnit[0][1].mark);
        }
        else{
            efield1_2.setIcon(null);
        }
        if(p.e.enemyFieldUnit[0][2]!=null){
            efield1_3.setIcon(p.e.enemyFieldUnit[0][2].mark);
        }
        else{
            efield1_3.setIcon(null);
        }
        if(p.e.enemyFieldUnit[0][3]!=null){
            efield1_4.setIcon(p.e.enemyFieldUnit[0][3].mark);
        }
        else{
            efield1_4.setIcon(null);
        }
        if(p.e.enemyFieldUnit[1][0]!=null){
            efield2_1.setIcon(p.e.enemyFieldUnit[1][0].mark);
        }
        else{
            efield2_1.setIcon(null);
        }
        if(p.e.enemyFieldUnit[1][1]!=null){
            efield2_2.setIcon(p.e.enemyFieldUnit[1][1].mark);
        }
        else{
            efield2_2.setIcon(null);
        }
        if(p.e.enemyFieldUnit[1][2]!=null){
            efield2_3.setIcon(p.e.enemyFieldUnit[1][2].mark);
        }
        else{
            efield2_3.setIcon(null);
        }
        if(p.e.enemyFieldUnit[1][3]!=null){
            efield2_4.setIcon(p.e.enemyFieldUnit[1][3].mark);
        }
        else{
            efield2_4.setIcon(null);
        }
        if(p.e.enemyFieldUnit[2][0]!=null){
            efield3_1.setIcon(p.e.enemyFieldUnit[2][0].mark);
        }
        else{
            efield3_1.setIcon(null);
        }
        if(p.e.enemyFieldUnit[2][1]!=null){
            efield3_2.setIcon(p.e.enemyFieldUnit[2][1].mark);
        }
        else{
            efield3_2.setIcon(null);
        }
        if(p.e.enemyFieldUnit[2][2]!=null){
            efield3_3.setIcon(p.e.enemyFieldUnit[2][2].mark);
        }
        else{
            efield3_3.setIcon(null);
        }
        if(p.e.enemyFieldUnit[2][3]!=null){
            efield3_4.setIcon(p.e.enemyFieldUnit[2][3].mark);
        }
        else{
            efield3_4.setIcon(null);
        }
        if(p.e.esp instanceof Alchon){
            ImageIcon espImage = new ImageIcon(getClass().getResource("/image/알촌로고.jpg"));
            EnemyButton.setIcon(espImage);
        }
        else if(p.e.esp instanceof Isaac){
            ImageIcon espImage = new ImageIcon(getClass().getResource("/image/이삭로고.jpg"));
            EnemyButton.setIcon(espImage);
        }
        else if(p.e.esp instanceof JJangDon){
            ImageIcon espImage = new ImageIcon(getClass().getResource("/image/짱돈로고.jpg"));
            EnemyButton.setIcon(espImage);
        }
    }
    
    public void enemyUpdate(){
        if(p.e.enemyFieldUnit[0][0]!=null){
            efield1_1.setIcon(p.e.enemyFieldUnit[0][0].mark);
        }
        else{
            efield1_1.setIcon(null);
        }
        if(p.e.enemyFieldUnit[0][1]!=null){
            efield1_2.setIcon(p.e.enemyFieldUnit[0][1].mark);
        }
        else{
            efield1_2.setIcon(null);
        }
        if(p.e.enemyFieldUnit[0][2]!=null){
            efield1_3.setIcon(p.e.enemyFieldUnit[0][2].mark);
        }
        else{
            efield1_3.setIcon(null);
        }
        if(p.e.enemyFieldUnit[0][3]!=null){
            efield1_4.setIcon(p.e.enemyFieldUnit[0][3].mark);
        }
        else{
            efield1_4.setIcon(null);
        }
        if(p.e.enemyFieldUnit[1][0]!=null){
            efield2_1.setIcon(p.e.enemyFieldUnit[1][0].mark);
        }
        else{
            efield2_1.setIcon(null);
        }
        if(p.e.enemyFieldUnit[1][1]!=null){
            efield2_2.setIcon(p.e.enemyFieldUnit[1][1].mark);
        }
        else{
            efield2_2.setIcon(null);
        }
        if(p.e.enemyFieldUnit[1][2]!=null){
            efield2_3.setIcon(p.e.enemyFieldUnit[1][2].mark);
        }
        else{
            efield2_3.setIcon(null);
        }
        if(p.e.enemyFieldUnit[1][3]!=null){
            efield2_4.setIcon(p.e.enemyFieldUnit[1][3].mark);
        }
        else{
            efield2_4.setIcon(null);
        }
        if(p.e.enemyFieldUnit[2][0]!=null){
            efield3_1.setIcon(p.e.enemyFieldUnit[2][0].mark);
        }
        else{
            efield3_1.setIcon(null);
        }
        if(p.e.enemyFieldUnit[2][1]!=null){
            efield3_2.setIcon(p.e.enemyFieldUnit[2][1].mark);
        }
        else{
            efield3_2.setIcon(null);
        }
        if(p.e.enemyFieldUnit[2][2]!=null){
            efield3_3.setIcon(p.e.enemyFieldUnit[2][2].mark);
        }
        else{
            efield3_3.setIcon(null);
        }
        if(p.e.enemyFieldUnit[2][3]!=null){
            efield3_4.setIcon(p.e.enemyFieldUnit[2][3].mark);
        }
        else{
            efield3_4.setIcon(null);
        }
    }
    
    public void mySetting(){
        if(p.fieldUnit[0][0]!=null){
            field1_1.setEnabled(true);
            field1_1.setIcon(p.fieldUnit[0][0].mark);
        }
        else{
            field1_1.setEnabled(false);
            field1_1.setIcon(null);
        }
        if(p.fieldUnit[0][1]!=null){
            field1_2.setEnabled(true);
            field1_2.setIcon(p.fieldUnit[0][1].mark);
        }
        else{
            field1_2.setEnabled(false);
            field1_2.setIcon(null);
        }
        if(p.fieldUnit[0][2]!=null){
            field1_3.setEnabled(true);
            field1_3.setIcon(p.fieldUnit[0][2].mark);
        }
        else{
            field1_3.setEnabled(false);
            field1_3.setIcon(null);
        }
        if(p.fieldUnit[0][3]!=null){
            field1_4.setEnabled(true);
            field1_4.setIcon(p.fieldUnit[0][3].mark);
        }
        else{
            field1_4.setEnabled(false);
            field1_4.setIcon(null);
        }
        if(p.fieldUnit[1][0]!=null){
            field2_1.setEnabled(true);
            field2_1.setIcon(p.fieldUnit[1][0].mark);
        }
        else{
            field2_1.setEnabled(false);
            field2_1.setIcon(null);
        }
        if(p.fieldUnit[1][1]!=null){
            field2_2.setEnabled(true);
            field2_2.setIcon(p.fieldUnit[1][1].mark);
        }
        else{
            field2_2.setEnabled(false);
            field2_2.setIcon(null);
        }
        if(p.fieldUnit[1][2]!=null){
            field2_3.setEnabled(true);
            field2_3.setIcon(p.fieldUnit[1][2].mark);
        }
        else{
            field2_3.setEnabled(false);
            field2_3.setIcon(null);
        }
        if(p.fieldUnit[1][3]!=null){
            field2_4.setEnabled(true);
            field2_4.setIcon(p.fieldUnit[1][3].mark);
        }
        else{
            field2_4.setEnabled(false);
            field2_4.setIcon(null);
        }
        if(p.fieldUnit[2][0]!=null){
            field3_1.setEnabled(true);
            field3_1.setIcon(p.fieldUnit[2][0].mark);
        }
        else{
            field3_1.setEnabled(false);
            field3_1.setIcon(null);
        }
        if(p.fieldUnit[2][1]!=null){
            field3_2.setEnabled(true);
            field3_2.setIcon(p.fieldUnit[2][1].mark);
        }
        else{
            field3_2.setEnabled(false);
            field3_2.setIcon(null);
        }
        if(p.fieldUnit[2][2]!=null){
            field3_3.setEnabled(true);
            field3_3.setIcon(p.fieldUnit[2][2].mark);
        }
        else{
            field3_3.setEnabled(false);
            field3_3.setIcon(null);
        }
        if(p.fieldUnit[2][3]!=null){
            field3_4.setEnabled(true);
            field3_4.setIcon(p.fieldUnit[2][3].mark);
        }
        else{
            field3_4.setEnabled(false);
            field3_4.setIcon(null);
        }
    }

    public void synchronization()
    {
        if(gg.isDefeat()){
            thread.SendCommand("5");
            p.isEnd = 2;
            gg.isEnd();
        }
        else{
        }
        this.pMoneyLabel.setText(Integer.toString(p.getPlayerMon()));
        this.pHpLabel.setText(Integer.toString(p.getPlayerHp()));
        if(p.turn % 2 == 1)
        {
            unitAttack.setEnabled(false);
            unitMove.setEnabled(false);
            unitListBt.setEnabled(false);
            unitMakeBt.setEnabled(false);
            endTurnBt.setEnabled(false);
            surrenderBt.setEnabled(false);
            
        }
        else
        {
            unitAttack.setEnabled(true);
            unitMove.setEnabled(true);
            unitListBt.setEnabled(true);
            unitMakeBt.setEnabled(true);
            endTurnBt.setEnabled(true);
            surrenderBt.setEnabled(true);
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        unitPop = new javax.swing.JPopupMenu();
        unitInfo = new javax.swing.JMenuItem();
        unitAttack = new javax.swing.JMenuItem();
        unitMove = new javax.swing.JMenuItem();
        enemyPop = new javax.swing.JPopupMenu();
        eName = new javax.swing.JMenuItem();
        eLife = new javax.swing.JMenuItem();
        eDam = new javax.swing.JMenuItem();
        jPanel2 = new javax.swing.JPanel();
        efield3_1 = new javax.swing.JButton();
        efield2_1 = new javax.swing.JButton();
        efield1_1 = new javax.swing.JButton();
        efield3_2 = new javax.swing.JButton();
        efield1_2 = new javax.swing.JButton();
        efield3_3 = new javax.swing.JButton();
        efield2_3 = new javax.swing.JButton();
        efield1_3 = new javax.swing.JButton();
        efield2_2 = new javax.swing.JButton();
        efield3_4 = new javax.swing.JButton();
        efield2_4 = new javax.swing.JButton();
        efield1_4 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        spImagePane = new javax.swing.JPanel();
        spImageLabel = new javax.swing.JLabel();
        unitMakeBt = new javax.swing.JButton();
        unitListBt = new javax.swing.JButton();
        surrenderBt = new javax.swing.JButton();
        endTurnBt = new javax.swing.JButton();
        spName = new javax.swing.JTextField();
        pMoneyLabel = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        pHpLabel = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        field1_1 = new javax.swing.JButton();
        field2_1 = new javax.swing.JButton();
        field3_1 = new javax.swing.JButton();
        field1_2 = new javax.swing.JButton();
        field3_2 = new javax.swing.JButton();
        field1_3 = new javax.swing.JButton();
        field2_3 = new javax.swing.JButton();
        field3_3 = new javax.swing.JButton();
        field2_2 = new javax.swing.JButton();
        field1_4 = new javax.swing.JButton();
        field2_4 = new javax.swing.JButton();
        field3_4 = new javax.swing.JButton();
        EnemyButton = new javax.swing.JButton();

        unitPop.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                unitPopMouseEntered(evt);
            }
        });

        unitInfo.setText("유닛정보");
        unitInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unitInfoActionPerformed(evt);
            }
        });
        unitPop.add(unitInfo);

        unitAttack.setText("공격");
        unitAttack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unitAttackActionPerformed(evt);
            }
        });
        unitPop.add(unitAttack);

        unitMove.setText("이동");
        unitMove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unitMoveActionPerformed(evt);
            }
        });
        unitPop.add(unitMove);

        eName.setText("jMenuItem1");
        enemyPop.add(eName);

        eLife.setText("jMenuItem2");
        enemyPop.add(eLife);

        eDam.setText("jMenuItem3");
        enemyPop.add(eDam);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                formMouseEntered(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel2MouseEntered(evt);
            }
        });

        efield3_1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                efield3_1MouseEntered(evt);
            }
        });
        efield3_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                efield3_1ActionPerformed(evt);
            }
        });

        efield2_1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                efield2_1MouseEntered(evt);
            }
        });
        efield2_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                efield2_1ActionPerformed(evt);
            }
        });

        efield1_1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                efield1_1MouseEntered(evt);
            }
        });
        efield1_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                efield1_1ActionPerformed(evt);
            }
        });

        efield3_2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                efield3_2MouseEntered(evt);
            }
        });
        efield3_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                efield3_2ActionPerformed(evt);
            }
        });

        efield1_2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                efield1_2MouseEntered(evt);
            }
        });
        efield1_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                efield1_2ActionPerformed(evt);
            }
        });

        efield3_3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                efield3_3MouseEntered(evt);
            }
        });
        efield3_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                efield3_3ActionPerformed(evt);
            }
        });

        efield2_3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                efield2_3MouseEntered(evt);
            }
        });
        efield2_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                efield2_3ActionPerformed(evt);
            }
        });

        efield1_3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                efield1_3MouseEntered(evt);
            }
        });
        efield1_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                efield1_3ActionPerformed(evt);
            }
        });

        efield2_2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                efield2_2MouseEntered(evt);
            }
        });
        efield2_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                efield2_2ActionPerformed(evt);
            }
        });

        efield3_4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                efield3_4MouseEntered(evt);
            }
        });
        efield3_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                efield3_4ActionPerformed(evt);
            }
        });

        efield2_4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                efield2_4MouseEntered(evt);
            }
        });
        efield2_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                efield2_4ActionPerformed(evt);
            }
        });

        efield1_4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                efield1_4MouseEntered(evt);
            }
        });
        efield1_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                efield1_4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(efield1_1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(efield1_2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(efield1_3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(efield1_4, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(efield3_1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(efield2_1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(efield3_2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(efield2_2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(efield2_3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(efield2_4, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(efield3_3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(efield3_4, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(75, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(efield3_1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(efield3_2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(efield3_3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(efield3_4, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(efield2_1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(efield2_3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(efield2_4, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(3, 3, 3)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(efield1_1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(efield1_3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(efield1_4, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(efield2_2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(efield1_2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(3, 3, 3))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51), 3));

        spImagePane.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout spImagePaneLayout = new javax.swing.GroupLayout(spImagePane);
        spImagePane.setLayout(spImagePaneLayout);
        spImagePaneLayout.setHorizontalGroup(
            spImagePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 84, Short.MAX_VALUE)
            .addGroup(spImagePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(spImagePaneLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(spImageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        spImagePaneLayout.setVerticalGroup(
            spImagePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 87, Short.MAX_VALUE)
            .addGroup(spImagePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(spImagePaneLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(spImageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        unitMakeBt.setText("생산하기");
        unitMakeBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unitMakeBtActionPerformed(evt);
            }
        });

        unitListBt.setText("유닛관리");
        unitListBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unitListBtActionPerformed(evt);
            }
        });

        surrenderBt.setText("항복");
        surrenderBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                surrenderBtActionPerformed(evt);
            }
        });

        endTurnBt.setText("턴넘기기");
        endTurnBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                endTurnBtActionPerformed(evt);
            }
        });

        spName.setEditable(false);
        spName.setText("jTextField2");

        pMoneyLabel.setEditable(false);
        pMoneyLabel.setText("500");
        pMoneyLabel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pMoneyLabelActionPerformed(evt);
            }
        });

        jLabel1.setText("종족 : ");

        jLabel2.setText("소지금 : ");

        jLabel3.setText("포만도 :");

        pHpLabel.setEditable(false);
        pHpLabel.setText("0");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(spImagePane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(spName)
                    .addComponent(pMoneyLabel)
                    .addComponent(pHpLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(unitMakeBt)
                    .addComponent(endTurnBt))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(unitListBt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(surrenderBt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(144, 144, 144))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(unitListBt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(spName, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(unitMakeBt))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(pMoneyLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(endTurnBt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(surrenderBt, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(2, 2, 2)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(pHpLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)))
                    .addComponent(spImagePane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        field1_1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                field1_1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                field1_1MouseExited(evt);
            }
        });
        field1_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                field1_1ActionPerformed(evt);
            }
        });

        field2_1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                field2_1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                field2_1MouseExited(evt);
            }
        });

        field3_1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                field3_1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                field3_1MouseExited(evt);
            }
        });

        field1_2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                field1_2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                field1_2MouseExited(evt);
            }
        });

        field3_2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                field3_2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                field3_2MouseExited(evt);
            }
        });

        field1_3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                field1_3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                field1_3MouseExited(evt);
            }
        });

        field2_3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                field2_3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                field2_3MouseExited(evt);
            }
        });

        field3_3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                field3_3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                field3_3MouseExited(evt);
            }
        });
        field3_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                field3_3ActionPerformed(evt);
            }
        });

        field2_2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                field2_2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                field2_2MouseExited(evt);
            }
        });

        field1_4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                field1_4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                field1_4MouseExited(evt);
            }
        });

        field2_4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                field2_4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                field2_4MouseExited(evt);
            }
        });

        field3_4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                field3_4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                field3_4MouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(field3_1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(field3_2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(field3_3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(field3_4, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(field1_1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(field2_1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(field1_2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(field2_2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(field2_3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(field2_4, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(field1_3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(field1_4, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(75, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(field1_1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(field1_2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(field1_3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(field1_4, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(field2_2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(field3_2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(field2_1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(field2_3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(field2_4, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(field3_3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(field3_4, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(field3_1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))))))
        );

        EnemyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EnemyButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(248, 248, 248)
                        .addComponent(EnemyButton, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(EnemyButton, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void pMoneyLabelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pMoneyLabelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pMoneyLabelActionPerformed

    private void unitMakeBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_unitMakeBtActionPerformed
        umc.UnitMakeForming(p,this);
    }//GEN-LAST:event_unitMakeBtActionPerformed

    private void unitListBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_unitListBtActionPerformed
        umc.UnitListForming(p,this,thread);
    }//GEN-LAST:event_unitListBtActionPerformed

    private void field3_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_field3_3ActionPerformed

    }//GEN-LAST:event_field3_3ActionPerformed

    private void field1_1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_field1_1MouseEntered
        if(field1_1.isEnabled()){
            unit = p.fieldUnit[0][0];
            unitPop.show(field1_1, 10,10 );
        }
    }//GEN-LAST:event_field1_1MouseEntered

    private void field1_1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_field1_1MouseExited

    }//GEN-LAST:event_field1_1MouseExited

    private void field1_2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_field1_2MouseEntered
        if(field1_2.isEnabled()){
            unit = p.fieldUnit[0][1];
            unitPop.show(field1_2, 10,10 );
        }
    }//GEN-LAST:event_field1_2MouseEntered

    private void field1_2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_field1_2MouseExited

    }//GEN-LAST:event_field1_2MouseExited

    private void field1_3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_field1_3MouseEntered
        if(field1_3.isEnabled()){
            unit = p.fieldUnit[0][2];
            unitPop.show(field1_3, 10,10 );
        }
    }//GEN-LAST:event_field1_3MouseEntered

    private void field1_3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_field1_3MouseExited

    }//GEN-LAST:event_field1_3MouseExited

    private void field1_4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_field1_4MouseEntered
        if(field1_4.isEnabled()){
            unit = p.fieldUnit[0][3];
            unitPop.show(field1_4, 10,10 );
        }
    }//GEN-LAST:event_field1_4MouseEntered

    private void field1_4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_field1_4MouseExited
 
    }//GEN-LAST:event_field1_4MouseExited

    private void field2_1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_field2_1MouseEntered
        if(field2_1.isEnabled()){
            unit = p.fieldUnit[1][0];
            unitPop.show(field2_1, 10,10 );
        }
    }//GEN-LAST:event_field2_1MouseEntered

    private void field2_1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_field2_1MouseExited

    }//GEN-LAST:event_field2_1MouseExited

    private void field2_2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_field2_2MouseEntered
        if(field2_2.isEnabled()){
            unit = p.fieldUnit[1][1];
            unitPop.show(field2_2, 10,10 );
        }
    }//GEN-LAST:event_field2_2MouseEntered

    private void field2_2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_field2_2MouseExited

    }//GEN-LAST:event_field2_2MouseExited

    private void field2_3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_field2_3MouseEntered
        if(field2_3.isEnabled()){
            unit = p.fieldUnit[1][2];
            unitPop.show(field2_3, 10,10 );
        }
    }//GEN-LAST:event_field2_3MouseEntered

    private void field2_3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_field2_3MouseExited

    }//GEN-LAST:event_field2_3MouseExited

    private void field2_4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_field2_4MouseEntered
        if(field2_4.isEnabled()){
            unit = p.fieldUnit[1][3];
            unitPop.show(field2_4, 10,10 );
        }
    }//GEN-LAST:event_field2_4MouseEntered

    private void field2_4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_field2_4MouseExited

    }//GEN-LAST:event_field2_4MouseExited

    private void field3_1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_field3_1MouseEntered
        if(field3_1.isEnabled()){
            unit = p.fieldUnit[2][0];
            unitPop.show(field3_1, 10,10 );
        }
    }//GEN-LAST:event_field3_1MouseEntered

    private void field3_1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_field3_1MouseExited

    }//GEN-LAST:event_field3_1MouseExited

    private void field3_2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_field3_2MouseEntered
        if(field3_2.isEnabled()){
            unit = p.fieldUnit[2][1];
            unitPop.show(field3_2, 10,10 );
        }
    }//GEN-LAST:event_field3_2MouseEntered

    private void field3_2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_field3_2MouseExited

    }//GEN-LAST:event_field3_2MouseExited

    private void field3_3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_field3_3MouseEntered
        if(field3_3.isEnabled()){
            unit = p.fieldUnit[2][2];
            unitPop.show(field3_3, 10,10 );
        }
    }//GEN-LAST:event_field3_3MouseEntered

    private void field3_3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_field3_3MouseExited

    }//GEN-LAST:event_field3_3MouseExited

    private void field3_4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_field3_4MouseEntered
        if(field3_4.isEnabled()){
            unit = p.fieldUnit[2][3];
            unitPop.show(field3_4, 10,10 );
        }
    }//GEN-LAST:event_field3_4MouseEntered

    private void field3_4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_field3_4MouseExited

    }//GEN-LAST:event_field3_4MouseExited

    private void unitPopMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_unitPopMouseEntered

    }//GEN-LAST:event_unitPopMouseEntered

    private void unitAttackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_unitAttackActionPerformed
        fixedunit = unit;
        ac.attack(this,fixedunit,p);
        unitPop.getInvoker().setEnabled(false);
        isDead();
        enemyUpdate();
    }//GEN-LAST:event_unitAttackActionPerformed

    private void unitInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_unitInfoActionPerformed
        uif = new UnitInfoFrame(unit);
        uif.setVisible(true);
    }//GEN-LAST:event_unitInfoActionPerformed

    private void unitMoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_unitMoveActionPerformed
        fixedunit = unit;
        movec.move(this,fixedunit,p);
    }//GEN-LAST:event_unitMoveActionPerformed

    private void efield1_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_efield1_4ActionPerformed
        thread.SendCommand("2o03"+fixedunit.damage);
        p.e.enemyFieldUnit[0][3].health -= fixedunit.damage;
        isDead();
        enemySetting();
    }//GEN-LAST:event_efield1_4ActionPerformed

    private void efield1_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_efield1_3ActionPerformed
        thread.SendCommand("2o02"+fixedunit.damage);
        p.e.enemyFieldUnit[0][2].health -= fixedunit.damage;
        isDead();
        enemySetting();
    }//GEN-LAST:event_efield1_3ActionPerformed

    private void efield1_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_efield1_2ActionPerformed
        thread.SendCommand("2o01"+fixedunit.damage);
        p.e.enemyFieldUnit[0][1].health -= fixedunit.damage;
        isDead();
        enemySetting();
    }//GEN-LAST:event_efield1_2ActionPerformed

    private void efield1_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_efield1_1ActionPerformed
        thread.SendCommand("2o00"+fixedunit.damage);
        p.e.enemyFieldUnit[0][0].health -= fixedunit.damage;
        isDead();
        enemySetting();
    }//GEN-LAST:event_efield1_1ActionPerformed

    private void efield2_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_efield2_4ActionPerformed
        thread.SendCommand("2o13"+fixedunit.damage);
        p.e.enemyFieldUnit[1][3].health -= fixedunit.damage;
        isDead();
        enemySetting();
    }//GEN-LAST:event_efield2_4ActionPerformed

    private void efield2_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_efield2_3ActionPerformed
        thread.SendCommand("2o12"+fixedunit.damage);
        p.e.enemyFieldUnit[1][2].health -= fixedunit.damage;
        isDead();
        enemySetting();
    }//GEN-LAST:event_efield2_3ActionPerformed

    private void efield2_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_efield2_2ActionPerformed
        thread.SendCommand("2o11"+fixedunit.damage);
        p.e.enemyFieldUnit[1][1].health -= fixedunit.damage;
        isDead();
        enemySetting();
    }//GEN-LAST:event_efield2_2ActionPerformed

    private void efield2_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_efield2_1ActionPerformed
        thread.SendCommand("2o10"+fixedunit.damage);
        p.e.enemyFieldUnit[1][0].health -= fixedunit.damage;
        isDead();
        enemySetting();
    }//GEN-LAST:event_efield2_1ActionPerformed

    private void efield3_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_efield3_4ActionPerformed
        thread.SendCommand("2o23"+fixedunit.damage);
        p.e.enemyFieldUnit[2][3].health -= fixedunit.damage;
        isDead();
        enemySetting();
    }//GEN-LAST:event_efield3_4ActionPerformed

    private void efield3_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_efield3_3ActionPerformed
        thread.SendCommand("2o22"+fixedunit.damage);
        p.e.enemyFieldUnit[2][2].health -= fixedunit.damage;
        isDead();
        enemySetting();
    }//GEN-LAST:event_efield3_3ActionPerformed

    private void efield3_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_efield3_2ActionPerformed
        thread.SendCommand("2o21"+fixedunit.damage);
        p.e.enemyFieldUnit[2][1].health -= fixedunit.damage;
        isDead();
        enemySetting();
    }//GEN-LAST:event_efield3_2ActionPerformed

    private void efield3_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_efield3_1ActionPerformed
        thread.SendCommand("2o20"+fixedunit.damage);
        p.e.enemyFieldUnit[2][0].health -= fixedunit.damage;
        isDead();
        enemySetting();
    }//GEN-LAST:event_efield3_1ActionPerformed

    private void EnemyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EnemyButtonActionPerformed
        thread.SendCommand("2c"+fixedunit.damage);
        p.e.Life += fixedunit.damage;
        enemySetting();
    }//GEN-LAST:event_EnemyButtonActionPerformed

    private void endTurnBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_endTurnBtActionPerformed
        p.turn++;
        umc.sendUnit();
        isDead();
        enemySetting();
        mySetting();
        synchronization();
        thread.SendCommand("4");
    }//GEN-LAST:event_endTurnBtActionPerformed

    private void formMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseEntered

    }//GEN-LAST:event_formMouseEntered

    private void jPanel2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel2MouseEntered

    private void field1_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_field1_1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_field1_1ActionPerformed

    private void surrenderBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_surrenderBtActionPerformed
        thread.SendCommand("5");
        p.isEnd = 2;
        gg.isEnd();
    }//GEN-LAST:event_surrenderBtActionPerformed

    private void efield1_1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_efield1_1MouseEntered
        if(p.e.enemyFieldUnit[0][0]!=null && !efield1_1.isEnabled()){
            unit = p.e.enemyFieldUnit[0][0];
            eName.setText(unit.name);
            eLife.setText("현재체력 : "+Integer.toString(unit.health));
            eDam.setText("데미지 : "+Integer.toString(unit.damage));
            enemyPop.show(efield1_1, 10,10 );
        }
    }//GEN-LAST:event_efield1_1MouseEntered

    private void efield1_2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_efield1_2MouseEntered
        if(p.e.enemyFieldUnit[0][1]!=null && !efield1_2.isEnabled()){
            unit = p.e.enemyFieldUnit[0][1];
            eName.setText(unit.name);
            eLife.setText("현재체력 : "+Integer.toString(unit.health));
            eDam.setText("데미지 : "+Integer.toString(unit.damage));
            enemyPop.show(efield1_2, 10,10 );
        }
    }//GEN-LAST:event_efield1_2MouseEntered

    private void efield1_3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_efield1_3MouseEntered
        if(p.e.enemyFieldUnit[0][2]!=null && !efield1_3.isEnabled()){
            unit = p.e.enemyFieldUnit[0][2];
            eName.setText(unit.name);
            eLife.setText("현재체력 : "+Integer.toString(unit.health));
            eDam.setText("데미지 : "+Integer.toString(unit.damage));
            enemyPop.show(efield1_3, 10,10 );
        }
    }//GEN-LAST:event_efield1_3MouseEntered

    private void efield1_4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_efield1_4MouseEntered
        if(p.e.enemyFieldUnit[0][3]!=null && !efield1_4.isEnabled()){
            unit = p.e.enemyFieldUnit[0][3];
            eName.setText(unit.name);
            eLife.setText("현재체력 : "+Integer.toString(unit.health));
            eDam.setText("데미지 : "+Integer.toString(unit.damage));
            enemyPop.show(efield1_4, 10,10 );
        }
    }//GEN-LAST:event_efield1_4MouseEntered

    private void efield2_1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_efield2_1MouseEntered
        if(p.e.enemyFieldUnit[1][0]!=null && !efield2_1.isEnabled()){
            unit = p.e.enemyFieldUnit[1][0];
            eName.setText(unit.name);
            eLife.setText("현재체력 : "+Integer.toString(unit.health));
            eDam.setText("데미지 : "+Integer.toString(unit.damage));
            enemyPop.show(efield2_1, 10,10 );
        }
    }//GEN-LAST:event_efield2_1MouseEntered

    private void efield2_2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_efield2_2MouseEntered
        if(p.e.enemyFieldUnit[1][1]!=null && !efield2_2.isEnabled()){
            unit = p.e.enemyFieldUnit[1][1];
            eName.setText(unit.name);
            eLife.setText("현재체력 : "+Integer.toString(unit.health));
            eDam.setText("데미지 : "+Integer.toString(unit.damage));
            enemyPop.show(efield2_2, 10,10 );
        }
    }//GEN-LAST:event_efield2_2MouseEntered

    private void efield2_3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_efield2_3MouseEntered
        if(p.e.enemyFieldUnit[1][2]!=null && !efield2_3.isEnabled()){
            unit = p.e.enemyFieldUnit[1][2];
            eName.setText(unit.name);
            eLife.setText("현재체력 : "+Integer.toString(unit.health));
            eDam.setText("데미지 : "+Integer.toString(unit.damage));
            enemyPop.show(efield2_3, 10,10 );
        }
    }//GEN-LAST:event_efield2_3MouseEntered

    private void efield2_4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_efield2_4MouseEntered
        if(p.e.enemyFieldUnit[1][3]!=null && !efield2_4.isEnabled()){
            unit = p.e.enemyFieldUnit[1][3];
            eName.setText(unit.name);
            eLife.setText("현재체력 : "+Integer.toString(unit.health));
            eDam.setText("데미지 : "+Integer.toString(unit.damage));
            enemyPop.show(efield2_4, 10,10 );
        }
    }//GEN-LAST:event_efield2_4MouseEntered

    private void efield3_1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_efield3_1MouseEntered
        if(p.e.enemyFieldUnit[2][0]!=null && !efield3_1.isEnabled()){
            unit = p.e.enemyFieldUnit[2][0];
            eName.setText(unit.name);
            eLife.setText("현재체력 : "+Integer.toString(unit.health));
            eDam.setText("데미지 : "+Integer.toString(unit.damage));
            enemyPop.show(efield3_1, 10,10 );
        }
    }//GEN-LAST:event_efield3_1MouseEntered

    private void efield3_2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_efield3_2MouseEntered
        if(p.e.enemyFieldUnit[2][1]!=null && !efield3_2.isEnabled()){
            unit = p.e.enemyFieldUnit[2][1];
            eName.setText(unit.name);
            eLife.setText("현재체력 : "+Integer.toString(unit.health));
            eDam.setText("데미지 : "+Integer.toString(unit.damage));
            enemyPop.show(efield3_2, 10,10 );
        }
    }//GEN-LAST:event_efield3_2MouseEntered

    private void efield3_3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_efield3_3MouseEntered
        if(p.e.enemyFieldUnit[2][2]!=null && !efield3_3.isEnabled()){
            unit = p.e.enemyFieldUnit[2][2];
            eName.setText(unit.name);
            eLife.setText("현재체력 : "+Integer.toString(unit.health));
            eDam.setText("데미지 : "+Integer.toString(unit.damage));
            enemyPop.show(efield3_3, 10,10 );
        }
    }//GEN-LAST:event_efield3_3MouseEntered

    private void efield3_4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_efield3_4MouseEntered
        if(p.e.enemyFieldUnit[2][3]!=null && !efield3_4.isEnabled()){
            unit = p.e.enemyFieldUnit[2][3];
            eName.setText(unit.name);
            eLife.setText("현재체력 : "+Integer.toString(unit.health));
            eDam.setText("데미지 : "+Integer.toString(unit.damage));
            enemyPop.show(efield3_4, 10,10 );
        }
    }//GEN-LAST:event_efield3_4MouseEntered

    /**     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton EnemyButton;
    private javax.swing.JMenuItem eDam;
    private javax.swing.JMenuItem eLife;
    private javax.swing.JMenuItem eName;
    public javax.swing.JButton efield1_1;
    public javax.swing.JButton efield1_2;
    public javax.swing.JButton efield1_3;
    public javax.swing.JButton efield1_4;
    public javax.swing.JButton efield2_1;
    public javax.swing.JButton efield2_2;
    public javax.swing.JButton efield2_3;
    public javax.swing.JButton efield2_4;
    public javax.swing.JButton efield3_1;
    public javax.swing.JButton efield3_2;
    public javax.swing.JButton efield3_3;
    public javax.swing.JButton efield3_4;
    public javax.swing.JButton endTurnBt;
    private javax.swing.JPopupMenu enemyPop;
    public javax.swing.JButton field1_1;
    public javax.swing.JButton field1_2;
    public javax.swing.JButton field1_3;
    public javax.swing.JButton field1_4;
    public javax.swing.JButton field2_1;
    public javax.swing.JButton field2_2;
    public javax.swing.JButton field2_3;
    public javax.swing.JButton field2_4;
    public javax.swing.JButton field3_1;
    public javax.swing.JButton field3_2;
    public javax.swing.JButton field3_3;
    public javax.swing.JButton field3_4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    public javax.swing.JTextField pHpLabel;
    public javax.swing.JTextField pMoneyLabel;
    public javax.swing.JLabel spImageLabel;
    public javax.swing.JPanel spImagePane;
    public javax.swing.JTextField spName;
    public javax.swing.JButton surrenderBt;
    private javax.swing.JMenuItem unitAttack;
    private javax.swing.JMenuItem unitInfo;
    public javax.swing.JButton unitListBt;
    public javax.swing.JButton unitMakeBt;
    private javax.swing.JMenuItem unitMove;
    public javax.swing.JPopupMenu unitPop;
    // End of variables declaration//GEN-END:variables
}
