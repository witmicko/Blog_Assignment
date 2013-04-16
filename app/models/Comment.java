package models;

import javax.persistence.Entity;
import play.db.jpa.Model;

@Entity
public class Comment extends Model
{
  public String name;

  public Comment(String name)
  {
    this.name = name;
  }
  
}