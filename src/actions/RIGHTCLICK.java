package actions;

import src.BotAction;

public class RIGHTCLICK {
	public int id;
	public String name;
	public String type;
	public int count;
	public int x;
	public int y;
	public int estTime;
	
	public RIGHTCLICK(int id) {
		this.id = id;
		name="Right Click";
		type="RIGHTCLICK";
		estTime=0;
		count=1;
	}
	
	public void execute() {
		int i = count;
		while (i-- > 0) {
			BotAction.RightClick(x, y);
		}
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
