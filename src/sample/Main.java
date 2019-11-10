package sample;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.MalformedInputException;



import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.*;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;

public class Main extends Application {
    URL link = null;
    URLConnection con = null;
    int i;

    private static String url="http://redirector.googlevideo.com/videoplayback?lmt=1428057161937059&gcr=us&source=youtube&pl=40&itag=18&dur=263.685&nh=IgpwcjA1LmRmdzA2KgkxMjcuMC4wLjE&mime=video%2Fmp4&expire=1450394596&ratebypass=yes&sver=3&initcwndbps=8415000&fexp=9407610%2C9416126%2C9419451%2C9420452%2C9420716%2C9421932%2C9422428%2C9422596%2C9422918%2C9423639%2C9423662%2C9423714%2C9424372%2C9424630%2C9424753&ipbits=0&ms=au&mv=m&mt=1450372942&key=yt6&ip=2a03%3A8180%3A1001%3A16a%3A%3A8ee1&upn=Y8FgiLPsdJM&mm=31&id=o-ANjrHmzin3DUo-_tl8U4tAyBdyaCipSl9gr1ti6iWTg3&mn=sn-q4f7sn7y&sparams=dur%2Cgcr%2Cid%2Cinitcwndbps%2Cip%2Cipbits%2Citag%2Clmt%2Cmime%2Cmm%2Cmn%2Cms%2Cmv%2Cnh%2Cpl%2Cratebypass%2Csource%2Cupn%2Cexpire&signature=B997697D537A5AA2CCD3AFF5D09F5B7CF40CD0B7.CF525B586B94D5440CBBDA00685F0E7F5C5135DA&title=Britney+Spears+-+Perfume";
    private static String httpUrl;
    private File f;
    private Stage stage;
    private Object downloaded;
    FileOutputStream fd;
    private StringBuffer LOCAL_FILE;
    private Object FileUtils;

    @Override
    public void start(Stage primaryStage) throws Exception
    {

        stage=primaryStage;
        //showPlayWindow();
        showDefaultWindow();
    }

    public void showDefaultWindow() throws Exception
    {
        FXMLLoader loader= new FXMLLoader();
        loader.setLocation(getClass().getResource("defaultPage.fxml"));
        Parent root=loader.load();

        defaultControl cnt=loader.getController();
        //System.out.println(this+" "+cnt+" "+loader.getController());
        cnt.setMain(this);

        Scene scene=new Scene(root, 600, 400);

        stage.setTitle("Media Player");
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
    }

    public void FromOnline(String onlineUrl) throws Exception{
        try{
            stage.setTitle("Online Streaming");
            Group root = new Group();
            httpUrl=onlineUrl;
            if(httpUrl.isEmpty())return;
            Media media = new Media(httpUrl);

            MediaPlayer mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setAutoPlay(true);
            Controller cn = new Controller(mediaPlayer);
            cn.getMp().setOnEndOfMedia(new Runnable() {
                @Override
                public void run() {
                    /*downloaded=media;
                    File dwd=new File("downloaded.mp4");
                    try {
                        fd= new FileOutputStream(dwd);
                        ObjectOutputStream oos=new ObjectOutputStream(fd);
                        oos.writeObject(downloaded);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }*/

                    /*try {
                        link = new URL("http://download.oracle.com/otndocs/products/javafx/oow2010-2.flv");
                        con = link.openConnection();
                        File file = new File(link.getFile());
                        BufferedInputStream bis = new BufferedInputStream(con.getInputStream());
                        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file.getName()));
                        while ((i = bis.read()) != -1) {
                            bos.write(i);
                        }
                        bos.flush();
                        bis.close();
                    } catch (MalformedInputException malformedInputException) {
                        malformedInputException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }*/


                }
            });



            Scene scene;
            scene = new Scene(root, 1000, 500);

            cn.getMediaView().setPreserveRatio(true);

            scene.setRoot(cn);

            stage.setScene(scene);

            stage.sizeToScene();
            stage.show();
            final DoubleProperty width = cn.getMediaView().fitWidthProperty();
            final DoubleProperty height = cn.getMediaView().fitHeightProperty();
            System.out.println("yes" + height + "no" + width);

            width.bind(Bindings.selectDouble(cn.getMediaView().sceneProperty(), "width"));
            height.bind(Bindings.selectDouble(cn.getMediaView().sceneProperty(), "height"));
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void showPlayWindow() throws Exception
    {
        try {
            FileChooser fc = new FileChooser();
            fc.setTitle("Choose Media");
            f = fc.showOpenDialog(stage);
            if(f==null) return;
            url = f.toURI().toASCIIString();

            stage.setTitle(f.getName());
            Group root = new Group();


            System.out.println(url);

            Media media = new Media(url);
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setAutoPlay(true);



            Controller cn = new Controller(mediaPlayer);
            /*int w= (int) cn.getMediaView().getFitWidth();
            int h= (int) cn.getMediaView().getFitHeight();
            final DoubleProperty width = cn.getMediaView().fitWidthProperty();
            final DoubleProperty height = cn.getMediaView().fitHeightProperty();
            System.out.println("width and height"+width.intValue()+" "+height.intValue());*/
            Scene scene;
            scene = new Scene(root, 1000, 500);

            cn.getMediaView().setPreserveRatio(true);

            scene.setRoot(cn);

            stage.setScene(scene);

            stage.sizeToScene();
            stage.show();
            final DoubleProperty width = cn.getMediaView().fitWidthProperty();
            final DoubleProperty height = cn.getMediaView().fitHeightProperty();
            System.out.println("yes" + height + "no" + width);

            width.bind(Bindings.selectDouble(cn.getMediaView().sceneProperty(), "width"));
            height.bind(Bindings.selectDouble(cn.getMediaView().sceneProperty(), "height"));

            /*scene.widthProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
                    System.out.println("Width: " + newSceneWidth);
                    cn.getMediaView().setFitWidth((Double) newSceneWidth);
                }
            });
            scene.heightProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneHeight, Number newSceneHeight) {
                    System.out.println("Height: " + newSceneHeight);
                    cn.getMediaView().setFitWidth((Double) newSceneHeight);
                }
            });*/
        }
        catch (Exception e){e.printStackTrace();}
    }


    public static void main(String[] args) {
        launch(args);
    }
}
