import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/tasks.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

//TASK ROUTES//

    get("/tasks", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      List<Task> tasks = Task.all();
      model.put("tasks", tasks);
      model.put("template", "templates/tasks.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/tasks", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      String description = request.queryParams("description");
      Task newTask = new Task(description);
      response.redirect("/tasks");
      return null;
    });

    get("/tasks/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      int id = Integer.parseInt(request.params("id"));
      Task task = Task.find(id);
      model.put("task", task);
      model.put("template", "templates/task.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    put("/tasks/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Task task = Task.find(Integer.parseInt(request.params("id")));
      String description = request.queryParams("description");
      task.update("description");
      model.put("template", "templates/task.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    delete("/tasks/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Task task = Task.find(Integer.parseInt(request.params("id")));
      task.delete();
      model.put("template", "templates/task.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

//CATEGORY ROUTES//

  //   get("/categories", (request, response) -> {
  //     HashMap<String, Object> model = new HashMap<String, Object>();
  //     model.put("categories", Category.all());
  //     model.put("template", "templates/categories.vtl");
  //     return new ModelAndView(model, layout);
  //   }, new VelocityTemplateEngine());
  //
  //   get("categories/new", (request, response) -> {
  //     HashMap<String, Object> model = new HashMap<String, Object>();
  //     model.put("template", "templates/category-form.vtl");
  //     return new ModelAndView(model, layout);
  //   }, new VelocityTemplateEngine());
  //
  //   post("/", (request, response) -> {
  //     HashMap<String, Object> model = new HashMap<String, Object>();
  //     Category category = Category.find(Integer.parseInt(request.queryParams("categoryId2")));
  //     category.delete();
  //     model.put("template", "templates/index.vtl");
  //     return new ModelAndView(model, layout);
  //     }, new VelocityTemplateEngine());
  //
  //   post("/categories", (request, response) -> {
  //     HashMap<String, Object> model = new HashMap<String, Object>();
  //     String name = request.queryParams("name");
  //     Category newCategory = new Category(name);
  //     newCategory.save();
  //     model.put("category", newCategory);
  //     model.put("template", "templates/success.vtl");
  //     return new ModelAndView(model, layout);
  //     }, new VelocityTemplateEngine());
  //
  //   post("/categories/:id", (request, response) -> {
  //     HashMap<String, Object> model = new HashMap<String, Object>();
  //     Task task = Task.find(Integer.parseInt(request.queryParams("taskId")));
  //     Category category = Category.find(task.getCategoryId());
  //     task.delete();
  //     List<Task> tasks = category.getTasks();
  //     model.put("category", category);
  //     model.put("tasks", tasks);
  //     model.put("template", "templates/category-tasks-form.vtl");
  //     return new ModelAndView(model, layout);
  //   }, new VelocityTemplateEngine());
  //
  //   get("/categories/:id", (request, response) -> {
  //     HashMap<String, Object> model = new HashMap<String, Object>();
  //     Category category = Category.find(Integer.parseInt(request.params(":id")));
  //     List<Task> tasks = category.getTasks();
  //     model.put("category", category);
  //     model.put("tasks", tasks);
  //     model.put("template", "templates/category-tasks-form.vtl");
  //     return new ModelAndView(model, layout);
  //   }, new VelocityTemplateEngine());
  //
  //   get("/categories/:id/tasks/new", (request, response) -> {
  //     HashMap<String, Object> model = new HashMap<String, Object>();
  //     Category category = Category.find(Integer.parseInt(request.params(":id")));
  //     List<Task> tasks = category.getTasks();
  //     model.put("category", category);
  //     model.put("tasks", tasks);
  //     model.put("template", "templates/category-tasks-form.vtl");
  //     return new ModelAndView(model, layout);
  //   }, new VelocityTemplateEngine());
  }
}
