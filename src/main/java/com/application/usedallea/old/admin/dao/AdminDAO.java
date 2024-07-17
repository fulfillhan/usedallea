package com.application.usedallea.old.admin.dao;

import com.application.usedallea.old.admin.dto.AdminDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminDAO {
    AdminDTO getLoginData(AdminDTO adminId);
}
