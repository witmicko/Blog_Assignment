package models;

import java.util.ArrayList;
import java.util.List;
//import javax.persistence.CascadeType;
//import javax.persistence.Entity;
//import javax.persistence.OneToMany;






import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import play.db.jpa.Blob;
import play.db.jpa.Model;

@Entity
@Table(name = "my_user")
public class User extends Model
{
  @OneToMany(mappedBy="author", cascade=CascadeType.ALL)
  public List<Blog> blogs; //stores blogs
  
  @OneToMany(mappedBy="author", cascade=CascadeType.ALL)
  public List<Comment>comments;
  
//  @OneToMany(mappedBy="author", cascade=CascadeType.ALL)
//  public List<Post>posts;
  
  public String     firstName;
  public String     lastName;
  public int        age;
  public String     email;
  public String     password;
  public Blob       profilePicture;
  public Blob       thumbnailPicture;
  public String      theme;

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
    this.comments = new ArrayList<Comment>();
    //this.posts = new ArrayList<Post>();
    this.theme = "@{'/public/bootstrap/themes/google/google-bootstrap.css'}";
  }
  public void setTheme(String theme){
    this.theme = theme;}
  
  /**
   * Method addBlog() add blog to the list of blogs
   * @param blog
   */
  public void addBlog(Blog blog){
    blog.author=this;
    blogs.add(blog);
  }
  public List<Blog> getBlogs(Long blogId) {
    return blogs;
  }
  
  public void removeBlog(Blog blog){
    blogs.remove(blog);
  }

  public static User findByEmail(String email) {
    return find("email", email).first();
  }
  
  public static User findByName(String firstName)
  {
    return find("firstName", firstName).first();
  }
  public int getBlogIndex(Blog blog){
    return blogs.indexOf(blog)+1;
  }
  
  public void addComment(Comment comment){
    comment.author = this;
    comments.add(comment);
  }
  
  public void removeComment(Comment comment){
    comments.remove(comment);
  }
  public boolean checkPassword(String password) {
    return this.password.equals(password);
  }
  public String toString()
  {
    return firstName + " "+lastName ;
  }
}