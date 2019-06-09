package com.library.system.controllerUtil;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 页面之间跳转的Controller
 * @author lisujie
 *
 */ 
@Controller
public class PageForwarController {
     @RequestMapping("/{pageName}")
	public String pageForward(@PathVariable("pageName") String pageName){
		return pageName;   	 
     }
}
