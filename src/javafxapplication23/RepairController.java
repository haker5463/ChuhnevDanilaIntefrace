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
public class RepairController implements Initializable {

    @FXML
    private Button A;
    @FXML
    private Button D;
    @FXML
    private Button O;
    @FXML
    private Button B;
    @FXML
    private Button E;
    @FXML
    private TextField IDRep;
    @FXML
    private TextField Name;
    @FXML
    private TextField Date_P;
    @FXML
    private TextField Quant;
    @FXML
    private TextField Price_x;
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
        if (event.getSource() == A) {
            String query = "insert into Repair values(" + IDRep.getText() + ",'" + Name.getText() + "','" + Date_P.getText() + "'," + Quant.getText() + "," + Price_x.getText() + ")";
            executeQuery(query);
        }

        //Кнопка Вывести(Активная)
        if (event.getSource() == O) {
            Ou.setText(null);
            String query = "SELECT * From Repair";
            try {
                Connection conn = DriverManager.getConnection("jdbc:mysql://mysql-162551.srv.hoster.ru/srv162551_db_danil", "srv162551_danil", "2010danil");
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {

                    Ou.appendText("IDRep= " + rs.getString(1) + " | Name= " + rs.getString(2) + " | Data= " + rs.getString(3) + " | Quant= " + rs.getString(4) + " | Price x1= " + rs.getString(5) + "\n");
                    Ou.setEditable(false);
                }

            } catch (SQLException sqlEx) {
                sqlEx.printStackTrace();
            }
        }

        //Кнопка Удалить (Активная)
        if (event.getSource() == D) {
            String query = "DELETE FROM Repair WHERE IDRep=" + IDRep.getText() + "";
            executeQuery(query);
        }

        //Кнопка Back 
        if (event.getSource() == B) {
            try {
                Stage stage = (Stage) B.getScene().getWindow();
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
        //Кнопка Exit
        if (event.getSource() == E) {

            Platform.exit();

        }
    }

}
