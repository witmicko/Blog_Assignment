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

 
public class UsersBrowser  extends Controller
{
  //public static void index(List<User> usrs) {
  public static void index(String name) {
    User user = Accounts.getLoggedInUser();
    List<User>users = new ArrayList<User>();
    User usr = null;
    if (name.equalsIgnoreCase("all")){
      users = User.findAll();
    }
    else{
      usr = User.findByName(name);
      //try{
          users.add(usr);}
      //catch(NullPointerException e){
      //}
      //}
    render(user,users);
  }
  
  public static void findUser(String name){
    String str = name;
    User user = User.findByName(str);
    user.save();
    List<User>users = new ArrayList<User>();
    users.add(user);
    index(name);
  }
}
