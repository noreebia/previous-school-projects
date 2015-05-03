/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Control;
import Model.*;
/**
 *
 * @author 이상명'spc
 */
public class InputControl {
    String command;
    String sub;
    int i, j;
    int damage;
    Player p;
    boolean isDoing = false;
    
    public void setCommand(String s,Player p){
        command = s;
        this.p = p;
        
        switch(s.charAt(0)){
            case '0':
                sub = s.substring(1);
                switch (sub) 
                {
                    case "알촌":
                        p.e = new Enemy();
                        p.e.esp = new Alchon();
                        System.out.println("생성");
                        break;
                    case "짱돈까스":
                        p.e = new Enemy();
                        p.e.esp = new JJangDon();
                        System.out.println("생성");
                        break;
                    case "이삭토스트":
                        p.e = new Enemy();
                        p.e.esp = new Isaac();
                        System.out.println("생성");
                        break;
                }
                break;
            case '1':
                i = Integer.parseInt(s.substring(1, 2));
                j = Integer.parseInt(s.substring(2, 3));
                
                System.out.println("i : " + i + ", j : " + j);
                sub = s.substring(3);
                
                p.e.enemyFieldUnit[i][j] = makeEnemyUnit(sub);
                isDoing = true;
                break;
            case '2':
                char c = s.charAt(1);
                
                if(c == 'c')
                {
                    damage = Integer.parseInt(s.substring(2));
                    p.satiety += damage;
                }
                else if(c == 'o')
                {
                    i = Integer.parseInt(s.substring(2, 3));
                    j = Integer.parseInt(s.substring(3, 4));
                    damage = Integer.parseInt(s.substring(4));
                    if(p.fieldUnit[i][j] != null){
                        p.fieldUnit[i][j].health -= damage;
                    }
                }
                else if(c == 'm'){
                    damage = Integer.parseInt(s.substring(2));
                    for(int i= 0;i<p.fieldUnit.length;i++){
                        for(int j=0;j<p.fieldUnit[i].length;j++){
                            if(p.fieldUnit[i][j]!=null){
                            p.fieldUnit[i][j].health -= damage;
                            }
                        }
                    }
                }
                isDoing = true;
                break;
            case '3':
                int k, t;
                i = Integer.parseInt(s.substring(1, 2));
                j = Integer.parseInt(s.substring(2, 3));
                k = Integer.parseInt(s.substring(3, 4));
                t = Integer.parseInt(s.substring(4, 5));
                
                p.e.enemyFieldUnit[k][t]= p.e.enemyFieldUnit[i][j];
                p.e.enemyFieldUnit[i][j] = null;
                isDoing = true;
                break;
            case '4':
                p.turn++;
                break;
        }
    }
    
    private Unit makeEnemyUnit(String name)
    {
        Unit u;
        if(p.e.esp.getName().equalsIgnoreCase("알촌"))
        {
            switch(name)
            {
                case "순한짜장" :
                    u = new ADefender1();
                    return u;
                case "매콤짜장" :
                    u = new ADefender2();
                    return u;
                case "진매짜장" :
                    u = new ADefender3();
                    return u;
                case "순한불밥" :
                    u = new ALong1();
                    return u;
                case "매콤불밥" :
                    u = new ALong2();
                    return u;
                case "진매불밥" :
                    u = new ALong3();
                    return u;
                case "순한카레" :
                    u = new AMagician1();
                    return u;
                case "매콤카레" :
                    u = new AMagician2();
                    return u;
                case "진매카레" :
                    u = new AMagician3();
                    return u;
            }
        }
        else if(p.e.esp.getName().equalsIgnoreCase("짱돈까스"))
        {
            switch(name)
            {
                case "돈까스" :
                    u = new JDefender1();
                    return u;
                case "돈까스정식" :
                    u = new JDefender2();
                    return u;
                case "돈까스스페셜" :
                    u = new JDefender3();
                    return u;
                case "함박스테이크" :
                    u = new JLong1();
                    return u;
                case "함박정식" :
                    u = new JLong2();
                    return u;
                case "함박스페셜" :
                    u = new JLong3();
                    return u;
                case "포크롤" :
                    u = new JMagician1();
                    return u;
                case "포크롤정식" :
                    u = new JMagician2();
                    return u;
                case "포크롤스페셜" :
                    u = new JMagician3();
                    return u;
            }
        }
        else
        {
            switch(name)
            {
                case "햄치즈" :
                    u = new IDefender1();
                    return u;
                case "햄치즈mvp" :
                    u = new IDefender2();
                    return u;
                case "햄치즈best" :
                    u = new IDefender3();
                    return u;
                case "베이컨" :
                    u = new ILong1();
                    return u;
                case "베이컨mvp" :
                    u = new ILong2();
                    return u;
                case "베이컨best" :
                    u = new ILong3();
                    return u;
                case "감자" :
                    u = new IMagician1();
                    return u;
                case "감자mvp" :
                    u = new IMagician2();
                    return u;
                case "감자best" :
                    u = new IMagician3();
                    return u;
            }
        }
        return null;
    }
}
