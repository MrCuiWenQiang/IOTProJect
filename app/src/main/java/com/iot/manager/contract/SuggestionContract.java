package com.iot.manager.contract;

/**
 * Function :
 * Remarks  :
 * Created by Mr.C on 2019/3/22 0022.
 */
public class SuggestionContract {
    public interface View{
        void commit_Success(String msg);
        void commit_Fail(String msg);
    }

    public interface Presenter{
        void commitSuggestion(String content);
    }
}
