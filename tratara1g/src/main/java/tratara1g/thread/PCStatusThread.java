package tratara1g.thread;

import java.io.IOException;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import tratara1g.model.PC;
import tratara1g.util.Util;

public class PCStatusThread implements Runnable {

	public PC pc = new PC();
	public boolean continueThread = true;

	@Override
	public void run() {
		pc.active = true;
	
		while(continueThread) {
			
			String ipAddress = "127.0.0.1";
			boolean pcReachable = false;
			try {
				InetAddress inet = InetAddress.getByName(ipAddress);
				pcReachable = inet.isReachable(5000);
				System.out.println( pcReachable ? ipAddress + " is reachable" : ipAddress + " is NOT reachable");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if(pcReachable) {
				try {
					Thread.sleep(60000);
					
					InetAddress inet = InetAddress.getByName(ipAddress);
					pcReachable = inet.isReachable(5000);
					System.out.println( pcReachable ? ipAddress + " is reachable for 2nd time" : ipAddress + " is NOT reachable for 2nd time");
					
					if(!pcReachable) {
						System.out.println( ipAddress + "is disconnected");
						SimpleDateFormat sdf = new SimpleDateFormat();  // fomat of date
						Calendar end = Calendar.getInstance();
						end.add(Calendar.MINUTE, -1);
						pc.endTime = sdf.format(end.getTime());
						continueThread = false;
						break;
					}
				} catch (InterruptedException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		pc.active = false;
		Util.doSaveProcess(pc);
	}

}
