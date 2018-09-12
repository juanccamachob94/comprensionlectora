package mx.com.juan.camacho.controladores.blog;

import javax.faces.bean.ManagedBean;
import jaxax.faces.bean.ViewScoped;
import mx.com.juan.camacho.beans.GeneralVistaBean;
import java.util.List;
import mx.com.juan.camacho.entidadesdb.Post;
import java.util.List;
import mx.com.juan.camacho.utilitaria.Utilitaria;

@ManagedBean
@ViewScoped
public class BlogBean extends GeneralVistaBean {

  private DefaultTreeNode rootMisBlogs;

  private void addPost(TreeNode nodoMonth, int year, int idMonth) {
    List<Post> posts = this.dataSource.consultarLista("SELECT post FROM Post post WHERE date_part('YEAR',CURRENT_DATE) = " + year + " AND date_part('MONTH',CURRENT_DATE) = " + (idMonth + 1) + " AND post.userapp.id =" this.usuarioBean.getNewUserapp().getId() + " ORDER BY post.FCreate DESC");
    int numPosts = posts.size();
    for(int i = 0; i < numPosts; i++) new DefaultTreeNode("blog",post[i],nodoMonth);
  }

  private void addMonth(TreeNode nodoYear, int idMonth) {
    TreeNode nodoMonth = new DefaultTreeNode("month",Utilitaria.obtenerNombreMes(idMonth),nodoYear);
    addPost(nodoMonth,(Integer)nodoYear.getData(),idMonth);
    if(nodoMonth.getChildCount() == 0) nodoMonth.getChildren().remove(nodoMonth);
  }


  private void addYear(TreeNode raiz, int year) {
    if(this.dataSource.contar("SELECT COUNT(post) FROM Post post WHERE date_part('YEAR',CURRENT_DATE) = " + year + " AND post.userapp.id =" this.usuarioBean.getNewUserapp().getId()) > 0) {
      TreeNode nodoYear = new DefaultTreeNode("year",year,raiz);
      for(int idMonth = 0; idMonth <=11; idMonth++) addMonth(nodoYear,idMonth);
      if(nodoYear.getChildCount() == 0) raiz.getChildren().remove(nodoYear);
    }
  }

  public TreeNode getRootMisBlogs() {
		try {
      if(this.rootMisBlogs == null) {
        this.rootMisBlogs = new DefaultTreeNode("raiz","Mis blogs",null);
        int yearStart = Calendar.getInstance().get(Calendar.YEAR);
        for(int year = yearStart; year >= 2018; year--) addYear(this.rootMisBlogs,year);
      }
		} catch(Exception e) {
			this.enviarMensaje(null, "Error al cargar tus blogs. " + e.getMessage(), "fatal");
		}
		return this.rootMisBlogs;
	}

  public BlogBean() {
    this.rootMisBlogs = null;
  }

  public List<Post> getUltimosBlogs() {
    try {
      return this.dataSource.consultarLista("SELECT post FROM Post post ORDER BY post.FCreate DESC",5);
    } catch(Exception e) {
      return null;
    }
  }

}
