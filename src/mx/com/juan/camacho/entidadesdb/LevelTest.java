package mx.com.juan.camacho.entidadesdb;
// Generated 2/10/2018 11:28:17 PM by Hibernate Tools 4.3.1


import java.math.BigDecimal;

/**
 * LevelTest generated by hbm2java
 */
public class LevelTest  implements java.io.Serializable {


     private int id;
     private Level level;
     private Test test;
     private BigDecimal min;
     private BigDecimal max;

    public LevelTest() {
    }

    public LevelTest(int id, Level level, Test test, BigDecimal min, BigDecimal max) {
       this.id = id;
       this.level = level;
       this.test = test;
       this.min = min;
       this.max = max;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public Level getLevel() {
        return this.level;
    }
    
    public void setLevel(Level level) {
        this.level = level;
    }
    public Test getTest() {
        return this.test;
    }
    
    public void setTest(Test test) {
        this.test = test;
    }
    public BigDecimal getMin() {
        return this.min;
    }
    
    public void setMin(BigDecimal min) {
        this.min = min;
    }
    public BigDecimal getMax() {
        return this.max;
    }
    
    public void setMax(BigDecimal max) {
        this.max = max;
    }




}

