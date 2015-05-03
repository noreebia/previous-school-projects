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
public class Dictionary {
	Entry[] objs = new Entry[20];
	int pos;
	
	public void insert(int k, Object v){
		Entry newE = new Entry(k,v);
		objs[pos] = newE;
		System.out.println(objs[pos]);
		pos++;
	}
	
	public void remove(int k){
		for(int i=0;i<objs.length;i++){
			if(objs[i].getKey()==k){
				System.out.println(objs[i]);
				objs[i] = null;
				for(int j=i;j<objs.length-1;j++){
					objs[j] = objs[j+1];
				}
				objs[objs.length-1] = null;
				break;
			}
		}
	}
	
	public Object find(int k){
		for(int i=0;i<objs.length;i++){
			if(objs[i]!=null){
				if(objs[i].getKey()==k){
					return objs[i].getObj();
				}
			}
		}
		return null;
	}
}
