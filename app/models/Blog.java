package models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.db.jpa.Model;

@Entity
public class Blog extends Model
{
  public String name;
  @ManyToOne
  public User user;

  
  public Blog(String name)
  {
    this.name = name;
  }
  public String toString()
  {
    return name;
  }
  public static Blog findByName(String name)
  {
    return find("name", name).first();
  }
}