package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.sun.jndi.url.corbaname.corbanameURLContextFactory;
import com.sun.org.apache.xml.internal.security.c14n.implementations.Canonicalizer11_OmitComments;

import play.data.validation.MaxSize;
import play.db.jpa.Model;

@Entity
public class Post extends Model
{
  @ManyToOne
  public Blog          blog;

  @OneToMany(mappedBy="post" ,cascade = CascadeType.ALL)
  public List<Comment> comments;

  @ManyToMany
  public List<User>likes;
  
  public String        title;

  @Lob
  @MaxSize(1000)
  public String        content;

  public Date          postedAt;

  private String       author;

  public Post(String title, String content, String author)
    {
      this.author = author;
      this.title = title;
      this.content = content;
      this.postedAt = new Date();
      this.comments = new ArrayList<Comment>();
      this.likes=new ArrayList<User>();
    }

  public void addComment(Comment comment) {
    comment.post = this;
    comments.add(comment);
  }
  public void removeComment(Comment comment){
    comments.remove(comment);
  }
  
  public void addLike(User user){
    likes.add(user);
  }
  
  public void removeLike(User user){
    likes.remove(user);
  }
  
  public boolean checkLiked(User user){
    if ( likes.contains(user)){
      return true;
    }
    else{
      return false;
    }
  }

  @Override
  public String toString() {
    return title;
  }
}