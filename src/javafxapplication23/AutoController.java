/*
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
 */
 /* 
    Created on : 22.12.2019, 20:58:30
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
 * @author karos
 */
public class AutoController implements Initializable {

    @FXML
    private Button Add;
    @FXML
    private Button Delete;
    @FXML
    private Button Output;
    @FXML
    private Button Back;
    @FXML
    private Button Ex;
    @FXML
    private TextField ID;
    @FXML
    private TextField MO;
    @FXML
    private TextField VE;
    @FXML
    private TextField VD;
    @FXML
    private TextField pN;
    @FXML
    private TextArea Ou;
    //метод описывающий добавление

    private void inser() {
        String query = "insert into Auto (AutoID, Model, year_V, volume_D, pNumber) \\n" + "values(" + ID.getText() + ",'" + MO.getText() + "','" + VE.getText() + "'," + VD.getText() + "," + pN.getText() + ")";
        executeQuery(query);

    }
    //Метод описывающий процедуру удаления

    @FXML
    private void deleten() {
        String query = "DELETE FROM Auto WHERE AutoID =" + ID.getText() + "";
        executeQuery(query);

    }

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

    //Делаем функционал кнопок
    @FXML
    private void handleButtonAction(ActionEvent event) throws SQLException {
        //Кнопка Добавить(Активная) 
        if (event.getSource() == Add) {
            String query = "insert into Auto values(" + ID.getText() + ",'" + MO.getText() + "','" + VE.getText() + "'," + VD.getText() + "," + pN.getText() + ")";
            executeQuery(query);
        }
        //Кнопка Вывести(Активная)
        if (event.getSource() == Output) {
            Ou.setText(null);
            String query = "SELECT * From Auto";
            try {
                Connection conn = DriverManager.getConnection("jdbc:mysql://mysql-162551.srv.hoster.ru/srv162551_db_danil", "srv162551_danil", "2010danil");
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {

                    Ou.appendText("AutoID= " + rs.getString(1) + ", Model= " + rs.getString(2) + ", Year= " + rs.getString(3) + ", Volume= " + rs.getString(4) + ", pNumber= " + rs.getString(5) + "\n");
                    Ou.setEditable(false);
                }

            } catch (SQLException sqlEx) {
                sqlEx.printStackTrace();
            }

        }

//Кнопка Удалить (Активная)
        if (event.getSource() == Delete) {
            String query = "DELETE FROM Auto WHERE AutoID=" + ID.getText() + "";
            executeQuery(query);
        }

        //Делаем кнопку Back активной
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
        //Делаем кнопку Exit активной
        if (event.getSource() == Ex) {

            Platform.exit();

        }
    }

}
