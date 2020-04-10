package com.ms.bugzilla;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BcryptEncoderTest {

	public static void main(String[] args) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		for(int i=1; i<=10; i++) {
		//	String encodedString = encoder.encode("password@!23@#!");
			String encodedString = encoder.encode("angular");
			System.out.println(encodedString);
		}
		
		// TODO Auto-generated method stub
/*
 * {
    "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJsZWFybiIsImV4cCI6MTU4Njg4Mjk2MCwiaWF0IjoxNTg2Mjc4MTYwfQ.zvaX8WydpDfhpLq0SyqrcgeSrcNkzqfGpcOQu8qXBOohN-dJgkO6eW_86y4agl7JYCAVg3Ub7GJcd1cRkaEVSQ"
}
 * */
	}

}
