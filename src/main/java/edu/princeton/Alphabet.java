package edu.princeton;

public class Alphabet {
	
	/**
     *  The base-64 alphabet (64 characters).
     */
    public static final Alphabet BASE64 = new Alphabet("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/");

    private char[] alphabet;
    private int[] inverse;       // indices
    private final int R;         // the radix of the alphabet
    
    public Alphabet(String alpha) {
    	// check that alphabet contains no duplicate chars
        boolean[] unicode = new boolean[Character.MAX_VALUE];
        for (int i = 0; i < alpha.length(); i++) {
        	char c = alpha.charAt(i);
        	 if (unicode[c])
                 throw new IllegalArgumentException("Illegal alphabet: repeated character = '" + c + "'");
             unicode[c] = true;
        }
        
        alphabet = alpha.toCharArray();
        R = alpha.length();
        inverse = new int[Character.MAX_VALUE];
        for (int i = 0; i < inverse.length; i++)
            inverse[i] = -1;
        
        // can't use char since R can be as big as 65,536
        for (int c = 0; c < R; c++)
            inverse[alphabet[c]] = c;
    }
    
    public int toIndex(char c) {
        if (c >= inverse.length || inverse[c] == -1) {
            throw new IllegalArgumentException("Character " + c + " not in alphabet");
        }
        return inverse[c];
    }
    
    public int[] toIndices(String s) {
    	char[] source = s.toCharArray();
    	int[] target  = new int[s.length()];
    	for (int i = 0; i < source.length; i++)
            target[i] = toIndex(source[i]);
        return target;
    }
    
    public char toChar(int index) {
        if (index < 0 || index >= R) {
            throw new IndexOutOfBoundsException("Alphabet index out of bounds");
        }
        return alphabet[index];
    }
    
    public String toChars(int[] indices) {
        StringBuilder s = new StringBuilder(indices.length);
        for (int i = 0; i < indices.length; i++)
            s.append(toChar(indices[i]));
        return s.toString();
    }

	public static void main(String[] args) {
		int[]  encoded1 = Alphabet.BASE64.toIndices("NowIsTheTimeForAllGoodMen");
		String decoded1 = Alphabet.BASE64.toChars(encoded1);
		StdOut.println(decoded1);
		
	}

}
