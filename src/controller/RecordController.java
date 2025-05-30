package controller;

import au.edu.uts.ap.javafx.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Game;
import model.Player;
import model.Record;
import model.Season;


public class RecordController extends Controller<Season>{
    @FXML private TableView<Record> recordTable;
    @FXML
    private Button clBtn;
    @FXML private TableColumn<Record, String> Round;
    @FXML private TableColumn<Record, String> Game;
    @FXML private TableColumn<Record, String> winningTeam;
    @FXML private TableColumn<Record, String> losingTeam;
    
    public Season getSeason() {
        return this.model;
    }
    
    @FXML
    public void initialise() {
        recordTable.setItems(getSeason().record());
        Round.setCellValueFactory(cellData -> cellData.getValue().roundProperty().asString());
        Game.setCellValueFactory(cellData -> cellData.getValue().gameNumberProperty().asString());
        winningTeam.setCellValueFactory(cellData -> cellData.getValue().winTeamProperty());
        losingTeam.setCellValueFactory(cellData -> cellData.getValue().loseTeamProperty());
    }
    
    @FXML private void handleCL(ActionEvent event) throws Exception {
        stage.close();
    }
    
}
