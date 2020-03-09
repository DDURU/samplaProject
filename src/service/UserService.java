package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import vo.UserVO;
import dao.UserDao;
import data.Session;

public class UserService {

	private static UserService instance;

	private UserService() {
	}

	public static UserService getInstance() {
		if (instance == null) {
			instance = new UserService();
		}
		return instance;
	}

	UserDao userDao = UserDao.getInstance();

	// 회원가입
	public void join() {
		Scanner s = new Scanner(System.in);

		String id = null;
		UserVO idCheck = null;
		do {
			System.out.println("아이디 :");
			id = s.nextLine();

			HashMap<String, String> param = new HashMap<>();
			param.put("ID", id);
			idCheck = userDao.selectUser(param);

			if (idCheck != null) { // 로그인 할 때 사용하는 메소드이므로 null이 아니면 아이디가 있다는 의미
				System.out.println("사용할 수 없는 아이디 입니다.");
			}
		} while (idCheck != null);

		System.out.println("비밀번호 : ");
		String passward = s.nextLine();
		System.out.println("이름 : ");
		String name = s.nextLine();

		UserVO user = new UserVO();

		user.setId(id);
		user.setPassword(passward);
		user.setName(name);

		userDao.insertUser(user);
		System.out.println("가입해주셔서 감사합니다.");

	}

	// 로그인
	public void login() {
		Scanner s = new Scanner(System.in);

		System.out.println("아이디 : ");
		String id = s.nextLine();
		System.out.println("비밀번호 : ");
		String passward = s.nextLine();

		HashMap<String, String> param = new HashMap<>();
		param.put("ID", id);
		param.put("PASSWARD", passward);

		UserVO user = userDao.selectUser(param);

		if (user == null) {
			System.out.println("아이디 혹은 비밀번호를 잘못 입력하셨습니다.");
		} else {
			System.out.println("로그인 성공!");
			System.out.println(user.getName() + "님 환영합니다.");
			Session.LoginUser = user;
		}
	}

	// 회원목록
	public void userList() {
		ArrayList<UserVO> userList = userDao.selectUserList();

		System.out.println("--------------------------------------");
		System.out.println("번호\t아이디\t이름");
		System.out.println("--------------------------------------");
		for (int i = userList.size() - 1; 0 <= i; i--) {
			UserVO user = userList.get(i);
			System.out.println(i + 1 + "\t" + user.getId() + "\t"
					+ user.getName());
		}
		System.out.println("--------------------------------------");
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
