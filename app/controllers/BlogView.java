package controllers;

import java.util.Collections;
import java.util.List;

import models.Blog;
import models.Post;
import models.User;
import play.Logger;
import play.db.jpa.GenericModel;
import play.mvc.Controller;

public class BlogView extends Controller
{
  public static void index() {
//    User user = Accounts.getLoggedInUser();
//    List<Post> posts = user.blogs.; // get user`s posts only
//    Collections.reverse(posts);
//    render(user, posts);
  }

  public static void membersBlog(Long id) {
    //User user = GenericModel.findById(id);
    //Logger.info("Just visiting the page for " + user.firstName + ' '
    //    + user.lastName);
    Blog blog = GenericModel.findById(id);
    List<Post> posts = blog.posts; // get user`s posts only
    Collections.reverse(posts);
    render(blog, posts);
  }

//this works
  public static void readBlog(Long id){
    Blog blog = Blog.findById(id);
    List<Post> posts = blog.posts; // get user`s posts only
    Collections.reverse(posts);
    render(blog, posts);
  }
  public static void newPost(String title, String content, Long id) {
    User user = Accounts.getLoggedInUser();
   // String author = user.firstName;
    Post post = new Post(title, content);
    Logger.info("title:" + title + " content:" + content + post.id + " "
        + user.id);
    Blog blog = GenericModel.findById(id);
    blog.addPost(post);
    user.save();
    index();
  }

  public static void deletePost(Long postid) {
//    User user = Accounts.getLoggedInUser();
//    Logger.info("Post ID = " + postid);
//
//    Post post = GenericModel.findById(postid);
//    user.posts.remove(post);
//
//    user.save();
//    post.delete();
//
//    index();
  }

}