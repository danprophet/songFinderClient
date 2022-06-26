package com.hit.model;

public interface IModel {
	public void sendRequest(Request outgoingRequest);
	public void updateSearchedSongList(Response fromServer);
}
