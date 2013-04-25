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
    render(user, blogs);
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

  
}