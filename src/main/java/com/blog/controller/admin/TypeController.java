package com.blog.controller.admin;

import com.blog.pojo.Type;
import com.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @GetMapping("/types")
    public String types(Integer page,Integer size,Model model){
        if (StringUtils.isEmpty(page)){
            page = 0;
        }
        if (StringUtils.isEmpty(size)){
            size = 10;
        }
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Page<Type> types = typeService.listType(PageRequest.of(page, size, sort));
        model.addAttribute("page",types);
        return "admin/types";
    }

    @GetMapping("/types/input")
    public String input(Model model){
        model.addAttribute("type",new Type());
        return "admin/types-input";
    }

    @GetMapping("/types/{id}/input")
    public String editInput(@PathVariable("id") Long id, Model model){
        model.addAttribute("type",typeService.getType(id));
        return "admin/types-input";
    }

    @PostMapping("/types")
    public String post(@Valid Type type, BindingResult result,RedirectAttributes attributes){
        Type byName = typeService.getTypeByName(type.getName());
        if (byName != null){
            // 手动添加错误信息
            result.rejectValue("name","nameError","不能添加重复的分类");
        }
        if (result.hasErrors()){
            return "admin/types-input";
        }
        Type t = typeService.saveType(type);
        if (t == null){
            attributes.addFlashAttribute("message","新增失败！");
        }else{
            attributes.addFlashAttribute("message","新增成功！");
        }
        return "redirect:/admin/types";
    }

    @PostMapping("/types/{id}")
    public String editPost(@Valid Type type,@PathVariable("id")Long id, BindingResult result, RedirectAttributes attributes){
        Type byName = typeService.getTypeByName(type.getName());
        if (byName != null){
            // 手动添加错误信息
            result.rejectValue("name","nameError","不能添加重复的分类");
        }
        if (result.hasErrors()){
            return "admin/types-input";
        }
        Type t = typeService.saveType(type);
        if (t == null){
            attributes.addFlashAttribute("message","更新失败！");
        }else{
            attributes.addFlashAttribute("message","更新成功！");
        }
        return "redirect:/admin/types";
    }

    @GetMapping("/types/{id}/delete")
    public String deleteType(@PathVariable("id") Long id,RedirectAttributes attributes){
        typeService.deleteType(id);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/types";
    }
}
