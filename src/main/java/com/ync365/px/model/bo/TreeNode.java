package com.ync365.px.model.bo;

import java.util.List;

public class TreeNode {
    
    private String id ;
    
    private String text;
    
    private Boolean children;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getChildren() {
        return children;
    }

    public void setChildren(Boolean children) {
        this.children = children;
    }

}
