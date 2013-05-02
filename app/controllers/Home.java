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
    //List<Blog> blogs = user.blogs;
    Logger.info("User theme: "+user.theme);
    List<Blog>blogs = Blog.findAll();

    render(user, user.blogs, blogs);
  }
  
  public static void setTheme(String theme){
    User user = Accounts.getLoggedInUser();
    user.theme = theme;
    Logger.info("User theme: "+user.theme);
    user.save();
    index();
    }
  
  
  public static void deleteBlog(Long id){
    User user = Accounts.getLoggedInUser();
    Blog blog = Blog.findById(id);
    user.removeBlog(blog);
    user.save();
    blog.delete();
    index();
  }
  
  public static void unSubscribe(Long id){
    User user = Accounts.getLoggedInUser();
    Blog blog = Blog.findById(id);
    blog.removeSub(user);
    blog.save();
    user.save();
    index();
  }
}