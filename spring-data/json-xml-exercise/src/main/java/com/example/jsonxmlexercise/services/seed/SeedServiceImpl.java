package com.example.jsonxmlexercise.services.seed;

import com.example.jsonxmlexercise.domain.entities.Category;
import com.example.jsonxmlexercise.domain.entities.Product;
import com.example.jsonxmlexercise.domain.entities.User;
import com.example.jsonxmlexercise.domain.models.category.CategoryImportModel;
import com.example.jsonxmlexercise.domain.models.category.wrappers.CategoryWrapperImportModel;
import com.example.jsonxmlexercise.domain.models.product.ProductImportModel;
import com.example.jsonxmlexercise.domain.models.product.wrappers.ProductWrapperImportModel;
import com.example.jsonxmlexercise.domain.models.user.UserImportModel;
import com.example.jsonxmlexercise.domain.models.user.wrappers.UserWrapperImportModel;
import com.example.jsonxmlexercise.repositories.CategoryRepository;
import com.example.jsonxmlexercise.repositories.ProductRepository;
import com.example.jsonxmlexercise.repositories.UserRepository;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

import static com.example.jsonxmlexercise.constants.Paths.*;
import static com.example.jsonxmlexercise.constants.Utils.GSON;
import static com.example.jsonxmlexercise.constants.Utils.MODEL_MAPPER;

@Service
public class SeedServiceImpl implements SeedService {

    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    @Autowired
    public SeedServiceImpl(UserRepository userRepository,
                           CategoryRepository categoryRepository,
                           ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void seedUsers(String type) throws IOException, JAXBException {
        if (this.userRepository.count() > 0) return;

        final List<User> usersToSave = type.equals("JSON") ?
                getAllUsersFromJson() :
                getAllUsersFromXml();

        this.userRepository.saveAll(usersToSave);
    }

    @Override
    public void seedCategories(String type) throws IOException, JAXBException {
        if (this.categoryRepository.count() > 0) return;

        final List<Category> categoriesToSave = type.equals("JSON") ?
                getAllCategoriesFromJson() :
                getAllCategoriesFromXml();

        this.categoryRepository.saveAll(categoriesToSave);
    }

    @Override
    public void seedProducts(String type) throws IOException, JAXBException {
        if (this.productRepository.count() > 0) return;


        final List<Product> productsBeforeMap = type.equals("JSON") ?
                getAllProductsFromJson() :
                getAllProductFromXml();

        final List<Product> productsToSave = productsBeforeMap.stream()
                .map(this::setRandomCategories)
                .map(this::setRandomBuyer)
                .map(this::setRandomSeller)
                .toList();

        this.productRepository.saveAll(productsToSave);
    }

    private Product setRandomSeller(Product product) {
        User seller = this.userRepository
                .getRandomEntity()
                .orElseThrow(NoSuchElementException::new);

        while (product.getBuyer() != null && product.getBuyer().getId().equals(seller.getId())) {
            seller = this.userRepository
                    .getRandomEntity()
                    .orElseThrow(NoSuchElementException::new);
        }

        product.setSeller(seller);

        return product;
    }

    private Product setRandomBuyer(Product product) {
        if (product.getPrice().compareTo(BigDecimal.valueOf(750)) > 0) {
            final User buyer = this.userRepository
                    .getRandomEntity()
                    .orElseThrow(NoSuchElementException::new);

            product.setBuyer(buyer);
        }

        return product;
    }

    private Product setRandomCategories(Product product) {
        final Random random = new Random();

        long countOfCategories = random.nextLong(this.categoryRepository.count() - 2);

        Set<Category> categories = new HashSet<>();

//        IntStream.range(0, countOfCategories)
//                .forEach(value -> {
//                    categories.add(this.categoryRepository
//                            .getRandomEntity()
//                            .orElseThrow(NoSuchElementException::new));
//                });

        for (int i = 0; i < countOfCategories; i++) {
            categories.add(this.categoryRepository
                    .getRandomEntity()
                    .orElseThrow(NoSuchElementException::new));
        }

        product.setCategories(categories);

        return product;
    }

    private List<Category> getAllCategoriesFromJson() throws IOException {
        final FileReader fileReader = new FileReader(CATEGORIES_JSON_PATH.toFile());

        final List<Category> categories = Arrays.stream(GSON.fromJson(fileReader, CategoryImportModel[].class))
                .map(categoryImportModel -> MODEL_MAPPER.map(categoryImportModel, Category.class))
                .toList();

        fileReader.close();

        return categories;
    }

    private List<Category> getAllCategoriesFromXml() throws IOException, JAXBException {
        final FileReader fileReader = new FileReader(CATEGORIES_XML_PATH.toFile());

        final JAXBContext context = JAXBContext.newInstance(CategoryWrapperImportModel.class);
        final Unmarshaller unmarshaller = context.createUnmarshaller();

        final CategoryWrapperImportModel wrapperImportModel = (CategoryWrapperImportModel) unmarshaller.unmarshal(fileReader);

        fileReader.close();

        return wrapperImportModel.getCategories()
                .stream()
                .map(categoryImportModel -> MODEL_MAPPER.map(categoryImportModel, Category.class))
                .toList();

    }

    private List<User> getAllUsersFromJson() throws IOException {
        final FileReader fileReader = new FileReader(USERS_JSON_PATH.toFile());

        final List<User> users = Arrays.stream(GSON.fromJson(fileReader, UserImportModel[].class))
                .map(userImportModel -> MODEL_MAPPER.map(userImportModel, User.class))
                .toList();

        fileReader.close();

        return users;
    }

    private List<User> getAllUsersFromXml() throws IOException, JAXBException {
        final FileReader fileReader = new FileReader(USERS_XML_PATH.toFile());

        final JAXBContext context = JAXBContext.newInstance(UserWrapperImportModel.class);

        final Unmarshaller unmarshaller = context.createUnmarshaller();

        final UserWrapperImportModel wrapperImportModel = (UserWrapperImportModel) unmarshaller.unmarshal(fileReader);

        fileReader.close();

        return wrapperImportModel.getUsers()
                .stream()
                .map(model -> MODEL_MAPPER.map(model, User.class))
                .toList();

    }

    private List<Product> getAllProductsFromJson() throws IOException {
        final FileReader fileReader = new FileReader(PRODUCTS_JSON_PATH.toFile());

        final List<Product> products = Arrays.stream(GSON.fromJson(fileReader, ProductImportModel[].class))
                .map(productImportModel -> MODEL_MAPPER.map(productImportModel, Product.class))
                .toList();

        fileReader.close();

        return products;
    }

    private List<Product> getAllProductFromXml() throws IOException, JAXBException {
        final FileReader fileReader = new FileReader(PRODUCTS_XML_PATH.toFile());

        final JAXBContext context = JAXBContext.newInstance(ProductWrapperImportModel.class);
        final Unmarshaller unmarshaller = context.createUnmarshaller();

        final ProductWrapperImportModel wrapperImportModel = (ProductWrapperImportModel) unmarshaller.unmarshal(fileReader);

        return wrapperImportModel.getProducts()
                .stream()
                .map(model -> MODEL_MAPPER.map(model, Product.class))
                .toList();

    }
}
