package com.hit.client;
import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class Client implements Runnable
{
	String parsedRequest;
	String receievedResponse;
	String ipAddress = "127.0.0.1";
	int Port = 34567;
	Response parsedResponse;

	
	public Client()
	{
	}
	
	

	public String requestToString(Request request)
	{
		String requestAsStr = "";
		
		JsonSerializer<Request> serializer = new JsonSerializer<Request>()
				{

					@Override
					public JsonElement serialize(com.hit.client.Request arg0, Type arg1, JsonSerializationContext arg2) {
						JsonObject request = new JsonObject();
						
						JsonObject headers = new JsonObject();
						JsonObject body = new JsonObject();
						
						headers.addProperty("action", arg0.getCommand());
						
						switch (arg0.getCommand())
						{
						case "add":
							body.addProperty("title", arg0.getTitle());
							body.addProperty("artist", arg0.getArtist());
							body.addProperty("lyrics", arg0.getLyrics());
							break;
						case "remove":
							body.addProperty("id", arg0.getId());
							break;
						case "search_title":
						case "search_artist":
						case "search_lyrics":
							body.addProperty("string", arg0.getSearchPattern());
							break;
						}
						
						request.add("headers", headers);
						request.add("body", body);
						
						return request;
					}
				};
		
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Request.class, serializer);
		Gson tempGson = gsonBuilder.create();
		requestAsStr = tempGson.toJson(request);
		System.out.println("[Client] Serialized request");
		
		return requestAsStr;
	}
	
	public Response handleResponseFromServer(String response)
	{
		Response returnResponse = null;
		JsonDeserializer<Response> deserializer = new JsonDeserializer<Response>()
				{

					@Override
					public Response deserialize(JsonElement arg0, Type arg1, JsonDeserializationContext arg2)
							throws JsonParseException 
					{
						Response parsedResponse = null;
						JsonObject jsonObject = arg0.getAsJsonObject();
						JsonObject headers = jsonObject.get("headers").getAsJsonObject(); // the command
						JsonObject body = jsonObject.get("body").getAsJsonObject(); // the contenet of the response
						
						String command = headers.get("command").getAsString();
						
						switch(command)
						{
						case "add":
						case "remove":
							boolean status = body.get("status").getAsBoolean();
							parsedResponse = new Response(command, status);
							break;
						case "search_title":
						case "search_artist":
						case "search_lyrics":
							{
								JsonArray songArray = body.get("songList").getAsJsonArray();
								List<Song> songList = new ArrayList<Song>();
								
								for (int i=0 ; i<songArray.size() ; i++)
								{
									JsonObject currentSong = songArray.get(i).getAsJsonObject();
									String title = currentSong.get("title").getAsString();
									int id = currentSong.get("id").getAsInt();
									String artist = currentSong.get("artist").getAsString();
									String lyrics = currentSong.get("lyrics").getAsString();
									
									Song newSong = new Song(title, id, artist, lyrics);
									songList.add(newSong);
								}
								
								parsedResponse = new Response(command, songList);
							}
						default:
							System.out.println("[Client] Request error");
							break;
						}
						return parsedResponse;
					}
				};
		
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Response.class, deserializer);
		Gson tempGson = gsonBuilder.create();
		returnResponse = tempGson.fromJson(response, Response.class);
		System.out.println("[Client] Parsed response");
				
		return returnResponse;
	}
	
	@Override
	public void run() 
	{
		try 
		{
			Socket mySocket = new Socket(this.ipAddress, this.Port); // open Socket toward server
			System.out.println("[Client] Server Socket "+ mySocket);
			
			// Sending to server:
			System.out.println("[Client] Sending Server Request: \n" + this.parsedRequest);
			PrintWriter output = new PrintWriter(new OutputStreamWriter(mySocket.getOutputStream()));
			output.println(this.parsedRequest);
			output.flush();
			
			// Receiveing From Server:
			BufferedReader input = new BufferedReader(new InputStreamReader(mySocket.getInputStream()));
			String serverResponse = input.readLine(); // Response Class as String
			System.out.println("[Client] Received From Server Response: \n" + serverResponse);
			this.parsedResponse = handleResponseFromServer(serverResponse);
			
			// To Model here:
			
			
			//
			System.out.println("[Client] Close Client");
			mySocket.close();
			output.close();
			input.close();
		} 
		
		catch (IOException e) 
		{
			e.printStackTrace();
		} 
		
	}

}
