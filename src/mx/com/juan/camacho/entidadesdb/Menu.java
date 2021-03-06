package mx.com.juan.camacho.entidadesdb;
// Generated 2/10/2018 11:28:17 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Menu generated by hbm2java
 */
public class Menu  implements java.io.Serializable {


     private int id;
     private Menu menu;
     private String name;
     private String page;
     private String icon;
     private int groupId;
     private int position;
     private Set menus = new HashSet(0);

    public Menu() {
    }

	
    public Menu(int id, String name, String icon, int groupId, int position) {
        this.id = id;
        this.name = name;
        this.icon = icon;
        this.groupId = groupId;
        this.position = position;
    }
    public Menu(int id, Menu menu, String name, String page, String icon, int groupId, int position, Set menus) {
       this.id = id;
       this.menu = menu;
       this.name = name;
       this.page = page;
       this.icon = icon;
       this.groupId = groupId;
       this.position = position;
       this.menus = menus;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public Menu getMenu() {
        return this.menu;
    }
    
    public void setMenu(Menu menu) {
        this.menu = menu;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public String getPage() {
        return this.page;
    }
    
    public void setPage(String page) {
        this.page = page;
    }
    public String getIcon() {
        return this.icon;
    }
    
    public void setIcon(String icon) {
        this.icon = icon;
    }
    public int getGroupId() {
        return this.groupId;
    }
    
    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }
    public int getPosition() {
        return this.position;
    }
    
    public void setPosition(int position) {
        this.position = position;
    }
    public Set getMenus() {
        return this.menus;
    }
    
    public void setMenus(Set menus) {
        this.menus = menus;
    }




}


