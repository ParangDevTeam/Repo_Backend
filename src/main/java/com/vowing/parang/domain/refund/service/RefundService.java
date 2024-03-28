package com.vowing.parang.domain.refund.service;

import com.vowing.parang.domain.refund.dto.RefundDTO;
import com.vowing.parang.domain.refund.entity.RefundEntity;
import com.vowing.parang.domain.refund.repository.RefundRepository;
import com.vowing.parang.domain.slot.entity.SlotEntity;
import com.vowing.parang.domain.slot.repository.SlotRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefundService {

    private final RefundRepository refundRepository;
    private final SlotRepository slotRepository;


    /**
     * 환불 등록
     */
    @Transactional
    public RefundDTO postRegisterRefund(RefundDTO dto) {
        SlotEntity addSlotEntity = slotRepository.getById(dto.getId());
        RefundEntity refundEntity = RefundEntity.fromSlotEntity(addSlotEntity);
        refundRepository.save(refundEntity);
        return refundEntity.toValueObject();
    }

    /**
     * 카테고리별 페이징 환불 목록
     */
    @Transactional
    public Page<RefundDTO> getFilterRefundPaging(String category, Pageable pageable) {
        Page<RefundEntity> refundEntityPage = refundRepository.findByCategory(
                category,
                PageRequest.of(
                        pageable.getPageNumber(),
                        pageable.getPageSize(),// 기본값이 10개
                        // 3,
                        Sort.by(Sort.Direction.DESC, "id")
                )
        );

        return refundEntityPage.map(RefundEntity::toValueObject);
    }

}
