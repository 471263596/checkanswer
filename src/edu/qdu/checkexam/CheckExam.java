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
		List answer = new ArrayList();// �洢��׼�𰸵ļ���
		try {
			anwserFile = new BufferedReader(new FileReader("D:\\��.txt"));// �������򿪱�׼���ļ�
			String a;// �����洢answer.txt��ĳһ��
			while ((a = anwserFile.readLine()) != null) {// ʹ��readLine������һ�ζ�һ��
				answer.add(a);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return answer;
	}
	
	public List getStuAnswer(File files){
		List stuAnswer = new ArrayList();// �洢һ��ѧ���𰸵ļ���
		try {
			BufferedReader stuFile = new BufferedReader(new FileReader(files.getAbsolutePath()));
			String s;
			while ((s = stuFile.readLine()) != null) {// ʹ��readLine������һ�ζ�һ��
				stuAnswer.add(s);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stuAnswer;
	}
	
	public String[] check(){
		File root = new File("D:\\����");// �ļ���·��
		File[] files = root.listFiles();// �ļ������ļ��б�
		String[] result =new String[files.length];//�洢ÿ��ѧ�������ͳɼ�
		for (int i = 0; i < files.length; i++) {//����ÿ��ѧ���𰸵��ļ�
// ÿ��ѧ���Ĵ�
//			BufferedReader stuFile = new BufferedReader(new FileReader(files[i].getAbsolutePath()));
//			String s;
//			ArrayList stuAnswer = new ArrayList();// �����洢ѧ���𰸵ļ���
//			int total = 100;// �ܷ�Ϊ100
//			while ((s = stuFile.readLine()) != null) {// ʹ��readLine������һ�ζ�һ��
//				int len2 = s.length();
//				for (int j = 0; j < len2; j++) {
//					stuAnswer.add(s.charAt(j)); // ��ÿ�еĴ𰸱���ֽڣ�һ�������뼯��
//				}
//			}
			List answer=getAnswer();//���÷�����ȡ��׼��
			List stuAnswer=getStuAnswer(files[i]);//���÷�����ȡѧ����
			int total=100;// �ܷ�Ϊ100
			for (int j = 0; j < answer.size(); j++) {
				char[] a=answer.get(j).toString().toCharArray();//ÿ�д�תΪ����
				char[] b=stuAnswer.get(j).toString().toCharArray();
				for (int k = 0; k < a.length; k++) {
					if ( b[k]!= a[k]) {// �Ƚϱ�׼�𰸺�ѧ���𰸵�������
						total -= 4;
					}
				}
			}
			result[i] = files[i].getName() + "    " + total;//�洢ÿ��ѧ�������ͳɼ�
			System.out.println(result[i]);
		}
		return result;
	}
	public void storeGrade(){
		try {
			File f = new File("D:\\grade.txt");// �����Ƿ����grade.txt
			f.createNewFile();// ���������½��������洢�ɼ�
			BufferedWriter grade = new BufferedWriter(new FileWriter(f));
			String[] result=check();//���÷�����ÿ��Խ��
			for (int i = 0; i < result.length; i++) {
				grade.write(result[i]);
				grade.newLine(); // ����������ǻس�����ÿ�����������newLine()
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
