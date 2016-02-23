import java.time.LocalDate;
import java.util.List;
import org.sql2o.*;

public class Task {

  private String description;
  // private boolean mCompleted;
  private int categoryId;
  private int id;
  private LocalDate dueDate;

  @Override
  public boolean equals(Object otherTask){
    if (!(otherTask instanceof Task)) {
      return false;
    } else {
      Task newTask = (Task) otherTask;
      System.out.println(this.getCategoryId());
      System.out.println(newTask.getCategoryId());
      return this.getDescription().equals(newTask.getDescription()) &&
             this.getId() == newTask.getId() &&
             this.getCategoryId() == newTask.getCategoryId();
    }
  }

  public Task(String description, int categoryId) {
    this.description = description;
    this.categoryId = categoryId;
    this.dueDate = null;
    // mCompleted = false;
  }

  public void addDueDate(String dueDate) {
    this.dueDate = LocalDate.parse(dueDate);
  }


  public String getDescription() {
    return description;
  }

  // public boolean isCompleted() {
  //   return mCompleted;
  // }

  public int getId() {
    return id;
  }

  public int getCategoryId(){
    return categoryId;
  }

  public LocalDate getDueDate(){
    return dueDate;
  }

  // public void completeTask() {
  //   mCompleted = true;
  // }

  public void save() {
  try(Connection con = DB.sql2o.open()) {
    String sql = "INSERT INTO tasks(description, categoryId) VALUES (:description, :categoryId)";
    this.id = (int) con.createQuery(sql,true)
    .addParameter("description", this.description)
    .addParameter("categoryId", this.categoryId)
    .executeUpdate()
    .getKey();
    }
  }

  public static Task find(int id) {
  try(Connection con = DB.sql2o.open()) {
    String sql = "SELECT * FROM Tasks where id=:id";
    Task task = con.createQuery(sql).addParameter("id", id).executeAndFetchFirst(Task.class);
    return task;
    }
  }

  public void update(String description) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE tasks SET description = :description) WHERE id = :id";
      con.createQuery(sql)
        .addParameter("description", description)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
    String sql = "DELETE FROM tasks WHERE id = :id;";
      con.createQuery(sql)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public static List<Task> all() {
    String sql = "SELECT id, description, categoryId FROM Tasks ORDER BY duedate DESC;";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
      .executeAndFetch(Task.class);
    }
  }
}
