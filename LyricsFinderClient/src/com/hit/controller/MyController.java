package com.hit.controller;

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
	
	@Override
	public void responseToView(Response responseFromServer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void requestToClient(Request requestFromView) {
		// TODO Auto-generated method stub
		
	}
	
	public List<Song> search(String searchPattern)
	{
		return 
	}

}
