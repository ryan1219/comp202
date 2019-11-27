package fall2016;

/*
 * comp202 fall2016 assignment2 answer version2
 */
//set the class//
public class YearlyBudget {

	public static void main(String[] args) {
		int pretaxIncome = Integer.parseInt(args[0]);
		int presentCreditCardBalance = Integer.parseInt(args[1]);
		int annualInterestRate = Integer.parseInt(args[2]);
		long creditCardNumber = Long.parseLong(args[3]);
		int monthlyRent = Integer.parseInt(args[4]);

		if (validateCreditCard(creditCardNumber)) {
			double yearlyPostTaxIncome = pretaxIncome - calculateTax(pretaxIncome, 10000, 20, 20000, 30, 45000, 50);
			double[] expense = buildExpense(monthlyRent);
			double[] payments = buildPayments(yearlyPostTaxIncome);

			printBalance(presentCreditCardBalance, annualInterestRate, expense, payments);

			double yearlySaving = 0.0;
			double[] monthlySavings = new double[12];

			// calculate monthly saving and cumulative saving
			for (int i = 0; i < monthlySavings.length; i++) {
				monthlySavings[i] = yearlyPostTaxIncome / 12 - expense[i];
				System.out.println("Monthly saving on month " + (i + 1) + " is " + monthlySavings[i]);
				yearlySaving += monthlySavings[i];
			}

			System.out.println("Cumulative saving is " + yearlySaving);

		} else {
			System.out.println("Invalid card");
		}

		// double totaltax = calculateTax(100000, 10000, 20, 20000, 30, 45000,
		// 50);
		// double monthlysaved = monthlySavings(700,800);

		// output the situation//
		// System.out.println("toal tax paid is"+(int)totaltax);
		// System.out.println("amount you saved that month is
		// "+(int)monthlysaved);
		//
		// //long creditCardNumber = 1234567812345678l;
		// System.out.println(validateCreditCard(creditCardNumber));
		//

	}

	// set the method and the arguments//
	public static double calculateTax(double yearlyIncome, double bracket1Dollars, double bracket1Rate,
			double bracket2Dollars, double bracket2Rate, double bracket3Dollars, double bracket3Rate) {

		double totaltax = 0.0;
		if (yearlyIncome <= bracket1Dollars) {
			totaltax = 0;
		}
		// set the equation for different situation//
		else if (yearlyIncome > bracket1Dollars && yearlyIncome <= bracket2Dollars) {
			totaltax = ((yearlyIncome - bracket1Dollars) * (bracket1Rate / 100));
		} else if (yearlyIncome > bracket2Dollars && yearlyIncome <= bracket3Dollars) {
			totaltax = (bracket2Dollars - bracket1Dollars) * (bracket1Rate / 100)
					+ (yearlyIncome - bracket2Dollars) * (bracket2Rate / 100);
		} else {
			totaltax = (bracket2Dollars - bracket1Dollars) * (bracket1Rate / 100)
					+ (bracket3Dollars - bracket2Dollars) * (bracket2Rate / 100)
					+ (yearlyIncome - bracket3Dollars) * (bracket3Rate / 100);
		}
		return totaltax;
	}

	// method monthly saving shows amount you saved that month/
	public static double monthlySavings(double monthlyExpense, double monthlyIncome) {
		// set the relationship of monthlysaved,monthlyincome and
		// monthlyexpense//
		double monthlysaved = monthlyIncome - monthlyExpense;
		return monthlysaved;
	}

	// question2
	public static boolean validateCreditCard(long creditCardNumber) {
		int oddSum = 0;
		int evenSum = 0;
		int index = 1;

		while (creditCardNumber > 0) {
			int currentDigit = (int) (creditCardNumber % 10);
			// odd
			if ((index % 2) != 0) {
				oddSum = oddSum + currentDigit;
			}
			// even
			else {
				evenSum = evenSum + currentDigit * 2 % 9;
			}

			index = index + 1;
			creditCardNumber = creditCardNumber / 10;
		}
		//
		System.out.println("oddSum is " + oddSum);
		System.out.println("evenSum is " + evenSum);
		//
		if ((oddSum + evenSum) % 10 == 0) {
			return true;
		} else {
			return false;
		}
	}

	// question4
	public static double[] buildExpense(double monthlyRent) {
		double[] expense = new double[12];

		for (int i = 0; i < expense.length; i++) {
			expense[i] = monthlyRent + 600;
			if (i == 0 || i == 5) {
				expense[i] = expense[i] + 200;
			}
			if (i == 8) {
				expense[i] = expense[i] + 300;
			}
			if (i == 3 || i == 6 || i == 8) {
				expense[i] = expense[i] + 100;
			}
			if (i == 11) {
				expense[i] = expense[i] + 200;
			}
		}
		return expense;
	}

	// question5
	public static double[] buildPayments(double yearlyPostTaxIncome) {
		double[] payments = new double[12];
		for (int i = 0; i < payments.length; i++) {
			payments[i] = payments[i] + yearlyPostTaxIncome * 0.1;
			if (i == 11) {
				payments[i] = payments[i] + 150;
			}
			if (i == 8) {
				payments[i] = payments[i] + 200;
			}
		}
		return payments;
	}

	// question6
	public static void printBalance(double creditCardBalance, double interest, double[] expense, double[] payments) {

		double[] results = new double[12];

		for (int i = 0; i < expense.length; i++) {

			if (i == 0) {
				results[i] = creditCardBalance;
			} else {
				results[i] = results[i - 1];
			}

			results[i] = results[i] + expense[i] - payments[i];

			// interest
			if (results[i] > 0) {
				results[i] = results[i] * (1 + interest / 100);
			}
		}

		for (int i = 0; i < results.length; i++) {
			System.out.println("The balance of month " + (i + 1) + " is " + results[i]);
		}

	}
}
