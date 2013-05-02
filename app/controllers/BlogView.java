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
    session.put("name", name);
    User user = Accounts.getLoggedInUser();
    List<Blog> blogs = Blog.all().fetch();
    if (!name.equalsIgnoreCase("all")) {
      List<Blog> searchResults = new ArrayList<Blog>();
      String str = name.toLowerCase();
      for (Blog b : blogs) {
        String bName = b.name;
        bName = bName.toLowerCase();
        if (bName.contains(str)) {
          Logger.info("blog name: " + b.name);
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
    User author = blog.author;
    List<Post> posts = blog.posts;
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
      String postAuthor = author.firstName + " " + author.lastName;
      post = new Post("Example Post", "Example Content", postAuthor);
      post.save();
      blog.addPost(post);
      blog.save();
      user.save();
    }
    session.put("index", index);
    Logger.info("BLOG ID " + blog.id);
    render(blog, post, user, author);
  }

  // this works
  public static void newBlog(String name) {
    if (name != null) {
      User user = Accounts.getLoggedInUser();
      Blog blog = new Blog(name);
      user.addBlog(blog);
      blog.save();
      user.save();
    }
    Home.index();
  }

  public static void newPost(String title2, String content, Long id) {
    User user = Accounts.getLoggedInUser();
    String author = user.firstName;
    Post post = new Post(title2, content, author);
    Blog blog = Blog.findById(id);
    blog.addPost(post);
    user.save();

    readBlog(id, 0);
  }

  public static void deletePost(Long postid, Long blogId) {
    User user = Accounts.getLoggedInUser();
    Logger.info("Post ID = " + postid);
    Blog blog = Blog.findById(blogId);

    Post post = Post.findById(postid);
    blog.removePost(post);
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
    User author = comment.author;
    post.removeComment(comment);
    author.removeComment(comment);
    author.save();
    post.save();
    blog.save();
    user.save();
    comment.delete();
    readBlog(blogId, 0);
  }

  public static void newComment(String content, Long postId, Long blogId) {

    Post post = Post.findById(postId);
    Blog blog = Blog.findById(blogId);
    User user = blog.author;
    User author = Accounts.getLoggedInUser();

    Comment comment = new Comment(content);
    post.addComment(comment);
    author.addComment(comment);
    comment.save();
    author.save();
    post.save();
    blog.save();
    user.save();

    readBlog(blogId, 0);
  }

  public static void subscribe(Long id) {
    User user = Accounts.getLoggedInUser();
    Blog blog = Blog.findById(id);
    user.save();
    User author = blog.author;
    blog.addSubscriber(user);
    blog.save();
    String str = session.get("name");
    findBlog(str);
  }

}