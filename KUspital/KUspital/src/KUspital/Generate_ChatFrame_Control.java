package KUspital;
import View.Chat;
import java.io.IOException;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Generate_ChatFrame_Control {
    
    MainSystem ms;
    int uno;
    
    public Generate_ChatFrame_Control(MainSystem ms, int uno)
    {
        this.uno = uno;
        this.ms = ms;
    }
    
    public void generate() 
    {
        Network_Handler nh;

        try 
        {
            nh = new Network_Handler(ms, uno);
            nh.start();
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(Generate_ChatFrame_Control.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void getin(String ip, int uno) throws IOException
    {
        Client chat;
        chat = new Client(ms, ip, uno);
        chat.start();
    }

}
