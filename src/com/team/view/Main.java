package com.team.view;

/**
 * 
 * @Description: 主函数，利用静态代码块，加载计算机系信息管理系统的开发者，版本，时间。
 * @author: 黎仕展
 * @version:1.0
 * @date: 2020年5月1日
 *
 */
public class Main {
	static {
		System.out.println("******************************计算机系信息管理系统 *************************");
		System.out.println("\t\t\t  系统开发人:2018软件工程B1班 黎仕展");
		System.out.println("\t\t\t	  开发时间: 2020年5月1日");
		System.out.println("\t\t\t版本1.0 JDK1.8 版权所有: 18软件工程B1班");
		System.out.println("****************************************************************************");
	}

	public static void main(String[] args) {
		PrimaryMenu.functionMenu();
	}
}
