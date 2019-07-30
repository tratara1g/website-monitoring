package tratara1g.controller.rest;

import java.util.HashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import tratara1g.model.PC;
import tratara1g.thread.PCStatusThread;

@RestController
public class ThreadStatus {

	@Autowired
	private HashMap<String, PCStatusThread> monitorThreadMap;

	@GetMapping("/admin/pcstatus")
	public JSONArray checkPCStatus() {
		JSONArray response = new JSONArray();
		System.out.println(monitorThreadMap.size());
		for(String key: monitorThreadMap.keySet()) {
			JSONObject pcJson = new JSONObject();
			PC pc = monitorThreadMap.get(key).pc;
			response.add(pc);
		}
		return response;
	}
}
