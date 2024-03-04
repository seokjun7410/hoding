package org.example;

import java.util.ArrayList;
import java.util.Random;

public class InfinitLoop {

	public static void main(String[] args) {
		Random random = new Random();
		while(true){
			ArrayList<String> dummyList = new ArrayList<>();
			for (int loop = 0; loop < 1000; loop++) {
				String temp = "ABCDEFG";
				dummyList.add(temp);
			}
		}
	}
}
