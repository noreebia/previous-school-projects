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
public class ADefender3 extends Defender3{
    public ADefender3(){
        this.health = 270;
        this.damage = 120;
        this.needMoney = 1700;
        this.needTurn = 4;
        this.name = "진매짜장";
        this.mark = new ImageIcon(getClass().getResource("/image/알촌짜장3.jpg"));
    }
}
