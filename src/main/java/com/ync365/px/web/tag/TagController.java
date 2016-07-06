package com.ync365.px.web.tag;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ync365.px.entity.PxProjectTag;
import com.ync365.px.model.Paginator;
import com.ync365.px.service.tag.TagService;

@Controller
public class TagController {
    
    @Autowired
    private TagService tagService;

    @RequestMapping(value = "/tag/list")
    public String classesList(Model model) {
        return "/tag/list";
    }

    @RequestMapping(value = "/listdata/tag/grid")
    @ResponseBody
    public Map<String, Object> classesGrid(Paginator page, PxProjectTag pxProjectTag, Model model) {
        Page<PxProjectTag> tags = tagService.listTagByPage(pxProjectTag, page);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data", tags.getContent());
        map.put("iTotalRecords", tags.getTotalElements());
        map.put("iTotalDisplayRecords", tags.getTotalElements());
        return map;
    }

    @RequestMapping(value = "/tag/edit/{id}")
    public String classesEdit(@PathVariable Long id, Model model) {
        model.addAttribute("tag", tagService.findById(id));
        return "/tag/edit";
    }

    @RequestMapping(value = "/tag/update")
    public String classesUpdate(PxProjectTag pxProjectTag) {
        tagService.updateTag(pxProjectTag);
        return "redirect:/tag/list";
    }

    @RequestMapping(value = "/tag/save")
    //@ResponseBody
    public String classesSave(PxProjectTag pxProjectTag) {
        tagService.insertTag(pxProjectTag);
      //  return "map";
        return "redirect:list";
    }

    @RequestMapping(value = "/tag/del")
    @ResponseBody
    public String classesDel(PxProjectTag pxProjectTag) {
        tagService.delTag(pxProjectTag);
        return "map";
    }
}
