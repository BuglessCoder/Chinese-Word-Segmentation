package application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
	
	public Client(){
		Scanner scanner = new Scanner(System.in);
		try {
			Socket socket = new Socket("localhost",8899);
			System.out.println("请在客户端输入一句话传给服务器：");
			PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
			out.println(scanner.nextLine());
			
			InputStreamReader isr = new InputStreamReader(socket.getInputStream());
			BufferedReader in = new BufferedReader(isr);
			String result = in.readLine();
			System.out.println("服务器返回客户端的分词结果为：" + result);
			
			socket.close();
		} catch (UnknownHostException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}		
	}
	
	public static void main(String[] args) {
		 Client client = new Client();
	}

}
