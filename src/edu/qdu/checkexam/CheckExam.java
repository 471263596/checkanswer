package edu.qdu.checkexam;

import java.util.List;
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
	public List getAnswer(){
		BufferedReader anwserFile=null;
		List answer = new ArrayList();// 存储标准答案的集合
		try {
			anwserFile = new BufferedReader(new FileReader("D:\\答案.txt"));// 缓冲流打开标准答案文件
			String a;// 用来存储answer.txt的某一行
			while ((a = anwserFile.readLine()) != null) {// 使用readLine方法，一次读一行
				answer.add(a);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return answer;
	}
	
	public List getStuAnswer(File files){
		List stuAnswer = new ArrayList();// 存储一个学生答案的集合
		try {
			BufferedReader stuFile = new BufferedReader(new FileReader(files.getAbsolutePath()));
			String s;
			while ((s = stuFile.readLine()) != null) {// 使用readLine方法，一次读一行
				stuAnswer.add(s);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stuAnswer;
	}
	
	public String[] check(){
		File root = new File("D:\\考试");// 文件夹路径
		File[] files = root.listFiles();// 文件夹内文件列表
		String[] result =new String[files.length];//存储每个学生姓名和成绩
		for (int i = 0; i < files.length; i++) {//遍历每个学生答案的文件
// 每个学生的答案
//			BufferedReader stuFile = new BufferedReader(new FileReader(files[i].getAbsolutePath()));
//			String s;
//			ArrayList stuAnswer = new ArrayList();// 用来存储学生答案的集合
//			int total = 100;// 总分为100
//			while ((s = stuFile.readLine()) != null) {// 使用readLine方法，一次读一行
//				int len2 = s.length();
//				for (int j = 0; j < len2; j++) {
//					stuAnswer.add(s.charAt(j)); // 将每行的答案变成字节，一个个存入集合
//				}
//			}
			List answer=getAnswer();//调用方法读取标准答案
			List stuAnswer=getStuAnswer(files[i]);//调用方法读取学生答案
			int total=100;// 总分为100
			for (int j = 0; j < answer.size(); j++) {
				char[] a=answer.get(j).toString().toCharArray();//每行答案转为数组
				char[] b=stuAnswer.get(j).toString().toCharArray();
				for (int k = 0; k < a.length; k++) {
					if ( b[k]!= a[k]) {// 比较标准答案和学生答案的相似性
						total -= 4;
					}
				}
			}
			result[i] = files[i].getName() + "    " + total;//存储每个学生姓名和成绩
			System.out.println(result[i]);
		}
		return result;
	}
	public void storeGrade(){
		try {
			File f = new File("D:\\grade.txt");// 查找是否存在grade.txt
			f.createNewFile();// 不存在则新建，用来存储成绩
			BufferedWriter grade = new BufferedWriter(new FileWriter(f));
			String[] result=check();//调用方法获得考试结果
			for (int i = 0; i < result.length; i++) {
				grade.write(result[i]);
				grade.newLine(); // 这个方法就是回车符，每次输出完必需加newLine()
				grade.flush();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		CheckExam exam=new CheckExam();
		exam.storeGrade();

	}

}
