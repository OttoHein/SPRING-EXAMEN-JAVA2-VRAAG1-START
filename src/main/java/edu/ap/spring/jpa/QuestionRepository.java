package edu.ap.spring.jpa;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends CrudRepository<Question, Long> {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	class QuestionRowMapper implements RowMapper<Question>{
		@Override
		public Question mapRow(ResultSet rs, int rowNum) throws SQLException {
			Question question = new Question();
			question.setId(rs.getInt("id"));
			question.setQuestion(rs.getString("question"));
			question.setAnswer(rs.getString("answer"));
			return question;
		}
	
		public List<Question> findAll() {
			return jdbcTemplate.query("select * from question", new QuestionRowMapper());
		}

		public Question findById(int id) {
			return jdbcTemplate.queryForObject("select * from question where id=?", new Object[] { id },
					new BeanPropertyRowMapper<Question>(Question.class));
		}

		public int deleteById(int id) {
			return jdbcTemplate.update("delete from question where id=?", new Object[] { id });
		}

		public int insert(Question question) {
			return jdbcTemplate.update("insert into question (id, question, answer) " + "values(?,  ?, ?)",
					new Object[] { question.getId(), question.getQuestion(), question.getAnswer() });
		}	
	}
}

