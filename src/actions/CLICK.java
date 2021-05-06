package actions;

import src.BotAction;

public class CLICK {
	public int id;
	public String name;
	public String type;
	public int count;
	public int x;
	public int y;
	public int estTime;
	
	public CLICK(int id) {
		this.id = id;
		name="Left Click";
		type="CLICK";
		count=1;
		estTime=0;
	}
	
	public void execute() {
		BotAction.Click(x, y, count);
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

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getEstTime() {
		return estTime;
	}

	public void setEstTime(int estTime) {
		this.estTime = estTime;
	}

}
