package integration.email;


import model.Email;

public interface EmailClient {

    boolean sendEmail(Email email);
}
