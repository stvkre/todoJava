import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Task {
  private String description;
  private boolean completed;
  private LocalDateTime createdAt;
  private int id;

  public Task(String description) {
    this.description = description;
    completed = false;
    createdAt = LocalDateTime.now();
  }
