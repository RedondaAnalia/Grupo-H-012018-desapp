package model.states.post;

import model.interfaces.IPostState;

public class ReservedPostST extends IPostState {

    @Override
    public boolean isReserved(){
        return true;
    };

    @Override
    public String toString(){
        return "reserved";
    }

}
