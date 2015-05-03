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
public class ALong2 extends Long_Range2 {
    public ALong2(){
        this.health = 120;
        this.damage = 85;
        this.needMoney = 1400;
        this.needTurn = 3;
        this.name = "매콤불밥";
        this.mark = new ImageIcon(getClass().getResource("/image/알촌불밥2.jpg"));
    }
}
