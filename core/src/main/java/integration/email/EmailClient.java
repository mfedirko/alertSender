package integration.email;


import model.entity.api.Email;

public interface EmailClient {

    boolean sendEmail(Email email);
}
