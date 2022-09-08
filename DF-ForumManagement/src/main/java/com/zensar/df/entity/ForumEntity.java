package com.zensar.df.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
	@Column(name = "categoryid")
	private long categoryid;
	@Override
	public String toString() {
		return "ForumEntity [questionid=" + questionid + ", question=" + question + ", status=" + status + ", answers="
				+ answers + ", categoryid=" + categoryid + "]";
	}
	public ForumEntity() {
		super();
	}
	public ForumEntity(long questionid, String question, boolean status, String answers, long categoryid) {
		super();
		this.questionid = questionid;
		this.question = question;
		this.status = status;
		this.answers = answers;
		this.categoryid = categoryid;
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
	public String getAnswers() {
		return answers;
	}
	public void setAnswers(String answers) {
		this.answers = answers;
	}
	public long getCategoryid() {
		return categoryid;
	}
	public void setCategoryid(long categoryid) {
		this.categoryid = categoryid;
	}
	
	
}
