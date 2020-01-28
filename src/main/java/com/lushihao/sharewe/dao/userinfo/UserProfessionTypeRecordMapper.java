package com.lushihao.sharewe.dao.userinfo;

import com.lushihao.sharewe.entity.userinfo.UserProfessionTypeRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserProfessionTypeRecordMapper {

    /**
     * 批量创建记录
     *
     * @param list
     */
    void batchCreateRecord(List<UserProfessionTypeRecord> list);

    /**
     * 批量删除记录
     *
     * @param list
     */
    void batchDeleteRecord(@Param("openId") String openId, List<UserProfessionTypeRecord> list);

    /**
     * 获取所有的职业
     *
     * @param openId
     * @return
     */
    List<UserProfessionTypeRecord> getAllProfession(String openId);

    /**
     * 修改记录
     *
     * @param userProfessionTypeRecord
     * @return
     */
    int updateUserProfessionTypeRecord(UserProfessionTypeRecord userProfessionTypeRecord);

}
