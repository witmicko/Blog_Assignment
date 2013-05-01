package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

@Entity
public class Blog extends Model
{
  public String name;
  
  @ManyToOne
  public User author;
  
  @OneToMany(mappedBy="blog", cascade=CascadeType.ALL)
  public List<Post> posts;
  
 // public User author;

  
  public Blog(String name)
  {
    this.name = name;
    this.posts = new ArrayList<Post>();
    
  }
  
  public void addPost(Post post){
    post.blog = this;
    post.save();
    posts.add(post);
    
  }
  public void removePost(Post post){
    this.posts.remove(post);
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