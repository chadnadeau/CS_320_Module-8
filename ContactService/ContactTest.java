// Chad Nadeau

package test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import contact.Contact;

public class ContactTest {

    @Test
    public void testValidContactCreation() {
        String contactID = "12345";
        String firstName = "John";
        String lastName = "Doe";
        String phone = "1234567890";
        String address = "123 Main St";

        Contact contact = new Contact(contactID, firstName, lastName, phone, address);

        Assertions.assertEquals(contactID, contact.getContactID());
        Assertions.assertEquals(firstName, contact.getFirstName());
        Assertions.assertEquals(lastName, contact.getLastName());
        Assertions.assertEquals(phone, contact.getPhone());
        Assertions.assertEquals(address, contact.getAddress());
    }

    @Test
    public void testInvalidContactCreation() {
        // Test invalid contact ID length
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Contact("12345678901", "John", "Doe", "1234567890", "123 Main St");
        });

        // Test invalid first name length
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Contact("12345", "Johnnnnnnnnnn", "Doe", "1234567890", "123 Main St");
        });

        // Test invalid last name length
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Contact("12345", "John", "Doooooooooeee", "1234567890", "123 Main St");
        });

        // Test invalid phone number length
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Contact("12345", "John", "Doe", "123", "123 Main St");
        });

        // Test invalid address length
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Contact("12345", "John", "Doe", "1234567890", "This is an address longer than 30 characters.");
        });
    }

    @Test
    public void testNullFieldValues() {
        // Test null contact ID
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Contact(null, "John", "Doe", "1234567890", "123 Main St");
        });

        // Test null first name
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Contact("12345", null, "Doe", "1234567890", "123 Main St");
        });

        // Test null last name
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Contact("12345", "John", null, "1234567890", "123 Main St");
        });

        // Test null phone number
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Contact("12345", "John", "Doe", null, "123 Main St");
        });

        // Test null address
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Contact("12345", "John", "Doe", "1234567890", null);
        });
    }
}