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
public class IDefender3 extends Defender3{
    public IDefender3(){
        this.health = 200;
        this.needTurn = 3;
        this.damage = 100;
        this.needMoney=1300;
        this.name = "햄치즈best";
        this.mark = new ImageIcon(getClass().getResource("/image/햄치즈3.jpg"));
    }
}
