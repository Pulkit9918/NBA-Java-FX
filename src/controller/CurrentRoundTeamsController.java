package controller;

import au.edu.uts.ap.javafx.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Game;
import model.Season;


public class CurrentRoundTeamsController extends Controller<Season> {
    @FXML Label titleLabel;
    @FXML private TableView<Game> currentRoundTable;
    @FXML
    private Button clBtn;
    @FXML private TableColumn<Game, String> teamONE;
    @FXML private TableColumn<Game, String> teamTWO;

  public Season getSeason() {
      return this.model;
  }
  @FXML
  public void initialize() {
      titleLabel.setText("Round: " + 1);
      currentRoundTable.setItems(model.getCurrentSchedule());
      teamONE.setCellValueFactory(cellData -> cellData.getValue().team1());
      teamTWO.setCellValueFactory(cellData -> cellData.getValue().team2());
  }
  @FXML private void handleCL(ActionEvent event) throws Exception {
        stage.close();
    }
}
