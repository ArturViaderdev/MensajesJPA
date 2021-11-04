package model.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.entities.Event;
import model.entities.Message;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-04-20T05:56:14")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile SingularAttribute<User, String> password;
    public static volatile ListAttribute<User, Event> eventList;
    public static volatile ListAttribute<User, Message> messageList;
    public static volatile SingularAttribute<User, String> surname;
    public static volatile SingularAttribute<User, String> name;
    public static volatile ListAttribute<User, Message> messageList1;
    public static volatile SingularAttribute<User, Integer> type;
    public static volatile SingularAttribute<User, String> username;

}