package com.application.usedallea.zzim.dao;

import com.application.usedallea.zzim.dto.ZzimDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ZzimDAO {

    long insertZzim(ZzimDTO zzimDTO);

    void updateZzimCnt(long zzimId);

    int getZzimCount(long productId);

    int getZzimIdCnt(ZzimDTO zzimDTO);

    void deleteZzim(ZzimDTO zzimDTO);
}
