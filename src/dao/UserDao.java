package dao;

import java.util.ArrayList;
import java.util.HashMap;

import data.Database;
import vo.UserVO;

public class UserDao {

	private static UserDao instance;
	
	private UserDao() {}
	
	public static UserDao getInstance() {
		if(instance == null) {
			instance = new UserDao();
		}
		return instance;
	}

	
	Database database = Database.getInstance();
	
	
	public void insertUser(UserVO user) {
		database.tb_user.add(user);
	}
	
	
	
	public UserVO selectUser(HashMap<String, String> param) {
		UserVO rtnUser = null;  // 리턴값으로 쓰기위해 선언
		for(int i = 0; i < database.tb_user.size(); i++) {
			UserVO user = database.tb_user.get(i);
			boolean flag = true;
			
			for(String key : param.keySet()) {
				String value = param.get(key);
				if(key.equals("ID")) {					// 비교할 대상을 키 이름으로 구분
					if(!user.getId().equals(value)) flag = false;
				}else if(key.equals("PASSWORD")){
					if(!user.getPassword().equals(value)) flag = false;
				}else if(key.equals("NAME")){
					if(!user.getName().equals(value)) flag = false;
				}else {
					flag = false;
				}
			} // end for
			if(flag) rtnUser = user;  // 로그인
		}
		return rtnUser;
	}

	
	
	
	public ArrayList<UserVO> selectUserList() {
		
		return database.tb_user;
		
	}
	
}
