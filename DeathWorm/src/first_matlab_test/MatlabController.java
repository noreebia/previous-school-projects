package first_matlab_test;

import matlabcontrol.*;

import java.io.*;

public class MatlabController{
	MatlabProxyFactoryOptions option = new MatlabProxyFactoryOptions.Builder()
	.setHidden(true)
	.setUsePreviouslyControlledSession(true)
	.setMatlabLocation(null).build();
	MatlabProxyFactory factory = new MatlabProxyFactory(option);
	MatlabProxy proxy;

	File f = new File(".");

	MatlabController(){
		String path="";

		try {
			path = f.getCanonicalPath();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			proxy = factory.getProxy();
			proxy.eval("addpath('"+path+"')"); 
		} catch (MatlabConnectionException e) {
			e.printStackTrace();
		}
		catch (MatlabInvocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void eval(String str){
		try {
			proxy.eval(str);
		} catch (MatlabInvocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public double getnumVari(String str) throws MatlabInvocationException{
		return ((double[])proxy.getVariable(str))[0];
	}

	public void setVari(String str,Object obj){
		try {
			proxy.setVariable(str, obj);
		} catch (MatlabInvocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
