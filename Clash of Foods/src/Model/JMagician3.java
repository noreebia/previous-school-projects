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
public class JMagician3 extends Magician3{
    public JMagician3(){
        this.health=500;
        this.needTurn=5;
        this.damage=60;
        this.needMoney=3000;
        this.name = "포크롤스페셜";
        this.mark = new ImageIcon(getClass().getResource("/image/포크롤3.jpg"));
    }
}
