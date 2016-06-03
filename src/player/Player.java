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
	private JButton btnBrowse, btnPlay, btnPause, btnStop, volUp, volDown;
	private String fullSongPath;
	private Audio song;
	private boolean isFirstSong = true;
	private int framePos;
	private float volume;
	private Thread isRunningThread;
	
	public Player()
	{
		setTitle("Music Player");
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(true);
		setLocationRelativeTo(null);
		setMinimumSize(new Dimension(WIDTH, HEIGHT));
		
		initWindow();
		volume = -20.0f;
		
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
					
					if(!isFirstSong)
					{
						song.close();
						playSong(fullSongPath, volume, 0);
					}
					else
					{
						isFirstSong = false;
						playSong(fullSongPath, volume, 0);
					}
					
					System.out.println(path);
					System.out.println(name);
					System.out.println(fullSongPath);
					
					songName.setText(name);
					
					btnPause.setEnabled(true);
					btnStop.setEnabled(true);
					volUp.setEnabled(true);
					volDown.setEnabled(true);
					
					isRunningThread = new Thread("Is song running")
					{
						@Override
						public void run()
						{
							if(!song.getIsRunning())
							{
								songName.setText("Song name");
								volUp.setEnabled(false);
								volDown.setEnabled(false);
								btnPlay.setEnabled(false);
								btnPause.setEnabled(false);
								btnStop.setEnabled(false);
							}
						}
					};
					
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
		volUp = new JButton(new ImageIcon("res/volUp.png"));
		volDown = new JButton(new ImageIcon("res/volDown.png"));
		
		btnPlay.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				song.playAfterPause(framePos);
				btnPlay.setEnabled(false);
				btnPause.setEnabled(true);
			}
		});
		
		btnPause.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				framePos = song.getFramePosition();
				song.stop();
				btnPlay.setEnabled(true);
				btnPause.setEnabled(false);
			}
		});
		
		btnStop.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				song.close();
				songName.setText("Song name");
				btnPause.setEnabled(false);
				btnStop.setEnabled(false);
				volUp.setEnabled(false);
				volDown.setEnabled(false);
			}
		});
		
		volUp.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				volume += 1.5f;
				song.changeVolume(volume);
			}
		});
		
		volDown.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				volume -= 1.5f;
				song.changeVolume(volume);
			}
		});

		volUp.setEnabled(false);
		volDown.setEnabled(false);
		btnPlay.setEnabled(false);
		btnPause.setEnabled(false);
		btnStop.setEnabled(false);
		buttonPanel.add(volUp);
		buttonPanel.add(btnPlay);
		buttonPanel.add(btnPause);
		buttonPanel.add(btnStop);
		buttonPanel.add(volDown);
	}
	
	private void playSong(String path, float volume, int framePos)
	{
		song = new Audio(path);
		song.play(volume, framePos);
	}
}
