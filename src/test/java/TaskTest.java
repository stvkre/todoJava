import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class TaskTest {

  @Before
  public void setUp() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/to_do_test", null, null);
  }

  @Test
public void save_returnsTrueIfDescriptionsAretheSame() {
  Task myTask = new Task("Mow the lawn");
  myTask.save();
  assertTrue(Task.all().get(0).equals(myTask));
}

  @After
 public void tearDown() {
   try(Connection con = DB.sql2o.open()) {
     String sql = "DELETE FROM tasks *;";
     con.createQuery(sql).executeUpdate();
   }
 }
