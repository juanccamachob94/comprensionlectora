package mx.com.juan.camacho.controladores.blog;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class NewBlogBean extends GeneralVistaBean {

  private Blog newblog;

  public Blog getNewBlog() {
    return this.newblog;
  }

  public void setNewBlog(Blog newblog) {
    this.newblog = newblog;
  }

  public NewBlogBean() {
    this.newblog = new Blog();
  }

  public void crearBlog() {
    try {
      this.newblog.setUserapp(this.usuarioBean.getUserappSession());
      this.dataSource.insertar(this.newblog);
      this.newblog = null;
      this.mostrarPagina("inicio/inicioSistema");
    } catch(Exception e) {
      this.enviarMensaje(null,"No se pudo crear el blog","fatal");
    }
  }
}
