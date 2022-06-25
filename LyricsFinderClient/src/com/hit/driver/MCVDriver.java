package com.hit.driver;
import java.awt.EventQueue;
import com.hit.controller.MyController;
import com.hit.view.SearchPanel;
import com.hit.view.StartPanel;

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