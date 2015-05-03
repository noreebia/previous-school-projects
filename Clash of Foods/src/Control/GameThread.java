package Control;

import java.net.*;
import java.io.*;
import Model.*;

public class GameThread extends Thread
{
    private Socket so;
    BufferedWriter bw;
    BufferedReader br;
    String command;
    Player p;
    
    InputControl ic = new InputControl();
    
    public GameThread(Socket so,Player p)
    {
        this.so = so;   
        this.p = p;
    }
    
    public void run()
    {
        try
        {
            br = new BufferedReader(new InputStreamReader(so.getInputStream()));
            command = "";
            
            while(true)
            {
                command = br.readLine();
                System.out.println("들어온 정보 : " + command);
                ic.setCommand(command,p);
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    
    public void SendCommand(String command)
    {
        try{
        bw = new BufferedWriter(new OutputStreamWriter(so.getOutputStream()));
        
        bw.write(command);
        bw.newLine();
        bw.flush();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
