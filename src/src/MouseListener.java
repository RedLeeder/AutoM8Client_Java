package src;

import java.awt.Point;
import com.google.gson.*;

import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseInputListener;

public class MouseListener implements NativeMouseInputListener {
	
	public static Point p;	
	private Point start;
	private boolean dragging;
	private static Gson g;
	
	public MouseListener() {
		p = new Point(0,0);
		g = new Gson();
	}
	
	public void nativeMouseClicked(NativeMouseEvent e) {
//		System.out.println("Mouse Clicked: " + e.getClickCount());
		if (AutoM8.SR.recording()) {
			AutoM8.KL.stringEnd();
			if (e.getButton() == 1) {
				AutoM8.SR.addLeftClick(e.getPoint());
			} else if (e.getButton() == 2) {
				AutoM8.SR.addRightClick(e.getPoint());
			}
		}
	}

	public void nativeMousePressed(NativeMouseEvent e) {
//		System.out.println("Mouse Pressed: " + e.getButton());
		if (AutoM8.SR.recording()) {
			start = e.getPoint();
		}
	}

	public void nativeMouseReleased(NativeMouseEvent e) {
//		System.out.println("Mouse Released: " + e.getButton());
		if (AutoM8.SR.recording()) {
			if (dragging) {
				dragging = false;
				AutoM8.SR.addMouseDrag(start, e.getPoint());
			}
		}
	}

	public void nativeMouseMoved(NativeMouseEvent e) {
		if (AutoM8.init) {
			p = e.getPoint();
//			System.out.println("Mouse Moved: " + e.getX() + ", " + e.getY());
		}
	}

	public void nativeMouseDragged(NativeMouseEvent e) {
//		System.out.println("Mouse Dragged: " + e.getX() + ", " + e.getY());
		if (AutoM8.SR.recording()) {
			AutoM8.KL.stringEnd();
			//start = e.getPoint();
			dragging = true;
		}
	}
}