package com.example.otpApi.Component;

import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class OtpUtils {
	public String genrateotp() {
		Random random=new Random();
		int randomnum=random.nextInt(999999);
		String otput=Integer.toString(randomnum);
		while(otput.length()<6) {
			otput="0"+otput;
		}
		return otput;
	}

}
