/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

/**
 *
 * @author 이상명'spc
 */
public class Enemy {
    public Unit[][] enemyFieldUnit = new Unit[3][4];
    public Species esp;
    public int Life;
    
    public Enemy()
    {
        this.Life = 0;
    }
    
    public boolean isDead()
    {
        return Life >= 5000;
    }
}
