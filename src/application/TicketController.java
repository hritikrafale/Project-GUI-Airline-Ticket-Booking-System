package application;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class TicketController {
	
	private int slno;
	private double amount;
	private String from;
	private String to;
	private Date departure_date;
	private String name;
	private String discount_category;
	
	@FXML
	private Label price;
	
	@FXML
	private Label name_1;
	
	@FXML
	private Label from_1;
	
	@FXML
	private Label to_1;
	
	@FXML
	private Label flightno_1;
	
	@FXML
	private Label departure_time_1;
	
	@FXML
	private Label boarding_time_1;
	
	@FXML
	private Label date_1;
	
	@FXML
	private Label name_2;
	
	@FXML
	private Label from_2;
	
	@FXML
	private Label to_2;
	
	@FXML
	private Label flightno_2;
	
	@FXML
	private Label departure_time_2;
	
	@FXML
	private Label boarding_time_2;
	
	@FXML
	private Label date_2;
	
	public void myFunc3(int slno,String from , String to,Date ddate,String name,String discount_category)
	{
		this.slno=slno;
		this.from=from;
		this.to=to;
		departure_date=ddate;
		this.name=name;
		this.discount_category=discount_category;
	}
	
	@FXML
	public void generateTicket(ActionEvent event)
	{
		name_1.setText(name);
		name_2.setText(name);
		from_1.setText(from);
		from_2.setText(from);
		to_1.setText(to);
		to_2.setText(to);
		
		DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
		
		String d =df.format(departure_date);
		date_1.setText(d);
		date_2.setText(d);
		
		try 
		{
			int count=1;
			ResultSet rs;
			Class.forName("oracle.jdbc.driver.OracleDriver");
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl2","scott","root");
			PreparedStatement stmt=con.prepareStatement("select s.btime,s.atime,f.fl_no,s.price from routes r , fl_schedule s, flight f  where r.routeId=f.rid and f.fl_no=s.flno and r.orig_airport=? and r.dest_airport=?");
			stmt.setString(1,from);
			stmt.setString(2,to);
			
			rs=stmt.executeQuery();
			
			while(rs.next())
			{
				if(count==slno)
				{
					boarding_time_1.setText(rs.getString(2));
					boarding_time_2.setText(rs.getString(2));
					departure_time_1.setText(rs.getString(1));
					departure_time_2.setText(rs.getString(1));
					flightno_1.setText(rs.getString(3));
					flightno_2.setText(rs.getString(3));
					amount=Double.parseDouble(rs.getString(4));
				}
				count++;
			}
			
			if(discount_category.equals("Armed Forces"))
				amount=amount-0.25*amount;
			
			if(discount_category.equals("Senior Citizen"))
				amount=amount-0.15*amount;
			
			if(discount_category.equals("Students"))
				amount=amount-0.05*amount;
			
			price.setText(Double.toString(amount));
			
			rs.close();			
			con.close();
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	
	

}
