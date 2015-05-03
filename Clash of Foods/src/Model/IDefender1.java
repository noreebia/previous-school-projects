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
public class IDefender1 extends Defender1{
    public IDefender1(){
        this.health = 85;
        this.needTurn = 2;
        this.damage = 55;
        this.needMoney = 500;
        this.name = "햄치즈";
        this.mark = new ImageIcon(getClass().getResource("/image/햄치즈1.jpg"));
    }
}
