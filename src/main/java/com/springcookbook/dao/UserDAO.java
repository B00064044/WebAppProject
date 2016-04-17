package com.springcookbook.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springcookbook.bo.User;

@Repository
@Transactional
public class UserDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * Add user to database
	 * @param user
	 */
	public void add(User user){
		String sql = "insert into user (first_name, age) values (?, ?)";
		jdbcTemplate.update(sql, user.getFirstName(), user.getAge());
	}
	
	public User findById(Long id) {
		String sql = "select * from user where id=?";
		User user = jdbcTemplate.queryForObject(sql, new
		Object[]{id}, new UserMapper());
		return user;
	}
	
	
	public List<User> findAll() {
		String sql = "select * from user";
		List<User> userList = jdbcTemplate.query(sql, new UserMapper());
		return userList;
	}
	
	public void update(User user) {
		String sql = "update user set first_name=?, age=? where id=?";
		jdbcTemplate.update(sql, user.getFirstName(), user.getAge(),
		user.getId());
		}
	
	public void save(User user) {
		if (user.getId() == null) {
			add(user);
			}
		else {
			update(user);
			}
		}
	
//	public void delete(User user) {
//		String sql = "delete from user where id=?";
//		getJdbcTemplate().update(sql, user.getId());
//		}
	
	static private class UserMapper implements RowMapper<User> {		
		public User mapRow(ResultSet row, int rowNum) throws SQLException {
			User user = new User();
			user.setId(row.getLong("id"));
			user.setFirstName(row.getString("first_name"));
			user.setAge(row.getInt("age"));
			return user;
		}
	}
	
}
