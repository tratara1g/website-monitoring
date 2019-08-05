package tratara1g.thread;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import tratara1g.model.PC;

public class MonitorThread extends Thread {

	private int port = 25000;
	private static Socket socket;
	
	public HashMap<String, PCStatusThread> monitorThreadMap = new HashMap<String, PCStatusThread>();

	public MonitorThread() {
		super("MonitorThread");
	}

	@SuppressWarnings("resource")
	@Override
	public void run() {
		System.out.println("MonitorThread~ Inside : " + Thread.currentThread().getName());
		try {
			ServerSocket serverSocket = new ServerSocket(port);
			System.out.println("MonitorThread~ Server Started and listening to the port 25000");
			monitorThreadMap.put("PC1", new PCStatusThread("PC1"));
			monitorThreadMap.put("PC2", new PCStatusThread("PC2"));
			monitorThreadMap.put("PC3", new PCStatusThread("PC3"));
			monitorThreadMap.put("PC4", new PCStatusThread("PC4"));
			monitorThreadMap.put("PC5", new PCStatusThread("PC5"));
			monitorThreadMap.put("PC6", new PCStatusThread("PC6"));
			monitorThreadMap.put("PC7", new PCStatusThread("PC7"));
			monitorThreadMap.put("PC45", new PCStatusThread("PC45"));

			//Server is running always. This is done using this while(true) loop
			while(true) {
				//Reading the message from the client
				socket = serverSocket.accept();
				InputStream is = socket.getInputStream();
				InputStreamReader isr = new InputStreamReader(is);
				BufferedReader br = new BufferedReader(isr);
				String recievedMsg = br.readLine();

				System.out.println("MonitorThread~ Message Received: " + recievedMsg);
				if(recievedMsg != null && !recievedMsg.isEmpty()) {
					String[] msgPart = recievedMsg.split("~");
					String pcn = "PC" + (msgPart[1].split("\\."))[3].substring(1);
					
					PCStatusThread pcThread = monitorThreadMap.get(pcn);
					pcThread.pc.setIpAddress(recievedMsg);
					
					SimpleDateFormat sdf = new SimpleDateFormat(); // fomat of date
					
						
					if(msgPart[0].equals("ON")) {
						Thread thread = new Thread(pcThread);
						pcThread.pc.startTime = sdf.format(new Date());
						
						thread.start();
					}
					else if(msgPart[0].equals("OFF")) {
						Calendar end = Calendar.getInstance();
						end.add(Calendar.MINUTE, -4);
						
						pcThread.pc.endTime = sdf.format(end.getTime());
						pcThread.pc.active = false;
						pcThread.continueThread = false;
					}
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				socket.close();
			} catch(Exception e){}
		}
	}

	public void setThreadMap(HashMap<String, PCStatusThread> mtm) {
		this.monitorThreadMap = mtm;
	}	
}
