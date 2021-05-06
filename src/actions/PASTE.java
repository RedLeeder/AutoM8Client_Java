package actions;

import src.BotAction;

public class PASTE {
	public int id;
	public String name;
	public String type;
	public int estTime;
	
	public PASTE(int id) {
		this.id = id;
		name="Paste";
		type="PASTE";
		estTime=0;
	}
	
	public void execute() {
		BotAction.Paste();
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
