package actions;

import src.BotAction;

public class ENTER {
	public int id;
	public String name;
	public String type;
	public int count;
	public int estTime;
	
	public ENTER(int id) {
		this.id = id;
		name="Enter";
		type="ENTER";
		count=1;
		estTime=0;
	}
	
	public void execute() {
		BotAction.Enter(count);
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

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getEstTime() {
		return estTime;
	}

	public void setEstTime(int estTime) {
		this.estTime = estTime;
	}

}
