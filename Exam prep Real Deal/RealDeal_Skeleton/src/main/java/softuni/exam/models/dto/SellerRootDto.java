package softuni.exam.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "sellers")
@XmlAccessorType(XmlAccessType.FIELD)
public class SellerRootDto {

    @XmlElement(name = "seller")
    private List<SellerXmlSeedDto> sellers;

    public SellerRootDto() {
    }

    public List<SellerXmlSeedDto> getSellers() {
        return sellers;
    }

    public void setSellers(List<SellerXmlSeedDto> sellers) {
        this.sellers = sellers;
    }
}
