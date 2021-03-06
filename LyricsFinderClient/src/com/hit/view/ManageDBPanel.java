package com.hit.view;
import com.hit.controller.MyController;
import com.hit.model.Song;
import java.util.List;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class ManageDBPanel extends JFrame implements ActionListener {
	  private JScrollPane songListInDBScroll;
	  private JTable songListInDBList;
	  private JLabel allSongListLabel;
	  private JLabel addNewSongJLabel;
	  private JLabel titleJLabel;
	  private JLabel artistJLabel;
	  private JLabel lyricsJLabel;
	  private JScrollPane lyricsScroll;
	  private JTextArea lyricsJTextArea;
	  private JTextField titleJTextField;
	  private JTextField artistJTextField;
	  private JButton addDBJButton;
	  private JLabel removeSongsJLabel;
	  private JLabel enterSongIDJlabel;
	  private JTextField enterSongIdJTextField;
	  private JButton removeSongJButton;
	  private JButton updateDBfromServer;
	  private JButton refreshSongListJButton;
	  static List<Song> songList;
	  String[] columnNames = {"Song Name", "Artist", "Song ID", "#"};
	  Object[][] songListInDBListItems = {{"No Song In List","No Song In List" , 0, 0}};
	  public static MyController controller;

	  
	  // button codes
	  static String addBtn = "addBtn";
	  static String removeBtn = "removeBtn";
	  static String updateBtn = "updateBtn";
	  static String refreshBtn = "refreshBtn";
	  
	  public ManageDBPanel() {
	      //construct components
	      songListInDBList = new JTable (new DefaultTableModel(songListInDBListItems, columnNames));
	      songListInDBList.setDefaultEditor(Object.class, null); //JTable set not editable
	      songListInDBScroll = new JScrollPane(songListInDBList);

	      allSongListLabel = new JLabel ("All Songs in DB:");
	      addNewSongJLabel = new JLabel ("Add New Song:");
	      titleJLabel = new JLabel ("Title:");
	      artistJLabel = new JLabel ("Artist:");
	      lyricsJLabel = new JLabel ("Lyrics:");
	      lyricsJTextArea = new JTextArea (5, 5);
	      lyricsScroll = new JScrollPane(lyricsJTextArea);
	      titleJTextField = new JTextField (5);
	      artistJTextField = new JTextField (5);
	      addDBJButton = new JButton ("Add To DB");
	      addDBJButton.setActionCommand(this.addBtn);
	      removeSongsJLabel = new JLabel ("Remove Song From DB:");
	      enterSongIDJlabel = new JLabel ("Enter Song ID:");
	      enterSongIdJTextField = new JTextField (5);
	      updateDBfromServer = new JButton ("1. Update DB From Server");
	      updateDBfromServer.setActionCommand(this.updateBtn);
	      removeSongJButton = new JButton ("Remove Song");
	      removeSongJButton.setActionCommand(this.removeBtn);
	      refreshSongListJButton = new JButton ("2. Refresh Song List");
	      refreshSongListJButton.setActionCommand(this.refreshBtn);

	      //adjust size and set layout
	      this.setBounds(200, 200, 900, 500);
	      this.setResizable(false);
//	      setPreferredSize (new Dimension (767, 466));
	      setLayout (null);

	      //add components
	      add (songListInDBScroll);
	      add (allSongListLabel);
	      add (addNewSongJLabel);
	      add (titleJLabel);
	      add (artistJLabel);
	      add (lyricsJLabel);
	      add (lyricsScroll);
	      add (titleJTextField);
	      add (artistJTextField);
	      add (addDBJButton);
	      add (removeSongsJLabel);
	      add (enterSongIDJlabel);
	      add (enterSongIdJTextField);
	      add (updateDBfromServer);
	      add (removeSongJButton);
	      add (refreshSongListJButton);

	      //set component bounds (only needed by Absolute Positioning)
	      songListInDBScroll.setBounds (15, 35, 365, 415);
	      songListInDBList.setBounds (15, 35, 365, 415);
	      allSongListLabel.setBounds (15, 5, 130, 30);
	      addNewSongJLabel.setBounds (395, 10, 100, 25);
	      titleJLabel.setBounds (395, 30, 100, 25);
	      artistJLabel.setBounds (395, 50, 100, 25);
	      lyricsJLabel.setBounds (395, 70, 100, 25);
	      lyricsJTextArea.setBounds (395, 90, 475, 175);
	      lyricsScroll.setBounds (395, 90, 475, 175);
	      titleJTextField.setBounds (485, 30, 385, 20);
	      artistJTextField.setBounds (485, 50, 385, 20);
	      addDBJButton.setBounds (395, 270, 100, 25);
	      removeSongsJLabel.setBounds (395, 310, 165, 25);
	      enterSongIDJlabel.setBounds (395, 335, 100, 25);
	      enterSongIdJTextField.setBounds (480, 335, 100, 25);
	      removeSongJButton.setBounds (605, 335, 125, 25);
	      updateDBfromServer.setBounds (395, 390, 185, 25);
	      refreshSongListJButton.setBounds (395, 424, 185, 25);
	      
	      //Register a listener for the radio buttons.
	      addDBJButton.addActionListener(this);
	      removeSongJButton.addActionListener(this);    
	      updateDBfromServer.addActionListener(this);    
	      refreshSongListJButton.addActionListener(this);    
	      songListInDBList.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				updateCurrentSongView();
			}});
	  }
	  
  void setControoler(MyController control)
  {
	  this.controller = control;
  }
  
@Override
public void actionPerformed(ActionEvent e) 
{
	String action = e.getActionCommand();
	
//	  static String addBtn = "addBtn";
//	  static String removeBtn = "removeBtn";
//	  static String updateBtn = "updateBtn";
//	  static String refreshBtn = "refreshBtn";
	
	switch(action)
	{
	case "addBtn":
		this.addToDB();
		break;
	case "removeBtn":
		this.removeFromDB();
		break;
	case "updateBtn":
		this.controller.getDB();
		break;
	case "refreshBtn":
		this.updateView();
		break;
	default:
		System.out.println("[Manage DB Panel] Unrecognized action");
		break;
	}
}

void updateCurrentSongView() 
{

}

void updateView() 
{
	DefaultTableModel dm = (DefaultTableModel)songListInDBList.getModel();
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
		songListInDBList.setModel(new DefaultTableModel(songSongItems, columnNames));
	}
}

void addToDB()
{
	String title = this.titleJTextField.getText();
	String artist = this.artistJTextField.getText();
	String lyrics = this.lyricsJTextArea.getText();
	this.controller.addSong(title, artist, lyrics);
}

void removeFromDB()
{
	int songID = Integer.parseInt(enterSongIdJTextField.getText());
	this.controller.removeSong(songID);
}

public static void fromControllerSongList(List<Song> songListFromController)
{
	songList = songListFromController;
}

public static void addRemoveStatus(String action, boolean status) {
	System.out.println("[Manage DB Panel] "+ action + " status: " + status);
}

}
