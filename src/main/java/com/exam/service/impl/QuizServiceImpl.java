package com.exam.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.entity.exam.Category;
import com.exam.entity.exam.Quiz;
import com.exam.repo.QuizRepository;
import com.exam.service.QuizService;

@Service
public class QuizServiceImpl implements QuizService{
	
	@Autowired
	private QuizRepository quizRepo;

	@Override
	public Quiz addQuiz(Quiz quiz) {
		// TODO Auto-generated method stub
		return this.quizRepo.save(quiz);
	}

	@Override
	public Quiz updateQuiz(Quiz quiz) {
		// TODO Auto-generated method stub
		return this.quizRepo.save(quiz);
	}

	@Override
	public Set<Quiz> getQuizes() {
		// TODO Auto-generated method stub
		return new HashSet<>(this.quizRepo.findAll());
	}

	@Override
	public Quiz getQuiz(Long quizId) {
		// TODO Auto-generated method stub
		return this.quizRepo.findById(quizId).get();
	}

	@Override
	public void deleteQuiz(Long quizId) {
		// TODO Auto-generated method stub
//		Quiz quiz=new Quiz();
//		quiz.setQid(quizId);
		this.quizRepo.deleteById(quizId);
	}
	
	@Override
	public List<Quiz> getQuizzesOfCategory(Category c) {
		// TODO Auto-generated method stub
		return this.quizRepo.findByCategory(c);
	}
	
	@Override
	public List<Quiz> getActiveQuizzes() {
		
		return this.quizRepo.findByActive(true);
	}
	@Override
	public List<Quiz> getActiveQuizzesOfCategory(Category c) {
		
		return this.quizRepo.findByCategoryAndActive(c, true);
	}

}
