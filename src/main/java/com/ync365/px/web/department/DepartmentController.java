package com.ync365.px.web.department;

import com.ync365.px.entity.Department;
import com.ync365.px.model.bo.TreeNode;
import com.ync365.px.service.department.DepartmentService;
import com.ync365.px.web.project.ProjectController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DepartmentController {

    public final Logger logger = LoggerFactory.getLogger(ProjectController.class);

    @Autowired
    private DepartmentService departmentService;

    @RequestMapping(value = "/department/list/{parentId}")
    public String getDepartment (@PathVariable  String parentId , Model model)
    {
        model.addAttribute("id",parentId);
        return  "/department/list";
    }

    @RequestMapping(value = "/department/getChildren")
    @ResponseBody
    public List<TreeNode> getDepartmentByParentId (String id) {
//        departmentService.getDepartmentByParentId(parentId);
        List<Department> departments  = new ArrayList<Department> ();
        if(id.equals("#")) {
            departments = departmentService.getDepartmentByParentId(-1l);
        } else {
            departments = departmentService.getDepartmentByParentId(Long.valueOf(id));
        }
        List<TreeNode> treeNodes = new ArrayList<TreeNode> ();



        for(Department department : departments ) {
            TreeNode treeNode = new TreeNode();
            treeNode.setId(String.valueOf(department.getId()));
            treeNode.setText(department.getName());
            treeNode.setChildren(department.getLeaf() == 1 ? false : true);
            treeNodes.add(treeNode);

        }
        return  treeNodes;
    }
}
