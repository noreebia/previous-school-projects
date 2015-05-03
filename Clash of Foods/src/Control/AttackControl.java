/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Control;
import View.*;
import Model.*;
/**
 *
 * @author 이상명'spc
 */
public class AttackControl {
    
    public void attack(B_BasicForm bf,Unit u,Player p){
        bf.enemySetting();
        if(canDirect(bf,p)){
            bf.EnemyButton.setEnabled(true);
        }
        else{
            bf.EnemyButton.setEnabled(false);
            if(u instanceof Defender){
                int[] range = new int[p.e.enemyFieldUnit[0].length];
                range[0] = 3;
                range[1] = 3;
                range[2] = 3;
                range[3] = 3;
                for(int i=0;i<p.e.enemyFieldUnit.length;i++){
                    for(int j=0;j<p.e.enemyFieldUnit[i].length;j++){
                        if(p.e.enemyFieldUnit[i][j]!=null){
                            if(i<range[j]){
                                range[j] = i;
                            }
                        }
                    }
                }
                switch(range[0]){
                    case 0 :
                        bf.efield1_1.setEnabled(true);
                        break;
                    case 1 :
                        bf.efield2_1.setEnabled(true);
                        break;
                    case 2 :
                        bf.efield3_1.setEnabled(true);
                        break;
                }
                switch(range[1]){
                    case 0 :
                        bf.efield1_2.setEnabled(true);
                        break;
                    case 1 :
                        bf.efield2_2.setEnabled(true);
                        break;
                    case 2 :
                        bf.efield3_2.setEnabled(true);
                        break;
                }
                switch(range[2]){
                    case 0 :
                        bf.efield1_3.setEnabled(true);
                        break;
                    case 1 :
                        bf.efield2_3.setEnabled(true);
                        break;
                    case 2 :
                        bf.efield3_3.setEnabled(true);
                        break;
                }
                switch(range[3]){
                    case 0 :
                        bf.efield1_4.setEnabled(true);
                        break;
                    case 1 :
                        bf.efield2_4.setEnabled(true);
                        break;
                    case 2 :
                        bf.efield3_4.setEnabled(true);
                        break;
                }
                
            }
            else if(u instanceof Long_Range){
                if(p.e.enemyFieldUnit[0][0]!=null){
                    bf.efield1_1.setEnabled(true);
                }
                if(p.e.enemyFieldUnit[0][1]!=null){
                    bf.efield1_2.setEnabled(true);
                }
                if(p.e.enemyFieldUnit[0][2]!=null){
                    bf.efield1_3.setEnabled(true);
                }
                if(p.e.enemyFieldUnit[0][3]!=null){
                    bf.efield1_4.setEnabled(true);
                }
                if(p.e.enemyFieldUnit[1][0]!=null){
                    bf.efield2_1.setEnabled(true);
                }
                if(p.e.enemyFieldUnit[1][1]!=null){
                    bf.efield2_2.setEnabled(true);
                }
                if(p.e.enemyFieldUnit[1][2]!=null){
                    bf.efield2_3.setEnabled(true);
                }
                if(p.e.enemyFieldUnit[1][3]!=null){
                    bf.efield2_4.setEnabled(true);
                }
                if(p.e.enemyFieldUnit[2][0]!=null){
                    bf.efield3_1.setEnabled(true);
                }
                if(p.e.enemyFieldUnit[2][1]!=null){
                    bf.efield3_2.setEnabled(true);
                }
                if(p.e.enemyFieldUnit[2][2]!=null){
                    bf.efield3_3.setEnabled(true);
                }
                if(p.e.enemyFieldUnit[2][3]!=null){
                    bf.efield3_4.setEnabled(true);
                }
            }
            else if(u instanceof Magician){
                bf.thread.SendCommand("2m"+u.damage);
                for(int i= 0;i<p.e.enemyFieldUnit.length;i++){
                        for(int j=0;j<p.e.enemyFieldUnit[i].length;j++){
                            if(p.e.enemyFieldUnit[i][j]!=null){
                                p.e.enemyFieldUnit[i][j].health -= u.damage;
                            }
                        }
                    }
            }
        }
    }
    
    public boolean canDirect(B_BasicForm bf,Player p){
        for(int i=0;i<p.e.enemyFieldUnit.length;i++){
            for(int j=0;j<p.e.enemyFieldUnit[i].length;j++){
                if(p.e.enemyFieldUnit[i][j] != null){
                    return false;
                }
            }
        }
        if(p.turn==0){
            return false;
        }
        return true;
    }
}
