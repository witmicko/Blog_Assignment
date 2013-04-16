package models;

import javax.persistence.Entity;
import play.db.jpa.Model;

@Entity
public class Post extends Model
{
  public String name;

  public Post(String name)
  {
    this.name = name;
  }
  public String toString()
  {
    return name;
  }
}