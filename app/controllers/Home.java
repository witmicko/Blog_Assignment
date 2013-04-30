package controllers;

import java.util.List;

import models.Blog;
import models.Post;
import models.User;
import play.Logger;
import play.db.jpa.GenericModel;
import play.mvc.Controller;

public class Home extends Controller
{
  public static void index() {
    User user = Accounts.getLoggedInUser();
    List<Blog> blogs = user.blogs;
    Logger.info("User theme: "+user.theme);

    render(user, blogs);
  }
  
  public static void setTheme(String theme){
    User user = Accounts.getLoggedInUser();
    Logger.info("1theme: "+theme);
    user.theme = theme;
    Logger.info("User theme: "+user.theme);
    user.save();
    index();
    }
  
  
  public static void deleteBlog(Long id){
    User user = Accounts.getLoggedInUser();
    List<Blog> blogs = user.blogs;
    Blog blog = Blog.findById(id);
    blogs.remove(blog);
    List<Post>posts = blog.posts;
    blog.posts.removeAll(posts);
    user.save();
    blog.delete();
    index();
  }
  //public 
}