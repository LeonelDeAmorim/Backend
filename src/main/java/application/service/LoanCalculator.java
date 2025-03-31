package application.service;
import org.springframework.stereotype.Service;

@Service
public class LoanCalculator {
	
	public double CalculateLoan( double vehiclePrice, double downPayment, double interestRate, int duration) 
	{
		double estimateMonthlyPayment =0.0;
		double debt = vehiclePrice- downPayment;
		double rate = (interestRate / 100) / 12;
		
		if(rate>0) {
			estimateMonthlyPayment = (debt * rate) / (1 - Math.pow(1 + rate, -duration));
		}
		else {
			 estimateMonthlyPayment = debt / duration;

		}		
		
		 estimateMonthlyPayment = Math.round(estimateMonthlyPayment * 100) / 100.0;
		
		return estimateMonthlyPayment;
	}

}
