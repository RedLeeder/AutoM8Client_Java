package src;

import java.awt.Point;

import actions.*;

public class SmartRecord {

	private static boolean recording;
	private static boolean delayTracking;
	private long startTime;
	private String activeString;
	private int offset;
	private int id;
	
	public SmartRecord() {
		recording = false;
		delayTracking = false;
		startTime = 0;
		activeString = "";
		offset=0;
		id = 1;
	}
	
	public void enableRecording(int id) {
//		System.out.println("Recording: Enabled");
		offset=0;
		this.id = id;
		recording = true;
		delayTracking = false;
		startTime = System.currentTimeMillis();
	}
	
	public void disableRecording() {
//		System.out.println("Recording: Disabled");
		AutoM8.ws.endRecord();
		AutoM8.KL.activeString = false;
		recording = false;
	}
	
	public boolean recording() {
		return recording;
	}
	
	public void addDelay(long delay) {
		AutoM8.ws.addAction(new DELAY(id++), offset++);
	}
	
	public void addLeftClick(Point p) {
		if (delayTracking) {
			addDelay(System.currentTimeMillis() - startTime);
			startTime = System.currentTimeMillis();
		}
		CLICK c = new CLICK(id++);
		c.setX(p.x);
		c.setY(p.y);
		AutoM8.ws.addAction(c, offset++);
	}
	
	public void addRightClick(Point p) {
		if (delayTracking) {
			addDelay(System.currentTimeMillis() - startTime);
			startTime = System.currentTimeMillis();
		}
		RIGHTCLICK c = new RIGHTCLICK(id++);
		c.setX(p.x);
		c.setY(p.y);
		AutoM8.ws.addAction(c, offset++);
	}
	
	public void addMouseDrag(Point start, Point end) {
		if (delayTracking) {
			addDelay(System.currentTimeMillis() - startTime);
			startTime = System.currentTimeMillis();
		}
		if (start.x != end.x || start.y != end.y) {
			DRAG d = new DRAG(id++);
			d.setX1(start.x);
			d.setY1(start.y);
			d.setX2(end.x);
			d.setY2(end.y);
			AutoM8.ws.addAction(d, offset++);
		}
	}
	
	public void addKeyEvent(String key) {
		if (delayTracking) {
			addDelay(System.currentTimeMillis() - startTime);
			startTime = System.currentTimeMillis();
		}
		switch (key) {
			case "Tab":		AutoM8.ws.addAction(new TAB(id++), offset++); break;
			case "Enter":	AutoM8.ws.addAction(new ENTER(id++), offset++); break;
			case "Up":		AutoM8.ws.addAction(new UP(id++), offset++); break;
			case "Down":	AutoM8.ws.addAction(new DOWN(id++), offset++); break;
			case "Left":	AutoM8.ws.addAction(new LEFT(id++), offset++); break;
			case "Right":	AutoM8.ws.addAction(new RIGHT(id++), offset++); break;
			default: 		break;
		}
	}
	
	public void addAction(String key) {
		if (delayTracking) {
			addDelay(System.currentTimeMillis() - startTime);
			startTime = System.currentTimeMillis();
		}
		switch (key) {
			case "C":	AutoM8.ws.addAction(new COPY(id++), offset++); break;
			case "V":	AutoM8.ws.addAction(new PASTE(id++), offset++); break;
			case "A":	AutoM8.ws.addAction(new SELECTALL(id++), offset++); break;
		}
	}
	
	public void addType(String s) {
		if (delayTracking) {
			addDelay(System.currentTimeMillis() - startTime);
			startTime = System.currentTimeMillis();
		}
		TYPE t = new TYPE(id++);
		t.setValue(s);
		AutoM8.ws.addAction(t, offset++);
		activeString = "";
	}
	
	public void appendType(String s) {
		activeString = activeString + s;
	}
	
	public void removeType() {
		if (activeString.length() <= 1) {
			activeString="";
		} else {
			activeString = activeString.substring(0, activeString.length() - 1);
		}
	}
	
}
