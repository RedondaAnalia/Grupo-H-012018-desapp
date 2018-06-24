package model.states.post;

import model.interfaces.IPostState;

public class AvailablePostST extends IPostState {

    @Override
    public boolean isAvailable(){
        return true;
    };

    @Override
    public String toString(){
        return "available";
    }
}
