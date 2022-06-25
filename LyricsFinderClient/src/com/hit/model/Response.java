package com.hit.model;
import java.util.List;

public class Response {
	private String action = "empty";
	private boolean status = false;
	private List<Song> resultSongList = null;
	
	public Response(String action, boolean status)
	{
		this.action = action;
		this.status = status;
	}
	
	public Response (String action, List<Song> resultList)
	{
		this.action = action;
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
