public class main {

    public static void main(String[] args) {
        Connect c = Connect.getInstance();
        c.QueryRecord( "INSERT INTO User (ID,phone,token_verify,name,phone_verified,discription) " +
                "VALUES (4, '00000000400','TRUE', 'aminhm','FALSE','helloooo amin is here for seecond time!!!');");
    }
}