import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static org.fluentlenium.core.filter.FilterConstructor.*;
import static org.assertj.core.api.Assertions.assertThat;

public class AppTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();

  @Override
  public WebDriver getDefaultDriver() {
    return webDriver;
  }

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @ClassRule
  public static ServerRule server = new ServerRule();


//   @Test
//   public void rootTest() {
//     goTo("http://localhost:4567/");
//     assertThat(pageSource()).contains("Todo List!");
//   }
//
// //CATEGORY TESTS//
//
//   @Test
//   public void categoryIsCreatedTest() {
//     goTo("http://localhost:4567/");
//     click("a", withText("Add a new category"));
//     fill("#name").with("Household chores");
//     submit(".btn");
//     assertThat(pageSource()).contains("Your category has been saved.");
//   }
//
//   @Test
//   public void categoryIsDisplayedTest() {
//     Category myCategory = new Category("Household chores");
//     String categoryPath = String.format("http://localhost:4567/%d", myCategory.getId());
//     goTo(categoryPath);
//     assertThat(pageSource()).contains("Household chores");
//   }
//
// //TASK TESTS//
//
//   @Test
//   public void categoryTasksFormIsDisplayed() {
//     goTo("http://localhost:4567/categories/new");
//     fill("#name").with("Shopping");
//     submit(".btn");
//     click("a", withText("View categories"));
//     click("a", withText("Shopping"));
//     click("a", withText("Add a new task"));
//     assertThat(pageSource()).contains("Add a Task to Shopping");
//   }
//
//   // @Test
//   // public void tasksAreAddedAndDisplayed() {
//   //   Category myCategory = new Category("Banking");
//   //   myCategory.save();
//   //   Task firstTask = new Task("Deposit paycheck");
//   //   firstTask.save();
//   //   Task secondTask = new Task("Request a loan");
//   //   secondTask.save();
//   //   String categoryPath = String.format("http://localhost:4567/%d", myCategory.getId());
//   //   goTo(categoryPath);
//   //   assertThat(pageSource()).contains("Deposit paycheck");
//   //   assertThat(pageSource()).contains("Request a loan");
//   // }
//
//   @Test
//   public void taskNotFoundMessageShown() {
//     goTo("http://localhost:4567/tasks/999");
//     assertThat(pageSource()).contains("Task not found");
//   }
}
