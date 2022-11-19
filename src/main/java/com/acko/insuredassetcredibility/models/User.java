/**
 * @author saurav roy
 * Date:19/11/22
 * Time:11:17 PM
 */
package com.acko.insuredassetcredibility.models;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class User {
    private String name;
    private String pincode;
    private Integer age;
    private String pan;
    private String mobile;
}
