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
public class JLong2 extends Long_Range2{
    public JLong2(){
        this.health = 180;
        this.needTurn=3;
        this.damage=100;
        this.needMoney=1800;
        this.name = "함박정식";
        this.mark = new ImageIcon(getClass().getResource("/image/함박2.jpg"));
    }
}
