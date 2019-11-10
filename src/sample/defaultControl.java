package sample;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.awt.*;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class defaultControl implements Initializable{

    private Main main;

    private String url="F:/BUET/PROJECTS/2-1 on JAVA/making mediaPlayer/pkgMediaPlayer/src/sample/w.jpg";
    private String onlineUrl;

    public String getOnlineUrl() {
        return onlineUrl;

    }


    private File f=new File(url);

    Image img=new Image(f.toURI().toASCIIString());

    @FXML
    private Pane pane;

    @FXML
    private TabPane tabpane;

    @FXML
    private Tab homeTab;

    @FXML
    private ImageView imageSector;

    @FXML
    private Tab playlistTab;

    @FXML
    private ListView<?> llistViewMedia;

    @FXML
    private Tab historyTab;

    @FXML
    private TableView<?> tableHistory;

    @FXML
    private TableColumn<?, ?> historyTableCol;

    @FXML
    private Tab aboutTab;

    @FXML
    private Button play;

    @FXML
    private Button updatePlaylist;

    @FXML
    private Button clearHistory;

    @FXML
    private Button LiveStreaming;

    @FXML
    private Button StreamStart;

    @FXML
    private TextField UrlBox;

    @FXML
    private ProgressBar progress;

    public void setMain(Main m)
    {
        main=m;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pane.setStyle("-fx-background-color: black;");
        play.setStyle("-fx-background-color: orange;");
        updatePlaylist.setStyle("-fx-background-color: orange;");
        clearHistory.setStyle("-fx-background-color: orange;");
        imageSector.setImage(img);
    }

    @FXML
    public void playButtonAction(ActionEvent event)
    {
        try
        {
            double i=0;
            main.showPlayWindow();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @FXML
    public void OpenUrlBox(ActionEvent event){
        try{
            UrlBox.setVisible(true);
            StreamStart.setVisible(true);
            Thread prog=new Thread() {
                public void run() {
                    double i = 0;
                    while (progress.getProgress() < 1.0)

                    {
                        i += 0.001;
                        System.out.println("this "+i);
                        progress.setProgress(i);

                    }
                }
            };
            prog.start();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    public void Stream(ActionEvent event){
        try{
            if(!(UrlBox.getText().isEmpty())){
                onlineUrl=UrlBox.getText();
                UrlBox.setVisible(false);
                StreamStart.setVisible(false);
                main.FromOnline(getOnlineUrl());
            }

            //UrlBox.setText(null);
            else return;
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

}

