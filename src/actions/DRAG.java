package actions;

import src.BotAction;

public class DRAG {
	public int id;
	public String name;
	public String type;
	public int count;
	public int x1;
	public int y1;
	public int x2;
	public int y2;
	public int estTime;
	
	public DRAG(int id) {
		this.id = id;
		name="Drag";
		type="DRAG";
		count=1;
		estTime=0;
	}
	
	public void execute() {
		BotAction.ClickHold(x1, y1);
		BotAction.ClickRelease(x2,y2);
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

	public int getX1() {
		return x1;
	}

	public void setX1(int x1) {
		this.x1 = x1;
	}

	public int getY1() {
		return y1;
	}

	public void setY1(int y1) {
		this.y1 = y1;
	}

	public int getX2() {
		return x2;
	}

	public void setX2(int x2) {
		this.x2 = x2;
	}

	public int getY2() {
		return y2;
	}

	public void setY2(int y2) {
		this.y2 = y2;
	}

	public int getEstTime() {
		return estTime;
	}

	public void setEstTime(int estTime) {
		this.estTime = estTime;
	}

}
