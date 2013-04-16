package controllers;

import java.util.List;

import models.Blog;
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

  
}