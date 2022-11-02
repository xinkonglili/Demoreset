package com.nk.mapper;

import com.nk.entity.Company;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyMapper {

    List<Company> getAllCompany();

    Boolean deleteCompanyById(@Param("idStr") String idStr);

    Boolean addCompany(Company company);

    List<Company> getCompanyById(@Param("companyId") Integer companyId);

    Boolean modifyCompany(Company company);

    List<Company> getCompanyByName(@Param("companyName") String companyName);

    List<Company> getCompanyByIdStr(@Param("idStr") String idStr);

    List<Company> getAllCompanyAdd();
}