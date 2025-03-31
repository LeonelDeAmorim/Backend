package application.controller;

import application.service.LoanCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class LoanController {
    @Autowired
    private LoanCalculator loanCalculator;

    // Endpoint to calculate the loan
    @GetMapping("/calculateLoan")
    public LoanResponse calculateLoan(@RequestParam("vehiclePrice") double vehiclePrice,
                                      @RequestParam("downPayment") double downPayment,
                                      @RequestParam("interestRate") double interestRate,
                                      @RequestParam("duration") int duration) {

        // Calculate monthly payment
        double monthlyPayment = loanCalculator.CalculateLoan(vehiclePrice, downPayment, interestRate, duration);
        
        // Return the result as a LoanResponse object
        return new LoanResponse(monthlyPayment);
    }

    // A simple POJO to wrap the response
    public static class LoanResponse {
        private double monthlyPayment;

        public LoanResponse(double monthlyPayment) {
            this.monthlyPayment = monthlyPayment;
        }

        public double getMonthlyPayment() {
            return monthlyPayment;
        }

        public void setMonthlyPayment(double monthlyPayment) {
            this.monthlyPayment = monthlyPayment;
        }
    }
}
