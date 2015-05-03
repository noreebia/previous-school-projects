package Control;

import java.net.*;
import java.io.*;
import Model.*;
import View.*;

public class Network_Handler extends Thread
{
    Player p;
    GameMatchingFrame gmf;
    WaitFrame wf;
    
    public Network_Handler(GameMatchingFrame gmf, WaitFrame wf)
    {
        p = new Player(5000,0,0);
        this.gmf = gmf;
        this.wf = wf;
    }
    
    public void run()
    {
        try
        {
            ServerSocket ss = new ServerSocket(9999);
            Socket so = ss.accept();
            GameThread thread = new GameThread(so,p);
            thread.start();
            Species_Selector sSel = new Species_Selector(thread);
            sSel.generateSF(p);
            gmf.dispose();
            wf.dispose();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        
    }
}
