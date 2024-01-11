package com.exam.controller;

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

import com.exam.entity.exam.Category;
import com.exam.entity.exam.Quiz;
import com.exam.service.QuizService;

@RestController
@CrossOrigin("*")
@RequestMapping("/quiz")
public class QuizContoller {
	
	@Autowired
	private QuizService quizService;
	
//	add quiz;
	@PostMapping("/")
	public ResponseEntity<Quiz> addQuiz(@RequestBody Quiz quiz){
		
		return ResponseEntity.ok(this.quizService.addQuiz(quiz));
	}
//	update Quiz
	@PutMapping("/")
	public ResponseEntity<Quiz> updateQuiz(@RequestBody Quiz quiz){
		
		return ResponseEntity.ok(this.quizService.updateQuiz(quiz));
	}
	
//	get all quiz
	@GetMapping("/")
	public ResponseEntity<?> qyizzes(){
		
		return ResponseEntity.ok(this.quizService.getQuizes());
	}
//	single quiz
	
	@GetMapping("/{quizId}")
	public Quiz quiz(@PathVariable("quizId") Long quizId) {
		
		return this.quizService.getQuiz(quizId);
	}
	
//	delete the quiz
	@DeleteMapping("/{quizId}")
	public void deleteQuiz(@PathVariable("quizId") Long quizId) {
		this.quizService.deleteQuiz(quizId);
	}
	
	@GetMapping("/category/{cid}")
	public ResponseEntity<?> getQuizzesOfCategory(@PathVariable("cid")long cid){
		Category c = new Category();
		c.setCid(cid);
		
		return ResponseEntity.ok().body(this.quizService.getQuizzesOfCategory(c));
	}
	
//	get active quizzes
	@GetMapping("/active")
	public ResponseEntity<?> getActiveQuizzes(){
		
		return ResponseEntity.ok().body(this.quizService.getActiveQuizzes());
	}
	
//	get active quizzes of category
	@GetMapping("/category/active/{cid}")
	public ResponseEntity<?> getActiveQuizzesOfCategory(@PathVariable("cid")Long cid){
		
		Category c = new Category();
		c.setCid(cid);
		return ResponseEntity.ok().body(this.quizService.getActiveQuizzesOfCategory(c));
	}

}
