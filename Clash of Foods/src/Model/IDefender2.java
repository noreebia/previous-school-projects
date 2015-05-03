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
public class IDefender2 extends Defender2{
    public IDefender2(){
        this.health = 120;
        this.needTurn = 2;
        this.damage = 80;
        this.needMoney = 800;
        this.name = "햄치즈mvp";
        this.mark = new ImageIcon(getClass().getResource("/image/햄치즈2.jpg"));
    }
}
