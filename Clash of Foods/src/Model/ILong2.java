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
public class ILong2 extends Long_Range2{
    public ILong2(){
        this.health = 100;
        this.needTurn = 2;
        this.damage = 70;
        this.needMoney = 1300;
        this.name = "베이컨mvp";
        this.mark = new ImageIcon(getClass().getResource("/image/베이컨2.jpg"));
    }
}
