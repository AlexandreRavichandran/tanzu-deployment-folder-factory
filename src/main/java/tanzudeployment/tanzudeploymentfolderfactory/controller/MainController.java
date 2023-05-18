package tanzudeployment.tanzudeploymentfolderfactory.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import tanzudeployment.tanzudeploymentfolderfactory.MainApplication;
import tanzudeployment.tanzudeploymentfolderfactory.service.ConfigEditor;
import tanzudeployment.tanzudeploymentfolderfactory.service.factory.FolderFactory;

import java.io.IOException;
import java.net.URL;
import java.nio.file.FileAlreadyExistsException;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import static java.util.Objects.nonNull;

public class MainController implements Initializable {

    @FXML
    public TextField folderPath;

    @FXML
    public DatePicker deploymentDate;

    @FXML
    public TextField deploymentVersion;

    @FXML
    public Button createFolderButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ConfigEditor configEditor = new ConfigEditor();
        this.folderPath.setDisable(Boolean.TRUE);
        this.folderPath.setText(configEditor.getMainConfig().getFolderBasePath());
    }

    public void onCreateFolder() throws IOException {
        this.createFolderButton.setDisable(Boolean.TRUE);
        FolderFactory factory = new FolderFactory();
        String message = "";
        String title = "";
        try {
            factory.createElement(this.folderPath.getText());
            title = "Success";
            message = "Folder created";
        } catch (FileAlreadyExistsException e) {
            title = "Error";
            message = "Folder already exists";
        } finally {
            this.showNotificationWindow(title, message);
            this.createFolderButton.setDisable(Boolean.FALSE);
        }

    }

    @FXML
    private void updateFolderPath() {
        ConfigEditor configEditor = new ConfigEditor();
        if (Boolean.TRUE.equals(areProductionDateAndDeploymentDateFieldsFilled())) {
            String updatedFolderPath =
                    configEditor.getMainConfig().getFolderBasePath() +
                    "Daytonext-" +
                    this.deploymentVersion.getText() +
                    "_" +
                    this.formatDate();

            this.folderPath.setText(updatedFolderPath);
            this.folderPath.setDisable(Boolean.FALSE);
        }

    }

    private Boolean areProductionDateAndDeploymentDateFieldsFilled() {

        return nonNull(this.deploymentDate.getValue()) && nonNull(this.deploymentVersion.getText());

    }

    private String formatDate() {
        return this.deploymentDate.getValue().format(DateTimeFormatter.ofPattern("MM-dd"));
    }

    private void showNotificationWindow(String label, String message) throws IOException {

        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("notification.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 200, 100);
        stage.setTitle(label);

        NotificationController notificationController = fxmlLoader.getController();
        notificationController.message.setText(message);
        fxmlLoader.setController(notificationController);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }
}