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
		thisController.updateSongList(fromServer.getSongList());
	}
	
	public static void responseAddRemoveStatus(Response fromServer)
	{
		setLastActionResult(fromServer.getStatus());
		// notify controller:
		
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
	

	public boolean isLastActionResult() {
		return lastActionResult;
	}

	public static void setLastActionResult(boolean lastActionResult) {
		MyModel.lastActionResult = lastActionResult;
	}
	
	
}
