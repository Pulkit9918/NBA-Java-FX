package controller;

import au.edu.uts.ap.javafx.Controller;
import javafx.fxml.FXML;
import model.Association;
import au.edu.uts.ap.javafx.ViewLoader;
import java.awt.event.KeyEvent;
import static java.lang.Integer.parseInt;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Player;
import model.Players;
import model.Team;
import model.Teams;


public class ViewPlayersController extends Controller<Teams> {
    @FXML private TableView<Player> playersTable;
    @FXML
    private Button clBtn;
    @FXML private TextField levelFilterTF;
    @FXML private TextField nameFilterTF;
    @FXML private TextField startAgeTF;
    @FXML private TextField endAgeTF;
    @FXML private TableColumn<Player, String> team;
    @FXML private TableColumn<Player, String> playerCredit;
    @FXML private TableColumn<Player, String> playerAge;
    @FXML private TableColumn<Player, String> playerName;
    @FXML private TableColumn<Player, String> playerNo;
    @FXML private TableColumn<Player, String> playerLevel;
    public ObservableList<Player> filteredPlayers = FXCollections.<Player>observableArrayList();
    
    public Teams getTeams(){
        return this.model;
    }
    
    private Player getSelectedPlayer() {
        return playersTable.getSelectionModel().getSelectedItem();
    }
    
    @FXML
    public void initialize() {
        filteredPlayers.addAll(getTeams().allPlayersList());
         playersTable.setItems(filteredPlayers);
         team.setCellValueFactory(cellData -> cellData.getValue().getTeamNameProperty());
         playerCredit.setCellValueFactory(cellData -> cellData.getValue().getPlayerCreditProperty().asString());
         playerAge.setCellValueFactory(cellData -> cellData.getValue().getPlayerAgeProperty().asString());
         playerName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
         playerNo.setCellValueFactory(cellData -> cellData.getValue().getPlayerNoProperty().asString());
         playerLevel.setCellValueFactory(cellData -> cellData.getValue().levelProperty());
         startAgeTF.setText("0");
         endAgeTF.setText("0");
         
        nameFilterTF.textProperty().addListener(
                (observable, oldValue, newValue) -> 
                        filteredTable()
        );
        levelFilterTF.textProperty().addListener(
                (observable, oldValue, newValue) ->
                filteredTable()
        );
        startAgeTF.textProperty().addListener(
                (observable, oldValue, newValue) ->
                filteredTable()
        );
        endAgeTF.textProperty().addListener(
                (observable, oldValue, newValue) ->
                filteredTable()
        );
    }
    
    @FXML private void handleCL(ActionEvent event) throws Exception {
        stage.close();
    }
    
    private ObservableList<Player> filteredTable() {
        filteredPlayers.clear();
        
        for (int i = 0; i<getTeams().currentTeams().size();i++) {
            getTeams().currentTeams().get(i).filterList(nameFilterTF.getText(), levelFilterTF.getText(), Integer.parseInt(startAgeTF.getText()), Integer.parseInt(endAgeTF.getText()));
            filteredPlayers.addAll(getTeams().currentTeams().get(i).getPlayers().getFilteredPlayers());
        }
        return filteredPlayers;
    }
    
}
