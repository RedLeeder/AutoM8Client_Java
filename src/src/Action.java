package src;
import java.awt.Point;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javafx.beans.Observable;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.util.Callback;

public class Action implements Serializable {
	private static final long serialVersionUID = -7605984823407369815L;
	Type type;
	private transient StringProperty name;
	private transient IntegerProperty count;
	Point point;
	Point point2;
	private transient StringProperty value;
	private transient StringProperty desc;
	private transient StringProperty estTime;
	
	public Action(Type type) {
		this.type = type;
		this.point = new Point(0,0);
		this.point2 = new Point(0,0);
		
		this.name = new SimpleStringProperty(type.toString());
		this.count = new SimpleIntegerProperty(1);
		this.value = new SimpleStringProperty("");
		this.desc = new SimpleStringProperty("");
		this.estTime = new SimpleStringProperty("");
		
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
	
	public static Callback<Action, Observable[]> extractor() {
        return new Callback<Action, Observable[]>() {
            @Override
            public Observable[] call(Action param) {
                return new Observable[]{param.name, param.count};
            }
        };
    }

    @Override
    public String toString() {
    	if (type != Action.Type.TYPE) {
            return String.format("%s: %s", name.get(), count.get());
    	} else {
            return String.format("%s: %s", name.get(), value.get());
    	}
    }
	
	public enum Type {
		CLICK, DRAG, RIGHTCLICK, DELAY, TAB, ENTER, TYPE, COPY, PASTE, SELECTALL, UP, DOWN, LEFT, RIGHT, EXECUTE, BREAK
	}
	
	public void execute() {
		switch (type) {
			case CLICK:			BotAction.Click(point.x, point.y, count.get()); break;
			case DRAG:			BotAction.ClickHold(point.x, point.y);
								BotAction.ClickRelease(point2.x, point2.y); break;
			case RIGHTCLICK:	BotAction.RightClick(point.x, point.y); break;
			case DELAY:			BotAction.Delay(count.get(), Integer.parseInt(value.get())); break;
			case TAB:			BotAction.Tab(count.get()); break;
			case ENTER:			BotAction.Enter(count.get()); break;
			case TYPE:			BotAction.Type(value.get()); break;
			case COPY:			BotAction.Copy(); break;
			case PASTE:			BotAction.Paste(); break;
			case SELECTALL:		BotAction.SelectAll(); break;
			case UP:			BotAction.arrowKey(type, count.get()); break;
			case DOWN:			BotAction.arrowKey(type, count.get()); break;
			case LEFT:			BotAction.arrowKey(type, count.get()); break;
			case RIGHT:			BotAction.arrowKey(type, count.get()); break;
			default: 			break;
		}
	}
	
	public void setName(String s) {
		this.name.set(s);
	}
	
	public String getName() {
		return name.getValue();
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
		value.set("(" + x + "," + y + ")");
	}
	public void setPoint(Point p) {
		point = new Point(p.x, p.y);
		value.set("(" + p.x + "," + p.y + ")");
	}
	
	public Point getPoint() {
		return point;
	}
	
	public void setPoint2(Point p) {
		point2 = new Point(p.x, p.y);
		value.set(value.get() + "->(" + p.x + "," + p.y + ")");
	}
	
	public Point getPoint2() {
		return point2;
	}
	
	public void setCount(int val) {
		count.set(val);
	}
	
	public void incrementCount() {
		count.set(count.get() + 1);
	}
	
	public void decrementCount() {
		count.set(count.get() - 1);
	}
	
	public int getCount() {
		return count.get();
	}
	
	public void setValue(String str) {
		if (this.type == Type.DELAY) {
			str = str.replaceAll("[^\\d]", "");
			if (str.length() == 0) {
				str = "1000";
			}
		}
		this.value.set(str);
	}
	
	public String getValue() {
		return value.get();
	}
	
	public void setDesc(String desc) {
		this.desc.set(desc);
	}
	
	public String getDesc() {
		return this.desc.get();
	}
	
	public void setEstTime() {
		this.estTime.set(getEstimatedTime() + "");
	}
	
	public StringProperty nameProperty() {
		return name;
	}
	
	public IntegerProperty countProperty() {
		return count;
	}
	
	public StringProperty valueProperty() {
		return value;
	}
	
	public StringProperty descProperty() {
		return desc;
	}
	
	public StringProperty estTimeProperty() {
		return estTime;
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
	
	public void writeAction(ObjectOutputStream s) throws IOException{
		final byte[] nameByte = name.get().getBytes("UTF-16");
		final byte[] strByte = value.get().getBytes("UTF-16");
		final byte[] descByte = desc.get().getBytes("UTF-16");
		
		s.writeInt(nameByte.length);
		s.write(nameByte);
		s.writeInt(count.get());
		s.writeInt(this.point.x);
		s.writeInt(this.point.y);
		s.writeInt(this.point2.x);
		s.writeInt(this.point2.y);
		s.writeInt(strByte.length);
		s.write(strByte);
		s.writeInt(descByte.length);
		s.write(descByte);
	}
	
	public void readAction(ObjectInputStream s) throws IOException {
		int nameLength = s.readInt();
		final byte[] nameByte = new byte[nameLength];
		for (int i = 0; i < nameLength; i++) {
			nameByte[i] = s.readByte();
		}
		
		this.name.setValue(new String(nameByte, "UTF-16"));
		setType(this.name.get());
		this.count.setValue(s.readInt());
		int x1 = s.readInt();
		int y1 = s.readInt();
		int x2 = s.readInt();
		int y2 = s.readInt();

		this.setPoint(new Point(x1, y1));
		this.setPoint2(new Point(x2, y2));
		
		int strLength = s.readInt();
		final byte[] strByte = new byte[strLength];
		for (int i = 0; i < strLength; i++) {
			strByte[i] = s.readByte();
		}
		this.value.setValue(new String(strByte, "UTF-16"));
		
		int descLength = s.readInt();
		final byte[] descByte = new byte[descLength];
		for (int i = 0; i < descLength; i++) {
			descByte[i] = s.readByte();
		}
		this.desc.setValue(new String(descByte, "UTF-16"));
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
