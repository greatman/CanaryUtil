package net.larry1123.lib.chat;

import java.util.ArrayList;
import java.util.Random;

import net.canarymod.chat.TextFormat;

public class ColorTools extends TextFormat {

    public static ArrayList<String> colorList = new ArrayList<String>();
    public static ArrayList<String> fontList = new ArrayList<String>();

    static final String Black = BLACK;
    static final String Navy = DARK_BLUE;
    static final String Green = GREEN;
    static final String Blue = TURQUIOSE;
    static final String Red = RED;
    static final String Purple = PURPLE;
    static final String Gold = ORANGE;
    static final String LightGray = LIGHT_GRAY;
    static final String Gray = GRAY;
    static final String DarkPurple = BLUE;
    static final String LightGreen = LIGHT_GREEN;
    static final String LightBlue = CYAN;
    static final String Rose = LIGHT_RED;
    static final String LightPurple = PINK;
    static final String Yellow = YELLOW;
    static final String White = WHITE;
    static final String Random = RANDOM;
    static final String Bold = BOLD;
    static final String Strike = STRIKE;
    static final String Underline = UNDERLINED;
    static final String Italic = ITALICS;
    static final String Reset = RESET;
    static final String Marker = MARKER;

    static {
	colorList.add(Black);
	colorList.add(Blue);
	colorList.add(DarkPurple);
	colorList.add(Gold);
	colorList.add(Gray);
	colorList.add(Green);
	colorList.add(LightBlue);
	colorList.add(LightGray);
	colorList.add(LightGreen);
	colorList.add(LightPurple);
	colorList.add(Navy);
	colorList.add(Purple);
	colorList.add(Red);
	colorList.add(Rose);
	colorList.add(White);
	colorList.add(Yellow);
	fontList.add(Bold);
	fontList.add(Strike);
	fontList.add(Underline);
	fontList.add(Italic);
	fontList.add(Marker);
	fontList.add(Random);
	fontList.add(Reset);
    }

    /**
     * Adds a random Color to each Char in a string.
     * 
     * @param string
     * @return Randomly Colored String
     */
    public static String CharRandomColor(String string) {
	char[] str = string.toCharArray();
	String retrn = "";
	for (char car : str) {
	    retrn = retrn + RandomColor() + car;
	}
	return retrn;
    }

    public static String RandomColor() {
	Random random = new Random();
	return colorList.get(random.nextInt(colorList.size() - 1));
    }

}
