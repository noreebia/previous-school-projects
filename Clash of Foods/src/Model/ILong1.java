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
public class ILong1 extends Long_Range1{
    public ILong1(){
        this.health = 60;
        this.needTurn = 2;
        this.damage = 45;
        this.needMoney = 600;
        this.name = "베이컨";
        this.mark = new ImageIcon(getClass().getResource("/image/베이컨1.jpg"));
    }
}
