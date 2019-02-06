package model.entity;

//
//@Getter
//@Setter
//@Builder
//@AllArgsConstructor

import model.Email;

public class DefaultEmail implements Email {

    public DefaultEmail(boolean isHTML, String[] to, String from, String[] cc, String[] bcc, String body, String subject, String replyTo) {
        this.isHTML = isHTML;
        this.to = to;
        this.from = from;
        this.cc = cc;
        this.bcc = bcc;
        this.body = body;
        this.subject = subject;
        this.replyTo = replyTo;
    }

    private boolean isHTML;
    private String[] to;
    private String from;
    private String[] cc;
    private String[] bcc;
    private String body;
    private String subject;
    private String replyTo;


    public static EmailBuilder builder(){
        return new EmailBuilder();
    }

    public static class EmailBuilder{

        private boolean isHTML;
        private String[] to;
        private String from;
        private String[] cc;
        private String[] bcc;
        private String body;
        private String subject;
        private String replyTo;

        public DefaultEmail build(){
            return new DefaultEmail(this.isHTML,this.to,this.from,this.cc,this.bcc,this.body,this.subject,this.replyTo);
        }
        public EmailBuilder isHTML(boolean isHTML){
            this.isHTML = isHTML;
            return this;
        }

        public EmailBuilder to(String[] to){
            this.to = to;
            return this;
        }

        public EmailBuilder cc(String[] cc){
            this.cc = cc;
            return this;
        }

        public EmailBuilder bcc(String[] bcc){
            this.bcc = bcc;
            return this;
        }


        public EmailBuilder from(String from){
            this.from = from;
            return this;
        }

        public EmailBuilder body(String body){
            this.body = body;
            return this;
        }

        public EmailBuilder subject(String subject){
            this.subject = subject;
            return this;
        }

        public EmailBuilder replyTo(String replyTo){
            this.replyTo = replyTo;
            return this;
        }
    }





    public boolean isHTML() {
        return isHTML;
    }

    public void setHTML(boolean HTML) {
        isHTML = HTML;
    }

    public String[] getTo() {
        return to;
    }

    public void setTo(String[] to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String[] getCc() {
        return cc;
    }

    public void setCc(String[] cc) {
        this.cc = cc;
    }


    public String[] getBcc() {
        return bcc;
    }

    public void setBcc(String[] bcc) {
        this.bcc = bcc;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getReplyTo() {
        return replyTo;
    }

    public void setReplyTo(String replyTo) {
        this.replyTo = replyTo;
    }
}
