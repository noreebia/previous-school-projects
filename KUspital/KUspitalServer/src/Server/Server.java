package Server;
import java.io.*;
import java.net.*;
import java.util.*;

public class Server
{
    private int port;
    private String name;
    private Hashtable<String, Socket> sockettable = new Hashtable<String, Socket>();

    public Server()
    {
    	service();
    }
    
    private void service() 
    {
    	System.out.println("서버 시작");
    	try
    	{
    		name = "";
    	   
            this.port = 2000;
            ServerSocket ss = new ServerSocket(port);
            
            while(true)
            {
            	Socket so = ss.accept();           	           	            
            	BufferedReader br = new BufferedReader(new InputStreamReader(so.getInputStream()));
            	
            	name = br.readLine();
            	System.out.println("name : " + name);

        	    sockettable.put(name, so);
        	    System.out.println(name + "접속");
        	    ServerThread thread = new ServerThread(so, sockettable, name);
        	    
        	    thread.start();          	
            }
                      
       }
       catch(IOException e)
       {
           e.printStackTrace();
       }
    }

}
