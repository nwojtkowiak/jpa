package com.capgemini.mappers;

import com.capgemini.domain.LoanEntity;
import com.capgemini.types.LoanTO;
import com.capgemini.types.LoanTO.LoanToBuilder;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class LoanMapper {

    public static LoanTO toTO(LoanEntity loanEntity) {
        if (loanEntity == null) {
            return null;
        }

        return new LoanToBuilder()
                .withId(loanEntity.getId())
                .withCar(loanEntity.getCar().getId())
                .withCustomer(loanEntity.getCustomer().getId())
                .withAmount(loanEntity.getAmount())
                .withDateFrom(loanEntity.getDateFrom())
                .withDateTo(loanEntity.getDateTo())
                .withOfficeFrom(loanEntity.getOfficeFrom().getId())
                .withOfficeTo(loanEntity.getOfficeTo().getId())
                .build();

    }

    public static LoanEntity toEntity(LoanTO loanTO) {
        if (loanTO == null) {
            return null;
        }
        return new LoanEntity(loanTO.getId(), loanTO.getDateFrom().toString(), loanTO.getDateTo().toString(), loanTO.getAmount());
    }

    public static List<Long> map2TOs(List<LoanEntity> loanEntities) {
        if (loanEntities != null) {
            return loanEntities.stream().map(LoanEntity::getId).collect(Collectors.toList());
        }
        return new LinkedList<>();
    }

    public static List<LoanEntity> map2Entities(List<LoanTO> loanTOs) {
        if (loanTOs != null) {
            return loanTOs.stream().map(LoanMapper::toEntity).collect(Collectors.toList());
        }
        return new LinkedList<>();
    }
}
