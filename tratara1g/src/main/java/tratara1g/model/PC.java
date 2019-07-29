package tratara1g.model;

public class PC {
	public String pcName;
	public String ipAddress;
	public String endTime;
	public String startTime;
	public boolean active = false;

	public boolean isActive() {
		return active;
	}

	public String getPcName() {
		return pcName;
	}

	public void setPcName(String pcName) {
		this.pcName = pcName;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}
