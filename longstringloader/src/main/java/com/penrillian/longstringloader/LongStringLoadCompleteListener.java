package com.penrillian.longstringloader;

/**
 * Interface to provide a callback once the long string
 * has been fully loaded
 */
public interface LongStringLoadCompleteListener
{
	/**
	 * Called once the long string has been processed.
	 * The UI can respond by, for example, hiding a loading spinner
	 */
	void onLongStringLoadComplete();
}
