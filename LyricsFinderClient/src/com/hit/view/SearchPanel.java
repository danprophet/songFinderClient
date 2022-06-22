package com.hit.view;
import com.hit.controller.MyController;
import com.hit.model.Song;
import java.util.List;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class SearchPanel extends JFrame implements ActionListener {
  private JLabel searchLabel;
  private JRadioButton titlesRadiobtn;
  private JRadioButton artistsRadiobtn;
  private JRadioButton lyricsRadiobtn;
  private ButtonGroup radioBtns;
  private JLabel searchPtrnLabel;
  private JTextField searchPtrnTextField;
  private JButton searchPtrnJBtn;
  private JList resultSongList;
  private JButton openSongBtn;
  private JLabel jcomp10;
  private JTextField jcomp11;
  private JLabel jcomp12;
  private JTextField jcomp13;
  private JLabel jcomp14;
  private JTextField jcomp15;
  private JLabel jcomp16;
  private JTextArea jcomp17;
  
  static List<Song> songList;
  private MyController controller;
  
  static String searchBtn = "searchBtn";
  static String openBtn = "openBtn";

  public SearchPanel() {
	  this.controller = new MyController();
      //construct preComponents
      String[] resultSongListItems = {};
//      this.setPreferredSize(new Dimension(500,500));
      this.setBounds(100, 100, 1020, 470);
      this.setResizable(false);
      //construct components
      searchLabel = new JLabel ("Search:");
      titlesRadiobtn = new JRadioButton ("Song Titles");
      artistsRadiobtn = new JRadioButton ("Song Artists");
      lyricsRadiobtn = new JRadioButton ("Song Lyrics");
      
      titlesRadiobtn.setActionCommand("titleBtn");
      artistsRadiobtn.setActionCommand("artistBtn");
      lyricsRadiobtn.setActionCommand("lyricsBtn");

      
      radioBtns = new ButtonGroup();
      radioBtns.add(titlesRadiobtn);
      radioBtns.add(artistsRadiobtn);
      radioBtns.add(lyricsRadiobtn);
      
      searchPtrnLabel = new JLabel ("Search Pattern:");
      searchPtrnTextField = new JTextField (5);
      searchPtrnJBtn = new JButton ("Search");
      searchPtrnJBtn.setActionCommand(this.searchBtn);
      resultSongList = new JList (resultSongListItems);
      openSongBtn = new JButton ("Open");
      openSongBtn.setActionCommand(this.openBtn);
      jcomp10 = new JLabel ("Song Title:");
      jcomp11 = new JTextField (5);
      jcomp12 = new JLabel ("Artist:");
      jcomp13 = new JTextField (5);
      jcomp14 = new JLabel ("Song ID:");
      jcomp15 = new JTextField (5);
      jcomp16 = new JLabel ("Lyrics:");
      jcomp17 = new JTextArea (5, 5);

      //adjust size and set layout
      setPreferredSize (new Dimension (1018, 440));
      setLayout (null);

      //add components
      add (searchLabel);
      add (titlesRadiobtn);
      add (artistsRadiobtn);
      add (lyricsRadiobtn);
      add (searchPtrnLabel);
      add (searchPtrnTextField);
      add (searchPtrnJBtn);
      add (resultSongList);
      add (openSongBtn);
      add (jcomp10);
      add (jcomp11);
      add (jcomp12);
      add (jcomp13);
      add (jcomp14);
      add (jcomp15);
      add (jcomp16);
      add (jcomp17);

      //set component bounds (only needed by Absolute Positioning)
      searchLabel.setBounds (5, 5, 50, 25);
      titlesRadiobtn.setBounds (89, 5, 90, 25);
      artistsRadiobtn.setBounds (202, 5, 100, 25);
      lyricsRadiobtn.setBounds (323, 5, 100, 25);
      searchPtrnLabel.setBounds (5, 35, 100, 25);
      searchPtrnTextField.setBounds (100, 35, 215, 25);
      searchPtrnJBtn.setBounds (320, 35, 110, 25);
      resultSongList.setBounds (5, 65, 310, 360);
      openSongBtn.setBounds (320, 400, 110, 25);
      jcomp10.setBounds (500, 5, 100, 25);
      jcomp11.setBounds (570, 5, 425, 25);
      jcomp12.setBounds (500, 35, 100, 25);
      jcomp13.setBounds (570, 35, 425, 25);
      jcomp14.setBounds (500, 65, 100, 25);
      jcomp15.setBounds (570, 65, 425, 25);
      jcomp16.setBounds (500, 100, 100, 25);
      jcomp17.setBounds (500, 125, 495, 300);
      
      //Register a listener for the radio buttons.
      searchPtrnJBtn.addActionListener(this);
      openSongBtn.addActionListener(this);      
  }

@Override
public void actionPerformed(ActionEvent e) {
	String action = e.getActionCommand();
	switch (action)
	{
	case "searchBtn":
	{
		if (this.radioBtns.getSelection() != null) 
		{
			String pattern = this.searchPtrnTextField.getText();
			String selectedBtn = this.radioBtns.getSelection().getActionCommand();
			switch (selectedBtn)
			{
			case "titleBtn":
				System.out.println("Search Title " + pattern);
				this.controller.searchTitle(pattern);
				break;
			case "artistBtn":
				System.out.println("Search artist " + pattern);
				this.controller.searchArtist(pattern);
				break;
			case "lyricsBtn":
				System.out.println("Search Lyrics " + pattern);
				this.controller.searchLyrics(pattern);
				break;
			default:
				System.out.println("no radio button selected");
				break;
			}
		}
		break;
	}
	
		
	case "openBtn":
		System.out.println("Open clicked");
		break;
	}
}

public static void fromControllerSongList(List<Song> songListFromController)
{
	songList = songListFromController;
}

private void updateSongList()
{
	
}


//  public static void main (String[] args) {
//      JFrame frame = new JFrame ("MyPanel");
//      frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
//      frame.getContentPane().add (new SearchPanel());
//      frame.pack();
//      frame.setVisible (true);
//  }
}
