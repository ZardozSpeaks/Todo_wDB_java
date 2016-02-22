import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.sql2o.*;

public class Task {

  @Override
  public boolean equals(Object otherTask){
    if (!(otherTask instanceof Task)) {
      return false;
    } else {
      Task newTask = (Task) otherTask;
      return this.getDescription().equals(newTask.getDescription()) &&
             this.getId() == newTask.getId();
    }
  }

  public void save() {
  try(Connection con = DB.sql2o.open()) {
    String sql = "INSERT INTO Tasks (description) VALUES (:description)";
    this.id = (int) con.createQuery(sql, true).addParameter("description", this.description).executeUpdate().getKey();
    }
  }

  public static Task find(int id) {
  try(Connection con = DB.sql2o.open()) {
    String sql = "SELECT * FROM Tasks where id=:id";
    Task task = con.createQuery(sql).addParameter("id", id).executeAndFetchFirst(Task.class);
    return task;
    }
  }

  private static ArrayList<Task> instances = new ArrayList<Task>();

  private String description;
  // private LocalDateTime mCreatedAt;
  // private boolean mCompleted;
  private int id;

  public Task(String description) {
    this.description = description;
    // mCreatedAt = LocalDateTime.now();
    // mCompleted = false;
    // instances.add(this);
    // mId = instances.size();
  }

  public String getDescription() {
    return description;
  }

  // public boolean isCompleted() {
  //   return mCompleted;
  // }

  // public LocalDateTime getCreatedAt() {
  //   return mCreatedAt;
  // }

  public int getId() {
    return id;
  }

  // public void completeTask() {
  //   mCompleted = true;
  // }

  public static List<Task> all() {
    String sql = "SELECT id, description FROM Tasks";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Task.class);
    }
  }

  public static void clear() {
    instances.clear();
  }
}
