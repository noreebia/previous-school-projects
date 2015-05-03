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
public class ADefender2 extends Defender2{
    public ADefender2(){
        this.health = 150;
        this.damage = 90;
        this.needMoney = 1000;
        this.needTurn = 2;
        this.name = "매콤짜장";
        this.mark = new ImageIcon(getClass().getResource("/image/알촌짜장2.jpg"));
    }
}
