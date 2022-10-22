package IA;

public class studentID {

	private String ID;
	private String SLast;
	private String SFirst;
	private String SPreferred;
	private String SChinese;
	private String SGender;
	private String SBday;
	private String SNationality;
	private String PEnglish;
	private String PChinese;
	private String Address;
	private String Entered;
	private String Departed;
	private String Graduation;
	private String Counselor;
	//private String Ceeb;
	//private String Ucas;
	private String completeness;
	
	public studentID() {
		this.completeness="OK";
	}
	
	public String getID() {
		return ID;
	}
	
	public String getCompleteness() {
		if(!"OK".equals(completeness))
			return completeness;
		else
		{
			if((this.SLast == null || (this.SLast).trim().length()<1)||(this.ID == null || (this.ID).trim().length()<1)||
					(this.SFirst == null || (this.SFirst).trim().length()<1)||(this.SPreferred == null || (this.SPreferred).trim().length()<1)||
					(this.SChinese == null || (this.SChinese).trim().length()<1)|| (this.SGender == null || (this.SGender).trim().length()<1)||
					(this.SBday == null || (this.SBday).trim().length()<1)||(this.SNationality == null || (this.SNationality).trim().length()<1)||
					(this.PEnglish == null || (this.PEnglish).trim().length()<1)||(this.PChinese == null || (this.PChinese).trim().length()<1)||
					(this.Address == null || (this.Address).trim().length()<1)||(this.Entered == null || (this.Entered).trim().length()<1)||
					(this.Departed == null || (this.Departed).trim().length()<1)||(this.Graduation == null || (this.Graduation).trim().length()<1)||
					(this.Counselor == null || (this.Counselor).trim().length()<1))
				this.completeness="NG";
			return this.completeness;
		}
	}
	public String getSLast() {
		return SLast;
	}
	public String getSFirst() {
		return SFirst;
	}
	public String getSPreferred() {
		return SPreferred;
	}
	public String getSChinese() {
		return SChinese;
	}
	public String getSGender() {
		return SGender;
	}
	public String getSBday() {
		return SBday;
	}
	public String getSNationality() {
		return SNationality;
	}
	public String getPEnglish() {
		return PEnglish;
	}
	public String getPChinese() {
		return PChinese ;
	}
	public String getAddress() {
		return Address;
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
	/*public String getCeeb() {
		return Ceeb;
	}
	public String getUcas() {
		return Ucas;
	}*/

	
	public void setSLast(String SLast) {
		this.SLast=SLast;
	}
	public void setID(String ID) {
		this.ID= ID;
	}
	public void setSFirst(String SFirst) {
		this.SFirst=SFirst;
	}
	public void setSPreferred(String SPreferred) {		
		this.SPreferred=SPreferred;
	}
	public void setSChinese(String SChinese) {
		this.SChinese=SChinese;
	}
	public void setSGender(String SGender) {
		this.SGender=SGender;
	}
	public void setSBday(String SBday) {
		 this.SBday=SBday;
	}
	public void setSNationality(String SNationality) {
		this.SNationality=SNationality;
	}

	public void setPEnglish(String PEnglish) {
		this.PEnglish = PEnglish;
	}
	public void setPChinese(String PChinese) {
		this.PChinese= PChinese ;
	}
	public void setAddress(String Address) {
		this.Address=Address;
	}
	public void setEntered(String Entered) {
		this.Entered=Entered;
	}
	public void setDeparted(String Departed) {
		this.Departed=Departed;
	}
	public void setGraduation(String Graduation) {
		this.Graduation=Graduation;
	}
	public void setCounselor(String Counselor) {
		this.Counselor=Counselor;
	}
	/*public void setCeeb(String Ceeb) {
		this.Ceeb=Ceeb;
	}
	public void setUcas(String Ucas) {
		this.Ucas=Ucas;
	}*/
	public void setCompleteness(String completeness) {
		
		this.completeness=completeness;
	}

	
}
