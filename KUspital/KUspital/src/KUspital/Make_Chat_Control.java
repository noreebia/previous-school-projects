package KUspital;

import java.net.*;
import java.io.*;
import View.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Make_Chat_Control extends Thread
{
    private String ip = "192.168.0.6";
    private int port = 2000;
    private BufferedReader br;
    private BufferedWriter bw;
    private String myID;
    Socket so;
    int uno;
    String RID;
    String ID;
    MainSystem ms; 
    String line;
    Waiting wt;
    
    DataInputStream dis;
    FileOutputStream fos;
    BufferedOutputStream bos;
    DataOutputStream dos;
    FileInputStream fis;
    BufferedInputStream bis;

    public Make_Chat_Control(String myID, MainSystem ms)
    {
    	this.ms = ms;
        this.myID = myID;
    }
             
    public void getPic(String path)
    {
        try 
        {
            bw.write("fileR" + path);
            bw.newLine();
            bw.flush();
            
            File f;
            try (ServerSocket fss = new ServerSocket(1111); Socket fso = fss.accept()) 
            {
                dos = new DataOutputStream(fso.getOutputStream());
                System.out.println("파일 수신 작업을 시작합니다.");
                dis = new DataInputStream(fso.getInputStream());
                String fName = dis.readUTF();
                System.out.println("파일명 " + fName + "을 전송받았습니다.");
                
                f = new File(fName);
                fos = new FileOutputStream(f);
                bos = new BufferedOutputStream(fos);
                System.out.println(fName + "파일을 생성하였습니다.");
                
                int len;
                int size = 4096;
                byte[] data = new byte[size];
                while ((len = dis.read(data)) != -1)
                {
                    bos.write(data, 0, len);
                }   
                bos.flush();
                bos.close();
                fos.close();
                dis.close();
            }
            System.out.println("파일 수신 작업을 완료하였습니다.");
            System.out.println("받은 파일의 사이즈 : " + f.length()); 
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(Make_Chat_Control.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void sendPic(String target)
    {
        try 
        {
            bw.write("fileS");
            bw.newLine();
            bw.flush();
            
            File f;
            try (Socket fso = new Socket(so.getInetAddress(), 1111)) 
            {
                dos = new DataOutputStream(fso.getOutputStream());
                String fName = target;
                dos.writeUTF(fName);
                System.out.printf("파일 이름(%s)을 전송하였습니다.\n", fName);
                
                f = new File(fName);
                fis = new FileInputStream(f);
                bis = new BufferedInputStream(fis);
                int len;
                int size = 4096;
                byte[] data = new byte[size];
                while ((len = bis.read(data)) != -1)
                {
                    dos.write(data, 0, len);
                }   
                dos.flush();
                dos.close();
                bis.close();
                fis.close();
            }
            System.out.println("파일 전송 작업을 완료하였습니다.");
            System.out.println("보낸 파일의 사이즈 : " + f.length());
        } 
        catch (IOException ex) 
        {
            ex.printStackTrace();
        }
    }
    
    public void run()
    {
    	try 
    	{
            so = new Socket(this.ip, port);

            System.out.println("서버접속");

            bw = new BufferedWriter(new OutputStreamWriter(so.getOutputStream()));
            br = new BufferedReader(new InputStreamReader(so.getInputStream()));
		
            if(so.isConnected())
            {
                    System.out.println("연결됨");
                    bw.write(myID);
                    bw.newLine();
                    bw.flush();
            }
            
            while(true)
            {
                line = br.readLine();
                System.out.println(line);
                
                if(line.equalsIgnoreCase("ok"))
                {
                    makeChat("");
                }
                else if(line.contains("request"))
                {
                    String names = line.substring(7);
                    RID = names;
                    uno = ms.DBoutput.get_registration_nums(names);
                    Requestin in = new Requestin(ms, bw, names);
                }
                else if(line.contains("name"))
                {
                    System.out.println(ID + "," + myID);
                    bw.write(ID + "," + myID);
                    bw.newLine();
                    bw.flush();
                }
                else if(line.equalsIgnoreCase("oks"))
                {
                    Generate_ChatFrame_Control gc = new Generate_ChatFrame_Control(ms, ms.DBoutput.get_registration_nums(ID));
                    gc.generate();
                    wt.dispose();
                }
                else if(line.equalsIgnoreCase("notin"))
                {
                    Notin nt = new Notin();
                }
                else if(!line.isEmpty() && !line.equalsIgnoreCase("oks"))
                {
                    System.out.println("신청자" + RID);
                    Generate_ChatFrame_Control gc = new Generate_ChatFrame_Control(ms, ms.DBoutput.get_registration_nums(RID));
                    gc.getin(line, uno);
                }
                            
            }
	}
        catch(SocketException e)
        {
            SocketError se = new SocketError();
            se.setVisible(true);
            
        }
    	catch (Exception e) 
    	{
		e.printStackTrace();
	}
    }
    
    public void makeChat(String ID, Waiting wt)
    {
        this.ID = ID;
        this.wt = wt;
        
        try
        {
            bw.write("request");
            bw.newLine();
            bw.flush();

        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    
    public void makeChat(String ID)
    {
        this.ID = ID;
        
        try
        {
            bw.write("request");
            bw.newLine();
            bw.flush();

        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}
