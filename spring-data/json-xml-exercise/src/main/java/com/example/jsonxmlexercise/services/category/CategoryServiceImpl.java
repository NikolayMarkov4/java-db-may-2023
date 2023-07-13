package com.example.jsonxmlexercise.services.category;

import com.example.jsonxmlexercise.domain.models.category.CategorySummaryModel;
import com.example.jsonxmlexercise.domain.models.category.wrappers.CategorySummaryWrapper;
import com.example.jsonxmlexercise.repositories.CategoryRepository;
import jakarta.xml.bind.JAXBException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

import static com.example.jsonxmlexercise.constants.Paths.THIRD_JSON_PATH;
import static com.example.jsonxmlexercise.constants.Paths.THIRD_XML_PATH;
import static com.example.jsonxmlexercise.constants.Utils.writeIntoJsonFile;
import static com.example.jsonxmlexercise.constants.Utils.writeIntoXmlFile;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategorySummaryModel> getCategorySummary() throws IOException, JAXBException {
        final List<CategorySummaryModel> categorySummaries = this.categoryRepository.getCategorySummary();

        final CategorySummaryWrapper wrapper = new CategorySummaryWrapper(categorySummaries);

        writeIntoJsonFile(categorySummaries, THIRD_JSON_PATH);
        writeIntoXmlFile(wrapper, THIRD_XML_PATH);

        return categorySummaries;
    }
}
