/**
 * @author saurav roy
 * Date:19/11/22
 * Time:11:17 PM
 */
package com.acko.insuredassetcredibility.dao;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class UserDao {
    @Id
    private String mobile;
    private String email;
    private Double lastLat;
    private Double lastLong;
    private String name;
    private String pincode;
    private Integer age;
    private String pan;
}
