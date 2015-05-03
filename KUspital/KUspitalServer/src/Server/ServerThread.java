package Server;

import java.net.*;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerThread extends Thread
{
    private Socket so;
    private String command;
    private BufferedWriter bw;
    private BufferedReader br;
    private Hashtable<String, Socket> sockettable;
    String ID;
    Socket ts;
    BufferedReader tbr;
    BufferedWriter tbw;
    
    DataInputStream dis;
    FileOutputStream fos;
    BufferedOutputStream bos;
    DataOutputStream dos;
    FileInputStream fis;
    BufferedInputStream bis;

    public ServerThread(Socket so, Hashtable<String, Socket> sockettable, String ID) throws IOException
    {
    	this.so = so;
        this.ID = ID;
    	this.br = new BufferedReader(new InputStreamReader(so.getInputStream()));
        this.bw = new BufferedWriter(new OutputStreamWriter(so.getOutputStream()));
    	this.sockettable = sockettable;
    }
    
    public void FileReceiver()
    {
        try {
            File f;
            try (ServerSocket fss = new ServerSocket(1111); Socket fso = fss.accept()) 
            {
                dos = new DataOutputStream(fso.getOutputStream());
                System.out.println("파일 수신 작업을 시작합니다.");
                dis = new DataInputStream(fso.getInputStream());
                String fName = dis.readUTF();
                System.out.println("파일명 " + fName + "을 전송받았습니다.");
                fName = ID + "." + fName.substring(fName.lastIndexOf(".")+1,fName.length());
                
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
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }
    
    public void FileSender(String target)
    {
        try 
        {
            File f;
            try (Socket fso = new Socket(so.getInetAddress(), 1111)) 
            {
                dos = new DataOutputStream(fso.getOutputStream());
                String fName = target;
                dos.writeUTF(fName);
                System.out.printf("파일 이름(%s)을 전송하였습니다.\n", fName);
                // 파일 내용을 읽으면서 전송
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
			while(true)
			{
				command = br.readLine();
				System.out.println(command);
                                
				if(command.equalsIgnoreCase("request"))
				{
                                    bw.write("name");
                                    bw.newLine();
                                    bw.flush();
				}
                                else if(command.contains("fileS"))
                                {
                                    FileReceiver();
                                }
                                else if(command.contains("fileR"))
                                {
                                    FileSender(command.substring(5));
                                }
                                else if(command.contains("ok1"))
                                    {
                                        String names = command.substring(3);
                                        
                                        ts = sockettable.get(names);
                                        
                                        tbw = new BufferedWriter(new OutputStreamWriter(ts.getOutputStream()));
                                            
                                        tbw.write("oks");
                                        tbw.newLine();
                                        tbw.flush();
                                            
                                        System.out.println(ts.getLocalAddress() + "   " + ts.getInetAddress());
                                        bw.write(ts.getInetAddress().toString().substring(1));
                                        bw.newLine();
                                        bw.flush();
                                        
                                    }
                                    else if(command.contains("no"))
                                    {
                                        bw.write("no");
                                        bw.newLine();
                                        bw.flush();
                                        command = "";
                                    }
				else if(!command.isEmpty() && !command.equalsIgnoreCase("ok1") && !command.equalsIgnoreCase("no"))
				{
                                    System.out.println("들어왓다");
                                    ts = sockettable.get(command.split(",")[0]);
                                    System.out.println(ts.getInetAddress() + "trhgh" + ts.getLocalAddress());
                                    tbr = new BufferedReader(new InputStreamReader(ts.getInputStream()));
                                    tbw = new BufferedWriter(new OutputStreamWriter(ts.getOutputStream()));
                                    
                                    tbw.write("request" + command.split(",")[1]);
                                    tbw.newLine();
                                    tbw.flush();

                                } 
                                
			}
                        
                        
		}
        catch(NullPointerException e)
        {
                try 
                {
                    bw.write("notin");
                    bw.newLine();
                    bw.flush();
                } 
                catch (IOException ex) 
                {
                    Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
                }
            
        }
    	catch (IOException e) 
    	{
            e.printStackTrace();
	}
    }
    
}
