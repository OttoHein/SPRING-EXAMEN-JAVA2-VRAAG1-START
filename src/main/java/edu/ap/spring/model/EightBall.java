package edu.ap.spring.model;
import org.springframework.stereotype.Component;
import java.util.Random;

@Component
public class EightBall {
	
	private String[] answers = {"It is certain", 
								"Yes definitely", 
								"Most likely",
								"Outlook good",
								"Better not tell you now",
								"Cannot predict now",
								"Don't count on it", 
								"Outlook not so good"};
	
	public String getRandomAnswer(String question) {
		String answer = "";
			int questionID = question.hashCode();
			Random rando = new Random();
			int random = rando.nextInt(9);
			answer = answers[random];
		return answer;
	}

	public String[] getAnswers() {
		return answers;
	}

	public void setAnswers(String[] answers) {
		this.answers = answers;
	}
}
