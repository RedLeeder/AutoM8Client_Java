package src;

//import static java.awt.event.KeyEvent.*;

import java.util.HashMap;

import org.jnativehook.keyboard.NativeKeyEvent;

public class TypeConverter {

	static HashMap<String, String> keywords;
	static HashMap<String, String> convert;
	static HashMap<String, String> convertShift;
	
	public TypeConverter() {
		keywords = new HashMap<String, String>();
		convert = new HashMap<String, String>();
		convertShift = new HashMap<String, String>();
		
		keywords.put("Caps Lock", "");
		keywords.put("Left Shift", "");
		keywords.put("Left Control", "");
		keywords.put("Left Alt", "");
		keywords.put("Right Alt", "");
		keywords.put("Right Control", "");
		keywords.put("Right Shift", "");
		
		keywords.put("Backspace", "|BS|");

		convert.put("Space", " ");
		convert.put("Back Quote", "`");
		convert.put("Minus", "-");
		convert.put("Equals", "=");
		convert.put("Open Bracket", "[");
		convert.put("Close Bracket", "]");
		convert.put("Back Slash", "\\");
		convert.put("Semicolon", ";");
		convert.put("Quote", "'");
		convert.put("Comma", ",");
		convert.put("Period", ".");
		convert.put("Slash", "/");

		convert.put("1", "1");
		convert.put("2", "2");
		convert.put("3", "3");
		convert.put("4", "4");
		convert.put("5", "5");
		convert.put("6", "6");
		convert.put("7", "7");
		convert.put("8", "8");
		convert.put("9", "9");
		convert.put("0", "0");
		
		
		convertShift.put("Space", " ");
		convertShift.put("Back Quote", "~");
		convertShift.put("Minus", "_");
		convertShift.put("Equals", "+");
		convertShift.put("Open Bracket", "{");
		convertShift.put("Close Bracket", "}");
		convertShift.put("Back Slash", "|");
		convertShift.put("Semicolon", ":");
		convertShift.put("Quote", "\"");
		convertShift.put("Comma", "<");
		convertShift.put("Period", ">");
		convertShift.put("Slash", "?");
		
		convertShift.put("1", "!");
		convertShift.put("2", "@");
		convertShift.put("3", "#");
		convertShift.put("4", "$");
		convertShift.put("5", "%");
		convertShift.put("6", "^");
		convertShift.put("7", "&");
		convertShift.put("8", "*");
		convertShift.put("9", "(");
		convertShift.put("0", ")");
	}
	
	public static String KeyConvert(NativeKeyEvent e) {
		String s = NativeKeyEvent.getKeyText(e.getKeyCode());
		String v = "";
		
		if (!AutoM8.KL.shift) {
			if (convert.containsKey(s)) {
				v = convert.get(s);
			} else if (keywords.containsKey(s)){
				return keywords.get(s);
			} else {
				return s.toLowerCase();
			}
		} else {
			if(convertShift.containsKey(s)) {
				v = convertShift.get(s);
			} else if (keywords.containsKey(s)){
				return keywords.get(s);
			} else {
				return s;
			}
		}
		return v;
	}
	
	/*
	public static void Type(char character) {
        switch (character) {
        case '`': doType(VK_BACK_QUOTE); break;
        case '0': doType(VK_0); break;
        case '1': doType(VK_1); break;
        case '2': doType(VK_2); break;
        case '3': doType(VK_3); break;
        case '4': doType(VK_4); break;
        case '5': doType(VK_5); break;
        case '6': doType(VK_6); break;
        case '7': doType(VK_7); break;
        case '8': doType(VK_8); break;
        case '9': doType(VK_9); break;
        case '-': doType(VK_MINUS); break;
        case '=': doType(VK_EQUALS); break;
        case '~': doType(VK_SHIFT, VK_BACK_QUOTE); break;
        //case '!': doType(VK_EXCLAMATION_MARK); break;
        case '!': doType(VK_SHIFT, VK_1); break;
        //case '@': doType(VK_AT); break;
        case '@': doType(VK_SHIFT, VK_2); break;
        //case '#': doType(VK_NUMBER_SIGN); break;
        case '#': doType(VK_SHIFT, VK_3); break;
        //case '$': doType(VK_DOLLAR); break;
        case '$': doType(VK_SHIFT, VK_4); break;
        case '%': doType(VK_SHIFT, VK_5); break;
        //case '^': doType(VK_CIRCUMFLEX); break;
        case '^': doType(VK_SHIFT, VK_6); break;
        //case '&': doType(VK_AMPERSAND); break;
        case '&': doType(VK_SHIFT, VK_7); break;
        //case '*': doType(VK_ASTERISK); break;
        case '*': doType(VK_SHIFT, VK_8); break;
        //case '(': doType(VK_LEFT_PARENTHESIS); break;
        case '(': doType(VK_SHIFT, VK_9); break;
        //case ')': doType(VK_RIGHT_PARENTHESIS); break;
        case ')': doType(VK_SHIFT, VK_0); break;
        //case '_': doType(VK_UNDERSCORE); break;
        case '_': doType(VK_SHIFT, VK_MINUS); break;
        //case '+': doType(VK_PLUS); break;
        case '+': doType(VK_SHIFT, VK_EQUALS); break;
        case '\t': doType(VK_TAB); break;
        case '\n': doType(VK_ENTER); break;
        case '[': doType(VK_OPEN_BRACKET); break;
        case ']': doType(VK_CLOSE_BRACKET); break;
        case '\\': doType(VK_BACK_SLASH); break;
        case '{': doType(VK_SHIFT, VK_OPEN_BRACKET); break;
        case '}': doType(VK_SHIFT, VK_CLOSE_BRACKET); break;
        case '|': doType(VK_SHIFT, VK_BACK_SLASH); break;
        case ';': doType(VK_SEMICOLON); break;
        //case ':': doType(VK_COLON); break;
        case ':': doType(VK_SHIFT, VK_SEMICOLON); break;
        case '\'': doType(VK_QUOTE); break;
        //case '"': doType(VK_QUOTEDBL); break;
        case '"': doType(VK_SHIFT, VK_QUOTE); break;
        case ',': doType(VK_COMMA); break;
        case '<': doType(VK_SHIFT, VK_COMMA); break;
        case '.': doType(VK_PERIOD); break;
        case '>': doType(VK_SHIFT, VK_PERIOD); break;
        case '/': doType(VK_SLASH); break;
        case '?': doType(VK_SHIFT, VK_SLASH); break;
        case ' ': doType(VK_SPACE); break;
        default:
            throw new IllegalArgumentException("Cannot type character " + character);
        }
    }
    */
}
