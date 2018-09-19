package mx.com.juan.camacho.controladores.test;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import mx.com.juan.camacho.entidadesdb.Test;
import mx.com.juan.camacho.entidadesdb.Blog;
import mx.com.juan.camacho.entidadesdb.Question;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;

@ManagedBean
@ViewScoped
public class NewTestBean extends mx.com.juan.camacho.beans.GeneralVistaBean {

  private Test newtest;
  private Question newquestion;
  private List<Question> questions;
  private float points;

  public float getPoints() {
    return this.points;
  }

  public void setPoints(float points) {
    this.points = points;
  }

  public NewTestBean() {
  }


  public Test getNewtest() {
    return this.newtest;
  }

  public void setNewtest(Test newtest) {
    this.newtest = newtest;
  }

  public Question getNewquestion() {
    return this.newquestion;
  }

  public void setNewquestion(Question newquestion) {
    this.newquestion = newquestion;
  }

  public List<Question> getQuestions() {
    return this.questions;
  }

  public void setQuestions(List<Question> questions) {
    this.questions = questions;
  }

  private void instanciarNewQuestion() {
     this.newquestion = new Question();
     this.newquestion.setFCreate(new java.util.Date());
     this.newquestion.setType("OPEN");
  }

  public void agregarPregunta() {
    try {
      if(this.points + this.newquestion.getPoints().floatValue() <= 100) {
    	  this.questions.add(this.newquestion);
    	  this.points += this.newquestion.getPoints().floatValue(); 
      }else this.enviarMensaje(null,"Has superado el máximo de puntos en el examen","error");
    } catch(Exception e) {
      this.enviarMensaje(null,"No se pudo agregar la pregunta al examen. " + e.getMessage(),"fatal");
    }
    instanciarNewQuestion();
  }

  public void setIdBlog(int idBlog) {
    try {
      Blog blog = (Blog) this.dataSource.consultarObjeto("SELECT blog FROM Blog blog WHERE blog.id = " + idBlog);
      if(this.usuarioBean.getUserappSession().getId() == blog.getUserapp().getId()) {
        this.points = 0;
        this.newtest = new Test();
        this.newtest.setBlog(blog);
        this.questions = new ArrayList<Question>();
        instanciarNewQuestion();
      } else  this.enviarMensaje(null,"No eres el autor de este blog","error");
    } catch(Exception e) {
      this.mostrarModal(null,"No se pudo construir el test. " + e.getMessage(),"fatal");
    }
  }

  public void publicarExamen() {
    try {
      if(this.points == 100) {
          this.dataSource.iniciarTransaccion();
          this.newtest.setFCreate(new Date());
          this.dataSource.insertar(this.newtest);
          this.dataSource.recargar(this.newtest);
          int t = this.questions.size();
          Question question;
          for(int i = 0; i < t; i++) {
            question = this.questions.get(i);
            question.setTest(this.newtest);
            this.dataSource.insertar(question);
          }
          this.dataSource.finalizarTransaccion();
          this.mostrarPagina("blog/viewblog","idBlog=" + Integer.toString(this.newtest.getBlog().getId()));
      } else this.enviarMensaje(null,"La cantidad de puntos del examen debe ser igual a 100","error");

    } catch(Exception e) {

      this.enviarMensaje(null,"No se pudo crear el examen. " + e.getMessage(),"fatal");
    }
  }
  
  public void eliminarPregunta(Question newquestion) {
	  try {
		  this.questions.remove(newquestion);
	  } catch(Exception e) {
		  this.mostrarModal("No se pudo eliminar la pregunta","fatal");
	  }
  }

  public int getIdBlog() {
    try {
      return this.newtest.getBlog().getId();
    } catch(Exception e) {
      return 0;
    }
  }



}
