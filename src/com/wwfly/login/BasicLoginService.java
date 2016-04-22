package com.wwfly.login;

import com.wwfly.db.service.ClientDataImplement;
import com.wwfly.service.in.ILogin;
import com.wwfly.service.param.LoginParam;
import com.wwfly.service.result.Log;
import com.wwfly.service.result.Pair;

public class BasicLoginService implements ILogin {
	public Pair<Log, Boolean> login(LoginParam logInfo) {
		ClientDataImplement clientDataService = new ClientDataImplement();
		return clientDataService.Userlogin(logInfo.getUsername(), logInfo.getPassword());
	}
}
