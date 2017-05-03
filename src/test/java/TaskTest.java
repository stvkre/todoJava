import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class TaskTest {

  public class TaskTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
public void save_returnsTrueIfDescriptionsAretheSame() {
  Task myTask = new Task("Mow the lawn");
  myTask.save();
  assertTrue(Task.all().get(0).equals(myTask));
}

@Test
 public void getId_tasksInstantiateWithAnID() {
   Task myTask = new Task("Mow the lawn");
   myTask.save();
   assertTrue(myTask.getId() > 0);
 }

 @Test
  public void find_returnsTaskWithSameId_secondTask() {
    Task firstTask = new Task("Mow the lawn");
    firstTask.save();
    Task secondTask = new Task("Buy groceries");
    secondTask.save();
    assertEquals(Task.find(secondTask.getId()), secondTask);
  }


@Test
  public void all_returnsAllInstancesOfTask_true() {
    Task firstTask = new Task("Mow the lawn");
    firstTask.save();
    Task secondTask = new Task("Buy groceries");
    secondTask.save();
    assertEquals(true, Task.all().get(0).equals(firstTask));
    assertEquals(true, Task.all().get(1).equals(secondTask));
  }

  @Test
  public void update_updatesTaskDescription_true() {
    Task myTask = new Task("Mow the lawn", 1);
    myTask.save();
    myTask.update("Take a nap");
    assertEquals("Take a nap", Task.find(myTask.getId()).getDescription());
  }

  @Test
public void save_assignsIdToObject() {
  Task myTask = new Task("Mow the lawn");
  myTask.save();
  Task savedTask = Task.all().get(0);
  assertEquals(myTask.getId(), savedTask.getId());
}

// deleting a task

@Test
public void delete_deletesTask_true() {
  Task myTask = new Task("Mow the lawn", 1);
  myTask.save();
  int myTaskId = myTask.getId();
  myTask.delete();
  assertEquals(null, Task.find(myTaskId));
}
