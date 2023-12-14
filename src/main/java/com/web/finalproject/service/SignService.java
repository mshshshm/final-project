package com.web.finalproject.service;


import com.web.finalproject.vo.SignVO;

public interface SignService {

	public int idCheck(String userid);
	public int usersInsert(SignVO vo);
	public SignVO loginSelect(String userid, String userpwd);
	public SignVO findId(SignVO vo);
}
