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
public class IMagician3 extends Magician3{
    public IMagician3(){
        this.health=300;
        this.needTurn=4;
        this.damage=35;
        this.needMoney=2000;
        this.name = "감자best";
        this.mark = new ImageIcon(getClass().getResource("/image/감자3.jpg"));
    }
}
