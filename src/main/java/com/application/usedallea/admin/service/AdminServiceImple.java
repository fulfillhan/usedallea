package com.application.usedallea.admin.service;

import com.application.usedallea.admin.dao.AdminDAO;
import com.application.usedallea.admin.dto.AdminDTO;
import com.application.usedallea.member.dto.MemberDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

@Service
public class AdminServiceImple implements AdminService{

    @Autowired
    private AdminDAO adminDAO;

    private static Logger logger =  LoggerFactory.getLogger(AdminServiceImple.class);
    @Override
    public boolean login(AdminDTO adminDTO) {
        AdminDTO loginData = adminDAO.getLoginData(adminDTO);
        if(loginData != null) {
            return true;   //로그인 되어 있는 상태
            }
        return false;
    }
}
