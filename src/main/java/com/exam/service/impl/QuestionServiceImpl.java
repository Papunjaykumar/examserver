package com.exam.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.entity.exam.Question;
import com.exam.entity.exam.Quiz;
import com.exam.repo.QuestionRepository;
import com.exam.service.QuestionService;
@Service
public class QuestionServiceImpl implements QuestionService{
	
	@Autowired
	private QuestionRepository questionRepo;

	@Override
	public Question addQuestion(Question question) {
		// TODO Auto-generated method stub
		return this.questionRepo.save(question);
	}

	@Override
	public Question updateQuestion(Question question) {
		// TODO Auto-generated method stub
		return this.questionRepo.save(question);
	}

	@Override
	public Set<Question> getQuestions() {
		// TODO Auto-generated method stub
		return new HashSet<>(this.questionRepo.findAll());
	}

	@Override
	public Question getQuestion(Long questionId) {
		// TODO Auto-generated method stub
		return this.questionRepo.findById(questionId).get();
	}

	@Override
	public Set<Question> getQuestionsOfQuiz(Quiz quiz) {
		// TODO Auto-generated method stub
		return this.questionRepo.findByQuiz(quiz);
	}

	@Override
	public void deleteQuestion(Long questionId) {
		// TODO Auto-generated method stub
		Question question = new Question();
		question.setQuesID(questionId);
		this.questionRepo.delete(question);
		
	}

}
