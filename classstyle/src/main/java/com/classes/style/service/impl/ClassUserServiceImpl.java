package com.classes.style.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.classes.style.constants.ClassStyleConfigContants;
import com.classes.style.entity.ClassUser;
import com.classes.style.mapper.ClassUserMapper;
import com.classes.style.service.ClassUserService;
import com.classes.style.utils.DateUtil;
import com.classes.style.utils.Md5Util;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ClassUserServiceImpl extends ServiceImpl<ClassUserMapper, ClassUser> implements ClassUserService {

    @Resource
    private ClassUserMapper classUserMapper;

    @Override
    @Transactional
    public Map<String, Object> userRegister(JSONObject post) {
        Map<String, Object> res = new HashMap<>();
        int number = post.getInteger("number");
        String username = post.getString("username");
        int identity = post.getInteger("identity");
        String office = post.getString("office");
        String password = post.getString("password");

        QueryWrapper qw = new QueryWrapper();
        qw.eq("number", number);

        ClassUser classUser = classUserMapper.selectOne(qw);
        if (classUser != null) {
            res.put("code", 500);
            res.put("msg", "学号已经存在");
            return res;
        }

        ClassUser user = new ClassUser();
        user.setAccountStatus(2);
        user.setUsername(username);
        user.setPassword(Md5Util.getMd5(ClassStyleConfigContants.PASSWORD_PREFIX + password));
        user.setIdentity(identity);
        user.setOffice(office);
        user.setHeaderImg("");
        user.setSummary("");
        user.setNumber(number);
        user.setOperator(username);
        user.setCreatedAt(DateUtil.getTimeNow());
        user.setUpdatedAt(DateUtil.getTimeNow());
        classUserMapper.insert(user);

        res.put("code", 200);
        res.put("msg", "注册成功");
        return res;
    }

    @Override
    public List<ClassUser> getClassOfficeList() {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.select("id", "username", "identity", "office", "header_img", "is_show", "summary", "number",
                "account_status");
        queryWrapper.eq("identity", 2);
        queryWrapper.eq("account_status", 1);
        queryWrapper.eq("user_type", 1);
        return classUserMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional
    public void saveUserSummary(JSONObject post) {
        int id = post.getInteger("id");
        String summary = post.getString("summary");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", id);
        ClassUser classUser = classUserMapper.selectOne(queryWrapper);
        classUser.setSummary(summary);
        classUserMapper.update(classUser, queryWrapper);
    }

    @Override
    @Transactional
    public void showEdit(JSONObject post) {
        int id = post.getInteger("id");
        int show = post.getInteger("show");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", id);
        ClassUser classUser = classUserMapper.selectOne(queryWrapper);
        classUser.setIsShow(show);
        classUserMapper.update(classUser, queryWrapper);
    }

    @Override
    @Transactional
    public void saveUserHeaderImages(JSONObject post) {
        int id = post.getInteger("id");
        String headerImage = post.getString("headerImage");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", id);
        ClassUser classUser = classUserMapper.selectOne(queryWrapper);
        classUser.setHeaderImg(headerImage);
        classUserMapper.update(classUser, queryWrapper);
    }

    @Override
    public List<ClassUser> getUserList() {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.select("id", "username", "office", "number", "account_status", "identity");
        queryWrapper.eq("user_type", 1);
        return classUserMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional
    public void editUserAccountStatus(JSONObject post) {
        int id = post.getInteger("id");
        Integer status = post.getInteger("status");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", id);
        ClassUser classUser = classUserMapper.selectOne(queryWrapper);
        classUser.setAccountStatus(status);
        classUserMapper.update(classUser, queryWrapper);
    }

    @Override
    @Transactional
    public void saveOfficeName(JSONObject post) {
        int id = post.getInteger("id");
        String office = post.getString("newOfficeName");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", id);
        ClassUser classUser = classUserMapper.selectOne(queryWrapper);
        classUser.setOffice(office);
        classUserMapper.update(classUser, queryWrapper);
    }

    @Override
    public Map<String, Object> login(JSONObject post) {
        Map<String, Object> res = new HashMap<>();
        String password = post.getString("password");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("number", post.getInteger("number"));
        queryWrapper.eq("password", Md5Util.getMd5(ClassStyleConfigContants.PASSWORD_PREFIX + password));
        ClassUser classUser = classUserMapper.selectOne(queryWrapper);
        if (classUser == null) {
            res.put("code", 500);
            res.put("msg", "用户名密码错误");
            return res;
        }
        // 账号状态 1 启用 2 未启用
        if (classUser.getAccountStatus() == 2) {
            res.put("code", 500);
            res.put("msg", "您的账号未启用，请联系老师启用账号");
            return res;
        }
        // 用学号登陆成功 且用户类型为1 学生 identity = 2 班干 登陆成功
        if (classUser != null && classUser.getUserType() == 1 && classUser.getIdentity() == 2) {
            setUserSession(classUser);
            res.put("code", 200);
            res.put("url", "/admin/index");
            return res;
        }
        // 用学号登陆成功 且用户类型为1 学生 identity = 1 学生 不允许登陆
        if (classUser != null && classUser.getUserType() == 1 && classUser.getIdentity() == 1) {
            res.put("code", 500);
            res.put("msg", "学生不允许登陆后台");
            return res;
        }
        // 用户类型为2 老师 登陆成功
        if (classUser != null && classUser.getUserType() == 2) {
            setUserSession(classUser);
            res.put("code", 200);
            res.put("url", "/admin/userlist");
            return res;
        }
        return null;
    }

    private void setUserSession(ClassUser classUser) {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        HttpSession session = request.getSession();
        session.setAttribute("username", classUser.getUsername());
        session.setAttribute("userType", classUser.getUserType());
    }

    @Override
    public Map<String, Object> homeLogin(Integer number, String password) {
        Map<String, Object> res = new HashMap<>();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("number", number);
        queryWrapper.eq("password", Md5Util.getMd5(ClassStyleConfigContants.PASSWORD_PREFIX + password));
        ClassUser classUser = classUserMapper.selectOne(queryWrapper);
        if (classUser == null) {
            res.put("code", 500);
            res.put("msg", "用户名密码错误");
            return res;
        }
        // 账号状态 1 启用 2 未启用
        if (classUser.getAccountStatus() == 2) {
            res.put("code", 500);
            res.put("msg", "您的账号未启用，请联系老师启用账号");
            return res;
        }
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        HttpSession session = request.getSession();
        session.setAttribute("homeusername", classUser.getUsername());
        session.setAttribute("homeuserid", classUser.getId());
        session.setAttribute("homeuseroffice", classUser.getOffice());
        res.put("code", 200);
        res.put("username", classUser.getUsername());
        res.put("office", classUser.getOffice());
        return res;
    }
}