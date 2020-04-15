package com.classes.style.controller.home;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.classes.style.commom.Code;
import com.classes.style.commom.ResponseMsg;
import com.classes.style.entity.*;
import com.classes.style.mapper.ClassActivityMapper;
import com.classes.style.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api")
public class ApiController {
    private static final Logger log = LoggerFactory.getLogger(ApiController.class);

    @Autowired
    private ClassUserService classUserService;

    @Autowired
    private ClassStyleConfigService classStyleConfigService;

    @Autowired
    private ClassNoticeService classNoticeService;

    @Autowired
    private ClassArticleService classArticleService;

    @Autowired
    private ClassHonorService classHonorService;

    @Autowired
    private ClassActivityService classActivityService;

    @Autowired
    private ClassNewsService classNewsService;

    @Autowired
    private ClassMessageService classMessageService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseMsg register(@RequestBody JSONObject post) {
        try {
            Map<String, Object> res = classUserService.userRegister(post);
            int code = (int) res.get("code");
            if (code == 200) {
                return new ResponseMsg(Code.SUCCESS, res, "注册成功");
            } else {
                return new ResponseMsg(Code.FAIL, res, (String) res.get("msg"));
            }
        } catch (Exception e) {
            log.info("请求 register 接口出错 {}", e.getMessage());
            return new ResponseMsg(Code.FAIL, "", "注册失败");
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseMsg login(@RequestBody JSONObject post) {
        try {
            Map<String, Object> res = classUserService.login(post);
            int code = (int) res.get("code");
            if (code == 200) {
                return new ResponseMsg(Code.SUCCESS, res, "登陆成功");
            } else {
                return new ResponseMsg(Code.FAIL, res, (String) res.get("msg"));
            }
        } catch (Exception e) {
            log.info("请求 login 接口出错 {}", e.getMessage());
            return new ResponseMsg(Code.FAIL, "", "登陆失败");
        }
    }

    @GetMapping("/getIndexData")
    public ResponseMsg getIndexData() {
        try {
            Map<String, Object> result = new HashMap<>();
            Map<String, Object> res = classStyleConfigService.getClassConfigByName();
            ClassNotice classNotice = classNoticeService.getNewsNotice();
            Map<String, Object> articleList = classArticleService.getIndexArticleList();
            List<ClassHonor> classHonorList = classHonorService.getIndexHonor();
            result.put("summary", res);
            result.put("notice", classNotice);
            result.put("article", articleList);
            result.put("honor", classHonorList);
            return new ResponseMsg(Code.SUCCESS, result, "获取成功");
        } catch (Exception e) {
            log.info("请求 getIndexData 接口出错 {}", e.getMessage());
            return new ResponseMsg(Code.FAIL, "", "获取失败");
        }
    }

    @GetMapping("/getNoticeData")
    public ResponseMsg getNoticeData() {
        try {
            List<ClassNotice> result = classNoticeService.getNoticeData();
            return new ResponseMsg(Code.SUCCESS, result, "获取成功");
        } catch (Exception e) {
            log.info("请求 getNoticeData 接口出错 {}", e.getMessage());
            return new ResponseMsg(Code.FAIL, "", "获取失败");
        }
    }

    @GetMapping("/getSingleData")
    public ResponseMsg getSingleData() {
        try {
            Map<String, Object> result = new HashMap<>();
            Map<String, Object> res = classStyleConfigService.getClassConfigByName();
            List<ClassUser> list = classUserService.getClassOfficeList();
            result.put("summary", res);
            result.put("user", list);
            return new ResponseMsg(Code.SUCCESS, result, "获取成功");
        } catch (Exception e) {
            log.info("请求 getSingleData 接口出错 {}", e.getMessage());
            return new ResponseMsg(Code.FAIL, "", "获取失败");
        }
    }

    @RequestMapping("/getSingleArticle/{id}/{articleType}")
    public ResponseMsg getSingleArticle(@PathVariable Integer id, @PathVariable String articleType) {
        try {
            if ("article".equals(articleType)) {
                ClassArticle classArticle = classArticleService.getSingleArticle(id);
                return new ResponseMsg(Code.SUCCESS, classArticle, "获取成功");
            }
            if ("news".equals(articleType)) {
                ClassNews classNews = classNewsService.getSingleNews(id);
                return new ResponseMsg(Code.SUCCESS, classNews, "获取成功");
            }
            return new ResponseMsg(Code.FAIL, "", "获取失败");
        } catch (Exception e) {
            log.info("请求 getSingleArticle 接口出错 {}", e.getMessage());
            return new ResponseMsg(Code.FAIL, "", "获取失败");
        }
    }

    @RequestMapping("/getProfessionList/{pageNumber}")
    public ResponseMsg getProfessionList(@PathVariable Integer pageNumber) {
        try {
            Page<ClassArticle> result = classArticleService.getArticleTypeByPage(pageNumber, 1);
            return new ResponseMsg(Code.SUCCESS, result, "获取成功");
        } catch (Exception e) {
            log.info("请求 getProfessionList 接口出错 {}", e.getMessage());
            return new ResponseMsg(Code.FAIL, "", "获取失败");
        }
    }

    @RequestMapping("/getOtherList/{pageNumber}")
    public ResponseMsg getOtherList(@PathVariable Integer pageNumber) {
        try {
            Page<ClassArticle> result = classArticleService.getArticleTypeByPage(pageNumber, 2);
            return new ResponseMsg(Code.SUCCESS, result, "获取成功");
        } catch (Exception e) {
            log.info("请求 getOtherList 接口出错 {}", e.getMessage());
            return new ResponseMsg(Code.FAIL, "", "获取失败");
        }
    }

    @RequestMapping("/getArchiveList/{pageNumber}")
    public ResponseMsg getArchiveList(@PathVariable Integer pageNumber) {
        try {
            Page<ClassActivity> result = classActivityService.getArchiveList(pageNumber);
            return new ResponseMsg(Code.SUCCESS, result, "获取成功");
        } catch (Exception e) {
            log.info("请求 getArchiveList 接口出错 {}", e.getMessage());
            return new ResponseMsg(Code.FAIL, "", "获取失败");
        }
    }

    @RequestMapping("/getNewsTimesList/{pageNumber}")
    public ResponseMsg getNewsTimesList(@PathVariable Integer pageNumber) {
        try {
            Page<ClassNews> result = classNewsService.getNewsListByType(pageNumber, 1);
            return new ResponseMsg(Code.SUCCESS, result, "获取成功");
        } catch (Exception e) {
            log.info("请求 getNewsTimesList 接口出错 {}", e.getMessage());
            return new ResponseMsg(Code.FAIL, "", "获取失败");
        }
    }

    @RequestMapping("/getNewsClassList/{pageNumber}")
    public ResponseMsg getNewsClassList(@PathVariable Integer pageNumber) {
        try {
            Page<ClassNews> result = classNewsService.getNewsListByType(pageNumber, 2);
            return new ResponseMsg(Code.SUCCESS, result, "获取成功");
        } catch (Exception e) {
            log.info("请求 getNewsClassList 接口出错 {}", e.getMessage());
            return new ResponseMsg(Code.FAIL, "", "获取失败");
        }
    }

    @GetMapping("/getHonorData/{pageNumber}")
    public ResponseMsg getHonorData(@PathVariable Integer pageNumber)
    {
        try {
            Page<ClassHonor> result = classHonorService.getHonorList(pageNumber);
            return new ResponseMsg(Code.SUCCESS, result, "获取成功");
        } catch (Exception e) {
            log.info("请求 getNewsClassList 接口出错 {}", e.getMessage());
            return new ResponseMsg(Code.FAIL, "", "获取失败");
        }
    }

    @GetMapping("/getMessageList/{pageNumber}")
    public ResponseMsg getMessageList(@PathVariable Integer pageNumber)
    {
        try {
            Page<ClassMessage> result = classMessageService.getIndexMessageList(pageNumber);
            return new ResponseMsg(Code.SUCCESS, result, "获取成功");
        } catch (Exception e) {
            log.info("请求 getMessageList 接口出错 {}", e.getMessage());
            return new ResponseMsg(Code.FAIL, "", "获取失败");
        }
    }

    @PostMapping("/Homelogin")
    @ResponseBody
    public ResponseMsg Homelogin(Integer number, String password)
    {
        try {
            Map<String, Object> result = classUserService.homeLogin(number, password);
            int code = (int) result.get("code");
            if (code == 200) {
                return new ResponseMsg(Code.SUCCESS, result, "登陆成功");
            }
            return new ResponseMsg(Code.FAIL, "", (String) result.get("msg"));
        } catch (Exception e) {
            log.info("请求 login 接口出错 {}", e.getMessage());
            return new ResponseMsg(Code.FAIL, "", "获取失败");
        }
    }

    @GetMapping("/getUserName")
    public ResponseMsg getUserName(HttpSession session)
    {
        try {
            Map<String, Object> result = new HashMap<>();
            result.put("username", session.getAttribute("homeusername"));
            result.put("office", session.getAttribute("homeuseroffice"));
            return new ResponseMsg(Code.SUCCESS, result, "获取成功");
        } catch (Exception e) {
            log.info("请求 getUserName 接口出错 {}", e.getMessage());
            return new ResponseMsg(Code.FAIL, "", "获取失败");
        }
    }

    @PostMapping("/submitMessage")
    @ResponseBody
    public ResponseMsg submitMessage(String msg, Integer anonymous)
    {
        try {
            classMessageService.submitMessage(msg, anonymous);
            return new ResponseMsg(Code.FAIL, "", "发表留言成功");
        } catch (Exception e) {
            log.info("请求 login 接口出错 {}", e.getMessage());
            return new ResponseMsg(Code.FAIL, "", "获取失败");
        }
    }
}
