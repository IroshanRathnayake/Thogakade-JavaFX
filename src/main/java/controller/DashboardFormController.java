package controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class DashboardFormController {

    @FXML
    private JFXButton btnAddNewCustomer;

    @FXML
    private JFXButton btnManageCustomer;

    @FXML
    void btnAddNewCustomerOnAction(ActionEvent event) {
        try{
            Stage stage =new Stage();
            stage.setScene(new Scene(
                    FXMLLoader.load(getClass().getResource("/view/add_customer_form.fxml"))
            ));
            stage.setTitle("Add New Customer");
            stage.getIcons().add(new Image("img/logo.png"));
            stage.show();
        }catch (IOException e){
            new Alert(Alert.AlertType.ERROR, "Error: \n"+e);
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnManageCustomerOnAction(ActionEvent event) {
        try{
            Stage stage =new Stage();
            stage.setScene(new Scene(
                    FXMLLoader.load(getClass().getResource("/view/manage_customer_form.fxml"))
            ));
            stage.setTitle("Manage Customer");
            stage.getIcons().add(new Image("img/logo.png"));
            stage.show();
        }catch (IOException e){
            new Alert(Alert.AlertType.ERROR, "Error: \n"+e);
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnViewCustomerOnAction(ActionEvent event) {
        try{
            Stage stage =new Stage();
            stage.setScene(new Scene(
                    FXMLLoader.load(getClass().getResource("/view/view_customer_form.fxml"))
            ));
            stage.setTitle("View Customer");
            stage.getIcons().add(new Image("img/logo.png"));
            stage.show();
        }catch (IOException e){
            new Alert(Alert.AlertType.ERROR, "Error: \n"+e);
            throw new RuntimeException(e);
        }
    }


}
