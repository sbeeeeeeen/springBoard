package kr.or.ddit.login.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.or.ddit.encrypt.kisa.seed.KISA_SEED_CBC;
import kr.or.ddit.login.dao.LoginDaoInf;
import kr.or.ddit.model.BoardVo;

import org.springframework.stereotype.Service;

@Service("loginService")
public class LoginService implements LoginServiceInf{
	
	@Resource(name="loginDao")
	private LoginDaoInf lDao;

	@Override
	public boolean getStudent(Map<String, String> map) {
		map.put("pass", KISA_SEED_CBC.Encrypt(map.get("pass")));
		return lDao.getStudent(map);
	}

	@Override
	public List<BoardVo> getBoardList() {
		return lDao.getBoardList();
	}

}
