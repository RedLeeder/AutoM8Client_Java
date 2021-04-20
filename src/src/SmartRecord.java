package src;

import java.awt.Point;

public class SmartRecord {

	private static boolean recording;
	private static boolean delayTracking;
	private long startTime;
	
	public SmartRecord() {
		recording = false;
		delayTracking = false;
		startTime = 0;
	}
	
	public void enableRecording() {
		System.out.println("Recording: Enabled");
		recording = true;
		delayTracking = false; // Need to assign somewhere
		startTime = System.currentTimeMillis();
	}
	
	public void disableRecording() {
		System.out.println("Recording: Disabled");
		if (AutoM8.KL.activeString) {
			AutoM8.KL.stringEnd();
		}
		recording = false;
	}
	
	public boolean recording() {
		return recording;
	}
	
	public void addDelay(long delay) {
		// TODO
	}
	
	public void addLeftClick(Point p) {
		if (delayTracking) {
			addDelay(System.currentTimeMillis() - startTime);
			startTime = System.currentTimeMillis();
		}
	}
	
	public void addRightClick(Point p) {
		if (delayTracking) {
			addDelay(System.currentTimeMillis() - startTime);
			startTime = System.currentTimeMillis();
		}
	}
	
	public void addMouseDrag(Point start, Point end) {
		if (delayTracking) {
			addDelay(System.currentTimeMillis() - startTime);
			startTime = System.currentTimeMillis();
		}
	}
	
	public void addKeyEvent(String key) {
		if (delayTracking) {
			addDelay(System.currentTimeMillis() - startTime);
			startTime = System.currentTimeMillis();
		}
		Action.Type t = Action.Type.TAB;
		switch (key) {
			case "Tab":		t = Action.Type.TAB; break;
			case "Enter":	t = Action.Type.ENTER; break;
			case "Up":		t = Action.Type.UP; break;
			case "Down":	t = Action.Type.DOWN; break;
			case "Left":	t = Action.Type.LEFT; break;
			case "Right":	t = Action.Type.RIGHT; break;
			default: 		break;
		}
	}
	
	public void addAction(String key) {
		if (delayTracking) {
			addDelay(System.currentTimeMillis() - startTime);
			startTime = System.currentTimeMillis();
		}
		Action.Type t = Action.Type.TAB;
		switch (key) {
			case "C":	t = Action.Type.COPY; break;
			case "V":	t = Action.Type.PASTE; break;
			case "A":	t = Action.Type.SELECTALL; break;
		}
	}
	
	public void addType(String s) {
		if (delayTracking) {
			addDelay(System.currentTimeMillis() - startTime);
			startTime = System.currentTimeMillis();
		}
	}
	
	public void appendType(String s) {
		// TODO
	}
	
	public void removeType() {
		if (AutoM8.KL.activeString) {
			// TODO
		}
	}
	
}
