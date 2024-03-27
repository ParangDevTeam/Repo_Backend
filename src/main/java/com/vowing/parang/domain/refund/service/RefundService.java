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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class RefundService {

    private final RefundRepository refundRepository;
    private final SlotRepository slotRepository;

    /**
     * 환불 등록
     * @param dto dto
     * @return 환불 등록
     */
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

    /**
     * 카테고리별 페이징 환불 목록
     * @param category category
     * @param pageable pageable
     * @return 카테고리별 페이징 환불 목록
     */
    @Transactional
    public RefundDTO postRegisterRefund(RefundDTO dto) {

        SlotEntity addSlotEntity = slotRepository.getById(dto.getId());

        final var refundEntity = new RefundEntity();
        refundEntity.setCategory(addSlotEntity.getCategory().getCategory());
        refundEntity.setUserId(addSlotEntity.getUserId());
        refundEntity.setProductName(addSlotEntity.getProductName());
        refundEntity.setProductMid(addSlotEntity.getProductMid());
        refundEntity.setStartDate(addSlotEntity.getStartDate());
        refundEntity.setRefundTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        refundRepository.save(refundEntity);

        return refundEntity.toValueObject();
    }
}
