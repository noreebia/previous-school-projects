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
public class ALong3 extends Long_Range3{
    public ALong3(){
        this.health = 220;
        this.damage = 100;
        this.needMoney = 2000;
        this.needTurn = 4;
        this.name = "진매불밥";
        this.mark = new ImageIcon(getClass().getResource("/image/알촌불밥3.jpg"));
    }
}
