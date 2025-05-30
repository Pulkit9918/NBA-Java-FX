package controller;

import au.edu.uts.ap.javafx.Controller;
import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Game;
import model.Player;
import model.Season;
import model.Team;


public class TeamsRoundController extends Controller<Season> {
    @FXML Label roundCount;
    @FXML private ListView<Team> allTeamsList;
    @FXML private TableView<Game> teamsAddedRoundTable;
    @FXML
    private Button ASBtn;
    @FXML
    private Button ADDBtn;
    @FXML private TableColumn<Game, String> Game;
    @FXML private TableColumn<Game, String> teamOne;
    @FXML private TableColumn<Game, String> teamTwo;
    
    public Season getSeason() {
        return this.model;
    }
    
    @FXML
    public void initialize() {
        allTeamsList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        allTeamsList.setItems(this.model.getCurrentTeams());
        allTeamsList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> 
                ASBtn.setDisable(allTeamsList.getSelectionModel().getSelectedItems().size() !=2));
        allTeamsList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> 
                ADDBtn.setDisable(allTeamsList.getSelectionModel().getSelectedItems().size() !=2));
    }
    
    @FXML private void handleAS(ActionEvent event) throws Exception {
        stage.close();
    }
    @FXML private void handleADD(ActionEvent event) throws Exception {
        model.addTeams(allTeamsList.getSelectionModel().getSelectedItems());
        teamsAddedRoundTable.setItems(this.model.getCurrentSchedule());
        Game.setCellValueFactory(cellData -> cellData.getValue().termProperty().asString());
        teamOne.setCellValueFactory(cellData -> cellData.getValue().team1());
        teamTwo.setCellValueFactory(cellData -> cellData.getValue().team2());
    }
}
