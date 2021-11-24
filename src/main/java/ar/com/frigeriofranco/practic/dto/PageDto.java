package ar.com.frigeriofranco.practic.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageDto <T>{
    private List<T> content;
    private Map<String,Object> utils = new HashMap<>();

}
