package webapp.BO.impl;

import webapp.BO.LogInBO;
import webapp.DAO.LogInDAO;
import webapp.DAO.impl.LogInDAOImpl;

public class LogInBOImpl implements LogInBO {
    private LogInDAO logInDAO;

    public LogInBOImpl() {
        if(logInDAO == null){
            logInDAO = new LogInDAOImpl();
        }
    }

    @Override
    public boolean checkCredentials(String username, String password) {
        return logInDAO.checkCredentials(username, password);
    }
}
