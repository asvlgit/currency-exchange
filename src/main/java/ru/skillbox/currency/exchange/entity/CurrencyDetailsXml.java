package ru.skillbox.currency.exchange.entity;

import lombok.Setter;
import ru.skillbox.currency.exchange.tools.adapter.StringToDoubleAdapter;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Objects;

@Setter
public final class CurrencyDetailsXml {
    private String id;
    private String name;
    private Integer isoNumCode;
    private String isoCharCode;
    private Integer nominal;
    private Double value;

    public CurrencyDetailsXml() {
    }

    @XmlAttribute(name = "ID")
    public String getId() {
        return id;
    }

    @XmlElement(name = "Name")
    public String getName() {
        return name;
    }

    @XmlElement(name = "NumCode")
    public Integer getIsoNumCode() {
        return isoNumCode;
    }

    @XmlElement(name = "CharCode")
    public String getIsoCharCode() {
        return isoCharCode;
    }

    @XmlElement(name = "Nominal")
    public Integer getNominal() {
        return nominal;
    }

    @XmlElement(name = "Value")
    @XmlJavaTypeAdapter(StringToDoubleAdapter.class)
    public Double getValue() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        CurrencyDetailsXml that = (CurrencyDetailsXml) obj;
        return Objects.equals(this.id, that.id) &&
                Objects.equals(this.isoNumCode, that.isoNumCode) &&
                Objects.equals(this.isoCharCode, that.isoCharCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isoNumCode, isoCharCode);
    }

    @Override
    public String toString() {
        return "CurrencyDetailsXml[" +
                "id=" + id + ", " +
                "numCode=" + isoNumCode + ", " +
                "charCode=" + isoCharCode + ']';
    }

}