package com.penrillian.longstringloader;

public class LongStringLoaderException extends Throwable
{
	private String message;

	public LongStringLoaderException(String message)
	{
		this.message = message;
	}

	@Override
	public String getLocalizedMessage()
	{
		return message;

	}
}
