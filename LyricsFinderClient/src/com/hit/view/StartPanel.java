package com.hit.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class StartPanel extends JPanel implements ActionListener{
	
	public StartPanel()
	{
		super (new BorderLayout());
		
        JButton searchMode = new JButton("Search Mode");
        searchMode.setSelected(true);
        
        JButton manageSongDB = new JButton("Manage Songs DB");
        manageSongDB.setSelected(true);
        
        ButtonGroup group = new ButtonGroup();
        group.add(searchMode);
        group.add(manageSongDB);
        
        JPanel buttonPanel = new JPanel(new GridLayout(0, 1));
        buttonPanel.add(searchMode);
        buttonPanel.add(manageSongDB);
        
        
        add(buttonPanel);
	}
	
	private static void createAndShowGUI()
	{
        //Create and set up the window.
        JFrame frame = new JFrame("StartPanel");
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
		// TODO Auto-generated method stub
		
	}

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
	
}
