package com.mapduck.serivce;

import com.mapduck.domain.Company;
import com.mapduck.dto.CompanyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    /**
     * 작성자: 양태영
     * 작성일: 21.11.13
     * 설명: company 엔티티에서 DTO로 바꾸는 매핑함수.
     * @param company
     * @return companyDto
     */
    @Override
    public CompanyDto entityToDto(Company company) {
        CompanyDto companyDto = new CompanyDto();
        companyDto.setCoId(company.getCoId());
        companyDto.setCoName(company.getCoName());
        return companyDto;
    }

    /**
     * 작성자: 양태영
     * 작성일: 21.11.15
     * 설명: DTO에서 Entity로 변환하는 매핑함수
     * @param companyDto: 변환할 DTO 객체
     * @return company: 변환될 Entity 객체
     */
    @Override
    public Company dtoToEntity(CompanyDto companyDto){
        var company = new Company();
        company.setCoId(companyDto.getCoId());
        company.setCoName(companyDto.getCoName());
        return company;
    }
}
