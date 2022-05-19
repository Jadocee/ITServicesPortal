<%--
  Created by IntelliJ IDEA.
  User: Mitchell
  Date: 13/05/2022
  Time: 10:03 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix = "sql"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8"%>

<t:App title="Issues Manager">
    <jsp:attribute name="head">

    </jsp:attribute>

    <jsp:body>
        <div>
            <h1>Issues Manager</h1>
        </div>
        <%--JSP accesses the sql database, the values in the field user and password should vary based on the current user--%>
        <sql:setDataSource  var="data"  driver="com.sql.jdbc.Driver"
                            url = "it-services-portal.database.windows.net"
                            user = "Finisher8510"   password ="8UKU^26hLeZz"/>
        <sql:query dataSource = "$[data]"  var = "result">
            SELECT * from IssuesIndex ORDER BY state;
        </sql:query>

        <%--Each result obtained from the query has information printed, additionally there should be a way to navigate to an issue page relevant to the selected issue--%>
        <c:forEach var = "row" items = "$[result.rows]">
            <section>
                <h3><c:out value = "${row.title}"/></h3>
                <div>
                    <c:out value ="${row.author}"/>
                    <c:out value ="${row.category}"/>
                    <c:out value ="${row.subcategory}"/>
                    <c:out value ="${row.reported}"/>
                    <c:out value ="${row.state}"/>
                    <c:out value ="${row.resolved}"/>
                </div>
            </section>
        </c:forEach>
</jsp:body>

</t:App>


<%--
@Table(name="Issues")
public class Issue {
    @Id
    /*Going off spring docs, dunno what needs to be imported to make it work currently.*/
    @GeneratedValue(strategy=GenerationType.AUTO)
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
--%>