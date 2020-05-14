package com.team.service;

import java.util.*;
import com.team.bean.Students;
import com.team.view.PrimaryMenu;

/**
 * @Description: 学生信息管理类
 * @author: 李甘伟,黎仕展
 * @version:
 * @date:May 5, 20202:24:58 PM
 */

public class StudentsInforMange {

	// 创建一个集合用于存储学生信息
	private static final List<Students> STUDENTS_ARRAY_LIST = new ArrayList<>();
	private static final Scanner SYS_SCANNER = new Scanner(System.in);

	public static void stuMenu() {
		System.out.println("\t\t\t\t*欢迎使用学生管理系统*\n");
		while (true) {
			System.out.println("\t\t   ***************学生信息管理***************");
			System.out.println("\t\t                 1.录入学生信息");
			System.out.println("\t\t                 2.打印学生信息");
			System.out.println("\t\t                 3.录入学生成绩");
			System.out.println("\t\t                 4.打印学生成绩表");
			System.out.println("\t\t                 5.排序学生信息");
			System.out.println("\t\t                 6.查找学生信息");
			System.out.println("\t\t                 7.删除学生信息");
			System.out.println("\t\t                 8.修改学生信息");
			System.out.println("\t\t                 9.统计学生信息");
			System.out.println("\t\t                 0.退出学生系统");
			System.out.println("\t\t   *****************************************");
			System.out.println("请输入您的选择(0~8)：");
			String select = SYS_SCANNER.next();
			switch (select) {
			case "1":
				addStudentInfo();
				break;
			case "2":
				printStudentInfo();
				break;
			case "3":
				addStudentScore();
				break;
			case "4":
				printStudentScort();
				break;
			case "5":
				SortStuInfo.sortStudentInfo(STUDENTS_ARRAY_LIST);
				break;
			case "6":
				searchStudentInfo();
				break;
			case "7":
				deleteStudentInfo();
				break;
			case "8":
				updateStudentInfo();
				break;
			case "9":
				statisticsStuInfo();
				break;
			case "0":
				return;
			default:
				System.err.println("您的命令输入有误，请重新确认！");
				PrimaryMenu.await();
			}
		}
	}

	/**
	 * 统计分数段、平均分、不及格学生
	 *
	 */
	private static void statisticsStuInfo() {
		if (STUDENTS_ARRAY_LIST.size() == 0) {
			System.out.println("学生信息管理系统空空如也!!!");
			PrimaryMenu.buttonEnter();
			return;

		}
		if (STUDENTS_ARRAY_LIST.size() == 1) {
			System.out.println("学生管理系统中只有一条记录:");
			Students students = STUDENTS_ARRAY_LIST.get(0);
			showInfoTitle();
			System.out.println(students.p());
			PrimaryMenu.buttonEnter();
			return;
		}
		
		while (true) {
			System.out.println("(1)平均分  (2)统计不及格的学生");
		    System.out.println("请输入您的选择:");
		    String select = SYS_SCANNER.next();
			switch (select) {
			case "1":// 统计平均分
				printAvgScore();
				if(PrimaryMenu.isExit())return;
				break;
			case "2":// 统计不及格的学生
				flunkStuInfo();
				if(PrimaryMenu.isExit())return;
				break;
			default:
				System.err.println("您的命令输入有误，请重新确认！");
				PrimaryMenu.await();
			}
		}

	}

	/**
	 * 不及格的学生
	 */
	private static void flunkStuInfo() {
		System.out.println("不及格的学生有:");
		System.out.println("学号\t姓名\t平均成绩");
		for(Students s: STUDENTS_ARRAY_LIST) {
			if(s.scoreAvg()<60) {
				System.err.println(s.getStuNum()+"\t"+s.getName()+"\t"+s.scoreAvg());
			}
		}
	
	}

	/**
	 * 统计平均分
	 */
	private static void printAvgScore() {
		System.out.println("学号\t姓名\t平均成绩");
		for(Students s:STUDENTS_ARRAY_LIST) {
			System.out.println(s.getStuNum()+"\t"+s.getName()+"\t"+s.scoreAvg());
		}
		
	}

