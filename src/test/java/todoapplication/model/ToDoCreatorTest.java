package todoapplication.model;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.HashMap;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

public class ToDoCreatorTest {
  HashMap<String, String> todoWithNoCategory;
  HashMap<String, String> todoWithNoCompletion;
  HashMap<String, String> todoWithNoPriority;
  HashMap<String, String> todoWithNoDueDate;

  @Before
  public void setUp() throws Exception {
    todoWithNoCategory = new HashMap<>();
    todoWithNoCompletion = new HashMap<>();
    todoWithNoPriority = new HashMap<>();
    todoWithNoDueDate = new HashMap<>();
  }

  @Test
  public void createToDoFromLineNoCategory() {
    todoWithNoCategory.put("id", "1");
    todoWithNoCategory.put("text", "wash dishes");
    todoWithNoCategory.put("completed", "false");
    todoWithNoCategory.put("due", "4/7/2020");
    todoWithNoCategory.put("priority", "3");
    todoWithNoCategory.put("category", "?");
    ToDo toDo = ToDoCreator.getToDoCreator().createToDoFromLine(todoWithNoCategory);
    TestCase.assertEquals((Integer)1, toDo.getID());
    TestCase.assertEquals("wash dishes", toDo.getText());
    TestCase.assertEquals("None", toDo.getCategory());
    TestCase.assertEquals(3, toDo.getPriority());
    TestCase.assertEquals(LocalDate.of(2020, 4, 7), toDo.getDueDate());
  }

  @Test
  public void createToDoFromLineNoCompletion() {
    todoWithNoCategory.put("id", "1");
    todoWithNoCategory.put("text", "wash dishes");
    todoWithNoCategory.put("completed", "?");
    todoWithNoCategory.put("due", "4/7/2020");
    todoWithNoCategory.put("priority", "3");
    todoWithNoCategory.put("category", "Housework");
    ToDo toDo = ToDoCreator.getToDoCreator().createToDoFromLine(todoWithNoCategory);
    TestCase.assertEquals((Integer)1, toDo.getID());
    TestCase.assertEquals("wash dishes", toDo.getText());
    TestCase.assertEquals("Housework", toDo.getCategory());
    TestCase.assertEquals(false, toDo.isCompleted());
  }

  @Test
  public void createToDoFromLineNoPriority() {
    todoWithNoCategory.put("id", "1");
    todoWithNoCategory.put("text", "wash dishes");
    todoWithNoCategory.put("completed", "?");
    todoWithNoCategory.put("due", "4/7/2020");
    todoWithNoCategory.put("priority", "?");
    todoWithNoCategory.put("category", "Housework");
    ToDo toDo = ToDoCreator.getToDoCreator().createToDoFromLine(todoWithNoCategory);
    TestCase.assertEquals((Integer)1, toDo.getID());
    TestCase.assertEquals(3, toDo.getPriority());
    TestCase.assertEquals("Housework", toDo.getCategory());
    TestCase.assertEquals(false, toDo.isCompleted());
  }

  @Test
  public void createToDoFromLineNoDueDate() {
    todoWithNoCategory.put("id", "1");
    todoWithNoCategory.put("text", "wash dishes");
    todoWithNoCategory.put("completed", "?");
    todoWithNoCategory.put("due", "?");
    todoWithNoCategory.put("priority", "?");
    todoWithNoCategory.put("category", "Housework");
    ToDo toDo = ToDoCreator.getToDoCreator().createToDoFromLine(todoWithNoCategory);
    TestCase.assertEquals((Integer)1, toDo.getID());
    TestCase.assertEquals(3, toDo.getPriority());
    TestCase.assertEquals("Housework", toDo.getCategory());
    TestCase.assertEquals(false, toDo.isCompleted());
    TestCase.assertEquals(LocalDate.MAX, toDo.getDueDate());
  }

  @Test
  public void createNewToDo() {
    ToDo toDo = ToDoCreator.getToDoCreator().createNewToDo("Homework", null,
        "4/7/2020", "?", "?");
    TestCase.assertEquals((Integer)1, toDo.getID());
    TestCase.assertEquals(3, toDo.getPriority());
    TestCase.assertEquals("None", toDo.getCategory());
    TestCase.assertEquals(false, toDo.isCompleted());
    TestCase.assertEquals(LocalDate.of(2020, 4, 7), toDo.getDueDate());
    TestCase.assertEquals("Homework", toDo.getText());
  }

}