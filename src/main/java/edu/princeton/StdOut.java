package edu.princeton;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class StdOut {
	private static final String CHARSET_NAME = "UTF-8";
	private static PrintWriter out;
	
	// this is called before invoking any methods
    static {
        try {
            out = new PrintWriter(new OutputStreamWriter(System.out, CHARSET_NAME), true);
        }
        catch (UnsupportedEncodingException e) {
            System.out.println(e);
        }
    }
	
	public static void println() {
        out.println();
    }
	
	public static void println(Object x) {
        out.println(x);
    }
	
	public static void println(boolean x) {
        out.println(x);
    }
	
	public static void println(char x) {
        out.println(x);
    }
	
	public static void println(double x) {
        out.println(x);
    }
	
	public static void println(float x) {
        out.println(x);
    }
	
	public static void println(int x) {
        out.println(x);
    }

	public static void println(long x) {
        out.println(x);
    }
	
	public static void println(short x) {
        out.println(x);
    }
	
	public static void println(byte x) {
        out.println(x);
    }

}
