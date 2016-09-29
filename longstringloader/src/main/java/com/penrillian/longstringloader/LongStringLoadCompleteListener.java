package com.penrillian.longstringloader;

/**
 * Interface to provide a callback once the long string
 * has been fully loaded
 */
public interface LongStringLoadCompleteListener
{
	/**
	 * Called once the long string has been fully loaded
	 */
	void onStringLoadComplete();
}
