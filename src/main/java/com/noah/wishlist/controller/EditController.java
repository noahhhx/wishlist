package com.noah.wishlist.controller;

import com.noah.wishlist.config.FileConfiguration;
import com.noah.wishlist.service.MarkdownService;
import java.io.IOException;
import java.nio.file.Files;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/edit")
@RequiredArgsConstructor
public class EditController {

    private final FileConfiguration fileConfiguration;
    private final MarkdownService markdownService;

    @Value("${wishlist.title:wishlist}")
    private String title;

    @GetMapping
    public String edit(Model model) throws IOException {
        String content = Files.readString(fileConfiguration.getMarkdownFile().toPath());

        model.addAttribute("pagetitle", title);
        model.addAttribute("rawContent", content);
        model.addAttribute("renderedContent", markdownService.markdownToHtml(content));
        return "edit";
    }
    
    @PostMapping
    public String save(@RequestParam String rawContent) throws IOException {
        markdownService.writeToMarkdownFile(fileConfiguration.getMarkdownFile(), rawContent);
        return "redirect:/";
    }
    
    @PostMapping("/preview")
    @ResponseBody
    public String preview(@RequestParam String rawContent, Model model) {
        // Apply your rendering logic here (markdown, etc.)
        String renderedContent = markdownService.markdownToHtml(rawContent);
        return renderedContent; // Return just the HTML fragment, not a full template
    }
}
