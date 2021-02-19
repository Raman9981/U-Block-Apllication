package com.upgrad.ublog.dao;

/**
 * TODO: 3.19. Implement the PostsDAO interface and implement this class using the Singleton pattern.
 *  (Hint: Should have a private no-arg Constructor, a private static instance attribute of type
 *  PostDAOImpl and a public static getInstance() method which returns the instance attribute.)
 * TODO: 3.20. Define the following methods and return null for each of them. You will provide a
 *  proper implementation for each of these methods, later in this project.
 *  a. findByEmailId()
 *  b. findByTag()
 *  c. findAllTags()
 *  d. findByPostId()
 *  e. deleteByPostId() (return false for this method for now)
 * TODO: 3.21. create() method should take post details as input and insert these details into
 *  the POST table. Return the same Post object which was passed as an input argument.
 *  (Hint: You should get the connection using the DatabaseConnection class)
 */

/**
 * TODO: 4.1. Implement findByEmailId() method which takes email id as an input parameter and
 *  returns all the posts corresponding to the email id from the Post table defined
 *  in the database.
 */

/**
 * TODO: 4.4. Implement the deleteByPostId() method which takes post id as an input argument and delete
 *  the corresponding post from the database. If the post was deleted successfully, then return true,
 *  otherwise, return false. (Hint: The executeUpdate() method returns the count of rows affected by the
 *  query.)
 * TODO: 4.5. Implement the findByPostId() method which takes post id as an input argument and return the
 *  post details from the database. If no post exists for the given id, then return a Post object
 *  without setting any of its attributes.
 */

import com.upgrad.ublog.db.Database;
import com.upgrad.ublog.dtos.Post;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO: 4.8. Implement findAllTags() method which returns a list of all tags in the POST table.
 * TODO: 4.9. Implement findByTag() method which takes "tag" as an input argument and returns all the
 *  posts corresponding to the input "tag" from the POST table defined in the database.
 */

public class PostDAOImpl implements PostDAO{
    private static PostDAOImpl instance=null;
    private  PostDAOImpl(){

    }
    public static PostDAOImpl getInstance(){
        if(instance==null){
            instance=new PostDAOImpl();
        }
        return instance;
    }

    @Override
    public Post findByPostId(int postId) throws SQLException {
        Connection connection = Database.getConnection();
        Statement statement = connection.createStatement();
        String sql="SELECT * FROM post WHERE postId= "+postId;
        ResultSet res= statement.executeQuery(sql);
        Post post=new Post();
        if( !res.next()){
            return null;
        }

        post.setPostId(res.getInt(1));
        post.setEmailId(res.getString(2));
        post.setTag(res.getString(3));
        post.setTitle(res.getString(4));
        post.setDescription(res.getString(5));
        post.setTimestamp(res.getString(6));



        return post;
    }

    @Override
    public boolean deleteByPostId(int postId) throws SQLException {
        Connection connection = Database.getConnection();
        Statement statement = connection.createStatement();
        String sql="DELETE FROM post WHERE postId= "+postId;
        int res=statement.executeUpdate(sql);

        return res>0;
    }

    @Override
    public List<Post> findByTag(String tag) throws SQLException {
        List<Post> posts =new ArrayList<>();
        Connection connection = Database.getConnection();
        Statement statement = connection.createStatement();
        String sql="SELECT * FROM post WHERE tag= '"+tag+"'";
        ResultSet res= statement.executeQuery(sql);
        Post post=new Post();
        while(res.next()){
            post.setPostId(res.getInt(1));
            post.setEmailId(res.getString(2));
            post.setTag(res.getString(3));
            post.setTitle(res.getString(4));
            post.setDescription(res.getString(5));
            post.setTimestamp(res.getString(6));
            posts.add(post);
        }

        return posts;
    }

    @Override
    public List<Post> findByEmailId(String emailId) throws SQLException {
        List<Post> posts= new ArrayList<>();
        Connection connection = Database.getConnection();
        Statement statement = connection.createStatement();
        String sql="SELECT * FROM post WHERE emailId= '"+emailId+"'";
        ResultSet res= statement.executeQuery(sql);
        Post post=new Post();
        while(res.next()){
            post.setPostId(res.getInt(1));
            post.setEmailId(res.getString(2));
            post.setTag(res.getString(3));
            post.setTitle(res.getString(4));
            post.setDescription(res.getString(5));
            post.setTimestamp(res.getString(6));
            posts.add(post);
        }
        return posts;
    }

    @Override
    public List<String> findAllTags() throws SQLException {
        List<String> tags=new ArrayList<>();
        Connection connection = Database.getConnection();
        Statement statement = connection.createStatement();
        String sql="SELECT tag FROM post";
        ResultSet res= statement.executeQuery(sql);
        while ((res.next())){
            String tag=res.getString(1);
            tags.add(tag);
        }

        return tags;
    }

    @Override
    public Post create(Post post) throws SQLException {
        Connection connection = Database.getConnection();
        Statement statement = connection.createStatement();
        String sql = "INSERT INTO post (postId, emailId, tag, title, description, timestamp) VALUES ("+
                post.getPostId() + ", '"+
                post.getEmailId() +  "', '"+
                post.getTag()+"', '"+
                post.getTitle()+"', '"+
                post.getDescription()+"', '"+
                post.getTimestamp()+
                "')";
        statement.executeUpdate(sql);

        return post;
    }

}
