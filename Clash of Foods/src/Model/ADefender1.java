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
public class ADefender1 extends Defender1{
    public ADefender1(){
        this.damage = 60;
        this.health = 100;
        this.needMoney = 700;
        this.needTurn = 2;
        this.name = "순한짜장";
        this.mark = new ImageIcon(getClass().getResource("/image/알촌짜장1.jpg"));
    }
}
