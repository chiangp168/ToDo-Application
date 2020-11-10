package todoapplication.model;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CollectionOfToDosTest {
  ToDo testToDo1;
  ToDo testToDo2;

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
  }

  @Test
  public void getToDos() {
    CollectionOfToDos.getCollectionOfToDos().addToDo(testToDo1);
    List<ToDo> expected = new ArrayList<>();
    expected.add(testToDo1);
    TestCase.assertEquals(expected, CollectionOfToDos.getCollectionOfToDos().getToDos());
  }

  @Test
  public void getNumberOfToDos() {
    TestCase.assertEquals(0, CollectionOfToDos.getCollectionOfToDos().getNumberOfToDos());
    CollectionOfToDos.getCollectionOfToDos().addToDo(testToDo1);
    TestCase.assertEquals(1, CollectionOfToDos.getCollectionOfToDos().getNumberOfToDos());
  }

  @Test
  public void addToDo() {
    TestCase.assertEquals(0, CollectionOfToDos.getCollectionOfToDos().getNumberOfToDos());
    CollectionOfToDos.getCollectionOfToDos().addToDo(testToDo2);
    TestCase.assertTrue(CollectionOfToDos.getCollectionOfToDos().getToDos().contains(testToDo2));
  }

  @Test
  public void updateToDoCompletion() throws IDNotFoundException {
    try {
      CollectionOfToDos.getCollectionOfToDos().addToDo(testToDo1);
      CollectionOfToDos.getCollectionOfToDos().updateToDoCompletion(1);
      TestCase.assertTrue(CollectionOfToDos.getCollectionOfToDos().getToDos().get(0).isCompleted());
    }
    catch (IDNotFoundException e) {
      fail("An exception should not have been thrown");
    }
  }

  @Test (expected = IDNotFoundException.class)
  public void updateToDoCompletionIDNotFound() throws IDNotFoundException {
    CollectionOfToDos.getCollectionOfToDos().addToDo(testToDo1);
    CollectionOfToDos.getCollectionOfToDos().updateToDoCompletion(11);
  }

  @Test
  public void testToString() {
    CollectionOfToDos.getCollectionOfToDos().addToDo(testToDo1);
    List<ToDo> expectedArray = new ArrayList<>();
    expectedArray.add(testToDo1);
    String expected = "CollectionOfToDos: \n" +
        "ToDos: " + expectedArray.toString() + "\n" +
        "Total Number of ToDos=" + "1";
    TestCase.assertEquals(expected, CollectionOfToDos.getCollectionOfToDos().toString());
  }

  @Test
  public void testReset() {
    CollectionOfToDos.getCollectionOfToDos().addToDo(testToDo1);
    CollectionOfToDos.getCollectionOfToDos().addToDo(testToDo2);
    CollectionOfToDos.getCollectionOfToDos().reset();
    TestCase.assertEquals(0, CollectionOfToDos.getCollectionOfToDos().getNumberOfToDos());

  }

  @After
  public void resetCollectionOfToDos() {
    CollectionOfToDos.getCollectionOfToDos().reset();
  }

}