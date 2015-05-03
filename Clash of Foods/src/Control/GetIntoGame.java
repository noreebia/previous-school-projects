package Control;

import java.net.*;
import java.io.*;
import Model.*;
import View.*;

public class GetIntoGame 
{
    private String ip;
    private Socket so;
    private Player p;
    GameMatchingFrame gm;
    AccessFrame af;
    
    public GetIntoGame(String ip, GameMatchingFrame gm, AccessFrame af)
    {
        this.gm = gm;
        this.af = af;
        this.ip = ip;
        p = new Player(5000,0,1);
    }
    
    public void Getin()
    {
        
        try
        {
            so = new Socket(ip, 9999);
            GameThread thread = new GameThread(so,p);
            thread.start();
            
            Species_Selector sSel = new Species_Selector(thread);
            sSel.generateSF(p);
            gm.dispose();
            af.dispose();
  
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    
    public void SendCommand(String command) throws IOException
    {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(so.getOutputStream()));
        
        bw.write(command);
        bw.newLine();
        bw.flush();
    }
}
