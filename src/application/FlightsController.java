package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class FlightsController implements Initializable{

	private String from_airport;
	
	private String to_airport;
	
	private String departure_date;
	
	private String return_date;
	
	private Date dd;
	
	private double amount;
	
	private String discount_category;
	
	@FXML
	private Label price;
	
	@FXML
	private TextField name;
	
	@FXML
	private TableView<Flights> table;
	
	@FXML
	private TableColumn<Flights,Integer> Slno;
	
	@FXML
	private TableColumn<Flights,String> From;
	
	@FXML
	private TableColumn<Flights,String> To;
	
	@FXML
	private TableColumn<Flights,String> Departure_Date;
	
	@FXML
	private TableColumn<Flights,String> Arrival_Date;
	
	@FXML
	private TableColumn<Flights,String> Departure_Time;
	
	@FXML
	private TableColumn<Flights,String> Arrival_Time;
	
	@FXML
	private TableColumn<Flights,Double> Price;
	
	@FXML
	private TextField slno_choice;
	
	public ObservableList<Flights> list=FXCollections.observableArrayList();
	
	public void myFunc(String orig_airport,String dest_airport,Date ddate,String discount_category)
	{
		DateFormat df = new SimpleDateFormat("yyyy-mm-dd ");  
		from_airport=orig_airport;
		to_airport=dest_airport;
		dd=ddate;
		departure_date=df.format(ddate);
		System.out.println(departure_date);
		this.discount_category=discount_category;
	}
	
	public void myFunc2(String orig_airport,String dest_airport,Date ddate,Date rdate)
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd ");  
		from_airport=orig_airport;
		to_airport=dest_airport;
		departure_date=dateFormat.format(ddate);
		return_date=dateFormat.format(rdate);
	}
	
	
	@FXML
	public void fillTable(ActionEvent event)
	{	
		try 
		{
			int count=1;
			ResultSet rs;
			Class.forName("oracle.jdbc.driver.OracleDriver");
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl2","scott","root");
			PreparedStatement stmt=con.prepareStatement("select r.orig_airport,r.dest_airport,s.btime,s.atime,s.price from routes r , fl_schedule s, flight f  where r.routeId=f.rid and f.fl_no=s.flno and r.orig_airport=? and r.dest_airport=?");
			stmt.setString(1,from_airport);
			stmt.setString(2,to_airport);
			
			rs=stmt.executeQuery();
			
			while(rs.next())
			{
				list.add(new Flights(count,from_airport,to_airport,departure_date,departure_date,rs.getString(3),rs.getString(4),rs.getDouble(5)));
				System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+" "+rs.getDouble(5));
				count++;
			}
			
			rs.close();			
			con.close();
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Slno.setCellValueFactory(new PropertyValueFactory<Flights,Integer>("slno"));
		From.setCellValueFactory(new PropertyValueFactory<Flights,String>("orig_airport"));
		To.setCellValueFactory(new PropertyValueFactory<Flights,String>("dest_airport"));
		Departure_Date.setCellValueFactory(new PropertyValueFactory<Flights,String>("departure_date"));
		Arrival_Date.setCellValueFactory(new PropertyValueFactory<Flights,String>("arrival_date"));
		Departure_Time.setCellValueFactory(new PropertyValueFactory<Flights,String>("departure_time"));
		Arrival_Time.setCellValueFactory(new PropertyValueFactory<Flights,String>("arrival_time"));
		Price.setCellValueFactory(new PropertyValueFactory<Flights,Double>("price"));
		table.setItems(list);
	}
	
	@FXML
	public void ticketGenerator(ActionEvent event) throws IOException
	{
		int  choice=Integer.parseInt(slno_choice.getText());
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("ticket.fxml"));
		Parent root = (Parent)loader.load();
		TicketController ticket = loader.getController();
		Stage primaryStage = new Stage();
		ticket.myFunc3(choice,from_airport,to_airport,dd,name.getText(),discount_category);
		primaryStage.setScene(new Scene(root,700,350));
		primaryStage.show();
	}
	
}
