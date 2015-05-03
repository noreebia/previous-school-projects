package KUspital;

import java.io.*;
import java.net.*;
import View.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Clientthread
{
	Socket so;
        Chat chat;
        public String input;
        private String target;
        private String name;
        
        DataInputStream dis;
        FileOutputStream fos;
        BufferedOutputStream bos;
        DataOutputStream dos;
        FileInputStream fis;
        BufferedInputStream bis;
        
	public Clientthread(Socket so, Chat chat, String input) 
	{
		this.so = so;
                this.chat = chat;
                this.input = input;
	}

        public void giveText(String line)
        {
            try
            {
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(so.getOutputStream()));

                bw.write(line);
                bw.newLine();
                bw.flush();
            }
            catch (Exception e) 
            {
		System.out.println("비정상 종료");
            }
        }
        
        public void sendFile()
        {
            File f;
                
            try (Socket fso = new Socket(so.getInetAddress(), 3000)) 
            {
                dos = new DataOutputStream(fso.getOutputStream());
                String fName = target;
                dos.writeUTF(name + "." + fName.substring(fName.lastIndexOf(".")+1,fName.length()));
                System.out.printf("파일 이름(%s)을 전송하였습니다.\n", name);

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

            System.out.println("파일 전송 작업을 완료하였습니다.");
            System.out.println("보낸 파일의 사이즈 : " + f.length());
            } 
            catch (IOException ex) 
            {
                Logger.getLogger(Clientthread.class.getName()).log(Level.SEVERE, null, ex);
            } 
        } 
               
                        
        public void sendFile(String target, String name)
        {
            BufferedWriter bw;
            this.target = target;
            this.name = name;
            try 
            {
                bw = new BufferedWriter(new OutputStreamWriter(so.getOutputStream()));
                
                bw.write("file" + name);
                bw.newLine();
                bw.flush();
                
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        
        public void getFile()
        {
             
            File f;
            try (ServerSocket fss = new ServerSocket(3000); Socket fso = fss.accept()) 
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
                
                System.out.println("파일 수신 작업을 완료하였습니다.");
                System.out.println("받은 파일의 사이즈 : " + f.length()); 
            } 
            catch (IOException ex) 
            {
                Logger.getLogger(Clientthread.class.getName()).log(Level.SEVERE, null, ex);
            }
            
             
            
        }
        
        public void getOut()
        {
            try
            {
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(so.getOutputStream()));

                bw.write("over");
                bw.newLine();
                bw.flush();
            }
            catch (Exception e) 
            {
		System.out.println("비정상 종료");
            }
        }
}
