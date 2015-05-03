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
public class Entry {
	private int key;
	private Object obj;
	
	public Entry(int key,Object obj){
		this.key = key;
		this.obj = obj;
	}
	
	public Entry(){
		
	}
	
	public int getKey(){
		return this.key;
	}
	
	public Object getObj(){
		return this.obj;
	}
	
	public String toString(){
		return "{"+key+","+obj.toString()+"}";
	}
}

