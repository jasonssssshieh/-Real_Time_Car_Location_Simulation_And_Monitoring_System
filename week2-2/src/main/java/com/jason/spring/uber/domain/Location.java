package com.jason.spring.uber.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Date;

//domain class
//@entity 表明是location是被持久化的. 因为entity是javax.persience天下的
//@table 是map我们的location 去我们的DB
//lombok的一个注解,消除代码中的构造方法，getter/setter等代码，使我们写的类更加简洁
//JsonInclude(JsonInclude.Include.NON_NULL)表示如果值为null,则不返回

@Table(name = "LOCATION")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@RequiredArgsConstructor
@Entity
public class Location {

    public enum GpsStatus{
        EXCELLENT, OK, UNRELIABLE, BAD, NOFIX, UNKNOWN;
    }

    public enum VehicleMovementType {
        IN_MOTION, STOPPED;

        public boolean isMoving() {
            return this != STOPPED;
        }
    }

    @Id
    @GeneratedValue
    private long id;

    /*
     * The @Embeddable annotation allows to specify a class whose instances are stored as intrinsic part of the owning entity.
     * This annotation has no attributes.
     * The @Embedded annotation is used to specify a persistent field or property of an entity whose value is an instance of an embeddable class. By default, column definitions specified in the @Embeddable class apply to the table of the owning entity
     * but you can override them using@AttributeOverride
     * */
    @Embedded
    @AttributeOverride(name = "engineMake", column = @Column(name = "unit_engine_make"))
    private final UnitInfo unitInfo;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "fmi", column = @Column(name = "unit_fmi")),
            @AttributeOverride(name = "spn", column = @Column(name = "unit_spn"))})
    private UnitFault unitFault;
    private double latitude;
    private double longitude;
    private String heading;
    private double gpsSpeed;
    private GpsStatus gpsStatus;
    private double odometer;
    private double totalEngineTime;
    private double totalIdleTime;
    private double totalFuelUsage;
    private String address;
    private Date timestamp = new Date();
    private String tspProvider;
    private VehicleMovementType vehicleMovementType = VehicleMovementType.STOPPED;
    private String serviceType;
    @Embedded
    private FaultCode faultCode;

    @SuppressWarnings("unused")
    private Location(){
        this.unitInfo = null;
        this.faultCode = null;
    }

    @JsonCreator
    @JsonIgnoreProperties(ignoreUnknown = true)
    private Location(@JsonProperty("vin") String vin) {
        this.unitInfo = new UnitInfo(vin);
    }

    public String getVin(){
        return this.unitInfo == null ? null : this.unitInfo.getUnitVin();
    }
}
