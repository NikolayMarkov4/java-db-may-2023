package softuni.exam.models.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.util.Date;

@XmlRootElement(name = "task")
@XmlAccessorType(XmlAccessType.FIELD)
public class TaskImportDto {

    @NotNull
    @Positive
    @XmlElement
    private BigDecimal price;

    @NotNull
    @XmlElement
    private String date;

    @NotNull
    @XmlElement
    private CarBase car;

    @NotNull
    @XmlElement
    private MechanicBase mechanic;

    @NotNull
    @XmlElement
    private PartBase part;

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public CarBase getCar() {
        return car;
    }

    public void setCar(CarBase car) {
        this.car = car;
    }

    public MechanicBase getMechanic() {
        return mechanic;
    }

    public void setMechanic(MechanicBase mechanic) {
        this.mechanic = mechanic;
    }

    public PartBase getPart() {
        return part;
    }

    public void setPart(PartBase part) {
        this.part = part;
    }
}
