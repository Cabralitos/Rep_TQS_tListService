package com.mycompany.servicetlist;

import com.mycompany.servicetlist.Task;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-07-12T14:14:16")
@StaticMetamodel(Member1.class)
public class Member1_ { 

    public static volatile SingularAttribute<Member1, String> username;
    public static volatile SingularAttribute<Member1, Integer> idmember;
    public static volatile ListAttribute<Member1, Task> taskList;
    public static volatile SingularAttribute<Member1, String> password;

}