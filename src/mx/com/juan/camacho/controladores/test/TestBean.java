package mx.com.juan.camacho.controladores.test;

import mx.com.juan.camacho.beans.GeneralVistaBean;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class TestBean extends GeneralVistaBean {

  public TestBean() {
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
