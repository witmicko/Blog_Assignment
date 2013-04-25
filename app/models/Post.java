package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.sun.jndi.url.corbaname.corbanameURLContextFactory;
import com.sun.org.apache.xml.internal.security.c14n.implementations.Canonicalizer11_OmitComments;

import play.db.jpa.Model;

@Entity
public class Post extends Model
{
  // @ManyToOne
  // public Blog blog;

  @OneToMany(cascade = CascadeType.ALL)
  public List<Comment> comments;

  public String        title;

  @Lob
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
    }

  public void addComment(Comment comment) {
    // comment.post = this;
    comments.add(comment);
  }

  @Override
  public String toString() {
    return title;
  }
}