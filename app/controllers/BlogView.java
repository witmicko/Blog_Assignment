package controllers;

import java.util.Collections;
import java.util.List;

import models.Blog;
import models.Comment;
import models.Post;
import models.User;
import play.Logger;
import play.db.jpa.GenericModel;
import play.mvc.Controller;

public class BlogView extends Controller
{

  public static void index() {
    // User user = Accounts.getLoggedInUser();
    // List<Post> posts = user.blogs.; // get user`s posts only
    // Collections.reverse(posts);
    // render(user, posts);
  }

  public static void membersBlog(Long id) {
    // User user = GenericModel.findById(id);
    // Logger.info("Just visiting the page for " + user.firstName + ' '
    // + user.lastName);
    Blog blog = GenericModel.findById(id);
    List<Post> posts = blog.posts; // get user`s posts only
    Collections.reverse(posts);
    render(blog, posts);
  }

  // this works
  public static void readBlog(Long id, int iterate) {
    User user = Accounts.getLoggedInUser();
    int index;
    Logger.info("1session index:" + session.get("index"));
    if(session.get("index") == null){
    index = 0;
    session.put("index", index);
    Logger.info("2session index:" + session.get("index"));
    }
    index = Integer.parseInt(session.get("index"));
    index += iterate;
    Logger.info( "3index:" + index);

    
    Blog blog = Blog.findById(id);
    List<Post> posts = blog.posts; // get user`s posts only
    Collections.reverse(posts);
    Post post = null;
    Logger.info("4size:" + posts.size() + " index: " + index);
    if (posts.size() != 0) {
      if (index < 0) {
        index = posts.size() - 1;
        post = posts.get(index);
      } else if (index >= posts.size()) {
        index = 0;
        post = posts.get(index);
      } else {
        post = posts.get(index);
      }
      Logger.info(" 5index: " + index);
      Logger.info("6first if");

    } else {
      user = Accounts.getLoggedInUser();
      String author = user.firstName +" "+ user.lastName;
      post = new Post("Example Post", "Example Content", author);
      blog.addPost(post);
      blog.save();
      user.save();
//      render(blog, demoPost);
    }
    session.put("index", index);
    render(blog, post, user);
  }

  // this works
  public static void newBlog(String name) {
    if (name != null) {
      User user = Accounts.getLoggedInUser();
      Blog blog = new Blog(name);
      Logger.info("Name:" + name + blog.id);
      user.addBlog(blog);
      user.save();
    }
    Home.index();
  }

  public static void newPost(String title2, String content, Long id) {
    User user = Accounts.getLoggedInUser();
    String author = user.firstName;
    Post post = new Post(title2, content, author);
    Logger.info("title:" + title2 + " content:" + content + post.id + " "
        + user.id);

    Blog blog = Blog.findById(id);
    blog.addPost(post);
    blog.save();
    user.save();

    readBlog(id, 0);
  }

  public static void deletePost(Long postid, Long blogId) {
    User user = Accounts.getLoggedInUser();
    Logger.info("Post ID = " + postid);
    Blog blog = Blog.findById(blogId);

    Post post = Post.findById(postid);
    blog.posts.remove(post);
    blog.save();
    user.save();
    post.delete();
    readBlog(blogId, 0);
  }
  
  public static void deleteComment(Long blogId, Long postid, Long commentId) {
    User user = Accounts.getLoggedInUser();
    Logger.info("Post ID = " + postid);
    Blog blog = Blog.findById(blogId);
    Post post = Post.findById(postid);
    Comment comment = Comment.findById(commentId);
    post.comments.remove(comment);
    post.save();
    blog.save();
    user.save();
    comment.delete();
    readBlog(blogId, 0);
  }
  
  public static void newComment(String content, Long postId, Long blogId) {
    Post post = Post.findById(postId);
    Blog blog = Blog.findById(blogId);
    User user = Accounts.getLoggedInUser();
    String author = user.firstName;
    Comment comment = new Comment(author, content);
    Logger.info("author:" + author+ " content: " + content  );
    post.comments.add(comment);
    post.save();
    blog.save();
    user.save();

    //Blog blog = Blog.findById(id);
    //blog.addPost(post);

    readBlog(blogId, 0);
  }
  
}