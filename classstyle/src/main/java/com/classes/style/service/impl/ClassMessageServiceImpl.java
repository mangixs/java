package com.classes.style.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.classes.style.constants.ClassStyleConfigContants;
import com.classes.style.entity.ClassMessage;
import com.classes.style.mapper.ClassMessageMapper;
import com.classes.style.service.ClassMessageService;
import com.classes.style.utils.DateUtil;
import com.classes.style.utils.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class ClassMessageServiceImpl extends ServiceImpl<ClassMessageMapper, ClassMessage>
        implements ClassMessageService {

    @Autowired
    private ClassMessageMapper classMessageMapper;

    @Override
    @Transactional
    public void deleteMessage(Integer id) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", id);
        ClassMessage classMessage = classMessageMapper.selectOne(queryWrapper);
        classMessage.setIsValid(2);
        classMessage.setOperator(HttpUtil.getOperator());
        classMessage.setUpdatedAt(DateUtil.getTimeNow());
        classMessageMapper.update(classMessage, queryWrapper);
    }

    @Override
    public Page<ClassMessage> getMessageList(Integer pageNumber) {
        Page page = new Page(pageNumber, ClassStyleConfigContants.PAGE_SIZE);
        List<ClassMessage> res = classMessageMapper.getMessageList(page);
        return page.setRecords(res);
    }

    @Override
    public Page<ClassMessage> getIndexMessageList(Integer pageNumber) {
        Page page = new Page(pageNumber, 5);
        List<ClassMessage> res = classMessageMapper.getMessageList(page);
        return page.setRecords(res);
    }

    @Override
    public void submitMessage(String msg, Integer anonymous) {
        ClassMessage classMessage = new ClassMessage();
        classMessage.setIsValid(1);
        classMessage.setUpdatedAt(DateUtil.getTimeNow());
        classMessage.setContext(msg);
        classMessage.setCreatedAt(DateUtil.getTimeNow());
        classMessage.setOperator(HttpUtil.getOperator());
        if (anonymous == 1) {
            classMessage.setUsername("匿名");
            classMessage.setOffice("匿名");
        } else {
            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder
                    .getRequestAttributes();
            HttpServletRequest request = servletRequestAttributes.getRequest();
            HttpSession session = request.getSession();
            if (session.getAttribute("homeuserid") != null) {
                String username = (String) session.getAttribute("homeusername");
                String office = (String) session.getAttribute("homeuseroffice");
                classMessage.setUsername(username);
                classMessage.setOffice(office);
            } else {
                classMessage.setUsername("游客");
                classMessage.setOffice("游客");
            }
        }
        classMessageMapper.insert(classMessage);
    }
}
