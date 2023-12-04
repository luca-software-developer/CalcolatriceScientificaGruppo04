package it.unisa.diem.ids2023.rpnpsc;

import it.unisa.diem.ids2023.rpnpsc.exceptions.InvalidFormatException;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * La classe {@code Complex} rappresenta un numero complesso espresso in
 * notazione cartesiana nella forma a ± bj. Essa definisce le operazioni di
 * somma, sottrazione, moltiplicazione, divisione, inversione del segno e radice
 * quadrata sui numeri complessi. Fornisce, inoltre, i metodi per convertire una
 * stringa che rappresenta un numero complesso, se correttamente formattata, in
 * un oggetto {@code Complex} e per convertire un oggetto {@code Complex} in una
 * rappresentazione in forma di stringa per la visualizzazione.
 */
public class Complex {

    /**
     * Rappresenta la parte reale del numero complesso. Viene utilizzato il tipo
     * primitivo double di Java così da soddisfare la specifica FC-1.3
     * (rappresentazione dei valori in virgola mobile).
     */
    private double realPart;

    /**
     * Rappresenta la parte immaginaria del numero complesso. Viene utilizzato
     * il tipo primitivo double di Java così da soddisfare la specifica FC-1.3
     * (rappresentazione dei valori in virgola mobile).
     */
    private double imaginaryPart;

    /**
     * Costruttore della classe {@code Complex}. Utilizzare questo costruttore
     * quando si desidera istanziare un numero complesso a parte immaginaria
     * nulla, ovvero un numero reale.
     *
     * @param realPart Parte reale del numero complesso.
     */
    public Complex(double realPart) {
        this(realPart, 0);
    }

    /**
     * Costruttore principale della classe {@code Complex}.
     *
     * @param realPart Parte reale del numero complesso.
     * @param imaginaryPart Parte immaginaria del numero complesso.
     */
    public Complex(double realPart, double imaginaryPart) {
        this.realPart = realPart;
        this.imaginaryPart = imaginaryPart;
    }

    /**
     * Restituisce la parte reale del numero complesso.
     *
     * @return Restituisce la parte reale del numero complesso.
     */
    public double getRealPart() {
        return realPart;
    }

    /**
     * Imposta la parte reale del numero complesso.
     *
     * @param realPart Parte reale del numero complesso.
     */
    public void setRealPart(double realPart) {
        this.realPart = realPart;
    }

    /**
     * Restituisce la parte immaginaria del numero complesso.
     *
     * @return Restituisce la parte immaginaria del numero complesso.
     */
    public double getImaginaryPart() {
        return imaginaryPart;
    }

    /**
     * Imposta la parte immaginaria del numero complesso.
     *
     * @param imaginaryPart Parte immaginaria del numero complesso.
     */
    public void setImaginaryPart(double imaginaryPart) {
        this.imaginaryPart = imaginaryPart;
    }

    /**
     * Effettua il parsing di una sequenza di caratteri che rappresenta un
     * numero reale o complesso.
     *
     * @param text Sequenza di caratteri che rappresenta un numero reale o
     * complesso.
     * @return Numero complesso corrispondente alla rappresentazione fornita.
     * @throws InvalidFormatException Se il formato specificato non è valido.
     */
    public static Complex parse(CharSequence text) throws InvalidFormatException {
        String element = text.toString().replaceAll("\\s", "");
        Pattern realPattern = Pattern.compile("^([+-]?\\d+(\\.\\d+)?)$");
        Matcher realMatcher = realPattern.matcher(element);
        if (realMatcher.find()) {
            return new Complex(Double.parseDouble(element));
        }
        if (element.equals("+j") || element.equals("j")) {
            return new Complex(0, 1);
        }
        if (element.equals("-j")) {
            return new Complex(0, -1);
        }
        Pattern imaginaryPattern = Pattern.compile("^([+-]?\\d+(\\.\\d+)?)j$");
        Matcher imaginaryMatcher = imaginaryPattern.matcher(element);
        if (imaginaryMatcher.find()) {
            return new Complex(0, Double.parseDouble(imaginaryMatcher.group(1)));
        }
        element = element.replaceAll("([+-])j$", "$11j");
        Pattern complexPattern = Pattern.compile("^(?<real>[+-]?\\d+(\\.\\d+)?)(?<imaginary>[+-]\\d+(\\.\\d+)?)j$");
        Matcher complexMatcher = complexPattern.matcher(element);
        if (complexMatcher.find()) {
            String realString = complexMatcher.group("real");
            String imaginaryString = complexMatcher.group("imaginary");
            return new Complex(Double.parseDouble(realString), Double.parseDouble(imaginaryString));
        } else {
            throw new InvalidFormatException("Formato non valido!", "L'elemento \"" + text + "\" non corrisponde ad un operando reale o complesso.");
        }
    }

