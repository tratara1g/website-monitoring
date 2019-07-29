package tratara1g;

import java.util.HashMap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.annotation.ApplicationScope;

import tratara1g.thread.MonitorThread;
import tratara1g.thread.PCStatusThread;

@SpringBootApplication
public class Application {
	public static MonitorThread monitorThread;
	public static HashMap<String, PCStatusThread> monitorThreadMap;
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		
		System.out.println("Preparing Thread Pool...");
		monitorThreadMap = new HashMap<String, PCStatusThread>();
		monitorThread = new MonitorThread();
		monitorThread.setThreadMap(monitorThreadMap);
		
		System.out.println("Starting monitor thread...");
		monitorThread.start();
	}

	@Bean
	@ApplicationScope
	public HashMap<String, PCStatusThread> monitorThreadMap() {
		return monitorThreadMap;
	}
}
