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
public class JMagician1 extends Magician1{
    public JMagician1(){
        this.health=200;
        this.needTurn=3;
        this.damage=40;
        this.needMoney=1100;
        this.name = "포크롤";
        this.mark = new ImageIcon(getClass().getResource("/image/포크롤1.jpg"));
    }
}
