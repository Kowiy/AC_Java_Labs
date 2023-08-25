package Jsonproj.Labassignment;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class Address {
    private String firstName;
    private String lastName;
    private String spouseFirstName;
    private String spouseLastName;
    private String streetNumber;
    private String streetName;
    private String streetType;
    private String streetOrientation;
    private String cityName;
    private String province;

    public void addLine(String line) {
        if (firstName == null) {
            parseName(line);
        } else if (streetNumber == null) {
            streetNumber = line.trim();
        } else if (streetName == null) {
            streetName = line.trim();
        } else if (streetType == null) {
            streetType = line.trim();
        } else if (streetOrientation == null) {
            streetOrientation = line.trim();
        } else if (cityName == null) {
            cityName = line.trim();
        } else if (province == null) {
            province = line.trim();
        }
    }

    private void parseName(String line) {
        String[] names = line.split(" and ");
        if (names.length > 0) {
            String[] firstNameParts = names[0].split(" ");
            firstName = firstNameParts[0].trim();
            if (firstNameParts.length > 1) {
                lastName = firstNameParts[1].trim();
            }
        }
        if (names.length > 1) {
            String[] spouseNameParts = names[1].split(" ");
            spouseFirstName = spouseNameParts[0].trim();
            if (spouseNameParts.length > 1) {
                spouseLastName = spouseNameParts[1].trim();
            } else {
                spouseLastName = lastName;
            }
        }
    }



    public String toOutputFormat() {
        return String.format("%s,%s,%s,%s,%s,%s,%s,%s",
                firstName, lastName, spouseFirstName, spouseLastName,
                streetNumber, streetName, streetType, cityName);
    }
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSpouseFirstName() {
        return spouseFirstName;
    }

    public void setSpouseFirstName(String spouseFirstName) {
        this.spouseFirstName = spouseFirstName;
    }

    public String getSpouseLastName() {
        return spouseLastName;
    }

    public void setSpouseLastName(String spouseLastName) {
        this.spouseLastName = spouseLastName;
    }
    public static Address fromCSV(String csvLine) {
        String[] fields = csvLine.split(",");
        Address address = new Address();

        address.setFirstName(fields[0]);
        address.setLastName(fields[1]);
        address.setSpouseFirstName(fields[2]);
        address.setSpouseLastName(fields[3]);
        return address;
    }
    
    public String getUrlEncodedAddress() throws UnsupportedEncodingException {
        StringBuilder addressBuilder = new StringBuilder();

        if (streetNumber != null && !streetNumber.isEmpty()) {
            addressBuilder.append(streetNumber).append(" ");
        }
        if (streetName != null && !streetName.isEmpty()) {
            addressBuilder.append(streetName).append(" ");
        }
        if (streetType != null && !streetType.isEmpty()) {
            addressBuilder.append(streetType).append(" ");
        }
        if (streetOrientation != null && !streetOrientation.isEmpty()) {
            addressBuilder.append(streetOrientation).append(" ");
        }
        if (cityName != null && !cityName.isEmpty()) {
            addressBuilder.append(cityName).append(" ");
        }
        if (province != null && !province.isEmpty()) {
            addressBuilder.append(province);
        }

        return URLEncoder.encode(addressBuilder.toString(), "UTF-8");
    }


}
