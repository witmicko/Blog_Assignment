package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Null;

import play.Logger;
import play.db.jpa.Model;

@Entity
public class Blog extends Model
{
  public String name;
  
  @ManyToOne
  public User author;
  
  @ManyToMany
  public List<User> readers;
  
  @OneToMany(mappedBy="blog", cascade=CascadeType.ALL)
  public List<Post> posts;
  
  public Blog(String name)
  {
    this.name = name;
    this.posts = new ArrayList<Post>();
    this.readers = new ArrayList<User>();
  }
  
  public void addPost(Post post){
    post.blog = this;
    post.save();
    posts.add(post);
  }
  
  public void removePost(Post post){
    this.posts.remove(post);
  }
  
  public void addSubscriber(User user){
    Logger.info("addReader "+ user.firstName);
    readers.add(user);
    Logger.info("ADDED READER "+ user.firstName);
  }
  
  public void removeSub(User user){
    readers.remove(user);
  }
  
  public boolean contains(User user){
    if (this.readers.contains(user)){
    return true;}
    else{
      return false;
    }
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