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
			anwserFile = new BufferedReader(new FileReader("D:\\��.txt"));// ��׼��
			String a;// ��ȡanswer.txt��ÿһ��
			ArrayList answer = new ArrayList();// ��¼��׼�𰸵ļ���answer
			while ((a = anwserFile.readLine()) != null) {// ʹ��readLine������һ�ζ�һ��
				int len1 = a.length();// ÿһ�еĳ���
				for (int i = 0; i < len1; i++) {
					answer.add(a.charAt(i)); // ����ȡ��ÿһ���ֽڸ�ֵ��answer����
				}
			}

			File f = new File("D:\\grade.txt");// �����Ƿ����grade.txt
			f.createNewFile();// ���������½��������洢�ɼ�
			grade = new BufferedWriter(new FileWriter(f));

			File root = new File("D:\\����");// �ļ���·��
			File[] files = root.listFiles();// �ļ������ļ��б�
			// String[] resultName = new String[files.length];
			for (int i = 0; i < files.length; i++) {
				// resultName[i] = files[i].getAbsolutePath();
				// ÿ��ѧ���Ĵ�
				BufferedReader stuFile = new BufferedReader(new FileReader(files[i].getAbsolutePath()));
				String s;
				ArrayList stuAnswer = new ArrayList();// �����洢ѧ���𰸵ļ���
				int total = 100;// �ܷ�Ϊ100
				while ((s = stuFile.readLine()) != null) {// ʹ��readLine������һ�ζ�һ��
					int len2 = s.length();
					for (int j = 0; j < len2; j++) {
						stuAnswer.add(s.charAt(j)); // ��ÿ�еĴ𰸱���ֽڣ�һ�������뼯��
					}
				}
				for (int j = 0; j < answer.size(); j++) {
					if (stuAnswer.get(j) != answer.get(j)) {// �Ƚϱ�׼����ѧ���𰸵�������
						total -= 4;
					}
				}
				String result = files[i].getName() + "    " + total;
				grade.write(result);
				grade.newLine(); // ����������ǻس�����ÿ�����������newLine()
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
