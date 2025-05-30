package controller;

import au.edu.uts.ap.javafx.Controller;
import model.Association;
import au.edu.uts.ap.javafx.ViewLoader;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.beans.binding.Bindings;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Teams;
import model.Team;

public class AddTeamController extends Controller<Teams>{
    @FXML private TextField newTeamNameTF;
    @FXML
    private Button addBtn;

    public Teams getTeams(){
        return this.model;
    }
    
    private String getNewName() {
      return this.newTeamNameTF.getText(); 
    }
    
    @FXML private void handleADD(ActionEvent event) throws Exception {
        Validator validator = new Validator();
        String newTeam = getNewName();
        Team team = new Team(newTeam);
        if(validator.isValid(newTeam)) {
            try {
                if(getTeams().hasTeam(newTeam)) {
                    stage.getIcons().add(new Image("/view/error.png"));
                    throw new Exception(newTeam + "Team already exists");
                }
                getTeams().addTeam(team);
                stage.close();
            }
            catch (Exception exception) {
                validator.addError(newTeam + "Team already exists");
                ViewLoader.showStage(validator, "/view/error.fxml", "Error!", stage);
            }
        }
    }
    
}

