package todoapplication;

import static org.junit.Assert.*;

import java.util.HashMap;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

public class CmdLineProcessorTest {
  CmdLineProcessor testCmdLineProcessor;
  HashMap<String, String> testHashMap;

  @Before
  public void setUp() throws Exception {
    testCmdLineProcessor = new CmdLineProcessor();
    testHashMap = new HashMap<String, String>();
    testHashMap.put("--csv-file", null);
    testHashMap.put("--add-todo", null);
    testHashMap.put("--todo-text", null);
    testHashMap.put("--completed", null);
    testHashMap.put("--due", null);
    testHashMap.put("--priority", null);
    testHashMap.put("--category", null);
    testHashMap.put("--complete-todo", null);
    testHashMap.put("--display", null);
    testHashMap.put("--show-incomplete", null);
    testHashMap.put("--show-category", null);
    testHashMap.put("--sort-by-date", null);
    testHashMap.put("--sort-by-priority", null);
  }

  @Test
  public void getCommandLineData() {
    TestCase.assertEquals(testHashMap, testCmdLineProcessor.getCommandLineData());
  }

  @Test
  public void processArgumentsValidCSVFile() throws CommandLineException {
    String[] cmdLineArgs = {"--csv-file", "hello.java"};
    testHashMap = testCmdLineProcessor.processArguments(cmdLineArgs);
    TestCase.assertEquals("hello.java", testHashMap.get("--csv-file"));
  }

  @Test (expected = CommandLineException.class)
  public void processArgumentsInvalidCSVFile() throws CommandLineException {
    String[] cmdLineArgs = {"--csv-file"};
    testHashMap = testCmdLineProcessor.processArguments(cmdLineArgs);
  }

  @Test
  public void processArgumentsValidAddToDo() throws CommandLineException {
    String[] cmdLineArgs = {"--csv-file", "hello.java", "--add-todo", "--todo-text", "Finish HW"};
    testHashMap = testCmdLineProcessor.processArguments(cmdLineArgs);
    TestCase.assertEquals("Given", testHashMap.get("--add-todo"));
    TestCase.assertEquals("Finish HW", testHashMap.get("--todo-text"));
  }

  @Test (expected = CommandLineException.class)
  public void processArgumentsInvalidAddToDo() throws CommandLineException {
    String[] cmdLineArgs = {"--csv-file", "hello.java", "--add-todo"};
    testHashMap = testCmdLineProcessor.processArguments(cmdLineArgs);
  }

  @Test (expected = CommandLineException.class)
  public void processArgumentsInvalidAddToDoDescription() throws CommandLineException {
    String[] cmdLineArgs = {"--csv-file", "hello.java", "--add-todo", "--todo-text", "Finish", "HW"};
    testHashMap = testCmdLineProcessor.processArguments(cmdLineArgs);
  }

  @Test
  public void processArgumentsValidCompleted() throws CommandLineException {
    String[] cmdLineArgs = {"--csv-file", "hello.java", "--add-todo", "--todo-text", "Finish HW",
    "--completed"};
    testHashMap = testCmdLineProcessor.processArguments(cmdLineArgs);
    TestCase.assertEquals("Given", testHashMap.get("--completed"));
  }

  @Test
  public void processArgumentsValidDueDate() throws CommandLineException {
    String[] cmdLineArgs = {"--csv-file", "hello.java", "--add-todo", "--todo-text", "Finish HW",
        "--completed", "--due", "12/1/2010"};
    testHashMap = testCmdLineProcessor.processArguments(cmdLineArgs);
    TestCase.assertEquals("12/1/2010", testHashMap.get("--due"));
  }

  @Test (expected = CommandLineException.class)
  public void processArgumentsInvalidDueDate() throws CommandLineException {
    String[] cmdLineArgs = {"--csv-file", "hello.java", "--add-todo", "--todo-text", "Finish HW",
        "--completed", "--due"};
    testHashMap = testCmdLineProcessor.processArguments(cmdLineArgs);
  }

