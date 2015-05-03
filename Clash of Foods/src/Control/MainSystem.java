package Control;

import Model.*;
import View.*;

public class MainSystem extends Thread
{
    private Player p;
    private GameThread thread;
    B_BasicForm bf;
    UnitControl umc;
    
    public MainSystem(Player p, GameThread thread)
    {
        this.p = p;
        this.thread = thread;
        this.umc = new UnitControl(p);
    }
    
    public void run()
    {
        if(p.getPlayerSp() instanceof Alchon){
            bf = new A_BasicForm(p,thread, umc);
            bf.setVisible(true);
        }
        else if(p.getPlayerSp() instanceof Isaac){
            bf = new I_BasicForm(p,thread, umc);
            bf.setVisible(true);
        }
        else if(p.getPlayerSp() instanceof JJangDon){
            bf = new J_BasicForm(p,thread, umc);
            bf.setVisible(true);
        }
        
        while(true)
        {
            if(thread.command!=""){
            if(thread.command.charAt(0) == '1' || thread.command.charAt(0)=='2' || thread.command.charAt(0)=='3'){
                bf.pHpLabel.setText(Integer.toString(p.getPlayerHp()));
                if(thread.ic.isDoing){
                    bf.isDead();
                    bf.enemySetting();
                    bf.mySetting();
                    thread.ic.isDoing = false;
                }
            }
            }
            if(thread.command.equalsIgnoreCase("4"))
            {
                p.money += 1500;
                bf.pMoneyLabel.setText(Integer.toString(p.getPlayerMon()));
                bf.pHpLabel.setText(Integer.toString(p.getPlayerHp()));
                umc.sendUnit();
                bf.isDead();
                bf.enemySetting();
                bf.mySetting();
                bf.synchronization();
                thread.command = "";
            }
            if(thread.command.equalsIgnoreCase("5")){
                p.isEnd = 1;
                bf.gg.isEnd();
            }
        }
    }
    
}
