package cyber.auto;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Unit test for simple App.
 */
public class PrinterTests
{

    @Test
    public void emptyInputNotificationTest() {
        PrinterDriver.sendToPrint("");
        Assert.assertTrue(PrinterDriver.isEmptyInputNotificationDisplayed());
    }

    @Test
    public void correctInputTest() {
        String letter = "A";
        PrinterDriver.sendToPrint(letter);
        Assert.assertTrue(PrinterDriver.isPrintingPatternDisplayed(letter));
    }

    @Test
    public void correctInputInFileTest() {
        String letter = "A";
        PrinterDriver.sendToPrint(letter);
        Assert.assertTrue(PrinterDriver.isPrintingPatternSavedToFile(letter));
    }

    @Test
    public void incorrectInputTest() {
        PrinterDriver.sendToPrint("q");
        Assert.assertTrue(PrinterDriver.hasNoOutput());
    }
}
