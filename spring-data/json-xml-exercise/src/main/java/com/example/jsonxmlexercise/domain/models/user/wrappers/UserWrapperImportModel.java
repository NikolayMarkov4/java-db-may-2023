package com.example.jsonxmlexercise.domain.models.user.wrappers;

import com.example.jsonxmlexercise.domain.models.user.UserImportModel;
import jakarta.xml.bind.annotation.XmlAccessType;
import lombok.Getter;
import lombok.Setter;

import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Getter
@Setter
@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserWrapperImportModel {

    @XmlElement(name = "user")
    private List<UserImportModel> users;

}
