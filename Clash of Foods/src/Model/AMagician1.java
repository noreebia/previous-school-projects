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
public class AMagician1 extends Magician1{
    public AMagician1(){
        this.health=150;
        this.damage = 30;
        this.needTurn=2;
        this.needMoney=1100;
        this.name = "순한카레";
        this.mark = new ImageIcon(getClass().getResource("/image/알촌카레1.jpg"));
    }
}
