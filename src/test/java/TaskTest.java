import org.junit.*;
import static org.junit.Assert.*;
import java.time.LocalDateTime;

public class TaskTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void clear_emptiesAllFirst() {
    assertEquals(Task.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfDescriptionsAretheSame() {
    Task firstTask = new Task("Mow the lawn");
    Task secondTask = new Task("Mow the lawn");
    assertTrue(firstTask.equals(secondTask));
  }

  @Test
  public void save_returnsTrueIfDescriptionsAretheSame() {
    Task myTask = new Task("Mow the lawn");
    myTask.save();
    assertTrue(Task.all().get(0).equals(myTask));
  }

  @Test
  public void save_assignsIdToObject() {
    Task myTask = new Task("Mow the lawn");
    myTask.save();
    Task savedTask = Task.all().get(0);
    assertEquals(myTask.getId(), savedTask.getId());
  }

  @Test
  public void find_findsTaskInDatabase_true() {
    Task myTask = new Task("Mow the lawn");
    myTask.save();
    Task savedTask = Task.find(myTask.getId());
    assertTrue(myTask.equals(savedTask));
  }

  @Test
  public void Task_instantiatesCorrectly_true() {
    Task myTask = new Task("Mow the lawn");
    assertEquals(true, myTask instanceof Task);
  }

  @Test
 public void task_instantiatesWithDescription_true() {
   Task myTask = new Task("Mow the lawn");
   assertEquals("Mow the lawn", myTask.getDescription());
 }

 // @Test
 // public void isCompleted_isFalseAfterInstansiation_false() {
 //   Task myTask = new Task("Mow the lawn");
 //   assertEquals(false, myTask.isCompleted());
 // }

 //  @Test
 //  public void getCreatedAt_instantiatesWithCurrentTime_today() {
 //    Task myTask = new Task("Mow the lawn");
 //    assertEquals(LocalDateTime.now().getDayOfWeek(), myTask.getCreatedAt().getDayOfWeek());
 // }

 @Test
 public void all_returnsAllInstancesOfTask_true() {
    Task firstTask = new Task("Mow the lawn");
    Task secondTask = new Task("Buy groceries");
    firstTask.save();
    secondTask.save();
    assertTrue(Task.all().contains(firstTask));
    assertTrue(Task.all().contains(secondTask));
 }

 @Test
  public void newId_tasksInstantiateWithAnID_true() {
    Task myTask = new Task("Mow the lawn");
    assertEquals(Task.all().size(), myTask.getId());
  }

  @Test
  public void find_returnsTaskWithSameId_secondTask() {
    Task firstTask = new Task("Mow the lawn");
    Task secondTask = new Task("Buy groceries");
    firstTask.save();
    secondTask.save();
    assertEquals(Task.find(secondTask.getId()), secondTask);
  }

  @Test
  public void find_returnsNullWhenNoTaskFound_null() {
    assertTrue(Task.find(999) == null);
  }

  @Test
  public void clear_emptiesAllTasksFromArrayList() {
    Task myTask = new Task("Mow the lawn");
    Task.clear();
    assertEquals(Task.all().size(), 0);
  }
}
