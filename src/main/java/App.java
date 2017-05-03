import java.util.Map;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
    Map<String, Object> model = new HashMap<String, Object>();
    model.put("categories", Category.all());
    model.put("template", "templates/index.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  get("/categories/:category_id/tasks/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Category category = Category.find(Integer.parseInt(request.params(":category_id")));
      Task task = Task.find(Integer.parseInt(request.params(":id")));
      model.put("category", category);
      model.put("task", task);
      model.put("template", "templates/task.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

// updating the to-do-list through a form

    post("/categories/:category_id/tasks/:id", (request, response) -> {
  Map<String, Object> model = new HashMap<String, Object>();
  Task task = Task.find(Integer.parseInt(request.params("id")));
  String description = request.queryParams("description");
  Category category = Category.find(task.getCategoryId());
  task.update(description);
  String url = String.format("/categories/%d/tasks/%d", category.getId(), task.getId());
  response.redirect(url);
  return new ModelAndView(model, layout);
}, new VelocityTemplateEngine());

  }
}
