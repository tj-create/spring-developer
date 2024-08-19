package me.parktaejin.spring_developer.controller;

import lombok.RequiredArgsConstructor;
import me.parktaejin.spring_developer.domain.Article;
import me.parktaejin.spring_developer.dto.ArticleListViewResponse;
import me.parktaejin.spring_developer.dto.ArticleViewResponse;
import me.parktaejin.spring_developer.service.BlogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BlogViewController {

    private final BlogService blogService;
    private BlogService v;

    @GetMapping("/articles")
    public String getArticles(Model model) {
        List<ArticleListViewResponse> articles = blogService.findAll().stream()
                .map((Article at) -> new ArticleListViewResponse(at.getId(), at.getTitle(), at.getContent()))
                .toList();
        model.addAttribute("articles", articles);

        return "articleList";
    }

    @GetMapping("/articles/{id}")
    public String getArticle(@PathVariable long id, Model model) {
        Article article = blogService.findById(id);
        model.addAttribute("article", new ArticleViewResponse(article));

        return "article";
    }

    @GetMapping("/new-article")
    public String newArticle(@RequestParam(required = false) Long id, Model model) {
        if (id == null) {
            model.addAttribute("article", new ArticleViewResponse());
        } else {
            Article article = blogService.findById(id);
            model.addAttribute("article", new ArticleViewResponse(article));
        }

        return "newArticle";
    }
}
