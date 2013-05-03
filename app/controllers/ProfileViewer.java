package controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import models.Blog;
import models.Comment;
import models.Post;
import models.User;
import play.Logger;
import play.db.jpa.Blob;
import play.db.jpa.GenericModel;
import play.mvc.Controller;

public class ProfileViewer extends Controller
{

  public static void index(Long id) {
    User user = Accounts.getLoggedInUser();
    User profile= User.findById(id);
    
    render(user, profile);
  }
  
  public static void getPicture(Long id) {
    User user = User.findById(id);
    Blob picture = user.profilePicture;
    if (picture.exists()) {
      response.setContentTypeIfNotSet(picture.type());
      renderBinary(picture.get());
    }
  }


  public static void uploadPicture(Blob picture) {
    User user = Accounts.getLoggedInUser();
    user.profilePicture = picture;
    user.save();
    Home.index();
  }

  public static void getThumbnail(Long id) {
    User user = User.findById(id);
    Blob picture = user.thumbnailPicture;
    if (picture.exists()) {
      response.setContentTypeIfNotSet(picture.type());
      renderBinary(picture.get());
    }
  }

  public static void uploadThumbnail(Blob picture) {
    User user = Accounts.getLoggedInUser();
    user.thumbnailPicture = picture;
    user.save();
    Home.index();
  }

}