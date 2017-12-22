package com.internousdev.yataberyouhin.util;

/**
 * 入力チェック全般を担当します。
 * @author internousdev
 *
 */
public class InputChecker {



	/**
	 * 姓の入力チェックをする
	 * @param familyName
	 * @return
	 */
	public String familyNameChk(String familyName) {
		String result = "OK";

		if (familyName.equals("")) {
			result = "姓を入力してください。";

		} else if (familyName.length() < 1 || familyName.length() > 16) {
			result = "姓は1文字以上16文字以下で入力してください。";

		} else if (!familyName.matches("^[a-zA-Z一-龠あ-ん]+$")) {
			result = "姓は半角英語、漢字またはひらがなで入力してください。";
		}

		return result;
	}


	public String firstNameChk(String firstName) {
		String result = "OK";

		if (firstName.equals("")) {
			result = "名を入力してください。";
		} else if (firstName.length() < 1 || firstName.length() > 16) {
			result = "名は1文字以上16文字以下で入力してください。";
		} else if (!firstName.matches("^[a-zA-Z一-龠あ-ん]+$")) {
				result = "名は半角英語、漢字またはひらがなで入力してください。";
		}

		return result;
	}


	public String familyNameKanaChk(String familyNameKana) {
		String result = "OK";

		if (familyNameKana.equals("")) {
			result = "姓ふりがなを入力してください。";
		} else if (familyNameKana.length() < 1 || familyNameKana.length() > 16) {
			result = "姓ふりがなは1文字以上16文字以下で入力してください。";
		} else if (!familyNameKana.matches("^[ぁ-ん]+$")) {
				result = "姓ふりがなはひらがなで入力してください。";
		}

		return result;
	}


	public String firstNameKanaChk(String firstNameKana) {
		String result = "OK";

		if (firstNameKana.equals("")) {
			result = "名ふりがなを入力してください。";
		} else if (firstNameKana.length() < 1 || firstNameKana.length() > 16) {
			result = "名ふりがなは1文字以上16文字以下で入力してください。";
		} else if (!firstNameKana.matches("^[ぁ-ん]+$")) {
				result = "名ふりがなはひらがなで入力してください。";
		}

		return result;
	}


	public String emailChk(String email) {
		String result = "OK";

		if (email.equals("")) {
			result = "メールアドレスを入力してください。";
		} else if (email.length() < 18 || email.length() > 32) {
			result = "メールアドレスは18文字以上32文字以下で入力してください。";
		} else if (!email.matches(".*")) {
			result = "メールアドレスは半角英数字と半角記号で入力してください。";
		}

		return result;
	}


	public String userIdChk(String userId) {
		String result = "OK";

		if (userId.equals("")) {
			result = "ログインIDを入力してください。";
		} else if (userId.length() < 1 || userId.length() > 16) {
			result = "ログインIDは1文字以上16文字以下で入力してください。";
		} else if (!userId.matches("^[a-zA-Z0-9]+$")) {
			result = "ログインIDは半角英数字で入力してください。";
		}

		return result;
	}


	public String passwordChk(String password) {
		String result = "OK";

		if (password.equals("")) {
			result = "パスワードを入力してください。";
		} else if (password.length() < 1 || password.length() > 8) {
			result = "パスワードは1文字以上8文字以下で入力してください。";
		} else if (!password.matches("^[a-zA-Z0-9]+$")) {
			result = "パスワードは半角英数字で入力してください。";
		}

		return result;
	}


	public String telNumberChk(String telNumber) {
		String result = "OK";

		if (telNumber.equals("")) {
			result = "電話番号を入力してください。";
		} else if (telNumber.length() < 1 || telNumber.length() > 13) {
			result = "電話番号は1文字以上13文字以下で入力してください。";
		} else if (!telNumber.matches("^[0-9]+$")) {
			result = "電話番号は半角数字で入力してください。";
		}

		return result;
	}


	public String userAddressChk(String userAddress) {
		String result = "OK";

		if (userAddress.equals("")) {
			result = "住所を入力してください。";
		} else if (userAddress.length() < 15 || userAddress.length() > 50) {
			result = "住所は15文字以上50文字以下で入力してください。";
		} else if (!userAddress.matches("^[a-zA-Z0-9ぁ-ん一-龠々!-~]+$")) {
			result = "住所は半角英数字、漢字、カタカナ及び半角記号で入力してください。";
		}

		return result;
	}

	/**
	 * 検索ワードの入力チェック
	 * @param keyword
	 * @return
	 */
	public String keywordChk(String keywords) {
		String result = "OK";

		if(keywords.length() < 1 || keywords.length() >16) {
			result = "検索は、1文字以上16文字以下で入力してください。";
		}else if(!keywords.matches("^[a-zA-Z一-龠あ-ん\\s]+$")) {
			result = "検索は半角英数字、漢字、ひらがなで入力してください。";
		}

		return result;
	}


}
