package com.imooc.pojo;

import java.io.Serializable;

/**
 * CREATED by buer on 2017/3/10.
 */
public abstract class BaseObject implements Serializable, Cloneable{

    private static final long serialVersionUID = 1L;

    @Override
    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }

    public boolean isUpdated;

    public boolean isUpdated() {
        return isUpdated;
    }

    public void setUpdated(boolean updated) {
        isUpdated = updated;
    }
}

