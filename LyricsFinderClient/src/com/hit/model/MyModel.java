package com.hit.model;
import java.util.List;
import com.hit.controller.MyController;

public class MyModel implements IModel{

	private static boolean lastActionResult;
	MyController thisController;
	
	public MyModel(MyController controller)
	{
		thisController = controller;
	}
	
	
	public void sendRequest(Request outgoingRequest)
	{
		Client myClient = new Client(this);
		myClient.setRequest(outgoingRequest);
		Thread t1 = new Thread(myClient);
		t1.start();
	}
	
	public void updateSearchedSongList(Response fromServer)
	{
		// notify controller:
		String action = fromServer.getAction();
		
		switch(action)
		{
		case "get_db":
			thisController.updateDB(fromServer.getSongList());
			break;
		case "search_title":
		case "search_artist":
		case "search_lyrics":
			thisController.updateSongList(fromServer.getSongList());
			break;
		case "add":
		case "remove":
			thisController.addRemoveStatus(action, fromServer.getStatus());
			break;
		default:
			System.out.println("[Model] Unrecognized action (Response)");
			break;
		}
	}
	
	public static void responseAddRemoveStatus(Response fromServer)
	{
		setLastActionResult(fromServer.getStatus());
		// notify controller:
		MyController.updateRequestStatus(lastActionResult);
	}

	public void search(String pattern, String action)
	{
		Request newRequest = null;
		
		switch (action)
		{
		case "search_title":
			newRequest = new Request("search_title", pattern);
			break;
		case "search_artist":
			newRequest = new Request("search_artist", pattern);
			break;
		case "search_lyrics":
			newRequest = new Request("search_lyrics", pattern);
			break;
		}
		
		this.sendRequest(newRequest);
	}
	
	public void addSong(String title, String artist, String lyrics)
	{
		Request newRequest = new Request("add", title, artist, lyrics);
		this.sendRequest(newRequest);
	}
	
	public void removeSong(int songID)
	{
		Request newRequest = new Request("remove", songID);
		this.sendRequest(newRequest);
	}
	
	public void getDB()
	{
		Request newRequest = new Request("get_db");
		this.sendRequest(newRequest);
	}

	public boolean isLastActionResult() {
		return lastActionResult;
	}

	public static void setLastActionResult(boolean lastActionResult) {
		MyModel.lastActionResult = lastActionResult;
	}
	
	
}
