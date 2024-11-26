package org.example.shop43.product.service;

import lombok.AllArgsConstructor;
import org.example.shop43.exception.ProductNotFoundException;
import org.example.shop43.product.dto.RequestProductDTO;
import org.example.shop43.product.dto.ResponseProductDTO;
import org.example.shop43.product.entity.Product;
import org.example.shop43.product.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;
    private final ModelMapper mapper;
    private final RequestProductDTO requestProductDTO;


    @Override
    public List<RequestProductDTO> findAll() {

        return repository.findAll().stream()
                .map(e-> mapper.map(e,RequestProductDTO.class))
                .toList();
    }

    @Override
    public ResponseProductDTO save(RequestProductDTO dto) {
        Product entity = mapper.map(dto, Product.class);  // RequestProductDTO -> Product
        Product newProduct = repository.save(entity);
        return mapper.map(newProduct, ResponseProductDTO.class); //Product -> ResponseProductDTO
    }

    @Override
    @Transactional
    public ResponseProductDTO update(Long id, RequestProductDTO dto) {
        Product entity = mapper.map(dto, Product.class);
        entity.setId(id);
        entity = repository.save(entity);
        return mapper.map(entity, ResponseProductDTO.class);

    }


    @Override
    @Transactional
    public ResponseProductDTO updateActiveStatus(Long productId, boolean active) {
        Product entity = findProductById(productId);

        entity.setActive(active);
        return mapper.map(entity, ResponseProductDTO.class);
    }

    @Override
    public Product findProductById(Long productId) {
        Product entity = repository
                .findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product " + productId + " not found"));
        return entity;
    }

    public ResponseProductDTO findById(Long id){
        Product product = findProductById(id);
        return mapper.map(product, ResponseProductDTO.class);
    }

    @Override
    public List<ResponseProductDTO> findByTitle(String title) {
        List<Product> productByTitle = repository.findByTitle(title);
        return productByTitle.stream()
                .map(p->mapper.map(p,ResponseProductDTO.class))
                .toList();
    }


}
