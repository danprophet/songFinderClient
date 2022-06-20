package com.hit.client;

public class Request {
	
	String command = "";
	String title = "";
	String artist = "";
	String lyrics = "";
	String search = "";
	int id = -1;
	
	public Request(String command, String title, String artist, String lyrics) // Add Ctor
	{
		this.command = command;
		this.title = title;
		this.artist = artist;
		this.lyrics = lyrics;
	}
	
	public Request(String command, String search) // Search Ctor
	{
		this.command = command;
		this.search = search;
	}
	
	public Request(String command, int id)
	{
		this.command = command;
		this.id = id;
	}
	
	public String getCommand()
	{
		return this.command;
	}
	
	public int getId()
	{
		return this.id;
	}
	
	public String getArtist()
	{
		return this.artist;
	}
	
	public String getTitle()
	{
		return this.title;
	}
	
	public String getLyrics()
	{
		return this.lyrics;
	}
	
	public String getSearchPattern()
	{
		return this.search;
	}
	
}
