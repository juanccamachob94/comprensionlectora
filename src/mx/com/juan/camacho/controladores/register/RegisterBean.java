package mx.com.juan.camacho.controladores.register;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import mx.com.juan.camacho.controladores.GeneralVistaBean;
import mx.com.juan.camacho.entidadesdb.Userapp;
import mx.com.juan.camacho.controladores.login.UsuarioBean;


@ManagedBean
@ViewScoped
public class RegisterBean extends GeneralVistaBean {


  private Userapp newUserapp;
  private String confirm_password;

  private UploadedFile archivoImagen;

  public UploadedFile getArchivoImagen() {
    return this.archivoImagen;
  }

  public void setArchivoImagen(UploadedFile archivoImagen) {
    this.archivoImagen = archivoImagen;
  }

  public String getConfirmPassword() {
    return this.confirm_password;
  }

  public void setConfirmPassword(String confirm_password) {
    this.confirm_password = confirm_password;
  }

  @ManagedProperty(value="#{usuarioBean}")
  private UsuarioBean usuarioBean;

  public UsuarioBean getUsuarioBean() {
    return this.usuarioBean;
  }

  public void setUsuarioBean(UsuarioBean usuarioBean) {
    this.usuarioBean = UsuarioBean;
  }

  @ManagedProperty(value="#{dataSource}")
  private DataSource dataSource;

  public Userapp getNewUserapp() {
    return this.newUserapp;
  }

  public void setNewUserapp(Userapp userapp) {
    this.newUserapp = userapp;
  }

  public RegisterBean() {
    this.newUserapp = new Userapp();
  }

  public DataSource getDataSource() {
    return this.dataSource;
  }

  public void setDataSource(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  public void register() {
    try {
      if(!(this.dataSource.consultarLista("SELECT userapp FROM Userapp userapp WHERE userapp.userappname = '" + this.newUserapp.getUserappname() + "'").isEmpty())) {
        this.sendMessage(null,"El nombre de usuario ya existe","error");
        reiniciarPasswords();
      } else {
        if(!(Pattern.compile("^[a-zA-Z0-9]+((_|\\-)?[a-zA-Z0-9]+)*(\\.([a-zA-Z0-9]+|([a-zA-Z0-9]+(_|\\-)?[a-zA-Z0-9]+)+))*@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|([a-zA-Z0-9]+(\\.[a-zA-Z0-9]+)*))$").matcher(this.newUserapp.getEmail())).matches()) {
          this.sendMessage(null,"El email no es correcto");
          reiniciarPasswords();
        } else {
          if(this.newUserapp.getPassword().equals(this.confirm_password)) {
            reiniciarPasswords();
            this.sendMessage(null,"Las contraseñas no coinciden","error");
          } else {
            this.newUserapp.setPassword(this.encriptarMD5(this.newUserapp.getPassword()));
            if(this.archivoImagen != null) {
              Calendar calendario = Calendar.getInstance();
              String extension = GestorArchivos.extension(archivoImagen.getFileName());
              String nombreArchivo = "img-" + calendario.getTimeInMillis() +  (extension.equals("")? "" : "." + extension);
              String rutaCarpetaWebAplicacion = (((ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext()).getRealPath("/")).replace("\\", "/");
              String rutaImagenPerfil = rutaCarpetaWebAplicacion + "/resources/imgs/users";
              GestorArchivos.crearArchivo(new File(new File(rutaImagenPerfil + "/" + nombreArchivo),archivoSubido);
              this.newUserapp.setPicture(nombreArchivo);
            }
            this.dataSource.insertar(this.newUserapp);
            this.usuarioBean.startSessionData(this.newUserapp);
          }
        }
      }
    } catch(Exception e) {
      reiniciarPasswords();
      this.sendMessage(null,"No se puede registrar al usuario. " + e.getMessage(),"fatal");
    }
  }

  private void reiniciarPasswords() {
    this.newUserapp.setPassword(null);
    this.confirm_password = null;
  }

  /**
     * Permite mostrar una respuesta en la parte superior derecha del navegador
     * @param boton
     * @param mensaje
     * @param tipo
     */
    private void sendMessage(UIComponent boton, String mensaje, String tipo) {
        FacesContext context = FacesContext.getCurrentInstance();
        Severity s = null;
        System.out.println("Mensaje enviado al usuario(" + tipo + "): " + mensaje);
        switch (tipo) {
            case "info":
                s = FacesMessage.SEVERITY_INFO;
                break;
            case "warn":
                s = FacesMessage.SEVERITY_WARN;
                break;
            case "error":
                s = FacesMessage.SEVERITY_ERROR;
                break;
            case "fatal":
                s = FacesMessage.SEVERITY_FATAL;
                break;
        }
        context.addMessage(boton == null ? null : boton.getClientId(context), new FacesMessage(s, mensaje + ".", mensaje + "."));
    }




}
