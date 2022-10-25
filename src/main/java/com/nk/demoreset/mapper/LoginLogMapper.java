package com.nk.demoreset.mapper;

import com.nk.demoreset.pojo.LoginLogCount;
import com.nk.demoreset.pojo.LoginLogPOVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface LoginLogMapper {
    List<LoginLogPOVO> getLoginLog(@Param("companyId") Integer companyId, @Param("departmentId") Integer departmentId, @Param("name") String name, @Param("username") String username, @Param("start") String start, @Param("end") String end);
    Boolean deleteLoginLog(@Param("idStr") String idStr);
    List<LoginLogCount> getLoginLogCount(@Param("companyId") Integer companyId, @Param("departmentId") Integer departmentId, @Param("name") String name, @Param("username") String username, @Param("start") String start, @Param("end") String end);
    Boolean insertLoginLog(@Param("sessionId") String sessionId, @Param("time")String time, @Param("userId")Integer userId);
    Boolean updateLoginLog(@Param("sessionId") String sessionId,@Param("time")String time);
    @Select("select login_time from loginlog t where user_id=#{userId} and not exists(select 1 from loginlog where id > t.id and user_id=#{userId})")
    String getPreLoginTimeByUserId(@Param("userId") Integer userId);
}