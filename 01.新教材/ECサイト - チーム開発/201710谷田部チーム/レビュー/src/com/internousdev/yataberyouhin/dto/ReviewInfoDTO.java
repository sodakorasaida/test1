package com.internousdev.yataberyouhin.dto;

import java.util.Date;

public class ReviewInfoDTO {


	private int id;

	private String userId;

	private String familyName;

	private String firstName;

	private int productId;

	private int evaluation;

	private String reviewDetail;

	private Date insertDate;

	/**
	 * @return id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id セットする id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return familyName
	 */
	public String getFamilyName() {
		return familyName;
	}

	/**
	 * @param familyName セットする familyName
	 */
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	/**
	 * @return firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName セットする firstName
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId セットする userId
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return productId
	 */
	public int getProductId() {
		return productId;
	}

	/**
	 * @param productId セットする productId
	 */
	public void setProductId(int productId) {
		this.productId = productId;
	}

	/**
	 * @return evaluation
	 */
	public int getEvaluation() {
		return evaluation;
	}

	/**
	 * @param evaluation セットする evaluation
	 */
	public void setEvaluation(int evaluation) {
		this.evaluation = evaluation;
	}

	/**
	 * @return reviewDetail
	 */
	public String getReviewDetail() {
		return reviewDetail;
	}

	/**
	 * @param reviewDetail セットする reviewDetail
	 */
	public void setReviewDetail(String reviewDetail) {
		this.reviewDetail = reviewDetail;
	}

	/**
	 * @return insertDate
	 */
	public Date getInsertDate() {
		return insertDate;
	}

	/**
	 * @param insertDate セットする insertDate
	 */
	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}
}
