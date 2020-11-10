package todoapplication;

import static org.junit.Assert.*;

import java.util.HashMap;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import todoapplication.model.FileErrorException;
import todoapplication.model.IDNotFoundException;
import todoapplication.model.Processor;
import todoapplication.view.NothingToDisplayException;

public class ControllerTest {
  HashMap<String, String> testCmdLineData;

  @Before
  public void setUp() throws Exception {
    testCmdLineData = new HashMap<>();
    testCmdLineData.put("--complete-todo", null);
    testCmdLineData.put("--display", "Given");
    testCmdLineData.put("--show-incomplete", "Given");
    testCmdLineData.put("--show-category", "Homework");
    testCmdLineData.put("--sort-by-date", null);
    testCmdLineData.put("--sort-by-priority", null);
    testCmdLineData.put("--add-todo", null);
    testCmdLineData.put("--completed", null);
    testCmdLineData.put("--priority", null);
    testCmdLineData.put("--category", null);
    testCmdLineData.put("--csv-file", "test_csv.txt");
  }

  @Test
  public void run() throws FileErrorException, IDNotFoundException, NothingToDisplayException {
    Controller.getController().run(testCmdLineData);
    TestCase.assertEquals(1, Processor.getProcessor().getToDosToWorkWith().size());
  }
}