package model;

public interface Email {

     boolean isHTML() ;
     String[] getTo() ;
     String getFrom() ;
     String[] getCc() ;
     String[] getBcc() ;
     String getBody() ;
     String getSubject() ;
     String getReplyTo();
}
