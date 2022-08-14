package hry.manage.remote.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ArticleCategory implements Serializable {
	
	private Long id ;
	private String name;
	private Integer sort;
	
	private List<Article> articles;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public List<Article> getArticles() {
		return articles;
	}
	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}
	
	
	
	
}
