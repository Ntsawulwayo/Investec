package za.co.psybergate.sifiso.addresses;

import java.util.Objects;

/**
 *
 * @author SIFISO
 */
public class Type {
    private String code;
    private String name;

    public Type() {
    }

    public Type(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.code);
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
        final Type other = (Type) obj;
        return Objects.equals(this.code, other.code);
    }

    @Override
    public String toString() {
        return "Type{" + "code=" + code + ", name=" + name + '}';
    }
}
