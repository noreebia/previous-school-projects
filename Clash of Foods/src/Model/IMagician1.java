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
public class IMagician1 extends Magician1{
    public IMagician1(){
        this.health=120;
        this.needTurn=2;
        this.damage=15;
        this.needMoney=1000;
        this.name = "감자";
        this.mark = new ImageIcon(getClass().getResource("/image/감자1.jpg"));
    }
}
