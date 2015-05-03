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
public class JMagician2 extends Magician2{
    public JMagician2(){
        this.health=270;
        this.needTurn=4;
        this.damage=50;
        this.needMoney=2200;
        this.name = "포크롤정식";
        this.mark = new ImageIcon(getClass().getResource("/image/포크롤2.jpg"));
    }
}
