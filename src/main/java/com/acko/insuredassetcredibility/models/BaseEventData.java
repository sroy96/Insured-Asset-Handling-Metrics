/**
 * @author saurav roy
 * Date:22/11/22
 * Time:4:53 PM
 */
package com.acko.insuredassetcredibility.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BaseEventData {
    String name;
    Date date;
}
