package KUspital;


import View.*;
import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client extends Thread
{
    MainSystem ms;
    Chat room;
    String ip;
    Socket so;
    int uno;
    int port;
    Clientthread thread;
    
	public Client(MainSystem ms, String ip, int uno) throws IOException
        {
            this.uno = uno;
            this.ms = ms;
            this.ip = ip;
            port = 1111;
            if(ms.my_type==0)
            {
               room = new DocChat(ms, uno);           
            }
            else
            {
               room = new PatientChat(ms, uno);           
            }
	}
        
        public void run()
        {
            String line = null;

		try {
                        System.out.println(ip + "접속 시도");
			so = new Socket(ip, port);
			BufferedReader br = new BufferedReader(new InputStreamReader(so.getInputStream()));
                        
                        
			thread = new Clientthread(so, room, line);
                        room.getThread(thread);
			System.out.println("쓰레드 실행");

			while (true) 
                        {
				line = br.readLine();
                                
                                if(line.equalsIgnoreCase("over"))
                                {
                                    room.saveChat(uno);
                                    break;
                                }
                                else if(line.contains("file"))
                                {
                                    
                                    System.out.println(line.substring(5));
                                    fileRequest fr = new fileRequest(thread, line.substring(5));
                                }
                                else if(line.equalsIgnoreCase("yes"))
                                {
                                    System.out.println("대성공");
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
                        room.dispose();
                        PartnerOut po = new PartnerOut();
                        po.setVisible(true);
                        System.out.println("정상종료");
		}
                catch(NullPointerException NE)
                {
                    try 
                    {
                        room.saveChat(uno);
                        so.close();
                        room.dispose();
                        System.out.println("정상종료");
                    } 
                    catch (IOException ex) 
                    {
                        Logger.getLogger(Network_Handler.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                catch (Exception e) {
			System.out.println("비정상 종료");
		}

		System.out.println("클라이언트 종료");
        }
}