	private static void updateStudentInfo() {
		// 判断集合是否为空
		if (isArrayEmpty())
			return;

		while (true) {
			System.out.println("请输入您要修改学生的学号:");
			String strNum = SYS_SCANNER.next();
			if (PrimaryMenu.putIsEmpty(strNum) || PrimaryMenu.isNumber(strNum)) {
				System.err.println("数据不合法,请重新输入");
			} else {
				Students students1 = null;
				for (int i = 0; i < STUDENTS_ARRAY_LIST.size(); i++) {
					if (STUDENTS_ARRAY_LIST.get(i).getStuNum().equals(strNum)) {
						students1 = STUDENTS_ARRAY_LIST.get(i);
						System.out.println("待修改的学生信息为:");
						showInfoTitle();
						System.out.println(students1.p());
						System.out.println();
						UpdataStuInfo.updataStudentInfo(STUDENTS_ARRAY_LIST, students1);
						return;
					}
				}
				if (students1 == null) {
					System.err.println("没有找到该学生信息!");
					PrimaryMenu.await();
				}
				if (PrimaryMenu.isExit())
					return;
			}
		}

	}

	private static void deleteStudentInfo() {
		if (isArrayEmpty())
			return;
		print("全部删除（1）or 部分删除（2）");
		switch (SYS_SCANNER.next()) {
		case "1":
			STUDENTS_ARRAY_LIST.clear();
			break;
		case "2":

			while (true) {
				System.out.println("请输入您要删除学生的学号:");
				String strNum = SYS_SCANNER.next();
				if (PrimaryMenu.putIsEmpty(strNum) || PrimaryMenu.isNumber(strNum)) {
					System.err.println("数据不合法,请重新输入");
				} else {
					Students students1 = null;
					for (int i = 0; i < STUDENTS_ARRAY_LIST.size(); i++) {
						if (STUDENTS_ARRAY_LIST.get(i).getStuNum().equals(strNum)) {
							students1 = STUDENTS_ARRAY_LIST.get(i);
							System.out.println("待删除的学生信息为:");
							showInfoTitle();
							System.out.println(students1.p());

							System.out.println("是否将学号为:" + students1.getStuNum() + "的学生信息删除,请确认(y/n)!");
							String YN = SYS_SCANNER.next();
							if ("y".equals(YN)) {
								STUDENTS_ARRAY_LIST.remove(i);

								System.out.println("删除成功!!");

							} else {
								System.out.println("删除失败!!");
							}

							if (PrimaryMenu.isExit()) {
								return;
							}
						}
					}
					if (students1 == null) {
						System.err.println("没有找到该学生信息!");
						PrimaryMenu.await();
					}
					if (PrimaryMenu.isExit())
						return;
				}

			}

		default:
			return;
		}
	}

	private static void searchStudentInfo() {
		// 判断集合是否为空
		if (isArrayEmpty())
			return;
		while (true) {
			System.out.println("请输入您要查找的学生学号:");
			String stuNum = SYS_SCANNER.next();
			if (PrimaryMenu.putIsEmpty(stuNum) || PrimaryMenu.isNumber(stuNum)) {
				System.err.println("数据不合法!!请重新输入:");
			} else {
				Students students1 = null;
				for (int i = 0; i < STUDENTS_ARRAY_LIST.size(); i++) {
					if (STUDENTS_ARRAY_LIST.get(i).getStuNum().equals(stuNum)) {
						students1 = STUDENTS_ARRAY_LIST.get(i);
						System.out.println("该学生信息如下:");
						showInfoTitle();
						System.out.println(students1.p());
						PrimaryMenu.buttonEnter();
						return;
					}
				}
				if (students1 == null) {
					System.err.println("没有找到该学生信息!");
					PrimaryMenu.await();
				}
				if (PrimaryMenu.isExit())
					return;
			}
		}

	}

