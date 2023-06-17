package music_app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class GUI extends JFrame implements ActionListener {
    // all the panels for GUI
    JPanel mainPanel = new JPanel(new BorderLayout());
    JPanel playerPanel = new JPanel(new GridBagLayout());
    JPanel playlistPanel = new JPanel();
    JPanel controlPanel = new JPanel(new BorderLayout());

    // labels
    JLabel titleLabel = new JLabel("Title");
    JLabel artistLabel = new JLabel("Artist");
    JLabel albumLabel = new JLabel("Album");
    JLabel timeLabel = new JLabel("0:0");

    // Textfield for search box
    JTextField searchField = new JTextField();

    // all the button for the GUI
    JButton playButton = new JButton("Play");
    JButton pauseButton = new JButton("Pause");
    JButton stopButton = new JButton("Stop");
    JButton prevButton = new JButton("Previous");
    JButton nextButton = new JButton("Next");
    JButton upload = new JButton("Upload");
    JButton addSongButton = new JButton("Add Song");

    // color for panel and buttons
    Color forPanel = new Color(140, 140, 140);
    Color forButton = new Color(32, 32, 96);

    DefaultListModel<String> listmodel = new DefaultListModel<>();
    JList<String> song_name = new JList<>(listmodel);

    JList<String> user_fav_song = new JList<>(listmodel);
    JCheckBox repeatCheckbox;

    GUI() {

        Font font = new Font("Bradley Hand", Font.BOLD, 20);

        playButton.setBackground(forButton);
        playButton.setOpaque(true);
        playButton.setForeground(forButton);
        playButton.setFont(font);

        stopButton.setBackground(forButton);
        stopButton.setOpaque(true);
        stopButton.setForeground(forButton);
        stopButton.setFont(font);

        prevButton.setBackground(forButton);
        prevButton.setOpaque(true);
        prevButton.setForeground(forButton);
        prevButton.setFont(font);

        nextButton.setBackground(forButton);
        nextButton.setOpaque(true);
        nextButton.setForeground(forButton);
        nextButton.setFont(font);

        pauseButton.setBackground(forButton);
        pauseButton.setOpaque(true);
        pauseButton.setForeground(forButton);
        pauseButton.setFont(font);

        titleLabel.setForeground(forButton);
        titleLabel.setOpaque(true);
        titleLabel.setFont(font);
        artistLabel.setForeground(forButton);
        artistLabel.setOpaque(true);
        artistLabel.setFont(font);
        albumLabel.setForeground(forButton);
        albumLabel.setOpaque(true);
        albumLabel.setFont(font);
        timeLabel.setForeground(forButton);
        timeLabel.setOpaque(true);
        timeLabel.setFont(font);

        addSongButton.setFont(font);
        addSongButton.setForeground(forButton);
        addSongButton.setOpaque(true);

        add(mainPanel);
        playerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(5, 5, 5, 5);

        titleLabel.setFont(new Font("Bradley Hand", Font.BOLD, 24));
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        playerPanel.add(titleLabel, c);

        // artistLabel.setForeground(Color.GRAY);
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 2;
        playerPanel.add(artistLabel, c);

        // albumLabel.setForeground(Color.GRAY);
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 2;
        playerPanel.add(albumLabel, c);

        // timeLabel.setForeground(Color.GRAY);
        timeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        c.gridx = 2;
        c.gridy = 2;
        c.gridwidth = 1;
        playerPanel.add(timeLabel, c);

        searchField.setPreferredSize(new Dimension(200, 30));
        c.gridx = 3;
        c.gridy = 0;
        c.gridwidth = 1;
        playerPanel.add(searchField, c);

        playButton.setPreferredSize(new Dimension(100, 30));

        c.gridx = 3;
        c.gridy = 1;
        c.gridwidth = 1;
        playerPanel.add(playButton, c);

        pauseButton.setPreferredSize(new Dimension(100, 30));
        c.gridx = 4;
        c.gridy = 1;
        c.gridwidth = 1;
        playerPanel.add(pauseButton, c);

        stopButton.setPreferredSize(new Dimension(100, 30));
        c.gridx = 5;
        c.gridy = 1;
        c.gridwidth = 1;
        playerPanel.add(stopButton, c);
        addSongButton.setPreferredSize(new Dimension(100, 30));
        c.gridx = 6;
        c.gridy = 1;
        c.gridwidth = 1;
        playerPanel.add(addSongButton, c);

        prevButton.setPreferredSize(new Dimension(100, 30));
        c.gridx = 4;
        c.gridy = 2;
        c.gridwidth = 1;
        playerPanel.add(prevButton, c);

        nextButton.setPreferredSize(new Dimension(100, 30));
        c.gridx = 5;
        c.gridy = 2;
        c.gridwidth = 1;
        playerPanel.add(nextButton, c);

        mainPanel.add(playerPanel, BorderLayout.NORTH);

        playlistPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));

        JLabel playlistLabel = new JLabel("Playlist");

        playlistLabel.setFont(new Font("Bradley Hand", Font.BOLD, 18));
        playlistPanel.add(playlistLabel, BorderLayout.NORTH);

        song_name.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane playlistScrollPane = new JScrollPane(song_name);
        playlistScrollPane.setPreferredSize(new Dimension(200, 200));
        playlistPanel.add(playlistScrollPane, BorderLayout.CENTER);

        user_fav_song.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane userplaylistscroll = new JScrollPane(user_fav_song);
        userplaylistscroll.setPreferredSize(new Dimension(200, 200));
        playlistPanel.add(userplaylistscroll, BorderLayout.CENTER);

        mainPanel.add(playlistPanel, BorderLayout.WEST);

        controlPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 20));

        repeatCheckbox = new JCheckBox("Repeat");
        controlPanel.add(repeatCheckbox, BorderLayout.WEST);
        controlPanel.add(upload, BorderLayout.EAST);

        mainPanel.add(controlPanel, BorderLayout.SOUTH);

        upload.addActionListener(this);
        playButton.addActionListener(this);
        stopButton.addActionListener(this);
        nextButton.addActionListener(this);
        prevButton.addActionListener(this);
        pauseButton.addActionListener(this);
        addSongButton.addActionListener(this);
        repeatCheckbox.addActionListener(this);

        mainPanel.setBackground(forPanel);
        playerPanel.setBackground(forPanel);
        controlPanel.setBackground(forPanel);
        playlistPanel.setBackground(forPanel);

    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == upload) {
            upload_button u = new upload_button();
            u.setVisible(true);
            u.setSize(300, 400);
        }
    }

    public static void main(String[] args) {
        GUI q = new GUI();
        q.setTitle("TUN_IN");
        q.setSize(800, 600);
        q.setVisible(true);
        q.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}