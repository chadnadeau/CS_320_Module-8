// Chad Nadeau

package test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows; 

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import contact.Contact;
import contact.ContactService;

public class ContactServiceTest {

    private ContactService contactService;

    @BeforeEach
    public void setUp() {
        contactService = new ContactService();
    }

    @Test
    public void testAddContact() {
        Contact contact = new Contact("12345", "John", "Doe", "1234567890", "123 Main St");
        contactService.addContact(contact);
        assertEquals(contact, contactService.getContactByID("12345"));
    }

    @Test
    public void testAddContactWithDuplicateID() {
        Contact contact1 = new Contact("54321", "Jane", "Smith", "9876543210", "456 Elm St");
        Contact contact2 = new Contact("54321", "Alice", "Johnson", "5555555555", "789 Oak Ave");
        contactService.addContact(contact1);
        assertThrows(IllegalArgumentException.class, () -> contactService.addContact(contact2));
    }

    @Test
    public void testDeleteContact() {
        Contact contact = new Contact("99999", "Test", "Contact", "1111111111", "Test Address");
        contactService.addContact(contact);
        assertNotNull(contactService.getContactByID("99999"));
        contactService.deleteContact("99999");
        assertNull(contactService.getContactByID("99999"));
    }

    @Test
    public void testDeleteNonExistentContact() {
        assertThrows(IllegalArgumentException.class, () -> contactService.deleteContact("11111"));
    }

    @Test
    public void testUpdateContactFirstName() {
        Contact contact = new Contact("77777", "Old", "Name", "2222222222", "Old Address");
        contactService.addContact(contact);
        contactService.updateContactFirstName("77777", "New"); 
        Contact updatedContact = contactService.getContactByID("77777");
        assertEquals("New", updatedContact.getFirstName());
    }

    @Test
    public void testUpdateContactLastName() {
        Contact contact = new Contact("77777", "Old", "Name", "2222222222", "Old Address");
        contactService.addContact(contact);
        contactService.updateContactLastName("77777", "NewLast"); 
        Contact updatedContact = contactService.getContactByID("77777");
        assertEquals("NewLast", updatedContact.getLastName());
    }

    @Test
    public void testUpdateContactPhone() {
        Contact contact = new Contact("77777", "Old", "Name", "2222222222", "Old Address");
        contactService.addContact(contact);
        contactService.updateContactPhone("77777", "3333333333"); 
        Contact updatedContact = contactService.getContactByID("77777");
        assertEquals("3333333333", updatedContact.getPhone());
    }

    @Test
    public void testUpdateContactAddress() {
        Contact contact = new Contact("77777", "Old", "Name", "2222222222", "Old Address");
        contactService.addContact(contact);
        contactService.updateContactAddress("77777", "New Address");
        Contact updatedContact = contactService.getContactByID("77777");
        assertEquals("New Address", updatedContact.getAddress());
    }

    @Test
    public void testUpdateNonExistentContact() {
        assertThrows(IllegalArgumentException.class, () -> contactService.updateContactFirstName("55555", "New"));
    }

    @Test
    public void testUpdateContactWithInvalidFields() {
        Contact contact = new Contact("88888", "Test", "Contact", "1234567890", "Test Address");
        contactService.addContact(contact);
        assertThrows(IllegalArgumentException.class, () -> contactService.updateContactFirstName("88888", "ThisIsAVeryLongFirstName"));
    }
}