package model.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.entities.User;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-04-20T05:56:14")
@StaticMetamodel(Message.class)
public class Message_ { 

    public static volatile SingularAttribute<Message, Date> date;
    public static volatile SingularAttribute<Message, User> receiver;
    public static volatile SingularAttribute<Message, User> sender;
    public static volatile SingularAttribute<Message, String> subject;
    public static volatile SingularAttribute<Message, String> body;
    public static volatile SingularAttribute<Message, Integer> idmessage;
    public static volatile SingularAttribute<Message, Integer> seen;

}