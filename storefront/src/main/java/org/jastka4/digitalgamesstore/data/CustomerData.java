package org.jastka4.digitalgamesstore.data;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class CustomerData implements Serializable {
    @Serial
    private static final long serialVersionUID = -2054386655979281969L;

    private String name;
    private String address;
    private String email;
    private String phone;
}
