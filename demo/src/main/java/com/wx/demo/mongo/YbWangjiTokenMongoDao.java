package com.wx.demo.mongo;

import com.wx.demo.entity.mongo.YbWangjiToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

@Component
public class YbWangjiTokenMongoDao {
    @Autowired
    private MongoTemplate mongoTemplate;

    public YbWangjiToken getWangjiAccessToken(String type){
        Criteria criteria = Criteria.where("type").is(type);
        Query query = Query.query(criteria);
        return mongoTemplate.findOne(query, YbWangjiToken.class);
    }

}
