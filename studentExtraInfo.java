package IA;

public class studentExtraInfo {

	private String ID;
	private String Entered;
	private String Departed;
	private String Graduation;
	private String Counselor;

	
	public String getID() {
		return ID;
	}
	public String getEntered() {
		return Entered;
	}
	public String getDeparted() {
		return Departed;
	}
	public String getGraduation() {
		return Graduation;
	}
	public String getCounselor() {
		return Counselor;
	}

	
	public void setID(String ID) {
		this.ID = ID;
	}
	public void setEntered(String Entered) {
		this.Entered = Entered;
	}
	public void setDeparted(String Departed) {
		this.Departed = Departed;
	}
	public void setGraduation(String Graduation) {
		this.Graduation = Graduation;
	}
	public void setCounselor(String Counselor) {
		this.Counselor = Counselor;
	}
	public String checkBlankAttribute() {
		String checkResult="PASS";
		if((this.ID == null || (this.ID).trim().length()<1)||(this.Entered == null|| (this.Entered).trim().length()<1)||
				(this.Departed == null || (this.Departed).trim().length()<1)||(this.Graduation == null || (this.Graduation).trim().length()<1)||
				(this.Counselor == null || (this.Counselor).trim().length()<1))
			checkResult="FAIL";
		
		return checkResult;
	}
}
