package com.wwfly.service.in;

import com.wwfly.service.param.LoginParam;
import com.wwfly.service.result.Log;
import com.wwfly.service.result.Pair;

public interface ILogin {
	public Pair<Log, Boolean> login(LoginParam logInfo);
}
