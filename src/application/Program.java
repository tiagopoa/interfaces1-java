package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import model.entities.CarRental;
import model.entities.Vehicle;
import model.services.BrazilTaxService;
import model.services.RentalService;

public class Program {

	public static void main(String[] args) throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:ss");

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		System.out.println("Enter rental data: ");
		System.out.print("Car model: ");
		String carModel = sc.nextLine();		
		System.out.print("Pickup (dd/MM/yyyy hh:ss): ");		
		Date start = sdf.parse(sc.nextLine());		
		System.out.print("Return (dd/MM/yyyy hh:ss): ");
		Date finish = sdf.parse(sc.nextLine());		
		System.out.print("Enter price per hour: ");
		Double pricePerHour = sc.nextDouble();
		System.out.print("Enter price per day: ");
		Double pricePerDay = sc.nextDouble();
		
		CarRental carRental = new CarRental(start,finish,new Vehicle(carModel));

		RentalService rentalService = new RentalService(pricePerDay, pricePerHour, new BrazilTaxService());	
		
		rentalService.processInvoice(carRental);
		
		System.out.println();
		System.out.println("INVOICE: ");
		System.out.println("Basic payment: " + carRental.getInvoice().getBasicPayment());
		System.out.println("Tax: " + carRental.getInvoice().getTax());
		System.out.println("Total payment: " + carRental.getInvoice().getTotalPayment());
		
		sc.close();
	}

}
