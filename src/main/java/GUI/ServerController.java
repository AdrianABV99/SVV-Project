package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;

import java.io.File;
import java.io.IOException;

public class ServerController {


    @FXML
    private Label serverStatus;
    @FXML
    private Label serverAddress;
    @FXML
    private Label serverListeningPort;
    @FXML
    private TextField setWebRoot;
    @FXML
    private TextField setMaintenanceRoot;
    @FXML
    private TextField serverPort;
    @FXML
    private Button buttonServerStatus;
    @FXML
    public Button btnWebRootDirectory;
    @FXML
    public Button btnMaintenanceDir;
    @FXML
    public CheckBox checkSwitch;

    public void initialize(){
        setTexts();
        setMaintenanceRoot.setText(guiMain.serverMaintenance);
        setWebRoot.setText(guiMain.serverRoot);
        serverPort.setText(String.valueOf(guiMain.serverListeningPort));
    }


    private void setTexts() {
        serverStatus.setText("Server Status: " + guiMain.webServerGUI.serverStatus);
        switch (guiMain.webServerGUI.serverStatus) {
            case "RUN_SERVER":
                buttonServerStatus.setText("Stop server");
                serverStatus.setText("running...");
                serverAddress.setText(guiMain.serverAddres);
                serverListeningPort.setText(String.valueOf(guiMain.serverListeningPort));
                checkSwitch.setDisable(false);
                serverPort.setDisable(true);
                btnWebRootDirectory.setDisable(true);
                setWebRoot.setDisable(true);
                btnMaintenanceDir.setDisable(false);
                setMaintenanceRoot.setDisable(false);
                break;
            case "MAINTENANCE_SERVER":
                serverStatus.setText("maintenance");
                serverAddress.setText(guiMain.serverAddres);
                serverListeningPort.setText(String.valueOf(guiMain.serverListeningPort));
                serverPort.setDisable(true);
                btnWebRootDirectory.setDisable(false);
                setWebRoot.setDisable(false);
                btnMaintenanceDir.setDisable(true);
                setMaintenanceRoot.setDisable(true);
                break;
            case "STOP_SERVER":
                buttonServerStatus.setText("Start server");
                serverStatus.setText("not running");
                serverAddress.setText("not running");
                serverListeningPort.setText("not running");
                checkSwitch.setDisable(true);
                serverPort.setDisable(false);
                serverPort.setText(String.valueOf(guiMain.serverListeningPort));
                btnWebRootDirectory.setDisable(false);
                setWebRoot.setDisable(false);
                btnMaintenanceDir.setDisable(false);
                setMaintenanceRoot.setDisable(false);
                break;
        }
    }


    @FXML
    protected void onHelloButtonClick() throws InterruptedException, IOException {
        if(guiMain.webServerGUI.serverStatus.equals("RUN_SERVER")) {
            guiMain.webServerGUI.serverStatus = "STOP_SERVER";
            //guiMain.serverSocketGUI.close();
            setTexts();
        } else {
            guiMain.webServerGUI.serverStatus = "RUN_SERVER";
            setTexts();
        }
        System.out.println("CURRENT SERVER STATUS:" + guiMain.webServerGUI.serverStatus);
    }
    @FXML
    public void chooseDirectoryMaintenance(javafx.event.ActionEvent actionEvent) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File file = directoryChooser.showDialog(null);

        if(file != null) {
            setMaintenanceRoot.setText(file.getAbsolutePath());
            guiMain.serverMaintenance = file.getAbsolutePath() + "\\index.html";
        }
    }
    @FXML
    public void chooseDirectoryRoot(javafx.event.ActionEvent actionEvent) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File file = directoryChooser.showDialog(null);

        if(file != null) {
            setWebRoot.setText(file.getPath());
            guiMain.serverRoot = file.getAbsolutePath() + "\\";
        }
    }

    @FXML
    public void checkSwitchMode(ActionEvent actionEvent) {
        if(!checkSwitch.isSelected()) {
            guiMain.webServerGUI.serverStatus = "RUN_SERVER";
            setTexts();
        } else {
            guiMain.webServerGUI.serverStatus = "MAINTENANCE_SERVER";
            setTexts();
        }
    }
    @FXML
    public void setServerPort(ActionEvent actionEvent) {
        guiMain.serverListeningPort = Integer.parseInt(serverPort.getText());
        setTexts();
    }

    public void webRootDirAction(ActionEvent actionEvent) {
    }

    public void maintenanceDirAction(ActionEvent actionEvent) {
    }
}
