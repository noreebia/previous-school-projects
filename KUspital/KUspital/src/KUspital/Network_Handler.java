package KUspital;

import java.io.*;
import java.net.*;
import View.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Network_Handler extends Thread
{
    Chat room;
    Socket so;
    MainSystem ms;
    ServerSocket ss;
    int uno;
            
    public Network_Handler(MainSystem ms, int uno) throws IOException
    {
        this.ms = ms;
        this.uno = uno;
    }
    
	public void run()
	{
		int port = 1111;
		Clientthread thread;
		System.out.println("서버 기동 -> 클라이언트를 기동하세요");

		try 
		{
			String line = null;
			ss = new ServerSocket(port);
                        System.out.println("서버 열림");
			so = ss.accept();
                        
                        if(ms.my_type==0)
                        {
                           room = new DocChat(ms, uno);           
                        }
                        else
                        {
                           room = new PatientChat(ms, uno);           
                        }
                        
                        BufferedReader br = new BufferedReader(new InputStreamReader(so.getInputStream()));
                        
                        thread = new Clientthread(so, room, line);
                        room.getThread(thread);
                        thread.giveText(ms.my_ID);
			System.out.println("클라이언트 접속 완료");
			

			while (true) 
			{
				line = br.readLine();
                                if(line == null)
                                {
                                    room.saveChat(uno);
                                    break;
                                }
                                else if(line.equalsIgnoreCase("over"))
                                {
                                    room.saveChat(uno);
                                    break;
                                }
                                else if(line.contains("file"))
                                {
                                    fileRequest fr = new fileRequest(thread, line.substring(5));
                                    fr.setVisible(true);
                                }
                                else if(line.equalsIgnoreCase("yes"))
                                {
                                    thread.sendFile();
                                }
                                else if(line.equalsIgnoreCase("deny"))
                                {
                                    fileDenied fd = new fileDenied();
                                    fd.setVisible(true);
                                }
                                room.appendText(line);
			}
                        
                        so.close();
                        ss.close();
                        room.dispose();
                        PartnerOut po = new PartnerOut();
                        po.setVisible(true);
                        System.out.println("정상종료");
		}
                catch(NullPointerException NE)
                {
                    NE.printStackTrace();
                    try 
                    {
                        room.saveChat(uno);
                        so.close();
                        ss.close();
                        room.dispose();
                        System.out.println("정상종료");
                    } 
                    catch (IOException ex) 
                    {
                        Logger.getLogger(Network_Handler.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}
