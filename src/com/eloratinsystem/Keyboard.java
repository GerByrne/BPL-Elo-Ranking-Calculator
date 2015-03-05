// Basic line-buffered keyboard input. 

// Author: J. M. Morris 
// Version 1.3      February 29th 2000.
//
//
package com.eloratinsystem; 

import java.io.*;

public class Keyboard {
    private static BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in));
    private static String buffer = "";
    private static int p = 1; // buffer[p..] contains next input

    private static String getToken() throws IOException {
        while (buffer != null && (p>= buffer.length() || 
                Character.isWhitespace(buffer.charAt(p)))) {
            if (p>= buffer.length()) {
                buffer = br.readLine();
                p = 0;
            }
            else p++;
        }
        if (buffer == null) throw new IOException("Unexpected end of file.");
        int t = p;
        p++;
        while(p < buffer.length() &&
                !(Character.isWhitespace(buffer.charAt(p))))
            p++;
        p++;
        return(buffer.substring(t,p-1));
    }

    public static int readInt() {
    // Consume and return an integer. Trailing delimiter consumed.
        try {   return Integer.parseInt(getToken());
        } catch (Exception e) {
            System.err.println("IO Exception in Keyboard.readInt");
            return 0;
        }
    }   

    public static boolean readBoolean() {
    // Consume and return a boolean. Trailing delimiter consumed.
    // Any string other than "true" (case ignored) is treated as false.
        try {   return new Boolean(getToken()).booleanValue();
        } catch (Exception e) {
            System.err.println("IO Exception in Keyboard.readBoolean");
            return false;
        }
    }

    public static double readDouble() {
    // Consume and return a double. Trailing delimiter consumed.
        try {return new Double(getToken()).doubleValue();
        } catch (Exception ioe) {
            System.err.println("IO Exception in Keyboard.readDouble");
            return 0.0;
        }
    }

    public static String readToken() {
    // Consume and return a token. Trailing delimiter consumed.
    // A token is a maximal sequence of non-whitespace characters.
    // null returned on end of file
        try {
            while (buffer != null && (p>= buffer.length() || 
                Character.isWhitespace(buffer.charAt(p)))) {
                if (p>= buffer.length()) {
                    buffer = br.readLine();
                    p = 0;
                }
                else p++;
            }
            if (buffer == null) return null;
            int t = p;
            p++;
            while(p < buffer.length() &&
                    !(Character.isWhitespace(buffer.charAt(p))))
                p++;
            p++;
            return(buffer.substring(t,p-1));
        } catch (IOException ioe) {
            System.err.println("IO Exception in Keyboard.readToken()");
            return "";
        } 
    }
    
    public static char readChar() {
    //Consume and return a character (which may be an end-of-line).
        try { 
            if (buffer != null && p>buffer.length()) {
                buffer = br.readLine();
                p = 0;
            }
            if (buffer == null) throw new IOException("Unexpected end of file."); 
            if (p == buffer.length()) { // supply end-of-line
                p++; 
                return('\n'); 
            }       
            else {
                p++;
                return buffer.charAt(p-1); 
            } 
        } catch (IOException ioe) {
                System.err.println("IO Exception in Keyboard.readChar()");
                return (char)0;
        }
    }

    public static char peekChar() {
    // The next available character if any (which may be an end-of-line). The
    // character is not consumed. If buffer is empty return null character.
        if (buffer == null || p>buffer.length()) return('\000'); 
        else if (p == buffer.length()) return('\n'); 
        else return buffer.charAt(p); 
    }

    public static String readString() {
    // Consume and return the remainder of current line (end-of-line discarded).
    // null returned on end of file
        try {
            if (buffer!= null && p>buffer.length()) {
                buffer = br.readLine();
                p = 0;
            }   
            if (buffer == null) return null; 
            int t = p;  p = buffer.length() + 1;
            return buffer.substring(t);
       } catch (IOException ioe) {
            System.err.println("IO Exception in Keyboard.readString()");
            return "";
       } 
    }

    public static int available() {
    // Number of characters available on this line (including end-of-line, 
    // which counts as one character, i.e. '\n')
        if (buffer == null) return 0;
        else return (buffer.length()+1-p);
    }
    
    public static boolean hasMoreTokens() {
    // Are there more tokens on the current line?
        if (buffer == null) return false;
        int q = p; 
        while (q<buffer.length() && Character.isWhitespace(buffer.charAt(q))) q++;
        return (q<buffer.length());
    }
    
    public static void skipLine() {
    // Skip any remaining input on this line.
        if (buffer != null) p = buffer.length() + 1;
    }

    public static void skipWhitespace() {
    // Consumes input until a non-whitespace character is entered (which
    // is not consumed).
        try {
            while (buffer != null && (p>= buffer.length() || 
                    Character.isWhitespace(buffer.charAt(p)))) {
                if (p>= buffer.length()) {
                        buffer = br.readLine(); 
                    p = 0;
                }
                else p++;
            }
        } catch (IOException ioe) {
            System.err.println("IO Exception in Keyboard.skipWhitespace()");
        }
    }   
    
    public static boolean EndOfFile() { // More characters? 
        // This method is intended for use when keyboard is redirected to file
        if (available()>0) return false;
        try { buffer = br.readLine(); 
        } catch (IOException ioe) {
            System.err.println("IO Exception in Keyboard.readChar()");
        }
        p = 0;
        return (buffer == null);
    }
}

