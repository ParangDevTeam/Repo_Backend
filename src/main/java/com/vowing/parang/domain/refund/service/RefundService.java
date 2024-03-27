package com.vowing.parang.domain.refund.service;

import com.vowing.parang.domain.member.entity.MemberEntity;
import com.vowing.parang.domain.member.error.UserNotFound;
import com.vowing.parang.domain.member.repository.MemberRepository;
import com.vowing.parang.domain.refund.dto.RefundDTO;
import com.vowing.parang.domain.refund.entity.RefundEntity;
import com.vowing.parang.domain.refund.repository.RefundRepository;
import com.vowing.parang.domain.slot.entity.AddSlotEntity;
import com.vowing.parang.domain.slot.repository.AddSlotRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RefundService {

    private final RefundRepository refundRepository;
    private final MemberRepository memberRepository;
    private final AddSlotRepository addSlotRepository;

    @Transactional
    public List<RefundDTO> refundList() {
        List<RefundEntity> refundEntityList = refundRepository.findAll();

        return refundEntityList.stream()
                .map(RefundEntity::toValueObject)
                .collect(Collectors.toList());
    }

    @Transactional
    public Page<RefundDTO> getFilterRefundPaging(String category, Pageable pageable) {
        Page<RefundEntity> refundEntityPage = refundRepository.findByCategory(
                category,
                PageRequest.of(
                        pageable.getPageNumber(),
                        // pageable.getPageSize(),// 기본값이 10개
                        3,
                        Sort.by(Sort.Direction.DESC, "id")
                )
        );

        return refundEntityPage.map(RefundEntity::toValueObject);
    }

    @Transactional
    public RefundDTO postRegisterRefund(RefundDTO dto) {

        AddSlotEntity addSlotEntity = addSlotRepository.getById(dto.getId());

         final var refundEntity = new RefundEntity();
         refundEntity.setCategory(addSlotEntity.getCategory());
         refundEntity.setUserId(addSlotEntity.getUserId());
         refundEntity.setProductName(addSlotEntity.getProductName());
         refundEntity.setProductMid(addSlotEntity.getProductMid());
         refundEntity.setStartDate(addSlotEntity.getStartDate());
         refundEntity.setRefundTime(Instant.now());

         refundRepository.save(refundEntity);

         return refundEntity.toValueObject();
    }
}
