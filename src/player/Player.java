package player;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Player extends JFrame
{
	public static final int WIDTH = 350, HEIGHT = 150;
	
	private static final long serialVersionUID = 1L;
	private JPanel browsePanel, namePanel, buttonPanel;
	private JLabel songName;
	private JButton btnBrowse, btnPlay, btnPause, btnStop;
	
	public Player()
	{
		setTitle("Music Player");
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(true);
		setLocationRelativeTo(null);
		setMinimumSize(new Dimension(WIDTH, HEIGHT));
		
		initWindow();
		
		setVisible(true);
	}
	
	private void initWindow()
	{
		browsePanel = new JPanel();
		add(browsePanel, BorderLayout.NORTH);
		btnBrowse = new JButton("Browse");
		browsePanel.add(btnBrowse);
		
		namePanel = new JPanel();
		add(namePanel, BorderLayout.CENTER);
		songName = new JLabel("Song name");
		namePanel.add(songName);
		
		buttonPanel = new JPanel();
		add(buttonPanel, BorderLayout.SOUTH);
		btnPlay = new JButton(new ImageIcon("res/play.png"));
		btnPause = new JButton(new ImageIcon("res/pause.png"));
		btnStop = new JButton(new ImageIcon("res/stop.png"));
		buttonPanel.add(btnPlay);
		buttonPanel.add(btnPause);
		buttonPanel.add(btnStop);
	}
}
