/**
 *
 */
package com.jund.framework.core.entity;

import java.io.Serializable;

/**
 * Created by zhijund on 2017/9/2.
 */
public abstract class AbstractEntity implements Serializable, Cloneable {

    public abstract String toString();

    public abstract boolean equals(Object o);

    public abstract int hashCode();

}
