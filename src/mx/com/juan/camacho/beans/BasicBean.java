package mx.com.juan.camacho.beans;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class BasicBean extends DBBean<mx.com.juan.camacho.entidadesdb.Blog> {

	public BasicBean() {
	}
	
	public int getCantidad() {
		try {
			return this.dataSource.contarQ("SELECT COUNT(*) FROM BLOG");
		} catch(Exception e) {
			System.out.println("FALLA " + e.getMessage());
			return 0;
		}
	}
}
