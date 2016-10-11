import org.junit.*;
import static org.junit.Assert.assertEquals;

public class CommandLineTest {

    @Test
    public void listTest() {
        CommandLine commandLine = new CommandLine();
        String expectedString = "untitled folder";

        assertEquals(expectedString, commandLine.listFolders());
    }

    @Test
    public void listTestFromInput() {
        String userInput = "/Users/per/Dev/annika/INTE/FinalProject/testFolder\"";
        String expectedString = "untitled folder";

        CommandLine commandLine = new CommandLine();

        assertEquals(commandLine.listFolders(userInput), expectedString);

    }

}
