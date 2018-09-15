package mx.com.juan.camacho.controladores.blog;

import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import mx.com.juan.camacho.controladores.diagram.DiagramBean;
import mx.com.juan.camacho.entidadesdb.Blog;
import java.util.List;
import java.util.ArrayList;
@ManagedBean
@ViewScoped
public class ViewBlogBean extends mx.com.juan.camacho.beans.GeneralVistaBean {

  private Blog blog;
  private String textoSeleccionado;
  private String content;
  private List<String> concepts;
  @ManagedProperty(value="#{diagramBean}")
  private DiagramBean diagramBean;
  
  
  public DiagramBean getDiagramBean() {
	  return this.diagramBean;
  }
  
  public void setDiagramBean(DiagramBean diagramBean) {
	  this.diagramBean = diagramBean;
  }
  
  public String getContent() {
	  return this.content;
  }
  
  public void setContent(String content) {
	  this.content = content;
  }
  
  public void seleccionarTexto() {
	  if(!this.textoSeleccionado.contains("style=\"background-color:yellow\"") || (this.textoSeleccionado.contains("style=\"background-color:yellow\"") && !this.textoSeleccionado.contains("</span>"))) {
		  this.content = this.content.replace(this.textoSeleccionado,"<span style=\"background-color:yellow\">" + this.textoSeleccionado + "</span>");
		  this.concepts.add(this.textoSeleccionado);
	  }
		  
  }
  
  public void solicitarDiagrama() {
	  this.diagramBean.setConcepts(this.concepts);
	  try {
		  this.mostrarPagina("diagram/diagram");
	  } catch(Exception e) {}
  }
  
  public String getTextoSeleccionado() {
	  return this.textoSeleccionado;
  }
  
  public void setTextoSeleccionado(String textoSeleccionado) {
	  this.textoSeleccionado = textoSeleccionado;
  }

  public ViewBlogBean() {
	  this.concepts = new ArrayList<String>();
  }

  public void setIdBlog(int idBlog) {
    try {
      this.blog = (Blog)this.dataSource.consultarObjeto("SELECT blog FROM Blog blog WHERE blog.id =" + idBlog);
      this.content = this.blog.getContent();
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
