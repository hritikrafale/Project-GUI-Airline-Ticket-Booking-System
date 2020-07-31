package application;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class HomePageController implements Initializable {

	private String from,to,discount_category;
	
	private LocalDate departure_date,return_date;
	
	
	private ObservableList<String> list = FXCollections.observableArrayList("Bombay","Bhubaneshwar","Chennai","Delhi","Bangalore");
	
	
	@FXML
	private ComboBox<String> tab1_fromcombobox;
	
	@FXML
	private ComboBox<String> tab1_tocombobox;
	
	@FXML
	private ComboBox<String> tab2_fromcombobox;
	
	@FXML
	private ComboBox<String> tab2_tocombobox;
	
	@FXML
	private Tab tab1;
	
	@FXML
	private Tab tab2;
	
	@FXML
	private TextField tab1_num_of_passengers;
	
	@FXML
	private TextField tab2_num_of_passengers;
	
	@FXML
	private DatePicker tab1_departure_date;
	
	@FXML
	private DatePicker  tab2_departure_date;
	
	@FXML
	private DatePicker  tab2_return_date;
	
	@FXML
	private RadioButton tab1_armed_forces;
	
	@FXML
	private RadioButton tab2_armed_forces;
	
	@FXML
	private RadioButton tab1_students;
	
	@FXML
	private RadioButton tab2_students;
	
	@FXML
	private Button tab1_search;
	
	@FXML
	private Button tab2_search;
	
	@FXML
	private RadioButton tab1_senior_citizen;
	
	@FXML
	private RadioButton tab2_senior_citizen;
	
	@FXML
	public void tabOneHandeler(ActionEvent event) throws Exception
	{
		from=tab1_fromcombobox.getValue();
		to=tab1_tocombobox.getValue();
		departure_date=tab1_departure_date.getValue();
		
		if(tab1_armed_forces.isSelected())
		{
			discount_category=tab1_armed_forces.getText();
		}
		
		if(tab1_students.isSelected())
		{
			discount_category=tab1_students.getText();
		}
		
		if(tab1_senior_citizen.isSelected())
		{
			discount_category=tab1_senior_citizen.getText();
		}
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("flights.fxml"));
		Parent root = (Parent)loader.load();
		FlightsController flight = loader.getController();
		Stage primaryStage = new Stage();
		flight.myFunc(from,to,java.sql.Date.valueOf(departure_date),discount_category);
		primaryStage.setScene(new Scene(root,850,500));
		primaryStage.show();
		
		/*Stage primaryStage = new Stage();
		Parent root=FXMLLoader.load(getClass().getResource("Flights.fxml"));
		Scene scene = new Scene(root,700,500);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();*/
	}
	
	@FXML
	public void tabTwoHandeler(ActionEvent event) throws Exception
	{
		from=tab2_fromcombobox.getValue();
		to=tab2_tocombobox.getValue();
		departure_date=tab2_departure_date.getValue();
		return_date=tab2_return_date.getValue();
		
		if(tab2_armed_forces.isSelected())
		{
			discount_category=tab2_armed_forces.getText();
		}
		
		if(tab2_students.isSelected())
		{
			discount_category=tab2_students.getText();
		}
		
		if(tab2_senior_citizen.isSelected())
		{
			discount_category=tab2_senior_citizen.getText();
		}
		
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("flights.fxml"));
		Parent root = (Parent)loader.load();
		FlightsController flight = loader.getController();
		Stage primaryStage = new Stage();
		flight.myFunc2(from,to,java.sql.Date.valueOf(departure_date),java.sql.Date.valueOf(return_date));
		primaryStage.setScene(new Scene(root,700,500));
		primaryStage.show();
		
	}
	
	@FXML
	public void signOutChange(ActionEvent event) throws Exception
	{
		Stage primaryStage = new Stage();
		Parent root=FXMLLoader.load(getClass().getResource("Login.fxml"));
		Scene scene = new Scene(root,300,400);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		tab1_fromcombobox.setItems(list);
		
		tab1_tocombobox.setItems(list);
		
		tab2_fromcombobox.setItems(list);
		
		tab2_tocombobox.setItems(list);		
		
	}
	
}
