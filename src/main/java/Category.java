import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;

public class Category {
  private String name;
  private int id;

  public static List<Category> all() {
       String sql = "SELECT id, name FROM categories";
       try(Connection con = DB.sql2o.open()) {
         return con.createQuery(sql).executeAndFetch(Category.class);
       }
     }

     public Category(String name) {
       this.name = name;
     }


  public static Category find(int id) {
        try(Connection con = DB.sql2o.open()) {
          String sql = "SELECT * FROM categories where id=:id";
          Category category = con.createQuery(sql)
            .addParameter("id", id)
            .executeAndFetchFirst(Category.class);
          return category;
        }
      }

  @Override
      public boolean equals(Object otherCategory) {
        if (!(otherCategory instanceof Category)) {
          return false;
        } else {
          Category newCategory = (Category) otherCategory;
          return this.getname().equals(newCategory.getname()) &&
                 this.getid() == newCategory.getid();
        }
      }
  public void save() {
        try(Connection con = DB.sql2o.open()) {
          String sql = "INSERT INTO categories(name) VALUES (:name)";
          this.id = (int) con.createQuery(sql, true)
            .addParameter("name", this.name)
            .executeUpdate()
            .getKey();
        }
      }

  public String getname() {
    return name;
  }

    public int getid() {
    return id;
  }

  public List<Task> getTasks() {
    
     }


     }
