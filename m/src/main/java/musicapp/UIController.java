package musicapp;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class UIController implements Initializable {

    @FXML
    private ImageView img1;
    @FXML
    private ImageView img2;
    @FXML
    private ImageView img3;
    @FXML
    private ImageView img4;
    @FXML
    private ImageView img5;
    @FXML
    private ImageView img6;
    @FXML
    private ImageView img7;
    @FXML
    private ImageView img8;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void excitedFromSidebarButton(MouseEvent event) {
        HBox hbox = (HBox)((Node)event.getSource());
        Image image01_onexcited = new Image("/javafxmusicplayer/images/icons8_playlist_30px_2.png");
        Image image02_onexcited = new Image("/javafxmusicplayer/images/icons8_person_male_24px_1.png");
        Image image03_onexcited = new Image("/javafxmusicplayer/images/icons8_albums_24px_3.png");
        Image image04_onexcited = new Image("/javafxmusicplayer/images/icons8_music_26px_1.png");
        Image image05_onexcited = new Image("/javafxmusicplayer/images/icons8_play_button_circled_30px_2.png");
        Image image06_onexcited = new Image("/javafxmusicplayer/images/icons8_radio_waves_30px_2.png");
        Image image07_onexcited = new Image("/javafxmusicplayer/images/icons8_hearts_24px_1.png");
        Image image08_onexcited = new Image("/javafxmusicplayer/images/icons8_browser_window_24px_4.png");
                
        switch(hbox.getId()){
            case "PLAYLIST":
                img1.setImage(image01_onexcited);
                break;
            case "ALBUM":
                img2.setImage(image02_onexcited);
                break;
            case "ARTIST":
                img3.setImage(image03_onexcited);
                break;
            case "SONGS":
                img4.setImage(image04_onexcited);
                break;
            case "STORE":
                img5.setImage(image05_onexcited);
                break;
            case "RADIO":
                img6.setImage(image06_onexcited);
                break;
            case "FORYOU":
                img7.setImage(image07_onexcited);
                break;
            case "BROWSER":
                img8.setImage(image08_onexcited);
                break;
                
        }
    }

    @FXML
    private void enterOnSidebarButton(MouseEvent event) {
        HBox hbox = (HBox)((Node)event.getSource());
        Image image01_onHover = new Image("/javafxmusicplayer/images/icons8_playlist_30px_3.png");
        Image image02_onHover = new Image("/javafxmusicplayer/images/icons8_person_male_24px_2.png");
        Image image03_onHover = new Image("/javafxmusicplayer/images/icons8_albums_24px_2.png");
        Image image04_onHover = new Image("/javafxmusicplayer/images/icons8_musical_notes_26px_1.png");
        Image image05_onHover = new Image("/javafxmusicplayer/images/icons8_play_button_circled_30px_3.png");
        Image image06_onHover = new Image("/javafxmusicplayer/images/icons8_radio_waves_30px_1.png");
        Image image07_onHover = new Image("/javafxmusicplayer/images/icons8_hearts_24px_2.png");
        Image image08_onHover = new Image("/javafxmusicplayer/images/icons8_browser_window_24px_1.png");
                
        switch(hbox.getId()){
            case "PLAYLIST":
                img1.setImage(image01_onHover);
                break;
            case "ALBUM":
                img2.setImage(image02_onHover);
                break;
            case "ARTIST":
                img3.setImage(image03_onHover);
                break;
            case "SONGS":
                img4.setImage(image04_onHover);
                break;
            case "STORE":
                img5.setImage(image05_onHover);
                break;
            case "RADIO":
                img6.setImage(image06_onHover);
                break;
            case "FORYOU":
                img7.setImage(image07_onHover);
                break;
            case "BROWSER":
                img8.setImage(image08_onHover);
                break;
                
        }
    }
    
}
