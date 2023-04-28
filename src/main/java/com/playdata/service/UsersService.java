package com.playdata.service;

import com.playdata.dao.UsersDao;
import com.playdata.dto.UsersDto;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class UsersService {
    public final static int LOGIN_SUCCESS = 0;
    public final static int LOGIN_FAIL_USER_NOT_FOUND = 1;
    public final static int LOGIN_FAIL_PASSWORD = 2;
    public final static int REGISTER_SUCCESS = 0;
    public final static int REGISTER_ID_DUPLICATE = 1;
    public final static int REGISTER_USER_EXIST = 2;
    /**
     * 로그인 form에서 받은 데이터로 로그인 수행
     * @param username
     * @param password
     * @param req
     * @return LOGIN_SUCCESS = 0, LOGIN_FAIL_USER_NOT_FOUND = 1, LOGIN_FAIL_PASSWORD = 2
     */
    public int login(String username, String password, HttpServletRequest req){
        UsersDao usersDao = new UsersDao();
        UsersDto usersDto = usersDao.selectByUsername(username, req);
        if (usersDto == null){
            // 해당하는 사용자가 없다.
            return LOGIN_FAIL_USER_NOT_FOUND;
        } else if(!usersDto.getPassword().equals(hashPassword(password))){
            // 로그인 실패
            // 로그인 실패 횟수 1만큼 증가
            usersDao.updateLoginFailedCount(username, usersDto.getLogin_fail_count()+1, req);
            return LOGIN_FAIL_PASSWORD;
        } else{
            //로그인 성공
            req.getSession().setAttribute("username", username);
            // 로그인 실패 횟수 0으로 초기화
            usersDao.updateLoginFailedCount(username, 0, req);
            return LOGIN_SUCCESS;
        }
    }

    /**
     * 패스워드 암호화 처리
     * @param password
     * @return hashedPassword
     */
    private String hashPassword(String password) {
        String hashedPassword = "";
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            hashedPassword = Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hashedPassword;
    }


    public void logout(HttpServletRequest req) {
//        req.getSession().setAttribute("username", null);
        req.getSession().invalidate();
    }


    public int register(String username, String password, HttpServletRequest req) {
        UsersDao usersDao = new UsersDao();
        UsersDto usersDto = usersDao.selectByUsername(username, req);
        if (usersDto == null){
            // 해당 아이디를 가진 사용자가 없다 = 회원가입 가능
            usersDao.registerUser(username, hashPassword(password), req);
            return REGISTER_SUCCESS;
        } else if(!usersDto.getPassword().equals(hashPassword(password))){
            // 해당 아이디를 가진 사람이 있으나 비밀번호까지 같지는 않다
            return REGISTER_ID_DUPLICATE;
        } else{
            // 해당 아이디와 비밀번호를 가진 사람이 있다
            return REGISTER_USER_EXIST;
        }
    }
}
