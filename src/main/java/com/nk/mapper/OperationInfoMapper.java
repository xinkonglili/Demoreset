package com.nk.mapper;

import com.nk.entity.OperationInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperationInfoMapper {

    List<OperationInfo> getOperationInfo(@Param("tableName") String tableName, @Param("type") String type, @Param("operator") String operator, @Param("start") String start, @Param("end") String end);

    Boolean addOperationInfo(OperationInfo operationInfo);
}