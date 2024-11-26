package org.example.shop43.product.service;


import org.example.shop43.product.dto.RequestProductDTO;
import org.example.shop43.product.dto.ResponseProductDTO;
import org.example.shop43.product.entity.Product;

import java.util.List;

public interface ProductService {
    List<RequestProductDTO> findAll();
    ResponseProductDTO save(RequestProductDTO dto);

    ResponseProductDTO update(Long id, RequestProductDTO dto);

    ResponseProductDTO updateActiveStatus(Long productId, boolean active);

    List<ResponseProductDTO> findByTitle(String title);

    Product findProductById(Long id);
}

