package za.co.psybergate.sifiso.addresses;

import java.util.Objects;

/**
 *
 * @author SIFISO
 */
public class AddressLineDetail {
   private String lineOne;
   private String lineTwo;

    public AddressLineDetail() {
    }

    public AddressLineDetail(String lineOne, String lineTwo) {
        this.lineOne = lineOne;
        this.lineTwo = lineTwo;
    }

    public String getLineOne() {
        return lineOne;
    }

    public void setLineOne(String lineOne) {
        this.lineOne = lineOne;
    }

    public String getLineTwo() {
        return lineTwo;
    }

    public void setLineTwo(String lineTwo) {
        this.lineTwo = lineTwo;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.lineOne);
        hash = 29 * hash + Objects.hashCode(this.lineTwo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AddressLineDetail other = (AddressLineDetail) obj;
        if (!Objects.equals(this.lineOne, other.lineOne)) {
            return false;
        }
        return Objects.equals(this.lineTwo, other.lineTwo);
    }
   
   
}
