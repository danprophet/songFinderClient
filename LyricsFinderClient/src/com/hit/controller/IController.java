package com.hit.controller;

import com.hit.model.Request;
import com.hit.model.Response;

public interface IController {
	
	public void responseToView(Response responseFromServer);
	public void requestToClient(Request requestFromView);

}
