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
public class ILong3 extends Long_Range3{
    public ILong3(){
        this.health = 190;
        this.needTurn= 3;
        this.damage = 90;
        this.needMoney = 1500;
        this.name = "베이컨best";
        this.mark = new ImageIcon(getClass().getResource("/image/베이컨3.jpg"));
    }
}
