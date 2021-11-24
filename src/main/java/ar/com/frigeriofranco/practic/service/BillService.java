package ar.com.frigeriofranco.practic.service;


import ar.com.frigeriofranco.practic.dto.*;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface BillService {

    public List<BillListDto> findAllWithClient();

     PageDto<BillListDto> findAllPage(PageRequestDto pageRequestDto,HttpServletRequest request);

    public BillResponseDto saveBill(BillRequestDto billRequestDto, Long id);
}
