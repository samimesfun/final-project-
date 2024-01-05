package org.perscholas.casestudy.formbean;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class CreateCarFormBean {

    private Integer id;

    @NotEmpty(message = "Category Name is Required")
    @Length(max = 45, message = "Category must be less than 45 characters.")
    private String category;

    @NotEmpty(message = "Model Name is Required")
    @Length(max = 45, message = "Model must be less than 45 characters.")
    private String model;

    @NotNull(message = "Year cannot be null")
    @Digits(integer = 10, fraction = 0, message = "Year must be a number with no decimal places")
    private Integer year;

    @NotNull(message = "Price cannot be null")
    @Digits(integer = 10, fraction = 2, message = "Price must be a number with up to 10 digits, and 2 decimal places")
    private Double price;

    private String imageUrl;


}
