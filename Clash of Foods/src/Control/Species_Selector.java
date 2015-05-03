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

//종족선택 컨트롤러입니다.

public class Species_Selector {
    SpeciesSelForm sf;
    public GameThread thread;
    
    public Species_Selector(){
        
    }
    
    public Species_Selector(GameThread thread){
        this.thread = thread;
    }
    
    public void generateSF(Player p){ //메인에 있는 플레이어를 인자로 받고 시작합니다. 그리고 이를 종족선택 프레임에 넘겨주죠.
       sf = new SpeciesSelForm(p,this);
       sf.setVisible(true);
    }
    
    public void generateBF(Player p){
        //이제 인자로 다시 받아들인 플레이어의 종족을 뭐의 인스턴스인지 구분해서 폼을 띄워줍니다.
        //종족마다 다른 프레임을 띄워주는 메소드이죠.
        
       MainSystem ms = new MainSystem(p, thread);
       ms.start();
    }
}
