package models;

import javax.persistence.Entity;
import play.db.jpa.Model;

@Entity
public class User extends Model
{
  public String name;

  public User(String name)
  {
    this.name = name;
  }
  public static User findByName(String name)
  {
    return find("name", name).first();
  }
  public String toString()
  {
    return name;
  }
}