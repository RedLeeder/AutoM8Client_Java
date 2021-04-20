package src;

import java.awt.Point;

import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseInputListener;

public class MouseListener implements NativeMouseInputListener {
	
	public static Point p;	
	private Point start;
	private boolean dragging;
	
	public MouseListener() {
		p = new Point(0,0);
	}
	
	public void nativeMouseClicked(NativeMouseEvent e) {
		if (AutoM8.SR.recording()) {
			AutoM8.KL.stringEnd();
			if (e.getButton() == 1) {
				AutoM8.SR.addLeftClick(e.getPoint());
			} else if (e.getButton() == 2) {
				AutoM8.SR.addRightClick(e.getPoint());
			}
		}
		//System.out.println("Mouse Clicked: " + e.getClickCount());
	}

	public void nativeMousePressed(NativeMouseEvent e) {
		if (AutoM8.SR.recording()) {
			start = e.getPoint();
		}
		//System.out.println("Mouse Pressed: " + e.getButton());
	}

	public void nativeMouseReleased(NativeMouseEvent e) {
		if (AutoM8.SR.recording()) {
			if (dragging) {
				dragging = false;
				AutoM8.SR.addMouseDrag(start, e.getPoint());
			}
		}
		//System.out.println("Mouse Released: " + e.getButton());
	}

	public void nativeMouseMoved(NativeMouseEvent e) {
		if (AutoM8.init) {
			p = e.getPoint();
		}
		//System.out.println("Mouse Moved: " + p.getX() + ", " + p.getY());
	}

	public void nativeMouseDragged(NativeMouseEvent e) {
		if (AutoM8.SR.recording()) {
			AutoM8.KL.stringEnd();
			//start = e.getPoint();
			dragging = true;
		}
		//System.out.println("Mouse Dragged: " + e.getX() + ", " + e.getY());
	}
}