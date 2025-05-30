package controller;

import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import model.Player;
import model.Players;
import model.Team;



public class PlayerUpdateController extends Controller<Player> {
    @FXML private TextField newNameTF;
    @FXML private TextField newAgeTF;
    @FXML private TextField newCreditTF;
    @FXML private TextField newNoTF;
    @FXML
    private Button updBtn;
    @FXML
    private Button addBtn;
    @FXML
    private Button clBtn;
    
    public Player getPlayer() {
        return this.model;
    }
    
    private String getPlayerName() { return newNameTF.getText(); }
    private int getPlayerAge() { return Integer.parseInt(newAgeTF.getText()); }
    private double getPlayerCredit() { return Double.parseDouble(newCreditTF.getText()); }
    private int getPlayerNo() { return  Integer.parseInt(newNoTF.getText()); }
    
    @FXML
    public void initialise() {
        newNameTF.setText(getPlayer().getName());
        newAgeTF.setText(""+getPlayer().getCredit());
        newCreditTF.setText(""+getPlayerCredit());
        newNoTF.setText(""+getPlayerNo());
    }
    
    @FXML private void handleCL(ActionEvent event) throws Exception {
        stage.close();
    }
    @FXML private void handleUPD(ActionEvent event) throws Exception {
        addBtn.setDisable(true);
        
        stage.close();

    }
    @FXML private void handleADD(ActionEvent event) throws Exception {
        updBtn.setDisable(true);
        Validator validator = new Validator();
        String newName = newNameTF.getText();
        String newAge = newAgeTF.getText();
        String newCredit = newCreditTF.getText();
        String newNumber = newNoTF.getText();
        
        Team team = getPlayer().getTeam();
        if(validator.isValid(newName, newAge, newCredit, newNumber)) {
            try {
                Player newPlayer = new Player(getPlayerName(), getPlayerCredit(), getPlayerAge(), getPlayerNo());
                team.getPlayers().addPlayer(newPlayer);
                stage.close();
            }
            catch (Exception exception) {
                ViewLoader.showStage(validator, "/view/error.fxml", "Error!", stage);
            }
        }
        else {
            validator.generateErrors(newName, newAge, newCredit, newNumber);
            stage.getIcons().add(new Image("/view/error.png"));
            ViewLoader.showStage(validator, "/view/error.fxml", "Error!", stage);
        }
    }
}
v
