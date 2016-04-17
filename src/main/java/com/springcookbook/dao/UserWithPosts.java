package com.springcookbook.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.springcookbook.bo.Post;
import com.springcookbook.bo.User;

public class UserWithPosts implements ResultSetExtractor <List<User>>{

	public List<User> extractData(ResultSet rs) throws SQLException,
			DataAccessException {
		Map<Long, User> userMap = new ConcurrentHashMap<Long,
				User>();
				User u = null;
				while (rs.next()) {
					// user already in map?
					Long id = rs.getLong("id");
					u = userMap.get(id);
					// if not, add it
					if(u == null) {
					u = new User();
					u.setId(id);
					u.setFirstName(rs.getString("first_name"));
					u.setAge(rs.getInt("age"));
					userMap.put(id, u);
				}
				// create post if there's one
				Long postId = rs.getLong("p_id");
				if (postId > 0) {
						System.out.println("add post id=" + postId);
						Post p = new Post();
						p.setId(postId);
						p.setTitle(rs.getString("p_title"));
						p.setDate(rs.getDate("p_date"));
						p.setUser(u);
						u.getPosts().add(p);
					}
				}
					return new LinkedList<User>(userMap.values());
		}

}
