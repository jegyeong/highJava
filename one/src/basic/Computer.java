package basic;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Computer object definition
 */


public class Computer {

    private String serialNo;
    private String model;
    private String os;
    private String cpu;
    private String ram;
    private Double price;

    private static final NumberFormat formatter = new DecimalFormat("#0.00");

    Computer() {
        serialNo = "";
        model = "";
        os = "";
        cpu = "";
        ram = "";
        price = 0.0;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public Double getPrice() { return price; }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
        public String toString() {
            return "Computer:  SerialNo:" + this.serialNo + ", Model:" + this.model +
                    ", OS:" + this.os + ", CPU:=" + this.cpu + ",  RAM:" + this.ram +
                    ", Price:" + formatter.format(this.price);
        }

}
