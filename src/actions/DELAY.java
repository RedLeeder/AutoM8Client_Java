package actions;

import src.BotAction;

public class DELAY {
	public int id;
	public String name;
	public String type;
	public int value;
	public int estTime;
	
	public DELAY(int id) {
		this.id = id;
		name="Delay";
		type="DELAY";
		value=0;
		estTime=0;
	}
	
	public void execute() {
		BotAction.Delay(value);
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

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getEstTime() {
		return estTime;
	}

	public void setEstTime(int estTime) {
		this.estTime = estTime;
	}

}
