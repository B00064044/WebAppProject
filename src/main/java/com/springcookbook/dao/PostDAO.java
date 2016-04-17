package com.springcookbook.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.springcookbook.bo.Post;
import com.springcookbook.bo.User;

@Repository
public class PostDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void add(Post post) {
		String sql = "insert into post (id, title, date, user_id) values (?, ?, ?, ?)";
		jdbcTemplate.update(sql, post.getId(), post.getTitle(), post.getDate(), post.getUser());
	}
	
	public List<Post> findAll() {
		String sql = "select u.id, u.first_name, u.age, p.id as p_id, p.title as p_title, "
				+ "p.date as p_date from post p left join user u on p.user_id = u.id order by p.date desc";
		return jdbcTemplate.query(sql, new PostMapper());
	}
	
	public Post findById(Long id) {
		String sql = "select * from post where id=?";
		Post post = jdbcTemplate.queryForObject(sql, new
		Object[]{id}, new PostMapper());
		return post;
	}
	
	static private class PostMapper implements RowMapper<Post> {		
	public Post mapRow(ResultSet row, int rowNum) throws SQLException {
			Post post = new Post();
			post.setId(row.getLong("p_id"));
			post.setTitle(row.getString("p_title"));
			post.setDate(row.getDate("p_date"));
			User u = new User();
			u.setFirstName(row.getString("u.first_name"));
			post.setUser(u);			
			return post;
		}
	}
	
	public void update(Post post){
		String sql = "update post set p_title=?, p_date=? where u.first_name=?";
		jdbcTemplate.update(sql, post.getTitle(), post.getDate(), post.getUser());
	}
	
	public void save(Post post){
//		if(post.getId() == null){
//			add(post);
//		}
//		else{
//			update(post);
//		}
	}
	
	public void delete(Post post){
//		String sql = "delete from post where id=?";
//		getJdbcTemplate().update(sql, post.getId());
	}

}
