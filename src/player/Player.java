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
	private JLabel name;
	private JButton btnBrowse, btnPlay, btnPause, btnStop;
	private String songPath, songName;
	
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
		btnBrowse.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				JFileChooser chooser = new JFileChooser();
				int rVal = chooser.showOpenDialog(Player.this);

				if(rVal == JFileChooser.APPROVE_OPTION)
				{
					songPath = chooser.getCurrentDirectory().toString();
					songName = chooser.getSelectedFile().getName();
				}
				System.out.println(songPath);
				System.out.println(songName);
			}
			
		});
		browsePanel.add(btnBrowse);
		
		namePanel = new JPanel();
		add(namePanel, BorderLayout.CENTER);
		name = new JLabel("Song name");
		namePanel.add(name);
		
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
