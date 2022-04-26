package com.example.videoplayerfx;

import java.io.File;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.media.MediaView;
import javafx.scene.Group;

import java.io.IOException;
import java.awt.image.*;
import javax.swing.*;
import java.util.ArrayList;

public class HelloApplication extends Application {
    private HBox mediaBar;
    private boolean atEndOfMedia = false;
    private boolean playing = false;
    ArrayList<BufferedImage> frames = new ArrayList<BufferedImage>();
    @Override
    public void start(Stage stage) throws IOException {
        String audioPath = "/Users/lindseymariewingate/Documents/school/576/fp/dataset2/Videos/data_test2.wav";
        //String imagePath = "Users/lindseymariewingate/Documents/school/576/fp/fitbit.png";
        String imagePath = "https://www.google.com/search?q=google&source=lnms&tbm=isch&sa=X&ved=2ahUKEwiYr_abu7D3AhWADkQIHdZHCbYQ_AUoBHoECAIQBg&biw=657&bih=955&dpr=2#imgrc=JT5K7I_TR0CmQM";
        Media media = new Media(new File(audioPath).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        //mediaPlayer.setAutoPlay(true);
        MediaView mediaView = new MediaView(mediaPlayer);

        Button playButton = new Button(">");

        playButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                Status status = mediaPlayer.getStatus();
                if ( status == Status.PAUSED
                        || status == Status.READY
                        || status == Status.STOPPED)
                {
                    // rewind the movie if we're sitting at the end
                    if (atEndOfMedia) {
                        mediaPlayer.seek(mediaPlayer.getStartTime());
                        atEndOfMedia = false;
                    }
                    mediaPlayer.play();
                    playing = true;
                }
            }
        });

        Button pauseButton = new Button("||");

        pauseButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Status status = mediaPlayer.getStatus();
                if (status == Status.PLAYING) {
                    mediaPlayer.pause();
                    playing = false;
                }
            }
        });

        //add media bar to hold play and pause buttons
        mediaBar = new HBox();
        mediaBar.setPadding(new Insets(5, 10, 5, 10));
        BorderPane.setAlignment(mediaBar, Pos.BOTTOM_CENTER);
        mediaBar.getChildren().add(playButton);
        mediaBar.getChildren().add(pauseButton);
        //Group root = new Group(mediaBar);
        //jframe for video
        //JFrame frame = new JFrame();
        //frame.add(new JLabel(new ImageIcon(imgOne)));
        //frame.pack();
        //frame.setVisible(true);

//add jframe to root for display?
        StackPane root = new StackPane();
        Scene scene = new Scene(root, 320, 240);
        Image img = new Image(imagePath);
        BackgroundImage backgroundImage = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        Background background = new Background(backgroundImage);
        root.getChildren().add(mediaBar);
        root.setBackground(background);
        stage.setScene(scene);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}