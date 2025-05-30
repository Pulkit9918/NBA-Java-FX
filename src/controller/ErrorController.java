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
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Teams;
import model.Team;

public class ErrorController extends Controller<Validator> {
    @FXML
    private Button okBtn;
    @FXML private Text errorsText;
    
    private Validator getValidator() {
        return this.model;
    }
    @FXML
    public void initialize() {
        for (int i = 0; i<getValidator().errors().size(); i++) {
            errorsText.setText(errorsText.getText() + getValidator().errors().get(i));
        }
    }
    @FXML private void handleOK(ActionEvent event) throws Exception {
        stage.close();
    }
    
}
