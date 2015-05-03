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
public class JDefender1 extends Defender1{
    public JDefender1(){
        this.health = 130;
        this.needTurn = 2;
        this.damage = 80;
        this.needMoney = 1000;
        this.name = "돈까스";
        this.mark = new ImageIcon(getClass().getResource("/image/돈까스1.jpg"));
    }
}
