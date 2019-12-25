/*
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
 */
 /* 
   
    Author     : karos
 */
package javafxapplication23;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 *
 * @author user
 */
public class FXMLDocumentController implements Initializable {

    private Button Log;
    private Button Back;
    private Button Ex;
    @FXML
    private Button Ad;
    @FXML
    private Button Del;
    @FXML
    private Button Out;
    @FXML
    private Button Bac;
    @FXML
    private Button Et;
    @FXML
    private TextField MastersIDD;
    @FXML
    private TextField FIOO;
    @FXML
    private TextField Speciff;
    @FXML
    private TextField Mone;
    @FXML
    private TextField Adrr;
    @FXML
    private TextField Year_Job;
    @FXML
    private TextField Number_phone;
    @FXML
    private TextArea Ou;

    // подключение
    public void executeQuery(String query) {
        Connection conn = getConnection();
        Statement st;
        try {
            st = conn.createStatement();
            st.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        Connection conn;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://mysql-162551.srv.hoster.ru/srv162551_db_danil", "srv162551_danil", "2010danil");
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {

        if (event.getSource() == Log) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Menu.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.initStyle(StageStyle.UNDECORATED);
                stage.setTitle("ABC");
                stage.setScene(new Scene(root1));
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        if (event.getSource() == Back) {
            try {
                Stage stage = (Stage) Back.getScene().getWindow();
                stage.close();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Menu.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                //     Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.initStyle(StageStyle.UNDECORATED);
                stage.setTitle("ABC");
                stage.setScene(new Scene(root1));
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        if (event.getSource() == Ex) {

            Platform.exit();

        }

        //Кнопка Добавить(Активная) 
        if (event.getSource() == Ad) {
            String query = "insert into Masters values(" + MastersIDD.getText() + ",'" + FIOO.getText() + "','" + Speciff.getText() + "'," + Mone.getText() + ",'" + Adrr.getText() + "'," + Year_Job.getText() + ",'" + Number_phone.getText() + "')";
            executeQuery(query);
        }
        // кнопка Output(Активная)
        if (event.getSource() == Out) {
            Ou.setText(null);
            String query = "SELECT * From Masters";
            try {
                Connection conn = DriverManager.getConnection("jdbc:mysql://mysql-162551.srv.hoster.ru/srv162551_db_danil", "srv162551_danil", "2010danil");
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {

                    Ou.appendText("MastersID= " + rs.getString(1) + " | FIO= " + rs.getString(2) + " | Specif= " + rs.getString(3) + " | Money= " + rs.getString(4) + " | Address= " + rs.getString(5) + " | Year Job " + rs.getString(6) + " | Number Phone " + rs.getString(7) + "\n");
                    Ou.setEditable(false);
                }

            } catch (SQLException sqlEx) {
                sqlEx.printStackTrace();
            }
        }

        //Кнопка Удалить (Активная)
        if (event.getSource() == Del) {
            String query = "DELETE FROM Masters WHERE MastersID=" + MastersIDD.getText() + "";
            executeQuery(query);
        }

// кнопка Back у окна Masters
        if (event.getSource() == Bac) {
            try {
                Stage stage = (Stage) Bac.getScene().getWindow();
                stage.close();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Menu.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                //     Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.initStyle(StageStyle.UNDECORATED);
                stage.setTitle("ABC");
                stage.setScene(new Scene(root1));
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        //кнопка Exit у окна Masters
        if (event.getSource() == Et) {

            Platform.exit();

        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
