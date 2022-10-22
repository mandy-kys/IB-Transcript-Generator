package IA;

public class DeltaRecord {
	private String className;
	private String score1;
	private String score2;
	public DeltaRecord() {};
	public void setClassName(String className) {
		this.className=className;
	}
	public void setScore1(String score1) {
		this.score1=score1;
	}
	public void setScore2(String string) {
		this.score2 = string;
	}
	public String getClassName()
	{
		return this.className;
	}
	public String getScore1()
	{
		return this.score1;
	}
	public String getScore2()
	{
		return this.score2;
	}
}
