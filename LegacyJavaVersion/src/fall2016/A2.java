package fall2016;

/*
 * comp202 fall2016 assignment2 answer version1
 */
public class A2 {

	public static void main(String[] args) {

		// main method inputs must be:
		// your pre-tax income, your present credit card balance, the annual
		// interest rate of the
		// card (as a percent), the credit card number, and your monthly rent.

		// the argument input is in following order
		/*
		 * agrs[0] pre-tax income args[1] present credit card balance args[2]
		 * annual interest rate args[3] credit card number args[4] monthly rent
		 */

		int pretaxIncome = Integer.parseInt(args[0]);
		int presentCreditCardBalance = Integer.parseInt(args[1]);
		int annualInterestRate = Integer.parseInt(args[2]);
		long cardNumber = Long.parseLong(args[3]);
		int monthlyRent = Integer.parseInt(args[4]);

		System.out.println("Your Card Number is: " + cardNumber);
		validateCreditCard(cardNumber); // thus we soon check if the credit card
										// number is valid or not!

		// some constants for method calculateTax

		double bracket1Dollars = 10000.0;
		double bracket1Rate = 20.0;
		double bracket2Dollars = 20000.0;
		double bracket2Rate = 30.0;
		double bracket3Dollars = 45000;
		double bracket3Rate = 50.0;

		if (!validateCreditCard(cardNumber)) {
			System.out.println("Invalid Card!");

		} else {
			System.out.println("Your card is valid!");
			// monthly credit card balances
			double[] expensesByMonth = buildExpenses(monthlyRent);

			double yearlyPostTaxIncome = pretaxIncome - calculateTax(pretaxIncome, bracket1Dollars, bracket1Rate,
					bracket2Dollars, bracket2Rate, bracket3Dollars, bracket3Rate);
			double[] paymentsByMonth = buildPayments(yearlyPostTaxIncome);
			printBalance(presentCreditCardBalance, annualInterestRate, expensesByMonth, paymentsByMonth);

			// monthly savings and cumulative savings
			// MonthlySavings = Amount Earned after taxes - Expenses for that
			// month
			double cumulativeSavings = 0.0;
			double[] savingsByMonth = new double[12];

			for (int i = 0; i < savingsByMonth.length; i++) {
				savingsByMonth[i] = monthlySavings(expensesByMonth[i], yearlyPostTaxIncome / 12);
			}

			for (int i = 0; i < savingsByMonth.length; i++) {
				System.out.println("Month " + (i + 1) + " saves " + savingsByMonth[i]);
				cumulativeSavings = cumulativeSavings + savingsByMonth[i];
			}
			System.out.println("Year cumulative savings is " + cumulativeSavings);

		}
	}

	// QUESTION1

	public static double calculateTax(double yearlyIncome, double bracket1Dollars, double bracket1Rate,
			double bracket2Dollars, double bracket2Rate, double bracket3Dollars, double bracket3Rate) {

		double totalTax = 0.0;

		if (yearlyIncome < bracket1Dollars) {
			totalTax = 0;
			return totalTax;
		}

		// example: 10,000 - 20,000
		if (yearlyIncome > bracket1Dollars && yearlyIncome < bracket2Dollars) {
			totalTax = bracket1Rate / 100 * (yearlyIncome - bracket1Dollars);

		}
		// example: 20,000 - 45,000
		else if (yearlyIncome > bracket2Dollars && yearlyIncome < bracket3Dollars) {
			// a+=b is same as a=a+b

			totalTax += bracket1Rate / 100 * (bracket2Dollars - bracket1Dollars); // *
			totalTax += bracket2Rate / 100 * (yearlyIncome - bracket2Dollars); // **

			// Thus, ** would be, assuming our yearly income was 35,000,
			// (35000-20000) * 20%
			// Meanwhile, from 10,000 to 20,000 will be taxed according to the
			// first totalTax line *
			// Thus, as the code goes, * and ** gets accumulated automatically
			// by +=

		}

		else if (yearlyIncome > bracket3Dollars) {
			totalTax += bracket1Rate / 100 * (bracket2Dollars - bracket1Dollars);
			totalTax += bracket2Rate / 100 * (bracket3Dollars - bracket2Dollars);
			totalTax += bracket3Rate / 100 * (yearlyIncome - bracket3Dollars);
		}
		return totalTax;
	}

