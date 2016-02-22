import org.junit.*;
import static org.junit.Assert.*;
import java.time.LocalDateTime;
import org.sql2o.*;

public class TaskTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void clear_emptiesAllFirst() {
    assertEquals(Task.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfDescriptionsAretheSame() {
    Task firstTask = new Task("Mow the lawn", 1);
    Task secondTask = new Task("Mow the lawn", 1);
    assertTrue(firstTask.equals(secondTask));
  }

  @Test
  public void save_returnsTrueIfDescriptionsAretheSame() {
    Task myTask = new Task("Mow the lawn", 1);
    myTask.save();
    assertTrue(Task.all().get(0).equals(myTask));
  }

  @Test
  public void save_assignsIdToObject() {
    Task myTask = new Task("Mow the lawn", 1);
    myTask.save();
    Task savedTask = Task.all().get(0);
    assertEquals(myTask.getId(), savedTask.getId());
  }

  @Test
  public void all_savesIntoDatabase_true() {
    Task myTask = new Task("Mow the lawn", 1);
    myTask.save();
    assertEquals(Task.all().get(0).getDescription(), "Mow the lawn");
  }

  @Test
  public void find_findsTaskInDatabase_true() {
    Task myTask = new Task("Mow the lawn", 1);
    myTask.save();
    Task savedTask = Task.find(myTask.getId());
    assertEquals(savedTask.getDescription(), "Mow the lawn");
  }

  @Test
  public void save_savesCategoryIdIntoDB_true() {
    Category myCategory = new Category("Household chores");
    myCategory.save();
    Task myTask = new Task("Mow the lawn", myCategory.getId());
    myTask.save();
    Task savedTask = Task.find(myTask.getId());
    assertEquals(savedTask.getCategoryId(), myCategory.getId());
  }

  @Test
  public void Task_instantiatesCorrectly_true() {
    Task myTask = new Task("Mow the lawn", 1);
    assertEquals(true, myTask instanceof Task);
  }

  @Test
 public void task_instantiatesWithDescription_true() {
   Task myTask = new Task("Mow the lawn", 1);
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
    Task firstTask = new Task("Mow the lawn", 1);
    Task secondTask = new Task("Buy groceries", 1);
    firstTask.save();
    secondTask.save();
    assertTrue(Task.all().contains(firstTask));
    assertTrue(Task.all().contains(secondTask));
 }

 @Test
  public void newId_tasksInstantiateWithAnID_true() {
    Task myTask = new Task("Mow the lawn", 1);
    assertEquals(Task.all().size(), myTask.getId());
  }

  @Test
  public void find_returnsTaskWithSameId_secondTask() {
    Task firstTask = new Task("Mow the lawn", 1);
    Task secondTask = new Task("Buy groceries", 1);
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
    Task myTask = new Task("Mow the lawn", 1);
    Task.clear();
    assertEquals(Task.all().size(), 0);
  }
}
