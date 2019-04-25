package org.typroject.tyboot.core.foundation.utils;

/**
 * Created by magintursh on 2017/12/3.
 */

import java.util.Collection;

public interface TreeNode<N,ID> {
    ID getMyParentId();

    ID getMyId();

    Collection<N> getChilds();

    void setChilds(Collection<N> childs);
}
