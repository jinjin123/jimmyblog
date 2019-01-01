package com.jimmyblog.interceptor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;

import com.jimmyblog.entity.Article;
import com.jimmyblog.entity.Category;
import com.jimmyblog.entity.Menu;
import com.jimmyblog.entity.Options;
import com.jimmyblog.enums.ArticleStatus;
import com.jimmyblog.enums.LinkStatus;
import com.jimmyblog.service.ArticleService;
import com.jimmyblog.service.CategoryService;
import com.jimmyblog.service.LinkService;
import com.jimmyblog.service.MenuService;
import com.jimmyblog.service.OptionsService;
import com.jimmyblog.service.TagService;
import com.sun.source.tree.Scope;

/**
 * Created  by  Jimmy  on 2019/01/01
 *
 */
public class HomeResourceInterceptor implements WebRequestInterceptor {
	
	@Autowired
	private ArticleService articleService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private TagService tagService;
	@Autowired
	private LinkService linkService;
	@Autowired
	private OptionsService optionService;
	@Autowired
	private MenuService menuService;
	
    /**
     * 在请求处理之前执行，该方法主要是用于准备资源数据的，然后可以把它们当做请求属性放到WebRequest中
     */
	@Override
	public void preHandle(WebRequest request) throws Exception {
		//menu list
		List<Menu> menuList = menuService.listMenu();
		request.setAttribute("menuList", menuList, WebRequest.SCOPE_REQUEST);
		
		List<Category> categoryList = categoryService.listCategory();
		request.setAttribute("allCategoryList", categoryList, WebRequest.SCOPE_REQUEST);
		
		//get website status
		List<String> siteBasicStatistics = new ArrayList<String>();
		siteBasicStatistics.add(articleService.countArticle(ArticleStatus.PUBLISH.getValue()) + "");
		siteBasicStatistics.add(articleService.countArticleComment()+ "");
		siteBasicStatistics.add(categoryService.countCategory()+ "");
		siteBasicStatistics.add(tagService.countTag() + "");
		siteBasicStatistics.add(linkService.countLink(LinkStatus.NORMAL.getValue()) + "");
		siteBasicStatistics.add(articleService.countArticleView() + "");
		request.setAttribute("siteBasicStatistics", siteBasicStatistics, WebRequest.SCOPE_REQUEST);
		//get last update artcle
		Article lastUpdateArticle = articleService.getLastUpdateArticle();
		request.setAttribute("lastUpdateArticle", lastUpdateArticle, WebRequest.SCOPE_REQUEST);
		//foolter
		Options options = optionService.getOptions();
		request.setAttribute("options", options,WebRequest.SCOPE_REQUEST);
		
	}

    /**
     * 该方法将在Controller执行之后，返回视图之前执行，ModelMap表示请求Controller处理之后返回的Model对象，所以可以在
     * 这个方法中修改ModelMap的属性，从而达到改变返回的模型的效果。
     */
	@Override
	public void postHandle(WebRequest request, ModelMap model) throws Exception {
	}
	

    /**
     * 该方法将在整个请求完成之后，也就是说在视图渲染之后进行调用，主要用于进行一些资源的释放
     */
	@Override
	public void afterCompletion(WebRequest request, Exception ex) throws Exception {
	}

}
