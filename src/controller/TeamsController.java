package controller;

import au.edu.uts.ap.javafx.Controller;
import model.Association;
import au.edu.uts.ap.javafx.ViewLoader;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Teams;
import model.Team;


public class TeamsController extends Controller<Teams> {
    
     @FXML
    private Button addBtn;
      @FXML
    private Button delBtn;
       @FXML
    private Button clBtn;
        @FXML
    private Button manageBtn;
    private Team team;
    private Teams teams;
    @FXML private TableView<Team> teamsTable;
    @FXML private TableColumn<Team, String> avgAge;
    @FXML private TableColumn<Team, String> avgPC;
    @FXML private TableColumn<Team, String> playerNo;
    @FXML private TableColumn<Team, String> teamName;
    
    public Teams getTeams(){
        return this.model;
    }
    
    private Team getSelectedTeam() {
        return teamsTable.getSelectionModel().getSelectedItem();
    }
    
    @FXML
    public void initialize() {
        teamsTable.setItems(getTeams().currentTeams());
        teamName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        playerNo.setCellValueFactory(cellData -> cellData.getValue().countPlayerProperty().asString());
        avgPC.setCellValueFactory(cellData -> cellData.getValue().countAvgCreditProperty().asString("$%.2f"));
        avgAge.setCellValueFactory(cellData -> cellData.getValue().countAvgAgeProperty().asString("$%.2f"));
        delBtn.disableProperty().bind(Bindings.isEmpty(teamsTable.getSelectionModel().getSelectedItems()));
        manageBtn.disableProperty().bind(Bindings.isEmpty(teamsTable.getSelectionModel().getSelectedItems()));
        addBtn.disableProperty().bind(teamsTable.getSelectionModel().selectedItemProperty().isNotNull());
    }
    
    @FXML private void handleCL(ActionEvent event) throws Exception {
        stage.close();
    }
    @FXML private void handleDEL(ActionEvent event) throws Exception {
        Team team = teamsTable.getSelectionModel().getSelectedItem();
        getTeams().remove(team);
        teamsTable.refresh();
    }
    @FXML private void handleADD(ActionEvent event) throws Exception {
        Team team = new Team("");
        Stage stage = new Stage();
        stage.getIcons().add(new Image("/view/edit.png"));
        ViewLoader.showStage(getTeams(), "/view/AddTeam.fxml", "Adding New Team", stage);
    }
    @FXML private void handleM(ActionEvent event) throws Exception {
        Team team = teamsTable.getSelectionModel().getSelectedItem();
        Stage stage = new Stage();
        stage.getIcons().add(new Image("/view/edit.png"));
        ViewLoader.showStage(team, "/view/ManageTeamView.fxml", "Managing Team", stage);
    }
    
}
