package com.hit.view;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Toolkit;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import com.hit.controller.MyController;
// icon: https://icons8.com/icon/wHwLtQV8HEWQ/music-record Music Record icon
// background: Photo by Elza Kurbanova https://unsplash.com/es/@kurbanova on Unsplash

public class StartPanel extends JPanel implements ActionListener{
	
    static String searchString = "searchMode";
    static String manageString = "manageDB";
    SearchPanel searchPanelGui = new SearchPanel();
    ManageDBPanel addRemovePanelGui = new ManageDBPanel();
    MyController control;
    
    public StartPanel (MyController passedController)
    {
    	this.control = passedController;
    	this.searchPanelGui.setControoler(passedController);
    	this.addRemovePanelGui.setControoler(passedController);
    	this.control.setListener(e->searchPanelGui.updateView());
    }
	public StartPanel()
	{
        //construct components
        JButton searchMode = new JButton("Search Mode");
        searchMode.setActionCommand(searchString);
        searchMode.setBackground(new Color( 130, 224, 170 ));

        JButton manageSongDB = new JButton("Manage Songs DB");
        manageSongDB.setActionCommand(manageString);
        manageSongDB.setBackground(new Color(171, 235, 198));

        //adjust size and set layout
		this.setPreferredSize(new Dimension(400,500));
        FlowLayout layout = new FlowLayout();
        layout.setHgap (8);
        layout.setVgap (450);
        this.setLayout (layout);
        
        //add components
        add(searchMode);
        add(manageSongDB);
        
        //Register a listener for the radio buttons.
        searchMode.addActionListener(this);
        manageSongDB.addActionListener(this); 
	}
	
	@Override
	  protected void paintComponent(Graphics g) {

	    super.paintComponent(g);
	    BufferedImage img = null;
	    try {
			img = ImageIO.read(new File("src\\com\\hit\\resources\\background.PNG"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        g.drawImage(img, 1, 1, 400, 500, null);
	}
	
	public static void createAndShowGUI()
	{
        //Create and set up the window.
        JFrame frame = new JFrame("Song Finder");
        frame.setResizable(false);
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage("src\\com\\hit\\resources\\icon.png"));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Create and set up the content pane.
        JComponent newContentPane = new StartPanel();

        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);
        
        //Display the window.
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		
		switch (command)
		{
		case "searchMode":
			this.searchPanelGui.setVisible(true);
			System.out.println("Search clicked.");
			break;
		case "manageDB":
			this.addRemovePanelGui.setVisible(true);
			System.out.println("Manage DB clicked.");
			break;
		default:
			System.out.println("Unrecognized Command.");
			break;
		}		
	}
	
}
