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
public class JLong1 extends Long_Range1{
    public JLong1(){
        this.health=100;
        this.needTurn=2;
        this.damage=60;
        this.needMoney=1200;
        this.name = "함박스테이크";
        this.mark = new ImageIcon(getClass().getResource("/image/함박1.jpg"));
    }
}
