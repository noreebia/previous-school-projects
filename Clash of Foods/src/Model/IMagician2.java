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
public class IMagician2 extends Magician2{
    public IMagician2(){
        this.health=170;
        this.needTurn=3;
        this.damage=25;
        this.needMoney=1600;
        this.name = "감자mvp";
        this.mark = new ImageIcon(getClass().getResource("/image/감자2.jpg"));
    }
}
