package csvpojo;

import com.opencsv.bean.CsvBindByName;

public class PojoClass {

    @CsvBindByName(column = "firstname")
    private String firstname;

    @CsvBindByName(column = "lastname")
    private String lastname;

    @CsvBindByName(column = "totalprice")
    private int totalprice;

    @CsvBindByName(column = "depositpaid")
    private boolean depositpaid;

    @CsvBindByName(column = "checkin")
    private String checkin;

    @CsvBindByName(column = "checkout")
    private String checkout;

    @CsvBindByName(column = "additionalneeds")
    private String additionalneeds;

    public String getFirstname(){
        return firstname;
    }

    public String getLastname(){
        return lastname;
    }

    public int getTotalprice(){
        return totalprice;
    }

    public boolean getDepositpaid(){
        return depositpaid;
    }

    public String getCheckin(){
        return checkin;
    }

    public String getCheckout(){
        return checkout;
    }

    public String getAdditionalneeds(){
        return additionalneeds;
    }


}
