package actions;

import src.BotAction;

public class TYPE {
	public int id;
	public String name;
	public String type;
	public String value;
	public int estTime;
	
	public TYPE(int id) {
		this.id = id;
		name="Type";
		type="TYPE";
		value="";
		estTime=0;
	}
	
	public void execute() {
		BotAction.Type(value);
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

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int getEstTime() {
		return estTime;
	}

	public void setEstTime(int estTime) {
		this.estTime = estTime;
	}

}
