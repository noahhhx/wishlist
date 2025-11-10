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
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class IndexController {

    private final MarkdownService markdownService;
    private final FileConfiguration fileConfiguration;

    @Value("${wishlist.title:wishlist}")
    private String title;

    @GetMapping
    public String index(Model model) throws IOException {
        model.addAttribute("pagetitle", title);
        model.addAttribute(
              "mdHtmlContent",
              markdownService.markdownToHtml(
                    Files.readString(fileConfiguration.getMarkdownFile().toPath())));
        return "index";
    }
}
