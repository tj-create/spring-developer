package me.parktaejin.spring_developer.repository;

import me.parktaejin.spring_developer.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Article,Long> {
}
