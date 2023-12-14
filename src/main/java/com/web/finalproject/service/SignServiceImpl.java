package com.web.finalproject.service;

import java.io.PrintWriter;


import com.web.finalproject.mapper.SignMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.finalproject.vo.SignVO;

@Service
public class SignServiceImpl implements SignService{

	@Autowired
	SignMapper signMapper;

	@Override
	public int idCheck(String userid) {
		return signMapper.idCheck(userid);
	}

	@Override
	public int usersInsert(SignVO vo) {
		return signMapper.usersInsert(vo);
	}

	@Override
	public SignVO loginSelect(String userid, String userpwd) {
		return signMapper.loginSelect(userid, userpwd);
	}

	@Override
	public SignVO findId(SignVO vo) {
		return signMapper.findId(vo);
	}



}
