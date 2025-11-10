package com.noah.wishlist.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.stereotype.Service;

@Service
public class MarkdownService {
    
    public String markdownToHtml(String markdown) {
        Parser parser = Parser.builder().build();
        Node document = parser.parse(markdown);
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        return renderer.render(document);
    }
    
    public void writeToMarkdownFile(File file, String markdown) throws IOException {
        Files.writeString(file.toPath(), markdown);
    }
    
    public String getMarkdown(File markdownFile) throws IOException {
        return Files.readString(markdownFile.toPath());
    }
}
