/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import javax.swing.ImageIcon;

/**
 *
 * @author 이상명'spc
 */
public class ALong1 extends Long_Range1 {
    public ALong1(){
        this.health =  80;
        this.damage = 50;
        this.needTurn = 2;
        this.needMoney = 800;
        this.name = "순한불밥";
        this.mark = new ImageIcon(getClass().getResource("/image/알촌불밥1.jpg"));
    }
}
