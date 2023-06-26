package music_app;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javazoom.jl.player.advanced.AdvancedPlayer;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;

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
    JLabel artistname = new JLabel();
    JLabel songdisplay = new JLabel();
    JLabel albumname = new JLabel();

    // all the button for the GUI
    JButton playButton = new JButton("Play");
    JButton pauseButton = new JButton("Pause");
    JButton resumeButton = new JButton("Resume");
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

    // information for database
    private String url = "jdbc:mysql://localhost:3306/music";
    private String user = "root";
    private String password = "sarthak@1226";

    private AdvancedPlayer player;
    private String selectedsong;
    private String artist;
    private String album;
    private String genre;
    private int year;
    private String audioPath;

    // for pausing and playing of the song
    private boolean paused = false;
    private long pausedPos = 0;
    private long totalLength = 0;
    private FileInputStream fis;
    private BufferedInputStream bis;
    private long newpostion;

    GUI() {

        Font font = new Font("Bradley Hand", Font.BOLD, 20);

        playButton.setForeground(Color.BLACK);
        playButton.setFont(font);
        playButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                playButton.setForeground(Color.green);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                playButton.setForeground(Color.BLACK);
            }
        });

        resumeButton.setBackground(forButton);
        resumeButton.setForeground(forButton);
        resumeButton.setFont(font);
        resumeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                resumeButton.setForeground(Color.green);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                resumeButton.setForeground(Color.BLACK);
            }
        });

        prevButton.setBackground(forButton);
        prevButton.setForeground(forButton);
        prevButton.setFont(font);
        prevButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                prevButton.setForeground(Color.green);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                prevButton.setForeground(Color.BLACK);
            }
        });

        nextButton.setBackground(forButton);
        nextButton.setForeground(forButton);
        nextButton.setFont(font);
        nextButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                nextButton.setForeground(Color.green);
            }

            @Override
            public void mouseExited(MouseEvent e) {

                nextButton.setForeground(Color.BLACK);
            }
        });

        pauseButton.setBackground(forButton);
        pauseButton.setForeground(forButton);
        pauseButton.setFont(font);
        pauseButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                pauseButton.setForeground(Color.green);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                pauseButton.setForeground(Color.BLACK);
            }
        });

        titleLabel.setForeground(forButton);
        titleLabel.setFont(font);

        artistLabel.setForeground(forButton);
        artistLabel.setFont(font);

        timeLabel.setForeground(forButton);
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
        c.gridx = 1;
        c.gridy = 4;
        c.gridwidth = 1;
        playerPanel.add(timeLabel, c);

        artistname.setPreferredSize(new Dimension(200, 30));
        c.gridx = 3;
        c.gridy = 1;
        c.gridwidth = 1;
        playerPanel.add(artistname, c);

        songdisplay.setPreferredSize(new Dimension(200, 30));
        c.gridx = 3;
        c.gridy = 0;
        c.gridwidth = 1;
        playerPanel.add(songdisplay, c);

        albumname.setPreferredSize(new Dimension(200, 30));
        c.gridx = 3;
        c.gridy = 2;
        c.gridwidth = 1;
        playerPanel.add(albumname, c);

        playButton.setPreferredSize(new Dimension(100, 30));

        c.gridx = 4;
        c.gridy = 0;
        c.gridwidth = 1;
        playerPanel.add(playButton, c);

        pauseButton.setPreferredSize(new Dimension(100, 30));
        c.gridx = 4;
        c.gridy = 1;
        c.gridwidth = 1;
        playerPanel.add(pauseButton, c);

        resumeButton.setPreferredSize(new Dimension(100, 30));
        c.gridx = 5;
        c.gridy = 1;
        c.gridwidth = 1;
        playerPanel.add(resumeButton, c);
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

        // Adding song from the database to the GUI
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver Loaded");
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("Connected");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM music_table");

            while (rs.next()) {
                String songname = rs.getString("title");
                listmodel.addElement(songname);
            }

            rs.close();
            st.close();
            con.close();
        } catch (Exception ex) {
            System.out.println("Error while extracting song from the database: " + ex.getMessage());
        }

        playlistLabel.setFont(new Font("Bradley Hand", Font.BOLD, 18));
        playlistPanel.add(playlistLabel, BorderLayout.NORTH);

        song_name.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // block of code for extracting the song title that was clicked on the Jlist -->
        song_name.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    selectedsong = song_name.getSelectedValue();
                    System.out.println(selectedsong);
                    extracting_details(selectedsong);
                }
            }
        });

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
        resumeButton.addActionListener(this);
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

    public void extracting_details(String selectedsong) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver Loaded");
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("Connected");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM music_table WHERE title = '" + selectedsong + "'");

            if (rs.next()) {
                artist = rs.getString("artist");
                album = rs.getString("album");
                genre = rs.getString("genre");
                year = rs.getInt("year");
                audioPath = rs.getString("file");
                artistname.setText(artist);
                albumname.setText(album);
                songdisplay.setText(selectedsong);
            }

            rs.close();
            st.close();
            con.close();
        } catch (Exception ex) {
            System.out.println("Error while extracting details from the database: " + ex.getMessage());
        }
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == upload) {
            upload_button u = new upload_button();
            u.setVisible(true);
            u.setSize(300, 400);
        }
        if (ae.getSource() == playButton) {
            System.out.println("Playbutton working properly");
            extracting_details(selectedsong);
            System.out.println("audioPath: " + audioPath);
            play(audioPath);
        }
        if (ae.getSource() == nextButton) {
            System.out.println("The button is working properly");
        }
        if (ae.getSource() == pauseButton) {
            paused = true;
            pause();
        }
        if (ae.getSource() == resumeButton) {
            resume();
        }
    }

    public void play(String audioPath) {
        try {
            stop();
            fis = new FileInputStream(audioPath);
            bis = new BufferedInputStream(fis);
            player = new AdvancedPlayer(bis);
            SwingWorker<Void, Void> playerThread = new SwingWorker<Void, Void>() {
                @Override
                protected Void doInBackground() throws Exception {

                    player.play();
                    return null;
                }
            };
            playerThread.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void stop() {
        if (player != null) {
            player.close();
            player = null;
        }
        if (fis != null) {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (bis != null) {
            try {
                bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void pause() {
        if (player != null) {
            try {
                pausedPos = fis.available();
                player.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void resume() {
        try {
            fis = new FileInputStream(audioPath);
            bis = new BufferedInputStream(fis);
            sizemethod(); // Calculate the total length of the audio file
            System.out.println("total " + totalLength);
            System.out.println("pausedPos " + pausedPos);
            newpostion = totalLength - pausedPos - 20000;
            fis.skip(newpostion);
            System.out.println("Temp " + newpostion);
            player = new AdvancedPlayer(bis);

            SwingWorker<Void, Void> playerThread = new SwingWorker<Void, Void>() {
                @Override
                protected Void doInBackground() throws Exception {
                    player.play();
                    return null;
                }
            };
            playerThread.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sizemethod() {
        try {
            File audioFile = new File(audioPath);
            totalLength = audioFile.length();
        } catch (Exception ex) {
            ex.printStackTrace();
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
