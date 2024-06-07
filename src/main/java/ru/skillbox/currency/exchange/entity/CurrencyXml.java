package ru.skillbox.currency.exchange.entity;

import lombok.Setter;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.Objects;

@Setter
@XmlRootElement(name = "ValCurs")
public final class CurrencyXml {
    private String date;
    private String name;
    private List<CurrencyDetailsXml> valutes;

    public CurrencyXml() {
    }

    @XmlAttribute(name = "Date")
    public String getDate() {
        return date;
    }

    @XmlAttribute(name = "name")
    public String getName() {
        return name;
    }

    @XmlElement(name = "Valute")
    public List<CurrencyDetailsXml> getValutes() {
        return valutes;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        CurrencyXml that = (CurrencyXml) obj;
        return Objects.equals(this.date, that.date) &&
                Objects.equals(this.valutes, that.valutes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, valutes);
    }

    @Override
    public String toString() {
        return "CurrencyXml[" +
                "date=" + date + ", " +
                "valutes=" + valutes + ']';
    }

}
