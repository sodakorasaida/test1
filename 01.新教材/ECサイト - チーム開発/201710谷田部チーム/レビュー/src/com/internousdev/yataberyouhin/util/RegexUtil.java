package com.internousdev.yataberyouhin.util;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class RegexUtil {

	public static void main(String args[]) {

		// 検索する文字列
		String str = "本";

		// 判定するパターンを生成
		Pattern p = Pattern.compile("^[a-zA-Z0-9ぁ-ん一-龠]+$");
		Matcher m = p.matcher(str);

		while (m.find()) {
			System.out.println(m.group());
		}
	}

}
