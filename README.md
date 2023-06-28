# Music App

This is a basic music app developed using Java.

## Libraries Used

- Mysql connector
- JLayer

## Features

The Music App provides the following features:

- Play music from a database
- Upload music to the database
- Pause and resume songs from the paused position
- Play the next song in the list
- Play the previous song in the list
- Create a favorite song list
- Add songs to the favorite list
- Play songs from the favorite list

## How to Use

1. Make sure you have the required libraries (Mysql connector and JLayer) added to your project.
2. Set up a MySQL database and configure the connection details in the application.
3. Compile and run the Java code to launch the Music App.
4. Use the app to play, upload, pause, resume, and navigate through songs.

## Example Code

```java
// Connect to the MySQL database and uploading the song to the database
 try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                System.out.println("Driver loaded");
                Connection con = DriverManager.getConnection(url, user, password);
                System.out.println("Connected");

                String query = String.format(
                        "INSERT INTO music_table (title, artist, album, genre, year, file) VALUES ('%s', '%s', '%s', '%s', %d, '%s')",
                        title_field.getText(), artist_field.getText(), album_field.getText(), genre_field.getText(),
                        Integer.parseInt(year_field.getText()), selectedFile.getAbsolutePath());

                Statement st = con.createStatement();
                int rowsAffected = st.executeUpdate(query);
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "File uploaded Successfully");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to upload the file");
                }

                st.close();
                con.close();
                System.out.println("Connection closed successfully");

            } catch (Exception ex) {
                System.out.println(ex);
            }



// Play a song
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
```

## Contributing

Contributions to the Music App are welcome! If you find any bugs or want to add new features, please submit an issue or a pull request.
