package todoapplication.model;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ProcessorTest {
  ToDo testToDo1;
  ToDo testToDo2;
  List<ToDo> testList;

  @Before
  public void setUp() throws Exception {
    testToDo1 = new ToDo.ToDoBuilder().id(1)
        .text("Wash dishes")
        .completed(false)
        .dueDate(LocalDate.of(2020, 4, 6))
        .priority(2)
        .category("Housework")
        .build();
    testToDo2 = new ToDo.ToDoBuilder().id(2)
        .text("Java, Assignment 9")
        .completed(false)
        .dueDate(LocalDate.of(2020, 4, 7))
        .priority(1)
        .category("Homework")
        .build();
    testList = new ArrayList<>();
    testList.add(testToDo1);
    testList.add(testToDo2);
    CollectionOfToDos.getCollectionOfToDos().addToDo(testToDo1);
  }

  @Test
  public void getCollectionOfToDos() {
    TestCase.assertEquals(CollectionOfToDos.getCollectionOfToDos(),
        Processor.getProcessor().getCollectionOfToDos());

  }

  @Test
  public void addTodo() {
    Processor.getProcessor().addTodo("Java, Assignment 9", null,
        "4/7/2020", "1", "Homework");
    TestCase.assertTrue(Processor.getProcessor().getCollectionOfToDos()
                                 .getToDos().contains(testToDo2));
  }

  @Test
  public void updateCompletionStatusOfToDos() throws IDNotFoundException {
    Processor.getProcessor().addTodo("Java, Assignment 9" , "false",
        "4/7/2020", "1", "Homework");
    Processor.getProcessor().addTodo("System A10" , "false",
        "4/10/2020", "1", "Homework");

    Processor.getProcessor().updateCompletionStatusOfToDos("1 2");
    TestCase.assertTrue(Processor.getProcessor().getCollectionOfToDos()
                                 .getToDos().get(0).isCompleted());
    TestCase.assertTrue(Processor.getProcessor().getCollectionOfToDos()
                                  .getToDos().get(1).isCompleted());
  }

  @Test (expected = IDNotFoundException.class)
  public void updateCompletionStatusOfToDosIDNotFound() throws IDNotFoundException {
    Processor.getProcessor().updateCompletionStatusOfToDos("3");
  }


  @Test
  public void populateCollectionOfToDosWithCSV() throws FileErrorException {
    Processor.getProcessor().populateCollectionOfToDosWithCSV("test_csv.txt");
    Processor.getProcessor().getCollectionOfToDos().getToDos().contains(testToDo2);
    Processor.getProcessor().getCollectionOfToDos().getToDos().contains(testToDo1);
  }

  @Test
  public void sortAndFilterFilterByCategorySortByPriority() {
    ToDo testToDo3 = new ToDo.ToDoBuilder().id(4)
                             .text("laundry")
                             .completed(true)
                             .dueDate(LocalDate.MAX)
                             .priority(3)
                             .category("None")
                             .build();
    Processor.getProcessor().addTodo("Java, Assignment 9" , "false",
        "4/7/2020", "1", "Homework");
    Processor.getProcessor().addTodo("Systems A10" , "false",
        "4/10/2020", "1", "Homework");
    Processor.getProcessor().addTodo("Wash dishes" , "false",
        "4/8/2020", "3", "Housework");
    Processor.getProcessor().addTodo("laundry" , "true",
        null, "3", "null");
    Processor.getProcessor().sortAndFilter("Given", "Homework",
        null, "Given");

    TestCase.assertTrue(Processor.getProcessor().getToDosToWorkWith().contains(testToDo2));
    TestCase.assertEquals(2, Processor.getProcessor().getToDosToWorkWith().size());
    TestCase.assertFalse(Processor.getProcessor().getToDosToWorkWith().contains(testToDo3));
  }

  @After
  public void resetCollectionOfToDos() {
    CollectionOfToDos.getCollectionOfToDos().reset();
    Processor.getProcessor().reset();
  }

}