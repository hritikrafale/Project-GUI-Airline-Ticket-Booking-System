package application;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Flights
{
	private final SimpleIntegerProperty slno;
	private final SimpleStringProperty orig_airport;
	private final SimpleStringProperty dest_airport;
	private final SimpleStringProperty departure_date;
	private final SimpleStringProperty arrival_date;
	private final SimpleStringProperty departure_time;
	private final SimpleStringProperty arrival_time;
	private final SimpleDoubleProperty price;
	
	
	public Flights(int slno,String orig_airport, String dest_airport,String departure_date,String arrival_date ,String departure_time, String arrival_time, double price) {
		super();
		this.slno=new SimpleIntegerProperty(slno);
		this.orig_airport =new SimpleStringProperty(orig_airport);
		this.dest_airport = new SimpleStringProperty(dest_airport);
		this.departure_date=new SimpleStringProperty(departure_date);
		this.arrival_date=new SimpleStringProperty(arrival_date);
		this.departure_time =new SimpleStringProperty(departure_time);
		this.arrival_time =new SimpleStringProperty(arrival_time);
		this.price =new SimpleDoubleProperty(price);
	}
	
	public int getSlno()
	{
		return slno.get();
	}
	
	public String getOrig_airport() {
		return orig_airport.get();
	}
	
	public String getDest_airport() {
		return dest_airport.get();
	}
	
	public String getDeparture_date()
	{
		return departure_date.get();
	}
	
	public String getArrival_date()
	{
		return arrival_date.get();
	}
	

	public String getDeparture_time() {
		return departure_time.get();
	}

	public String getArrival_time() {
		return arrival_time.get();
	}

	public double getPrice() {
		return price.get();
	}

}
