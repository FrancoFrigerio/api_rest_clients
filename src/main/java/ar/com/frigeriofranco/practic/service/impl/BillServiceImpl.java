package ar.com.frigeriofranco.practic.service.impl;

import ar.com.frigeriofranco.practic.dto.*;
import ar.com.frigeriofranco.practic.model.Bill;
import ar.com.frigeriofranco.practic.model.Client;
import ar.com.frigeriofranco.practic.model.Item;
import ar.com.frigeriofranco.practic.repository.BillRepository;
import ar.com.frigeriofranco.practic.repository.ClientRepository;
import ar.com.frigeriofranco.practic.repository.ProductRepository;
import ar.com.frigeriofranco.practic.service.BillService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.*;



@Service
@Slf4j
public class BillServiceImpl implements BillService {

    @Autowired
    BillRepository billRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ModelMapper mapper;

    @Override
    public List<BillListDto> findAllWithClient(String desde,String hasta) {
        List<BillListDto> billsDtos = new ArrayList<>();
            billRepository.fetchAllWithClient(desde,hasta).forEach(bill -> {
            ClientListRespDto clientDto = mapper.map(bill.getCliente(),ClientListRespDto.class);
            BillListDto billDto = mapper.map(bill,BillListDto.class);
            billDto.setClient(clientDto);
            billsDtos.add(billDto);
        });
        return billsDtos;
    }

    @Override
    public PageDto<BillListDto> findAllPage(PageRequestDto pageRequestDto,HttpServletRequest request) throws ParseException {
      PageDto<BillListDto> datos = new PageDto<>();
      List<BillListDto> listado = new ArrayList<>();

      Map<String, Object> links = new HashMap<>();
      Pageable page;
      if(pageRequestDto.getOrder().equalsIgnoreCase("desc")){
           page = PageRequest.of(pageRequestDto.getPage(),pageRequestDto.getSizePage(),Sort.by(pageRequestDto.getSort()).descending());
      }else{
           page = PageRequest.of(pageRequestDto.getPage(),pageRequestDto.getSizePage() ,Sort.by(pageRequestDto.getSort()).ascending());
      }
      Page<Bill> pageList = billRepository.findAll(page,pageRequestDto.getDesde(),pageRequestDto.getHasta());

      pageList.forEach(element->{
          ClientListRespDto clientDto = mapper.map(element.getCliente(),ClientListRespDto.class);
          BillListDto billDto = mapper.map(element,BillListDto.class);
          billDto.setClient(clientDto);
          listado.add(billDto);
      });
      links.put("next_page",pageList.hasNext()?(page.getPageNumber()+1):null);
      links.put("previous",pageList.hasPrevious()?(page.getPageNumber()-1):null);
      links.put("last_page",pageList.getTotalPages()-1);
      links.put("total_elements",pageList.getTotalElements());

      datos.setContent(listado);
      datos.setUtils(links);

      return datos;
    }


    @Override
    public BillResponseDto saveBill(BillRequestDto billRequestDto, Long id) {
        Client client = clientRepository.getById(id);
        Bill billToSave = mapper.map(billRequestDto,Bill.class);
        billToSave.setItemsProducts(new ArrayList<>());
        billRequestDto.getItemsProducts().forEach(product ->{
            Item i = new Item();
            i.setProduct(productRepository.getById(product.getProductID()));
            i.setCount(product.getProductCount());
            billToSave.getItemsProducts().add(i);
        });
        client.getBills().add(billToSave);
        billToSave.setCliente(client);
        return mapper.map(billRepository.save(billToSave),BillResponseDto.class);
    }

    @Override
    public BillUniqueDto getBillWithUserAndItems(Long id) {
        Bill bill = billRepository.getById(id);
        BillUniqueDto billDto = mapper.map(bill,BillUniqueDto.class);
        billDto.setClient(mapper.map(bill.getCliente(),ClientListRespDto.class));
        return billDto;
    }

}
