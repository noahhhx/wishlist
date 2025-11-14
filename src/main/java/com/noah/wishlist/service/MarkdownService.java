package com.noah.wishlist.service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.stereotype.Service;

@Service
public class MarkdownService {
    
    private static final Charset MD_CHARSET = StandardCharsets.UTF_8;
    
    private final Parser markdownParser;
    private final HtmlRenderer htmlRenderer;
    
    public MarkdownService() {
        markdownParser = Parser.builder().build();
        htmlRenderer = HtmlRenderer.builder().build();
    }
    
    public String markdownToHtml(String markdown) {
        Node document = markdownParser.parse(markdown);
        return htmlRenderer.render(document);
    }
    
    public void writeToMarkdownFile(File file, String markdown) throws IOException {
        Files.writeString(file.toPath(), markdown, MD_CHARSET);
    }
    
    public String getMarkdown(File markdownFile) throws IOException {
        return Files.readString(markdownFile.toPath(), MD_CHARSET);
    }
}
