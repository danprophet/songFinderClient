package com.hit.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.Toolkit;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;


public class StartPanel extends JPanel implements ActionListener{
	
    static String searchString = "searchMode";
    static String manageString = "manageDB";
    SearchPanel searchPanelGui = new SearchPanel();
    
	public StartPanel()
	{
		super (new BorderLayout());
		
		this.setPreferredSize(new Dimension(200,100));
//		this.setBounds(100, 100, 1020, 470);
        JButton searchMode = new JButton("Search Mode");
        searchMode.setActionCommand(searchString);
        searchMode.setSelected(true);
        
        JButton manageSongDB = new JButton("Manage Songs DB");
        manageSongDB.setActionCommand(manageString);
        manageSongDB.setSelected(true);
        
        JPanel buttonPanel = new JPanel(new GridLayout(0, 1));
        buttonPanel.add(searchMode);
        buttonPanel.add(manageSongDB);
      
        add(buttonPanel);
        
        //Register a listener for the radio buttons.
        searchMode.addActionListener(this);
        manageSongDB.addActionListener(this);
	}
	
	private static void createAndShowGUI()
	{
        //Create and set up the window.
        JFrame frame = new JFrame("StartPanel");
        frame.setResizable(false);
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage("src\\com\\hit\\resources\\iconDisc.jpg"));
        frame.setBackground(Color.cyan);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        //Create and set up the content pane.
        JComponent newContentPane = new StartPanel();

        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
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
			System.out.println("Manage DB clicked.");
			break;
		default:
			System.out.println("Unrecognized Command.");
			break;
		}		
	}

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
	
}
