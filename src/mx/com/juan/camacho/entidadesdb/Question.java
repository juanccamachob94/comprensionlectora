package mx.com.juan.camacho.entidadesdb;
// Generated 2/10/2018 11:28:17 PM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Question generated by hbm2java
 */
public class Question  implements java.io.Serializable {


     private int id;
     private Test test;
     private String content;
     private BigDecimal points;
     private Date FCreate;
     private String type;
     private Set options = new HashSet(0);
     private Set userappQuestions = new HashSet(0);

    public Question() {
    }

	
    public Question(int id, Test test, String content, BigDecimal points, String type) {
        this.id = id;
        this.test = test;
        this.content = content;
        this.points = points;
        this.type = type;
    }
    public Question(int id, Test test, String content, BigDecimal points, Date FCreate, String type, Set options, Set userappQuestions) {
       this.id = id;
       this.test = test;
       this.content = content;
       this.points = points;
       this.FCreate = FCreate;
       this.type = type;
       this.options = options;
       this.userappQuestions = userappQuestions;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public Test getTest() {
        return this.test;
    }
    
    public void setTest(Test test) {
        this.test = test;
    }
    public String getContent() {
        return this.content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    public BigDecimal getPoints() {
        return this.points;
    }
    
    public void setPoints(BigDecimal points) {
        this.points = points;
    }
    public Date getFCreate() {
        return this.FCreate;
    }
    
    public void setFCreate(Date FCreate) {
        this.FCreate = FCreate;
    }
    public String getType() {
        return this.type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    public Set getOptions() {
        return this.options;
    }
    
    public void setOptions(Set options) {
        this.options = options;
    }
    public Set getUserappQuestions() {
        return this.userappQuestions;
    }
    
    public void setUserappQuestions(Set userappQuestions) {
        this.userappQuestions = userappQuestions;
    }




}


