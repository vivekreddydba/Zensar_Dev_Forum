package com.zensar.df.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.stereotype.Component;
@Component
@Entity
@Table(name = "forum")
public class ForumEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "questionid")
	private long questionid;
	@Column(name = "question")
	private String question;
	@Column(name = "status")
	private boolean status;
	@Column(name = "answers")
	private String answers;
	@Column(name = "username")
	private String username;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@ManyToOne(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="category_id")
	private CategoryEntity category;
	
	public CategoryEntity getCategory() {
		return category;
	}
	public void setCategory(CategoryEntity category) {
		this.category = category;
	}
	public ForumEntity() {
		super();
	}
	
	public ForumEntity(long questionid, String question, boolean status, String answers) {
		super();
		this.questionid = questionid;
		this.question = question;
		this.status = status;
		this.answers = answers;
		//this.category = category;
	}
	
	public ForumEntity(long questionid, String question, boolean status, String answers, CategoryEntity category) {
	super();
	this.questionid = questionid;
	this.question = question;
	this.status = status;
	this.answers = answers;
	this.category = category;
}
	public long getQuestionid() {
		return questionid;
	}
	public void setQuestionid(long questionid) {
		this.questionid = questionid;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public boolean getStatus() {
		return status;
	}
	public String getAnswers() {
		return answers;
	}
	public void setAnswers(String answers) {
		this.answers = answers;
	}
	@Override
	public String toString() {
		return "ForumEntity [questionid=" + questionid + ", question=" + question + ", status=" + status + ", answers="
				+ answers + ", username=" + username + ", category=" + category + "]";
	}
	public ForumEntity(long questionid, String question, boolean status, String answers, String username,
			CategoryEntity category) {
		super();
		this.questionid = questionid;
		this.question = question;
		this.status = status;
		this.answers = answers;
		this.username = username;
		this.category = category;
	}

}
