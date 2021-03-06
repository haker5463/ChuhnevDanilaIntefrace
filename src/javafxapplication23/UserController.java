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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author user
 */
public class UserController implements Initializable {

    @FXML
    private Button Add;
    @FXML
    private Button Delete;
    @FXML
    private Button Out;
    @FXML
    private Button Back;
    @FXML
    private Button Exit;
    @FXML
    private TextField IDUser;
    @FXML
    private TextField FIO;
    @FXML
    private TextField Number_Phone;
    @FXML
    private TextField AutoID;
    @FXML
    private TextField Price_repair;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {

        //Кнопка Добавить(Активная) 
        if (event.getSource() == Add) {
            String query = "insert into User values(" + IDUser.getText() + ",'" + FIO.getText() + "','" + Number_Phone.getText() + "'," + AutoID.getText() + "," + Price_repair.getText() + ")";
            executeQuery(query);
        }
        //Кнопка Вывести(Активная)
        if (event.getSource() == Out) {
            Ou.setText(null);

//      Формирования запроса для отображения связи таблиц
            String query = "SELECT User.IDUSER,User.fiop,User.Number_Phone, Auto.`Model` as `AutoID`, User.price_repair FROM User INNER JOIN Auto ON (User.`AutoID`= Auto.`AutoID`)";
            try {
                Connection conn = DriverManager.getConnection("jdbc:mysql://mysql-162551.srv.hoster.ru/srv162551_db_danil", "srv162551_danil", "2010danil");
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {

                    Ou.appendText("IDUser= " + rs.getString(1) + " | FIO= " + rs.getString(2) + " | Number Phone= " + rs.getString(3) + " | AutoID= " + rs.getString(4) + " | Price repair= " + rs.getString(5) + "\n");
                    Ou.setEditable(false);
                }

            } catch (SQLException sqlEx) {
                sqlEx.printStackTrace();
            }
        }

        //Кнопка Удалить (Активная)
        if (event.getSource() == Delete) {
            String query = "DELETE FROM User WHERE IDUser=" + IDUser.getText() + "";
            executeQuery(query);
        }

        //Кнопка Back
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
        //кнопка Exit
        if (event.getSource() == Exit) {

            Platform.exit();

        }

    }
}
