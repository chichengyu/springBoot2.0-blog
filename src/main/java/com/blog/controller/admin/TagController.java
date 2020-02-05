package com.blog.controller.admin;

import com.blog.pojo.Tag;
import com.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("/tags")
    public String types(Integer page,Integer size,Model model){
        if (StringUtils.isEmpty(page)){
            page = 0;
        }
        if (StringUtils.isEmpty(size)){
            size = 10;
        }
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Page<Tag> tags = tagService.listTags(PageRequest.of(page, size, sort));
        model.addAttribute("page",tags);
        return "admin/tags";
    }

    @GetMapping("/tags/input")
    public String input(Model model){
        model.addAttribute("tag",new Tag());
        return "admin/tags-input";
    }

    @GetMapping("/tags/{id}/input")
    public String editInput(@PathVariable("id") Long id, Model model){
        model.addAttribute("tag",tagService.getTagById(id));
        return "admin/tags-input";
    }

    /**
     * 添加标签
     * @param tag
     * @param result
     * @param attributes
     * @return
     */
    @PostMapping("/tags")
    public String post(@Valid Tag tag, BindingResult result,RedirectAttributes attributes){
        Tag byName = tagService.getTagByName(tag.getName());
        if (byName != null){
            // 手动添加错误信息
            result.rejectValue("name","nameError","不能添加重复的分类");
        }
        if (result.hasErrors()){
            return "admin/tags-input";
        }
        Tag t = tagService.saveTag(tag);
        if (t == null){
            attributes.addFlashAttribute("message","新增失败！");
        }else{
            attributes.addFlashAttribute("message","新增成功！");
        }
        return "redirect:/admin/tags";
    }

    /**
     * 更新标签
     * @param tag
     * @param id
     * @param result
     * @param attributes
     * @return
     */
    @PostMapping("/tags/{id}")
    public String editPost(@Valid Tag tag,@PathVariable("id")Long id, BindingResult result, RedirectAttributes attributes){
        Tag byName = tagService.getTagByName(tag.getName());
        if (byName != null){
            // 手动添加错误信息
            result.rejectValue("name","nameError","不能添加重复的分类");
        }
        if (result.hasErrors()){
            return "admin/tags-input";
        }
        Tag t = tagService.updateTag(id,tag);
        if (t == null){
            attributes.addFlashAttribute("message","更新失败！");
        }else{
            attributes.addFlashAttribute("message","更新成功！");
        }
        return "redirect:/admin/tags";
    }

    @GetMapping("/tags/{id}/delete")
    public String deleteType(@PathVariable("id") Long id,RedirectAttributes attributes){
        tagService.deleteTag(id);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/tags";
    }
}
