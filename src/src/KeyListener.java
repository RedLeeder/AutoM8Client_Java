package src;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

public class KeyListener implements NativeKeyListener {
	
	public boolean ctrl;
	public boolean shift;
	public boolean activeString;
	
	public KeyListener() {
		ctrl = false;
		shift = false;
		activeString = false;
	}
	
    public void nativeKeyPressed(NativeKeyEvent e) {
    	if (e.getKeyCode() == NativeKeyEvent.VC_F8) {
            try {
				GlobalScreen.unregisterNativeHook();
			} catch (NativeHookException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        }
    	
    	if (AutoM8.SR.recording()) {
    		if (e.getKeyCode() == NativeKeyEvent.VC_ESCAPE) {
				AutoM8.SR.disableRecording();
    		} else if (e.getKeyCode() == NativeKeyEvent.VC_CONTROL) {
	    		ctrl = true;
	    	} else if (e.getKeyCode() == NativeKeyEvent.VC_SHIFT) {
	    	} else {
	    		actionCheck(e);
	    	}
    	}
    }

    public void nativeKeyReleased(NativeKeyEvent e) {
        
//    	if (!AutoM8.SR.recording()) {
//    		AutoM8.SM.ShortcutCheck(e);
//    	}
    	
    	if (e.getKeyCode() == NativeKeyEvent.VC_CONTROL) {
	    	ctrl = false;
	    } else if (e.getKeyCode() == NativeKeyEvent.VC_SHIFT) {
	    	shift = false;
	    }
    	
    }

    public void nativeKeyTyped(NativeKeyEvent e) {
    	    	
        System.out.println("Key Typed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
//        ArkBotGUI.GUIText("Key Typed: " + e.getKeyText(e.getKeyCode()));
    }

    private void actionCheck(NativeKeyEvent e) {
    	if (ctrl) {
    		switch (e.getKeyCode()) {
	    		case NativeKeyEvent.VC_C: stringEnd(); AutoM8.SR.addAction("C"); break;
	    		case NativeKeyEvent.VC_V: stringEnd(); AutoM8.SR.addAction("V"); break;
	    		case NativeKeyEvent.VC_A: stringEnd(); AutoM8.SR.addAction("A"); break;
	    		default: System.out.println("No Match");
    		}
    	} else {
    		switch (e.getKeyCode()) {
	    		case NativeKeyEvent.VC_TAB: 	stringEnd(); AutoM8.SR.addKeyEvent("Tab"); break;
	    		case NativeKeyEvent.VC_ENTER: 	stringEnd(); AutoM8.SR.addKeyEvent("Enter"); break;
	    		case NativeKeyEvent.VC_UP: 		stringEnd(); AutoM8.SR.addKeyEvent("Up"); break;
	    		case NativeKeyEvent.VC_DOWN: 	stringEnd(); AutoM8.SR.addKeyEvent("Down"); break;
	    		case NativeKeyEvent.VC_LEFT: 	stringEnd(); AutoM8.SR.addKeyEvent("Left"); break;
	    		case NativeKeyEvent.VC_RIGHT: 	stringEnd(); AutoM8.SR.addKeyEvent("Right"); break;
	    		default: stringModify(e);
    		}
    	}
    }
    
    public void stringModify(NativeKeyEvent e) {
    	if (TypeConverter.KeyConvert(e) == "|BS|"){
    		AutoM8.SR.removeType();
    	} else if (!activeString) {
    		AutoM8.SR.addType(TypeConverter.KeyConvert(e));
    		activeString = true;
    	} else {
    		AutoM8.SR.appendType(TypeConverter.KeyConvert(e));
    	}
    }
    
    public void stringEnd() {
    	if (activeString) {
    		activeString = false;
    	}
    }
    
    
    
//    public static void main(String[] args) {
//        try {
//            GlobalScreen.registerNativeHook();
//        }
//        catch (NativeHookException ex) {
//            System.err.println("There was a problem registering the native hook.");
//            System.err.println(ex.getMessage());
//
//            System.exit(1);
//        }
//
//        GlobalScreen.addNativeKeyListener(new ShortcutManager());
//    }
}