  @Test
  public void processArgumentsValidPriority() throws CommandLineException {
    String[] cmdLineArgs = {"--csv-file", "hello.java", "--add-todo", "--todo-text", "Finish HW",
        "--completed", "--due", "12/1/2010", "--priority", "1"};
    testHashMap = testCmdLineProcessor.processArguments(cmdLineArgs);
    TestCase.assertEquals("1", testHashMap.get("--priority"));
  }

  @Test (expected = CommandLineException.class)
  public void processArgumentsInvalidPriority() throws CommandLineException {
    String[] cmdLineArgs = {"--csv-file", "hello.java", "--add-todo", "--todo-text", "Finish HW",
        "--completed", "--due", "12/1/2010", "--priority"};
    testHashMap = testCmdLineProcessor.processArguments(cmdLineArgs);
  }

  @Test
  public void processArgumentsValidCategory() throws CommandLineException {
    String[] cmdLineArgs = {"--csv-file", "hello.java", "--add-todo", "--todo-text", "Finish HW",
        "--completed", "--due", "12/1/2010", "--priority", "1", "--category", "Problems in life"};
    testHashMap = testCmdLineProcessor.processArguments(cmdLineArgs);
    TestCase.assertEquals("Problems in life", testHashMap.get("--category"));
  }

  @Test (expected = CommandLineException.class)
  public void processArgumentsInvalidCategory() throws CommandLineException {
    String[] cmdLineArgs = {"--csv-file", "hello.java", "--add-todo", "--todo-text", "Finish HW",
        "--completed", "--due", "12/1/2010", "--priority", "1", "--category"};
    testHashMap = testCmdLineProcessor.processArguments(cmdLineArgs);
  }

  @Test
  public void processArgumentsValidCompleteToDo() throws CommandLineException {
    String[] cmdLineArgs = {"--csv-file", "hello.java", "--add-todo", "--todo-text", "Finish HW",
        "--completed", "--due", "12/1/2010", "--priority", "1", "--category", "Problems in life",
    "--complete-todo", "112"};
    testHashMap = testCmdLineProcessor.processArguments(cmdLineArgs);
    TestCase.assertEquals("112", testHashMap.get("--complete-todo"));
  }

  @Test (expected = CommandLineException.class)
  public void processArgumentsInvalidCompleteToDo() throws CommandLineException {
    String[] cmdLineArgs = {"--csv-file", "hello.java", "--add-todo", "--todo-text", "Finish HW",
        "--completed", "--due", "12/1/2010", "--priority", "1", "--category", "Problems in life",
        "--complete-todo"};
    testHashMap = testCmdLineProcessor.processArguments(cmdLineArgs);
  }

  @Test
  public void processArgumentsValidDisplay() throws CommandLineException {
    String[] cmdLineArgs = {"--csv-file", "hello.java", "--add-todo", "--todo-text", "Finish HW",
        "--completed", "--due", "12/1/2010", "--priority", "1", "--category", "Problems in life",
        "--complete-todo", "112", "--display"};
    testHashMap = testCmdLineProcessor.processArguments(cmdLineArgs);
    TestCase.assertEquals("Given", testHashMap.get("--display"));
  }

  @Test
  public void processArgumentsValidShowIncomplete() throws CommandLineException {
    String[] cmdLineArgs = {"--csv-file", "hello.java", "--add-todo", "--todo-text", "Finish HW",
        "--completed", "--due", "12/1/2010", "--priority", "1", "--category", "Problems in life",
        "--complete-todo", "112", "--display", "--show-incomplete"};
    testHashMap = testCmdLineProcessor.processArguments(cmdLineArgs);
    TestCase.assertEquals("Given", testHashMap.get("--show-incomplete"));
  }

  @Test
  public void processArgumentsValidShowCategory() throws CommandLineException {
    String[] cmdLineArgs = {"--csv-file", "hello.java", "--add-todo", "--todo-text", "Finish HW",
        "--completed", "--due", "12/1/2010", "--priority", "1", "--category", "Problems in life",
        "--complete-todo", "112", "--display", "--show-incomplete", "--show-category", "School"};
    testHashMap = testCmdLineProcessor.processArguments(cmdLineArgs);
    TestCase.assertEquals("School", testHashMap.get("--show-category"));
  }

