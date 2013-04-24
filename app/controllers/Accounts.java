package controllers;

import models.User;
import play.Logger;
import play.db.jpa.GenericModel;
import play.mvc.Controller;

public class Accounts extends Controller
{
  public static void signup() {
    render();
  }

  public static void login() {
    render();
  }

  public static void logout() {
    session.clear();
    index();
  }

  public static void index() {
    render();
  }

  public static void register(String firstName, String lastName, int age,
      String email, String password, String password2) {
    Logger.info(firstName + " " + lastName +" " +age+ " " + email + " " + password);
    User user = new User(firstName, lastName, age, email, password);
    user.save();
    authenticate(user.email, user.password);
  }

  public static void authenticate(String email, String password) {
    User user = User.findByEmail(email);
    if (user == null || !user.checkPassword(password)) {
      index();
    }
    session.put("logged_in_userid", user.id);
    Home.index();
  }

  public static User getLoggedInUser() {
    User user = null;
    if (session.contains("logged_in_userid")) {
      String userId = session.get("logged_in_userid");
      user = User.findById(Long.parseLong(userId));
    } else {
      login();
    }
    return user;
  }
}