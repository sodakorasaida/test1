package com.internousdev.sukesyunshop.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.sukesyunshop.dao.DestinationDAO;
import com.internousdev.sukesyunshop.dto.DestinationDTO;
import com.internousdev.sukesyunshop.util.Validation;
import com.opensymphony.xwork2.ActionSupport;

public class ConfirmDestAction extends ActionSupport implements SessionAware {

	// 宛先情報変数
	private String familyName;
	private String firstName;
	private String familyNameKana;
	private String firstNameKana;
	private int sex;
	private String sexStr;
	private String email;
	private String telNumber;
	private String userAddress;

	// 各入力のエラーメッセージ

	private String familyMessage;
	private String firstMessage;
	private String familyKanaMessage;
	private String firstKanaMessage;
	private String emailMessage;
	private String telNumberMessage;
	private String addressMessage;

	// 宛先情報を格納
	public Map<String, Object> session;

	// 各機能が外部クラスに存在しているのでインスタンス化

	public Validation validation = new Validation();

	public DestinationDAO destDAO = new DestinationDAO();

	public DestinationDTO destDTO = new DestinationDTO();

	/*------実行メソッド-------*/
	public String execute() {

		/*------- 姓のエラー処理 ------*/
		if (validation.emptyValid(familyName)) {
			System.out.println(familyName + "1");
			familyMessage = "姓が未入力です";
			return ERROR;
		}
		if (validation.overUnderValid(familyName, 1, 16)) {
			System.out.println(familyName + "2");
			familyMessage = "姓は1文字以上16文字以下で入力してください。";
			return ERROR;
		}
		if (validation.hiraganaValid(familyName) && validation.harfEnglishValied(familyName)
				&& validation.kanjiValid(familyName)) {
			System.out.println(familyName + "3");
			familyMessage = "姓は半角英語or漢字orひらがなで入力してください";
			return ERROR;
		}

		/*------- 名のエラー処理 ------*/
		if (validation.emptyValid(firstName)) {
			System.out.println(firstName + "4");
			firstMessage = "名が未入力です";
			return ERROR;
		}
		if (validation.overUnderValid(firstName, 1, 16)) {
			System.out.println(firstName + "5");
			firstMessage = "名は1文字以上16文字以下で入力してください。";
		}
		if (validation.harfEnglishValied(firstName) && validation.kanjiValid(firstName)
				&& validation.hiraganaValid(firstName)) {
			System.out.println(firstName + "6");
			firstMessage = "名は半角英語or漢字orひらがなで入力してください";
			return ERROR;
		}

		/*------- 姓ふりがなのエラー処理 ------*/
		if (validation.emptyValid(familyNameKana)) {
			System.out.println(familyNameKana + "7");
			familyKanaMessage = "姓ふりがなが未入力です";
			return ERROR;
		}
		if (validation.overUnderValid(familyNameKana, 1, 16)) {
			System.out.println(familyNameKana + "8");
			familyKanaMessage = "姓ふりがなは1文字以上16文字以下で入力してください。";
			return ERROR;
		}
		if (validation.hiraganaValid(familyNameKana)) {
			System.out.println(familyNameKana + "9");
			familyKanaMessage = "姓ふりがなはひらがなで入力してください";
			return ERROR;

		}

		/*------- 名ふりがなのエラー処理 ------*/
		if (validation.emptyValid(firstNameKana)) {
			System.out.println(firstNameKana + "10");
			firstKanaMessage = "姓ふりがなが未入力です";
			return ERROR;
		}
		if (validation.overUnderValid(firstNameKana, 1, 16)) {
			System.out.println(firstNameKana + "11");
			firstKanaMessage = "姓ふりがなは1文字以上16文字以下で入力してください。";
			return ERROR;
		}
		if (validation.hiraganaValid(firstNameKana)) {
			System.out.println(firstNameKana + "12");
			firstKanaMessage = "姓ふりがなはひらがなで入力してください";
			return ERROR;
		}

		/*------- 性別選択 -------*/
		if (sex == 0) {
			System.out.println("akasatana");
			sexStr = "男性";
		}

		if (sex == 1) {
			System.out.println("aiueo");
			sexStr = "女性";
		}

		/*------- 住所のエラー処理 ------*/
		if (validation.emptyValid(userAddress)) {
			System.out.println(userAddress + "13");
			addressMessage = "住所が未入力です";
			return ERROR;
		}
		if (validation.overUnderValid(userAddress, 15, 50)) {
			System.out.println(userAddress + "14");
			addressMessage = "住所は15文字以上50文字以下で入力してください。";
			return ERROR;
		}
		if (!(validation.harfEnglishValied(userAddress) && validation.hiraganaValid(userAddress))) {
			System.out.println(userAddress + "15");
			addressMessage = "住所は半角英数字and漢字and半角記号or全角カタカナで入力してください";
			return ERROR;
		}

		/*------- 電話番号のエラー処理 ------*/
		if (validation.emptyValid(telNumber)) {
			System.out.println(telNumber + "16");
			telNumberMessage = "電話番号が未入力です";
			return ERROR;
		}
		if (validation.overUnderValid(telNumber, 11, 13)) {
			System.out.println(telNumber + "17");

			telNumberMessage = "電話番号は11文字以上13文字以下で入力してください。";
			return ERROR;
		}
		if (validation.harfEngNumValied(telNumber)) {
			System.out.println(telNumber + "18");

			telNumberMessage = "電話番号は半角数字で入力してください";
			return ERROR;
		}

		/*------- メールアドレスのエラー処理 ------*/
		if (validation.emptyValid(email)) {
			System.out.println(email + "19");

			emailMessage = "メールアドレスが未入力です";
			return ERROR;
		}
		if (validation.overUnderValid(email, 18, 32)) {
			System.out.println(email + "20");

			emailMessage = "メールアドレスは18文字以上32文字以下で入力してください。";
			return ERROR;
		}
		if (validation.emailAddressValid(email)) {
			System.out.println(email + "21");

			emailMessage = "メールアドレスは半角英数字・半角記号で入力してください";
			return ERROR;
		}
		/*-------成功処理 処理 ------*/
		return SUCCESS;

	}

