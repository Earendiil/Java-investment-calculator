package calculator;

public class InvestmentCalculator {
    public static void main(String[] args) {
        double startingInvestment = 5000; 
        double monthlyInvestment = 2000; 
        double annualRate = 0.10; 
        double monthlyRate = annualRate / 12; 
        double retirementTarget = 200000;  

        
        int monthsToReachGoal = calculateMonthsToReachGoal(startingInvestment, monthlyInvestment, monthlyRate, retirementTarget);

        // Convert months to years and months
        int years = monthsToReachGoal / 12;
        int remainingMonths = monthsToReachGoal % 12;

        System.out.printf("You will reach your retirement goal of €%.2f in %d years and %d months.%n", retirementTarget, years, remainingMonths);

        int[] yearsArray = {2, 5, 8, 10,  15, 20, 25}; // Investment durations in years
        System.out.printf("%-10s %-20s %-20s %-20s %-20s%n", "Years", "Future Value (€)", "Total Invested (€)", "Starting Capital (€)", "Growth (€)");
        for (int year : yearsArray) {
            int n = year * 12; 
            double futureValue = calculateFutureValue(startingInvestment, monthlyInvestment, monthlyRate, n);
            
            double totalInvested = startingInvestment + (monthlyInvestment * n); // Total amount invested including starting capital
            double growth = futureValue - totalInvested;
 
            System.out.printf("%-10d %-20.2f %-20.2f %-20.2f %-20.2f%n", year, futureValue, totalInvested, startingInvestment, growth);
        }
    }

    // Method to calculate the time it takes to reach the retirement goal
    private static int calculateMonthsToReachGoal(double startingInvestment, double monthlyInvestment, double monthlyRate, double retirementGoal) {
        double balance = startingInvestment;
        int months = 0;

        // Keep adding monthly investment and applying interest until balance reaches goal
        while (balance < retirementGoal) {
            balance += monthlyInvestment;  
            balance *= (1 + monthlyRate);  
            months++;
        }

        return months;
    }

    // Future Value formula for an annuity with initial capital
    private static double calculateFutureValue(double startingInvestment, double monthlyInvestment, double monthlyRate, int n) {
        return startingInvestment * Math.pow(1 + monthlyRate, n) +
               monthlyInvestment * ((Math.pow(1 + monthlyRate, n) - 1) / monthlyRate) * (1 + monthlyRate);
    }
}
