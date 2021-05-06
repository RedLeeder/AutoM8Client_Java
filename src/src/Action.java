package src;
import java.awt.Point;

public class Action {
	private int id;
	Type type;
	private String name;
	private int count;
	Point point;
	Point point2;
	private String value;
	private String desc;
	private String estTime;
	
	public Action(int id, Type type) {
		this.id = id;
		this.type = type;
		this.point = new Point(0,0);
		this.point2 = new Point(0,0);
		
		this.name = type.toString();
		this.count = 1;
		this.value = "";
		this.desc = "";
		this.estTime = "";
		
		setEstTime();
	}
	
	private void setType(String typeS) {
		switch (typeS) {
		case "CLICK":		this.type = Type.CLICK; break;
		case "DRAG":		this.type = Type.DRAG; break;
		case "RIGHTCLICK":	this.type = Type.RIGHTCLICK; break;
		case "DELAY":		this.type = Type.DELAY; break;
		case "TAB":			this.type = Type.TAB; break;
		case "ENTER":		this.type = Type.ENTER; break;
		case "TYPE":		this.type = Type.TYPE; break;
		case "COPY":		this.type = Type.COPY; break;
		case "PASTE":		this.type = Type.PASTE; break;
		case "SELECTALL":	this.type = Type.SELECTALL; break;
		case "UP":			this.type = Type.UP; break;
		case "DOWN":		this.type = Type.DOWN; break;
		case "LEFT":		this.type = Type.LEFT; break;
		case "RIGHT":		this.type = Type.RIGHT; break;
		case "EXECUTE":		this.type = Type.EXECUTE; break;
		case "BREAK":		this.type = Type.BREAK; break;
		default: 			break;
		}
	}

    @Override
    public String toString() {
    	if (type != Action.Type.TYPE) {
            return String.format("%s: %s", name, count);
    	} else {
            return String.format("%s: %s", name, value);
    	}
    }
	
	public enum Type {
		CLICK, DRAG, RIGHTCLICK, DELAY, TAB, ENTER, TYPE, COPY, PASTE, SELECTALL, UP, DOWN, LEFT, RIGHT, EXECUTE, BREAK
	}
	
	public void execute() {
		switch (type) {
			case CLICK:			BotAction.Click(point.x, point.y, count); break;
			case DRAG:			BotAction.ClickHold(point.x, point.y);
								BotAction.ClickRelease(point2.x, point2.y); break;
			case RIGHTCLICK:	BotAction.RightClick(point.x, point.y); break;
			case DELAY:			BotAction.Delay(count, Integer.parseInt(value)); break;
			case TAB:			BotAction.Tab(count); break;
			case ENTER:			BotAction.Enter(count); break;
			case TYPE:			BotAction.Type(value); break;
			case COPY:			BotAction.Copy(); break;
			case PASTE:			BotAction.Paste(); break;
			case SELECTALL:		BotAction.SelectAll(); break;
			case UP:			BotAction.arrowKey("UP", count); break;
			case DOWN:			BotAction.arrowKey("DOWN", count); break;
			case LEFT:			BotAction.arrowKey("LEFT", count); break;
			case RIGHT:			BotAction.arrowKey("RIGHT", count); break;
			default: 			break;
		}
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return this.id;
	}
	
	public void setName(String s) {
		this.name = s;
	}
	
	public String getName() {
		return name;
	}
	
	public Type getType() {
		return this.type;
	}
	
	public void setType(Type t) {
		this.type = t;
		setName(t.toString());
	}
	
	public void setPoint(int x, int y) {
		point = new Point(x, y);
		value = "(" + x + "," + y + ")";
	}
	public void setPoint(Point p) {
		point = new Point(p.x, p.y);
		value = "(" + p.x + "," + p.y + ")";
	}
	
	public Point getPoint() {
		return point;
	}
	
	public void setPoint2(Point p) {
		point2 = new Point(p.x, p.y);
		value = value + "->(" + p.x + "," + p.y + ")";
	}
	
	public Point getPoint2() {
		return point2;
	}
	
	public void setCount(int val) {
		count = val;
	}
	
	public void incrementCount() {
		count++;
	}
	
	public void decrementCount() {
		count--;
	}
	
	public int getCount() {
		return count;
	}
	
	public void setValue(String str) {
		if (this.type == Type.DELAY) {
			str = str.replaceAll("[^\\d]", "");
			if (str.length() == 0) {
				str = "1000";
			}
		}
		this.value = str;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public String getDesc() {
		return this.desc;
	}
	
	public void setEstTime() {
		this.estTime = getEstimatedTime() + "";
	}
	
	public int getEstimatedTime() {
		int estTime = 0;
		switch (type) {
			case CLICK:			estTime = 165 * getCount(); break;
			case DRAG:			estTime = 165 * getCount(); break;
			case RIGHTCLICK:	estTime = 165 * getCount(); break;
			case DELAY:			estTime = Integer.parseInt(getValue()); break;
			case TAB:			estTime = 165 * getCount(); break;
			case ENTER:			estTime = 165 * getCount(); break;
			case TYPE:			estTime = 100 + getValue().length(); break;
			case COPY:			estTime = 90; break;
			case PASTE:			estTime = 90; break;
			case SELECTALL:		estTime = 200; break;
			case UP:			estTime = 165 * getCount(); break;
			case DOWN:			estTime = 165 * getCount(); break;
			case LEFT:			estTime = 165 * getCount(); break;
			case RIGHT:			estTime = 165 * getCount(); break;
			case EXECUTE:		estTime = 10000; break;
			case BREAK:			estTime = 5000; break;
			default: 			break;
		}
		return estTime;
	}
	
	public void printAction() {
		System.out.println("-------");
		System.out.println("Type: " + this.getType());
		System.out.println("Count: " + this.getCount());
		System.out.println("Value: " + this.getValue());
		System.out.println("Point1: " + this.getPoint());
		System.out.println("Point2: " + this.getPoint2());
		System.out.println("Desc: " + this.getDesc());
		System.out.println("-------");
	}
}
