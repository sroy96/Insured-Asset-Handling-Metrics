/**
 * @author saurav roy
 * Date:23/11/22
 * Time:8:02 PM
 */
package com.acko.insuredassetcredibility.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Component
public class KeyFactorDataScore {
    KeyFactorsData keyFactorsData;
    Integer score;

}
