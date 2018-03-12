package com.racproject.dao;

import java.net.UnknownHostException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.racproject.config.WebConfig;
import com.racproject.model.Login;
import com.racproject.model.QueryApp;
import com.racproject.model.User;

@Repository("UserDao")
public class UserDaoImpl implements UserDao {

	@Autowired(required = true)
	MongoTemplate mongoTemplate;
	
	private static final String COLLECTION_NAME = "user";
	
	public List<User> listUser() {
		return mongoTemplate.findAll(User.class, COLLECTION_NAME);
	}

	public void add(User user) {
		if(!mongoTemplate.collectionExists(User.class)) {
			mongoTemplate.createCollection(User.class);
		}
		
		user.setId(UUID.randomUUID().toString());
		mongoTemplate.insert(user, COLLECTION_NAME);
	}

	public void update(User user) {
		mongoTemplate.save(user);
	}

	public void delete(User user) {
		mongoTemplate.remove(user, COLLECTION_NAME);
	}

	public User findUserById(String id) {
		return mongoTemplate.findById(id, User.class);
	}
	
	public User validateUser(Login login) throws Exception {
		Query selectQuery = new Query();
		selectQuery.addCriteria(Criteria.where("username").is(login.getUsername()).and("password").is(login.getPassword()));
		User user = WebConfig.mongoTemplate().findOne(selectQuery, User.class);
		return user;
	}
	
	
}
