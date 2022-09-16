package com.zensar.df.dto;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Component;

import io.swagger.v3.oas.annotations.media.Schema;


@Component
public class ForumDto {
	@Schema(description="Question ID")
	private long questionId;
	@Schema(description="Question")
	private String question;
	@Schema(description="Question Status")
	private boolean status;
	@Schema(description="Answers")
	private String answers;
	@Schema(description="Category ID")
	private long categoryid;
	@Schema(description="User name")
	private String username;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public long getQuestionId() {
		return questionId;
	}
	public void setQuestionId(long questionId) {
		this.questionId = questionId;
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
	public void setAnswers(String string) {
		this.answers = string;
	}
	public long getCategoryid() {
		return categoryid;
	}
	public void setCategoryid(long categoryid) {
		this.categoryid = categoryid;
	}
	public ForumDto() {}
	public ForumDto(long questionId, String question, boolean status, String answers, long categoryid) {
		super();
		this.questionId = questionId;
		this.question = question;
		this.status = status;
		this.answers = answers;
		this.categoryid = categoryid;
	}
	public ForumDto( long questionId,String question, boolean status, String answers) {
		super();
		this.questionId = questionId;
		this.question = question;
		this.status = status;
		this.answers = answers;
		//this.categoryid = categoryid;
	}
	public ForumDto( String question, boolean status, String answers, long categoryid) {
		super();
		//this.questionId = questionId;
		this.question = question;
		this.status = status;
		this.answers = answers;
		this.categoryid = categoryid;
	}
	public ForumDto(long questionId, String question) {
		super();
		this.questionId = questionId;
		this.question = question;
	}


	public ForumDto(long questionId, String question, boolean status, String answers, long categoryid,
			String username) {
		super();
		this.questionId = questionId;
		this.question = question;
		this.status = status;
		this.answers = answers;
		this.categoryid = categoryid;
		this.username = username;
	}
	@Override
	public String toString() {
		return "ForumDto [questionId=" + questionId + ", question=" + question + ", status=" + status + ", answers="
				+ answers + ", categoryid=" + categoryid + ", username=" + username + "]";
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ForumDto other = (ForumDto) obj;
		return Objects.equals(question, other.question);
	}
}


