package com.liuyanzhao.ssm.blog.controller.admin;


import com.liuyanzhao.ssm.blog.entity.Banner;
import com.liuyanzhao.ssm.blog.entity.Link;
import com.liuyanzhao.ssm.blog.service.BannerService;
import com.liuyanzhao.ssm.blog.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;


/**
 * @author liuyanzhao
 */
@Controller
@RequestMapping("/admin/link")
public class BackLinkController {

    @Autowired
    private LinkService linkService;
    @Autowired
    private BannerService bannerService;

    /**
     * 后台链接列表显示
     *
     * @return modelAndView
     */
    @RequestMapping(value = "")
    public ModelAndView linkList()  {
        ModelAndView modelandview = new ModelAndView();

        List<Banner> linkList = bannerService.listLink();
        modelandview.addObject("linkList",linkList);

        modelandview.setViewName("Admin/Link/index");
        return modelandview;

    }

    /**
     * 后台添加链接页面显示
     *
     * @return modelAndView
     */
    @RequestMapping(value = "/insert")
    public ModelAndView insertLinkView()  {
        ModelAndView modelAndView = new ModelAndView();

        List<Banner> linkList = bannerService.listLink();

        modelAndView.addObject("linkList",linkList);

        modelAndView.setViewName("Admin/Link/insert");
        return modelAndView;
    }

    /**
     * 后台添加链接页面提交
     *
     * @param banner 链接
     * @return 响应
     */
    @RequestMapping(value = "/insertSubmit",method = RequestMethod.POST)
    public String insertLinkSubmit(Banner banner)  {

        bannerService.insertBanner(banner);
       // linkService.insertLink();
        return "redirect:/admin/link/insert";
    }

    /**
     * 删除链接
     *
     * @param id 链接ID
     * @return 响应
     */
    @RequestMapping(value = "/delete/{id}")
    public String deleteLink(@PathVariable("id") Integer id)  {
        bannerService.deleteBanner(id);
        //linkService.deleteLink(id);
        return "redirect:/admin/link";
    }

    /**
     * 编辑链接页面显示
     *
     * @param id
     * @return modelAndVIew
     */
    @RequestMapping(value = "/edit/{id}")
    public ModelAndView editLinkView(@PathVariable("id") Integer id)  {
        ModelAndView modelAndView = new ModelAndView();
        Banner linkCustom=bannerService.getBannerById(id);
        //Link linkCustom =  linkService.getLinkById(id);
        modelAndView.addObject("linkCustom",linkCustom);

        List<Banner> linkList = bannerService.listLink();
        //List<Link> linkList = linkService.listLink(null);
        modelAndView.addObject("linkList",linkList);

        modelAndView.setViewName("Admin/Link/edit");
        return modelAndView;
    }


    /**
     * 编辑链接提交
     *
     * @param banner 链接
     * @return 响应
     */
    @RequestMapping(value = "/editSubmit",method = RequestMethod.POST)
    public String editLinkSubmit(Banner banner)  {
       bannerService.updateBanner(banner);
       // linkService.updateLink(link);
        return "redirect:/admin/link";
    }
}
