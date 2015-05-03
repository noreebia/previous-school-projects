package KUspital;

import java.io.*;
import View.*;

public class ReadHistory 
{
    public void readHistory(String name, Chat chat) throws FileNotFoundException, IOException
    {
        File History = new File(name);
        
        if(History.exists())
        {
            BufferedReader br = new BufferedReader(new FileReader(name));
            String line = null;
            
            while((line = br.readLine()) != null)
            {
                chat.appendText(line);
            }
        }
        else
        {
            System.out.println("처음채팅");
        }
    }
    
}