    /**
     * Restituisce il risultato della somma tra il numero complesso corrente ed
     * il numero reale o complesso specificato come parametro.
     *
     * @param other Numero reale o complesso.
     * @return Restituisce il risultato della somma tra il numero complesso
     * corrente ed il numero reale o complesso specificato come parametro.
     */
    public Complex add(Complex other) {
        double a = getRealPart(), b = getImaginaryPart();
        double c = other.getRealPart(), d = other.getImaginaryPart();
        return new Complex(a + c, b + d);
    }

    /**
     * Restituisce il risultato della sottrazione tra il numero complesso
     * corrente ed il numero reale o complesso specificato come parametro.
     *
     * @param other Numero reale o complesso.
     * @return Restituisce il risultato della sottrazione tra il numero
     * complesso corrente ed il numero reale o complesso specificato come
     * parametro.
     */
    public Complex subtract(Complex other) {
        double a = getRealPart(), b = getImaginaryPart();
        double c = other.getRealPart(), d = other.getImaginaryPart();
        return new Complex(a - c, b - d);
    }

    /**
     * Restituisce il risultato della moltiplicazione tra il numero complesso
     * corrente ed il numero reale o complesso specificato come parametro.
     *
     * @param other Numero reale o complesso.
     * @return Restituisce il risultato della moltiplicazione tra il numero
     * complesso corrente ed il numero reale o complesso specificato come
     * parametro.
     */
    public Complex multiply(Complex other) {
        double a = getRealPart(), b = getImaginaryPart();
        double c = other.getRealPart(), d = other.getImaginaryPart();
        return new Complex(a * c - b * d, a * d + b * c);
    }

    /**
     * Restituisce il risultato della divisione tra il numero complesso corrente
     * ed il numero reale o complesso specificato come parametro.
     *
     * @param other Numero reale o complesso.
     * @return Restituisce il risultato della divisione tra il numero complesso
     * corrente ed il numero reale o complesso specificato come parametro.
     * @throws ArithmeticException Se viene specificato un divisore nullo.
     */
    public Complex divide(Complex other) {
        double a = getRealPart(), b = getImaginaryPart();
        double c = other.getRealPart(), d = other.getImaginaryPart();
        double denominator = Math.pow(c, 2) + Math.pow(d, 2);
        if (denominator == 0) {
            throw new ArithmeticException("Non è possibile dividere per zero.");
        }
        return new Complex((a * c + b * d) / denominator, (b * c - a * d) / denominator);
    }

    /**
     * Restituisce l'opposto del numero complesso corrente.
     *
     * @return Restituisce l'opposto del numero complesso corrente.
     */
    public Complex getOpposite() {
        return new Complex(-getRealPart(), -getImaginaryPart());
    }

    /**
     * Restituisce la radice quadrata del numero complesso corrente. Per
     * semplicità viene restituita solo la radice che si ottiene per k = 0.
     *
     * @return Restituisce la radice quadrata del numero complesso corrente.
     */
    public Complex getSquareRoot() {
        double a = getRealPart(), b = getImaginaryPart();
        if (a >= 0 && b == 0) {
            return new Complex(Math.sqrt(a), 0);
        }
        double magnitude = Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
        double argument;
        if (a == 0) {
            if (b > 0) {
                argument = Math.PI / 2;
            } else {
                argument = 3 * Math.PI / 2;
            }
        } else if (a > 0) {
            if (b >= 0) {
                argument = Math.atan(b / a);
            } else {
                argument = Math.atan(b / a) + 2 * Math.PI;
            }
        } else {
            argument = Math.atan(b / a) + Math.PI;
        }
        double magnitudeSqrt = Math.sqrt(magnitude);
        double realSqrt = magnitudeSqrt * Math.cos((argument) / 2);
        double imaginarySqrt = magnitudeSqrt * Math.sin((argument) / 2);
        return new Complex(realSqrt, imaginarySqrt);
    }

    /**
     * Restituisce la rappresentazione del numero complesso corrente in forma di
     * stringa, usando la notazione cartesiana ed effettuando semplificazioni
     * quando possibile.
     *
     * @return Restituisce la rappresentazione del numero complesso corrente in
     * forma di stringa.
     */
    @Override
    public String toString() {
        double a = getRealPart(), b = getImaginaryPart();
        if (b == 0) {
            String realString = String.format(Locale.US, "%.15f", a);
            realString = realString.replaceAll("[0]*$", "").replaceAll("\\.$", "");
            return realString;
        } else {
            String realString = String.format(Locale.US, "%.15f", a);
            String imaginaryString = String.format(Locale.US, "%+.15f", b);
            realString = realString.replaceAll("[0]*$", "").replaceAll("\\.$", "");
            realString = realString.replaceAll("^0$", "");
            realString = realString.replaceAll("^-0$", "");
            imaginaryString = imaginaryString.replaceAll("[0]*$", "").replaceAll("\\.$", "");
            imaginaryString = imaginaryString.replaceAll("[+]1$", a == 0 ? "" : "+");
            imaginaryString = imaginaryString.replaceAll("[-]1$", "-");
            if (realString.isEmpty() && b > 0) {
                imaginaryString = imaginaryString.substring(1);
            }
            return String.format("%s%sj", realString, imaginaryString);
        }
    }

}
