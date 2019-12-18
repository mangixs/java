package com.example.demo.config;


import com.example.demo.common.WinitApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "winit")
public class WinitConfig {

    private List<String> list;

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }
    @Autowired
    private Environment env;
    /**
    * @Description: 获取万邑通api对象
    * @Param: [account]
    * @return: com.ebuy.cloud.service.hwc.servicehwc.common.WinitApi
    * @Author: helios
    * @Date: 2019/12/10 0010
    */
    public WinitApi getWinitApi(){
        WinitApi api = new WinitApi();
        Map<String, Object> configList = getConfigList();
        api.setConfigList(configList);
        return api;
    }

    private Map<String, String> getAccountConfig(String account) {
        Map<String, String> config = new HashMap();
        config.put("token", env.getProperty("winit.account." + account + ".token"));
        config.put("appKey", env.getProperty("winit.account." + account + ".appKey"));
        config.put("url", env.getProperty("winit.account." + account + ".url"));
        config.put("trackUrl", env.getProperty("winit.account." + account + ".trackUrl"));
        config.put("clientId", env.getProperty("winit.account." + account + ".clientId"));
        config.put("clientSecret", env.getProperty("winit.account." + account + ".clientSecret"));
        config.put("platforms", env.getProperty("winit.account." + account + ".platforms"));
        return config;
    }

    private Map<String, Object> getConfigList() {
        Map<String, Object> configList = new HashMap();
        for(String account : list) {
            Map<String, String> config = getAccountConfig(account);
            configList.put(account, config);
        }
        return configList;
    }
}
