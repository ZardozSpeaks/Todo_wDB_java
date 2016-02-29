import org.junit.*;
import static org.junit.Assert.*;
import java.time.LocalDate;
import org.sql2o.*;
import java.util.List;
import java.util.ArrayList;


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
  public void all_savesIntoDatabase_true() {
    Task myTask = new Task("Mow the lawn");
    myTask.save();
    assertEquals(Task.all().get(0).getDescription(), "Mow the lawn");
  }

  @Test
  public void find_findsTaskInDatabase_true() {
    Task myTask = new Task("Mow the lawn");
    myTask.save();
    Task savedTask = Task.find(myTask.getId());
    assertEquals(savedTask.getDescription(), "Mow the lawn");
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
  public void addDueDate_addsDueDatePropertyToTask(){
    Task firstTask = new Task("Mow the lawn");
    firstTask.addDueDate("2016-02-25");
    assertEquals(firstTask.getDueDate(), LocalDate.parse("2016-02-25"));
  }

  @Test
  public void save_savesTaskWithUpdatedDueDateProperty_true(){
    Task firstTask = new Task("Mow the lawn");
    firstTask.addDueDate("2016-02-25");
    firstTask.save();
    Task savedTask = Task.find(firstTask.getId());
    assertEquals(savedTask, firstTask);
  }

  @Test
  public void addCategory_addsCategoryToTask() {
    Category myCategory = new Category("Household chores");
    myCategory.save();

    Task myTask = new Task("Mow the lawn");
    myTask.save();

    myTask.addCategory(myCategory);
    Category savedCategory = myTask.getCategories().get(0);
    assertTrue(myCategory.equals(savedCategory));
  }

  @Test
  public void getCategories_returnsAllCategories_ArrayList() {
    Category myCategory = new Category("Household chores");
    myCategory.save();

    Task myTask = new Task("Mow the lawn");
    myTask.save();

    myTask.addCategory(myCategory);
    List savedCategories = myTask.getCategories();
    assertEquals(savedCategories.size(), 1);
  }

  @Test
  public void delete_deletesAllTasksAndListsAssociations() {
    Category myCategory = new Category("Household chores");
    myCategory.save();

    Task myTask = new Task("Mow the lawn");
    myTask.save();

    myTask.addCategory(myCategory);
    myTask.delete();
    assertEquals(myCategory.getTasks().size(), 0);
  }
}
