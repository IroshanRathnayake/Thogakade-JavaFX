package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import db.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.image.ImageView;
import model.Customer;
import validation.DataValidation;
import javafx.scene.image.Image;

import java.net.URL;
import java.text.ParseException;
import java.util.List;
import java.util.ResourceBundle;


public class AddCustomerFormController implements Initializable {

    @FXML
    private JFXComboBox<String> cmbTitle;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtContact;

    @FXML
    private JFXTextField txtCustomerID;

    @FXML
    private DatePicker txtDateOfBirth;

    @FXML
    private JFXTextField txtName;

    //initialize object variable for data validations
    DataValidation dataValidation;

    @FXML
    void btnAddOnAction(ActionEvent event) {
        List<Customer> customerList = DBConnection.getInstance().getConnection();

        //get Data for validations
        String id = txtCustomerID.getText();
        String dob = txtDateOfBirth.getValue() != null ? txtDateOfBirth.getValue().toString() : null;
        String contact = txtContact.getText();

        try{
            //Validate Customer ID, Birthday & Contact
            if (!dataValidation.isDuplicateID(customerList, id) && !dataValidation.validateDOB(dob) && !dataValidation.validateContact(contact)) {
                customerList.add(new Customer(
                        id,
                        cmbTitle.getValue(),
                        txtName.getText(),
                        txtAddress.getText(),
                        txtContact.getText(),
                        txtDateOfBirth.getValue()
                ));
                showAlert(Alert.AlertType.INFORMATION,
                        "Successfully Added",
                        "Customer added successfully!",
                        "img/success-48.png");
                clearField();
            } else {
                showAlert(Alert.AlertType.WARNING,
                        "Warning",
                        "Warning: \nInvalid inputs are Occurred. \n\n" +
                                "Check following data : \n\t[1] Duplicate Customer ID\n\t[2] Invalid Contact (07xxxxxxxx) \n\t[3]Invalid Birthday format" +
                                "\n\n\tPlease input valid data!",
                        "img/warning-48.png");
            }
        }catch (ParseException e){
            showAlert(Alert.AlertType.ERROR,
                    "Error","Invalid date format. Please enter a valid date of birth.", "img/error-48.png");
        }catch (Exception e){
            showAlert(Alert.AlertType.ERROR,"Error","An unexpected error occurred: \n"+e.getMessage(), "img/error-48.png");
        }

        //For debugging purposes
        customerList.forEach(ob -> System.out.println(ob.toString()));
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearField();
    }


    //Clear all the textFields to null
    private void clearField() {
        txtCustomerID.setText(null);
        cmbTitle.setValue(null);
        txtName.setText(null);
        txtAddress.setText(null);
        txtContact.setText(null);
        txtDateOfBirth.setValue(null);
    }

    //Custom Alert
    private void showAlert(Alert.AlertType alertType, String title, String content, String path) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.setHeaderText(null);

        //Set Alert Icon
        Image customIcon = new Image(path);
        ImageView imageView = new ImageView(customIcon);
        alert.getDialogPane().setGraphic(imageView);

        alert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dataValidation = new DataValidation();
        cmbTitle.setItems(DBConnection.getInstance().getTitles());
    }
}
