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
public class AMagician2 extends Magician2{
    public AMagician2(){
        this.health=220;
        this.needTurn=3;
        this.damage=40;
        this.needMoney=1900;
        this.name = "매콤카레";
        this.mark = new ImageIcon(getClass().getResource("/image/알촌카레2.jpg"));
    }
}
