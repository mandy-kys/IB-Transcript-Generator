package IA;

public class ScoreRecord {
	
	private String score;
	private String studentID;
	private String seme;
	private String className;
	private int sequence;
	private String key;
	
	public int getSequence() {
		return sequence;
	}
	public String getClassName() {
		return className;
	}
	public String getScore() {
		return score;
	}
	public String getStudentID() {
		return studentID;
	}
	public String getSeme() {
		return seme;
	}
	public String getKey() {
		return key;
	}
	public void setSequence(int sequence) {
		this.sequence=sequence;
	}
	public void setClassName(String className) {
		this.className=className;
	}
	public void setKey(String key) {
		this.key=key;
	}
	public void setScore(String score) {
		this.score=score;
	}
	public void setStudentID(String studentID) {
		this.studentID=studentID;
	}
	public void setSeme(String seme) {
		this.seme=seme;
	}
	ScoreRecord()
	{
		
	}
	ScoreRecord(String stdID,String seme,String className,String score)
	{
		this.className=className;
		this.score=score;
		this.studentID=studentID;
		this.seme=seme;
		this.sequence = sequence;
		this.key=studentID+";"+seme+";"+className+";"+score;
	}
}

