package ar.com.frigeriofranco.practic.service;


import ar.com.frigeriofranco.practic.dto.*;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public interface BillService {

    public List<BillListDto> findAllWithClient(String desde,String hasta);

    PageDto<BillListDto> findAllPage(PageRequestDto pageRequestDto,HttpServletRequest request) throws ParseException;

    public BillResponseDto saveBill(BillRequestDto billRequestDto, Long id);

    BillUniqueDto getBillWithUserAndItems(Long id);

}
