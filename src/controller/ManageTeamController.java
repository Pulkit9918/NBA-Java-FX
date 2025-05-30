package controller;

import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import model.Teams;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Player;
import model.Players;
import model.Teams;
import model.Team;


public class ManageTeamController extends Controller<Team> {
    @FXML private TextField teamNameTF;
    @FXML private TableView<Player> manageTable;
    @FXML
    private Button updBtn;
    @FXML
    private Button addBtn;
    @FXML
    private Button delBtn;
    @FXML
    private Button SACBtn;
    @FXML private TableColumn<Player, String> playerName;
    @FXML private TableColumn<Player, String> playerCredit;
    @FXML private TableColumn<Player, String> playerAge;
    @FXML private TableColumn<Player, String> playerNo;
    
    public Team getTeam(){
        return this.model;
    }
    
    private Player getSelectedPlayer() {
        return manageTable.getSelectionModel().getSelectedItem();
    }
    
    private String getTeamName() { return teamNameTF.getText(); }
    private void setTeamName(String type) { teamNameTF.setText(type); }
    
    @FXML
    public void initialize() {
      manageTable.setItems(this.model.getCurrentPlayers());
        playerName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        playerCredit.setCellValueFactory(cellData -> cellData.getValue().getPlayerCreditProperty().asString("$%.2f"));
        playerAge.setCellValueFactory(cellData -> cellData.getValue().getPlayerAgeProperty().asString());
        playerNo.setCellValueFactory(cellData -> cellData.getValue().getPlayerNoProperty().asString());
        updateTF();
        getTeam().nameProperty().addListener((obs, oldName, newName) -> {
            updateTF();
        });
        manageTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> delBtn.setDisable(true));
        addBtn.disableProperty().bind(manageTable.getSelectionModel().selectedItemProperty().isNotNull());
    }
    
    private void updateTF() {
        String newTeamName = getTeam().getName();
        if (newTeamName != null) {
            teamNameTF.setText(newTeamName);
        } else {
            teamNameTF.clear();
        }
    }
    
    @FXML private void handleSAC(ActionEvent event) throws Exception {
        getTeam().setName(teamNameTF.getText());
        stage.close();
    }
    @FXML private void handleADD(ActionEvent event) throws Exception {
        Player player = new Player("", -1.0, -1, -1);
        player.setTeam(this.model);
        Stage stage = new Stage();
        stage.getIcons().add(new Image("/view/edit.png"));
        ViewLoader.showStage(player, "/view/PlayerUpdateView.fxml", "Adding New Player", stage);
    }
    @FXML private void handleDEL(ActionEvent event) throws Exception {
        Player player =  manageTable.getSelectionModel().getSelectedItem();
        getTeam().getPlayers().removePlayer(player);
        manageTable.refresh();
    }
    @FXML private void handleUPD(ActionEvent event) throws Exception {
        Player player = manageTable.getSelectionModel().getSelectedItem();
        Stage stage = new Stage();
        stage.getIcons().add(new Image("/view/edit.png"));
        ViewLoader.showStage(player, "/view/PlayerUpdateView.fxml", "Updating Player:", stage);
    }
    
    
}
