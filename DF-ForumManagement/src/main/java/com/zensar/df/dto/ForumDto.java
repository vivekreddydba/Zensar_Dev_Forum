package com.zensar.df.dto;

import org.springframework.stereotype.Component;

import io.swagger.annotations.ApiModelProperty;
@Component
public class ForumDto {
	@ApiModelProperty(value="Question ID")
	private long questionId;
	@ApiModelProperty(value="Question")
	private String question;
	@ApiModelProperty(value="Question Status")
	private boolean status;
	@ApiModelProperty(value="Answers")
	private String answers;
	@ApiModelProperty(value="Category ID")
	private long categoryid;
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
	public void setAnswers(String answers) {
		this.answers = answers;
	}
	public long getCategoryid() {
		return categoryid;
	}
	public void setCategoryid(long categoryid) {
		this.categoryid = categoryid;
	}
	public ForumDto(long questionId, String question, boolean status, String answers, long categoryid) {
		super();
		this.questionId = questionId;
		this.question = question;
		this.status = status;
		this.answers = answers;
		this.categoryid = categoryid;
	}
	public ForumDto() {
		super();
	}
	@Override
	public String toString() {
		return "ForumDto [questionId=" + questionId + ", question=" + question + ", status=" + status + ", answers="
				+ answers + ", categoryid=" + categoryid + "]";
	}


}
