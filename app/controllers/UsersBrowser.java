package controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import models.Blog;
import models.Post;
import models.User;
import play.Logger;
import play.db.jpa.GenericModel;
import play.mvc.Controller;

public class UsersBrowser extends Controller
{
  public static void index(String name) {
    User user = Accounts.getLoggedInUser();
    List<User> users = User.all().fetch();
    if (!name.equalsIgnoreCase("all")) {
      List<User> searchResults = new ArrayList<User>();
      Logger.info("else loop Name: " + name);
      String str = name.toLowerCase();
      Logger.info("str: " + str);
      for (User u : users) {
        String uName = u.firstName + u.lastName;
        uName = uName.toLowerCase();
        if (uName.contains(str)) {
          Logger.info("user name: " + uName);
          searchResults.add(u);
        }
      }
      users = searchResults;
    }
    render(user, users, name);
  }

  public static void findUser(String name) {
    String str = name;
    User user = User.findByName(str);
    user.save();
    List<User> users = new ArrayList<User>();
    users.add(user);
    index(name);
  }
}
