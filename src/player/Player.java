package player;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
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
	private String fullSongPath;
	private Audio song;
	
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
		btnBrowse = new JButton("Browse");
		add(browsePanel, BorderLayout.NORTH);	
		btnBrowse.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				JFileChooser chooser = new JFileChooser();
				int rVal = chooser.showOpenDialog(Player.this);

				if(rVal == JFileChooser.APPROVE_OPTION)
				{
					String path = chooser.getCurrentDirectory().toString();
					String name = chooser.getSelectedFile().getName();
					fullSongPath = path + "\\" + name;
					
					song = new Audio(fullSongPath);
					song.play(-15.0f);
					
					System.out.println(path);
					System.out.println(name);
					System.out.println(fullSongPath);
					
					songName.setText(name);
					
					btnPause.setEnabled(true);
					btnStop.setEnabled(true);
				}			
			}		
		});
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
		
		btnStop.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				song.close();
				btnPause.setEnabled(false);
				btnStop.setEnabled(false);
			}
		});
		
		btnPlay.setEnabled(false);
		btnPause.setEnabled(false);
		btnStop.setEnabled(false);
		buttonPanel.add(btnPlay);
		buttonPanel.add(btnPause);
		buttonPanel.add(btnStop);
	}
}
