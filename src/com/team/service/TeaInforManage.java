package com.team.service;

import com.team.bean.Teachers;
import com.team.view.PrimaryMenu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @Description: 教师信息管理类。该类中要有教师人数域、教师列表域（教师类对象数组），
 *               并且对教师信息进行管理，例如教师信息录入、课时数录入、输出教师列表等。更多功能自选。
 * @author: 黎仕展
 * @version:1.0
 * @date: 2020年5月1日
 *
 */
public class TeaInforManage {
	/**
	 * 定义一个集合用于存储教师信息数据
	 */
	private static final List<Teachers> TEACHERS_ARRAY_LIST = new ArrayList<>();
	// 输入常量
	private static final Scanner SYS_SCANNER = new Scanner(System.in);

	public static void teachMenu() {
		System.out.println("\t\t\t\t*欢迎使用教师管理系统*\n");

		while (true) {
			System.out.println("\t\t   ***************教师信息管理***************");
			System.out.println("\t\t                 1.录入教师信息  ");
			System.out.println("\t\t                 2.打印教师信息    ");
			System.out.println("\t\t                 3.录入课时数  ");
			System.out.println("\t\t                 4.打印课时数  ");
			System.out.println("\t\t                 5.删除教师信息  ");
			System.out.println("\t\t                 6.修改教师信息  ");
			System.out.println("\t\t                 0.退出教师系统  ");
			System.out.println("\t\t   *****************************************");
			System.out.println("请输入您的选择(0~3)：");
			String select = SYS_SCANNER.next();
			switch (select) {
			case "1":
				addTeacherInfo();
				break;
			case "2":
				showTeachInfo();
				break;
			case "3":
				addTeacClassHour();
				break;
			case "4":
				printClassNum();
				break;
			case "5":
				deleteTeacherInfo();
				break;
			case "6":
				updataTeacherInfo();
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
	 * 打印课时数
	 */
	private static void printClassNum() {
		if (isArrayEmpty())
			return;
		System.out.println();
		System.out.println("*******教师课时表*******");
		System.out.println("工号\t  姓名\t课时数");
		System.out.println("*********************");

		for (Teachers teachers : TEACHERS_ARRAY_LIST) {
			System.out.println(teachers.p());
		}
		System.out.println();
		PrimaryMenu.buttonEnter();
	}

	/**
	 * 修改教师信息
	 */
	private static void updataTeacherInfo() {
		// 判断集合是否为空
		if (isArrayEmpty())
			return;

		while (true) {
			System.out.println("请输入您要修改教师的工号:");
			String strNum = SYS_SCANNER.next();
			if (PrimaryMenu.putIsEmpty(strNum) || PrimaryMenu.isNumber(strNum)) {
				System.err.println("数据不合法,请重新输入");
			} else {
				Teachers teachers = null;
				for (int i = 0; i < TEACHERS_ARRAY_LIST.size(); i++) {
					if (TEACHERS_ARRAY_LIST.get(i).getTeachId().equals(strNum)) {
						teachers = TEACHERS_ARRAY_LIST.get(i);
						System.out.println("待修改的教师信息为:");

						showInfoTitle();
						System.out.println(teachers);

						UpdataTeaInfo.updataStudentInfo(TEACHERS_ARRAY_LIST, teachers);
						return;
					}
				}
				if (teachers == null) {
					System.err.println("没有找到该教师信息!");
					PrimaryMenu.await();
				}
				if (PrimaryMenu.isExit())
					return;
			}
		}

	}

	/**
	 * 删除教师信息
	 */
	private static void deleteTeacherInfo() {
		if (isArrayEmpty())
			return;
		while (true) {
			System.out.println("请输入您要删除教师的工号:");
			String strNum = SYS_SCANNER.next();
			if (PrimaryMenu.putIsEmpty(strNum) || PrimaryMenu.isNumber(strNum)) {
				System.err.println("数据不合法,请重新输入");
			} else {
				Teachers teachers = null;
				for (int i = 0; i < TEACHERS_ARRAY_LIST.size(); i++) {
					if (TEACHERS_ARRAY_LIST.get(i).getTeachId().equals(strNum)) {
						teachers = TEACHERS_ARRAY_LIST.get(i);
						System.out.println("待删除的教师信息为:");

						showInfoTitle();
						System.out.println(teachers);
						PrimaryMenu.await();
						System.err.println("是否将工号为:" + teachers.getTeachId() + "的教师信息删除,请确认(y/n)!");
						String YN = SYS_SCANNER.next();
						if ("y".equals(YN)) {
							TEACHERS_ARRAY_LIST.remove(i);
							System.out.println("删除成功!!");
						} else {
							System.out.println("删除失败!!");
						}

						if (PrimaryMenu.isExit()) {
							return;
						}
					}
				}
				if (teachers == null) {
					System.err.println("没有找到该教师信息!");
					PrimaryMenu.await();
				}
				if (PrimaryMenu.isExit())
					return;
			}

		}

	}

	/**
	 * 添加课时
	 */
	private static void addTeacClassHour() {
		boolean flag = true;
		while (flag) {
			if (isArrayEmpty()) {
				flag = false;

			} else {
				addClassHour();
				flag = false;
			}
		}
	}

	/**
	 * 判断数据合法性与课时录入
	 */
	private static void addClassHour() {
		Teachers teachers = null;
		while (true) {
			boolean sign = false;
			System.out.println("请输入要录入课时教师的教师工号：");
			String teaId = SYS_SCANNER.next();

			for (int i = 0; i < TEACHERS_ARRAY_LIST.size(); i++) {
				if (teaId.equals(TEACHERS_ARRAY_LIST.get(i).getTeachId())) {
					// 如果存在匹配身份证信息,则获得教师对象
					teachers = TEACHERS_ARRAY_LIST.get(i);
					boolean flag = true;
					while (flag) {
						System.out.println("请输入" + teachers.getName() + "教师的课时数： ");
						String hour = SYS_SCANNER.next();
						if (PrimaryMenu.isNumber(hour)) {
							System.err.println("数据不合法请重新输入！");

						} else {
							teachers.setClassHour(hour);
							System.out.println("课时数录入成功！！");
							if (PrimaryMenu.isExit()) {
								return;
							} else {
								flag = false;
								sign = true;
							}
						}
					}
					if (sign == true) {
						break;
					} else {
						return;
					}
				}
			}
			if (teachers == null) {
				System.err.println("您要录入课时的教师号不存在！！");
				PrimaryMenu.await();
			}
			if (PrimaryMenu.isExit())
				return;
		}
	}

	/**
	 * 遍历所有教师信息
	 */
	private static void showTeachInfo() {
		if (isArrayEmpty())
			return;
		showInfoTitle();
		for (Teachers teachers : TEACHERS_ARRAY_LIST) {
			System.out.println(teachers.toString());
		}
		System.out.println();
		PrimaryMenu.buttonEnter();
	}

	/**
	 * 添加教师主要信息
	 */
	private static void addTeacherInfo() {

		Teachers teacher = null;
		boolean flga = true;
		while (flga) {
			while (true) {
				System.out.println("请输入教师身份证号：");
				String string = SYS_SCANNER.next();
				if (PrimaryMenu.isIDRepetition(TEACHERS_ARRAY_LIST, string)) {
					System.err.println("您要录入的身份证号已经存在,请重新输入!");
				} else if (PrimaryMenu.cheackID(string)) {
					System.err.println("身份证号非法! 请重新输入");

				} else {
					teacher = new Teachers();
					teacher.setId(string);
					break;
				}
			}
			while (true) {
				System.out.println("请输入教师姓名：");
				String name = SYS_SCANNER.next();
				if (PrimaryMenu.putIsEmpty(name)) {
					System.err.println("教师姓名不能为空！请重新输入\n");

				} else {
					teacher.setName(name);
					break;
				}
			}

			while (true) {
				System.out.println("请输入教职工号：");
				String teachId = SYS_SCANNER.next();
				if (PrimaryMenu.isNumRepetition(TEACHERS_ARRAY_LIST, teachId)) {
					System.err.println("您要录入的教职工号已经存在,请重新输入!");
				} else if (PrimaryMenu.putIsEmpty(teachId)) {
					System.err.println("教职工号不能为空！请重新输入\n");

				} else {
					teacher.setTeachId(teachId);
					break;
				}
			}

			// 输入性别
			PrimaryMenu.inputSex(teacher);

			// 输入年龄
			PrimaryMenu.inputAge(teacher);

			while (true) {
				System.out.println("请输入教师专业领域:");
				String profession = SYS_SCANNER.next();
				teacher.setProfession(profession);
				break;
			}

			TEACHERS_ARRAY_LIST.add(teacher);
			System.out.println("教师主要信息录入成功！！");

			if (PrimaryMenu.isExit()) {
				flga = false;
			}
		}

	}

	/**
	 * 判断教师信息集合是否为空
	 *
	 * @return :集合为空则返回true,反之false.
	 */
	private static boolean isArrayEmpty() {
		if (TEACHERS_ARRAY_LIST.size() == 0) {
			System.out.println("教师信息管理系统空空如也!!!");
			PrimaryMenu.buttonEnter();
			return true;
		}
		return false;
	}

	/**
	 * 打印信息字段
	 */
	private static void showInfoTitle() {
		System.out.println();
		System.out.println("****************************教师信息表***************************");
		System.out.println("身份证号\t\t\t工号\t  姓名\t性别\t年龄\t 教学领域");
		System.out.println("**************************************************************");
	}

}
