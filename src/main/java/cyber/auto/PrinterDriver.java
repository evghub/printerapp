package cyber.auto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class PrinterDriver {

    private static Runtime rt;
    private static Process proc;

    public static void sendToPrint(String s) {

        try{
            rt = Runtime.getRuntime();
            proc = rt.exec("java -cp C:/Dev  Printer " + s);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private static List<String> readPrinterOutputFromConsole(InputStream inStream) {

        List<String> outputAsStringList = new LinkedList<String>();
        try {
            InputStreamReader isr = new InputStreamReader(inStream);
            BufferedReader br = new BufferedReader(isr);
            String line = null;
            while ( (line = br.readLine()) != null)
                outputAsStringList.add(line);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return outputAsStringList;
    }

    private static List<String> readPrinterOutputFromFile() {
        List<String> outputAsStringList = new LinkedList<String>();
        BufferedReader br = null;
        FileReader fr = null;

        try {
            fr = new FileReader("C:/Dev/printer.txt");
            br = new BufferedReader(new FileReader("C:/Dev/printer.txt"));

            String line;

            while ((line = br.readLine()) != null) {
                outputAsStringList.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)
                    br.close();

                if (fr != null)
                    fr.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return outputAsStringList;
    }

    public static Boolean isEmptyInputNotificationDisplayed() {
        List<String> listOfStrings = readPrinterOutputFromConsole(proc.getInputStream());
        System.out.println(listOfStrings.toString());
        return PrintingPatterns.EMPTY_INPUT_NOTIFICATION.equals(listOfStrings.toString());
    }

    public static Boolean isPrintingPatternDisplayed(String letter) {
        List<String> listOfStrings = readPrinterOutputFromConsole(proc.getInputStream());
        switch (letter) {
            case "A": return PrintingPatterns.UPPERCASE_A_PATTERN.equals(listOfStrings.toString());
            case "B": return PrintingPatterns.UPPERCASE_B_PATTERN.equals(listOfStrings.toString());
            case "C": return PrintingPatterns.UPPERCASE_C_PATTERN.equals(listOfStrings.toString());
            default: return false;
        }
    }

    public static Boolean isPrintingPatternSavedToFile(String letter) {
        List<String> listOfStrings = readPrinterOutputFromConsole(proc.getInputStream());
        switch (letter) {
            case "A": return PrintingPatterns.UPPERCASE_A_PATTERN.equals(listOfStrings.toString());
            case "B": return PrintingPatterns.UPPERCASE_B_PATTERN.equals(listOfStrings.toString());
            case "C": return PrintingPatterns.UPPERCASE_C_PATTERN.equals(listOfStrings.toString());
            default: return false;
        }
    }

    public static Boolean hasNoOutput() {
        List<String> listOfStrings = readPrinterOutputFromConsole(proc.getInputStream());
        return listOfStrings.isEmpty();
    }


    public static void main(String[] args) {
        sendToPrint("A");
        System.out.println(readPrinterOutputFromConsole(proc.getInputStream()));

        sendToPrint("B");
        System.out.println(readPrinterOutputFromConsole(proc.getInputStream()));

        sendToPrint("C");
        System.out.println(readPrinterOutputFromConsole(proc.getInputStream()));

        sendToPrint("q");
        System.out.println(readPrinterOutputFromConsole(proc.getInputStream()));

        sendToPrint("");
        System.out.println(readPrinterOutputFromConsole(proc.getInputStream()));
    }
}
