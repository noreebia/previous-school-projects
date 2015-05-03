/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;
import java.util.ArrayList;
/**
 *
 * @author 이상명'spc
 */
public class Player {
    public Unit[][] fieldUnit = new Unit[3][4];
    public Enemy e;
    public Species sp;
    public int isEnd;
    public int money;
    public int satiety;
    public int turn;
    public ArrayList G1UnitList = new ArrayList();
    public ArrayList G2UnitList = new ArrayList();
    public ArrayList G3UnitList = new ArrayList();
    public ArrayList R1UnitList = new ArrayList();
    public ArrayList R2UnitList = new ArrayList();
    public ArrayList R3UnitList = new ArrayList();
    public ArrayList M1UnitList = new ArrayList();
    public ArrayList M2UnitList = new ArrayList();
    public ArrayList M3UnitList = new ArrayList();
    
    public Player(){
    }
    
    public Player(int i, int j,int k){
        money = i;
        satiety = j;
        turn = k;
        isEnd = 0;
    }
    
    public void setPlayerSp(Species s){
        sp = s;
    }
    
    public Species getPlayerSp(){
        return sp;
    }
    
    public void setPlayerMon(int i){
        money = i;
    }
    
    public int getPlayerMon(){
        return money;
    }
    
    public void setPlayerHp(int i){
        satiety = i;
    }
    
    public int getPlayerHp(){
        return satiety;
    }
    
    public boolean isMyTurn()
    {
        return turn%2 != 0;
    }
}
