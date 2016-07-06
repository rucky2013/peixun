package com.ync365.px.shiro;

import java.util.List;
/**
 * 菜单模型
 * @author 
 *
 */
public class Menu implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String text;
	private String href;
	private boolean checked=false;
	private List<Menu> children = null;
	
	public Menu(){
		
	}
	public Menu(int id, String text, String href, List<Menu> children) {
		super();
		this.id = id;
		this.text = text;
		this.href = href;
		this.children = children;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public List<Menu> getChildren() {
		return children;
	}
	public void setChildren(List<Menu> children) {
		this.children = children;
	}
	public void addChildren(Menu menu){
		this.children.add(menu);
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	
}
