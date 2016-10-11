import org.junit.*;
import static org.junit.Assert.assertEquals;

public class CommandLineTest {

    @Test
    public void listTest() {

        CommandLine cm = new CommandLine();

        String expectedString = "untitled folder";

        assertEquals(expectedString, cm.listFolders());

    }

}
