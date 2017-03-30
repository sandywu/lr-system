package test;

import java.time.LocalDateTime;

import com.bqq.util.SendEmail;

public class Test {
	public static void main(String[] args) {
		//System.out.println(LocalDateTime.now());
		SendEmail.send("944398239@qq.com", "shanks", "password");
	}
}
