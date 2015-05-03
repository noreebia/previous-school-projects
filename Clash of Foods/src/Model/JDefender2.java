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
public class JDefender2 extends Defender2{
    public JDefender2(){
        this.health = 200;
        this.needTurn=3;
        this.damage=120;
        this.needMoney=1500;
        this.name = "돈까스정식";
        this.mark = new ImageIcon(getClass().getResource("/image/돈까스2.jpg"));
    }
}
