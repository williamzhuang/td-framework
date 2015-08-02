package com.riforin.gameobjects;

/**
 * Class representing the path to be taken. 
 * @author William Zhuang
 * TODO: Modify class for path finding. 
 */
public class Path {
	
	int[] head;	// Coordinates of the current position in the path.
	Path next;	// Rest of the path.
	boolean isEnd;// Returns true if this is the end of the path. 
	
	public Path(int[] head0, Path next0, boolean isEnd0) {
		head = head0;
		next = next0;
		isEnd = isEnd0;
	}
}
