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
public class UnitControl {
    public B_UnitMakeForm um;
    public B_UnitListForm ul;
    public B_BasicForm bf;
    private Player p;
    private Dictionary dictionary;
    
    //유닛을 배열에 집어넣어서 인자에 편하게 넘겨주기 위함입니다.
    public Unit[] A_Units = {new ADefender1(),new ADefender2(), new ADefender3(), new ALong1(),new ALong2(),new ALong3(),new AMagician1(),new AMagician2(),new AMagician3()};
    public Unit[] I_Units = {new IDefender1(),new IDefender2(), new IDefender3(), new ILong1(),new ILong2(),new ILong3(),new IMagician1(),new IMagician2(),new IMagician3()};
    public Unit[] J_Units = {new JDefender1(),new JDefender2(), new JDefender3(), new JLong1(),new JLong2(),new JLong3(),new JMagician1(),new JMagician2(),new JMagician3()};
    
    public UnitControl(Player p){
        this.p = p;
        dictionary = new Dictionary();
    }
    
    public void makeUnit(Unit u)
    {
        dictionary.insert(p.turn + (u.needTurn * 2), u);
    }
    
    public void sendUnit()
    {
        System.out.println(p.turn);
        while(dictionary.find(p.turn) != null)
        {
            UnitToList((Unit)dictionary.find(p.turn));
            dictionary.remove(p.turn);
        }
    }
    
    public void UnitMakeForming(Player p, B_BasicForm bf){ //유닛생산 프레임을 띄워줄때 구분해서 띄워주게하는 메소드입니다.
        if(p.getPlayerSp() instanceof Alchon){
            this.bf = bf;
             um = new A_UnitMakeForm(p,A_Units,bf, this);
             um.setVisible(true);
        }
        else if(p.getPlayerSp() instanceof Isaac){
            this.bf = bf;
            um = new I_UnitMakeForm(p,I_Units,bf, this);
            um.setVisible(true);
        }
        else if(p.getPlayerSp() instanceof JJangDon){
            this.bf = bf;
            um = new J_UnitMakeForm(p,J_Units,bf, this);
            um.setVisible(true);
        }
    }
    
    public void UnitToList(Unit u){ //생산된 유닛을 리스트에 집어넣어줍니다. 이때도 몇티어의 무슨종류 유닛인지 구분합니다.
        if(u instanceof Long_Range1){
            p.R1UnitList.add(u);
        }
        else if(u instanceof Long_Range2){
            p.R2UnitList.add(u);
        }
        else if(u instanceof Long_Range3){
            p.R3UnitList.add(u);
        }
        else if(u instanceof Defender1){
            p.G1UnitList.add(u);
        }
        else if(u instanceof Defender2){
            p.G2UnitList.add(u);
        }
        else if(u instanceof Defender3){
            p.G3UnitList.add(u);
        }
        else if(u instanceof Magician1){
            p.M1UnitList.add(u);
        }
        else if(u instanceof Magician2){
            p.M2UnitList.add(u);
        }
        else if(u instanceof Magician3){
            p.M3UnitList.add(u);
        }
    }
    
    public void UnitListForming(Player p,B_BasicForm bf,GameThread thread){ //유닛관리창을 띄워주는 메소드입니다.
        if(p.getPlayerSp() instanceof Alchon){
             ul = new A_UnitListForm(p,bf,thread);
             ul.setVisible(true);
        }
        else if(p.getPlayerSp() instanceof Isaac){
            ul = new I_UnitListForm(p,bf,thread);
            ul.setVisible(true);
        }
        else if(p.getPlayerSp() instanceof JJangDon){
            ul = new J_UnitListForm(p,bf,thread);
            ul.setVisible(true);
        }
    }
}
