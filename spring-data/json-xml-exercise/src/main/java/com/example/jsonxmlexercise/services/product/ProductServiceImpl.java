package com.example.jsonxmlexercise.services.product;

import com.example.jsonxmlexercise.domain.models.product.ProductBasicInfoWithSellerFullName;
import com.example.jsonxmlexercise.domain.models.product.wrappers.ProductBasicInfoWrapper;
import com.example.jsonxmlexercise.repositories.ProductRepository;
import jakarta.xml.bind.JAXBException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import static com.example.jsonxmlexercise.constants.Paths.FIRST_JSON_PATH;
import static com.example.jsonxmlexercise.constants.Paths.FIRST_XML_PATH;
import static com.example.jsonxmlexercise.constants.Utils.writeIntoJsonFile;
import static com.example.jsonxmlexercise.constants.Utils.writeIntoXmlFile;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public List<ProductBasicInfoWithSellerFullName> getProductsInRangeWithNoBuyer(BigDecimal lowBoundary, BigDecimal highBoundary) throws IOException, JAXBException {
        final List<ProductBasicInfoWithSellerFullName> products =
                this.productRepository
                        .findAllByPriceBetweenAndBuyerIsNullOrderByPrice(lowBoundary, highBoundary)
                        .stream()
                        .map(ProductBasicInfoWithSellerFullName::getFromProduct)
                        .toList();

        final ProductBasicInfoWrapper wrapper = new ProductBasicInfoWrapper(products);

        writeIntoJsonFile(products, FIRST_JSON_PATH);
        writeIntoXmlFile(wrapper, FIRST_XML_PATH);

        return products;
    }

}
