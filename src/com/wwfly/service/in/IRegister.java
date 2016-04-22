package com.wwfly.service.in;
import com.wwfly.service.param.LoginParam;
import com.wwfly.service.param.RegisterParam;
import com.wwfly.service.result.Log;

public interface IRegister {
	public Log register(RegisterParam regInfo);
	public Log addAccount(LoginParam login);
}
