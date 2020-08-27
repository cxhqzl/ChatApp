package LinkServer;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import paramer.Config;

public class TankeLoginServer extends Thread {
	
	static Socket socket = null;
	static InputStream in = null;
	static OutputStream out = null;
	
	public TankeLoginServer(String ip,String username) throws Exception{
		try {
			socket = new Socket(ip,8886);
			in = socket.getInputStream();
			out = socket.getOutputStream();
		}catch(Exception e) {
			throw new Exception("连接失败！");
		}
		try {
			out.write(username.getBytes());
			out.flush();
			byte[] b = new byte[1024*10];
			in.read(b);
			if(new String(b).trim().equals("用户已存在！")) {
				throw new Exception("用户已存在！");
			}
			String s = new String(b).trim();
			Config.sources = s;
			out.write("123".getBytes());
			out.flush();
			b = new byte[100];
			in.read(b);
			Config.number = Integer.parseInt(new String(b).trim());
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
		
		this.start();
	}
	 
	public void run() {
		byte[] b = new byte[1024*10];
		try {
			while(true) {
				in.read(b);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void Close() {
		try {
			in.close();
			out.close();
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
