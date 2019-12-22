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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author user
 */
public class LogController implements Initializable {

    @FXML
    private Button log;
    @FXML
    private TextField login;
    @FXML
    private TextField password;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
  
    @FXML
    private void handleButtonAction(ActionEvent event) {
    String lg= login.getText();
    String pw= password.getText();
    String l="Danil";
    String p="123321";
        if (event.getSource()==log){
       if ((lg.equals(l))&&(pw.equals(p))) {
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
        
   } else {
           
  login.setText("ERROR");
  password.setText("ERROR");
  
   }
    
}}}
