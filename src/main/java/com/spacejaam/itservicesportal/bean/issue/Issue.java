package com.spacejaam.itservicesportal.bean.issue;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.EnumSet;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 *
 */
public class Issue implements Serializable {

    private static String title;
    private static String desc;
    private static Category category;
    private String author;
    private State state;
    private EnumSet<Tag> tags;
    private Date reported;
    private ArrayList<Comment> comments; // TODO might be redundant

    public Issue() {
    }

    public static String getTitle() {
        return title;
    }

    public static String getDescription() {
        return desc;
    }

    public static Category getCategory() {
        return category;
    }
}

/*
@Table(name="Issues")
public class Issue {
    @Id
    private Integer id;

    @Column("author")
    private String  author;

    @Column("category")
    private String  category;

    @Column("subcategory")
    private String  subcategory;

    @Column("reported")
    private Date    reported;

    @Column("state")
    private String  state;

    @Column("resolved")
    private Date    resolved;

    @Column("description")
    private String  description;




    public  Integer getId(){
        return id;
    }
    public  void setId(Integer id){
        this.id =   id;
    }

    public  String  getAuthor(){
        return author;
    }
    public void setAuthor(String author){
        this.author=author;
    }

    public  String  getCategory(){
        return category;
    }
    public void setCategory(String category){
        this.category=category;
    }

    public  String  getSubcategory(){
        return subcategory;
    }
    public void setSubcategory(String subcategory){
        this.subcategory=subcategory;
    }

    public Date getReported(){
        return reported;
    }
    public void setReported(Date reported){
        this.reported=reported;
    }

    public  String  getAuthor(){
        return author;
    }
    public void setAuthor(String author){
        this.author=author;
    }

    public Date getResolved(){
        return resolved;
    }
    public void setResolved(Date resolved){
        this.resolved=resolved;
    }

    public String   getDescription(){
        return  description;
    }
    public  void    setDescription(String description){
        this.description=description;
    }
}
*/
