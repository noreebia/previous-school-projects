package Server;

import java.io.*;
import java.net.*;
import java.util.*;

public class Clientthread extends Thread
{
	Socket so;

	Clientthread(Socket so) 
	{
		this.so = so;
	}

	public void run() 
	{
		String line = null;
		Scanner scan = new Scanner(System.in);

		try 
		{
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(so.getOutputStream()));

			while (true) 
			{
				System.out.println("메세지를 입력하세요.");
				line = scan.nextLine();
				bw.write(line);
				bw.newLine();
				bw.flush();

				if (line.equals("exit"))
				{
					break;
				}
			}
			so.close();
		} 
		catch (Exception e) 
		{
			System.out.println("비정상 종료");
		}

		System.out.println("서버 쓰레드 종료");
	}
}
