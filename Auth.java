class Auth
{
    public int validateClass(String id, String pass){

        String username="class";
        String password="12345";
        if(username.equals(id) && password.equals(pass)){

            return 1;
        }
        return 0;
    }

    public int validateStudent(String id, String pass){

        String username="student";
        String password="12345";
        if(username.equals(id) && password.equals(pass)){

            return 1;
        }

        return 0;
    }

    public int validateaAssignment(String id, String pass){

        String username="assignment";
        String password="12345";
        if(username.equals(id) && password.equals(pass)){

            return 1;
        }

        return 0;
    }

}
