package com.mapduck.serivce;

import com.mapduck.domain.Company;
import com.mapduck.dto.CompanyDto;

public interface CompanyService {
    CompanyDto entityToDto(Company company);
}