  @Test (expected = CommandLineException.class)
  public void processArgumentsInvalidShowCategory() throws CommandLineException {
    String[] cmdLineArgs = {"--csv-file", "hello.java", "--add-todo", "--todo-text", "Finish HW",
        "--completed", "--due", "12/1/2010", "--priority", "1", "--category", "Problems in life",
        "--complete-todo", "112", "--display", "--show-incomplete", "--show-category"};
    testHashMap = testCmdLineProcessor.processArguments(cmdLineArgs);
  }

  @Test
  public void processArgumentsValidSortByDate() throws CommandLineException {
    String[] cmdLineArgs = {"--csv-file", "hello.java", "--add-todo", "--todo-text", "Finish HW",
        "--completed", "--due", "12/1/2010", "--priority", "1", "--category", "Problems in life",
        "--complete-todo", "112", "--display", "--show-incomplete", "--show-category", "School",
    "--sort-by-date"};
    testHashMap = testCmdLineProcessor.processArguments(cmdLineArgs);
    TestCase.assertEquals("Given", testHashMap.get("--sort-by-date"));
  }

  @Test (expected = CommandLineException.class)
  public void processArgumentsInvalidSortByDate() throws CommandLineException {
    String[] cmdLineArgs = {"--csv-file", "hello.java", "--add-todo", "--todo-text", "Finish HW",
        "--completed", "--due", "12/1/2010", "--priority", "1", "--category", "Problems in life",
        "--complete-todo", "112", "--display", "--show-incomplete", "--show-category", "School",
        "--sort-by-date", "--sort-by-priority"};
    testHashMap = testCmdLineProcessor.processArguments(cmdLineArgs);
  }

  @Test
  public void testEqualsReflexive() {
    TestCase.assertTrue(testCmdLineProcessor.equals(testCmdLineProcessor));
  }

  @Test
  public void testEqualsSymmetric() {
    CmdLineProcessor testProcessor = new CmdLineProcessor();
    TestCase.assertTrue(testCmdLineProcessor.equals(testProcessor));
    TestCase.assertTrue(testProcessor.equals(testCmdLineProcessor));
  }

  @Test
  public void testEqualsTransitive() {
    CmdLineProcessor testProcessor2 = new CmdLineProcessor();
    CmdLineProcessor testProcessor3 = new CmdLineProcessor();
    TestCase.assertTrue(testCmdLineProcessor.equals(testProcessor2));
    TestCase.assertTrue(testProcessor2.equals(testProcessor3));
    TestCase.assertTrue(testCmdLineProcessor.equals(testProcessor3));
  }

  @Test
  public void testEqualsConsistency() {
    TestCase.assertTrue(testCmdLineProcessor.equals(testCmdLineProcessor));
    TestCase.assertTrue(testCmdLineProcessor.equals(testCmdLineProcessor));
    TestCase.assertTrue(testCmdLineProcessor.equals(testCmdLineProcessor));
  }

  @Test
  public void testEqualsNull() {
    TestCase.assertFalse(testCmdLineProcessor.equals(null));
  }

  @Test
  public void testHashCodeConsistency() {
    TestCase.assertEquals(testCmdLineProcessor.hashCode(), testCmdLineProcessor.hashCode());
  }

  @Test
  public void testHashCodeDifferent() {
    CmdLineProcessor testProcessor2 = new CmdLineProcessor();
    TestCase.assertNotSame(testCmdLineProcessor.hashCode(), testProcessor2.hashCode());
  }

  @Test
  public void testToString() {
    String expected = "CmdLineProcessor{commandLineData={--show-incomplete=null, "
        + "--show-category=null, --display=null, --due=null, --category=null, "
        + "--complete-todo=null, --sort-by-priority=null, --csv-file=null, --add-todo=null, "
        + "--completed=null, --sort-by-date=null, --priority=null, --todo-text=null}}";
    TestCase.assertEquals(expected, testCmdLineProcessor.toString());
  }
}