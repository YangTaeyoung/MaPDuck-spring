package com.mapduck.serivce;

import com.mapduck.domain.Company;
import com.mapduck.dto.CompanyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    @Override
    public CompanyDto entityToDto(Company company) {
        CompanyDto companyDto = new CompanyDto();
        companyDto.setCoId(company.getCoId());
        companyDto.setCoName(company.getCoName());
        return companyDto;
    }
}
