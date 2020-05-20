import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class profileController {

    private StringProperty name = new SimpleStringProperty();
    private StringProperty firm = new SimpleStringProperty();

    @FXML
    private TextField nameField;

    @FXML
    private TextField firmField;

    @FXML
    private TextField jobTitleField;

    @FXML
    private TextField practiceAreaField;

    @FXML
    private TextField specialityField;

    @FXML
    private TextField ethnicityField;

    @FXML
    private TextField approachedField;

    @FXML
    private TextField firmProfileField;

    @FXML
    private TextField linkedinProfileField;

    @FXML
    private TextField phoneNoField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField admissionDateField;

    @FXML
    private TextField admissionJuristictionField;

    @FXML
    private MenuBar menuBar;

    @FXML
    private Menu saveMenu;

    @FXML
    private MenuItem updateRecordButton;

    @FXML
    private MenuItem deleteRecordMenu;

    @FXML
    private GridPane commentGrid;

    @FXML
    private Label label0;

    @FXML
    private Label label2;

    @FXML
    private Label label3;

    @FXML
    private Label label4;

    @FXML
    private Label label5;

    @FXML
    private TextArea commentField;

    @FXML
    private Button sendCommentButton;


    private People rowDataUpdate;


    public void changeLabels(People rowData) {

        rowDataUpdate = rowData;

        nameField.setText(nameField.getText()+rowData.getName());
        firmField.setText(firmField.getText()+rowData.getFirmName());

        if (rowData.getFirmProfile() != null){
            firmProfileField.setText(rowData.getFirmProfile());
        }
        if (rowData.getJobTitle()!= null){
            jobTitleField.setText(rowData.getJobTitle());
        }
        if (rowData.getPractiseArea()!= null){
            practiceAreaField.setText(rowData.getPractiseArea());
        }
        if (rowData.getSpeciality()!= null){
            specialityField.setText(rowData.getSpeciality());
        }
        if (rowData.getEthnicity()!= null){
            ethnicityField.setText(rowData.getEthnicity());
        }
        if (rowData.getLinkedinProfile()!= null){
            linkedinProfileField.setText(linkedinProfileField.getText()+rowData.getLinkedinProfile());
        }
        if (rowData.getPhoneNo()!= null){
            phoneNoField.setText(phoneNoField.getText()+rowData.getPhoneNo());
        }
        if (rowData.getEmail()!= null){
            emailField.setText(emailField.getText()+rowData.getEmail());
        }
        if (rowData.getAdmissionJuristiction()!= null){
            admissionJuristictionField.setText(admissionJuristictionField.getText()+rowData.getAdmissionJuristiction());
        }
        if (rowData.getAdmissionDate()!= null){
            admissionDateField.setText(admissionDateField.getText()+rowData.getAdmissionDate());
        }
        if (rowData.getApproached()!= null){
            approachedField.setText(rowData.getApproached());
        }
    }

    public void updateRecordButton(Event e) throws IOException {

        getPerson();
        Database.editRecord(rowDataUpdate);



    }

    public void removeRecordButton(Event e) throws IOException {

        getPerson();
        Database.removeEntry(rowDataUpdate);
        Stage stage = (Stage) nameField.getScene().getWindow();
        stage.getOnCloseRequest().handle(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
        stage.close();

    }

    private void getPerson() {
        rowDataUpdate.setName(nameField.getText());
        rowDataUpdate.setFirmName(firmField.getText());
        rowDataUpdate.setAdmissionDate(admissionDateField.getText());
        rowDataUpdate.setAdmissionJuristiction(admissionJuristictionField.getText());
        rowDataUpdate.setApproached(approachedField.getText());
        rowDataUpdate.setEmail(emailField.getText());
        rowDataUpdate.setEthnicity(ethnicityField.getText());
        rowDataUpdate.setFirmProfile(firmProfileField.getText());
        rowDataUpdate.setJobTitle(jobTitleField.getText());
        rowDataUpdate.setLinkedinProfile(linkedinProfileField.getText());
        rowDataUpdate.setPhoneNo(phoneNoField.getText());
        rowDataUpdate.setPractiseArea(practiceAreaField.getText());
        rowDataUpdate.setSpeciality(specialityField.getText());
    }

    public void sendCommentButton(Event e) {
        String commentToSend = commentField.getText();
        if (commentToSend.length() <= 400){

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText("Maximum of 400 characters per comment");
            alert.initStyle(StageStyle.UTILITY);
            alert.showAndWait();
        }
    }

    public void showComments(People rowData) {

        ArrayList<String[]> comments = Database.getComments(rowData.getPersonID());

    }
}