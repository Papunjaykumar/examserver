package com.exam.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.entity.exam.Question;
import com.exam.entity.exam.Quiz;
import com.exam.service.QuestionService;
import com.exam.service.QuizService;

@RestController
@CrossOrigin("*")
@RequestMapping("/question")
public class QuestionController {
	
	@Autowired	
	private QuestionService questionService;
	@Autowired
	private QuizService quizService;
	
//	add questions
	@PostMapping("/")
	public ResponseEntity<Question> addQuestion(@RequestBody Question question) {
		
		return ResponseEntity.ok(this.questionService.addQuestion(question));
	}
	
//	update Question
	@PutMapping("/")
	public ResponseEntity<Question> updateQuestion(@RequestBody Question question){
		
		return ResponseEntity.ok(this.questionService.updateQuestion(question));
	}
//	get all questions of any quiz
	@GetMapping("/quiz/{quizId}")
	public ResponseEntity<?> getQuestionsOfQuiz(@PathVariable("quizId")Long quizId){
//		give all the questions
		/*
		 * Quiz quiz = new Quiz(); quiz.setQid(quizId); Set<Question> questionsOfQuiz =
		 * this.questionService.getQuestionsOfQuiz(quiz);
		 * 
		 * return ResponseEntity.ok(questionsOfQuiz);
		 */
		Quiz quiz = this.quizService.getQuiz(quizId);
		Set<Question> questions = quiz.getQuestions();
		List<Question> list=new ArrayList<>(questions);
		if(list.size()>Integer.parseInt(quiz.getNumberOfquestion())) {
			list=list.subList(0, Integer.parseInt(quiz.getNumberOfquestion())+1);
		}
//		shuffle the question
		Collections.shuffle(list);
		return ResponseEntity.ok(list);
		
	}
	
//	get all questions of any quiz
	@GetMapping("/quiz/all/{quizId}")
	public ResponseEntity<?> getQuestionsOfQuizAdmin(@PathVariable("quizId")Long quizId){
//		give all the questions
		
		  Quiz quiz = new Quiz(); quiz.setQid(quizId); Set<Question> questionsOfQuiz =
		  this.questionService.getQuestionsOfQuiz(quiz);
		  
		  return ResponseEntity.ok(questionsOfQuiz);
		
	}

	
//	get single Question 
	@GetMapping("/{questionId}")
	public Question getQuestion(@PathVariable("questionId")Long questionId) {
		
		return this.questionService.getQuestion(questionId);
	}
	
//	delete Question
	@DeleteMapping("/{questionId}")
	public void deleteQuestion(@PathVariable("questionId")Long questionId) {
		
		this.questionService.deleteQuestion(questionId);
	}

}
