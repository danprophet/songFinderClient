package com.hit.model;
import java.util.List;

public class MyModel implements IModel{

	private static List<Song> mySongList;
	private static int amountOfSongs;
	private static boolean lastActionResult;
	
	public MyModel()
	{
		
	}
	
	public void notifyController()
	{
		
	}
	
	public static void updateSearchedSongList(Response fromServer)
	{
		setMySongList(fromServer.getSongList());
		setAmountOfSongs(fromServer.getSongList().size());
	}
	
	public static void responseAddRemoveStatus(Response fromServer)
	{
		setLastActionResult(fromServer.getStatus());
	}

	
	
	
	public static List<Song> getMySongList() {
		return mySongList;
	}

	public static void setMySongList(List<Song> mySongList) {
		MyModel.mySongList = mySongList;
	}

	public static int getAmountOfSongs() {
		return amountOfSongs;
	}

	public static void setAmountOfSongs(int amountOfSongs) {
		MyModel.amountOfSongs = amountOfSongs;
	}

	public boolean isLastActionResult() {
		return lastActionResult;
	}

	public static void setLastActionResult(boolean lastActionResult) {
		MyModel.lastActionResult = lastActionResult;
	}
	
	
}
