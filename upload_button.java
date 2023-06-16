import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class upload_button extends JFrame {

    // all the labels
    JLabel title = new JLabel("Title");
    JLabel artist = new JLabel("Artist");
    JLabel album = new JLabel("Album");
    JLabel genre = new JLabel("Genre");
    JLabel year = new JLabel("Year");
    JLabel file_name_label = new JLabel("");

    // all the button
    JButton upload = new JButton("upload");
    JButton choose = new JButton("Choose");

    // Textfields
    JTextField title_field = new JTextField("");
    JTextField artist_field = new JTextField("");
    JTextField album_field = new JTextField("");
    JTextField genre_field = new JTextField("");
    JTextField year_field = new JTextField("");

    // Color
    Color forPanel = new Color(102, 102, 102);
    Color forButton = new Color(255, 230, 230);

    upload_button() {
        Font font = new Font("Bradley Hand", Font.BOLD, 20);
        upload.setBackground(forButton);
        upload.setForeground(Color.BLACK);
        upload.setOpaque(true);
        upload.setHorizontalAlignment(SwingConstants.CENTER);
        upload.setFont(font);

        title.setBackground(forButton);
        title.setForeground(Color.BLACK);
        title.setOpaque(true);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(font);

        artist.setBackground(forButton);
        artist.setForeground(Color.BLACK);
        artist.setOpaque(true);
        artist.setHorizontalAlignment(SwingConstants.CENTER);
        artist.setFont(font);

        album.setBackground(forButton);
        album.setForeground(Color.BLACK);
        album.setOpaque(true);
        album.setHorizontalAlignment(SwingConstants.CENTER);
        album.setFont(font);

        genre.setBackground(forButton);
        genre.setForeground(Color.BLACK);
        genre.setOpaque(true);
        genre.setHorizontalAlignment(SwingConstants.CENTER);
        genre.setFont(font);

        year.setBackground(forButton);
        year.setForeground(Color.BLACK);
        year.setOpaque(true);
        year.setHorizontalAlignment(SwingConstants.CENTER);
        year.setFont(font);

        choose.setBackground(forButton);
        upload.setForeground(Color.BLACK);
        upload.setFont(font);
        choose.setOpaque(true);
        choose.setFont(font);

        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));
        panel.setBackground(forPanel);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(title);
        panel.add(title_field);
        panel.add(artist);
        panel.add(artist_field);
        panel.add(album);
        panel.add(album_field);
        panel.add(genre);
        panel.add(genre_field);
        panel.add(year);
        panel.add(year_field);

        panel.add(choose);
        panel.add(file_name_label);

        add(panel, BorderLayout.CENTER);
        add(upload, BorderLayout.SOUTH);

    }

    public void main(String[] args) {

        upload_button obj = new upload_button();
        obj.setSize(800, 600);
        obj.setVisible(true);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
