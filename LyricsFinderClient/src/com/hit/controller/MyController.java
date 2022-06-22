package com.hit.controller;
import com.hit.view.SearchPanel;
import java.util.List;

import com.hit.model.MyModel;
import com.hit.model.Request;
import com.hit.model.Response;
import com.hit.model.Song;

public class MyController implements IController{
	MyModel thisModel;

	public MyController()
	{
		thisModel = new MyModel();
	}
	
	public static void updateSongList(List<Song> songListFromModel) {
		SearchPanel.fromControllerSongList(songListFromModel);
	}
	
	public static void updateRequestStatus(boolean status) {
//		SearchPanel.fromControllerSongList(songListFromModel);
	}
	
	@Override
	public void responseToView(Response responseFromServer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void requestToClient(Request requestFromView) {
		// TODO Auto-generated method stub
		
	}
	
	public void searchTitle(String searchPattern)
	{
		thisModel.search(searchPattern, "search_title");
	}
	
	public void searchArtist(String searchPattern)
	{
		thisModel.search(searchPattern, "search_artist");
	}
	
	public void searchLyrics(String searchPattern)
	{
		thisModel.search(searchPattern, "search_lyrics");
	}

}
