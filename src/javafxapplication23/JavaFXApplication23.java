/*
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
*/
/* 
   
    Author     : karos
*/
package javafxapplication23;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author karos
 */
public class JavaFXApplication23 extends Application {
   
     
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Log.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

public static void main(String[] args) {
    /*
   Запрос созданный дня привильного вывода связанных таблиц находиться в UserController.java (Вместо поля AutoID выводиться Model автомобиля.
   css файл я подключал непосредственно к каждому fxml файлу с помощью SceneBuilder , сам ксс файл находиться в пакете JavaFXApplication23
    */
        launch(args);
    }
    
}
