package models;

import java.util.ArrayList;
import java.util.List;
//import javax.persistence.CascadeType;
//import javax.persistence.Entity;
//import javax.persistence.OneToMany;




import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import play.db.jpa.Blob;
import play.db.jpa.Model;

@Entity
public class User extends Model
{
  @OneToMany(cascade=CascadeType.ALL)
  public List<Blog> blogs; //stores blogs
  public String     firstName;
  public String     lastName;
  public int        age;
  public String     email;
  public String     password;
  public Blob       profilePicture;
  public Blob       thumbnailPicture;

  /**
   * Constructor for object of class User
   * @param name
   */
  public User(String firstName,String lastName ,int age, String email,String password )
  {
    this.firstName = firstName;
    this.lastName = lastName;
    this.age = age;
    this.email = email;
    this.password = password;
    this.blogs = new ArrayList<Blog>();
  }
  
  /**
   * Method addBlog() add blog to the list of blogs
   * @param blog
   */
  public void addBlog(Blog blog){
    //blog.user=this;
    this.blogs.add(blog);
  }
  public List<Blog> getBlogs(Long blogId) {
    return blogs;
  }

  public static User findByEmail(String email) {
    return find("email", email).first();
  }
  
  public static User findByName(String firstName)
  {
    return find("firstName", firstName).first();
  }
  
  public boolean checkPassword(String password) {
    return this.password.equals(password);
  }
  public String toString()
  {
    return firstName + " "+lastName ;
  }
}