	// ---------------------------------------------------------------------------

	// QUESTION 2

	public static double monthlySavings(double monthlyExpenses, double monthlyIncome) {
		double result = (monthlyIncome - monthlyExpenses);
		return result;

	}

	// ----------------------------------------------------------------------------

	// QUESTION 3

	public static boolean validateCreditCard(long cardNumber) {
		long oddSum = 0;
		long evenSum = 0;
		int counter = 1; // finds the even odd position of the digits in the
							// card number

		while (cardNumber > 0) {

			// check if the counter is odd
			if ((counter % 2) == 0) { // counter is EVEN here

				// this should be even but the question in the assignment has
				// typo so...
				// RECHECK: Counter starts from 0 but technically, the digits of
				// the positions of the numbers start from 1 so when
				// counter is even, the position of the number in the card will
				// actually be odd********

				oddSum = oddSum + cardNumber % 10; // cardNumber % 10 being the
													// last digit in the card
													// number
			} else {
				evenSum = evenSum + cardNumber % 10 * 2 % 9;
			}
			cardNumber = cardNumber / 10; // now the card number gets shortened,
											// and excludes the last digit
			counter++;
		}

		System.out.println("oddSum: " + oddSum);
		System.out.println("evenSum: " + evenSum);

		if ((oddSum + evenSum) % 10 == 0) {

			System.out.println("The card number is valid");
			return true;
		}

		else {
			System.out.println("The card number is invalid");
			return false;
		}
	}
	// what should I return???!!! is true||false ok?

	// --------------------------------------------------------------------------

	// QUESTION 4
	public static double[] buildExpenses(double monthlyRent) { // we input the
																// monthly Rent
		double[] month = new double[12]; // set the size of array to 12
		for (int i = 0; i < month.length; i++) {
			month[i] = 600.0 + monthlyRent; // you spend 600bucks every month +
											// input Rent

			if (i == 0 || i == 5) {
				month[i] = month[i] + 200;
			}
			if (i == 8) {
				month[i] = month[i] + 300;
			}
			if (i == 8 || i == 3 || i == 6) {
				month[i] = month[i] + 100;
			}
			if (i == 11) {
				month[i] = month[i] + 200;
			}
		}
		return month;
	}

	// ----------------------------------------------------------------------------

	// QUESTION 5

	public static double[] buildPayments(double yearlyPostTaxIncome) { // thus
																		// our
																		// input
																		// is
																		// yearly
																		// post
																		// tax
																		// income
		double[] monthlyccp = new double[12]; // ccp= credit card payments
		for (int i = 0; i < monthlyccp.length; i++) {
			if (i == 8) {
				monthlyccp[i] = 200; // just write like this beccause of
										// "entire" in the question
			} else {
				monthlyccp[i] = 0.1 * yearlyPostTaxIncome;
				if (i == 11) {
					monthlyccp[i] = monthlyccp[i] + 150;
				}
			}
		}
		return monthlyccp;
	}

	// ------------------------------------------------------------------------

	// QUESTION 6

	public static void printBalance(double ccb, double yearlyInterestRate, double[] expensesByMonth,
			double[] payments) { // both expenses by month and payments are
									// Arrays!!!
		double[] balance = new double[12];
		for (int i = 0; i < balance.length; i++) {
			// check if current month is Jan
			if (i == 0) {
				balance[i] = ccb; // we weren't told what initial balance
									// is...maybe will come up later!
			} else {
				balance[i] = balance[i - 1];
				// because we have to start the balance of that month with what
				// we got from previous month
				// For instance, if our ccb is $50 in January and wasn't paid,
				// that's what we start with for Feb.
			}

			balance[i] = balance[i] + expensesByMonth[i]; // remeember , credit
															// card balance is
															// how much you OWE!

			balance[i] = balance[i] - payments[i];
			if (balance[i] > 0) {
				balance[i] = balance[i] * (1 + (yearlyInterestRate / 100 / 12));
				// since interest rate is given in percentage,divide it by 100.
				// Also written in the instruction!
			}
		}

		for (int i = 0; i < balance.length; i++) {
			System.out.println("Month " + (i + 1) + "balance " + balance[i]);
		}

	}

}