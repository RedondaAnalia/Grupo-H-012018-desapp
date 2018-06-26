package model.interfaces;

import model.Entity;

public abstract class IPostState extends Entity {

    public boolean isReserved(){
        return false;
    };

    public boolean isAvailable(){
        return false;
    };
}
