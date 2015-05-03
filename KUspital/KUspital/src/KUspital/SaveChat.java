package KUspital;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SaveChat 
{
    BufferedWriter file;

    public void Save(String name, String text)
    {
        try 
        {
            this.file = new BufferedWriter(new FileWriter(name));
            file.write(text);
        
            file.close();
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(SaveChat.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
}