	private static void printStudentScort() {
		// 判断集合是否为空
		if (isArrayEmpty())
			return;
		PrimaryMenu.showTitle();
		for (Students students : STUDENTS_ARRAY_LIST) {
			System.out.println(students.printStuScort());
		}
		PrimaryMenu.buttonEnter();

	}

	private static void addStudentScore() {
		if (isArrayEmpty())
			return;
		for (Students s : STUDENTS_ARRAY_LIST) {
			if (isEm(s)) {
				print("当前学号为" + s.getStuNum() + "的学生未输入成绩");
				print("输入英语成绩");
				s.setEnglish(SYS_SCANNER.nextInt());
				print("输入java成绩");
				s.setJava(SYS_SCANNER.nextInt());
				print("输入数学成绩");
				s.setMath(SYS_SCANNER.nextInt());
			}
		}
		System.out.println("成绩录入成功!!");

	}

	private static void printStudentInfo() {
		// 判断集合是否为空
		if (isArrayEmpty())
			return;
		System.out.println();
		System.out.println("*************************学生信息表************************\n");
		System.out.println("身份证号\t\t\t学号\t  姓名\t性别\t年龄\t ");
		System.out.println("*********************************************************");
		for (Students students1 : STUDENTS_ARRAY_LIST) {
			System.out.println(students1.p());
		}
		PrimaryMenu.buttonEnter();

	}

	private static void addStudentInfo() {

		while (true) {
			int n = STUDENTS_ARRAY_LIST.size();
			System.out.println("当前为第" + (n + 1) + "人，请输入相关信息");
			Students student = new Students();
			while (true) {
				System.out.println("请输入学生身份证号：");
				String string = SYS_SCANNER.next();
				// 进行数据校验
				if (PrimaryMenu.isIDRepetition(STUDENTS_ARRAY_LIST, string)) {
					System.err.println("您要录入的身份证号已经存在,请重新输入!");
				} else if (PrimaryMenu.cheackID(string)) {
					System.err.println("身份证号非法! 请重新输入");
				} else {
					student.setId(string);
					break;
				}
			}

			while (true) {
				System.out.println("请输入学生学号：");
				String stuId = SYS_SCANNER.next();
				if (PrimaryMenu.isNumRepetition(STUDENTS_ARRAY_LIST, stuId)) {
					System.err.println("您要录入的学生学号已经存在,请重新输入!");
				} else if (PrimaryMenu.putIsEmpty(stuId)) {
					System.err.println("学号不能为空！请重新输入\n");

				} else if(PrimaryMenu.isNumber(stuId)){
					System.err.println("学号只能是数字!");
				}
				else {
					student.setStuNum(stuId);
					break;
				}
			}

			print("请输入姓名:");
			student.setName(SYS_SCANNER.next());

			PrimaryMenu.inputSex(student);

			PrimaryMenu.inputAge(student);

			STUDENTS_ARRAY_LIST.add(student);
			if (PrimaryMenu.isExit())
				return;

		}

	}

	static void print(String s) {
		System.out.println(s);
	}

	static boolean isEm(Students students) {
		return students.getEnglish() == 0 || students.getJava() == 0 || students.getMath() == 0;
	}

	static boolean isNum(String num) {
		for (Students s : STUDENTS_ARRAY_LIST) {
			if (num.equals(s.getStuNum())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 判断学生信息集合是否为空
	 *
	 * @return :集合为空则返回true,反之false.
	 */
	private static boolean isArrayEmpty() {
		if (STUDENTS_ARRAY_LIST.size() == 0) {
			System.out.println("学生信息管理系统空空如也!!!");
			PrimaryMenu.buttonEnter();
			return true;
		}
		return false;
	}

	/**
	 * 打印信息字段
	 */
	public static void showInfoTitle() {
		System.out.println();
		System.out.println("***********************学生信息表**********************");
		System.out.println("身份证号\t\t\t学号\t  姓名\t性别\t年龄");
		System.out.println("****************************************************");
	}

}
