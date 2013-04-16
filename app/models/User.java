package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

@Entity
public class User extends Model
{
  @OneToMany(mappedBy="user",cascade=CascadeType.ALL)
  public List<Blog> blogs; //stores blogs
  public String name;      //name of the user

  /**
   * Constructor for object of class User
   * @param name
   */
  public User(String name)
  {
    this.name = name;
    this.blogs = new ArrayList<Blog>();
  }
  
  /**
   * Method addBlog() add blog to the list of blogs
   * @param blog
   */
  public void addBlog(Blog blog){
    blog.user=this;
    this.blogs.add(blog);
  }
//turns out play does it :)  
//  /**
//   * Method removeBlog() removes passed in blog from the list of blogs
//   * @param blog
//   */
//  public void removeBlog(Blog blog){
//    if(blogs !=null){
//    for(int i =0;i<blogs.size();i++){
//      if(blogs.get(i).hashCode() == blog.hashCode()){
//        blogs.remove(0);
//      }
//    }
//    }
//  }
  
  public static User findByName(String name)
  {
    return find("name", name).first();
  }
  public String toString()
  {
    return name;
  }
}