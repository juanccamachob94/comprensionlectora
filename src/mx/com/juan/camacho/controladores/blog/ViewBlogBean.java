package mx.com.juan.camacho.controladores.blog;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import mx.com.juan.camacho.entidadesdb.Blog;

@ManagedBean
@ViewScoped
public class ViewBlogBean extends mx.com.juan.camacho.beans.GeneralVistaBean {

  private Blog blog;

  public ViewBlogBean() {
  }

  public void setIdBlog(int idBlog) {
    try {
      this.blog = (Blog)this.dataSource.consultarObjeto("SELECT blog FROM Blog blog WHERE blog.id =" + idBlog);
    } catch(Exception e) {
      this.mostrarModal("No se pudo consultar el blog " + idBlog + ". " + e.getMessage(),"fatal");
    }
  }

  public int getIdBlog() {
	  try {
		  return this.blog.getId();		  
	  } catch(Exception e) {
		  return 0;
	  }
    
  }

  public void setBlog(Blog blog) {
    this.blog = blog;
  }

  public Blog getBlog() {
    return this.blog;
  }

}
