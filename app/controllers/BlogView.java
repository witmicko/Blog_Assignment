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
  static Long currentId;
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
    currentId=id;
    Blog blog = Blog.findById(id);
    List<Post> posts = blog.posts; // get user`s posts only
    Collections.reverse(posts);
    render(blog, posts);
  }
  //this works
  public static void newBlog(String name) {
    if(name!=null){
    User user = Accounts.getLoggedInUser();
    Blog blog = new Blog(name);
    Logger.info("Name:" + name+ blog.id);
    user.addBlog(blog);
    user.save();
    }
    Home.index();
  }
  
  
  
  public static void newPost(String title, String content) {
    User user = Accounts.getLoggedInUser();
    String author = user.firstName;
    Post post = new Post(title, content, author);
    Logger.info("title:" + title + " content:" + content + post.id + " "
        + user.id);
    
    Blog blog = Blog.findById(currentId);
    blog.addPost(post);
    blog.save();
    user.save();
    
    readBlog(currentId);
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