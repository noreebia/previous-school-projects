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
public class AMagician3 extends Magician3{
    public AMagician3(){
        this.health = 390;
        this.needTurn=5;
        this.damage=50;
        this.needMoney=2500;
        this.name = "진매카레";
        this.mark = new ImageIcon(getClass().getResource("/image/알촌카레3.jpg"));
    }
}
