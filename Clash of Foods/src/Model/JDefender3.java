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
public class JDefender3 extends Defender3{
    public JDefender3(){
        this.health = 310;
        this.needTurn=4;
        this.damage=150;
        this.needMoney=2000;
        this.name = "돈까스스페셜";
        this.mark = new ImageIcon(getClass().getResource("/image/돈까스3.jpg"));
    }
}
