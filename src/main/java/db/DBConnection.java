package db;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customer;

import java.util.ArrayList;
import java.util.List;

public class DBConnection {
    private static DBConnection instance;

    private final List<Customer> customerList;

    //Title list
    private ObservableList<String> titles;

    private DBConnection(){
        customerList = new ArrayList<>();
        titles = FXCollections.observableArrayList();
    }

    public static DBConnection getInstance(){
        if(instance == null){
            instance = new DBConnection();
        }
        return instance;
    }

    //Get customer array-list
    public List<Customer> getConnection(){
        return customerList;
    }

    //Get titles list
    public ObservableList<String> getTitles(){
        titles.add("Mr.");
        titles.add("Miss");

        return titles;
    }

}
