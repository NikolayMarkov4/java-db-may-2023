package com.example.jsonxmlexercise.services.category;

import com.example.jsonxmlexercise.domain.models.category.CategorySummaryModel;
import jakarta.xml.bind.JAXBException;

import java.io.IOException;
import java.util.List;

public interface CategoryService {

    List<CategorySummaryModel> getCategorySummary() throws IOException, JAXBException;

}
