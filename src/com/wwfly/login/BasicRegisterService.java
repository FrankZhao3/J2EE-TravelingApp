package com.wwfly.login;

import com.wwfly.db.service.ClientDataImplement;
import com.wwfly.db.tables.School;
import com.wwfly.db.tables.User;
import com.wwfly.service.in.IRegister;
import com.wwfly.service.param.LoginParam;
import com.wwfly.service.param.RegisterParam;
import com.wwfly.service.result.Log;

public class BasicRegisterService implements IRegister{
	public Log register(RegisterParam regInfo) {
		User user = new User(regInfo.getName(), regInfo.getAge(), regInfo.getSex(), regInfo.getUserEmail(), regInfo.getUserPhone());
		ClientDataImplement clientDataFactory = new ClientDataImplement();
		if(regInfo.getSchoolName()!=null){
			School school = clientDataFactory.getSchoolBasedOnName(regInfo.getSchoolName());
			if(school != null) {
				user.setSchool(school);
			}
		}
		return clientDataFactory.UserRegister(user);
	}

	@Override
	public Log addAccount(LoginParam login) {
		String password = login.getPassword();
		String username = login.getUsername();
		ClientDataImplement clientDataFactory = new ClientDataImplement();
		return clientDataFactory.AcctRegister(username, password);
	}
}
