package edu.qdu.checkexam;

import java.awt.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CheckExam {
	public static void main(String[] args) throws IOException {
		BufferedReader anwserFile=null;
		BufferedWriter grade=null;
		try {
			anwserFile = new BufferedReader(new FileReader("D:\\答案.txt"));// 标准答案
			String a;// 读取answer.txt的每一行
			ArrayList answer = new ArrayList();// 记录标准答案的集合answer
			while ((a = anwserFile.readLine()) != null) {// 使用readLine方法，一次读一行
				int len1 = a.length();// 每一行的长度
				for (int i = 0; i < len1; i++) {
					answer.add(a.charAt(i)); // 将获取的每一个字节赋值给answer集合
				}
			}

			File f = new File("D:\\grade.txt");// 查找是否存在grade.txt
			f.createNewFile();// 不存在则新建，用来存储成绩
			grade = new BufferedWriter(new FileWriter(f));

			File root = new File("D:\\考试");// 文件夹路径
			File[] files = root.listFiles();// 文件夹内文件列表
			// String[] resultName = new String[files.length];
			for (int i = 0; i < files.length; i++) {
				// resultName[i] = files[i].getAbsolutePath();
				// 每个学生的答案
				BufferedReader stuFile = new BufferedReader(new FileReader(files[i].getAbsolutePath()));
				String s;
				ArrayList stuAnswer = new ArrayList();// 用来存储学生答案的集合
				int total = 100;// 总分为100
				while ((s = stuFile.readLine()) != null) {// 使用readLine方法，一次读一行
					int len2 = s.length();
					for (int j = 0; j < len2; j++) {
						stuAnswer.add(s.charAt(j)); // 将每行的答案变成字节，一个个存入集合
					}
				}
				for (int j = 0; j < answer.size(); j++) {
					if (stuAnswer.get(j) != answer.get(j)) {// 比较标准到和学生答案的相似性
						total -= 4;
					}
				}
				String result = files[i].getName() + "    " + total;
				grade.write(result);
				grade.newLine(); // 这个方法就是回车符，每次输出完必需加newLine()
				grade.flush();
				System.out.println(result);
			    stuFile.close();
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			anwserFile.close();
			grade.close();
		}

	}

}
