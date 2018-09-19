package mx.com.juan.camacho.controladores.test;

import mx.com.juan.camacho.beans.GeneralVistaBean;
import mx.com.juan.camacho.entidadesdb.UserappQuestion;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import mx.com.juan.camacho.entidadesdb.Question;
import mx.com.juan.camacho.entidadesdb.Test;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.Date;

@ManagedBean
@ViewScoped
public class TestBean extends GeneralVistaBean {

  private Test test;
  private List<UserappQuestion> userappQuestions;

  public TestBean() {
  }
  
  public Test getTest() {
	  return this.test;
  }
  
  public boolean isTestEvaluado() {
	  try {
		  return this.dataSource.contar("SELECT COUNT(test.id) FROM Test test, Question question, UserappQuestion userappQuestion WHERE question.test.id = test.id AND test.id = " + this.test.getId() + " AND userappQuestion.question.id = question.id AND userappQuestion.userapp.id = " + this.usuarioBean.getUserappSession().getId() + " AND userappQuestion.earnedPoints IS NOT NULL") > 0;
	  } catch(Exception e) {
		  this.mostrarModal("No se pudo revisar si el test ya fue o no evaluado. " + e.getMessage(),"fatal");
		  return false;
	  }
  }
  
  public void setTest(Test test) {
	  this.test = test;
  }

  public List<UserappQuestion> getUserappQuestions() {
    return this.userappQuestions;
  }

  public void setUserappQuestions(List<UserappQuestion> questions) {
    this.userappQuestions = questions;
  }

  public void setIdTest(int idTest) {
    try {
      this.test = (Test)this.dataSource.consultarObjeto("SELECT test FROM Test test WHERE test.id = " + idTest);
      this.userappQuestions = new ArrayList();
      Set<Question> questions = (Set<Question>)test.getQuestions();
      UserappQuestion userappQuestion;
      for (Question question : questions) {
        userappQuestion = new UserappQuestion();
        userappQuestion.setUserapp(this.usuarioBean.getUserappSession());
        userappQuestion.setQuestion(question);
        this.userappQuestions.add(userappQuestion);
      }
    } catch(Exception e) {
      this.mostrarModal("No se pudo consultar el examen " + idTest + ". " + e.getMessage(),"fatal");
    }
  }

  public int getIdTest() {
    try {
      return this.test.getId();
    } catch(Exception e) {
      return 0;
    }
  }

  public boolean isExamenPresentado() {
    try {
    	return this.dataSource.contar("SELECT COUNT(test.id) FROM Test test, Question question, UserappQuestion userappQuestion WHERE question.test.id = test.id AND test.id = " + this.test.getId() + " AND userappQuestion.question.id = question.id AND userappQuestion.userapp.id = " + this.usuarioBean.getUserappSession().getId()) > 0;
    } catch(Exception e) {
      this.mostrarModal("Error en el sistema al revisar si el examen ya fue presentado. " + e.getMessage(),"fatal");
      return false;
    }
  }

  public Integer getPuntosObtenidosExamen() {
    try {
      return this.dataSource.contar("SELECT COUNT(uq.earnedPoints) FROM UserappQuestion uq WHERE uq.userapp.id = " + this.usuarioBean.getUserappSession().getId() + " AND uq.question.test.id = " + this.test.getId());
    } catch(Exception e) {
      return null;
    }
  }

  public void finalizarExamen() {
    try {
      UserappQuestion userappQuestion;
      Date fechaHoy = new Date();
      int t = this.userappQuestions.size();
      this.dataSource.iniciarTransaccion();
      for(int i = 0; i < t; i++) {
        userappQuestion = this.userappQuestions.get(i);
        userappQuestion.setDateOfGrades(fechaHoy);
        this.dataSource.insertar(userappQuestion);
      }
      this.dataSource.finalizarTransaccion();
      this.mostrarModal("Examen presentado exitosamente","info");
    } catch(Exception e) {
      this.enviarMensaje(null,"No se pudo presentar el examen. " + e.getMessage(),"fatal");
    }
  }

  public int getExamenesPresentados() {
    try {
      return this.dataSource.contar("SELECT COUNT(uq.question.test.id) FROM UserappQuestion uq, Question q WHERE q.id = uq.question.id AND uq.userapp.id = " + this.usuarioBean.getUserappSession().getId() + " GROUP BY uq.question.test.id");
    } catch(Exception e) {
      return 0;
    }
  }

  public int getPuntosObtenidos() {
    try {
      return this.dataSource.contar("SELECT COUNT(uq.earnedPoints) FROM UserappQuestion uq WHERE uq.userapp.id = " + this.usuarioBean.getUserappSession().getId());
    } catch(Exception e) {
      return 0;
    }
  }






}
