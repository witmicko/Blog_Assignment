package controllers;

import java.util.ArrayList;
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

  public static void index(String name) {
    User user = Accounts.getLoggedInUser();
    List<User> users = new ArrayList<User>();
    User usr = null;
    if (name.equalsIgnoreCase("all")) {
      users = User.findAll();
    } else {
      usr = User.findByName(name);
      users.add(usr);
    }
    render(user, users);
  }

  public static void findBlog(String name) {
    User user = Accounts.getLoggedInUser();
    List<Blog> blogs = Blog.all().fetch();
    if (name.equalsIgnoreCase("all")) {
      Logger.info("all blogs");
      
    }else {
      List<Blog> searchResults = new ArrayList<Blog>();
      Logger.info("else loop Name: " + name);
      String str = name.toLowerCase();
      Logger.info("str: "+str );
      for(Blog b:blogs){
        String bName = b.name;
        bName = bName.toLowerCase();
        if(bName.contains(str)){
          Logger.info("blog name: "+b.name);
          searchResults.add(b);
        }
      }
      blogs = searchResults;
    }
    render(user, blogs);
  }

  // this works
  public static void readBlog(Long id, int iterate) {
    User user = Accounts.getLoggedInUser();
    int index;
    if (session.get("index") == null) {
      index = 0;
      session.put("index", index);
    }
    index = Integer.parseInt(session.get("index"));
    index += iterate;

    Blog blog = Blog.findById(id);
    List<Post> posts = blog.posts; // get user`s posts only
    Collections.reverse(posts);
    Post post = null;
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

    } else {
      user = Accounts.getLoggedInUser();
      String author = user.firstName + " " + user.lastName;
      post = new Post("Example Post", "Example Content", author);
      blog.addPost(post);
      blog.save();
      user.save();
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
    Logger.info("author:" + author + " content: " + content);
    post.comments.add(comment);
    post.save();
    blog.save();
    user.save();

    readBlog(blogId, 0);
  }

}