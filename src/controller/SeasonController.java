package controller;

import au.edu.uts.ap.javafx.Controller;
import model.Association;
import au.edu.uts.ap.javafx.ViewLoader;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Season;


public class SeasonController extends Controller<Season>  {
    @FXML
    private Button roundButton;
    @FXML
    private Button gameButton;
    @FXML
    private Button closeButton;
    @FXML
    private Button resultButton;
    @FXML
    private Button currentButton;
    public Season getSeason(){
        return this.model;
    }
    
    @FXML private void handleRound(ActionEvent event) throws Exception {
        Stage stage = new Stage();
        stage.getIcons().add(new Image("/view/nba.png"));
        ViewLoader.showStage(getSeason(), "/view/SeasonRoundView.fxml", "Season Rounds", stage);
    }
    @FXML private void handleCurrent(ActionEvent event) throws Exception {
        Stage stage = new Stage();
        stage.getIcons().add(new Image("/view/nba.png"));
        ViewLoader.showStage(getSeason(), "/view/CurrentRoundTeams.fxml", "Tournament", stage);
    }
    @FXML private void handleGame(ActionEvent event) throws Exception {
//        this.model.playGame();
        Stage stage = new Stage();
        stage.getIcons().add(new Image("/view/nba.png"));
        ViewLoader.showStage(getSeason(), "/view/error.fxml", "All Games Played!", stage);
    }
    @FXML private void handleResult(ActionEvent event) throws Exception {
        Stage stage = new Stage();
        stage.getIcons().add(new Image("/view/nba.png"));
        ViewLoader.showStage(getSeason(), "/view/RecordView.fxml", "Season Record", stage);
    }
    @FXML private void handleClose(ActionEvent event) throws Exception {
        stage.close();
    }
}
