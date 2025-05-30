package controller;

import model.Association;
import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Team;
import model.Teams;

public class ExploreTeamsController extends Controller<Teams>{
    
    @FXML
    private Button exploreTeamsButton;
    @FXML
    private Button teamMenuButton;
    @FXML
    private Button viewPlayersButton;

    public final Teams getTeams(){
        return this.model;
    }
    @FXML
    public void initialize() {
        
    }
    @FXML private void handleTM(ActionEvent event) throws Exception {
        Stage stage = new Stage();
        stage.getIcons().add(new Image("/view/nba.png"));
        ViewLoader.showStage(getTeams(), "/view/TeamsTable.fxml", "Teams menu", stage);
    }
    @FXML private void handleVP(ActionEvent event) throws Exception {
        Stage stage = new Stage();
        stage.getIcons().add(new Image("/view/nba.png"));
        ViewLoader.showStage(getTeams(), "/view/PlayersView.fxml", "Players", stage);
    }
    @FXML private void handleCL(ActionEvent event) throws Exception {
        stage.close();
    }
}
