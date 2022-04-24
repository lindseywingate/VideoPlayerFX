package com.example.videoplayerfx;

import java.io.File;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.media.MediaView;
import javafx.scene.Group;

import java.io.IOException;

public class HelloApplication extends Application {
    private HBox mediaBar;
    private boolean atEndOfMedia = false;

    @Override
    public void start(Stage stage) throws IOException {
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        String path = "/Users/lindseymariewingate/Documents/school/576/fp/dataset2/Videos/data_test2.wav";
        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        //mediaPlayer.setAutoPlay(true);
        MediaView mediaView = new MediaView(mediaPlayer);

        //create play button
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
                }
            }
        });

        //add media bar to hold play and pause buttons
        mediaBar = new HBox();
        mediaBar.setAlignment(Pos.BOTTOM_CENTER);
        mediaBar.setPadding(new Insets(5, 10, 5, 10));
        BorderPane.setAlignment(mediaBar, Pos.BOTTOM_CENTER);
        mediaBar.getChildren().add(playButton);
        mediaBar.getChildren().add(pauseButton);
        Group root = new Group(mediaBar);

        root.getChildren().add(mediaView);
        Scene scene = new Scene(root, 320, 240);
        stage.setScene(scene);
        stage.show();


    }

    public static void main(String[] args) {
        launch();
    }
}