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
public class JLong3 extends Long_Range3{
    public JLong3(){
        this.health=300;
        this.needTurn=5;
        this.damage=130;
        this.needMoney=2200;
        this.name = "함박스페셜";
        this.mark = new ImageIcon(getClass().getResource("/image/함박3.jpg"));
    }
}
