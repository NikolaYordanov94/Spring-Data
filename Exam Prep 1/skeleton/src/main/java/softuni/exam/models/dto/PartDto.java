package softuni.exam.models.dto;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.*;

public class PartDto {

    @Expose
    @Size(min = 2, max = 19)
    @NotNull
    private String partName;

    @Expose
    @DecimalMin("10.0")
    @DecimalMax("2000.0")
    @NotNull
    private Double price;

    @Expose
    @Positive
    @NotNull
    private Integer quantity;

    public PartDto() {
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
