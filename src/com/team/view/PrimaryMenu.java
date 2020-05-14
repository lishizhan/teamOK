package com.team.view;

import com.team.bean.GenderEnum;
import com.team.bean.Person;
import com.team.bean.Students;
import com.team.bean.Teachers;
import com.team.service.StudentsInforMange;
import com.team.service.TeaInforManage;

import java.io.*;
import java.util.List;
import java.util.Scanner;

/**
 * @Description: 主菜单：显示教师信息管理系统和学生信息管理系统共用户选择； 数据校验，用于校验用户输入数据的合法性，判断用户选择；
 *               公共方法：避免代码重复，提高代码的利用率；
 * @author: 黎仕展
 * @version:1.0
 * @date: 2020年5月1日
 *
 */
public class PrimaryMenu {
	// 输入常量
	private static final Scanner SYS_SCANNER = new Scanner(System.in);

	public static void functionMenu() {
		while (true) {
			System.out.println("\t\t====================系统功能====================");
			System.out.println("\t\t                1、教师信息管理                 ");
			System.out.println("\t\t                2、学生信息管理                 ");
			System.out.println("\t\t                0、退出管理系统                 ");
			System.out.println("\t\t===============================================");

			System.out.println("请输入您的选择(0~2)：");
			String select = SYS_SCANNER.next();

			switch (select) {
			case "1":
				TeaInforManage.teachMenu();
				break;
			case "2":
				StudentsInforMange.stuMenu();
				break;
			case "0":
				// 退出系统
				System.out.println("退出成功，期待您下次光临！");
				System.exit(0);

			default:
				System.err.println("您的命令输入有误，请重新确认！\n");
				await();
			}
		}

	}

	/**
	 * 判断是否继续
	 * 
	 * @return : false:继续；true:退出
	 */
	public static boolean isExit() {
		System.out.println("是否退出？（y/n）");
		String str = SYS_SCANNER.next();
		if ("y".equals(str))
			return true;
		return false;
	}

	/**
	 * 按回车键继续
	 * 
	 */
	public static void buttonEnter() {

		System.out.println("按回车继续");
		try {
			new BufferedReader(new InputStreamReader(System.in)).readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 使用正则表达式验证身份证号是否合法
	 *
	 * @return :false 合法;true 不合法
	 */
	public static boolean cheackID(String strID) {
		String regex = "\\d{15}(\\d{2}[0-9xX])?";
		return strID.matches(regex) ? false : true;
	}

	/**
	 * 判断用户输入是否为空
	 */
	public static boolean putIsEmpty(String string) {
		if (string.isEmpty())
			return true;
		return false;
	}

	/**
	 * 使用正则表达式判断是否为百分制
	 */
	public static boolean isNumberDouble(String strNum) {
		String regex = "^(\\d{1}|[1-9]\\d{1}|100)$";
		return strNum.matches(regex) ? false : true;
	}

	/**
	 * 使用正则表达式判断是否为数字
	 */
	public static boolean isNumber(String strNum) {
		String regex = "\\d+";
		return strNum.matches(regex) ? false : true;
	}

	/**
	 * 判断身份证号是否重复
	 * 
	 * @param ARRAY_LIST:存储信息的集合
	 * @param strID:用户输入的身份证号
	 * @return :重复返回true;反之为false
	 */
	public static boolean isIDRepetition(List ARRAY_LIST, String strID) {

		if (ARRAY_LIST.size() == 0) {
			return false;
		}
		for (int i = 0; i < ARRAY_LIST.size(); i++) {
			Object o = ARRAY_LIST.get(i);
			if (((Person) o).getId().equals(strID)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 判断工号与学号在各自的集合中是否重复
	 * 
	 * @param ARRAY_LIST：教师与学生的集合
	 * @param strNum：学号或工号
	 * @return true为重复，反之亦然
	 */
	public static boolean isNumRepetition(List ARRAY_LIST, String strNum) {

		if (ARRAY_LIST.size() == 0) {
			return false;
		}
		for (int i = 0; i < ARRAY_LIST.size(); i++) {
			Object o = ARRAY_LIST.get(i);
			if (o instanceof Students && ((Students) o).getStuNum().equals(strNum)) {
				return true;
			}
			if (o instanceof Teachers && ((Teachers) o).getTeachId().equals(strNum)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 判断年龄是否合法
	 * 
	 * @param age；获取用户年龄
	 * @return false 不合法
	 */
	public static boolean isAge(String age) {

		if (age.isEmpty() || PrimaryMenu.isNumber(age))
			return false;
		int strage = Integer.parseInt(age);
		if (strage < 0 || strage > 120)
			return false;
		return true;
	}

	/**
	 * 修改姓名
	 */
	public static void updataName(Person person) {
		while (true) {
			System.out.println("请修改姓名为:");
			String name = SYS_SCANNER.next();
			if (PrimaryMenu.putIsEmpty(name)) {
				System.err.println("姓名不能为空,请重新输入");
			} else {
				person.setName(name);
				System.out.println("修改成功!!");
				break;
			}
		}

	}

	/**
	 * 输入年年龄
	 */
	public static void inputAge(Person person) {
		while (true) {
			System.out.println("请输入年龄:");
			String age = SYS_SCANNER.next();
			if (PrimaryMenu.isAge(age)) {
				person.setAge(Integer.parseInt(age));
				break;
			} else {
				System.err.println("您输入的年龄不合法!请重新输入");
			}
		}
	}

	/**
	 * 输入性别
	 */
	public static void inputSex(Person person) {
		while (true) {
			boolean flag = true;
			System.out.println("请输入性别：");
			String teachSex = SYS_SCANNER.next();
			if (PrimaryMenu.putIsEmpty(teachSex)) {
				System.err.println("性别不能为空！请重新输入\n");
				flag = false;
			} else {
				switch (teachSex) {
				case "男":
					person.setSex(GenderEnum.男);
					break;
				case "女":
					person.setSex(GenderEnum.女);
					break;
				default:
					System.err.println("性别只能是男或者女!请重新输入");
					flag = false;
				}
			}
			if (flag) {
				break;
			}
		}

	}

	/**
	 * 打印成绩标题
	 */
	public static void showTitle() {
		System.out.println();
		System.out.println("*******************************学生成绩表*******************************");
		System.out.println("学号\t\t姓名\t\t数学\t\t英语\t\tJava");
		System.out.println("*********************************************************************");
	}

	/**
	 * 利用线程等待
	 */
	public static void await() {
		try {
			Thread.sleep(100);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
