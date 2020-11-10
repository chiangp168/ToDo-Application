package todoapplication.model;

import static org.junit.Assert.*;

import java.time.LocalDate;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ToDoUpdaterTest {
  Integer[] idsToUpdate;
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
        .text("Java, A9")
        .completed(false)
        .dueDate(LocalDate.of(2020, 4, 8))
        .priority(1)
        .category("Homework")
        .build();
    idsToUpdate = new Integer[2];
    idsToUpdate[0] = 1;
    idsToUpdate[1] = 2;
    CollectionOfToDos.getCollectionOfToDos().addToDo(testToDo1);
    CollectionOfToDos.getCollectionOfToDos().addToDo(testToDo2);
  }

  @Test
  public void updateToDos() throws IDNotFoundException {
    try {
      ToDoUpdater.getToDoUpdater().updateToDos(idsToUpdate);
      TestCase.assertTrue(CollectionOfToDos.getCollectionOfToDos().getToDos().get(0).isCompleted());
      TestCase.assertTrue(CollectionOfToDos.getCollectionOfToDos().getToDos().get(1).isCompleted());
    } catch (IDNotFoundException e) {
      fail("An exception should not have been thrown");
    }
  }

  @Test (expected = IDNotFoundException.class)
  public void updateToDosInvalidID() throws IDNotFoundException {
    idsToUpdate[0] = 11;
    ToDoUpdater.getToDoUpdater().updateToDos(idsToUpdate);
  }

  @After
  public void resetCollection() {
    CollectionOfToDos.getCollectionOfToDos().reset();
  }
}