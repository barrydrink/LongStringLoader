package com.penrillian.longstringloader;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LongStringUtils
{
	@NonNull
	public static List<String> getSplitString(String stringToSplit, int stringSplitLength)
	{
		/*
			length to split string needs to be at least as long as the longest series of non-whitespace in the string.
			E.G. if the following is the longest series of non-whitespace in the string
				"licences:"
			then stringSplitLength needs to be at least 9 (8 letters plus a colon)
		 */
		stringSplitLength = Math.max(getLengthOfLongestWord(stringToSplit), stringSplitLength);

		List<String> splitString = new ArrayList<>();

		Pattern p = Pattern.compile("\\G\\s*(.{1,"+ stringSplitLength +"})(?=\\s|$)", Pattern.DOTALL);
		Matcher m2 = p.matcher(stringToSplit);
		while (m2.find())
		{
			splitString.add(m2.group(1));
		}
		return splitString;
	}

	public static int getLengthOfLongestWord(String string)
	{
		int longestWordLength = 0;
		Matcher m = Pattern.compile("\\s*(\\S+)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE).matcher(string);
		while(m.find())
		{
			longestWordLength = Math.max(longestWordLength, m.group(1).length());
		}
		return  longestWordLength;
	}
}
