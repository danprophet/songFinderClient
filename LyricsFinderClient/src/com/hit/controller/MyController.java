package com.hit.controller;
import com.hit.view.SearchPanel;
import com.hit.view.ManageDBPanel;
import java.util.List;

import javax.swing.event.ChangeListener;

import com.hit.model.MyModel;
import com.hit.model.Request;
import com.hit.model.Response;
import com.hit.model.Song;

public class MyController implements IController{
	MyModel thisModel;
	ChangeListener listener;

	public MyController()
	{
		thisModel = new MyModel(this);
	}
	
	public void updateSongList(List<Song> songListFromModel) {
		SearchPanel.fromControllerSongList(songListFromModel);
		notifyListener();
	}
	
	public void updateDB(List<Song> songListFromModel) {
		ManageDBPanel.fromControllerSongList(songListFromModel);
	}
	
	public void addRemoveStatus(String action, boolean status) {
		ManageDBPanel.addRemoveStatus(action, status);
	}
	
    void notifyListener(){
        if(listener != null){
            listener.stateChanged(null);
        }
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
	
	public void addSong(String title, String artist, String lyrics)
	{
		thisModel.addSong(title, artist, lyrics);
	}
	
	public void removeSong(int songID)
	{
		thisModel.removeSong(songID);
	}
	
	public void getDB()
	{
		thisModel.getDB();
	}
	
    public void setListener(ChangeListener listener) {
        this.listener = listener;
    }

}
