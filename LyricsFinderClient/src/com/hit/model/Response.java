package com.hit.model;
import java.util.List;

public class Response {
	String action = "empty";
	boolean status = false;
	List<Song> resultSongList = null;
	
	public Response(String action, boolean status)
	{
		this.status = status;
	}
	
	public Response (String action, List<Song> resultList)
	{
		this.resultSongList = resultList;
	}
	
	public String getAction()
	{
		return this.action;
	}
	
	public boolean getStatus()
	{
		return this.status;
	}

	public List<Song> getSongList()
	{
		return this.resultSongList;
	}
	
}
