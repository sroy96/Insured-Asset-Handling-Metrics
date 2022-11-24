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

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KeyFactorDataScore {
    KeyFactorsData keyFactorsData;
    Double score;

}
