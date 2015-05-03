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
public class MoveControl {
    UnitMoveForm umf;
    
    public void move(B_BasicForm bf,Unit u,Player p){
        if(bf.unitPop.getInvoker() == bf.field1_1){
            p.fieldUnit[0][0] = null;
            umf = new UnitMoveForm(p,u,bf,"00");
        }
        else if(bf.unitPop.getInvoker() == bf.field1_2){
            p.fieldUnit[0][1] = null;
            umf = new UnitMoveForm(p,u,bf,"01");
        }
        else if(bf.unitPop.getInvoker() == bf.field1_3){
            p.fieldUnit[0][2] = null;
            umf = new UnitMoveForm(p,u,bf,"02");
        }
        else if(bf.unitPop.getInvoker() == bf.field1_4){
            p.fieldUnit[0][3] = null;
            umf = new UnitMoveForm(p,u,bf,"03");
        }
        else if(bf.unitPop.getInvoker() == bf.field2_1){
            p.fieldUnit[1][0] = null;
            umf = new UnitMoveForm(p,u,bf,"10");
        }
        else if(bf.unitPop.getInvoker() == bf.field2_2){
            p.fieldUnit[1][1] = null;
            umf = new UnitMoveForm(p,u,bf,"11");
        }
        else if(bf.unitPop.getInvoker() == bf.field2_3){
            p.fieldUnit[1][2] = null;
            umf = new UnitMoveForm(p,u,bf,"12");
        }
        else if(bf.unitPop.getInvoker() == bf.field2_4){
            p.fieldUnit[1][3] = null;
            umf = new UnitMoveForm(p,u,bf,"13");
        }
        else if(bf.unitPop.getInvoker() == bf.field3_1){
            p.fieldUnit[2][0] = null;
            umf = new UnitMoveForm(p,u,bf,"20");
        }
        else if(bf.unitPop.getInvoker() == bf.field3_2){
            p.fieldUnit[2][1] = null;
            umf = new UnitMoveForm(p,u,bf,"21");
        }
        else if(bf.unitPop.getInvoker() == bf.field3_3){
            p.fieldUnit[2][2] = null;
            umf = new UnitMoveForm(p,u,bf,"22");
        }
        else if(bf.unitPop.getInvoker() == bf.field3_4){
            p.fieldUnit[2][3] = null;
            umf = new UnitMoveForm(p,u,bf,"23");
        }
        umf.setVisible(true);
    }
}
