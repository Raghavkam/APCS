// Name:J6-16-23
// Date: 03-22-23

import java.util.*;
import java.text.DecimalFormat;

interface PolynomialInterface {

	public void makeTerm(Integer exp, Integer coef);

	public Map<Integer, Integer> getMap();

	public double evaluateAt(double x);

	//precondition: both polynomials are in standard form
	//postcondition: terms with zero disappear. If all terms disappear (the size is zero), 
	//               add pair (0,0).
	public Polynomial add(Polynomial other);

	//precondition: both polynomials are in standard form
	//postcondition: terms with zero disappear. If all terms disappear (the size is zero), 
	//               add pair (0,0)
	public Polynomial multiply(Polynomial other);

	public String toString();
}


class Polynomial implements PolynomialInterface {
	Map<Integer, Integer> poly;

	public Polynomial() {
		poly = new TreeMap<Integer, Integer>();
	}

	public Polynomial(String exp) { // processes ("2x^3 + 4x^2 + 6x^1 + -3")
		poly = new TreeMap<Integer, Integer>();
		String[] parts = exp.split(" +");

		for (String term: parts) {
			if (!term.contains("+")) {
				int xloc = term.indexOf("x");

				if (xloc == -1) {
					this.makeTerm(0, Integer.valueOf(term.trim()));
				} 
            else {
					this.makeTerm(Integer.valueOf(term.substring(xloc + 2, xloc + 3)), Integer.valueOf(term.substring(xloc - 1, xloc)));
				}
			}
		}
	}

	public Map<Integer, Integer> getMap() {
		return poly;
	}

	public Polynomial add(Polynomial other) {
		Polynomial newP = new Polynomial();

		for (Integer i: poly.keySet()) {
			newP.makeTerm(i, poly.get(i));
		}

		for (Integer i: other.getMap().keySet()) {
			if (!newP.getMap().containsKey(i)) {
				newP.makeTerm(i, other.getMap().get(i));
			} 
         else {
				newP.makeTerm(i, newP.getMap().remove(i) + other.getMap().get(i));
			}
		}

		return newP;
	}

	public Polynomial multiply(Polynomial other) {
		Polynomial newP = new Polynomial();

		for (Integer i: other.getMap().keySet()) {
			for (Integer k: poly.keySet()) {
				if (!newP.getMap().containsKey(i + k)) {
					newP.makeTerm(i + k, (poly.get(k) * other.getMap().get(i)));
				} 
            else {
					newP.makeTerm(i + k, newP.getMap().remove(i + k) + (poly.get(k) * other.getMap().get(i)));
				}
			}
		}

		return newP;
	}

	public double evaluateAt(double x) {

		double eval = 0.0;

		for (Integer i: poly.keySet()) {
			eval += Math.pow(x, i) * poly.get(i);
		}

		return (eval);
	}

	public String toString() {
		if (poly.size() == 0) {
			return "0";
		}

		String equation = "";

		for (int i = 10; i >= 0; i--) {
			if (poly.containsKey((Integer) i)) {
				if (poly.get(i) == -1 && i != 0) { //if negative and has x
					equation += "-";
				} 
            else if (poly.get(i) != 1 && i != 0) { //if positive and has x 
					equation += (poly.get((Integer) i));
				} 
            else if (i == 0 && poly.get(i) != 0) { //if constant and not 0
					equation += (poly.get((Integer) i));
				}

				if (i != 0) {
					equation += "x";
				}

				if (i > 1) {
					equation += "^" + i;
				}

				equation += " + ";
			}
		}

		return equation.substring(0, equation.length() - 3);
	}

	public void makeTerm(Integer deg, Integer coef) {
		if (coef != 0) {
			poly.put(deg, coef);
		}
	}
}