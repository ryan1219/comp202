package fall2015;

import java.util.Scanner;

/*
 * COMP-202A - Foundations of Computing (Fall 2015) - All Sections	Midterm Examination
 * Long Answer Questions unofficial solution
 */
public class Hypotenuse {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		double base, height;
		System.out.println("Input the base:");
		base = sc.nextDouble();
		System.out.println("Input the height:");
		height = sc.nextDouble();
		System.out.println("The hypotenuse is " + squareRoot((power(base, 2) + power(height, 2))));
		sc.close();
	}

	public static double absVal(double number) {
		if (number < 0) {
			number = number * -1;
		}
		return number;
	}

	public static double power(double x, int y) {
		if (y == 0) {
			return 1;
		}
		// using recursion, brute force method
		// such as looping through and accumulate answer is also good
		return (x * power(x, y - 1));
	}

	public static double squareRoot(double y) {
		double error = 1e-1;
		double s0 = y / 2;
		double s1 = (s0 + y / s0) / 2;

		while (absVal(s1 - s0) > error) {
			s0 = s1;
			s1 = (s1 + y / s1) / 2;
		}

		return s1;
	}
}
