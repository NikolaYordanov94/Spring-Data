package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.SellerRootDto;
import softuni.exam.models.dto.SellerXmlSeedDto;
import softuni.exam.models.entity.Seller;
import softuni.exam.repository.SellerRepository;
import softuni.exam.service.SellerService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Service
public class SellerServiceImpl implements SellerService {

    private String SELLERS_FILE_PATH = "src/main/resources/files/xml/sellers.xml";

    private SellerRepository sellerRepository;
    private XmlParser xmlParser;
    private ModelMapper modelMapper;
    private ValidationUtil validationUtil;


    public SellerServiceImpl(SellerRepository sellerRepository, XmlParser xmlParser, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.sellerRepository = sellerRepository;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return sellerRepository.count() > 0;
    }

    @Override
    public String readSellersFromFile() throws IOException {
        return Files.readString(Path.of(SELLERS_FILE_PATH));
    }

    @Override
    public String importSellers() throws IOException, JAXBException {

        String filePath = readSellersFromFile();

        List<SellerXmlSeedDto> sellersDto = xmlParser.fromFile(filePath, SellerRootDto.class)
                .getSellers();

        StringBuilder sb = new StringBuilder();

        for (SellerXmlSeedDto sellerDto : sellersDto) {
            if(!validationUtil.isValid(sellerDto)){
                sb.append("Invalid seller").append(System.lineSeparator());
                continue;
            }

            Seller seller = modelMapper.map(sellerDto, Seller.class);
            sellerRepository.save(seller);

            sb.append(String.format("Successfully import seller %s - %s",
                    seller.getLastName(), seller.getEmail()));
        }

        return sb.toString().trim();
    }
}
