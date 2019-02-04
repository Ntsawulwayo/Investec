package za.co.psybergate.sifiso.addresses;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author SIFISO
 */
public class PretifyAddress {

    //(a) Pretty Print Address
    public String prettyPrintAddress(Address address) {
        return address.getType().getName()
                + "\n" + address.getAddressLineDetail().getLineOne()
                + "\n" + address.getAddressLineDetail().getLineTwo()
                + "\n" + address.getCityOrTown()
                + "\n" + address.getProvinceOrState().getName()
                + "\n" + address.getPostalCode()
                + "\n" + address.getCountry().getName();
    }

    //(b) Pretty Print all Addresses
    public String prettyPrintAllAddresses(List<Address> addresses) {
        String prettyAddresses = "";
        prettyAddresses = addresses.stream().map((address) -> prettyPrintAddress(address) + "\n\n").reduce(prettyAddresses, String::concat);
        return prettyAddresses;
    }

    private boolean isNumeric(String value) {
        try {
            Integer.parseInt(value);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    //(c) Pretty Print all Addresses of given type
    public String prettyPrintAddressByType(List<Address> addresses, String type) {
        String addressesToPrint = "";
        for (Address address : addresses) {
            if (address.getType().getName().contains(type)) {
                addressesToPrint += prettyPrintAddress(address) + "\n\n";
            }
        }

        return addressesToPrint;
    }

    private boolean isNullOrEmpty(String value) {
        return "".equals(value) || value == null;
    }


    public String  checkAddressInvalidFields(Address address) {
        if (!isNumeric(address.getPostalCode() + "")) {
            return "Postal Code";
        }
        if (address.getCountry() == null) {
            return "Contry";
        }
        if (isNullOrEmpty(address.getAddressLineDetail().getLineOne()) && isNullOrEmpty(address.getAddressLineDetail().getLineTwo())) {
            return "Address Line Details";
        }
        if ("ZA".equals(address.getCountry().getCode()) && isNullOrEmpty(address.getProvinceOrState().getName())) {
            return "Province";
        }

        return "Valid";
    }

    //  (4) Validate an address
    public boolean isValidAddress (Address address) {
        return "Valid".equals(checkAddressInvalidFields(address));
    }
    
    // (5) Vallidate addres and indicate invalid fileds
     public void printAddressesAndValidityStatus(List<Address> addre) {
         addre.forEach(address -> {
             if(isValidAddress(address)) {
                System.out.println("Is Address with id " + address.getId() + " valid?: "+isValidAddress(address));  
             }else{
                System.out.println("Is Address with id " + address.getId() + " valid?: "+isValidAddress(address) +":- Missing: "+checkAddressInvalidFields(address));  
             }
           
         });
     }
    
    
    private static Type setType(JSONObject type) {
        return new Type((String) type.get("code"), (String) type.get("name"));
    }

    private static ProvinceOrState setProvinceOrState(JSONObject provinceOrState) {
        return new ProvinceOrState((String) provinceOrState.get("code"), (String) provinceOrState.get("name"));
    }

    private static AddressLineDetail setAddressLineDetail(JSONObject addressLineDetail) {
        return new AddressLineDetail((String) addressLineDetail.get("line1"), (String) addressLineDetail.get("line2"));
    }

    private static Country setCountry(JSONObject country) {
        return new Country((String) country.get("code"), (String) country.get("name"));
    }

    public static void main(String[] args) {
        JSONParser parser = new JSONParser();
        List<Address> addresses = new ArrayList<>();
        JSONArray addressList = new JSONArray();
        try {
            Object obj = parser.parse(new FileReader("resources/addresses.json"));
            addressList = (JSONArray) obj;

        } catch (IOException | ParseException e) {
        }

        addressList.forEach(address -> {
            Address tempAddress = new Address();
            JSONObject jSONObject = (JSONObject) address;
            tempAddress.setId((String) jSONObject.get("id"));
            tempAddress.setType(setType((JSONObject) jSONObject.get("type")));
            AddressLineDetail addressLineDetail = new AddressLineDetail();
            if (((JSONObject) jSONObject.get("addressLineDetail")) == null) {
                tempAddress.setAddressLineDetail(addressLineDetail);
            } else {
                tempAddress.setAddressLineDetail(setAddressLineDetail(((JSONObject) jSONObject.get("addressLineDetail"))));
            }
            tempAddress.setCityOrTown((String) jSONObject.get("cityOrTown"));
            tempAddress.setCountry(setCountry((JSONObject) jSONObject.get("country")));
            ProvinceOrState provinceOrState = new ProvinceOrState();
            if (((JSONObject) jSONObject.get("provinceOrState")) == null) {
                tempAddress.setProvinceOrState(provinceOrState);
            } else {
                tempAddress.setProvinceOrState(setProvinceOrState(((JSONObject) jSONObject.get("provinceOrState"))));
            }
            tempAddress.setPostalCode((String) jSONObject.get("postalCode"));
            tempAddress.setLastUpdated((String) jSONObject.get("lastUpdated"));
            addresses.add(tempAddress);
        });

        PretifyAddress pretifyAddress = new PretifyAddress();

        //(1) Test Pretty Print Address
        Address address = addresses.get(0);
        System.out.println("(1) Pretty Print Address:\n" + pretifyAddress.prettyPrintAddress(address));
        System.out.println("************END OF QUESTION 1 OUTPUT***************");
           //(2) Test Pretty Print all Addresses
        System.out.println("\n(2) Pretty Print All Addresses:\n" + pretifyAddress.prettyPrintAllAddresses(addresses));
        System.out.println("************END OF QUESTION 2 OUTPUT***************");
        //(3) Test Pretty Print all Addresses of given type
        System.out.println("\n(3) Pretty Print Address By Type:\n" + pretifyAddress.prettyPrintAddressByType(addresses, "Physical"));
        System.out.println("************END OF QUESTION 3 OUTPUT***************");
        
        //(4) Validate Address
        addresses.forEach(addresss -> {
        System.out.println("Is Address with id " + address.getId() + " valid?: "+pretifyAddress.isValidAddress(addresss));
        });
        System.out.println("************END OF QUESTION 4 OUTPUT***************\n\n");
        
        
        pretifyAddress.printAddressesAndValidityStatus(addresses);
        System.out.println("************END OF QUESTION 5 OUTPUT***************");
        
        
    }
}
