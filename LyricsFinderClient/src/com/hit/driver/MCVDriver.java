package com.hit.driver;
import java.awt.EventQueue;
import com.hit.controller.MyController;
import com.hit.view.SearchPanel;
import com.hit.view.StartPanel;

/* 
* Song Finder Application
* Manages Song Lyrics Database with Server and Client
* Author: Dan Averin
* Email: dan.averinn@gmail.com
*/

public class MCVDriver {
	public static void main(String[] args)
	{
		MyController control = new MyController();
		 
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					StartPanel startPanel = new StartPanel(control);
					startPanel.createAndShowGUI();
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}
}
