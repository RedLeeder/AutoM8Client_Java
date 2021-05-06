package actions;

import src.BotAction;

public class SELECTALL {
	public int id;
	public String name;
	public String type;
	public int estTime;
	
	public SELECTALL(int id) {
		this.id = id;
		name="Select All";
		type="SELECTALL";
		estTime=0;
	}
	
	public void execute() {
		BotAction.SelectAll();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getEstTime() {
		return estTime;
	}

	public void setEstTime(int estTime) {
		this.estTime = estTime;
	}

}