	/*------ 宛先情報のゲッターセッター ------*/
	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getFamilyNameKana() {
		return familyNameKana;
	}

	public void setFamilyNameKana(String familyNameKana) {
		this.familyNameKana = familyNameKana;
	}

	public String getFirstNameKana() {
		return firstNameKana;
	}

	public void setFirstNameKana(String firstNameKana) {
		this.firstNameKana = firstNameKana;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getSexStr() {
		return sexStr;
	}

	public void setSexStr(String sexStr) {
		this.sexStr = sexStr;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelNumber() {
		return telNumber;
	}

	public void setTelNumber(String telNumber) {
		this.telNumber = telNumber;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	/*------ エラーメッセージのゲッターセッター ------*/
	public String getFamilyMessage() {
		return familyMessage;
	}

	public void setFamilyMessage(String familyMessage) {
		this.familyMessage = familyMessage;
	}

	public String getFirstMessage() {
		return firstMessage;
	}

	public void setFirstMessage(String firstMessage) {
		this.firstMessage = firstMessage;
	}

	public String getFamilyKanaMessage() {
		return familyKanaMessage;
	}

	public void setFamilyKanaMessage(String familyKanaMessage) {
		this.familyKanaMessage = familyKanaMessage;
	}

	public String getFirstKanaMessage() {
		return firstKanaMessage;
	}

	public void setFirstKanaMessage(String firstKanaMessage) {
		this.firstKanaMessage = firstKanaMessage;
	}

	public String getEmailMessage() {
		return emailMessage;
	}

	public void setEmailMessage(String emailMessage) {
		this.emailMessage = emailMessage;
	}

	public String getTelNumberMessage() {
		return telNumberMessage;
	}

	public void setTelNumberMessage(String telNumberMessage) {
		this.telNumberMessage = telNumberMessage;
	}

	public String getAddressMessage() {
		return addressMessage;
	}

	public void setAddressMessage(String addressMessage) {
		this.addressMessage = addressMessage;
	}

	/* セッションのセッター */

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

}
