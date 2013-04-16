package models;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class Page extends Model
{
  public String name;

  public Page(String name)
  {
    this.name = name;
  }
  public String toString()
  {
    return name;
  }
  public static Page findByName(String name)
  {
    return find("name", name).first();
  }
}