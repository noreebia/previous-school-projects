/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Control;
import Model.*;
import View.*;
/**
 *
 * @author 이상명'spc
 */
public class GameEnd {
    B_BasicForm bf;
    Player p;
    Victory vic = new Victory();
    Defeat def = new Defeat();
    
    public GameEnd(){
        
    }
    
    public GameEnd(B_BasicForm bf,Player p){
        this.bf = bf;
        this.p = p;
    }
    
    public void isEnd(){
        if(p.isEnd == 0){
            
        }
        else{
            if(p.isEnd == 1){
                bf.dispose();
                vic.setVisible(true);
            }
            else if(p.isEnd == 2){
                bf.dispose();
                def.setVisible(true);
            }
        }
    }
    
    public boolean isDefeat(){
        if(p.satiety>1500){
            return true;
        }
        else{
            return false;
        }
    }
}
