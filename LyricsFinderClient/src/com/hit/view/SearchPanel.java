package com.hit.view;
import com.hit.controller.MyController;
import com.hit.model.Song;
import java.util.List;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class SearchPanel extends JFrame implements ActionListener {
  private JLabel searchLabel;
  private JRadioButton titlesRadiobtn;
  private JRadioButton artistsRadiobtn;
  private JRadioButton lyricsRadiobtn;
  private ButtonGroup radioBtns;
  private JLabel searchPtrnLabel;
  private JTextField searchPtrnTextField;
  private JButton searchPtrnJBtn;
  private JScrollPane resultSongListScroll;
  private JTable  resultSongList;  
  private JButton refreshBtn;
  private JLabel songTitleJLable;
  private JTextField songTitleJTextField;
  private JLabel artistJLable;
  private JTextField artistJTextField;
  private JLabel songIDJLable;
  private JTextField songIDJTextField;
  private JLabel lyricsJLable;
  private JScrollPane lyricsScroll;
  private JTextArea lyricsJTextField;
  String[] resultColumnNames = {"Song Name", "Artist", "Song ID", "#"};
  static List<Song> songList;
  public static MyController controller;
  
  // button codes
  static String searchBtn = "searchBtn";
  static String openBtn = "openBtn";
  
  public SearchPanel() {
      //construct preComponents
      Object[][] resultSongListItems = {{"No Song In List","No Song In List" , 0, 0}};
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
      //JTable 
      resultSongList = new JTable(new DefaultTableModel(resultSongListItems, resultColumnNames));
      resultSongListScroll = new JScrollPane(resultSongList);
      resultSongList.setDefaultEditor(Object.class, null); //JTable set not editable
      
      refreshBtn = new JButton ("Refresh");
      refreshBtn.setActionCommand(this.openBtn);
      songTitleJLable = new JLabel ("Song Title:");
      songTitleJTextField = new JTextField (5);
      songTitleJTextField.setEditable(false);
      artistJLable = new JLabel ("Artist:");
      artistJTextField = new JTextField (5);
      artistJTextField.setEditable(false);
      songIDJLable = new JLabel ("Song ID:");
      songIDJTextField = new JTextField (5);
      songIDJTextField.setEditable(false);
      lyricsJLable = new JLabel ("Lyrics:");
      
      lyricsJTextField = new JTextArea (5, 5);
      lyricsJTextField.setEditable(false);
      lyricsScroll = new JScrollPane(lyricsJTextField);

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
      add (resultSongListScroll);
      add (refreshBtn);
      add (songTitleJLable);
      add (songTitleJTextField);
      add (artistJLable);
      add (artistJTextField);
      add (songIDJLable);
      add (songIDJTextField);
      add (lyricsJLable);
      add (lyricsScroll);

      //set component bounds (only needed by Absolute Positioning)
      searchLabel.setBounds (10, 5, 50, 25);
      titlesRadiobtn.setBounds (89, 5, 90, 25);
      artistsRadiobtn.setBounds (202, 5, 100, 25);
      lyricsRadiobtn.setBounds (323, 5, 100, 25);
      searchPtrnLabel.setBounds (10, 35, 100, 25);
      searchPtrnTextField.setBounds (105, 35, 280, 25);
      searchPtrnJBtn.setBounds (390, 35, 90, 25);
      resultSongListScroll.setBounds (10, 65, 375, 360);
      resultSongList.setBounds (10, 65, 375, 360);
      refreshBtn.setBounds (390, 400, 90, 25);
      songTitleJLable.setBounds (500, 5, 100, 25);
      songTitleJTextField.setBounds (570, 5, 425, 25);
      artistJLable.setBounds (500, 35, 100, 25);
      artistJTextField.setBounds (570, 35, 425, 25);
      songIDJLable.setBounds (500, 65, 100, 25);
      songIDJTextField.setBounds (570, 65, 425, 25);
      lyricsJLable.setBounds (500, 100, 100, 25);
      lyricsJTextField.setBounds (500, 125, 495, 300);
      lyricsScroll.setBounds (500, 125, 495, 300);
      
      //Register a listener for the radio buttons.
      searchPtrnJBtn.addActionListener(this);
      refreshBtn.addActionListener(this);    
      resultSongList.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

		@Override
		public void valueChanged(ListSelectionEvent e) {
			updateCurrentSongView();
//			System.out.println("Selection made" + resultSongList.getSelectedRow());
		}});
  }

  void setControoler(MyController control)
  {
	  this.controller = control;
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
			DefaultTableModel dm = (DefaultTableModel)resultSongList.getModel();
			dm.setRowCount(0);

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
		updateView();
		break;
	}
}

void updateCurrentSongView() 
{
	try 
	{
		int row = resultSongList.getSelectedRow();
		if (row >= 0)
		{
			int position =  (int) resultSongList.getValueAt(row, 3);
			
			if (songList.size() > 0 && row > -1 && position >= 0)
			{
				//Set Song data to gui:
				songTitleJTextField.setText(songList.get(position).getTitle());
				artistJTextField.setText(songList.get(position).getArtist());
				songIDJTextField.setText(((Integer)songList.get(position).getSongID()).toString());
				lyricsJTextField.setText(songList.get(position).getSongLyrics());
			}
		}

	}
	catch(Exception e)
	{
		System.out.println(e);
	}
}

void updateView() 
{
	DefaultTableModel dm = (DefaultTableModel)resultSongList.getModel();
	dm.setRowCount(0);

	if (songList != null)
	{
		Object[][] songSongItems = new Object[songList.size()][4];
		for (int i =0; i<songList.size() ; i++)
		{
			songSongItems[i][0] = songList.get(i).getTitle();
			songSongItems[i][1] = songList.get(i).getArtist();
			songSongItems[i][2] = songList.get(i).getSongID();
			songSongItems[i][3] = i;
			
		}
	    resultSongList.setModel(new DefaultTableModel(songSongItems, resultColumnNames));
	    System.out.println("Trying to update songlist in gui");
	}
}

public static void fromControllerSongList(List<Song> songListFromController)
{
	songList = songListFromController;
}

}