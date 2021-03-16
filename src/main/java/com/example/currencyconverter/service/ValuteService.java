package com.example.currencyconverter.service;

import com.example.currencyconverter.domain.Valute;
import com.example.currencyconverter.repos.ValuteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import java.net.URL;

@Service
public class ValuteService {

    @Autowired
    public ValuteService(ValuteRepo valuteRepo) {
        this.valuteRepo = valuteRepo;
    }

    private final ValuteRepo valuteRepo;

    private void saveAllCourses() {

        try {
            Document doc = DocumentBuilderFactory.newInstance()
                    .newDocumentBuilder().parse(
                            new URL("http://www.cbr.ru/scripts/XML_daily.asp").openStream()
                    );

            NodeList valutes = doc.getDocumentElement().getChildNodes();

            for (int i = 0; i < valutes.getLength(); i++) {
                Node val = valutes.item(i);
                String id = val.getAttributes().getNamedItem("ID").getNodeValue();
                String numCode = val.getChildNodes().item(0).getChildNodes().item(0).getNodeValue();
                String charCode = val.getChildNodes().item(1).getChildNodes().item(0).getNodeValue();
                Integer nominal = Integer.parseInt(
                        val.getChildNodes().item(2).getChildNodes().item(0).getNodeValue());

                String name = val.getChildNodes().item(3).getChildNodes().item(0).getNodeValue();

                Double value = Double.parseDouble(
                        val.getChildNodes().item(4)
                                .getChildNodes().item(0).getNodeValue().replace(',', '.')
                );

                valuteRepo.save(new Valute(id, numCode, charCode, nominal, name, value/nominal));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Utility method to parse string containing "," into Double value
     * @param str contains "," but not '.' so we cant parse it
     * @return the double number
     */
    public static Double getValue(String str) {
        String[] strings = str.split(",");
        return Double.parseDouble(strings[0] +"." + strings[1]);
    }
}
